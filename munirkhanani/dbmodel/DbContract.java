package munirkhanani.dbmodel;

import android.provider.BaseColumns;
/* loaded from: classes2.dex */
public class DbContract {

    /* loaded from: classes2.dex */
    public static final class accountsList implements BaseColumns {
        public static final String ACCOUNT_NO = "ITEM_SYMBOL";
        public static final String TABLE_NAME = "accountsList";
    }

    /* loaded from: classes2.dex */
    public static final class customeWatchList implements BaseColumns {
        public static final String ASK_PRICE = "ASK_PRICE";
        public static final String ASK_VOLUME = "ASK_VOLUME";
        public static final String AVERAGE_PRICE = "AVERAGE_PRICE";
        public static final String BID_PRICE = "BID_PRICE";
        public static final String BID_VOLUME = "BID_VOLUME";
        public static final String HIGH_PRICE = "HIGH_PRICE";
        public static final String LAST_TRADE_PRICE = "LAST_TRADE_PRICE";
        public static final String LAST_TRADE_TIME = "LAST_TRADE_TIME";
        public static final String LAST_TRADE_VOLUME = "LAST_TRADE_VOLUME";
        public static final String LOW_PRICE = "LOW_PRICE";
        public static final String LVAL = "LVAL";
        public static final String MARKET_CODE = "MARKET_CODE";
        public static final String NET_CHANGE = "NET_CHANGE";
        public static final String NET_CHANGE_PER = "NET_CHANGE_PER";
        public static final String OPEN_PRICE = "OPEN_PRICE";
        public static final String SYMBOL_CODE = "SYMBOL_CODE";
        public static final String SYMBOL_NAME = "SYMBOL_NAME";
        public static final String TABLE_NAME = "customeWatchList";
        public static final String UVAL = "UVAL";
    }

    /* loaded from: classes2.dex */
    public static final class dateServer implements BaseColumns {
        public static final String Date = "date";
        public static final String TABLE_NAME = "dateServerList";
    }

    /* loaded from: classes2.dex */
    public static final class marketCode implements BaseColumns {
        public static final String TABLE_NAME = "marketCode";
        public static final String marketCodeItem = "marketCodeItem";
    }

    /* loaded from: classes2.dex */
    public static final class mboMbpCustom implements BaseColumns {
        public static final String SYMBOL_CODE = "SYMBOL_CODE";
        public static final String TABLE_NAME = "mbombpCustomList";
    }

    /* loaded from: classes2.dex */
    public static final class ordersMsgList implements BaseColumns {
        public static final String BgColor = "bgColor";
        public static final String DateTime = "dateTime";
        public static final String Message = "msg";
        public static final String TABLE_NAME = "orderMsgList";
        public static final String TextColor = "textColor";
    }

    /* loaded from: classes2.dex */
    public static final class symbolsList implements BaseColumns {
        public static final String SYMBOL_LIST_STRING = "SymbolListString";
        public static final String TABLE_NAME = "symbolsList";
    }
}
