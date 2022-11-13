package munirkhanani.dbmodel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/* loaded from: classes2.dex */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tradelink.db";
    private static final int DATABASE_VERSION = 27;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 27);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE  dateServerList (_id INTEGER PRIMARY KEY AUTOINCREMENT,date TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE  customeWatchList (_id INTEGER PRIMARY KEY AUTOINCREMENT,SYMBOL_CODE TEXT,SYMBOL_NAME TEXT,MARKET_CODE TEXT,BID_PRICE TEXT,BID_VOLUME TEXT,ASK_PRICE TEXT,ASK_VOLUME TEXT,LAST_TRADE_PRICE TEXT,LAST_TRADE_VOLUME TEXT,LAST_TRADE_TIME TEXT,AVERAGE_PRICE TEXT,HIGH_PRICE TEXT,LOW_PRICE TEXT,NET_CHANGE TEXT,NET_CHANGE_PER TEXT,OPEN_PRICE TEXT,UVAL TEXT,LVAL TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE  symbolsList (_id INTEGER PRIMARY KEY AUTOINCREMENT,SymbolListString TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE  accountsList (_id INTEGER PRIMARY KEY AUTOINCREMENT,ITEM_SYMBOL TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE  orderMsgList (_id INTEGER PRIMARY KEY AUTOINCREMENT,msg TEXT,dateTime TEXT,bgColor TEXT,textColor TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE  mbombpCustomList (_id INTEGER PRIMARY KEY AUTOINCREMENT,SYMBOL_CODE TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE  marketCode (_id INTEGER PRIMARY KEY AUTOINCREMENT,marketCodeItem TEXT);");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS dateServerList");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS mbombpCustomList");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS orderMsgList");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS accountsList");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS symbolsList");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS customeWatchList");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS marketCode");
        onCreate(sQLiteDatabase);
    }
}
