package munirkhanani.model;
/* loaded from: classes2.dex */
public class UserManual {
    private String desc;
    private int icon;
    private String name;

    public void UserManual(String str, int i, String str2) {
        this.name = str;
        this.icon = i;
        this.desc = str2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getIcon() {
        return this.icon;
    }

    public void setIcon(int i) {
        this.icon = i;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }
}
