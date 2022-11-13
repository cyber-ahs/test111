package munirkhanani.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.microlinks.Munirkhanani.R;
import java.util.List;
import munirkhanani.model.MoversModel;
/* loaded from: classes2.dex */
public class TopMoversAdapter extends RecyclerView.Adapter<MyMarket> {
    private Context context;
    private LayoutInflater inflater;
    List<MoversModel> moversModelArrayList;
    MoversModel moversModelModel;

    public TopMoversAdapter(Context context, List<MoversModel> list) {
        this.context = context;
        this.moversModelArrayList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.top_movers_list_item, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, int i) {
        try {
            MoversModel moversModel = this.moversModelArrayList.get(i);
            this.moversModelModel = moversModel;
            if (moversModel.getByVolSymbCode().length() >= 11) {
                myMarket.byVolSymbolCode.setTextSize(2, 10.0f);
            } else if (this.moversModelModel.getByVolSymbCode().length() > 8) {
                myMarket.byVolSymbolCode.setTextSize(2, 11.0f);
            } else {
                myMarket.byVolSymbolCode.setTextSize(2, 14.0f);
            }
            myMarket.byVolSymbolCode.setText(this.moversModelModel.getByVolSymbCode());
            myMarket.byVolSymbolName.setText(this.moversModelModel.getByVolSymbName());
            TextView textView = myMarket.byVolChange;
            textView.setText("" + this.moversModelModel.getByVolChange());
            TextView textView2 = myMarket.byVolcurrent;
            textView2.setText("" + this.moversModelModel.getByVolcurrent());
            TextView textView3 = myMarket.byVolChangePerc;
            textView3.setText("" + this.moversModelModel.getByVolChangePerc());
            TextView textView4 = myMarket.byVolVolume;
            textView4.setText("" + this.moversModelModel.getByVolVolume());
            if (this.moversModelModel.getByVolChangePerc().length() == 10) {
                myMarket.byVolChangePerc.setTextSize(2, 14.0f);
                TextView textView5 = myMarket.byVolChangePerc;
                textView5.setText("" + this.moversModelModel.getByVolChangePerc().substring(0, 2) + "K%");
            } else if (this.moversModelModel.getByVolChangePerc().length() > 6) {
                myMarket.byVolChangePerc.setTextSize(2, 13.0f);
            } else {
                myMarket.byVolChangePerc.setTextSize(2, 14.0f);
            }
            myMarket.byVolChange.setTextColor(this.context.getResources().getColor(this.moversModelModel.getColor()));
            myMarket.byVolcurrent.setTextColor(this.context.getResources().getColor(this.moversModelModel.getColor()));
            myMarket.byVolChangePerc.setTextColor(this.context.getResources().getColor(this.moversModelModel.getColor()));
        } catch (Exception unused) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.moversModelArrayList.size();
    }

    /* loaded from: classes2.dex */
    public class MyMarket extends RecyclerView.ViewHolder {
        TextView byVolChange;
        TextView byVolChangePerc;
        TextView byVolSymbolCode;
        TextView byVolSymbolName;
        TextView byVolVolume;
        TextView byVolcurrent;
        CardView card_view;

        public MyMarket(View view) {
            super(view);
            this.card_view = (CardView) view.findViewById(R.id.card_view);
            this.byVolcurrent = (TextView) view.findViewById(R.id.byVolcurrentByD);
            this.byVolVolume = (TextView) view.findViewById(R.id.byVolVolumeByD);
            this.byVolSymbolCode = (TextView) view.findViewById(R.id.byVolsymbolCodeByD);
            this.byVolSymbolName = (TextView) view.findViewById(R.id.byVolsymbolNameByD);
            this.byVolChange = (TextView) view.findViewById(R.id.byVolChangeByD);
            this.byVolChangePerc = (TextView) view.findViewById(R.id.byVolChnagePercByD);
        }
    }
}
