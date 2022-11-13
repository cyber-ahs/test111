package munirkhanani.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.microlinks.Munirkhanani.R;
import java.util.ArrayList;
import munirkhanani.helpers.Constants;
import munirkhanani.model.LogSummary;
/* loaded from: classes2.dex */
public class LogSummaryAdapter extends RecyclerView.Adapter<MyLogSummary> {
    private Context context;
    private LayoutInflater inflater;
    private LogSummary logSummary;
    private ArrayList<LogSummary> logSummaryArrayList;

    public LogSummaryAdapter(Context context, ArrayList<LogSummary> arrayList) {
        this.context = context;
        this.logSummaryArrayList = arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyLogSummary onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyLogSummary(layoutInflater != null ? layoutInflater.inflate(R.layout.log_list_item, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyLogSummary myLogSummary, int i) {
        try {
            LogSummary logSummary = this.logSummaryArrayList.get(i);
            this.logSummary = logSummary;
            if (logSummary.getScrip() != null) {
                myLogSummary.scrip.setText(this.logSummary.getScrip());
            }
            myLogSummary.logSymbolName.setText(this.logSummary.getScripName());
            TextView textView = myLogSummary.market;
            textView.setText("\t" + this.logSummary.getMarket() + "-" + this.logSummary.getOrderType());
            if (this.logSummary.getTicketNo() == null) {
                TextView textView2 = myLogSummary.orderid;
                textView2.setText("" + this.logSummary.getOrderid());
            } else {
                TextView textView3 = myLogSummary.orderid;
                textView3.setText("" + this.logSummary.getOrderid() + "-" + this.logSummary.getTicketNo());
            }
            myLogSummary.card_view.setCardBackgroundColor(this.logSummary.getMarketBackground());
            myLogSummary.date.setText(this.logSummary.getDate());
            myLogSummary.time.setText(this.logSummary.getTime());
            myLogSummary.clientid.setText(this.logSummary.getClientid());
            TextView textView4 = myLogSummary.volume;
            textView4.setText(Constants.GetVolumeFormattedValue(this.logSummary.getVolume()) + " @ " + Constants.GetPriceFormattedValue(this.logSummary.getPrice()));
            myLogSummary.userName.setText(this.logSummary.getUserName());
            myLogSummary.volume.setTextColor(this.logSummary.getMarketTextColor());
            myLogSummary.market.setTextColor(this.logSummary.getMarketTextColor());
            myLogSummary.orderid.setTextColor(this.logSummary.getMarketTextColor());
            myLogSummary.scrip.setTextColor(this.logSummary.getMarketTextColor());
            myLogSummary.userName.setTextColor(this.logSummary.getMarketTextColor());
            myLogSummary.clientid.setTextColor(this.logSummary.getMarketTextColor());
            myLogSummary.date.setTextColor(this.logSummary.getMarketTextColor());
            myLogSummary.time.setTextColor(this.logSummary.getMarketTextColor());
            myLogSummary.status.setTextColor(this.logSummary.getMarketTextColor());
            myLogSummary.status.setText(this.logSummary.getStatus());
        } catch (Exception e) {
            Log.d("exc", e.getMessage());
        }
    }

    public LogSummary getItem(int i) {
        return this.logSummaryArrayList.get(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<LogSummary> arrayList = this.logSummaryArrayList;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class MyLogSummary extends RecyclerView.ViewHolder {
        TextView cancle;
        CardView card_view;
        TextView clientid;
        TextView date;
        ImageView dateIcon;
        TextView logSymbolName;
        TextView market;
        TextView orderid;
        TextView price;
        TextView scrip;
        View separator;
        TextView status;
        TextView time;
        ImageView timeIcon;
        TextView userName;
        TextView volume;

        MyLogSummary(View view) {
            super(view);
            this.clientid = (TextView) view.findViewById(R.id.clientid);
            this.card_view = (CardView) view.findViewById(R.id.card_view);
            this.dateIcon = (ImageView) view.findViewById(R.id.logDateIcon);
            this.logSymbolName = (TextView) view.findViewById(R.id.logSymbolName);
            this.timeIcon = (ImageView) view.findViewById(R.id.logTimeIcon);
            this.cancle = (TextView) view.findViewById(R.id.logCancle);
            this.orderid = (TextView) view.findViewById(R.id.logOrderId);
            this.status = (TextView) view.findViewById(R.id.statusOfActivity);
            this.userName = (TextView) view.findViewById(R.id.userName);
            this.scrip = (TextView) view.findViewById(R.id.logScrip);
            this.market = (TextView) view.findViewById(R.id.logMarket);
            this.volume = (TextView) view.findViewById(R.id.logVolume);
            this.date = (TextView) view.findViewById(R.id.logDate);
            this.time = (TextView) view.findViewById(R.id.logTime);
            this.price = (TextView) view.findViewById(R.id.logPrice);
            this.separator = view.findViewById(R.id.separator);
        }
    }
}
