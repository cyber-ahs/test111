package munirkhanani.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.microlinks.Munirkhanani.R;
import java.util.List;
import munirkhanani.model.ViewDetailsExecuted_Pending_Model;
/* loaded from: classes2.dex */
public class ViewDetailsPendingExecutingAdapter extends RecyclerView.Adapter<MyMarket> {
    private Context context;
    Boolean isBuyer;
    ViewDetailsExecuted_Pending_Model viewSummaryModel;
    List<ViewDetailsExecuted_Pending_Model> viewSummaryModelList;

    public ViewDetailsPendingExecutingAdapter(Context context, List<ViewDetailsExecuted_Pending_Model> list, Boolean bool) {
        Boolean.valueOf(false);
        this.context = context;
        this.viewSummaryModelList = list;
        this.isBuyer = bool;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.view_details_item_list, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, int i) {
        String str = "";
        try {
            this.viewSummaryModel = this.viewSummaryModelList.get(i);
            Log.d("positoin_check", i + "");
            if (this.viewSummaryModel.getScreenName().equals("Pending")) {
                if (i % 2 == 1) {
                    myMarket.card_view.setBackgroundColor(this.context.getResources().getColor(R.color.bg_executedRow));
                } else {
                    myMarket.card_view.setBackgroundColor(this.context.getResources().getColor(R.color.bg_executed_light_Row));
                }
            } else if (i % 2 == 1) {
                myMarket.card_view.setBackgroundColor(this.context.getResources().getColor(R.color.bg_pendingRow));
            } else {
                myMarket.card_view.setBackgroundColor(this.context.getResources().getColor(R.color.bg_pending_light_Row));
            }
            if (this.isBuyer.booleanValue()) {
                myMarket.avgPriceTxt.setTextColor(ContextCompat.getColor(this.context, R.color.colorPrimary));
                myMarket.voltxt.setTextColor(ContextCompat.getColor(this.context, R.color.colorPrimary));
                myMarket.amountTxt.setTextColor(ContextCompat.getColor(this.context, R.color.colorPrimary));
            } else {
                myMarket.avgPriceTxt.setTextColor(ContextCompat.getColor(this.context, R.color.red));
                myMarket.voltxt.setTextColor(ContextCompat.getColor(this.context, R.color.red));
                myMarket.amountTxt.setTextColor(ContextCompat.getColor(this.context, R.color.red));
            }
            myMarket.avgPriceTxt.setText(this.viewSummaryModel.getBuyAvgPrice().equals("null") ? "" : this.viewSummaryModel.getBuyAvgPrice());
            myMarket.voltxt.setText(this.viewSummaryModel.getBuyVolume().equals("null") ? "" : this.viewSummaryModel.getBuyVolume());
            TextView textView = myMarket.amountTxt;
            if (!this.viewSummaryModel.getBuyAmount().equals("null")) {
                str = this.viewSummaryModel.getBuyAmount();
            }
            textView.setText(str);
        } catch (Exception unused) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.viewSummaryModelList.size();
    }

    /* loaded from: classes2.dex */
    public class MyMarket extends RecyclerView.ViewHolder {
        TextView amountTxt;
        TextView avgPriceTxt;
        LinearLayout card_view;
        TextView dateTxt;
        TextView voltxt;

        public MyMarket(View view) {
            super(view);
            this.card_view = (LinearLayout) view.findViewById(R.id.card_view);
            this.voltxt = (TextView) view.findViewById(R.id.volTxt);
            this.amountTxt = (TextView) view.findViewById(R.id.amountTxt);
            this.avgPriceTxt = (TextView) view.findViewById(R.id.avgPriTxt);
            TextView textView = (TextView) view.findViewById(R.id.dateTxt);
            this.dateTxt = textView;
            textView.setVisibility(8);
        }
    }
}
