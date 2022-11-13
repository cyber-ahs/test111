package munirkhanani.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.microlinks.Munirkhanani.R;
import java.util.ArrayList;
import munirkhanani.model.MBO_Sell_Model;
/* loaded from: classes2.dex */
public class MBPSellAdapter extends RecyclerView.Adapter<MyMarket> {
    private Context context;
    private LayoutInflater inflater;
    private MBO_Sell_Model marketData;
    private ArrayList<MBO_Sell_Model> mbp_mbo_modelArrayList;

    public MBPSellAdapter(Context context, ArrayList<MBO_Sell_Model> arrayList) {
        this.context = context;
        this.mbp_mbo_modelArrayList = arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.mbp_mbo_list_item_sell, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, int i) {
        try {
            this.marketData = this.mbp_mbo_modelArrayList.get(i);
            myMarket.sellValue.setText(this.marketData.getSell());
            myMarket.sellVolume.setText(this.marketData.getSellvolume());
            Log.i("Data Index", "" + i);
        } catch (Exception unused) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<MBO_Sell_Model> arrayList = this.mbp_mbo_modelArrayList;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
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
