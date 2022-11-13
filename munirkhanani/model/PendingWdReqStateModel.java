package munirkhanani.model;

import java.io.Serializable;
/* loaded from: classes2.dex */
public class PendingWdReqStateModel implements Serializable {
    private String amountAppr;
    private String amountReq;
    private String comments;
    private String date;
    private String method;
    private String serial_number;
    private String status;

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getAmountReq() {
        return this.amountReq;
    }

    public void setAmountReq(String str) {
        this.amountReq = str;
    }

    public String getAmountAppr() {
        return this.amountAppr;
    }

    public void setAmountAppr(String str) {
        this.amountAppr = str;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String str) {
        this.comments = str;
    }

    public String getSerial_number() {
        return this.serial_number;
    }

    public void setSerial_number(String str) {
        this.serial_number = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
