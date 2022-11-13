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
public class LowerLockedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<CapWatchDataModel> capwatchArrayList;
    private Context context;
    private LayoutInflater inflater;

    public LowerLockedAdapter(Context context, List<CapWatchDataModel> list) {
        this.context = context;
        this.capwatchArrayList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 2) {
            return null;
        }
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lower_locked_item_list, viewGroup, false);
        inflate.setTag(Integer.valueOf(i));
        return new RecyclerViewHolderLower(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        try {
            CapWatchDataModel capWatchDataModel = this.capwatchArrayList.get(i);
            if (capWatchDataModel != null && capWatchDataModel.getType() == 2) {
                ((RecyclerViewHolderLower) viewHolder).lowsymbol.setText(capWatchDataModel.getLowSymbol());
                ((RecyclerViewHolderLower) viewHolder).lowsymbolvolume.setText(capWatchDataModel.getLowSymbolTotalVolume());
                ((RecyclerViewHolderLower) viewHolder).lowsymbolprice.setText(capWatchDataModel.getLowSymbolPrice());
                ((RecyclerViewHolderLower) viewHolder).lowsymboltotalvalue.setText(capWatchDataModel.getLowSymbolVolume());
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
    public class RecyclerViewHolderLower extends RecyclerView.ViewHolder {
        TextView lowsymbol;
        TextView lowsymbolprice;
        TextView lowsymboltotalvalue;
        TextView lowsymbolvolume;

        public RecyclerViewHolderLower(View view) {
            super(view);
            this.lowsymbol = (TextView) view.findViewById(R.id.lowsymbol);
            this.lowsymbolvolume = (TextView) view.findViewById(R.id.lowsymbolvolume);
            this.lowsymbolprice = (TextView) view.findViewById(R.id.lowsymbolprice);
            this.lowsymboltotalvalue = (TextView) view.findViewById(R.id.lowsymboltotalvalue);
        }
    }
}
