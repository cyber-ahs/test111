package munirkhanani.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.microlinks.Munirkhanani.R;
import java.util.List;
import munirkhanani.model.MBO_Sell_Model;
/* loaded from: classes2.dex */
public class MBOSellAdapter extends RecyclerView.Adapter<MyMarket> {
    private Context context;
    private LayoutInflater inflater;
    MBO_Sell_Model marketData;
    List<MBO_Sell_Model> mbp_mbo_modelArrayList;

    public MBOSellAdapter(Context context, List<MBO_Sell_Model> list) {
        this.context = context;
        this.mbp_mbo_modelArrayList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.mbo_list_item_sell, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, int i) {
        try {
            this.marketData = this.mbp_mbo_modelArrayList.get(i);
            myMarket.sellValue.setText(this.marketData.getSell());
            myMarket.sellVolume.setText(this.marketData.getSellvolume());
            Log.i("Data Index", "" + i);
        } catch (Exception e) {
            Log.d("crash_report", e.toString());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MBO_Sell_Model> list = this.mbp_mbo_modelArrayList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* loaded from: classes2.dex */
    public class MyMarket extends RecyclerView.ViewHolder {
        TextView sellValue;
        TextView sellVolume;

        public MyMarket(View view) {
            super(view);
            this.sellValue = (TextView) view.findViewById(R.id.sellValue);
            this.sellVolume = (TextView) view.findViewById(R.id.sellVolume);
        }
    }
}
