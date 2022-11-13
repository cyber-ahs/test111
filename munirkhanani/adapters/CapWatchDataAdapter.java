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
public class CapWatchDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<CapWatchDataModel> capwatchArrayList;
    private Context context;
    private LayoutInflater inflater;

    public CapWatchDataAdapter(Context context, List<CapWatchDataModel> list) {
        this.context = context;
        this.capwatchArrayList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cap_watch_list_item, viewGroup, false);
        inflate.setTag(Integer.valueOf(i));
        return new RecyclerViewHoldercapwatch(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        try {
            CapWatchDataModel capWatchDataModel = this.capwatchArrayList.get(i);
            if (capWatchDataModel != null) {
                int type = capWatchDataModel.getType();
                if (type == 1) {
                    ((RecyclerViewHoldercapwatch) viewHolder).lowsymbol.setText(capWatchDataModel.getSymbol());
                    ((RecyclerViewHoldercapwatch) viewHolder).lowsymbolvolume.setText(capWatchDataModel.getSymbolBidTotalVolume());
                    ((RecyclerViewHoldercapwatch) viewHolder).lowsymbolprice.setText(capWatchDataModel.getSymbolBidPrice());
                    ((RecyclerViewHoldercapwatch) viewHolder).lowsymboltotalvalue.setText(capWatchDataModel.getSymbolBidVolume());
                    ((RecyclerViewHoldercapwatch) viewHolder).lowsymbol.setTextColor(this.context.getResources().getColor(R.color.positive_text_color));
                    ((RecyclerViewHoldercapwatch) viewHolder).lowsymbolvolume.setTextColor(this.context.getResources().getColor(R.color.dark_blue_01));
                    ((RecyclerViewHoldercapwatch) viewHolder).lowsymbolprice.setTextColor(this.context.getResources().getColor(R.color.positive_text_color));
                    ((RecyclerViewHoldercapwatch) viewHolder).lowsymboltotalvalue.setTextColor(this.context.getResources().getColor(R.color.positive_text_color));
                } else if (type == 2) {
                    ((RecyclerViewHoldercapwatch) viewHolder).lowsymbol.setText(capWatchDataModel.getLowSymbol());
                    ((RecyclerViewHoldercapwatch) viewHolder).lowsymbolvolume.setText(capWatchDataModel.getLowSymbolTotalVolume());
                    ((RecyclerViewHoldercapwatch) viewHolder).lowsymbolprice.setText(capWatchDataModel.getLowSymbolPrice());
                    ((RecyclerViewHoldercapwatch) viewHolder).lowsymboltotalvalue.setText(capWatchDataModel.getLowSymbolVolume());
                    ((RecyclerViewHoldercapwatch) viewHolder).lowsymbol.setTextColor(this.context.getResources().getColor(R.color.negative_text_color));
                    ((RecyclerViewHoldercapwatch) viewHolder).lowsymbolvolume.setTextColor(this.context.getResources().getColor(R.color.dark_blue_01));
                    ((RecyclerViewHoldercapwatch) viewHolder).lowsymbolprice.setTextColor(this.context.getResources().getColor(R.color.negative_text_color));
                    ((RecyclerViewHoldercapwatch) viewHolder).lowsymboltotalvalue.setTextColor(this.context.getResources().getColor(R.color.negative_text_color));
                }
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
    public class RecyclerViewHoldercapwatch extends RecyclerView.ViewHolder {
        TextView lowsymbol;
        TextView lowsymbolprice;
        TextView lowsymboltotalvalue;
        TextView lowsymbolvolume;

        public RecyclerViewHoldercapwatch(View view) {
            super(view);
            this.lowsymbol = (TextView) view.findViewById(R.id.tv_symbolName);
            this.lowsymbolvolume = (TextView) view.findViewById(R.id.tv_tradeVolume);
            this.lowsymbolprice = (TextView) view.findViewById(R.id.tv_price);
            this.lowsymboltotalvalue = (TextView) view.findViewById(R.id.tv_askVolume);
        }
    }
}
