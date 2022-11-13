package munirkhanani.model;
/* loaded from: classes2.dex */
public class CapWatchDataModel {
    public static final int LOCKER = 2;
    public static final int UPPER = 1;
    private String lowSymbol;
    private String lowSymbolPrice;
    private String lowSymbolTotalVolume;
    private String lowSymbolVolume;
    private String symbol;
    private String symbolBidPrice;
    private String symbolBidTotalVolume;
    private String symbolBidVolume;
    private int type;

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String str) {
        this.symbol = str;
    }

    public String getSymbolBidTotalVolume() {
        return this.symbolBidTotalVolume;
    }

    public void setSymbolBidTotalVolume(String str) {
        this.symbolBidTotalVolume = str;
    }

    public String getSymbolBidPrice() {
        return this.symbolBidPrice;
    }

    public void setSymbolBidPrice(String str) {
        this.symbolBidPrice = str;
    }

    public String getSymbolBidVolume() {
        return this.symbolBidVolume;
    }

    public void setSymbolBidVolume(String str) {
        this.symbolBidVolume = str;
    }

    public String getLowSymbol() {
        return this.lowSymbol;
    }

    public void setLowSymbol(String str) {
        this.lowSymbol = str;
    }

    public String getLowSymbolVolume() {
        return this.lowSymbolVolume;
    }

    public void setLowSymbolVolume(String str) {
        this.lowSymbolVolume = str;
    }

    public String getLowSymbolPrice() {
        return this.lowSymbolPrice;
    }

    public void setLowSymbolPrice(String str) {
        this.lowSymbolPrice = str;
    }

    public String getLowSymbolTotalVolume() {
        return this.lowSymbolTotalVolume;
    }

    public void setLowSymbolTotalVolume(String str) {
        this.lowSymbolTotalVolume = str;
    }
}
