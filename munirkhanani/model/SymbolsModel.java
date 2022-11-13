package munirkhanani.model;

import java.io.Serializable;
/* loaded from: classes2.dex */
public class SymbolsModel implements Serializable {
    private String CotStatus;
    private String MarketCode;
    private String SectorCode;
    private String SectorName;
    private String ShariahCompliant;
    private String SymbolCode;
    private String SymbolName;

    public String getSectorCode() {
        return this.SectorCode;
    }

    public void setSectorCode(String str) {
        this.SectorCode = str;
    }

    public String getSectorName() {
        return this.SectorName;
    }

    public void setSectorName(String str) {
        this.SectorName = str;
    }

    public String getMarketCode() {
        return this.MarketCode;
    }

    public void setMarketCode(String str) {
        this.MarketCode = str;
    }

    public String getSymbolCode() {
        return this.SymbolCode;
    }

    public void setSymbolCode(String str) {
        this.SymbolCode = str;
    }

    public String getSymbolName() {
        return this.SymbolName;
    }

    public void setSymbolName(String str) {
        this.SymbolName = str;
    }

    public String getCotStatus() {
        return this.CotStatus;
    }

    public void setCotStatus(String str) {
        this.CotStatus = str;
    }

    public void SymbolsModel(String str, String str2, String str3, String str4) {
        this.MarketCode = str;
        this.SymbolCode = str2;
        this.SymbolName = str3;
        this.CotStatus = str4;
    }

    public String getShariahCompliant() {
        return this.ShariahCompliant;
    }

    public void setShariahCompliant(String str) {
        this.ShariahCompliant = str;
    }
}
