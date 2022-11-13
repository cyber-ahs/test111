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
import munirkhanani.model.TradeLog;
/* loaded from: classes2.dex */
public class TradeLogAdapter extends RecyclerView.Adapter<MyTradeLog> {
    private Context context;
    private LayoutInflater inflater;
    private TradeLog tradeLog;
    private ArrayList<TradeLog> tradeLogArrayList;

    public TradeLogAdapter(Context context, ArrayList<TradeLog> arrayList) {
        this.context = context;
        this.tradeLogArrayList = arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyTradeLog onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyTradeLog(layoutInflater != null ? layoutInflater.inflate(R.layout.trade_list_item, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyTradeLog myTradeLog, int i) {
        try {
            TradeLog tradeLog = this.tradeLogArrayList.get(i);
            this.tradeLog = tradeLog;
            if (tradeLog.getScrip() != null) {
                myTradeLog.scrip.setText(this.tradeLog.getScrip());
            }
            TextView textView = myTradeLog.market;
            textView.setText("\t" + this.tradeLog.getMarket() + "-" + this.tradeLog.getOrderType());
            TextView textView2 = myTradeLog.orderid;
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(this.tradeLog.getOrderid());
            textView2.setText(sb.toString());
            myTradeLog.card_view.setCardBackgroundColor(this.tradeLog.getMarketBackground());
            myTradeLog.date.setText(this.tradeLog.getDate());
            myTradeLog.time.setText(this.tradeLog.getTime());
            myTradeLog.clientid.setText(this.tradeLog.getClientid());
            TextView textView3 = myTradeLog.volume;
            textView3.setText(Constants.GetVolumeFormattedValue(this.tradeLog.getVolume()) + " @ " + Constants.GetPriceFormattedValue(this.tradeLog.getPrice()));
            myTradeLog.userName.setText(this.tradeLog.getUserName());
            myTradeLog.volume.setTextColor(this.tradeLog.getMarketTextColor());
            myTradeLog.market.setTextColor(this.tradeLog.getMarketTextColor());
            myTradeLog.orderid.setTextColor(this.tradeLog.getMarketTextColor());
            myTradeLog.scrip.setTextColor(this.tradeLog.getMarketTextColor());
            myTradeLog.userName.setTextColor(this.tradeLog.getMarketTextColor());
            myTradeLog.clientid.setTextColor(this.tradeLog.getMarketTextColor());
            myTradeLog.date.setTextColor(this.tradeLog.getMarketTextColor());
            myTradeLog.time.setTextColor(this.tradeLog.getMarketTextColor());
        } catch (Exception e) {
            Log.i("tradeLog", e.getMessage());
        }
    }

    public TradeLog getItem(int i) {
        return this.tradeLogArrayList.get(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<TradeLog> arrayList = this.tradeLogArrayList;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class MyTradeLog extends RecyclerView.ViewHolder {
        CardView card_view;
        TextView clientid;
        TextView date;
        ImageView dateIcon;
        TextView market;
        TextView orderid;
        TextView price;
        TextView scrip;
        TextView time;
        ImageView timeIcon;
        TextView userName;
        TextView volume;

        MyTradeLog(View view) {
            super(view);
            this.card_view = (CardView) view.findViewById(R.id.card_view);
            this.dateIcon = (ImageView) view.findViewById(R.id.tradeDateIcon);
            this.timeIcon = (ImageView) view.findViewById(R.id.tradeTimeIcon);
            this.orderid = (TextView) view.findViewById(R.id.tradeOrderId);
            this.scrip = (TextView) view.findViewById(R.id.tradeScrip);
            this.market = (TextView) view.findViewById(R.id.tradeMarket);
            this.volume = (TextView) view.findViewById(R.id.tradeVolume);
            this.date = (TextView) view.findViewById(R.id.tradeDate);
            this.time = (TextView) view.findViewById(R.id.tradeTime);
            this.clientid = (TextView) view.findViewById(R.id.clientid);
            this.price = (TextView) view.findViewById(R.id.tradePrice);
            this.userName = (TextView) view.findViewById(R.id.userName);
        }
    }
}
