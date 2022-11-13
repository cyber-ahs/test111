package munirkhanani.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.microlinks.Munirkhanani.R;
import java.util.List;
import munirkhanani.model.CapWatchDataModel;
/* loaded from: classes2.dex */
public class UpperCappedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<CapWatchDataModel> capwatchArrayList;
    private Context context;
    private LayoutInflater inflater;

    public UpperCappedAdapter(Context context, List<CapWatchDataModel> list) {
        this.context = context;
        this.capwatchArrayList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 1) {
            return null;
        }
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.upper_capped_item_list, viewGroup, false);
        inflate.setTag(Integer.valueOf(i));
        return new RecyclerViewHolder(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        try {
            CapWatchDataModel capWatchDataModel = this.capwatchArrayList.get(i);
            if (capWatchDataModel != null && capWatchDataModel.getType() == 1) {
                ((RecyclerViewHolder) viewHolder).symbol.setText(capWatchDataModel.getSymbol());
                ((RecyclerViewHolder) viewHolder).symbolvolume.setText(capWatchDataModel.getSymbolBidTotalVolume());
                ((RecyclerViewHolder) viewHolder).symbolprice.setText(capWatchDataModel.getSymbolBidPrice());
                ((RecyclerViewHolder) viewHolder).symboltotalvalue.setText(capWatchDataModel.getSymbolBidVolume());
            }
        } catch (Exception unused) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<CapWatchDataModel> list = this.capwatchArrayList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        CapWatchDataModel capWatchDataModel;
        List<CapWatchDataModel> list = this.capwatchArrayList;
        if (list == null || (capWatchDataModel = list.get(i)) == null) {
            return 0;
        }
        return capWatchDataModel.getType();
    }

    /* loaded from: classes2.dex */
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView lowsymbol;
        TextView lowsymbolprice;
        TextView lowsymboltotalvalue;
        TextView lowsymbolvolume;
        TextView symbol;
        TextView symbolprice;
        TextView symboltotalvalue;
        TextView symbolvolume;

        public RecyclerViewHolder(View view) {
            super(view);
            this.symbol = (TextView) view.findViewById(R.id.symbol);
            this.symbolvolume = (TextView) view.findViewById(R.id.symbolvolume);
            this.symbolprice = (TextView) view.findViewById(R.id.symbolprice);
            this.symboltotalvalue = (TextView) view.findViewById(R.id.symboltotalvalue);
        }
    }
}
