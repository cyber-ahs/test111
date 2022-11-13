package munirkhanani.model;
/* loaded from: classes2.dex */
public class MoversModel {
    private int Color;
    private int backgroundChanges;
    private String byVolChange;
    private String byVolChangePerc;
    private String byVolSymbCode;
    private String byVolSymbName;
    private String byVolVolume;
    private String byVolcurrent;

    public int getColor() {
        return this.Color;
    }

    public void setColor(int i) {
        this.Color = i;
    }

    public String getByVolSymbName() {
        return this.byVolSymbName;
    }

    public void setByVolSymbName(String str) {
        this.byVolSymbName = str;
    }

    public String getByVolSymbCode() {
        return this.byVolSymbCode;
    }

    public void setByVolSymbCode(String str) {
        this.byVolSymbCode = str;
    }

    public String getByVolChange() {
        return this.byVolChange;
    }

    public void setByVolChange(String str) {
        this.byVolChange = str;
    }

    public String getByVolChangePerc() {
        return this.byVolChangePerc;
    }

    public void setByVolChangePerc(String str) {
        this.byVolChangePerc = str;
    }

    public String getByVolVolume() {
        return this.byVolVolume;
    }

    public void setByVolVolume(String str) {
        this.byVolVolume = str;
    }

    public String getByVolcurrent() {
        return this.byVolcurrent;
    }

    public void setByVolcurrent(String str) {
        this.byVolcurrent = str;
    }

    public int getBackgroundChanges() {
        return this.backgroundChanges;
    }

    public void setBackgroundChanges(int i) {
        this.backgroundChanges = i;
    }

    public void VolumeLeader(String str, String str2, String str3, String str4, String str5, String str6, int i, int i2) {
        this.byVolSymbCode = str;
        this.byVolSymbName = str3;
        this.byVolChange = str5;
        this.byVolChangePerc = str4;
        this.byVolVolume = str6;
        this.byVolcurrent = str2;
        this.Color = i2;
        this.backgroundChanges = i;
    }
}
