package munirkhanani.model;

import android.graphics.drawable.Drawable;
/* loaded from: classes2.dex */
public class LogSummary {
    private String clientid;
    private String date;
    private Drawable dateBackground;
    private String market;
    private int marketBackground;
    private int marketTextColor;
    private String orderType;
    private Drawable orderTypeImage;
    private String orderid;
    private String price;
    private String scrip;
    private String scripName;
    private String status;
    private String ticketNo;
    private String time;
    private Drawable timeBackground;
    private String userName;
    private String volume;

    public String getScripName() {
        return this.scripName;
    }

    public void setScripName(String str) {
        this.scripName = str;
    }

    public String getTicketNo() {
        return this.ticketNo;
    }

    public void setTicketNo(String str) {
        this.ticketNo = str;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public int getMarketTextColor() {
        return this.marketTextColor;
    }

    public void setMarketTextColor(int i) {
        this.marketTextColor = i;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
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

    public String getOrderType() {
        return this.orderType;
    }

    public String getClientid() {
        return this.clientid;
    }

    public void setClientid(String str) {
        this.clientid = str;
    }

    public void setOrderType(String str) {
        this.orderType = str;
    }

    public Drawable getOrderTypeImage() {
        return this.orderTypeImage;
    }

    public void setOrderTypeImage(Drawable drawable) {
        this.orderTypeImage = drawable;
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

    public void LogSummary(String str, String str2, String str3, String str4, String str5, String str6, String str7, Drawable drawable, String str8, int i, Drawable drawable2, Drawable drawable3, String str9) {
        this.scrip = str;
        this.orderType = str8;
        this.dateBackground = drawable2;
        this.timeBackground = drawable3;
        this.marketBackground = i;
        this.orderTypeImage = drawable;
        this.status = str9;
        this.price = str2;
        this.date = str3;
        this.time = str4;
        this.volume = str7;
        this.market = str5;
        this.orderid = str6;
    }
}
