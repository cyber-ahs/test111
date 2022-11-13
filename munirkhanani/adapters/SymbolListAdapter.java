package munirkhanani.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.microlinks.Munirkhanani.R;
import java.util.ArrayList;
import java.util.List;
import munirkhanani.model.SymbolsModel;
/* loaded from: classes2.dex */
public class SymbolListAdapter extends RecyclerView.Adapter<MyMarket> implements Filterable {
    private Context context;
    Boolean isMarketCodeShow;
    private OnItemClickListener listener;
    private List<SymbolsModel> mFilteredList;
    private List<SymbolsModel> mMarketData;
    private SymbolsModel marketData;
    String whichClass;

    /* loaded from: classes2.dex */
    public interface OnItemClickListener {
        void onItemClick(SymbolsModel symbolsModel);
    }

    public SymbolListAdapter(Context context, List<SymbolsModel> list, OnItemClickListener onItemClickListener, String str) {
        this.isMarketCodeShow = true;
        this.whichClass = str;
        this.context = context;
        this.mMarketData = list;
        this.mFilteredList = list;
        this.listener = onItemClickListener;
    }

    public SymbolListAdapter(Context context, List<SymbolsModel> list, OnItemClickListener onItemClickListener, String str, Boolean bool) {
        Boolean.valueOf(true);
        this.whichClass = str;
        this.context = context;
        this.mMarketData = list;
        this.mFilteredList = list;
        this.listener = onItemClickListener;
        this.isMarketCodeShow = bool;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.symbol_list_item, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, int i) {
        try {
            myMarket.bind(this.mFilteredList.get(i), this.listener);
            Log.i("", "whichClass SymbollistAdapter : " + this.whichClass);
            this.marketData = this.mFilteredList.get(i);
            if (this.isMarketCodeShow.booleanValue()) {
                myMarket.marketSymbolCode.setText(this.marketData.getSymbolCode());
                myMarket.marketMarketCode.setVisibility(0);
                myMarket.marketMarketCode.setText(this.marketData.getMarketCode());
            } else if (!this.marketData.getMarketCode().equals("ODL")) {
                myMarket.marketMarketCode.setVisibility(8);
                myMarket.marketSymbolCode.setText(this.marketData.getSymbolCode());
            }
        } catch (Exception unused) {
        }
    }

    public SymbolsModel getItem(int i) {
        return this.mFilteredList.get(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<SymbolsModel> list = this.mFilteredList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        return new Filter() { // from class: munirkhanani.adapters.SymbolListAdapter.1
            @Override // android.widget.Filter
            protected Filter.FilterResults performFiltering(CharSequence charSequence) {
                try {
                    String charSequence2 = charSequence.toString();
                    if (charSequence2.isEmpty()) {
                        SymbolListAdapter symbolListAdapter = SymbolListAdapter.this;
                        symbolListAdapter.mFilteredList = symbolListAdapter.mMarketData;
                    } else {
                        ArrayList arrayList = new ArrayList();
                        if (SymbolListAdapter.this.whichClass.equals("Ord")) {
                            Log.i("ADAPTER", "Orders : ");
                            if (charSequence2.contains("/")) {
                                String[] split = charSequence2.split("/");
                                for (SymbolsModel symbolsModel : SymbolListAdapter.this.mMarketData) {
                                    if (symbolsModel.getMarketCode().equals(split[0]) && symbolsModel.getSymbolCode().startsWith(split[1])) {
                                        arrayList.add(symbolsModel);
                                    }
                                }
                            }
                        } else {
                            Log.i("ADAPTER", "Normall : ");
                            for (SymbolsModel symbolsModel2 : SymbolListAdapter.this.mMarketData) {
                                if (symbolsModel2.getSymbolCode().startsWith(charSequence2) || symbolsModel2.getSymbolCode().toLowerCase().startsWith(charSequence2) || symbolsModel2.getSymbolCode().toUpperCase().startsWith(charSequence2)) {
                                    arrayList.add(symbolsModel2);
                                }
                            }
                        }
                        SymbolListAdapter.this.mFilteredList = arrayList;
                    }
                    Filter.FilterResults filterResults = new Filter.FilterResults();
                    filterResults.values = SymbolListAdapter.this.mFilteredList;
                    return filterResults;
                } catch (Exception unused) {
                    return null;
                }
            }

            @Override // android.widget.Filter
            protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                try {
                    SymbolListAdapter.this.mFilteredList = (List) filterResults.values;
                    SymbolListAdapter.this.notifyDataSetChanged();
                } catch (Exception unused) {
                }
            }
        };
    }

    /* loaded from: classes2.dex */
    public class MyMarket extends RecyclerView.ViewHolder {
        RelativeLayout mainLayout;
        TextView marketMarketCode;
        TextView marketSymbolCode;

        public MyMarket(View view) {
            super(view);
            this.marketSymbolCode = (TextView) view.findViewById(R.id.symbolName);
            this.marketMarketCode = (TextView) view.findViewById(R.id.marketCode);
            this.mainLayout = (RelativeLayout) view.findViewById(R.id.mainLayout);
        }

        void bind(final SymbolsModel symbolsModel, final OnItemClickListener onItemClickListener) {
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: munirkhanani.adapters.SymbolListAdapter.MyMarket.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    onItemClickListener.onItemClick(symbolsModel);
                }
            });
        }
    }
}
