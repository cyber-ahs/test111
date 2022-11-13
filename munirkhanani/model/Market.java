package munirkhanani.model;
/* loaded from: classes2.dex */
public class Market {
    private String avg;
    private int bgColor;
    private String buyPri;
    public int buyPriceColor;
    public int buyPriceHighLightedColor;
    private String buyVol;
    public int buyVolumeColor;
    public int buyVolumeHighLightedColor;
    private double changePerc;
    public int color;
    public int colors;
    private String high;
    public int highLightedColor;
    private int imageIcon;
    private String lastPrice;
    public int lastPriceColor;
    public int lastPriceHighLightedColor;
    private String lastTime;
    public int lastTotalVolColor;
    public int lastTotalVolHighLightedColor;
    private String lastVolume;
    private String low;
    private String lowerPri;
    private String marketCode;
    private String netChange;
    private String openprice;
    private String sellPri;
    public int sellPriceColor;
    public int sellPriceHighLightedColor;
    private String sellVol;
    public int sellVolumeColor;
    public int sellVolumeHighLightedColor;
    private String symbolCode;
    private String symbolName;
    private boolean updateRow = false;
    private String upperPri;

    public boolean isUpdateRow() {
        return this.updateRow;
    }

    public void setUpdateRow(boolean z) {
        this.updateRow = z;
    }

    public int getHighLightedColor() {
        return this.highLightedColor;
    }

    public void setHighLightedColor(int i) {
        this.highLightedColor = i;
    }

    public int getBuyVolumeColor() {
        return this.buyVolumeColor;
    }

    public void setBuyVolumeColor(int i) {
        this.buyVolumeColor = i;
    }

    public int getSellPriceColor() {
        return this.sellPriceColor;
    }

    public void setSellPriceColor(int i) {
        this.sellPriceColor = i;
    }

    public int getSellVolumeColor() {
        return this.sellVolumeColor;
    }

    public void setSellVolumeColor(int i) {
        this.sellVolumeColor = i;
    }

    public int getLastPriceColor() {
        return this.lastPriceColor;
    }

    public void setLastPriceColor(int i) {
        this.lastPriceColor = i;
    }

    public int getLastTotalVolColor() {
        return this.lastTotalVolColor;
    }

    public void setLastTotalVolColor(int i) {
        this.lastTotalVolColor = i;
    }

    public int getBuyVolumeHighLightedColor() {
        return this.buyVolumeHighLightedColor;
    }

    public void setBuyVolumeHighLightedColor(int i) {
        this.buyVolumeHighLightedColor = i;
    }

    public int getSellPriceHighLightedColor() {
        return this.sellPriceHighLightedColor;
    }

    public void setSellPriceHighLightedColor(int i) {
        this.sellPriceHighLightedColor = i;
    }

    public int getSellVolumeHighLightedColor() {
        return this.sellVolumeHighLightedColor;
    }

    public void setSellVolumeHighLightedColor(int i) {
        this.sellVolumeHighLightedColor = i;
    }

    public int getLastPriceHighLightedColor() {
        return this.lastPriceHighLightedColor;
    }

    public void setLastPriceHighLightedColor(int i) {
        this.lastPriceHighLightedColor = i;
    }

    public int getLastTotalVolHighLightedColor() {
        return this.lastTotalVolHighLightedColor;
    }

    public void setLastTotalVolHighLightedColor(int i) {
        this.lastTotalVolHighLightedColor = i;
    }

    public int getBuyPriceColor() {
        return this.buyPriceColor;
    }

    public void setBuyPriceColor(int i) {
        this.buyPriceColor = i;
    }

    public int getBuyPriceHighLightedColor() {
        return this.buyPriceHighLightedColor;
    }

    public void setBuyPriceHighLightedColor(int i) {
        this.buyPriceHighLightedColor = i;
    }

    public int getBgColor() {
        return this.bgColor;
    }

    public void setBgColor(int i) {
        this.bgColor = i;
    }

    public String getSymbolName() {
        return this.symbolName;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int i) {
        this.color = i;
    }

    public int getColors() {
        return this.colors;
    }

    public void setColors(int i) {
        this.colors = this.colors;
    }

    public int getImageIcon() {
        return this.imageIcon;
    }

    public void setImageIcon(int i) {
        this.imageIcon = i;
    }

    public String getSymbolCode() {
        return this.symbolCode;
    }

    public void setSymbolCode(String str) {
        this.symbolCode = str;
    }

    public String getHigh() {
        return this.high;
    }

    public void setHigh(String str) {
        this.high = str;
    }

    public String getLow() {
        return this.low;
    }

    public void setLow(String str) {
        this.low = str;
    }

    public String getAvg() {
        return this.avg;
    }

    public void setAvg(String str) {
        this.avg = str;
    }

    public double getChangePerc() {
        return this.changePerc;
    }

    public void setChangePerc(double d) {
        this.changePerc = d;
    }

    public String getLastVolume() {
        return this.lastVolume;
    }

    public void setLastVolume(String str) {
        this.lastVolume = str;
    }

    public String getUpperPri() {
        return this.upperPri;
    }

    public void setUpperPri(String str) {
        this.upperPri = str;
    }

    public String getLowerPri() {
        return this.lowerPri;
    }

    public void setLowerPri(String str) {
        this.lowerPri = str;
    }

    public String getMarketCode() {
        return this.marketCode;
    }

    public void setMarketCode(String str) {
        this.marketCode = str;
    }

    public String getNetChange() {
        return this.netChange;
    }

    public void setNetChange(String str) {
        this.netChange = str;
    }

    public String getLastPrice() {
        return this.lastPrice;
    }

    public void setLastPrice(String str) {
        this.lastPrice = str;
    }

    public String getLastTime() {
        return this.lastTime;
    }

    public void setLastTime(String str) {
        this.lastTime = str;
    }

    public String getBuyVol() {
        return this.buyVol;
    }

    public void setBuyVol(String str) {
        this.buyVol = str;
    }

    public String getBuyPri() {
        return this.buyPri;
    }

    public void setBuyPri(String str) {
        this.buyPri = str;
    }

    public String getSellVol() {
        return this.sellVol;
    }

    public void setSellVol(String str) {
        this.sellVol = str;
    }

    public String getSellPri() {
        return this.sellPri;
    }

    public void setSellPri(String str) {
        this.sellPri = str;
    }

    public String getOpenprice() {
        return this.openprice;
    }

    public void setOpenprice(String str) {
        this.openprice = str;
    }

    public void setSymbolName(String str) {
        this.symbolName = str;
    }
}
