package munirkhanani.model;
/* loaded from: classes2.dex */
public class AccountStatement {
    public String AccountNum;
    public String FromDate;
    public String ToDate;
    public String ledgerType;
    public String num;

    public String getLedgerType() {
        return this.ledgerType;
    }

    public void setLedgerType(String str) {
        this.ledgerType = str;
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String str) {
        this.num = str;
    }

    public String getAccountNum() {
        return this.AccountNum;
    }

    public void setAccountNum(String str) {
        this.AccountNum = str;
    }

    public String getFromDate() {
        return this.FromDate;
    }

    public void setFromDate(String str) {
        this.FromDate = str;
    }

    public String getToDate() {
        return this.ToDate;
    }

    public void setToDate(String str) {
        this.ToDate = str;
    }
}
