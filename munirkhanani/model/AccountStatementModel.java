package munirkhanani.model;
/* loaded from: classes2.dex */
public class AccountStatementModel {
    private String Balance;
    private String Credit;
    public String Date;
    private String Debit;
    private String Desc;
    public int color;

    public int getColor() {
        return this.color;
    }

    public void setColor(int i) {
        this.color = i;
    }

    public String getDate() {
        return this.Date;
    }

    public void setDate(String str) {
        this.Date = str;
    }

    public String getDesc() {
        return this.Desc;
    }

    public void setDesc(String str) {
        this.Desc = str;
    }

    public String getDebit() {
        return this.Debit;
    }

    public void setDebit(String str) {
        this.Debit = str;
    }

    public String getCredit() {
        return this.Credit;
    }

    public void setCredit(String str) {
        this.Credit = str;
    }

    public String getBalance() {
        return this.Balance;
    }

    public void setBalance(String str) {
        this.Balance = str;
    }

    public void AccountStatementModel(String str, String str2, String str3, String str4, String str5, int i) {
        this.Date = str;
        this.Desc = str2;
        this.Debit = str3;
        this.color = i;
        this.Credit = str4;
        this.Balance = str5;
    }
}
