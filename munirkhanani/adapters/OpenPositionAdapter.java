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
import munirkhanani.helpers.Constants;
import munirkhanani.model.OpenPositionModel;
/* loaded from: classes2.dex */
public class OpenPositionAdapter extends RecyclerView.Adapter<MyMarket> {
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener listener;
    private OpenPositionModel openPositionModel;
    private List<OpenPositionModel> openPositionModelArrayList;

    /* loaded from: classes2.dex */
    public interface OnItemClickListener {
        void onItemClick(OpenPositionModel openPositionModel, String str);
    }

    public OpenPositionAdapter(Context context, List<OpenPositionModel> list, OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
        this.context = context;
        this.openPositionModelArrayList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.open_position_list_item, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, final int i) {
        try {
            this.openPositionModel = this.openPositionModelArrayList.get(i);
            myMarket.openSymbol.setText(this.openPositionModel.getSymbol());
            myMarket.sybName.setText(this.openPositionModel.getSymbolName());
            myMarket.totalMtm.setText(this.openPositionModel.getTotalMtm());
            if (!this.openPositionModel.getVolume().equals("0") && !this.openPositionModel.getValue().equals("0.00") && !this.openPositionModel.getAvgPrice().equals("0.00")) {
                myMarket.open_row.setVisibility(0);
                myMarket.openPosition.setText(this.openPositionModel.getPosition());
                myMarket.openVolume.setText(this.openPositionModel.getVolume());
                setTextColor(this.openPositionModel.getVolume(), myMarket.openVolume, R.color.colorPrimary, R.color.red);
                myMarket.openAvgPrice.setText(this.openPositionModel.getAvgPrice());
                myMarket.openValue.setText(this.openPositionModel.getValue());
                setTextColor(this.openPositionModel.getVolume(), myMarket.openAvgPrice, R.color.colorPrimary, R.color.red);
                setTextColor(this.openPositionModel.getVolume(), myMarket.openValue, R.color.colorPrimary, R.color.red);
                myMarket.openMTM.setText(this.openPositionModel.getMtm());
            } else {
                myMarket.open_row.setVisibility(8);
            }
            if (!this.openPositionModel.getSessionVolume().equals("0") && !this.openPositionModel.getSessionValue().equals("0.00") && !this.openPositionModel.getSessionAvgPrice().equals("0.00")) {
                myMarket.pending_row.setVisibility(0);
                myMarket.sessionPosition.setText(this.openPositionModel.getSessionPosition());
                myMarket.sessionVolume.setText(this.openPositionModel.getSessionVolume());
                setTextColor(this.openPositionModel.getSessionVolume(), myMarket.sessionVolume, R.color.colorPrimary, R.color.red);
                myMarket.sessionAvgPrice.setText(this.openPositionModel.getSessionAvgPrice());
                myMarket.sessionValue.setText(this.openPositionModel.getSessionValue());
                setTextColor(this.openPositionModel.getSessionVolume(), myMarket.sessionAvgPrice, R.color.colorPrimary, R.color.red);
                setTextColor(this.openPositionModel.getSessionVolume(), myMarket.sessionValue, R.color.colorPrimary, R.color.red);
                myMarket.sessionMTM.setText(this.openPositionModel.getSessionMtm());
            } else {
                myMarket.pending_row.setVisibility(8);
            }
            if (!this.openPositionModel.getExecutedVolume().equals("0") && !this.openPositionModel.getExecutedValue().equals("0.00") && !this.openPositionModel.getExecutedAvgPrice().equals("0.00")) {
                myMarket.executed_row.setVisibility(0);
                myMarket.executedPosition.setText(this.openPositionModel.getExecutedPosition());
                myMarket.executedVolume.setText(this.openPositionModel.getExecutedVolume());
                setTextColor(this.openPositionModel.getExecutedVolume(), myMarket.executedVolume, R.color.colorPrimary, R.color.red);
                myMarket.executedAvgPrice.setText(this.openPositionModel.getExecutedAvgPrice());
                myMarket.executedValue.setText(this.openPositionModel.getExecutedValue());
                setTextColor(this.openPositionModel.getExecutedVolume(), myMarket.executedAvgPrice, R.color.colorPrimary, R.color.red);
                setTextColor(this.openPositionModel.getExecutedVolume(), myMarket.executedValue, R.color.colorPrimary, R.color.red);
                myMarket.executedMTM.setText(this.openPositionModel.getExecutedMtm());
            } else {
                myMarket.executed_row.setVisibility(8);
            }
            if (!this.openPositionModel.getInventroyVolume().equals("0")) {
                myMarket.inventory_row.setVisibility(0);
                myMarket.inventroyPosition.setText(this.openPositionModel.getInventroyPosition());
                myMarket.inventroyVolume.setText(this.openPositionModel.getInventroyVolume());
                setTextColor(this.openPositionModel.getInventroyVolume(), myMarket.inventroyVolume, R.color.colorPrimary, R.color.red);
                myMarket.inventroyAvgPrice.setText(this.openPositionModel.getInventroyAvgPrice());
                myMarket.inventroyValue.setText(this.openPositionModel.getInventroyValue());
                setTextColor(this.openPositionModel.getInventroyVolume(), myMarket.inventroyAvgPrice, R.color.colorPrimary, R.color.red);
                setTextColor(this.openPositionModel.getInventroyVolume(), myMarket.inventroyValue, R.color.colorPrimary, R.color.red);
                myMarket.inventroyMTM.setText(this.openPositionModel.getInventroyMtm());
            } else {
                myMarket.inventory_row.setVisibility(8);
            }
            myMarket.inventory_row.setOnClickListener(new View.OnClickListener() { // from class: munirkhanani.adapters.OpenPositionAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OpenPositionAdapter openPositionAdapter = OpenPositionAdapter.this;
                    openPositionAdapter.openPositionModel = (OpenPositionModel) openPositionAdapter.openPositionModelArrayList.get(i);
                    Constants.accountPositionSelectedPosition = i;
                    OpenPositionAdapter.this.listener.onItemClick(OpenPositionAdapter.this.openPositionModel, "Inventory");
                }
            });
            myMarket.open_row.setOnClickListener(new View.OnClickListener() { // from class: munirkhanani.adapters.OpenPositionAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OpenPositionAdapter openPositionAdapter = OpenPositionAdapter.this;
                    openPositionAdapter.openPositionModel = (OpenPositionModel) openPositionAdapter.openPositionModelArrayList.get(i);
                    Constants.accountPositionSelectedPosition = i;
                    if (OpenPositionAdapter.this.openPositionModel.getMktCode().equals("FUT")) {
                        OpenPositionAdapter.this.listener.onItemClick(OpenPositionAdapter.this.openPositionModel, "Open_future");
                    } else {
                        OpenPositionAdapter.this.listener.onItemClick(OpenPositionAdapter.this.openPositionModel, "Open");
                    }
                }
            });
            myMarket.executed_row.setOnClickListener(new View.OnClickListener() { // from class: munirkhanani.adapters.OpenPositionAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OpenPositionAdapter openPositionAdapter = OpenPositionAdapter.this;
                    openPositionAdapter.openPositionModel = (OpenPositionModel) openPositionAdapter.openPositionModelArrayList.get(i);
                    Constants.accountPositionSelectedPosition = i;
                    OpenPositionAdapter.this.listener.onItemClick(OpenPositionAdapter.this.openPositionModel, "Executed");
                }
            });
            myMarket.pending_row.setOnClickListener(new View.OnClickListener() { // from class: munirkhanani.adapters.OpenPositionAdapter.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OpenPositionAdapter openPositionAdapter = OpenPositionAdapter.this;
                    openPositionAdapter.openPositionModel = (OpenPositionModel) openPositionAdapter.openPositionModelArrayList.get(i);
                    Constants.accountPositionSelectedPosition = i;
                    OpenPositionAdapter.this.listener.onItemClick(OpenPositionAdapter.this.openPositionModel, "Pending");
                }
            });
            myMarket.openTotalVolume.setText(this.openPositionModel.getNetPosition());
            myMarket.openMarketPrice.setText(this.openPositionModel.getMarketPrice());
        } catch (Exception unused) {
        }
    }

    private void setTextColor(String str, TextView textView, int i, int i2) {
        if (str.contains("-")) {
            textView.setTextColor(this.context.getResources().getColor(i2));
        } else {
            textView.setTextColor(this.context.getResources().getColor(i));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<OpenPositionModel> list = this.openPositionModelArrayList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* loaded from: classes2.dex */
    public class MyMarket extends RecyclerView.ViewHolder {
        TextView executedAvgPrice;
        TextView executedMTM;
        TextView executedPosition;
        TextView executedValue;
        TextView executedVolume;
        LinearLayout executed_row;
        LinearLayout inventory_row;
        TextView inventroyAvgPrice;
        TextView inventroyMTM;
        TextView inventroyPosition;
        TextView inventroyValue;
        TextView inventroyVolume;
        TextView openAvgPrice;
        TextView openMTM;
        TextView openMarketPrice;
        TextView openPosition;
        TextView openSymbol;
        TextView openTotalVolume;
        TextView openValue;
        TextView openVolume;
        LinearLayout open_row;
        LinearLayout pending_row;
        TextView sessionAvgPrice;
        TextView sessionMTM;
        TextView sessionPosition;
        TextView sessionValue;
        TextView sessionVolume;
        TextView sybName;
        TextView totalMtm;

        public MyMarket(View view) {
            super(view);
            this.openSymbol = (TextView) view.findViewById(R.id.open_symbol);
            this.sybName = (TextView) view.findViewById(R.id.sybName);
            this.open_row = (LinearLayout) view.findViewById(R.id.open_row);
            this.openPosition = (TextView) view.findViewById(R.id.open_position);
            this.openVolume = (TextView) view.findViewById(R.id.open_volume);
            this.openAvgPrice = (TextView) view.findViewById(R.id.open_avg_price);
            this.openValue = (TextView) view.findViewById(R.id.open_value);
            this.openMTM = (TextView) view.findViewById(R.id.open_mtm);
            this.totalMtm = (TextView) view.findViewById(R.id.totalMtm);
            this.inventory_row = (LinearLayout) view.findViewById(R.id.inventory_row);
            this.inventroyPosition = (TextView) view.findViewById(R.id.inventroy_position);
            this.inventroyVolume = (TextView) view.findViewById(R.id.inventroy_volume);
            this.inventroyAvgPrice = (TextView) view.findViewById(R.id.inventroy_avg_price);
            this.inventroyValue = (TextView) view.findViewById(R.id.inventroy_value);
            this.inventroyMTM = (TextView) view.findViewById(R.id.inventroy_mtm);
            this.pending_row = (LinearLayout) view.findViewById(R.id.pending_row);
            this.sessionPosition = (TextView) view.findViewById(R.id.session_position);
            this.sessionVolume = (TextView) view.findViewById(R.id.session_volume);
            this.sessionAvgPrice = (TextView) view.findViewById(R.id.session_avg_price);
            this.sessionValue = (TextView) view.findViewById(R.id.session_value);
            this.sessionMTM = (TextView) view.findViewById(R.id.session_mtm);
            this.executed_row = (LinearLayout) view.findViewById(R.id.executed_row);
            this.executedPosition = (TextView) view.findViewById(R.id.executed_position);
            this.executedVolume = (TextView) view.findViewById(R.id.executed_volume);
            this.executedAvgPrice = (TextView) view.findViewById(R.id.executed_avg_price);
            this.executedValue = (TextView) view.findViewById(R.id.executed_value);
            this.executedMTM = (TextView) view.findViewById(R.id.executed_mtm);
            this.openMarketPrice = (TextView) view.findViewById(R.id.marketPriceOpen);
            this.openTotalVolume = (TextView) view.findViewById(R.id.totalVolOpen);
        }
    }
}
