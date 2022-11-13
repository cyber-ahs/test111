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
import munirkhanani.model.MBO_Buy_Model;
/* loaded from: classes2.dex */
public class MBOBuyAdapter extends RecyclerView.Adapter<MyMarket> {
    private Context context;
    private LayoutInflater inflater;
    MBO_Buy_Model marketData;
    ArrayList<MBO_Buy_Model> mbp_mbo_modelArrayList;

    public MBOBuyAdapter(Context context, ArrayList<MBO_Buy_Model> arrayList) {
        this.context = context;
        this.mbp_mbo_modelArrayList = arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.mbp_mbo_list_item_buy, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, int i) {
        try {
            this.marketData = this.mbp_mbo_modelArrayList.get(i);
            myMarket.buyValue.setText(this.marketData.getBuy());
            myMarket.buyVolume.setText(this.marketData.getBuyvolume());
            Log.i("Data Index", "" + i);
        } catch (Exception unused) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<MBO_Buy_Model> arrayList = this.mbp_mbo_modelArrayList;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    /* loaded from: classes2.dex */
    public class MyMarket extends RecyclerView.ViewHolder {
        TextView buyValue;
        TextView buyVolume;

        public MyMarket(View view) {
            super(view);
            this.buyValue = (TextView) view.findViewById(R.id.buyValue);
            this.buyVolume = (TextView) view.findViewById(R.id.buyVolume);
        }
    }
}
