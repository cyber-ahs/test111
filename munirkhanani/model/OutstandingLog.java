package munirkhanani.model;

import android.graphics.drawable.Drawable;
/* loaded from: classes2.dex */
public class OutstandingLog {
    private String clientCode;
    private String clientid;
    private String date;
    private Drawable dateBackground;
    private String exchOrderId;
    private String market;
    private int marketBackground;
    private int marketTextColor;
    private String orderType;
    private Drawable orderTypeImage;
    private String orderid;
    private String price;
    private String scrip;
    private String scripName;
    private String time;
    private Drawable timeBackground;
    private String userName;
    private String volume;
    public int volumemarketTextColor;

    public String getClientid() {
        return this.clientid;
    }

    public void setClientid(String str) {
        this.clientid = str;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public String getScripName() {
        return this.scripName;
    }

    public void setScripName(String str) {
        this.scripName = str;
    }

    public int getMarketTextColor() {
        return this.marketTextColor;
    }

    public void setMarketTextColor(int i) {
        this.marketTextColor = i;
    }

    public int getVolumeMarketTextColor() {
        return this.volumemarketTextColor;
    }

    public void setVolumeMarketTextColor(int i) {
        this.volumemarketTextColor = i;
    }

    public String getClientCode() {
        return this.clientCode;
    }

    public void setClientCode(String str) {
        this.clientCode = str;
    }

    public void OutstandingLog(String str, String str2, String str3, String str4, String str5, String str6, String str7, Drawable drawable, int i, Drawable drawable2, Drawable drawable3, String str8, int i2, String str9, String str10, int i3) {
        this.scrip = str;
        this.price = str2;
        this.date = str3;
        this.time = str4;
        this.market = str5;
        this.orderid = str6;
        this.orderType = str7;
        this.orderTypeImage = drawable;
        this.marketBackground = i;
        this.dateBackground = drawable2;
        this.timeBackground = drawable3;
        this.marketTextColor = i3;
        this.volume = str8;
        this.clientCode = str9;
        this.exchOrderId = str10;
    }

    public String getExchOrderId() {
        return this.exchOrderId;
    }

    public void setExchOrderId(String str) {
        this.exchOrderId = str;
    }

    public String getScrip() {
        return this.scrip;
    }

    public void setScrip(String str) {
        this.scrip = str;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public String getMarket() {
        return this.market;
    }

    public void setMarket(String str) {
        this.market = str;
    }

    public String getVolume() {
        return this.volume;
    }

    public void setVolume(String str) {
        this.volume = str;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getOrderid() {
        return this.orderid;
    }

    public void setOrderid(String str) {
        this.orderid = str;
    }

    public Drawable getOrderTypeImage() {
        return this.orderTypeImage;
    }

    public void setOrderTypeImage(Drawable drawable) {
        this.orderTypeImage = drawable;
    }

    public String getOrderType() {
        return this.orderType;
    }

    public void setOrderType(String str) {
        this.orderType = str;
    }

    public int getMarketBackground() {
        return this.marketBackground;
    }

    public void setMarketBackground(int i) {
        this.marketBackground = i;
    }

    public Drawable getDateBackground() {
        return this.dateBackground;
    }

    public void setDateBackground(Drawable drawable) {
        this.dateBackground = drawable;
    }

    public Drawable getTimeBackground() {
        return this.timeBackground;
    }

    public void setTimeBackground(Drawable drawable) {
        this.timeBackground = drawable;
    }
}
