package munirkhanani.util;

import android.content.Context;
import android.util.Log;
import munirkhanani.helpers.Constants;
/* loaded from: classes2.dex */
public class utils {
    public static int VALUE_SIZE = 50;
    public static int VALUE_SIZE_ACTIVE = 33;

    public static int convertDpToPixel(int i, Context context) {
        return Math.round(i * (context.getResources().getDisplayMetrics().densityDpi / 160.0f));
    }

    public static double convertStringToDouble(String str) {
        if (str != null) {
            try {
                if (!str.equals("") && !str.equals("null")) {
                    if (str.contains(",")) {
                        str = str.replace(",", "");
                    }
                    return Double.parseDouble(str);
                }
            } catch (Exception e) {
                Log.d("convert_issue", e.getMessage());
            }
        }
        return 0.0d;
    }

    public static String convertStringTOMillions(String str) {
        return convertintToMillion(Double.valueOf(convertStringToDouble(str)));
    }

    public static String convertintToMillion(Double d) {
        try {
            Double valueOf = Double.valueOf(convertStringToDouble(Constants.PRICE_FORMAT.format(d)));
            if (valueOf.doubleValue() > 999.0d && valueOf.doubleValue() < 1000000.0d) {
                double convertStringToDouble = convertStringToDouble(Constants.PRICE_FORMAT.format(valueOf.doubleValue() / 1000.0d));
                return convertStringToDouble + "K";
            } else if (valueOf.doubleValue() >= 1000000.0d) {
                double convertStringToDouble2 = convertStringToDouble(Constants.PRICE_FORMAT.format(valueOf.doubleValue() / 1000000.0d));
                return convertStringToDouble2 + "M";
            } else {
                return valueOf + "";
            }
        } catch (Exception e) {
            Log.d("convert_issue", e.getMessage());
            return "0";
        }
    }
}
