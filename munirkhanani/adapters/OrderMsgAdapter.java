package munirkhanani.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.microlinks.Munirkhanani.R;
import java.util.ArrayList;
import munirkhanani.model.OrderMsg;
/* loaded from: classes2.dex */
public class OrderMsgAdapter extends RecyclerView.Adapter<MyMarket> {
    private Context context;
    private LayoutInflater inflater;
    OrderMsg orderMsg;
    ArrayList<OrderMsg> orderMsgsArrayList;

    public OrderMsgAdapter(Context context, ArrayList<OrderMsg> arrayList) {
        this.context = context;
        this.orderMsgsArrayList = arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.order_list_item, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, int i) {
        try {
            this.orderMsg = this.orderMsgsArrayList.get(i);
            myMarket.orderMsgTextview.setText(this.orderMsg.getMessage());
            myMarket.orderDateTime.setText(this.orderMsg.getDateTime());
            if (this.orderMsg.getBgColor() != null) {
                myMarket.bgColorLayout.setCardBackgroundColor(Color.parseColor(this.orderMsg.getBgColor()));
            }
            if (this.orderMsg.getTextColor() != null) {
                myMarket.borderColorLayout.setCardBackgroundColor(Color.parseColor(this.orderMsg.getTextColor()));
                myMarket.orderMsgTextview.setTextColor(Color.parseColor(this.orderMsg.getTextColor()));
                myMarket.orderDateTime.setTextColor(Color.parseColor(this.orderMsg.getTextColor()));
            }
        } catch (Exception unused) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.orderMsgsArrayList.size();
    }

    /* loaded from: classes2.dex */
    public class MyMarket extends RecyclerView.ViewHolder {
        CardView bgColorLayout;
        CardView borderColorLayout;
        TextView orderDateTime;
        TextView orderMsgTextview;

        public MyMarket(View view) {
            super(view);
            this.borderColorLayout = (CardView) view.findViewById(R.id.borderColorLayout);
            this.bgColorLayout = (CardView) view.findViewById(R.id.bgColorLayout);
            this.orderMsgTextview = (TextView) view.findViewById(R.id.msgTextView);
            this.orderDateTime = (TextView) view.findViewById(R.id.dateTimeTextView);
        }
    }
}
