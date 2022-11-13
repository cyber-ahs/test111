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
import munirkhanani.model.ViewAssetsPositionCollateralsModel;
/* loaded from: classes2.dex */
public class ViewAssetsPositionCollateralsAdapter extends RecyclerView.Adapter<MyMarket> {
    private Context context;
    ViewAssetsPositionCollateralsModel viewSummaryModel;
    List<ViewAssetsPositionCollateralsModel> viewSummaryModelList;

    public ViewAssetsPositionCollateralsAdapter(Context context, List<ViewAssetsPositionCollateralsModel> list) {
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
            myMarket.ledbalan.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_red));
            myMarket.invenMarkval.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray_2));
            myMarket.invanSold.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_red));
            myMarket.heldAmt.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray_2));
            myMarket.ledbalan.setText(this.viewSummaryModel.getLedgerBalance());
            myMarket.invenMarkval.setText(this.viewSummaryModel.getInventoryMarketValue());
            myMarket.invanSold.setText(this.viewSummaryModel.getInventorySold());
            myMarket.invanSold.setTextColor(Color.parseColor(this.viewSummaryModel.getExcessShorColor()));
            myMarket.heldAmt.setText("");
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
        TextView heldAmt;
        TextView invanSold;
        TextView invenMarkval;
        TextView ledbalan;

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
