package munirkhanani.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.microlinks.Munirkhanani.R;
import java.util.List;
import munirkhanani.model.ViewAssetsPositionCashModel;
/* loaded from: classes2.dex */
public class ViewAssetsPositionCashAdapter extends RecyclerView.Adapter<MyMarket> {
    private Context context;
    ViewAssetsPositionCashModel viewSummaryModel;
    List<ViewAssetsPositionCashModel> viewSummaryModelList;

    public ViewAssetsPositionCashAdapter(Context context, List<ViewAssetsPositionCashModel> list) {
        this.context = context;
        this.viewSummaryModelList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.view_asstes_position_list_item, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, int i) {
        try {
            this.viewSummaryModel = this.viewSummaryModelList.get(i);
            myMarket.ledbalan.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_blue));
            myMarket.invenMarkval.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray));
            myMarket.invanSold.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_blue));
            myMarket.heldAmt.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray));
            myMarket.ledbalan.setText(this.viewSummaryModel.getLedgerBalance());
            myMarket.invenMarkval.setText(this.viewSummaryModel.getInventoryMarketValue());
            myMarket.invanSold.setText(this.viewSummaryModel.getInventorySold());
            myMarket.heldAmt.setText(this.viewSummaryModel.getHeldAmt());
            myMarket.heldAmt.setTextColor(Color.parseColor(this.viewSummaryModel.getExcessShorColor()));
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
        }
    }
}
