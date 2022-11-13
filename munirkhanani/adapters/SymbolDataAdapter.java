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
import munirkhanani.model.SymbolDataModel;
/* loaded from: classes2.dex */
public class SymbolDataAdapter extends RecyclerView.Adapter<MyMarket> {
    private Context context;
    private LayoutInflater inflater;
    private List<SymbolDataModel> symbolDataModelList;

    public SymbolDataAdapter(Context context, List<SymbolDataModel> list) {
        this.context = context;
        this.symbolDataModelList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.entitlement_list_item2, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, final int i) {
        try {
            SymbolDataModel symbolDataModel = this.symbolDataModelList.get(i);
            if (i % 2 == 1) {
                myMarket.cover.setBackgroundColor(this.context.getResources().getColor(R.color.alternate));
            } else {
                myMarket.cover.setBackgroundColor(this.context.getResources().getColor(R.color.white));
            }
            myMarket.effective.setText(symbolDataModel.getEffective());
            myMarket.haircut.setText(symbolDataModel.getHaircut());
            myMarket.settlement.setText(symbolDataModel.getSettlementtype());
            myMarket.lotsize.setText(symbolDataModel.getLotsize());
            myMarket.varmargin.setText(symbolDataModel.getVarmargin());
            myMarket.acceptableQty.setText(symbolDataModel.getAcceptableqty());
            myMarket.facevalue.setText(symbolDataModel.getFacevalue());
            myMarket.sector.setText(symbolDataModel.getSector());
            myMarket.itemView.setOnClickListener(new View.OnClickListener() { // from class: munirkhanani.adapters.SymbolDataAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                }
            });
        } catch (Exception unused) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<SymbolDataModel> list = this.symbolDataModelList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* loaded from: classes2.dex */
    public class MyMarket extends RecyclerView.ViewHolder {
        TextView acceptableQty;
        LinearLayout cover;
        TextView effective;
        TextView facevalue;
        TextView haircut;
        TextView lotsize;
        TextView sector;
        TextView settlement;
        TextView varmargin;

        public MyMarket(View view) {
            super(view);
            this.cover = (LinearLayout) view.findViewById(R.id.cover);
            this.effective = (TextView) view.findViewById(R.id.effectivedata);
            this.haircut = (TextView) view.findViewById(R.id.haircutdata);
            this.sector = (TextView) view.findViewById(R.id.sectoradata);
            this.settlement = (TextView) view.findViewById(R.id.settlementdata);
            this.acceptableQty = (TextView) view.findViewById(R.id.acceptableQtydata);
            this.varmargin = (TextView) view.findViewById(R.id.varmargindata);
            this.facevalue = (TextView) view.findViewById(R.id.facevaluedata);
            this.lotsize = (TextView) view.findViewById(R.id.lotsizedata);
        }
    }
}
