package munirkhanani.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.microlinks.Munirkhanani.R;
import java.util.List;
import munirkhanani.model.EntitlementDataModel;
/* loaded from: classes2.dex */
public class EntitlementDataAdapter extends RecyclerView.Adapter<MyMarket> {
    private Context context;
    private List<EntitlementDataModel> entitlementDataModelList;
    private LayoutInflater inflater;

    public EntitlementDataAdapter(Context context, List<EntitlementDataModel> list) {
        this.context = context;
        this.entitlementDataModelList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.entitlement_list_item, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, final int i) {
        try {
            EntitlementDataModel entitlementDataModel = this.entitlementDataModelList.get(i);
            if (i % 2 == 1) {
                myMarket.cover.setBackgroundColor(this.context.getResources().getColor(R.color.alternate));
            } else {
                myMarket.cover.setBackgroundColor(this.context.getResources().getColor(R.color.white));
            }
            myMarket.announcementDate.setText(entitlementDataModel.getAnnouncementData());
            myMarket.entitlementyear.setText(entitlementDataModel.getEntitlementyear());
            myMarket.type.setText(entitlementDataModel.getType());
            myMarket.premDisc.setText(entitlementDataModel.getPremDisc());
            myMarket.bookClosureFrom.setText(entitlementDataModel.getBookClosureFrom());
            myMarket.bookClosureTo.setText(entitlementDataModel.getBookClosureTo());
            myMarket.expDate.setText(entitlementDataModel.getExpDate());
            myMarket.Quarter.setText(entitlementDataModel.getQuarter());
            myMarket.itemView.setOnClickListener(new View.OnClickListener() { // from class: munirkhanani.adapters.EntitlementDataAdapter.1
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
        List<EntitlementDataModel> list = this.entitlementDataModelList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* loaded from: classes2.dex */
    public class MyMarket extends RecyclerView.ViewHolder {
        TextView Quarter;
        TextView announcementDate;
        TextView bookClosureFrom;
        TextView bookClosureTo;
        LinearLayout cover;
        TextView entitlementtype;
        TextView entitlementyear;
        TextView expDate;
        TextView premDisc;
        TextView type;

        public MyMarket(View view) {
            super(view);
            this.cover = (LinearLayout) view.findViewById(R.id.cover);
            this.announcementDate = (TextView) view.findViewById(R.id.announcementDate);
            this.entitlementyear = (TextView) view.findViewById(R.id.entitlementyear);
            this.premDisc = (TextView) view.findViewById(R.id.premDisc);
            this.bookClosureFrom = (TextView) view.findViewById(R.id.bookClosureFrom);
            this.bookClosureTo = (TextView) view.findViewById(R.id.bookClosureTo);
            this.expDate = (TextView) view.findViewById(R.id.expDate);
            this.Quarter = (TextView) view.findViewById(R.id.Quarter);
            this.type = (TextView) view.findViewById(R.id.type);
        }
    }
}
