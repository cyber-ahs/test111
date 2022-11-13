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
import munirkhanani.model.ViewDetailsOpenModel;
/* loaded from: classes2.dex */
public class ViewDetailsOpenAdapter extends RecyclerView.Adapter<MyMarket> {
    private Context context;
    ViewDetailsOpenModel viewSummaryModel;
    List<ViewDetailsOpenModel> viewSummaryModelList;

    public ViewDetailsOpenAdapter(Context context, List<ViewDetailsOpenModel> list) {
        this.context = context;
        this.viewSummaryModelList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.view_details_open_items_list, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, int i) {
        try {
            this.viewSummaryModel = this.viewSummaryModelList.get(i);
            if (i % 2 == 1) {
                myMarket.card_view.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_red));
            } else {
                myMarket.card_view.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray_2));
            }
            String str = "";
            myMarket.tv_quanity.setText(this.viewSummaryModel.getQuanity().equals("null") ? "" : this.viewSummaryModel.getQuanity());
            myMarket.tv_avg_price.setText(this.viewSummaryModel.getAvg_price().equals("null") ? "" : this.viewSummaryModel.getAvg_price());
            myMarket.tv_days.setText(this.viewSummaryModel.getDays().equals("null") ? "" : this.viewSummaryModel.getDays());
            myMarket.tv_prem_price.setText(this.viewSummaryModel.getPrem_price().equals("null") ? "" : this.viewSummaryModel.getPrem_price());
            myMarket.tv_markup_amount.setText(this.viewSummaryModel.getMarkup_amount().equals("null") ? "" : this.viewSummaryModel.getMarkup_amount());
            myMarket.tv_release_amount.setText(this.viewSummaryModel.getRelease_amount().equals("null") ? "" : this.viewSummaryModel.getRelease_amount());
            myMarket.tv_mtm.setText(this.viewSummaryModel.getMtm().equals("null") ? "" : this.viewSummaryModel.getMtm());
            myMarket.tv_invest_amount.setText(this.viewSummaryModel.getInvest_amount().equals("null") ? "" : this.viewSummaryModel.getInvest_amount());
            myMarket.tv_open_price.setText(this.viewSummaryModel.getOpen_price().equals("null") ? "" : this.viewSummaryModel.getOpen_price());
            myMarket.tv_initial_date.setText(this.viewSummaryModel.getInitial_date().equals("null") ? "" : this.viewSummaryModel.getInitial_date());
            myMarket.tv_premium.setText(this.viewSummaryModel.getPremium().equals("null") ? "" : this.viewSummaryModel.getPremium());
            TextView textView = myMarket.tv_market;
            if (!this.viewSummaryModel.getMarket().equals("null")) {
                str = this.viewSummaryModel.getMarket();
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
        LinearLayout card_view;
        TextView tv_avg_price;
        TextView tv_days;
        TextView tv_initial_date;
        TextView tv_invest_amount;
        TextView tv_market;
        TextView tv_markup_amount;
        TextView tv_mtm;
        TextView tv_open_price;
        TextView tv_prem_price;
        TextView tv_premium;
        TextView tv_quanity;
        TextView tv_release_amount;

        public MyMarket(View view) {
            super(view);
            this.card_view = (LinearLayout) view.findViewById(R.id.card_view);
            this.tv_quanity = (TextView) view.findViewById(R.id.tv_quanity);
            this.tv_avg_price = (TextView) view.findViewById(R.id.tv_avg_price);
            this.tv_days = (TextView) view.findViewById(R.id.tv_days);
            this.tv_prem_price = (TextView) view.findViewById(R.id.tv_prem_price);
            this.tv_markup_amount = (TextView) view.findViewById(R.id.tv_markup_amount);
            this.tv_release_amount = (TextView) view.findViewById(R.id.tv_release_amount);
            this.tv_invest_amount = (TextView) view.findViewById(R.id.tv_invest_amount);
            this.tv_mtm = (TextView) view.findViewById(R.id.tv_mtm);
            this.tv_open_price = (TextView) view.findViewById(R.id.tv_open_price);
            this.tv_initial_date = (TextView) view.findViewById(R.id.tv_initial_date);
            this.tv_premium = (TextView) view.findViewById(R.id.tv_premium);
            this.tv_market = (TextView) view.findViewById(R.id.tv_market);
        }
    }
}
