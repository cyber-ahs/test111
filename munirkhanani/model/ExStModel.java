package munirkhanani.model;

import android.widget.ImageView;
/* loaded from: classes2.dex */
public class ExStModel {
    public int HighValueChangeTextColor;
    private Object ImageDrawable;
    public int IndicesValueTextColor;
    public int LowValueChangeTextColor;
    public int NetChangeTextColor;
    public int Value_tradedTextColor;
    ImageView arrowred;
    String current_index;
    String highChangePercan;
    String highChangeValue;
    String high_index;
    String indiciesValue;
    public ImageView kse100NetChangeIcon;
    String lowChangePercan;
    String lowChangeValue;
    String low_index;
    String market_code;
    String net_change;
    String net_changePercan;
    private boolean updateRow = false;
    public ImageView uppergreen;
    String value_traded;
    String volume_traded;

    public boolean isUpdateRow() {
        return this.updateRow;
    }

    public void setUpdateRow(boolean z) {
        this.updateRow = z;
    }

    public void ExStModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, int i2, int i3, String str9, String str10, String str11, String str12, ImageView imageView) {
        this.market_code = str;
        this.current_index = str2;
        this.volume_traded = str3;
        this.value_traded = str4;
        this.high_index = str5;
        this.low_index = str6;
        this.net_change = str7;
        this.net_changePercan = str8;
        this.NetChangeTextColor = i;
        this.HighValueChangeTextColor = i2;
        this.LowValueChangeTextColor = i3;
        this.highChangeValue = str9;
        this.highChangePercan = str10;
        this.lowChangeValue = str11;
        this.lowChangePercan = str12;
        this.arrowred = this.arrowred;
    }

    public String getMarket_code() {
        return this.market_code;
    }

    public void setMarket_code(String str) {
        this.market_code = str;
    }

    public String getCurrent_index() {
        return this.current_index;
    }

    public void setCurrent_index(String str) {
        this.current_index = str;
    }

    public String getVolume_traded() {
        return this.volume_traded;
    }

    public void setVolume_traded(String str) {
        this.volume_traded = str;
    }

    public String getValue_traded() {
        return this.value_traded;
    }

    public void setValue_traded(String str) {
        this.value_traded = str;
    }

    public String getHigh_index() {
        return this.high_index;
    }

    public void setHigh_index(String str) {
        this.high_index = str;
    }

    public String getLow_index() {
        return this.low_index;
    }

    public void setLow_index(String str) {
        this.low_index = str;
    }

    public String getNet_change() {
        return this.net_change;
    }

    public void setNet_change(String str) {
        this.net_change = str;
    }

    public String getNet_changePercan() {
        return this.net_changePercan;
    }

    public void setNet_changePercan(String str) {
        this.net_changePercan = str;
    }

    public int getHighValueChangeTextColor() {
        return this.HighValueChangeTextColor;
    }

    public void setHighValueChangeTextColor(int i) {
        this.HighValueChangeTextColor = i;
    }

    public int getLowValueChangeTextColor() {
        return this.LowValueChangeTextColor;
    }

    public void setLowValueChangeTextColor(int i) {
        this.LowValueChangeTextColor = i;
    }

    public String getHighChangeValue() {
        return this.highChangeValue;
    }

    public void setHighChangeValue(String str) {
        this.highChangeValue = str;
    }

    public String getHighChangePercan() {
        return this.highChangePercan;
    }

    public void setHighChangePercan(String str) {
        this.highChangePercan = str;
    }

    public String getLowChangeValue() {
        return this.lowChangeValue;
    }

    public void setLowChangeValue(String str) {
        this.lowChangeValue = str;
    }

    public String getLowChangePercan() {
        return this.lowChangePercan;
    }

    public void setLowChangePercan(String str) {
        this.lowChangePercan = str;
    }

    public void setNetChangeTextColor(int i) {
        this.NetChangeTextColor = i;
    }

    public int getNetChangeTextColor() {
        return this.NetChangeTextColor;
    }
}
