package munirkhanani.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.microlinks.Munirkhanani.R;
import java.util.List;
import munirkhanani.model.ViewSummaryModelThree;
/* loaded from: classes2.dex */
public class ViewSummaryThreeAdapter extends RecyclerView.Adapter<MyMarket> {
    private Context context;
    ViewSummaryModelThree viewSummaryModel;
    List<ViewSummaryModelThree> viewSummaryModelList;

    public ViewSummaryThreeAdapter(Context context, List<ViewSummaryModelThree> list) {
        this.context = context;
        this.viewSummaryModelList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.view_summary_three_list_item, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, int i) {
        try {
            this.viewSummaryModel = this.viewSummaryModelList.get(i);
            myMarket.ledbalan.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_dark_gray));
            myMarket.invenMarkval.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray_3));
            myMarket.invanSold.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_dark_gray));
            myMarket.heldAmt.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray_3));
            myMarket.realizedAmt.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_dark_gray));
            myMarket.expanseAmount.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray_3));
            myMarket.netWorth.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_dark_gray));
            myMarket.expanseAmount.setText("");
            myMarket.ledbalan.setText(this.viewSummaryModel.getLedgerBalance());
            myMarket.invenMarkval.setText(this.viewSummaryModel.getInventoryMarketValue());
            myMarket.invanSold.setText(this.viewSummaryModel.getInventorySold());
            myMarket.heldAmt.setText(this.viewSummaryModel.getHeldAmt());
            myMarket.realizedAmt.setText(this.viewSummaryModel.getRealizedAmt());
            myMarket.netWorth.setText(this.viewSummaryModel.getNetWorth());
        } catch (Exception e) {
            Log.i("ViewSummaryThreeAdapter", e.getMessage());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.viewSummaryModelList.size();
    }

    /* loaded from: classes2.dex */
    public class MyMarket extends RecyclerView.ViewHolder {
        LinearLayout card_view;
        TextView expanseAmount;
        TextView heldAmt;
        TextView invanSold;
        TextView invenMarkval;
        TextView ledbalan;
        TextView netWorth;
        TextView realizedAmt;

        public MyMarket(View view) {
            super(view);
            this.card_view = (LinearLayout) view.findViewById(R.id.cardView);
            this.ledbalan = (TextView) view.findViewById(R.id.ledbalan);
            this.invenMarkval = (TextView) view.findViewById(R.id.invenMarkval);
            this.invanSold = (TextView) view.findViewById(R.id.invanSold);
            this.heldAmt = (TextView) view.findViewById(R.id.heldAmt);
            this.realizedAmt = (TextView) view.findViewById(R.id.realizedAmt);
            this.expanseAmount = (TextView) view.findViewById(R.id.expanseAmount);
            this.netWorth = (TextView) view.findViewById(R.id.netWorth);
        }
    }
}
