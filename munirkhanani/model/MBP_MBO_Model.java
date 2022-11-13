package munirkhanani.model;
/* loaded from: classes2.dex */
public class MBP_MBO_Model {
    private String buy;
    private String buyvolume;
    private String sell;
    private String sellvolume;

    public void MBP_MBO_Model(String str, String str2, String str3, String str4) {
        this.buy = str;
        this.buyvolume = str4;
        this.sell = str2;
        this.sellvolume = str3;
    }

    public String getBuy() {
        return this.buy;
    }

    public void setBuy(String str) {
        this.buy = str;
    }

    public String getBuyvolume() {
        return this.buyvolume;
    }

    public void setBuyvolume(String str) {
        this.buyvolume = str;
    }

    public String getSell() {
        return this.sell;
    }

    public void setSell(String str) {
        this.sell = str;
    }

    public String getSellvolume() {
        return this.sellvolume;
    }

    public void setSellvolume(String str) {
        this.sellvolume = str;
    }
}
