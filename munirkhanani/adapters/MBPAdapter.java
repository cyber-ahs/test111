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
import munirkhanani.model.MBP_MBO_Model;
/* loaded from: classes2.dex */
public class MBPAdapter extends RecyclerView.Adapter<MyMarket> {
    private Context context;
    private LayoutInflater inflater;
    MBP_MBO_Model marketData;
    ArrayList<MBP_MBO_Model> mbp_mbo_modelArrayList;

    public MBPAdapter(Context context, ArrayList<MBP_MBO_Model> arrayList) {
        this.context = context;
        this.mbp_mbo_modelArrayList = arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.mbp_mbo_list_item, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, final int i) {
        try {
            this.marketData = this.mbp_mbo_modelArrayList.get(i);
            myMarket.buyValue.setText(this.marketData.getBuy());
            myMarket.buyVolume.setText(this.marketData.getBuyvolume());
            myMarket.sellValue.setText(this.marketData.getSell());
            myMarket.sellVolume.setText(this.marketData.getSellvolume());
            Log.i("Data Index", "" + i);
            myMarket.itemView.setOnClickListener(new View.OnClickListener() { // from class: munirkhanani.adapters.MBPAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    int i2 = i;
                    Log.i("Data Index", "" + i2);
                }
            });
        } catch (Exception unused) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<MBP_MBO_Model> arrayList = this.mbp_mbo_modelArrayList;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    /* loaded from: classes2.dex */
    public class MyMarket extends RecyclerView.ViewHolder {
        TextView buyValue;
        TextView buyVolume;
        TextView sellValue;
        TextView sellVolume;

        public MyMarket(View view) {
            super(view);
            this.buyValue = (TextView) view.findViewById(R.id.buyValue);
            this.buyVolume = (TextView) view.findViewById(R.id.buyVolume);
            this.sellValue = (TextView) view.findViewById(R.id.sellValue);
            this.sellVolume = (TextView) view.findViewById(R.id.sellVolume);
        }
    }
}
