package munirkhanani.adapters;

import android.animation.ValueAnimator;
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
import munirkhanani.model.SectorsModel;
/* loaded from: classes2.dex */
public class SectorsListAdapter extends RecyclerView.Adapter<MyMarket> implements Filterable {
    private ValueAnimator colorAnimation;
    private Context context;
    private int ddd;
    private LayoutInflater inflater;
    private OnItemClickListener listener;
    private List<SectorsModel> mFilteredList;
    private List<SectorsModel> mMarketData;
    private SectorsModel marketData;
    String whichClass;
    private int expandedPosition = -1;
    private int curent = -1;

    /* loaded from: classes2.dex */
    public interface OnItemClickListener {
        void onItemClick(SectorsModel sectorsModel);
    }

    public SectorsListAdapter(Context context, List<SectorsModel> list, OnItemClickListener onItemClickListener, String str) {
        this.whichClass = str;
        this.context = context;
        this.mMarketData = list;
        this.mFilteredList = list;
        this.listener = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.sectors_list_item, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, int i) {
        try {
            myMarket.bind(this.mFilteredList.get(i), this.listener);
            if (i % 2 == 1) {
                myMarket.cover.setBackgroundColor(this.context.getResources().getColor(R.color.alternate));
            } else {
                myMarket.cover.setBackgroundColor(this.context.getResources().getColor(R.color.white));
            }
            Log.i("", "whichClass SymbollistAdapter : " + this.whichClass);
            SectorsModel sectorsModel = this.mFilteredList.get(i);
            this.marketData = sectorsModel;
            if (!sectorsModel.getSectorName().equals("null") && this.marketData.getSectorName() != "null") {
                myMarket.symbolSectorName.setText(this.marketData.getSectorName());
                return;
            }
            myMarket.symbolSectorName.setText("");
        } catch (Exception unused) {
        }
    }

    public SectorsModel getItem(int i) {
        return this.mFilteredList.get(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<SectorsModel> list = this.mFilteredList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        return new Filter() { // from class: munirkhanani.adapters.SectorsListAdapter.1
            @Override // android.widget.Filter
            protected Filter.FilterResults performFiltering(CharSequence charSequence) {
                String charSequence2 = charSequence.toString();
                if (charSequence2.isEmpty()) {
                    SectorsListAdapter sectorsListAdapter = SectorsListAdapter.this;
                    sectorsListAdapter.mFilteredList = sectorsListAdapter.mMarketData;
                } else {
                    ArrayList arrayList = new ArrayList();
                    if (SectorsListAdapter.this.whichClass.equals("Ord")) {
                        Log.i("ADAPTER", "Orders : ");
                        if (charSequence2.contains("/")) {
                            charSequence2.split("/");
                            for (SectorsModel sectorsModel : SectorsListAdapter.this.mMarketData) {
                            }
                        }
                    } else {
                        Log.i("ADAPTER", "Normall : ");
                        for (SectorsModel sectorsModel2 : SectorsListAdapter.this.mMarketData) {
                            if (sectorsModel2.getSectorName().startsWith(charSequence2)) {
                                arrayList.add(sectorsModel2);
                            }
                        }
                    }
                    SectorsListAdapter.this.mFilteredList = arrayList;
                }
                Filter.FilterResults filterResults = new Filter.FilterResults();
                filterResults.values = SectorsListAdapter.this.mFilteredList;
                return filterResults;
            }

            @Override // android.widget.Filter
            protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                SectorsListAdapter.this.mFilteredList = (List) filterResults.values;
                SectorsListAdapter.this.notifyDataSetChanged();
            }
        };
    }

    /* loaded from: classes2.dex */
    public static class MyMarket extends RecyclerView.ViewHolder {
        RelativeLayout cover;
        TextView symbolSectorName;

        public MyMarket(View view) {
            super(view);
            this.cover = (RelativeLayout) view.findViewById(R.id.card);
            this.symbolSectorName = (TextView) view.findViewById(R.id.symbolSectorName);
        }

        void bind(final SectorsModel sectorsModel, final OnItemClickListener onItemClickListener) {
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: munirkhanani.adapters.SectorsListAdapter.MyMarket.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    onItemClickListener.onItemClick(sectorsModel);
                }
            });
        }
    }
}
