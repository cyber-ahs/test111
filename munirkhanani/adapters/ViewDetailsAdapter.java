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
import munirkhanani.model.ViewDetailsModel;
/* loaded from: classes2.dex */
public class ViewDetailsAdapter extends RecyclerView.Adapter<MyMarket> {
    private Context context;
    ViewDetailsModel viewSummaryModel;
    List<ViewDetailsModel> viewSummaryModelList;

    public ViewDetailsAdapter(Context context, List<ViewDetailsModel> list) {
        this.context = context;
        this.viewSummaryModelList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.view_details_item_list, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, int i) {
        try {
            ViewDetailsModel viewDetailsModel = this.viewSummaryModelList.get(i);
            this.viewSummaryModel = viewDetailsModel;
            if (viewDetailsModel.getScreenName().equals("Open_future")) {
                if (i % 2 == 1) {
                    myMarket.card_view.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_red));
                } else {
                    myMarket.card_view.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray_2));
                }
            } else if (i % 2 == 1) {
                myMarket.card_view.setBackgroundColor(this.context.getResources().getColor(R.color.alternate));
            } else {
                myMarket.card_view.setBackgroundColor(this.context.getResources().getColor(R.color.colorLightblue));
            }
            String str = "";
            myMarket.dateTxt.setText(this.viewSummaryModel.getDate().equals("null") ? "" : this.viewSummaryModel.getDate());
            myMarket.avgPriceTxt.setText(this.viewSummaryModel.getAvgPrice().equals("null") ? "" : this.viewSummaryModel.getAvgPrice());
            myMarket.voltxt.setText(this.viewSummaryModel.getVolume().equals("null") ? "" : this.viewSummaryModel.getVolume());
            TextView textView = myMarket.amountTxt;
            if (!this.viewSummaryModel.getAmount().equals("null")) {
                str = this.viewSummaryModel.getAmount();
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
            this.dateTxt = (TextView) view.findViewById(R.id.dateTxt);
        }
    }
}
