package munirkhanani.model;
/* loaded from: classes2.dex */
public class CancelOrderParameters {
    public String MarketCode;
    public String OrderId;
    public String OrderSide;
    public String OrgClientOrderId;
    public String SymbolCode;
    public String pinCode;

    public String getPinCode() {
        return this.pinCode;
    }

    public void setPinCode(String str) {
        this.pinCode = str;
    }

    public String getSymbolCode() {
        return this.SymbolCode;
    }

    public void setOrderSide(String str) {
        this.OrderSide = str;
    }

    public String getMarketCode() {
        return this.MarketCode;
    }

    public void setSymbolCode(String str) {
        this.SymbolCode = str;
    }

    public void setMarketCode(String str) {
        this.MarketCode = str;
    }

    public void setOrderId(String str) {
        this.OrderId = str;
    }

    public void setOrgClientOrderId(String str) {
        this.OrgClientOrderId = str;
    }
}
