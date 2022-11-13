package munirkhanani.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.microlinks.Munirkhanani.R;
import java.util.List;
import munirkhanani.model.LiveDataModel;
/* loaded from: classes2.dex */
public class LiveDataAdapter extends RecyclerView.Adapter<MyMarket> {
    private Context context;
    private LayoutInflater inflater;
    private List<LiveDataModel> liveDataModelList;

    public LiveDataAdapter(Context context, List<LiveDataModel> list) {
        this.context = context;
        this.liveDataModelList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.fragment_live_data, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, final int i) {
        try {
            LiveDataModel liveDataModel = this.liveDataModelList.get(i);
            if (i % 2 == 1) {
                myMarket.cover.setBackgroundColor(this.context.getResources().getColor(R.color.alternate));
            } else {
                myMarket.cover.setBackgroundColor(this.context.getResources().getColor(R.color.white));
            }
            myMarket.AskPrice.setText(liveDataModel.getAskPrice());
            myMarket.AskVolume.setText(liveDataModel.getAskVolume());
            myMarket.AveragePrice.setText(liveDataModel.getAveragePrice());
            myMarket.BidPrice.setText(liveDataModel.getBidPrice());
            myMarket.BidVolume.setText(liveDataModel.getBidVolume());
            myMarket.HighPrice.setText(liveDataModel.getHighPrice());
            myMarket.LastDayClosePrice.setText(liveDataModel.getLastDayClosePrice());
            myMarket.LastTradePrice.setText(liveDataModel.getLastTradePrice());
            myMarket.Lasttradetime.setText(liveDataModel.getLasttradetime());
            myMarket.LastTradeVolume.setText(liveDataModel.getLastTradeVolume());
            myMarket.LowerValue.setText(liveDataModel.getLowerValue());
            myMarket.LowPrice.setText(liveDataModel.getLowPrice());
            myMarket.NetChange.setText(liveDataModel.getNetChange());
            myMarket.OpenPrice.setText(liveDataModel.getOpenPrice());
            myMarket.TotalTradedVolume.setText(liveDataModel.getTotalTradedVolume());
            myMarket.TotalTrades.setText(liveDataModel.getTotalTrades());
            myMarket.UpperValue.setText(liveDataModel.getUpperValue());
            myMarket.itemView.setOnClickListener(new View.OnClickListener() { // from class: munirkhanani.adapters.LiveDataAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                }
            });
        } catch (Exception unused) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<LiveDataModel> list = this.liveDataModelList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* loaded from: classes2.dex */
    public class MyMarket extends RecyclerView.ViewHolder {
        TextView AskPrice;
        TextView AskVolume;
        TextView AveragePrice;
        TextView BidPrice;
        TextView BidVolume;
        TextView HighPrice;
        TextView LastDayClosePrice;
        TextView LastTradePrice;
        TextView LastTradeVolume;
        TextView Lasttradetime;
        TextView LowPrice;
        TextView LowerValue;
        TextView NetChange;
        TextView OpenPrice;
        TextView TotalTradedVolume;
        TextView TotalTrades;
        TextView UpperValue;
        LinearLayout cover;

        public MyMarket(View view) {
            super(view);
            this.cover = (LinearLayout) view.findViewById(R.id.cover);
            this.AskPrice = (TextView) view.findViewById(R.id.AskPrice);
            this.AskVolume = (TextView) view.findViewById(R.id.AskVolume);
            this.AveragePrice = (TextView) view.findViewById(R.id.AveragePrice);
            this.BidPrice = (TextView) view.findViewById(R.id.BidPrice);
            this.BidVolume = (TextView) view.findViewById(R.id.BidVolume);
            this.HighPrice = (TextView) view.findViewById(R.id.HighPrice);
            this.LastDayClosePrice = (TextView) view.findViewById(R.id.LastDayClosePrice);
            this.LastTradePrice = (TextView) view.findViewById(R.id.LastTradePrice);
            this.Lasttradetime = (TextView) view.findViewById(R.id.LastTradeTime);
            this.LastTradeVolume = (TextView) view.findViewById(R.id.LastTradeVolume);
            this.LowerValue = (TextView) view.findViewById(R.id.LowerValue);
            this.LowPrice = (TextView) view.findViewById(R.id.LowPrice);
            this.NetChange = (TextView) view.findViewById(R.id.NetChange);
            this.OpenPrice = (TextView) view.findViewById(R.id.OpenPrice);
            this.TotalTradedVolume = (TextView) view.findViewById(R.id.TotalTradedVolume);
            this.TotalTrades = (TextView) view.findViewById(R.id.TotalTrades);
            this.UpperValue = (TextView) view.findViewById(R.id.UpperValue);
        }
    }
}
