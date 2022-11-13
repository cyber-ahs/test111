package munirkhanani.network;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.bumptech.glide.load.Key;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import munirkhanani.activities.LoginActivity;
import munirkhanani.dbmodel.DbContract;
import munirkhanani.helpers.Constants;
import munirkhanani.helpers.ServiceSocket;
import munirkhanani.model.UpperLowerPriceModel;
import munirkhanani.util.utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class HttpHandler {
    private static int ReqTimeOutStatic = 5000;
    private static final String TAG = "HttpHandler";
    public static HashMap<String, UpperLowerPriceModel> Upper_lowerCap = new HashMap<>();
    private static String userTokenSession;
    private String BaseUrl = Constants.getConstant(SplashLoader.USER_INT_IP, "API_URL");
    private Context context;
    private String response;
    private String usersTokenId;

    public HttpHandler() {
    }

    public HttpHandler(Context context) {
        this.context = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v14, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r7v0, types: [munirkhanani.network.HttpHandler] */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v19, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r9v6, types: [java.io.OutputStream] */
    public String getApiSecret(String str, String str2, String str3) {
        OutputStream outputStream;
        String str4;
        HttpURLConnection httpURLConnection;
        OutputStream outputStream2;
        HttpURLConnection httpURLConnection2;
        OutputStream outputStream3;
        HttpURLConnection httpURLConnection3;
        OutputStream outputStream4;
        HttpURLConnection httpURLConnection4;
        OutputStream outputStream5;
        HttpURLConnection httpURLConnection5;
        BufferedWriter bufferedWriter;
        ?? r3 = "API_URL";
        SplashLoader.apiUrl = Constants.getConstant(SplashLoader.USER_INT_IP, "API_URL");
        BufferedWriter bufferedWriter2 = null;
        r2 = null;
        r2 = null;
        r2 = null;
        String str5 = null;
        r2 = null;
        r2 = null;
        r2 = null;
        r2 = null;
        r2 = null;
        r2 = null;
        r2 = null;
        r2 = null;
        bufferedWriter2 = null;
        bufferedWriter2 = null;
        r2 = null;
        r2 = null;
        r2 = null;
        BufferedWriter bufferedWriter3 = null;
        try {
            try {
                URL url = new URL(SplashLoader.apiUrl + "_ri0");
                Log.d("API_Called", url.toString());
                r3 = (HttpURLConnection) url.openConnection();
                try {
                    r3.setRequestMethod("POST");
                    r3.setConnectTimeout(ReqTimeOutStatic);
                    r3.addRequestProperty(Constants.getConstant(SplashLoader.USER_INT_IP, "USASAS"), str);
                    r3.addRequestProperty(Constants.getConstant(SplashLoader.USER_INT_IP, "USDEE"), str2);
                    r3.addRequestProperty(Constants.getConstant(SplashLoader.USER_INT_IP, "USSEAEE"), str3);
                    r3.setDoInput(true);
                    r3.setDoOutput(true);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("_thv", (Integer) 0);
                    str2 = r3.getOutputStream();
                    try {
                        bufferedWriter = new BufferedWriter(new OutputStreamWriter((OutputStream) str2, Key.STRING_CHARSET_NAME));
                    } catch (MalformedURLException e) {
                        e = e;
                        str4 = null;
                        httpURLConnection4 = r3;
                        outputStream4 = str2;
                    } catch (ProtocolException e2) {
                        e = e2;
                        str4 = null;
                        httpURLConnection3 = r3;
                        outputStream3 = str2;
                    } catch (IOException e3) {
                        e = e3;
                        str4 = null;
                        httpURLConnection2 = r3;
                        outputStream2 = str2;
                    } catch (Exception e4) {
                        e = e4;
                        str4 = null;
                        httpURLConnection = r3;
                        outputStream = str2;
                    }
                    try {
                        bufferedWriter.write(getQuery(contentValues));
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        str2.close();
                        str5 = convertStreamToString(new BufferedInputStream(r3.getInputStream()));
                        r3.connect();
                        String headerField = r3.getHeaderField("Set-Cookie");
                        String headerField2 = r3.getHeaderField(Constants.getConstant(SplashLoader.USER_INT_IP, "USEEWAI"));
                        Log.d("", "" + headerField.split(";")[0] + "\nRegisteration  : " + headerField2);
                        SharedPreferences.Editor edit = this.context.getSharedPreferences("Register", 0).edit();
                        if (headerField2 != null) {
                            edit.putString(Constants.getConstant(SplashLoader.USER_INT_IP, "USEEWAI"), headerField2);
                            edit.apply();
                        }
                        Log.i("Result API SECRET", str5);
                        Dispose(bufferedWriter, str2, r3);
                        return str5;
                    } catch (MalformedURLException e5) {
                        e = e5;
                        str4 = str5;
                        bufferedWriter3 = bufferedWriter;
                        httpURLConnection4 = r3;
                        outputStream4 = str2;
                        Log.e(TAG, "MalformedURLException:1 " + e.getMessage());
                        httpURLConnection5 = httpURLConnection4;
                        outputStream5 = outputStream4;
                        Dispose(bufferedWriter3, outputStream5, httpURLConnection5);
                        return str4;
                    } catch (ProtocolException e6) {
                        e = e6;
                        str4 = str5;
                        bufferedWriter3 = bufferedWriter;
                        httpURLConnection3 = r3;
                        outputStream3 = str2;
                        Log.e(TAG, "ProtocolException:2 " + e.getMessage());
                        httpURLConnection5 = httpURLConnection3;
                        outputStream5 = outputStream3;
                        Dispose(bufferedWriter3, outputStream5, httpURLConnection5);
                        return str4;
                    } catch (IOException e7) {
                        e = e7;
                        str4 = str5;
                        bufferedWriter3 = bufferedWriter;
                        httpURLConnection2 = r3;
                        outputStream2 = str2;
                        Log.e(TAG, "IOException:3 " + e.getMessage());
                        httpURLConnection5 = httpURLConnection2;
                        outputStream5 = outputStream2;
                        Dispose(bufferedWriter3, outputStream5, httpURLConnection5);
                        return str4;
                    } catch (Exception e8) {
                        e = e8;
                        str4 = str5;
                        bufferedWriter3 = bufferedWriter;
                        httpURLConnection = r3;
                        outputStream = str2;
                        Log.e(TAG, "Exception:4 " + e.getMessage());
                        httpURLConnection5 = httpURLConnection;
                        outputStream5 = outputStream;
                        Dispose(bufferedWriter3, outputStream5, httpURLConnection5);
                        return str4;
                    } catch (Throwable th) {
                        th = th;
                        bufferedWriter2 = bufferedWriter;
                        Dispose(bufferedWriter2, str2, r3);
                        throw th;
                    }
                } catch (MalformedURLException e9) {
                    e = e9;
                    outputStream4 = null;
                    str4 = null;
                    httpURLConnection4 = r3;
                } catch (ProtocolException e10) {
                    e = e10;
                    outputStream3 = null;
                    str4 = null;
                    httpURLConnection3 = r3;
                } catch (IOException e11) {
                    e = e11;
                    outputStream2 = null;
                    str4 = null;
                    httpURLConnection2 = r3;
                } catch (Exception e12) {
                    e = e12;
                    outputStream = null;
                    str4 = null;
                    httpURLConnection = r3;
                } catch (Throwable th2) {
                    th = th2;
                    str2 = 0;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (MalformedURLException e13) {
            e = e13;
            outputStream4 = null;
            str4 = null;
            httpURLConnection4 = null;
        } catch (ProtocolException e14) {
            e = e14;
            outputStream3 = null;
            str4 = null;
            httpURLConnection3 = null;
        } catch (IOException e15) {
            e = e15;
            outputStream2 = null;
            str4 = null;
            httpURLConnection2 = null;
        } catch (Exception e16) {
            e = e16;
            outputStream = null;
            str4 = null;
            httpURLConnection = null;
        } catch (Throwable th4) {
            th = th4;
            str2 = 0;
            r3 = 0;
        }
    }

    public static boolean findBinary(String str) {
        String[] strArr = {"/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"};
        for (int i = 0; i < 8; i++) {
            String str2 = strArr[i];
            if (new File(str2 + str).exists()) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRooted() {
        return findBinary("su");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v24, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r10v7, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v16, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r9v0, types: [munirkhanani.network.HttpHandler] */
    public String loginMethod(String str, String str2, String str3, String str4, String str5) {
        OutputStream outputStream;
        HttpURLConnection httpURLConnection;
        BufferedWriter bufferedWriter;
        HttpURLConnection httpURLConnection2 = "isDeviceRooted";
        if (isRooted()) {
            Log.d("isDeviceRooted", "Rooted");
        } else {
            Log.d("isDeviceRooted", "Not Rooted");
        }
        BufferedWriter bufferedWriter2 = null;
        this.response = null;
        try {
            try {
                URL url = new URL(this.BaseUrl + "login");
                Log.d("API_Called", "" + url);
                Log.d("base_url", "BaseURL : " + this.BaseUrl);
                httpURLConnection2 = (HttpURLConnection) url.openConnection();
                try {
                    SharedPreferences sharedPreferences = this.context.getSharedPreferences("loginSecure", 0);
                    SharedPreferences sharedPreferences2 = this.context.getSharedPreferences("Register", 0);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    String string = sharedPreferences2.getString(Constants.getConstant(SplashLoader.USER_INT_IP, "USEEWAI"), "");
                    httpURLConnection2.setRequestMethod("POST");
                    httpURLConnection2.setConnectTimeout(ReqTimeOutStatic);
                    httpURLConnection2.addRequestProperty(Constants.getConstant(SplashLoader.USER_INT_IP, "USEEWAI"), string);
                    httpURLConnection2.addRequestProperty(Constants.getConstant(SplashLoader.USER_INT_IP, "USASAS"), str3);
                    httpURLConnection2.addRequestProperty(Constants.getConstant(SplashLoader.USER_INT_IP, "USDEE"), str4);
                    httpURLConnection2.setDoInput(true);
                    httpURLConnection2.setDoOutput(true);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("uId", (String) str);
                    contentValues.put("uPass", str2);
                    contentValues.put("userApp", "a");
                    contentValues.put("currentVersion", str5);
                    str = httpURLConnection2.getOutputStream();
                    try {
                        bufferedWriter = new BufferedWriter(new OutputStreamWriter((OutputStream) str, Key.STRING_CHARSET_NAME));
                    } catch (ConnectException e) {
                        e = e;
                    } catch (MalformedURLException e2) {
                        e = e2;
                    } catch (ProtocolException e3) {
                        e = e3;
                    } catch (IOException e4) {
                        e = e4;
                    } catch (Exception e5) {
                        e = e5;
                    }
                    try {
                        bufferedWriter.write(getQuery(contentValues));
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        str.close();
                        String convertStreamToString = convertStreamToString(new BufferedInputStream(httpURLConnection2.getInputStream()));
                        this.response = convertStreamToString;
                        Log.d("login_response", convertStreamToString);
                        httpURLConnection2.connect();
                        userTokenSession = httpURLConnection2.getHeaderField(Constants.getConstant(SplashLoader.USER_INT_IP, "SESS_TOKEN"));
                        String headerField = httpURLConnection2.getHeaderField(Constants.getConstant(SplashLoader.USER_INT_IP, "RAND_TOKEN"));
                        String str6 = userTokenSession;
                        if (str6 != null) {
                            edit.putString("userTokenId", str6);
                            edit.putString("userTeeKey", headerField);
                            edit.apply();
                        }
                        Log.d("logintaggg", this.response + " : 204");
                        Log.i("Result LOGIN", this.response + "getToken : " + userTokenSession + "\nRandom :" + headerField + "\n Registeration : " + string);
                        Dispose(bufferedWriter, str, httpURLConnection2);
                    } catch (ConnectException e6) {
                        e = e6;
                        bufferedWriter2 = bufferedWriter;
                        Log.e("ConnectException :7 ", String.valueOf(e));
                        httpURLConnection = httpURLConnection2;
                        outputStream = str;
                        Dispose(bufferedWriter2, outputStream, httpURLConnection);
                        return this.response;
                    } catch (MalformedURLException e7) {
                        e = e7;
                        bufferedWriter2 = bufferedWriter;
                        Log.e(TAG, "MalformedURLException:5 " + e.getMessage());
                        httpURLConnection = httpURLConnection2;
                        outputStream = str;
                        Dispose(bufferedWriter2, outputStream, httpURLConnection);
                        return this.response;
                    } catch (ProtocolException e8) {
                        e = e8;
                        bufferedWriter2 = bufferedWriter;
                        Log.e(TAG, "ProtocolException:6 " + e.getMessage());
                        httpURLConnection = httpURLConnection2;
                        outputStream = str;
                        Dispose(bufferedWriter2, outputStream, httpURLConnection);
                        return this.response;
                    } catch (IOException e9) {
                        e = e9;
                        bufferedWriter2 = bufferedWriter;
                        Log.e(TAG, "IOException:8 " + e.getMessage());
                        httpURLConnection = httpURLConnection2;
                        outputStream = str;
                        Dispose(bufferedWriter2, outputStream, httpURLConnection);
                        return this.response;
                    } catch (Exception e10) {
                        e = e10;
                        bufferedWriter2 = bufferedWriter;
                        e.printStackTrace();
                        Log.e("Result LOGIN Error", String.valueOf(e));
                        httpURLConnection = httpURLConnection2;
                        outputStream = str;
                        Dispose(bufferedWriter2, outputStream, httpURLConnection);
                        return this.response;
                    } catch (Throwable th) {
                        th = th;
                        bufferedWriter2 = bufferedWriter;
                        Dispose(bufferedWriter2, str, httpURLConnection2);
                        throw th;
                    }
                } catch (ConnectException e11) {
                    e = e11;
                    str = null;
                } catch (MalformedURLException e12) {
                    e = e12;
                    str = null;
                } catch (ProtocolException e13) {
                    e = e13;
                    str = null;
                } catch (IOException e14) {
                    e = e14;
                    str = null;
                } catch (Exception e15) {
                    e = e15;
                    str = null;
                } catch (Throwable th2) {
                    th = th2;
                    str = 0;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (ConnectException e16) {
            e = e16;
            str = null;
            httpURLConnection2 = null;
        } catch (MalformedURLException e17) {
            e = e17;
            str = null;
            httpURLConnection2 = null;
        } catch (ProtocolException e18) {
            e = e18;
            str = null;
            httpURLConnection2 = null;
        } catch (IOException e19) {
            e = e19;
            str = null;
            httpURLConnection2 = null;
        } catch (Exception e20) {
            e = e20;
            str = null;
            httpURLConnection2 = null;
        } catch (Throwable th4) {
            th = th4;
            str = 0;
            httpURLConnection2 = 0;
        }
        return this.response;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String GetDoubleValue2(String str) {
        try {
            return Constants.PRICE_FORMAT.format(utils.convertStringToDouble(str));
        } catch (Exception unused) {
            return "0.00";
        }
    }

    public void GetUpperCap_LowerCap() {
        try {
            URL url = new URL(this.BaseUrl + Constants.getCapLockUrl);
            Log.d("API_Called", "" + url);
            AndroidNetworking.post(url.toString()).addBodyParameter("v", "1").build().getAsString(new StringRequestListener() { // from class: munirkhanani.network.HttpHandler.1
                @Override // com.androidnetworking.interfaces.StringRequestListener
                public void onError(ANError aNError) {
                }

                @Override // com.androidnetworking.interfaces.StringRequestListener
                public void onResponse(String str) {
                    if (str != null) {
                        Log.e("", "jsonStr :" + str);
                        try {
                            JSONObject jSONObject = new JSONObject(str).getJSONObject("aData");
                            JSONArray jSONArray = jSONObject.getJSONArray("aHeader");
                            JSONArray jSONArray2 = jSONObject.getJSONArray("aData");
                            if (jSONArray2.length() > 0) {
                                for (int i = 0; i < jSONArray2.length(); i++) {
                                    UpperLowerPriceModel upperLowerPriceModel = new UpperLowerPriceModel();
                                    JSONArray jSONArray3 = jSONArray2.getJSONArray(i);
                                    String string = jSONArray3.getString(LoginActivity.getIndexFromJSONArray(jSONArray, DbContract.customeWatchList.UVAL));
                                    String string2 = jSONArray3.getString(LoginActivity.getIndexFromJSONArray(jSONArray, DbContract.customeWatchList.LVAL));
                                    String string3 = jSONArray3.getString(LoginActivity.getIndexFromJSONArray(jSONArray, DbContract.customeWatchList.MARKET_CODE));
                                    String string4 = jSONArray3.getString(LoginActivity.getIndexFromJSONArray(jSONArray, "SYMBOL_CODE"));
                                    upperLowerPriceModel.setUpperPri("" + HttpHandler.this.GetDoubleValue2(string));
                                    upperLowerPriceModel.setLowerPri("" + HttpHandler.this.GetDoubleValue2(string2));
                                    HashMap<String, UpperLowerPriceModel> hashMap = HttpHandler.Upper_lowerCap;
                                    hashMap.put(string3 + "_" + string4, upperLowerPriceModel);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (MalformedURLException e) {
            String str = TAG;
            Log.e(str, "MalformedURLException:9 " + e.getMessage());
        } catch (Exception e2) {
            String str2 = TAG;
            Log.e(str2, "Exception: 10" + e2.getMessage());
        }
    }

    public void GetUpperCap_LowerCap1() {
        try {
            URL url = new URL(this.BaseUrl + Constants.getCapLockUrl);
            Log.d("API_Called", "" + url);
            AndroidNetworking.post(url.toString()).build().getAsString(new StringRequestListener() { // from class: munirkhanani.network.HttpHandler.2
                @Override // com.androidnetworking.interfaces.StringRequestListener
                public void onError(ANError aNError) {
                }

                @Override // com.androidnetworking.interfaces.StringRequestListener
                public void onResponse(String str) {
                    if (str != null) {
                        Log.e("", "jsonStr :" + str);
                        try {
                            JSONArray jSONArray = new JSONObject(str).getJSONArray("aData");
                            if (jSONArray.length() > 0) {
                                for (int i = 0; i < jSONArray.length(); i++) {
                                    UpperLowerPriceModel upperLowerPriceModel = new UpperLowerPriceModel();
                                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                                    upperLowerPriceModel.setUpperPri("" + HttpHandler.this.GetDoubleValue2(jSONObject.getString(DbContract.customeWatchList.UVAL)));
                                    upperLowerPriceModel.setLowerPri("" + HttpHandler.this.GetDoubleValue2(jSONObject.getString(DbContract.customeWatchList.LVAL)));
                                    if ((jSONObject.getString(DbContract.customeWatchList.MARKET_CODE) + "_" + jSONObject.getString("SYMBOL_CODE")).equals("REG_OCTOPUS")) {
                                        Log.d("upper_lower", upperLowerPriceModel.getUpperPri() + " , " + upperLowerPriceModel.getLowerPri());
                                    }
                                    HashMap<String, UpperLowerPriceModel> hashMap = HttpHandler.Upper_lowerCap;
                                    hashMap.put(jSONObject.getString(DbContract.customeWatchList.MARKET_CODE) + "_" + jSONObject.getString("SYMBOL_CODE"), upperLowerPriceModel);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (MalformedURLException e) {
            String str = TAG;
            Log.e(str, "MalformedURLException:9 " + e.getMessage());
        } catch (Exception e2) {
            String str2 = TAG;
            Log.e(str2, "Exception: 10" + e2.getMessage());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:71:0x01d6, code lost:
        if (r5 == 1) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x01d8, code lost:
        if (r5 == 2) goto L48;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v13, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r3v56 */
    /* JADX WARN: Type inference failed for: r3v57 */
    /* JADX WARN: Type inference failed for: r3v58 */
    /* JADX WARN: Type inference failed for: r3v59 */
    /* JADX WARN: Type inference failed for: r3v60 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String GetRequestHandler(String str, HashMap<String, String> hashMap) {
        Throwable th;
        OutputStream outputStream;
        HttpURLConnection httpURLConnection;
        Exception exc;
        OutputStream outputStream2;
        IOException iOException;
        OutputStream outputStream3;
        ProtocolException protocolException;
        OutputStream outputStream4;
        MalformedURLException malformedURLException;
        OutputStream outputStream5;
        ConnectException connectException;
        OutputStream outputStream6;
        SharedPreferences.Editor edit;
        URL url;
        OutputStream outputStream7;
        OutputStream outputStream8;
        OutputStream outputStream9;
        OutputStream outputStream10;
        OutputStream outputStream11;
        OutputStream outputStream12;
        ContentValues contentValues;
        OutputStream outputStream13;
        ?? r3 = "logout";
        BufferedWriter bufferedWriter = null;
        this.response = null;
        try {
            try {
                Log.d("API_Called", str);
                SharedPreferences sharedPreferences = this.context.getSharedPreferences("loginSecure", 0);
                edit = sharedPreferences.edit();
                this.usersTokenId = sharedPreferences.getString("userTokenId", "");
                String string = sharedPreferences.getString("userTeeKey", "");
                if (str.equals("")) {
                    try {
                        url = new URL("");
                    } catch (ConnectException e) {
                        connectException = e;
                        outputStream6 = null;
                        httpURLConnection = null;
                        Log.e("ConnectException : ", String.valueOf(connectException) + " : " + str);
                        r3 = outputStream6;
                        Dispose(bufferedWriter, r3, httpURLConnection);
                        return this.response;
                    } catch (MalformedURLException e2) {
                        malformedURLException = e2;
                        outputStream5 = null;
                        httpURLConnection = null;
                        Log.e(TAG, "MalformedURLException:11 " + malformedURLException.getMessage() + " : " + str);
                        r3 = outputStream5;
                        Dispose(bufferedWriter, r3, httpURLConnection);
                        return this.response;
                    } catch (ProtocolException e3) {
                        protocolException = e3;
                        outputStream4 = null;
                        httpURLConnection = null;
                        Log.e(TAG, "ProtocolException:12 " + protocolException.getMessage() + " : " + str);
                        r3 = outputStream4;
                        Dispose(bufferedWriter, r3, httpURLConnection);
                        return this.response;
                    } catch (IOException e4) {
                        iOException = e4;
                        outputStream3 = null;
                        httpURLConnection = null;
                        Log.e(TAG, "IOException:13 " + iOException.getMessage() + " : " + str);
                        r3 = outputStream3;
                        Dispose(bufferedWriter, r3, httpURLConnection);
                        return this.response;
                    } catch (Exception e5) {
                        exc = e5;
                        outputStream2 = null;
                        httpURLConnection = null;
                        Log.e(TAG, "Exception:14 " + exc.getMessage() + " : " + str);
                        r3 = outputStream2;
                        Dispose(bufferedWriter, r3, httpURLConnection);
                        return this.response;
                    } catch (Throwable th2) {
                        th = th2;
                        outputStream = null;
                        httpURLConnection = null;
                        Dispose(bufferedWriter, outputStream, httpURLConnection);
                        throw th;
                    }
                } else {
                    url = new URL(this.BaseUrl + str);
                }
                httpURLConnection = (HttpURLConnection) url.openConnection();
                try {
                    httpURLConnection.setRequestMethod("POST");
                    if (str.equals("logout")) {
                        try {
                            httpURLConnection.setConnectTimeout(PathInterpolatorCompat.MAX_NUM_POINTS);
                        } catch (ConnectException e6) {
                            connectException = e6;
                            outputStream6 = null;
                            Log.e("ConnectException : ", String.valueOf(connectException) + " : " + str);
                            r3 = outputStream6;
                            Dispose(bufferedWriter, r3, httpURLConnection);
                            return this.response;
                        } catch (MalformedURLException e7) {
                            malformedURLException = e7;
                            outputStream5 = null;
                            Log.e(TAG, "MalformedURLException:11 " + malformedURLException.getMessage() + " : " + str);
                            r3 = outputStream5;
                            Dispose(bufferedWriter, r3, httpURLConnection);
                            return this.response;
                        } catch (ProtocolException e8) {
                            protocolException = e8;
                            outputStream4 = null;
                            Log.e(TAG, "ProtocolException:12 " + protocolException.getMessage() + " : " + str);
                            r3 = outputStream4;
                            Dispose(bufferedWriter, r3, httpURLConnection);
                            return this.response;
                        } catch (IOException e9) {
                            iOException = e9;
                            outputStream3 = null;
                            Log.e(TAG, "IOException:13 " + iOException.getMessage() + " : " + str);
                            r3 = outputStream3;
                            Dispose(bufferedWriter, r3, httpURLConnection);
                            return this.response;
                        } catch (Exception e10) {
                            exc = e10;
                            outputStream2 = null;
                            Log.e(TAG, "Exception:14 " + exc.getMessage() + " : " + str);
                            r3 = outputStream2;
                            Dispose(bufferedWriter, r3, httpURLConnection);
                            return this.response;
                        } catch (Throwable th3) {
                            th = th3;
                            outputStream = null;
                            Dispose(bufferedWriter, outputStream, httpURLConnection);
                            throw th;
                        }
                    } else {
                        httpURLConnection.setConnectTimeout(ReqTimeOutStatic);
                    }
                    httpURLConnection.addRequestProperty(Constants.getConstant(SplashLoader.USER_INT_IP, "SESS_TOKEN"), this.usersTokenId);
                    Log.e(TAG, "userTeeKey get: " + string + "    urlPath  :" + str);
                    if (string != null) {
                        httpURLConnection.addRequestProperty(Constants.getConstant(SplashLoader.USER_INT_IP, "RAND_TOKEN"), string);
                    }
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    contentValues = new ContentValues();
                    for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                        contentValues.put(entry.getKey(), entry.getValue());
                    }
                    outputStream13 = httpURLConnection.getOutputStream();
                } catch (ConnectException e11) {
                    connectException = e11;
                    outputStream12 = null;
                } catch (MalformedURLException e12) {
                    malformedURLException = e12;
                    outputStream11 = null;
                } catch (ProtocolException e13) {
                    protocolException = e13;
                    outputStream10 = null;
                } catch (IOException e14) {
                    iOException = e14;
                    outputStream9 = null;
                } catch (Exception e15) {
                    exc = e15;
                    outputStream8 = null;
                } catch (Throwable th4) {
                    th = th4;
                    outputStream7 = null;
                }
            } catch (Throwable th5) {
                th = th5;
                outputStream = r3;
            }
        } catch (ConnectException e16) {
            connectException = e16;
            outputStream6 = null;
            bufferedWriter = null;
            httpURLConnection = null;
        } catch (MalformedURLException e17) {
            malformedURLException = e17;
            outputStream5 = null;
            bufferedWriter = null;
            httpURLConnection = null;
        } catch (ProtocolException e18) {
            protocolException = e18;
            outputStream4 = null;
            bufferedWriter = null;
            httpURLConnection = null;
        } catch (IOException e19) {
            iOException = e19;
            outputStream3 = null;
            bufferedWriter = null;
            httpURLConnection = null;
        } catch (Exception e20) {
            exc = e20;
            outputStream2 = null;
            bufferedWriter = null;
            httpURLConnection = null;
        } catch (Throwable th6) {
            th = th6;
            outputStream = null;
            bufferedWriter = null;
            httpURLConnection = null;
        }
        try {
            BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(outputStream13, Key.STRING_CHARSET_NAME));
            try {
                bufferedWriter2.write(getQuery(contentValues));
                bufferedWriter2.flush();
                bufferedWriter2.close();
                outputStream13.close();
                this.response = convertStreamToString(new BufferedInputStream(httpURLConnection.getInputStream()));
                httpURLConnection.connect();
                String headerField = httpURLConnection.getHeaderField(Constants.getConstant(SplashLoader.USER_INT_IP, "RAND_TOKEN"));
                if (headerField != null) {
                    edit.putString("userTeeKey", headerField);
                    edit.apply();
                    Log.e(TAG, "userTeeKey save: " + headerField + "    urlPath  :" + str);
                }
                String headerField2 = httpURLConnection.getHeaderField(Constants.getConstant(SplashLoader.USER_INT_IP, "S_STATE"));
                Log.e(TAG, "s_state: " + headerField2 + "    urlPath  :" + str);
                char c = 65535;
                int hashCode = headerField2.hashCode();
                if (hashCode != 50) {
                    if (hashCode != 51) {
                        if (hashCode == 53 && headerField2.equals("5")) {
                            c = 2;
                        }
                    } else if (headerField2.equals("3")) {
                        c = 0;
                    }
                } else if (headerField2.equals("2")) {
                    c = 1;
                }
                if (!str.equals("logout")) {
                    Log.e("", "Relogin case handlerURLs1");
                    Constants._continueRunning = false;
                    new ServiceSocket().reConnect();
                }
                Dispose(bufferedWriter2, outputStream13, httpURLConnection);
            } catch (ConnectException e21) {
                connectException = e21;
                outputStream6 = outputStream13;
                bufferedWriter = bufferedWriter2;
                Log.e("ConnectException : ", String.valueOf(connectException) + " : " + str);
                r3 = outputStream6;
                Dispose(bufferedWriter, r3, httpURLConnection);
                return this.response;
            } catch (MalformedURLException e22) {
                malformedURLException = e22;
                outputStream5 = outputStream13;
                bufferedWriter = bufferedWriter2;
                Log.e(TAG, "MalformedURLException:11 " + malformedURLException.getMessage() + " : " + str);
                r3 = outputStream5;
                Dispose(bufferedWriter, r3, httpURLConnection);
                return this.response;
            } catch (ProtocolException e23) {
                protocolException = e23;
                outputStream4 = outputStream13;
                bufferedWriter = bufferedWriter2;
                Log.e(TAG, "ProtocolException:12 " + protocolException.getMessage() + " : " + str);
                r3 = outputStream4;
                Dispose(bufferedWriter, r3, httpURLConnection);
                return this.response;
            } catch (IOException e24) {
                iOException = e24;
                outputStream3 = outputStream13;
                bufferedWriter = bufferedWriter2;
                Log.e(TAG, "IOException:13 " + iOException.getMessage() + " : " + str);
                r3 = outputStream3;
                Dispose(bufferedWriter, r3, httpURLConnection);
                return this.response;
            } catch (Exception e25) {
                exc = e25;
                outputStream2 = outputStream13;
                bufferedWriter = bufferedWriter2;
                Log.e(TAG, "Exception:14 " + exc.getMessage() + " : " + str);
                r3 = outputStream2;
                Dispose(bufferedWriter, r3, httpURLConnection);
                return this.response;
            } catch (Throwable th7) {
                th = th7;
                outputStream = outputStream13;
                bufferedWriter = bufferedWriter2;
                Dispose(bufferedWriter, outputStream, httpURLConnection);
                throw th;
            }
        } catch (ConnectException e26) {
            connectException = e26;
            outputStream12 = outputStream13;
            bufferedWriter = null;
            outputStream6 = outputStream12;
            Log.e("ConnectException : ", String.valueOf(connectException) + " : " + str);
            r3 = outputStream6;
            Dispose(bufferedWriter, r3, httpURLConnection);
            return this.response;
        } catch (MalformedURLException e27) {
            malformedURLException = e27;
            outputStream11 = outputStream13;
            bufferedWriter = null;
            outputStream5 = outputStream11;
            Log.e(TAG, "MalformedURLException:11 " + malformedURLException.getMessage() + " : " + str);
            r3 = outputStream5;
            Dispose(bufferedWriter, r3, httpURLConnection);
            return this.response;
        } catch (ProtocolException e28) {
            protocolException = e28;
            outputStream10 = outputStream13;
            bufferedWriter = null;
            outputStream4 = outputStream10;
            Log.e(TAG, "ProtocolException:12 " + protocolException.getMessage() + " : " + str);
            r3 = outputStream4;
            Dispose(bufferedWriter, r3, httpURLConnection);
            return this.response;
        } catch (IOException e29) {
            iOException = e29;
            outputStream9 = outputStream13;
            bufferedWriter = null;
            outputStream3 = outputStream9;
            Log.e(TAG, "IOException:13 " + iOException.getMessage() + " : " + str);
            r3 = outputStream3;
            Dispose(bufferedWriter, r3, httpURLConnection);
            return this.response;
        } catch (Exception e30) {
            exc = e30;
            outputStream8 = outputStream13;
            bufferedWriter = null;
            outputStream2 = outputStream8;
            Log.e(TAG, "Exception:14 " + exc.getMessage() + " : " + str);
            r3 = outputStream2;
            Dispose(bufferedWriter, r3, httpURLConnection);
            return this.response;
        } catch (Throwable th8) {
            th = th8;
            outputStream7 = outputStream13;
            bufferedWriter = null;
            outputStream = outputStream7;
            Dispose(bufferedWriter, outputStream, httpURLConnection);
            throw th;
        }
        return this.response;
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x018f, code lost:
        if (r5 == 1) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0191, code lost:
        if (r5 == 2) goto L44;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v13, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r2v51 */
    /* JADX WARN: Type inference failed for: r2v52 */
    /* JADX WARN: Type inference failed for: r2v53 */
    /* JADX WARN: Type inference failed for: r2v54 */
    /* JADX WARN: Type inference failed for: r2v55 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String GetRequestHandlerWithBaseUrl(String str, HashMap<String, String> hashMap) {
        Throwable th;
        OutputStream outputStream;
        HttpURLConnection httpURLConnection;
        Exception exc;
        OutputStream outputStream2;
        IOException iOException;
        OutputStream outputStream3;
        ProtocolException protocolException;
        OutputStream outputStream4;
        MalformedURLException malformedURLException;
        OutputStream outputStream5;
        ConnectException connectException;
        OutputStream outputStream6;
        SharedPreferences.Editor edit;
        OutputStream outputStream7;
        OutputStream outputStream8;
        OutputStream outputStream9;
        OutputStream outputStream10;
        OutputStream outputStream11;
        OutputStream outputStream12;
        ContentValues contentValues;
        OutputStream outputStream13;
        ?? r2 = str;
        BufferedWriter bufferedWriter = null;
        this.response = null;
        try {
            try {
                Log.d("API_Called", r2);
                SharedPreferences sharedPreferences = this.context.getSharedPreferences("loginSecure", 0);
                edit = sharedPreferences.edit();
                this.usersTokenId = sharedPreferences.getString("userTokenId", "");
                String string = sharedPreferences.getString("userTeeKey", "");
                httpURLConnection = (HttpURLConnection) new URL(r2).openConnection();
                try {
                    httpURLConnection.setRequestMethod("POST");
                    if (r2.equals("logout")) {
                        try {
                            httpURLConnection.setConnectTimeout(PathInterpolatorCompat.MAX_NUM_POINTS);
                        } catch (ConnectException e) {
                            connectException = e;
                            outputStream6 = null;
                            Log.e("ConnectException : ", String.valueOf(connectException));
                            r2 = outputStream6;
                            Dispose(bufferedWriter, r2, httpURLConnection);
                            return this.response;
                        } catch (MalformedURLException e2) {
                            malformedURLException = e2;
                            outputStream5 = null;
                            Log.e(TAG, "MalformedURLException:15 " + malformedURLException.getMessage());
                            r2 = outputStream5;
                            Dispose(bufferedWriter, r2, httpURLConnection);
                            return this.response;
                        } catch (ProtocolException e3) {
                            protocolException = e3;
                            outputStream4 = null;
                            Log.e(TAG, "ProtocolException: 16" + protocolException.getMessage());
                            r2 = outputStream4;
                            Dispose(bufferedWriter, r2, httpURLConnection);
                            return this.response;
                        } catch (IOException e4) {
                            iOException = e4;
                            outputStream3 = null;
                            Log.e(TAG, "IOException: 17" + iOException.getMessage());
                            r2 = outputStream3;
                            Dispose(bufferedWriter, r2, httpURLConnection);
                            return this.response;
                        } catch (Exception e5) {
                            exc = e5;
                            outputStream2 = null;
                            Log.e(TAG, "Exception:18 " + exc.getMessage());
                            r2 = outputStream2;
                            Dispose(bufferedWriter, r2, httpURLConnection);
                            return this.response;
                        } catch (Throwable th2) {
                            th = th2;
                            outputStream = null;
                            Dispose(bufferedWriter, outputStream, httpURLConnection);
                            throw th;
                        }
                    } else {
                        httpURLConnection.setConnectTimeout(ReqTimeOutStatic);
                    }
                    httpURLConnection.addRequestProperty(Constants.getConstant(SplashLoader.USER_INT_IP, "SESS_TOKEN"), this.usersTokenId);
                    Log.e(TAG, "userTeeKey get: " + string + "    urlPath  :" + ((String) r2));
                    if (string != null) {
                        httpURLConnection.addRequestProperty(Constants.getConstant(SplashLoader.USER_INT_IP, "RAND_TOKEN"), string);
                    }
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    contentValues = new ContentValues();
                    for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                        contentValues.put(entry.getKey(), entry.getValue());
                    }
                    outputStream13 = httpURLConnection.getOutputStream();
                } catch (ConnectException e6) {
                    connectException = e6;
                    outputStream12 = null;
                } catch (MalformedURLException e7) {
                    malformedURLException = e7;
                    outputStream11 = null;
                } catch (ProtocolException e8) {
                    protocolException = e8;
                    outputStream10 = null;
                } catch (IOException e9) {
                    iOException = e9;
                    outputStream9 = null;
                } catch (Exception e10) {
                    exc = e10;
                    outputStream8 = null;
                } catch (Throwable th3) {
                    th = th3;
                    outputStream7 = null;
                }
            } catch (Throwable th4) {
                th = th4;
                outputStream = r2;
            }
        } catch (ConnectException e11) {
            connectException = e11;
            outputStream6 = null;
            bufferedWriter = null;
            httpURLConnection = null;
        } catch (MalformedURLException e12) {
            malformedURLException = e12;
            outputStream5 = null;
            bufferedWriter = null;
            httpURLConnection = null;
        } catch (ProtocolException e13) {
            protocolException = e13;
            outputStream4 = null;
            bufferedWriter = null;
            httpURLConnection = null;
        } catch (IOException e14) {
            iOException = e14;
            outputStream3 = null;
            bufferedWriter = null;
            httpURLConnection = null;
        } catch (Exception e15) {
            exc = e15;
            outputStream2 = null;
            bufferedWriter = null;
            httpURLConnection = null;
        } catch (Throwable th5) {
            th = th5;
            outputStream = null;
            bufferedWriter = null;
            httpURLConnection = null;
        }
        try {
            BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(outputStream13, Key.STRING_CHARSET_NAME));
            try {
                bufferedWriter2.write(getQuery(contentValues));
                bufferedWriter2.flush();
                bufferedWriter2.close();
                outputStream13.close();
                this.response = convertStreamToString(new BufferedInputStream(httpURLConnection.getInputStream()));
                httpURLConnection.connect();
                String headerField = httpURLConnection.getHeaderField(Constants.getConstant(SplashLoader.USER_INT_IP, "RAND_TOKEN"));
                if (headerField != null) {
                    edit.putString("userTeeKey", headerField);
                    edit.apply();
                    Log.e(TAG, "userTeeKey save: " + headerField + "    urlPath  :" + ((String) r2));
                }
                String headerField2 = httpURLConnection.getHeaderField(Constants.getConstant(SplashLoader.USER_INT_IP, "S_STATE"));
                Log.e(TAG, "s_state: " + headerField2 + "    urlPath  :" + ((String) r2));
                char c = 65535;
                int hashCode = headerField2.hashCode();
                if (hashCode != 50) {
                    if (hashCode != 51) {
                        if (hashCode == 53 && headerField2.equals("5")) {
                            c = 2;
                        }
                    } else if (headerField2.equals("3")) {
                        c = 0;
                    }
                } else if (headerField2.equals("2")) {
                    c = 1;
                }
                if (!r2.equals("logout")) {
                    Log.e("", "Relogin case handlerURLs2");
                    Constants._continueRunning = false;
                    new ServiceSocket().reConnect();
                }
                Dispose(bufferedWriter2, outputStream13, httpURLConnection);
            } catch (ConnectException e16) {
                connectException = e16;
                outputStream6 = outputStream13;
                bufferedWriter = bufferedWriter2;
                Log.e("ConnectException : ", String.valueOf(connectException));
                r2 = outputStream6;
                Dispose(bufferedWriter, r2, httpURLConnection);
                return this.response;
            } catch (MalformedURLException e17) {
                malformedURLException = e17;
                outputStream5 = outputStream13;
                bufferedWriter = bufferedWriter2;
                Log.e(TAG, "MalformedURLException:15 " + malformedURLException.getMessage());
                r2 = outputStream5;
                Dispose(bufferedWriter, r2, httpURLConnection);
                return this.response;
            } catch (ProtocolException e18) {
                protocolException = e18;
                outputStream4 = outputStream13;
                bufferedWriter = bufferedWriter2;
                Log.e(TAG, "ProtocolException: 16" + protocolException.getMessage());
                r2 = outputStream4;
                Dispose(bufferedWriter, r2, httpURLConnection);
                return this.response;
            } catch (IOException e19) {
                iOException = e19;
                outputStream3 = outputStream13;
                bufferedWriter = bufferedWriter2;
                Log.e(TAG, "IOException: 17" + iOException.getMessage());
                r2 = outputStream3;
                Dispose(bufferedWriter, r2, httpURLConnection);
                return this.response;
            } catch (Exception e20) {
                exc = e20;
                outputStream2 = outputStream13;
                bufferedWriter = bufferedWriter2;
                Log.e(TAG, "Exception:18 " + exc.getMessage());
                r2 = outputStream2;
                Dispose(bufferedWriter, r2, httpURLConnection);
                return this.response;
            } catch (Throwable th6) {
                th = th6;
                outputStream = outputStream13;
                bufferedWriter = bufferedWriter2;
                Dispose(bufferedWriter, outputStream, httpURLConnection);
                throw th;
            }
        } catch (ConnectException e21) {
            connectException = e21;
            outputStream12 = outputStream13;
            bufferedWriter = null;
            outputStream6 = outputStream12;
            Log.e("ConnectException : ", String.valueOf(connectException));
            r2 = outputStream6;
            Dispose(bufferedWriter, r2, httpURLConnection);
            return this.response;
        } catch (MalformedURLException e22) {
            malformedURLException = e22;
            outputStream11 = outputStream13;
            bufferedWriter = null;
            outputStream5 = outputStream11;
            Log.e(TAG, "MalformedURLException:15 " + malformedURLException.getMessage());
            r2 = outputStream5;
            Dispose(bufferedWriter, r2, httpURLConnection);
            return this.response;
        } catch (ProtocolException e23) {
            protocolException = e23;
            outputStream10 = outputStream13;
            bufferedWriter = null;
            outputStream4 = outputStream10;
            Log.e(TAG, "ProtocolException: 16" + protocolException.getMessage());
            r2 = outputStream4;
            Dispose(bufferedWriter, r2, httpURLConnection);
            return this.response;
        } catch (IOException e24) {
            iOException = e24;
            outputStream9 = outputStream13;
            bufferedWriter = null;
            outputStream3 = outputStream9;
            Log.e(TAG, "IOException: 17" + iOException.getMessage());
            r2 = outputStream3;
            Dispose(bufferedWriter, r2, httpURLConnection);
            return this.response;
        } catch (Exception e25) {
            exc = e25;
            outputStream8 = outputStream13;
            bufferedWriter = null;
            outputStream2 = outputStream8;
            Log.e(TAG, "Exception:18 " + exc.getMessage());
            r2 = outputStream2;
            Dispose(bufferedWriter, r2, httpURLConnection);
            return this.response;
        } catch (Throwable th7) {
            th = th7;
            outputStream7 = outputStream13;
            bufferedWriter = null;
            outputStream = outputStream7;
            Dispose(bufferedWriter, outputStream, httpURLConnection);
            throw th;
        }
        return this.response;
    }

    private String getTimeStamp() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        sb.append(String.format("%02X:", Byte.valueOf(hardwareAddress[i])));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    private void Dispose(BufferedWriter bufferedWriter, OutputStream outputStream, HttpURLConnection httpURLConnection) {
        if (bufferedWriter != null) {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        if (outputStream != null) {
            outputStream.close();
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    private String convertStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                try {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                        sb.append('\n');
                    } catch (IOException e) {
                        e.printStackTrace();
                        inputStream.close();
                    }
                } catch (Throwable th) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    throw th;
                }
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        inputStream.close();
        return sb.toString();
    }

    private String getQuery(ContentValues contentValues) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
            if (z) {
                z = false;
            } else {
                sb.append("&");
            }
            sb.append(URLEncoder.encode(entry.getKey(), Key.STRING_CHARSET_NAME));
            sb.append("=");
            sb.append(URLEncoder.encode(String.valueOf(entry.getValue()), Key.STRING_CHARSET_NAME));
        }
        return sb.toString();
    }
}
