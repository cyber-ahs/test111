package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.load.Key;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzy {
    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzz zzb(Context context, String str) throws zzaa {
        zzz zzd = zzd(context, str);
        return zzd != null ? zzd : zzc(context, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzz zzc(Context context, String str) {
        zzz zzzVar = new zzz(zza.zzb(), System.currentTimeMillis());
        zzz zza = zza(context, str, zzzVar, true);
        if (zza != null && !zza.equals(zzzVar)) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Loaded key after generating new one, using loaded one");
            }
            return zza;
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Generated new key");
        }
        zza(context, str, zzzVar);
        return zzzVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Context context) {
        File[] listFiles;
        for (File file : zzb(context).listFiles()) {
            if (file.getName().startsWith("com.google.InstanceId")) {
                file.delete();
            }
        }
    }

    private final zzz zzd(Context context, String str) throws zzaa {
        zzz zze;
        try {
            zze = zze(context, str);
        } catch (zzaa e) {
            e = e;
        }
        if (zze != null) {
            zza(context, str, zze);
            return zze;
        }
        e = null;
        try {
            zzz zza = zza(context.getSharedPreferences("com.google.android.gms.appid", 0), str);
            if (zza != null) {
                zza(context, str, zza, false);
                return zza;
            }
        } catch (zzaa e2) {
            e = e2;
        }
        if (e == null) {
            return null;
        }
        throw e;
    }

    private static KeyPair zzc(String str, String str2) throws zzaa {
        try {
            byte[] decode = Base64.decode(str, 8);
            byte[] decode2 = Base64.decode(str2, 8);
            try {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                return new KeyPair(keyFactory.generatePublic(new X509EncodedKeySpec(decode)), keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decode2)));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 19);
                sb.append("Invalid key stored ");
                sb.append(valueOf);
                Log.w("FirebaseInstanceId", sb.toString());
                throw new zzaa(e);
            }
        } catch (IllegalArgumentException e2) {
            throw new zzaa(e2);
        }
    }

    private final zzz zze(Context context, String str) throws zzaa {
        File zzf = zzf(context, str);
        if (zzf.exists()) {
            try {
                return zza(zzf);
            } catch (zzaa | IOException e) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String valueOf = String.valueOf(e);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 40);
                    sb.append("Failed to read key from file, retrying: ");
                    sb.append(valueOf);
                    Log.d("FirebaseInstanceId", sb.toString());
                }
                try {
                    return zza(zzf);
                } catch (IOException e2) {
                    String valueOf2 = String.valueOf(e2);
                    StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 45);
                    sb2.append("IID file exists, but failed to read from it: ");
                    sb2.append(valueOf2);
                    Log.w("FirebaseInstanceId", sb2.toString());
                    throw new zzaa(e2);
                }
            }
        }
        return null;
    }

    private final zzz zza(Context context, String str, zzz zzzVar, boolean z) {
        String zzv;
        String zzw;
        long j;
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Writing key to properties file");
        }
        Properties properties = new Properties();
        zzv = zzzVar.zzv();
        properties.setProperty("pub", zzv);
        zzw = zzzVar.zzw();
        properties.setProperty("pri", zzw);
        j = zzzVar.zzbs;
        properties.setProperty("cre", String.valueOf(j));
        File zzf = zzf(context, str);
        try {
            zzf.createNewFile();
            RandomAccessFile randomAccessFile = new RandomAccessFile(zzf, "rw");
            FileChannel channel = randomAccessFile.getChannel();
            try {
                channel.lock();
                if (z && channel.size() > 0) {
                    try {
                        channel.position(0L);
                        zzz zza = zza(channel);
                        if (channel != null) {
                            zza((Throwable) null, channel);
                        }
                        zza((Throwable) null, randomAccessFile);
                        return zza;
                    } catch (zzaa | IOException e) {
                        if (Log.isLoggable("FirebaseInstanceId", 3)) {
                            String valueOf = String.valueOf(e);
                            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 64);
                            sb.append("Tried reading key pair before writing new one, but failed with: ");
                            sb.append(valueOf);
                            Log.d("FirebaseInstanceId", sb.toString());
                        }
                    }
                }
                channel.position(0L);
                properties.store(Channels.newOutputStream(channel), (String) null);
                if (channel != null) {
                    zza((Throwable) null, channel);
                }
                zza((Throwable) null, randomAccessFile);
                return zzzVar;
            } finally {
            }
        } catch (IOException e2) {
            String valueOf2 = String.valueOf(e2);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 21);
            sb2.append("Failed to write key: ");
            sb2.append(valueOf2);
            Log.w("FirebaseInstanceId", sb2.toString());
            return null;
        }
    }

    private static File zzb(Context context) {
        File noBackupFilesDir = ContextCompat.getNoBackupFilesDir(context);
        if (noBackupFilesDir == null || !noBackupFilesDir.isDirectory()) {
            Log.w("FirebaseInstanceId", "noBackupFilesDir doesn't exist, using regular files directory instead");
            return context.getFilesDir();
        }
        return noBackupFilesDir;
    }

    private static File zzf(Context context, String str) {
        String sb;
        if (TextUtils.isEmpty(str)) {
            sb = "com.google.InstanceId.properties";
        } else {
            try {
                String encodeToString = Base64.encodeToString(str.getBytes(Key.STRING_CHARSET_NAME), 11);
                StringBuilder sb2 = new StringBuilder(String.valueOf(encodeToString).length() + 33);
                sb2.append("com.google.InstanceId_");
                sb2.append(encodeToString);
                sb2.append(".properties");
                sb = sb2.toString();
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
        return new File(zzb(context), sb);
    }

    private final zzz zza(File file) throws zzaa, IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            FileChannel channel = fileInputStream.getChannel();
            channel.lock(0L, Long.MAX_VALUE, true);
            zzz zza = zza(channel);
            if (channel != null) {
                zza((Throwable) null, channel);
            }
            zza((Throwable) null, fileInputStream);
            return zza;
        } finally {
        }
    }

    private static zzz zza(FileChannel fileChannel) throws zzaa, IOException {
        Properties properties = new Properties();
        properties.load(Channels.newInputStream(fileChannel));
        String property = properties.getProperty("pub");
        String property2 = properties.getProperty("pri");
        if (property == null || property2 == null) {
            throw new zzaa("Invalid properties file");
        }
        try {
            return new zzz(zzc(property, property2), Long.parseLong(properties.getProperty("cre")));
        } catch (NumberFormatException e) {
            throw new zzaa(e);
        }
    }

    private static zzz zza(SharedPreferences sharedPreferences, String str) throws zzaa {
        String string = sharedPreferences.getString(zzaw.zzd(str, "|P|"), null);
        String string2 = sharedPreferences.getString(zzaw.zzd(str, "|K|"), null);
        if (string == null || string2 == null) {
            return null;
        }
        return new zzz(zzc(string, string2), zzb(sharedPreferences, str));
    }

    private final void zza(Context context, String str, zzz zzzVar) {
        String zzv;
        String zzw;
        long j;
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.android.gms.appid", 0);
        try {
            if (zzzVar.equals(zza(sharedPreferences, str))) {
                return;
            }
        } catch (zzaa unused) {
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Writing key to shared preferences");
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String zzd = zzaw.zzd(str, "|P|");
        zzv = zzzVar.zzv();
        edit.putString(zzd, zzv);
        String zzd2 = zzaw.zzd(str, "|K|");
        zzw = zzzVar.zzw();
        edit.putString(zzd2, zzw);
        String zzd3 = zzaw.zzd(str, "cre");
        j = zzzVar.zzbs;
        edit.putString(zzd3, String.valueOf(j));
        edit.commit();
    }

    private static long zzb(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(zzaw.zzd(str, "cre"), null);
        if (string != null) {
            try {
                return Long.parseLong(string);
            } catch (NumberFormatException unused) {
                return 0L;
            }
        }
        return 0L;
    }

    private static /* synthetic */ void zza(Throwable th, FileChannel fileChannel) {
        if (th == null) {
            fileChannel.close();
            return;
        }
        try {
            fileChannel.close();
        } catch (Throwable th2) {
            com.google.android.gms.internal.firebase_messaging.zzc.zza(th, th2);
        }
    }

    private static /* synthetic */ void zza(Throwable th, RandomAccessFile randomAccessFile) {
        if (th == null) {
            randomAccessFile.close();
            return;
        }
        try {
            randomAccessFile.close();
        } catch (Throwable th2) {
            com.google.android.gms.internal.firebase_messaging.zzc.zza(th, th2);
        }
    }

    private static /* synthetic */ void zza(Throwable th, FileInputStream fileInputStream) {
        if (th == null) {
            fileInputStream.close();
            return;
        }
        try {
            fileInputStream.close();
        } catch (Throwable th2) {
            com.google.android.gms.internal.firebase_messaging.zzc.zza(th, th2);
        }
    }
}
