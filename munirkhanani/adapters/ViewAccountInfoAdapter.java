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
import munirkhanani.model.ViewAccountInfoModel;
/* loaded from: classes2.dex */
public class ViewAccountInfoAdapter extends RecyclerView.Adapter<MyMarket> {
    private Context context;
    ViewAccountInfoModel viewSummaryModel;
    List<ViewAccountInfoModel> viewSummaryModelList;

    public ViewAccountInfoAdapter(Context context, List<ViewAccountInfoModel> list) {
        this.context = context;
        this.viewSummaryModelList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.account_info_list_item, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, int i) {
        try {
            this.viewSummaryModel = this.viewSummaryModelList.get(i);
            String str = "";
            myMarket.cdc.setText(this.viewSummaryModel.getCdc().equals("null") ? "" : this.viewSummaryModel.getCdc());
            myMarket.cnic.setText(this.viewSummaryModel.getCnic().equals("null") ? "" : this.viewSummaryModel.getCnic());
            if (!this.viewSummaryModel.getLandline().equals("null")) {
                myMarket.landLine.setText(this.viewSummaryModel.getLandline());
            }
            myMarket.mobileNo.setText(this.viewSummaryModel.getMobileNo().equals("null") ? "" : this.viewSummaryModel.getMobileNo());
            myMarket.email.setText(this.viewSummaryModel.getEmail().equals("null") ? "" : this.viewSummaryModel.getEmail());
            myMarket.dealerName.setText(this.viewSummaryModel.getDealerName().equals("null") ? "" : this.viewSummaryModel.getDealerName());
            TextView textView = myMarket.clientName;
            if (!this.viewSummaryModel.getClientName().equals("null")) {
                str = this.viewSummaryModel.getClientName();
            }
            textView.setText(str);
        } catch (Exception e) {
            Log.i("View AccountInfo", e.getMessage());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.viewSummaryModelList.size();
    }

    /* loaded from: classes2.dex */
    public class MyMarket extends RecyclerView.ViewHolder {
        LinearLayout card_view;
        TextView cdc;
        TextView clientName;
        TextView cnic;
        TextView dealerName;
        TextView email;
        TextView landLine;
        TextView mobileNo;

        public MyMarket(View view) {
            super(view);
            this.card_view = (LinearLayout) view.findViewById(R.id.cardView);
            this.clientName = (TextView) view.findViewById(R.id.AccountInfoClientName);
            this.dealerName = (TextView) view.findViewById(R.id.AccountInfoDealerName);
            this.email = (TextView) view.findViewById(R.id.AccountInfoEmail);
            this.cdc = (TextView) view.findViewById(R.id.AccountInfoCdc);
            this.cnic = (TextView) view.findViewById(R.id.AccountInfoCnic);
            this.landLine = (TextView) view.findViewById(R.id.AccountInfoLandLine);
            this.mobileNo = (TextView) view.findViewById(R.id.AccountInfoMobileNo);
        }
    }
}
