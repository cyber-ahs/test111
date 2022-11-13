package munirkhanani.model;
/* loaded from: classes2.dex */
public class OpenPositionModel {
    private String avgPrice;
    private String executedAvgPrice;
    private String executedMtm;
    private String executedPosition;
    private String executedValue;
    private String executedVolume;
    private String inventroyAvgPrice;
    private String inventroyMtm;
    private String inventroyPosition;
    private String inventroyValue;
    private String inventroyVolume;
    private String marketPrice;
    private String mktCode;
    private String mtm;
    private String netPosition;
    private String position;
    private String realizedMtm;
    private String sessionAvgPrice;
    private String sessionMtm;
    private String sessionPosition;
    private String sessionValue;
    private String sessionVolume;
    private String symbol;
    private String symbolName;
    private String totalMtm;
    private String value;
    private String volume;

    public String getMktCode() {
        return this.mktCode;
    }

    public void setMktCode(String str) {
        this.mktCode = str;
    }

    public String getSymbolName() {
        return this.symbolName;
    }

    public void setSymbolName(String str) {
        this.symbolName = str;
    }

    public String getTotalMtm() {
        return this.totalMtm;
    }

    public void setTotalMtm(String str) {
        this.totalMtm = str;
    }

    public String getMarketPrice() {
        return this.marketPrice;
    }

    public void setMarketPrice(String str) {
        this.marketPrice = str;
    }

    public String getNetPosition() {
        return this.netPosition;
    }

    public void setNetPosition(String str) {
        this.netPosition = str;
    }

    public String getRealizedMtm() {
        return this.realizedMtm;
    }

    public void setRealizedMtm(String str) {
        this.realizedMtm = str;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String str) {
        this.symbol = str;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String str) {
        this.position = str;
    }

    public String getAvgPrice() {
        return this.avgPrice;
    }

    public void setAvgPrice(String str) {
        this.avgPrice = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String getVolume() {
        return this.volume;
    }

    public void setVolume(String str) {
        this.volume = str;
    }

    public String getMtm() {
        return this.mtm;
    }

    public void setMtm(String str) {
        this.mtm = str;
    }

    public String getInventroyPosition() {
        return this.inventroyPosition;
    }

    public void setInventroyPosition(String str) {
        this.inventroyPosition = str;
    }

    public String getInventroyAvgPrice() {
        return this.inventroyAvgPrice;
    }

    public void setInventroyAvgPrice(String str) {
        this.inventroyAvgPrice = str;
    }

    public String getInventroyValue() {
        return this.inventroyValue;
    }

    public void setInventroyValue(String str) {
        this.inventroyValue = str;
    }

    public String getInventroyVolume() {
        return this.inventroyVolume;
    }

    public void setInventroyVolume(String str) {
        this.inventroyVolume = str;
    }

    public String getInventroyMtm() {
        return this.inventroyMtm;
    }

    public void setInventroyMtm(String str) {
        this.inventroyMtm = str;
    }

    public String getSessionPosition() {
        return this.sessionPosition;
    }

    public void setSessionPosition(String str) {
        this.sessionPosition = str;
    }

    public String getSessionAvgPrice() {
        return this.sessionAvgPrice;
    }

    public void setSessionAvgPrice(String str) {
        this.sessionAvgPrice = str;
    }

    public String getSessionValue() {
        return this.sessionValue;
    }

    public void setSessionValue(String str) {
        this.sessionValue = str;
    }

    public String getSessionVolume() {
        return this.sessionVolume;
    }

    public void setSessionVolume(String str) {
        this.sessionVolume = str;
    }

    public String getSessionMtm() {
        return this.sessionMtm;
    }

    public String getExecutedPosition() {
        return this.executedPosition;
    }

    public void setExecutedPosition(String str) {
        this.executedPosition = str;
    }

    public String getExecutedAvgPrice() {
        return this.executedAvgPrice;
    }

    public void setExecutedAvgPrice(String str) {
        this.executedAvgPrice = str;
    }

    public String getExecutedValue() {
        return this.executedValue;
    }

    public void setExecutedValue(String str) {
        this.executedValue = str;
    }

    public String getExecutedVolume() {
        return this.executedVolume;
    }

    public void setExecutedVolume(String str) {
        this.executedVolume = str;
    }

    public String getExecutedMtm() {
        return this.executedMtm;
    }

    public void setExecutedMtm(String str) {
        this.executedMtm = str;
    }

    public void setSessionMtm(String str) {
        this.sessionMtm = str;
    }

    public void OpenPositionModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19) {
        this.symbol = str;
        this.netPosition = str18;
        this.marketPrice = str17;
        this.position = str2;
        this.avgPrice = str3;
        this.value = str4;
        this.volume = str5;
        this.mtm = str6;
        this.inventroyPosition = str7;
        this.inventroyAvgPrice = str8;
        this.inventroyValue = str9;
        this.inventroyVolume = str10;
        this.inventroyMtm = str11;
        this.sessionPosition = str12;
        this.sessionAvgPrice = str13;
        this.sessionValue = str14;
        this.sessionVolume = str15;
        this.sessionMtm = str16;
        this.realizedMtm = str19;
    }
}
