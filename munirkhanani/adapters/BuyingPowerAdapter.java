package munirkhanani.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.microlinks.Munirkhanani.R;
import java.util.List;
import munirkhanani.fragments.ExpWatchFrag;
/* loaded from: classes2.dex */
public class BuyingPowerAdapter extends RecyclerView.Adapter<MyMarket> {
    private Context context;
    private LayoutInflater inflater;
    private List<ExpWatchFrag.BuyingPowerData> openPositionModelArrayList;

    public BuyingPowerAdapter(Context context, List<ExpWatchFrag.BuyingPowerData> list) {
        this.context = context;
        this.openPositionModelArrayList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.view_buying_power_list_item, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, final int i) {
        try {
            ExpWatchFrag.BuyingPowerData buyingPowerData = this.openPositionModelArrayList.get(i);
            switch (i % 8) {
                case 0:
                    myMarket.cashPolicy.setBackgroundColor(Color.parseColor("#dbe5f1"));
                    myMarket.collPolicy.setBackgroundColor(Color.parseColor("#dbe5f1"));
                    myMarket.cashAssetsUtilize.setBackgroundColor(Color.parseColor("#dbe5f1"));
                    myMarket.collAssetsUtilize.setBackgroundColor(Color.parseColor("#dbe5f1"));
                    myMarket.cashUtilize.setBackgroundColor(Color.parseColor("#dbe5f1"));
                    myMarket.collUtilize.setBackgroundColor(Color.parseColor("#dbe5f1"));
                    myMarket.cashRemaining.setBackgroundColor(Color.parseColor("#dbe5f1"));
                    myMarket.collRemaining.setBackgroundColor(Color.parseColor("#dbe5f1"));
                    myMarket.ll_cashRemaining.setBackgroundColor(Color.parseColor("#dbe5f1"));
                    myMarket.ll_outerLayout.setBackgroundColor(Color.parseColor("#dbe5f1"));
                    myMarket.totalPolicy.setBackgroundColor(Color.parseColor("#dbe5f1"));
                    myMarket.maxOrderValue.setBackgroundColor(Color.parseColor("#dbe5f1"));
                    myMarket.exposureLimit.setBackgroundColor(Color.parseColor("#dbe5f1"));
                    myMarket.mktTypeApproved.setBackgroundColor(Color.parseColor("#4f81bd"));
                    myMarket.cashAndColl.setBackgroundColor(Color.parseColor("#95b3d7"));
                    break;
                case 1:
                    myMarket.cashPolicy.setBackgroundColor(Color.parseColor("#b8cce4"));
                    myMarket.collPolicy.setBackgroundColor(Color.parseColor("#b8cce4"));
                    myMarket.cashAssetsUtilize.setBackgroundColor(Color.parseColor("#b8cce4"));
                    myMarket.collAssetsUtilize.setBackgroundColor(Color.parseColor("#b8cce4"));
                    myMarket.cashUtilize.setBackgroundColor(Color.parseColor("#b8cce4"));
                    myMarket.collUtilize.setBackgroundColor(Color.parseColor("#b8cce4"));
                    myMarket.cashRemaining.setBackgroundColor(Color.parseColor("#b8cce4"));
                    myMarket.collRemaining.setBackgroundColor(Color.parseColor("#b8cce4"));
                    myMarket.ll_cashRemaining.setBackgroundColor(Color.parseColor("#b8cce4"));
                    myMarket.ll_outerLayout.setBackgroundColor(Color.parseColor("#b8cce4"));
                    myMarket.totalPolicy.setBackgroundColor(Color.parseColor("#b8cce4"));
                    myMarket.maxOrderValue.setBackgroundColor(Color.parseColor("#b8cce4"));
                    myMarket.exposureLimit.setBackgroundColor(Color.parseColor("#b8cce4"));
                    myMarket.mktTypeApproved.setBackgroundColor(Color.parseColor("#4f81bd"));
                    myMarket.cashAndColl.setBackgroundColor(Color.parseColor("#95b3d7"));
                    break;
                case 2:
                    myMarket.cashPolicy.setBackgroundColor(Color.parseColor("#f2dddc"));
                    myMarket.collPolicy.setBackgroundColor(Color.parseColor("#f2dddc"));
                    myMarket.cashAssetsUtilize.setBackgroundColor(Color.parseColor("#f2dddc"));
                    myMarket.collAssetsUtilize.setBackgroundColor(Color.parseColor("#f2dddc"));
                    myMarket.cashUtilize.setBackgroundColor(Color.parseColor("#f2dddc"));
                    myMarket.collUtilize.setBackgroundColor(Color.parseColor("#f2dddc"));
                    myMarket.cashRemaining.setBackgroundColor(Color.parseColor("#f2dddc"));
                    myMarket.collRemaining.setBackgroundColor(Color.parseColor("#f2dddc"));
                    myMarket.ll_cashRemaining.setBackgroundColor(Color.parseColor("#f2dddc"));
                    myMarket.ll_outerLayout.setBackgroundColor(Color.parseColor("#f2dddc"));
                    myMarket.totalPolicy.setBackgroundColor(Color.parseColor("#f2dddc"));
                    myMarket.maxOrderValue.setBackgroundColor(Color.parseColor("#f2dddc"));
                    myMarket.exposureLimit.setBackgroundColor(Color.parseColor("#f2dddc"));
                    myMarket.mktTypeApproved.setBackgroundColor(Color.parseColor("#c0504d"));
                    myMarket.cashAndColl.setBackgroundColor(Color.parseColor("#d99795"));
                    break;
                case 3:
                    myMarket.cashPolicy.setBackgroundColor(Color.parseColor("#e6b9b8"));
                    myMarket.collPolicy.setBackgroundColor(Color.parseColor("#e6b9b8"));
                    myMarket.cashAssetsUtilize.setBackgroundColor(Color.parseColor("#e6b9b8"));
                    myMarket.collAssetsUtilize.setBackgroundColor(Color.parseColor("#e6b9b8"));
                    myMarket.cashUtilize.setBackgroundColor(Color.parseColor("#e6b9b8"));
                    myMarket.collUtilize.setBackgroundColor(Color.parseColor("#e6b9b8"));
                    myMarket.cashRemaining.setBackgroundColor(Color.parseColor("#e6b9b8"));
                    myMarket.collRemaining.setBackgroundColor(Color.parseColor("#e6b9b8"));
                    myMarket.ll_cashRemaining.setBackgroundColor(Color.parseColor("#e6b9b8"));
                    myMarket.ll_outerLayout.setBackgroundColor(Color.parseColor("#e6b9b8"));
                    myMarket.totalPolicy.setBackgroundColor(Color.parseColor("#e6b9b8"));
                    myMarket.maxOrderValue.setBackgroundColor(Color.parseColor("#e6b9b8"));
                    myMarket.exposureLimit.setBackgroundColor(Color.parseColor("#e6b9b8"));
                    myMarket.mktTypeApproved.setBackgroundColor(Color.parseColor("#c0504d"));
                    myMarket.cashAndColl.setBackgroundColor(Color.parseColor("#d99795"));
                    break;
                case 4:
                    myMarket.cashPolicy.setBackgroundColor(Color.parseColor("#e5e0ec"));
                    myMarket.collPolicy.setBackgroundColor(Color.parseColor("#e5e0ec"));
                    myMarket.cashAssetsUtilize.setBackgroundColor(Color.parseColor("#e5e0ec"));
                    myMarket.collAssetsUtilize.setBackgroundColor(Color.parseColor("#e5e0ec"));
                    myMarket.cashUtilize.setBackgroundColor(Color.parseColor("#e5e0ec"));
                    myMarket.collUtilize.setBackgroundColor(Color.parseColor("#e5e0ec"));
                    myMarket.cashRemaining.setBackgroundColor(Color.parseColor("#e5e0ec"));
                    myMarket.collRemaining.setBackgroundColor(Color.parseColor("#e5e0ec"));
                    myMarket.ll_cashRemaining.setBackgroundColor(Color.parseColor("#e5e0ec"));
                    myMarket.ll_outerLayout.setBackgroundColor(Color.parseColor("#e5e0ec"));
                    myMarket.totalPolicy.setBackgroundColor(Color.parseColor("#e5e0ec"));
                    myMarket.maxOrderValue.setBackgroundColor(Color.parseColor("#e5e0ec"));
                    myMarket.exposureLimit.setBackgroundColor(Color.parseColor("#e5e0ec"));
                    myMarket.mktTypeApproved.setBackgroundColor(Color.parseColor("#8064a2"));
                    myMarket.cashAndColl.setBackgroundColor(Color.parseColor("#b2a1c7"));
                    break;
                case 5:
                    myMarket.cashPolicy.setBackgroundColor(Color.parseColor("#ccc0da"));
                    myMarket.collPolicy.setBackgroundColor(Color.parseColor("#ccc0da"));
                    myMarket.cashAssetsUtilize.setBackgroundColor(Color.parseColor("#ccc0da"));
                    myMarket.collAssetsUtilize.setBackgroundColor(Color.parseColor("#ccc0da"));
                    myMarket.cashUtilize.setBackgroundColor(Color.parseColor("#ccc0da"));
                    myMarket.collUtilize.setBackgroundColor(Color.parseColor("#ccc0da"));
                    myMarket.cashRemaining.setBackgroundColor(Color.parseColor("#ccc0da"));
                    myMarket.collRemaining.setBackgroundColor(Color.parseColor("#ccc0da"));
                    myMarket.ll_cashRemaining.setBackgroundColor(Color.parseColor("#ccc0da"));
                    myMarket.ll_outerLayout.setBackgroundColor(Color.parseColor("#ccc0da"));
                    myMarket.totalPolicy.setBackgroundColor(Color.parseColor("#ccc0da"));
                    myMarket.maxOrderValue.setBackgroundColor(Color.parseColor("#ccc0da"));
                    myMarket.exposureLimit.setBackgroundColor(Color.parseColor("#ccc0da"));
                    myMarket.mktTypeApproved.setBackgroundColor(Color.parseColor("#8064a2"));
                    myMarket.cashAndColl.setBackgroundColor(Color.parseColor("#b2a1c7"));
                    break;
                case 6:
                    myMarket.cashPolicy.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray_4));
                    myMarket.collPolicy.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray_4));
                    myMarket.cashAssetsUtilize.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray_4));
                    myMarket.collAssetsUtilize.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray_4));
                    myMarket.cashUtilize.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray_4));
                    myMarket.collUtilize.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray_4));
                    myMarket.cashRemaining.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray_4));
                    myMarket.collRemaining.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray_4));
                    myMarket.ll_cashRemaining.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray_4));
                    myMarket.ll_outerLayout.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray_4));
                    myMarket.totalPolicy.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray_4));
                    myMarket.maxOrderValue.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray_4));
                    myMarket.exposureLimit.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_gray_4));
                    myMarket.mktTypeApproved.setBackgroundColor(Color.parseColor("#68a225"));
                    myMarket.cashAndColl.setBackgroundColor(Color.parseColor("#b3de81"));
                    break;
                case 7:
                    myMarket.cashPolicy.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_green));
                    myMarket.collPolicy.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_green));
                    myMarket.cashAssetsUtilize.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_green));
                    myMarket.collAssetsUtilize.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_green));
                    myMarket.cashUtilize.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_green));
                    myMarket.collUtilize.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_green));
                    myMarket.cashRemaining.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_green));
                    myMarket.collRemaining.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_green));
                    myMarket.ll_cashRemaining.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_green));
                    myMarket.ll_outerLayout.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_green));
                    myMarket.totalPolicy.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_green));
                    myMarket.maxOrderValue.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_green));
                    myMarket.exposureLimit.setBackgroundColor(this.context.getResources().getColor(R.color.xexpu_sum_green));
                    myMarket.mktTypeApproved.setBackgroundColor(Color.parseColor("#68a225"));
                    myMarket.cashAndColl.setBackgroundColor(Color.parseColor("#b3de81"));
                    break;
            }
            myMarket.mktType.setText(buyingPowerData.FirstObject.getMktType());
            myMarket.approved_status.setText(buyingPowerData.FirstObject.getApprovedStatusType());
            myMarket.cashTitle.setText(buyingPowerData.FirstObject.getCashTitle());
            myMarket.cashPolicy.setText(buyingPowerData.FirstObject.getCashOne());
            myMarket.cashAssetsUtilize.setText(buyingPowerData.FirstObject.getCashTwo());
            myMarket.cashUtilize.setText(buyingPowerData.FirstObject.getCashThree());
            myMarket.cashRemaining.setText(buyingPowerData.FirstObject.getCashFour());
            myMarket.collTitle.setText(buyingPowerData.FirstObject.getCollateralsTitle());
            myMarket.collPolicy.setText(buyingPowerData.FirstObject.getCollateralsOne());
            myMarket.collAssetsUtilize.setText(buyingPowerData.FirstObject.getCollateralsTwo());
            myMarket.collUtilize.setText(buyingPowerData.FirstObject.getCollateralsThree());
            myMarket.collRemaining.setText(buyingPowerData.FirstObject.getCollateralsFour());
            if (buyingPowerData.FirstObject.getMaxOrderValue().contains("-")) {
                myMarket.maxOrderValue.setTextColor(this.context.getResources().getColor(R.color.negative_text_color));
            } else if (buyingPowerData.FirstObject.getMaxOrderValue().equals("0.00")) {
                myMarket.maxOrderValue.setTextColor(this.context.getResources().getColor(R.color.neutral_text_color));
            } else {
                myMarket.maxOrderValue.setTextColor(this.context.getResources().getColor(R.color.positive_text_color));
            }
            myMarket.maxOrderValue.setText(buyingPowerData.FirstObject.getMaxOrderValue());
            if (buyingPowerData.FirstObject.getTotalPolicyValue().contains("%")) {
                myMarket.totalPolicy.setText(buyingPowerData.FirstObject.getTotalPolicyValue());
            } else {
                TextView textView = myMarket.totalPolicy;
                textView.setText(buyingPowerData.FirstObject.getTotalPolicyValue() + "%");
            }
            myMarket.exposureLimit.setText(buyingPowerData.FirstObject.getExposureLimit());
            myMarket.itemView.setOnClickListener(new View.OnClickListener() { // from class: munirkhanani.adapters.BuyingPowerAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                }
            });
        } catch (Exception unused) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<ExpWatchFrag.BuyingPowerData> list = this.openPositionModelArrayList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* loaded from: classes2.dex */
    public class MyMarket extends RecyclerView.ViewHolder {
        TextView approved_status;
        LinearLayout cashAndColl;
        TextView cashAssetsUtilize;
        TextView cashPolicy;
        TextView cashRemaining;
        TextView cashTitle;
        TextView cashUtilize;
        TextView collAssetsUtilize;
        TextView collPolicy;
        TextView collRemaining;
        TextView collTitle;
        TextView collUtilize;
        LinearLayout cover;
        TextView exposureLimit;
        LinearLayout ll_cashRemaining;
        LinearLayout ll_outerLayout;
        TextView maxOrderValue;
        TextView mktType;
        LinearLayout mktTypeApproved;
        TextView totalPolicy;

        public MyMarket(View view) {
            super(view);
            this.cover = (LinearLayout) view.findViewById(R.id.cover);
            this.cashAndColl = (LinearLayout) view.findViewById(R.id.cashAndColl);
            this.mktTypeApproved = (LinearLayout) view.findViewById(R.id.mktTypeApproved);
            this.mktType = (TextView) view.findViewById(R.id.mktType);
            this.approved_status = (TextView) view.findViewById(R.id.approved_status);
            this.cashTitle = (TextView) view.findViewById(R.id.cashTitle);
            this.cashPolicy = (TextView) view.findViewById(R.id.cashPolicy);
            this.cashAssetsUtilize = (TextView) view.findViewById(R.id.cashAssetsUtilize);
            this.cashUtilize = (TextView) view.findViewById(R.id.cashUtilize);
            this.cashRemaining = (TextView) view.findViewById(R.id.cashRemaining);
            this.collTitle = (TextView) view.findViewById(R.id.collTitle);
            this.collPolicy = (TextView) view.findViewById(R.id.collPolicy);
            this.collAssetsUtilize = (TextView) view.findViewById(R.id.collAssetsUtilize);
            this.collUtilize = (TextView) view.findViewById(R.id.collUtilize);
            this.collRemaining = (TextView) view.findViewById(R.id.collRemaining);
            this.ll_cashRemaining = (LinearLayout) view.findViewById(R.id.ll_cashRemaining);
            this.ll_outerLayout = (LinearLayout) view.findViewById(R.id.ll_outerLayout);
            this.totalPolicy = (TextView) view.findViewById(R.id.totalPolicy);
            this.maxOrderValue = (TextView) view.findViewById(R.id.maxOrderValue);
            this.exposureLimit = (TextView) view.findViewById(R.id.exposureLimit);
        }
    }
}
