package munirkhanani.helpers;

import java.util.Random;
/* loaded from: classes2.dex */
public class MyHelper {
    public static StringBuilder resSb;

    public static String Enpts(String str) {
        resSb = new StringBuilder();
        Random random = new Random();
        String[] split = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        for (int i = 0; i < str.length(); i++) {
            String str2 = split[random.nextInt(51) + 1];
            char charAt = str.charAt(i);
            if (i != str.length() - 1) {
                StringBuilder sb = resSb;
                sb.append((int) charAt);
                sb.append(str2);
            } else {
                resSb.append((int) charAt);
            }
        }
        return String.valueOf(resSb);
    }

    public static String Enpt(String str) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        String[] split = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        for (int i = 0; i < str.length(); i++) {
            String str2 = split[random.nextInt(51) + 1];
            char charAt = str.charAt(i);
            if (i != str.length() - 1) {
                sb.append((int) charAt);
                sb.append(str2);
            } else {
                sb.append((int) charAt);
            }
        }
        return String.valueOf(sb);
    }
}
