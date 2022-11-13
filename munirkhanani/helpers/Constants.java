package munirkhanani.helpers;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.load.Key;
import com.microlinks.Munirkhanani.R;
import java.io.PrintStream;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.text.Typography;
import kotlinx.coroutines.debug.internal.DebugCoroutineInfoImplKt;
import munirkhanani.activities.LoginActivity;
import munirkhanani.activities.MenuActivity;
import munirkhanani.util.utils;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class Constants {
    public static boolean _continueRunning = false;
    public static Boolean chinaFlag = null;
    public static final String getCapLockUrl = "getCapLock";
    public static final String getClientExposureUrl = "getclientexposure";
    public static final String getdtUrl = "getdt";
    public static final String gettopMoversUrl = "getTopMovers";
    public static boolean isDetailQuoteActive = false;
    public static boolean isReLogin = false;
    public static boolean isRowShow = false;
    public static boolean isTaskCompleted = false;
    public static boolean pormptIdCheck = false;
    private static String port1 = null;
    private static String port2 = null;
    private static String port3 = null;
    private static String port4 = null;
    public static int resSize = 0;
    public static int resSizeTablet = 0;
    public static boolean successLogout = false;
    public static boolean successLogoutForOnDestroy = false;
    public static String versionNumber;
    Context context;
    public static DecimalFormat PRICE_FORMAT = new DecimalFormat("###,###,###,###,##0.00");
    public static DecimalFormat VOLUME_FORMAT = new DecimalFormat("###,###,###,###");
    public static DecimalFormat formatter = new DecimalFormat("###,###,###,###.##");
    public static DecimalFormat fourDecimal_FORMAT = new DecimalFormat("###,###,###,###,##0.0000");
    public static String ColorKATSBuyFilledOrderBG = "#00c7c6";
    public static String ColorKATSBuyFilledOrderFG = "#000096";
    public static String ColorKATSSellFilledOrderBG = "#ceb2b5";
    public static String ColorKATSSellFilledOrderFG = "#cf14aa";
    public static String ColorKATSBuyQueueFGFilledBG = "#00c7c6";
    public static String ColorKATSBuyCancelFG = "#7b7218";
    public static String ColorKATSSellQueueFGFilledBG = "#ceb2b5";
    public static String ColorKATSSellCancelFG = "#c35d61";
    public static String ColorKATSBuyChangeOrderFG = "#6d75a6";
    public static String ColorKATSSellChangeOrderFG = "#d34e25";
    public static String ColorKATSSellOrderFG = "#d732be";
    public static String ColorWhite = "#FFFFFF";
    public static String ColorBlack = "#000000";
    public static int marketWatchSelectedPosition = 0;
    public static int menuItemSelectedPosition = 0;
    public static int accountPositionSelectedPosition = 0;
    public static Boolean isFullRowBlink = false;
    public static int screenOrientation = -1;
    public static Boolean isPerformedClick = false;
    public static Boolean isApplicationInBackground = false;
    public static HashMap<String, Object> ExchangeStateDictionary = new HashMap<>();
    public static String minimum_app_version = "";
    public static Boolean isDaysFirstLogin = false;
    public static int notificationIcon = R.mipmap.mmkiconbg;
    public static String base_url = "http://tla.munirkhanani.com";
    public static boolean isZlk = false;
    public static final String temp_base_url = "http://tla.munirkhanani.com";
    public static Boolean isFirstTimeComeInLogs = false;
    public static Boolean isFromCancelOrder = false;
    public static HashMap<String, String> mConstants = new HashMap<>();
    public static String ApiDistibutor = "distributor/get_host";

    public static String getServerTime() {
        return "[9,{\"key\":\"getServerTime\"}]";
    }

    public static String setSendFormatexSt() {
        return "[5,\"{\\\"exSt\\\":[\\\"exSt\\\"]}\"]";
    }

    public Constants(Context context) {
        this.context = context;
    }

    public Constants() {
    }

    private static HashMap<String, String> getaConstants(String str) {
        if (isZlk) {
            mConstants.put("HOUSE_INT_IP", base_url);
            mConstants.put("SOCKET_SERVER_IP", LoginActivity.socketUrlGol);
            mConstants.put("API_DISTRIBUTOR", ApiDistibutor);
            notificationIcon = R.mipmap.zlk_launch;
        } else {
            mConstants.put("HOUSE_INT_IP", base_url);
            mConstants.put("SOCKET_SERVER_IP", LoginActivity.socketUrlGol);
            mConstants.put("API_DISTRIBUTOR", ApiDistibutor);
            notificationIcon = R.mipmap.mmkiconbg;
        }
        mConstants.put("API_SUFFIX", "");
        mConstants.put("SESS_TOKEN", "trdlnks");
        mConstants.put("RAND_TOKEN", "rtk");
        mConstants.put("S_STATE", "rstate");
        mConstants.put("USASAS", "ss");
        mConstants.put("USDEE", "d");
        mConstants.put("USSEAEE", "ce");
        mConstants.put("USEEWAI", "ey");
        mConstants.put("SECPROTO", "Sec-WebSocket-Protocol");
        if (str != null && str.equals(mConstants.get("HOUSE_INT_IP"))) {
            if (isZlk) {
                base_url = "http://tradelinks.zlk.com.pk";
                mConstants.put("SOCKET_SERVER_IP", LoginActivity.socketUrlGol);
                notificationIcon = R.mipmap.zlk_launch;
            } else {
                base_url = "http://tla.munirkhanani.com";
                mConstants.put("SOCKET_SERVER_IP", LoginActivity.socketUrlGol);
                notificationIcon = R.mipmap.mmkiconbg;
            }
        }
        if (mConstants.get("ROOT_PORT") == null) {
            if (isZlk) {
                mConstants.put("ROOT_PORT", ":14411");
            } else {
                mConstants.put("ROOT_PORT", ":31301");
            }
        }
        HashMap<String, String> hashMap = mConstants;
        hashMap.put("BASE_URL", base_url + mConstants.get("ROOT_PORT") + "/");
        if (str != null && str.equals("API_DISTRIBUTOR")) {
            HashMap<String, String> hashMap2 = mConstants;
            hashMap2.put("API_URL", mConstants.get("BASE_URL") + mConstants.get("API_DISTRIBUTOR"));
        } else {
            HashMap<String, String> hashMap3 = mConstants;
            hashMap3.put("API_URL", mConstants.get("BASE_URL") + "api_new/");
        }
        return mConstants;
    }

    public static String getConstant(String str, String str2) {
        return getaConstants(str).get(str2);
    }

    public static String GetSymbolName(String str) {
        try {
            return LoginActivity.symbolList.get(str).getSymbolName();
        } catch (Exception unused) {
            return null;
        }
    }

    public static JSONArray extractDataFromJsonObject(JSONObject jSONObject, String str) {
        JSONArray jSONArray = null;
        try {
            Iterator<String> keys = jSONObject.keys();
            JSONArray jSONArray2 = new JSONArray();
            while (keys.hasNext()) {
                try {
                    String next = keys.next();
                    if (next.equals(str)) {
                        jSONArray2.put(jSONObject.get(next));
                    }
                    if (!MenuActivity.categories.contains(next)) {
                        MenuActivity.categories.add(next);
                    }
                    ExchangeStateDictionary.put(next, jSONObject.get(next));
                } catch (Exception unused) {
                    jSONArray = jSONArray2;
                    return jSONArray;
                }
            }
            return jSONArray2;
        } catch (Exception unused2) {
        }
    }

    public static String secDiffer(long j) {
        Date date;
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        String format = simpleDateFormat.format(date2);
        PrintStream printStream = System.out;
        printStream.println("dateStop: " + format);
        try {
            date = simpleDateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        long time = (date.getTime() / 1000) - j;
        PrintStream printStream2 = System.out;
        printStream2.println("sec differ Total seconds: " + time);
        return String.valueOf(time);
    }

    public static String utcTime() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Calendar calendar2 = Calendar.getInstance(Locale.ENGLISH);
        calendar2.set(1970, 0, 1);
        Log.e("", "secondsSinceEpoch : " + calendar2.getTime());
        Log.e("", "secondsSinceEpoch : " + calendar.getTime());
        long timeInMillis = calendar2.getTimeInMillis() / 1000;
        long timeInMillis2 = calendar.getTimeInMillis() / 1000;
        Log.e("", "secondsSinceEpoch : " + timeInMillis2);
        Log.e("", "secondsSinceEpochmyCalendar : " + timeInMillis);
        Log.e("", "secondsSinceEpoch diff: " + (timeInMillis2 - timeInMillis));
        return String.valueOf(timeInMillis2);
    }

    public static String getMd5HashKey() {
        byte[] digest;
        StringBuilder sb;
        String format = new SimpleDateFormat("HH:mm:ss.SSSSSS").format(new Date());
        Log.e("", "formateddate :" + format);
        StringBuilder sb2 = null;
        try {
            digest = MessageDigest.getInstance("MD5").digest(format.getBytes(Key.STRING_CHARSET_NAME));
            sb = new StringBuilder();
        } catch (Exception e) {
            e = e;
        }
        try {
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02X", Byte.valueOf(digest[i])));
            }
            System.out.println(sb.toString());
            return sb.toString();
        } catch (Exception e2) {
            e = e2;
            sb2 = sb;
            e.printStackTrace();
            return sb2.toString();
        }
    }

    public static String GetDescByMarketCode(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 1537:
                if (str.equals("01")) {
                    c = 0;
                    break;
                }
                break;
            case 1539:
                if (str.equals("03")) {
                    c = 1;
                    break;
                }
                break;
            case 1541:
                if (str.equals("05")) {
                    c = 2;
                    break;
                }
                break;
            case 1542:
                if (str.equals("06")) {
                    c = 3;
                    break;
                }
                break;
            case 1543:
                if (str.equals("07")) {
                    c = 4;
                    break;
                }
                break;
            case 1544:
                if (str.equals("08")) {
                    c = 5;
                    break;
                }
                break;
            case 1545:
                if (str.equals("09")) {
                    c = 6;
                    break;
                }
                break;
            case 1567:
                if (str.equals("10")) {
                    c = 7;
                    break;
                }
                break;
            case 1569:
                if (str.equals("12")) {
                    c = '\b';
                    break;
                }
                break;
            case 1570:
                if (str.equals("13")) {
                    c = '\t';
                    break;
                }
                break;
            case 47726:
                if (str.equals("020")) {
                    c = '\n';
                    break;
                }
                break;
            case 47727:
                if (str.equals("021")) {
                    c = 11;
                    break;
                }
                break;
            case 47728:
                if (str.equals("022")) {
                    c = '\f';
                    break;
                }
                break;
            case 47729:
                if (str.equals("023")) {
                    c = '\r';
                    break;
                }
                break;
            case 47730:
                if (str.equals("024")) {
                    c = 14;
                    break;
                }
                break;
            case 65910:
                if (str.equals("BNB")) {
                    c = 15;
                    break;
                }
                break;
            case 69989:
                if (str.equals("FUT")) {
                    c = 16;
                    break;
                }
                break;
            case 78103:
                if (str.equals("ODL")) {
                    c = 17;
                    break;
                }
                break;
            case 81012:
                if (str.equals("REG")) {
                    c = 18;
                    break;
                }
                break;
            case 82096:
                if (str.equals("SIF")) {
                    c = 19;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 18:
                return "Regular";
            case 1:
            case 16:
                return "Future";
            case 2:
                return "Stock Option";
            case 3:
                return "Index Option";
            case 4:
            case 19:
                return "Stock Index Future";
            case 5:
            case 17:
                return "Odd Lot";
            case 6:
                return "Negotiated Deal";
            case 7:
                return "Equities Square-Up";
            case '\b':
                return "Futures Square-Up";
            case '\t':
                return "Trade Rectification and Modification";
            case '\n':
            case 15:
                return "Bills and Bonds";
            case 11:
                return "Bills and Bonds TCR";
            case '\f':
                return "Bills and Bonds Quote Request";
            case '\r':
                return "Bills and Bonds Quote";
            case 14:
                return "Bills and Bonds Quote Response";
            default:
                return "Invalid Market State";
        }
    }

    public static String GetErrorCodeDesc(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 47653683:
                if (str.equals("20001")) {
                    c = 0;
                    break;
                }
                break;
            case 47653684:
                if (str.equals("20002")) {
                    c = 1;
                    break;
                }
                break;
            case 47653687:
                if (str.equals("20005")) {
                    c = 2;
                    break;
                }
                break;
            case 47653688:
                if (str.equals("20006")) {
                    c = 3;
                    break;
                }
                break;
            case 47653689:
                if (str.equals("20007")) {
                    c = 4;
                    break;
                }
                break;
            case 47653690:
                if (str.equals("20008")) {
                    c = 5;
                    break;
                }
                break;
            case 47653691:
                if (str.equals("20009")) {
                    c = 6;
                    break;
                }
                break;
            case 47653713:
                if (str.equals("20010")) {
                    c = 7;
                    break;
                }
                break;
            case 47653714:
                if (str.equals("20011")) {
                    c = '\b';
                    break;
                }
                break;
            case 47653719:
                if (str.equals("20016")) {
                    c = '\t';
                    break;
                }
                break;
            case 47653747:
                if (str.equals("20023")) {
                    c = '\n';
                    break;
                }
                break;
            case 47653748:
                if (str.equals("20024")) {
                    c = 11;
                    break;
                }
                break;
            case 47653749:
                if (str.equals("20025")) {
                    c = '\f';
                    break;
                }
                break;
            case 47653753:
                if (str.equals("20029")) {
                    c = '\r';
                    break;
                }
                break;
            case 47653776:
                if (str.equals("20031")) {
                    c = 14;
                    break;
                }
                break;
            case 47653812:
                if (str.equals("20046")) {
                    c = 15;
                    break;
                }
                break;
            case 47653838:
                if (str.equals("20051")) {
                    c = 16;
                    break;
                }
                break;
            case 47653842:
                if (str.equals("20055")) {
                    c = 17;
                    break;
                }
                break;
            case 47653846:
                if (str.equals("20059")) {
                    c = 18;
                    break;
                }
                break;
            case 47653872:
                if (str.equals("20064")) {
                    c = 19;
                    break;
                }
                break;
            case 47653873:
                if (str.equals("20065")) {
                    c = 20;
                    break;
                }
                break;
            case 47653900:
                if (str.equals("20071")) {
                    c = 21;
                    break;
                }
                break;
            case 47653905:
                if (str.equals("20076")) {
                    c = 22;
                    break;
                }
                break;
            case 47653966:
                if (str.equals("20095")) {
                    c = 23;
                    break;
                }
                break;
            case 47653967:
                if (str.equals("20096")) {
                    c = 24;
                    break;
                }
                break;
            case 47653968:
                if (str.equals("20097")) {
                    c = 25;
                    break;
                }
                break;
            case 47653970:
                if (str.equals("20099")) {
                    c = 26;
                    break;
                }
                break;
            case 47654644:
                if (str.equals("20101")) {
                    c = 27;
                    break;
                }
                break;
            case 47654645:
                if (str.equals("20102")) {
                    c = 28;
                    break;
                }
                break;
            case 47654646:
                if (str.equals("20103")) {
                    c = 29;
                    break;
                }
                break;
            case 47654647:
                if (str.equals("20104")) {
                    c = 30;
                    break;
                }
                break;
            case 47654648:
                if (str.equals("20105")) {
                    c = 31;
                    break;
                }
                break;
            case 47654649:
                if (str.equals("20106")) {
                    c = ' ';
                    break;
                }
                break;
            case 47654650:
                if (str.equals("20107")) {
                    c = '!';
                    break;
                }
                break;
            case 47654708:
                if (str.equals("20123")) {
                    c = Typography.quote;
                    break;
                }
                break;
            case 47654736:
                if (str.equals("20130")) {
                    c = '#';
                    break;
                }
                break;
            case 47654739:
                if (str.equals("20133")) {
                    c = Typography.dollar;
                    break;
                }
                break;
            case 47654773:
                if (str.equals("20146")) {
                    c = '%';
                    break;
                }
                break;
            case 47654774:
                if (str.equals("20147")) {
                    c = Typography.amp;
                    break;
                }
                break;
            case 47658488:
                if (str.equals("20501")) {
                    c = '\'';
                    break;
                }
                break;
            case 47658489:
                if (str.equals("20502")) {
                    c = '(';
                    break;
                }
                break;
            case 47658490:
                if (str.equals("20503")) {
                    c = ')';
                    break;
                }
                break;
            case 47658491:
                if (str.equals("20504")) {
                    c = '*';
                    break;
                }
                break;
            case 47930738:
                if (str.equals("29999")) {
                    c = '+';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return "Client Code is not existed.";
            case 1:
                return "Client Code is not active.";
            case 2:
                return "Security switch has been turned off.";
            case 3:
                return "This kind of order is not allowed in the current trading phase.";
            case 4:
                return "This kind of order is not allowed when symbol is suspended.";
            case 5:
                return "Price should be an integral multiple of price tick.";
            case 6:
                return "Price is out of limit range.";
            case 7:
                return "Qty should be an integral multiple of Lot size.";
            case '\b':
                return "Order Qty and Order Value are out of limit range.";
            case '\t':
                return "Short sell will only be permissible on Uptick or Zero-Plus Tick.";
            case '\n':
                return "Member or Market Not Exist.";
            case 11:
                return "The qualification of the member is restricted on this product.";
            case '\f':
                return "The qualification of the member is restricted on this business.";
            case '\r':
                return "The qualification of the participant is restricted on this business.";
            case 14:
                return "The order elements from the two parties cannot match.";
            case 15:
                return "The market order is cancelled since it doesn't meet the trading conditions.";
            case 16:
                return "SettlementDate is out of range.";
            case 17:
                return "Member has no business rights.";
            case 18:
                return "Member Exposue is Out of Limit.";
            case 19:
                return "UIN Trading Maring is Out of Limit.";
            case 20:
                return "Market is closed.";
            case 21:
                return "Order submitted by Exchange could not be cancelled or replaced by investor.";
            case 22:
                return "The combination of TimeInForce, OrdType, Side fields is invalid.";
            case 23:
                return "Symbol or ApplID of cancel order/replace cancel order should be same as the original order.";
            case 24:
                return "Order cannot be cancelled or replaced.";
            case 25:
                return "Original order of cancel request/replace cancel request does not exist.";
            case 26:
                return "Duplicated Order.";
            case 27:
                return "Wrong ApplID.";
            case 28:
                return "This symbol does not support this business.";
            case 29:
                return "Wrong message format.";
            case 30:
                return "Market is not opened.";
            case 31:
                return "Trader ID does not match the gateway.";
            case ' ':
                return "Invalid field value.";
            case '!':
                return "Unsupported message type.";
            case '\"':
                return "Counterparty Trader ID Not Exist.";
            case '#':
                return "Can not find counterparty order.";
            case '$':
                return "TradeDate must be T or T – 1 day.";
            case '%':
                return "Order Timeout.";
            case '&':
                return "Duplicated QuoteReqID or QuoteID orQuoteRespID.";
            case '\'':
                return "UIN Position Over Limit.";
            case '(':
                return "Member Position Over Limit.";
            case ')':
                return "Market Position Over Limit.";
            case '*':
                return "Special Position Over Limit.";
            case '+':
                return "Other fault.";
            default:
                return str;
        }
    }

    public static String GetDescByOrderSideInitCap(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 49:
                if (str.equals("1")) {
                    c = 0;
                    break;
                }
                break;
            case 50:
                if (str.equals("2")) {
                    c = 1;
                    break;
                }
                break;
            case 53:
                if (str.equals("5")) {
                    c = 2;
                    break;
                }
                break;
            case 56:
                if (str.equals("8")) {
                    c = 3;
                    break;
                }
                break;
            case 71:
                if (str.equals("G")) {
                    c = 4;
                    break;
                }
                break;
            case 89:
                if (str.equals("Y")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return "Buy Order ";
            case 1:
                return "Sell Order ";
            case 2:
            case 5:
                return "Short Sell Order ";
            case 3:
                return "Cross Order ";
            case 4:
                return "Leverage Buy Order ";
            default:
                return "Invalid Order Side ";
        }
    }

    public static String OrderNature(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 49:
                if (str.equals("1")) {
                    c = 0;
                    break;
                }
                break;
            case 50:
                if (str.equals("2")) {
                    c = 1;
                    break;
                }
                break;
            case 53:
                if (str.equals("5")) {
                    c = 2;
                    break;
                }
                break;
            case 71:
                if (str.equals("G")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 3:
                return " Bought Order ";
            case 1:
            case 2:
                return " Sold Order ";
            default:
                return " Invalid Order Nature ";
        }
    }

    public static String sendOrderKeyValue(String str) {
        return "[9,{\"key\":\"ordKey\",\"val\": \"" + str + "\"}]";
    }

    public static String getSocketFeedFromMemory(String str) {
        return "[9,{\"key\":\"getMf\",\"val\":[\"" + str + "\"]}]";
    }

    public static String setSendFormatSub(String str) {
        return "[5, \"{\\\"mf\\\":[\\\"" + str + "\\\"]}\"]";
    }

    public static String setSendFormatUnSub(String str) {
        return "[6, \"{\\\"mf\\\":[\\\"" + str + "\\\"]}\"]";
    }

    public static String setSendFormatSubList(String str) {
        return "[5, \"{\\\"mf\\\":[" + str + "]}\"]";
    }

    public static String setSendFormatUnSubList(String str) {
        return "[6, \"{\\\"mf\\\":[" + str + "]}\"]";
    }

    public static String setSendFormatSubMO(String str) {
        return "[5,\"{\\\"mbo\\\":[\\\"" + str + "\\\"]}\"]";
    }

    public static String setSendFormatUnSubMO(String str) {
        return "[6,\"{\\\"mbo\\\":[\\\"" + str + "\\\"]}\"]";
    }

    public static String setSendFormatSubMP(String str) {
        return "[5,\"{\\\"mbp\\\":[\\\"" + str + "\\\"]}\"]";
    }

    public static String setSendFormatUnSubMP(String str) {
        return "[6,\"{\\\"mbp\\\":[\\\"" + str + "\\\"]}\"]";
    }

    public static String getCurrentTimeUsingDate() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public void getStatuses(Activity activity, String str, ImageView imageView) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2044189691:
                if (str.equals("LOADED")) {
                    c = 0;
                    break;
                }
                break;
            case -1957249943:
                if (str.equals("OPENED")) {
                    c = 1;
                    break;
                }
                break;
            case -545201864:
                if (str.equals("OPENING")) {
                    c = 2;
                    break;
                }
                break;
            case -544880658:
                if (str.equals("PRE-OPENING")) {
                    c = 3;
                    break;
                }
                break;
            case -528833708:
                if (str.equals("PRE-OPEN")) {
                    c = 4;
                    break;
                }
                break;
            case 78230:
                if (str.equals("OHO")) {
                    c = 5;
                    break;
                }
                break;
            case 646035605:
                if (str.equals("OPEN-CLOSE")) {
                    c = 6;
                    break;
                }
                break;
            case 774832654:
                if (str.equals("PRE-CLOSE")) {
                    c = 7;
                    break;
                }
                break;
            case 1124965819:
                if (str.equals(DebugCoroutineInfoImplKt.SUSPENDED)) {
                    c = '\b';
                    break;
                }
                break;
            case 1584523413:
                if (str.equals("CLOSING")) {
                    c = '\t';
                    break;
                }
                break;
            case 1916572267:
                if (str.equals("POST-CLOSE")) {
                    c = '\n';
                    break;
                }
                break;
            case 1990776172:
                if (str.equals("CLOSED")) {
                    c = 11;
                    break;
                }
                break;
            case 2027654547:
                if (str.equals("DUMPED")) {
                    c = '\f';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                imageView.setBackgroundResource(R.drawable.custom_circle_red);
                return;
            case 1:
                imageView.setBackgroundResource(R.drawable.custom_circle_green);
                return;
            case 2:
                imageView.setBackgroundResource(R.drawable.custom_half_circle_y_g);
                return;
            case 3:
                imageView.setBackgroundResource(R.drawable.custom_half_circle_r_y);
                return;
            case 4:
                imageView.setBackgroundResource(R.drawable.custom_circle_yellow);
                return;
            case 5:
                imageView.setBackgroundResource(R.drawable.custom_circle_green);
                return;
            case 6:
                imageView.setBackgroundResource(R.drawable.custom_circle_red);
                return;
            case 7:
                imageView.setBackgroundResource(R.drawable.custom_circle_yellow);
                return;
            case '\b':
                imageView.setBackgroundResource(R.drawable.custom_circle_red);
                return;
            case '\t':
                imageView.setBackgroundResource(R.drawable.custom_circle_red);
                return;
            case '\n':
                imageView.setBackgroundResource(R.drawable.custom_half_circle_g_y);
                return;
            case 11:
                imageView.setBackgroundResource(R.drawable.custom_circle_red);
                return;
            case '\f':
                imageView.setBackgroundResource(R.drawable.custom_half_circle_y_r);
                return;
            default:
                return;
        }
    }

    public void getStatuses(Context context, String str, ImageView imageView, TextView textView) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2044189691:
                if (str.equals("LOADED")) {
                    c = 0;
                    break;
                }
                break;
            case -1957249943:
                if (str.equals("OPENED")) {
                    c = 1;
                    break;
                }
                break;
            case -545201864:
                if (str.equals("OPENING")) {
                    c = 2;
                    break;
                }
                break;
            case -544880658:
                if (str.equals("PRE-OPENING")) {
                    c = 3;
                    break;
                }
                break;
            case -528833708:
                if (str.equals("PRE-OPEN")) {
                    c = 4;
                    break;
                }
                break;
            case 78230:
                if (str.equals("OHO")) {
                    c = 5;
                    break;
                }
                break;
            case 646035605:
                if (str.equals("OPEN-CLOSE")) {
                    c = 6;
                    break;
                }
                break;
            case 774832654:
                if (str.equals("PRE-CLOSE")) {
                    c = 7;
                    break;
                }
                break;
            case 1124965819:
                if (str.equals(DebugCoroutineInfoImplKt.SUSPENDED)) {
                    c = '\b';
                    break;
                }
                break;
            case 1584523413:
                if (str.equals("CLOSING")) {
                    c = '\t';
                    break;
                }
                break;
            case 1916572267:
                if (str.equals("POST-CLOSE")) {
                    c = '\n';
                    break;
                }
                break;
            case 1990776172:
                if (str.equals("CLOSED")) {
                    c = 11;
                    break;
                }
                break;
            case 2027654547:
                if (str.equals("DUMPED")) {
                    c = '\f';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 6:
            case '\b':
            case '\t':
            case 11:
                imageView.setBackgroundResource(R.drawable.custom_circle_red);
                textView.setTextColor(context.getResources().getColor(R.color.negative_text_color));
                return;
            case 1:
            case 5:
                imageView.setBackgroundResource(R.drawable.custom_circle_green);
                textView.setTextColor(context.getResources().getColor(R.color.positive_text_color));
                return;
            case 2:
                imageView.setBackgroundResource(R.drawable.custom_half_circle_y_g);
                return;
            case 3:
                imageView.setBackgroundResource(R.drawable.custom_half_circle_r_y);
                return;
            case 4:
            case 7:
                imageView.setBackgroundResource(R.drawable.custom_circle_yellow);
                textView.setTextColor(context.getResources().getColor(R.color.pre_open_text_color));
                return;
            case '\n':
                imageView.setBackgroundResource(R.drawable.custom_half_circle_g_y);
                return;
            case '\f':
                imageView.setBackgroundResource(R.drawable.custom_half_circle_y_r);
                return;
            default:
                return;
        }
    }

    public static String getNetworkClass(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.isAvailable()) {
            if (activeNetworkInfo.getType() == 1) {
                return "WIFI";
            }
            if (activeNetworkInfo.getType() == 0) {
                switch (activeNetworkInfo.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return "2G";
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                    case 17:
                        return "3G";
                    case 13:
                    case 18:
                    case 19:
                        return "4G";
                    case 16:
                    default:
                        return "?";
                }
            }
            return "?";
        }
        return "-";
    }

    public static boolean isOnline(Context context) {
        String networkClass = getNetworkClass(context);
        if (networkClass.equals("WIFI") || networkClass.equals("3G") || networkClass.equals("4G") || networkClass.equals("2G")) {
            return true;
        }
        if (context != null) {
            Toast.makeText(context, "Please Check Your Internet Connection and Try again later.", 0).show();
        }
        return false;
    }

    public static String getTimeStamp() {
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

    public int size() {
        Context context = this.context;
        int parseInt = Integer.parseInt("lstFontSize");
        Object[] objArr = new Object[1];
        int i = resSize;
        if (i == 0) {
            i = resSizeTablet;
        }
        objArr[0] = String.valueOf(i);
        return Integer.parseInt(context.getString(parseInt, objArr));
    }

    public static String GetPriceFormattedValue(String str) {
        try {
            return PRICE_FORMAT.format(utils.convertStringToDouble(str));
        } catch (Exception unused) {
            return "0.00";
        }
    }

    public static String GetPriceFormattedValueBuyPower(String str) {
        try {
            return PRICE_FORMAT.format(utils.convertStringToDouble(str));
        } catch (Exception unused) {
            return "-";
        }
    }

    public static String GetVolumeFormattedValue(String str) {
        try {
            return VOLUME_FORMAT.format(utils.convertStringToDouble(str));
        } catch (Exception unused) {
            return "0";
        }
    }
}
