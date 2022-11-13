package munirkhanani.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.microlinks.Munirkhanani.R;
import java.util.ArrayList;
import java.util.List;
import munirkhanani.model.ClientsCodeModel;
/* loaded from: classes2.dex */
public class ClientCodeListAdapter extends RecyclerView.Adapter<MyMarket> implements Filterable {
    private ClientsCodeModel clientsCodeModel;
    private List<ClientsCodeModel> clientsCodeModelList;
    private Context context;
    private OnItemClickListener listener;
    private List<ClientsCodeModel> mFilteredList;

    /* loaded from: classes2.dex */
    public interface OnItemClickListener {
        void onItemClick(ClientsCodeModel clientsCodeModel);
    }

    public ClientCodeListAdapter(Context context, List<ClientsCodeModel> list, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.clientsCodeModelList = list;
        this.mFilteredList = list;
        this.listener = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.clients_code_list_item, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, int i) {
        try {
            myMarket.bind(this.mFilteredList.get(i), this.listener);
            this.clientsCodeModel = this.mFilteredList.get(i);
            myMarket.clientsCode.setText(this.clientsCodeModel.getClientsCode());
        } catch (Exception unused) {
        }
    }

    public ClientsCodeModel getItem(int i) {
        return this.mFilteredList.get(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<ClientsCodeModel> list = this.mFilteredList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        return new Filter() { // from class: munirkhanani.adapters.ClientCodeListAdapter.1
            @Override // android.widget.Filter
            protected Filter.FilterResults performFiltering(CharSequence charSequence) {
                String charSequence2 = charSequence.toString();
                if (charSequence2.isEmpty()) {
                    ClientCodeListAdapter clientCodeListAdapter = ClientCodeListAdapter.this;
                    clientCodeListAdapter.mFilteredList = clientCodeListAdapter.clientsCodeModelList;
                } else {
                    ArrayList arrayList = new ArrayList();
                    for (ClientsCodeModel clientsCodeModel : ClientCodeListAdapter.this.clientsCodeModelList) {
                        if (clientsCodeModel.getClientsCode().toLowerCase().startsWith(charSequence2) || clientsCodeModel.getClientsCode().toUpperCase().startsWith(charSequence2)) {
                            arrayList.add(clientsCodeModel);
                        }
                    }
                    ClientCodeListAdapter.this.mFilteredList = arrayList;
                }
                Filter.FilterResults filterResults = new Filter.FilterResults();
                filterResults.values = ClientCodeListAdapter.this.mFilteredList;
                return filterResults;
            }

            @Override // android.widget.Filter
            protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                ClientCodeListAdapter.this.mFilteredList = (List) filterResults.values;
                ClientCodeListAdapter.this.notifyDataSetChanged();
            }
        };
    }

    /* loaded from: classes2.dex */
    public class MyMarket extends RecyclerView.ViewHolder {
        TextView clientsCode;

        public MyMarket(View view) {
            super(view);
            this.clientsCode = (TextView) view.findViewById(R.id.clientsCode);
        }

        void bind(final ClientsCodeModel clientsCodeModel, final OnItemClickListener onItemClickListener) {
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: munirkhanani.adapters.ClientCodeListAdapter.MyMarket.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    onItemClickListener.onItemClick(clientsCodeModel);
                }
            });
        }
    }
}
