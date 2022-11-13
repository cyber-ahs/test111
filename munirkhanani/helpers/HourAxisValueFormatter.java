package munirkhanani.helpers;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/* loaded from: classes2.dex */
public class HourAxisValueFormatter implements AxisValueFormatter {
    private DateFormat mDataFormat = new SimpleDateFormat("hh:mm");
    private Date mDate = new Date();
    private long referenceTimestamp;

    @Override // com.github.mikephil.charting.formatter.AxisValueFormatter
    public int getDecimalDigits() {
        return 0;
    }

    public HourAxisValueFormatter(long j) {
        this.referenceTimestamp = j;
    }

    @Override // com.github.mikephil.charting.formatter.AxisValueFormatter
    public String getFormattedValue(float f, AxisBase axisBase) {
        return getHour(this.referenceTimestamp + f);
    }

    private String getHour(long j) {
        try {
            this.mDate.setTime(j);
            return this.mDataFormat.format(this.mDate);
        } catch (Exception unused) {
            return "xx";
        }
    }
}
