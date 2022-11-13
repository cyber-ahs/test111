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
import java.util.ArrayList;
import munirkhanani.model.AccountStatementModel;
/* loaded from: classes2.dex */
public class AccountStatementAdapter extends RecyclerView.Adapter<MyMarket> {
    private ArrayList<AccountStatementModel> accountStatementModelArrayList;
    private Context context;
    private LayoutInflater inflater;

    public AccountStatementAdapter(Context context, ArrayList<AccountStatementModel> arrayList) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.accountStatementModelArrayList = arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyMarket(this.inflater.inflate(R.layout.account_statement_list, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, final int i) {
        try {
            AccountStatementModel accountStatementModel = this.accountStatementModelArrayList.get(i);
            myMarket.cover.setBackgroundColor(this.context.getResources().getColor(R.color.white));
            myMarket.accountStatDate.setText(accountStatementModel.getDate());
            myMarket.accountStatDesc.setText(accountStatementModel.getDesc());
            myMarket.accountStatDebit.setText(accountStatementModel.getDebit().equals("0.00") ? "0" : accountStatementModel.getDebit());
            if (myMarket.accountStatDebit.getText() == null) {
                myMarket.accountStatDebit.setText("");
            }
            if (myMarket.accountStatCredit.getText() == null) {
                myMarket.accountStatCredit.setText("");
            }
            myMarket.accountStatCredit.setText(accountStatementModel.getCredit());
            TextView textView = myMarket.accountStatBalance;
            textView.setText(accountStatementModel.getBalance() + " ");
            myMarket.accountStatBalance.setTextColor(this.context.getResources().getColor(accountStatementModel.getColor()));
            myMarket.itemView.setOnClickListener(new View.OnClickListener() { // from class: munirkhanani.adapters.AccountStatementAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Log.i("Data Index", "" + i);
                }
            });
        } catch (Exception unused) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.accountStatementModelArrayList.size();
    }

    /* loaded from: classes2.dex */
    public class MyMarket extends RecyclerView.ViewHolder {
        TextView accountStatBalance;
        TextView accountStatCredit;
        TextView accountStatDate;
        TextView accountStatDebit;
        TextView accountStatDesc;
        LinearLayout cover;

        public MyMarket(View view) {
            super(view);
            this.cover = (LinearLayout) view.findViewById(R.id.cover);
            this.accountStatDate = (TextView) view.findViewById(R.id.accountStatDate);
            this.accountStatDesc = (TextView) view.findViewById(R.id.accountStatDesc);
            this.accountStatDebit = (TextView) view.findViewById(R.id.accountStatDebit);
            this.accountStatCredit = (TextView) view.findViewById(R.id.accountStatCredit);
            this.accountStatBalance = (TextView) view.findViewById(R.id.accountStatBalance);
        }
    }
}
