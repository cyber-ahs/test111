package munirkhanani.adapters;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.microlinks.Munirkhanani.R;
import java.util.ArrayList;
import munirkhanani.adapters.MarketWatchAdapter;
import munirkhanani.model.ExStModel;
/* loaded from: classes2.dex */
public class IndicesAdapter extends RecyclerView.Adapter<MyMarket> {
    private ValueAnimator colorAnimation;
    private Context context;
    private LayoutInflater inflater;
    ExStModel mExStModel;
    ArrayList<ExStModel> mExStModelArrayList;

    public IndicesAdapter(Context context, ArrayList<ExStModel> arrayList) {
        this.context = context;
        this.mExStModelArrayList = arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.indices_list_item, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyMarket myMarket, final int i) {
        try {
            this.mExStModel = this.mExStModelArrayList.get(i);
            myMarket.currentIndex.setText(this.mExStModel.getCurrent_index());
            if (this.mExStModel.getMarket_code().length() >= 8) {
                myMarket.marketMarketCode.setTextSize(2, 11.0f);
            } else {
                myMarket.marketMarketCode.setTextSize(2, 13.0f);
            }
            myMarket.marketMarketCode.setText(this.mExStModel.getMarket_code());
            myMarket.indicesVolumeValue.setText(this.mExStModel.getVolume_traded());
            myMarket.indicesNetChanges.setText(this.mExStModel.getNet_change());
            TextView textView = myMarket.indicesNetChangesPerc;
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            String str = "0.00";
            sb.append(this.mExStModel.getNet_changePercan().equals("NaN") ? "0.00" : this.mExStModel.getNet_changePercan());
            sb.append("%)");
            textView.setText(sb.toString());
            myMarket.indicestradeValue.setText(this.mExStModel.getValue_traded());
            myMarket.highChangeValue.setText(this.mExStModel.getHighChangeValue());
            TextView textView2 = myMarket.highChangePercan;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("(");
            sb2.append(this.mExStModel.getHighChangePercan().equals("NaN") ? "0.00" : this.mExStModel.getHighChangePercan());
            sb2.append("%)");
            textView2.setText(sb2.toString());
            myMarket.lowChangeValue.setText(this.mExStModel.getLowChangeValue());
            TextView textView3 = myMarket.lowChangePercan;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("(");
            if (!this.mExStModel.getLowChangePercan().equals("NaN")) {
                str = this.mExStModel.getLowChangePercan();
            }
            sb3.append(str);
            sb3.append("%)");
            textView3.setText(sb3.toString());
            myMarket.high.setText(this.mExStModel.getHigh_index());
            myMarket.low.setText(this.mExStModel.getLow_index());
            myMarket.indicesNetChanges.setTextColor(this.mExStModel.getNetChangeTextColor());
            myMarket.indicesNetChangesPerc.setTextColor(this.mExStModel.getNetChangeTextColor());
            myMarket.marketMarketCode.setTextColor(this.mExStModel.getNetChangeTextColor());
            myMarket.currentIndex.setTextColor(this.mExStModel.getNetChangeTextColor());
            myMarket.indicesLowName.setTextColor(this.mExStModel.getLowValueChangeTextColor());
            myMarket.lowChangeValue.setTextColor(this.mExStModel.getLowValueChangeTextColor());
            myMarket.lowChangePercan.setTextColor(this.mExStModel.getLowValueChangeTextColor());
            myMarket.low.setTextColor(this.mExStModel.getLowValueChangeTextColor());
            myMarket.highChangeValue.setTextColor(this.mExStModel.getHighValueChangeTextColor());
            myMarket.highChangePercan.setTextColor(this.mExStModel.getHighValueChangeTextColor());
            myMarket.high.setTextColor(this.mExStModel.getHighValueChangeTextColor());
            myMarket.indicesHighName.setTextColor(this.mExStModel.getHighValueChangeTextColor());
            myMarket.itemView.setOnClickListener(new View.OnClickListener() { // from class: munirkhanani.adapters.IndicesAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    IndicesAdapter indicesAdapter = IndicesAdapter.this;
                    indicesAdapter.mExStModel = indicesAdapter.mExStModelArrayList.get(i);
                }
            });
        } catch (Exception unused) {
        }
    }

    private void setAnim(int i, int i2, MarketWatchAdapter.NewMyMarket newMyMarket) {
        ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), Integer.valueOf(i), Integer.valueOf(i2));
        this.colorAnimation = ofObject;
        ofObject.setDuration(3000L);
        this.colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: munirkhanani.adapters.IndicesAdapter.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
            }
        });
        this.colorAnimation.start();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mExStModelArrayList.size();
    }

    /* loaded from: classes2.dex */
    public class MyMarket extends RecyclerView.ViewHolder {
        TextView currentIndex;
        TextView high;
        TextView highChangePercan;
        TextView highChangeValue;
        TextView indicesHighName;
        TextView indicesLowName;
        TextView indicesNetChanges;
        TextView indicesNetChangesPerc;
        TextView indicesVolumeValue;
        TextView indicestradeValue;
        TextView low;
        TextView lowChangePercan;
        TextView lowChangeValue;
        TextView marketMarketCode;

        public MyMarket(View view) {
            super(view);
            this.highChangeValue = (TextView) view.findViewById(R.id.indicesHighValueChange);
            this.highChangePercan = (TextView) view.findViewById(R.id.indicesHighPercanChange);
            this.lowChangeValue = (TextView) view.findViewById(R.id.indicesLowValueChange);
            this.lowChangePercan = (TextView) view.findViewById(R.id.indicesLowPercanChange);
            this.high = (TextView) view.findViewById(R.id.indicesHighValue);
            this.low = (TextView) view.findViewById(R.id.indicesLowValue);
            this.currentIndex = (TextView) view.findViewById(R.id.indicesValue);
            this.marketMarketCode = (TextView) view.findViewById(R.id.indicesName);
            this.indicesHighName = (TextView) view.findViewById(R.id.indicesHighName);
            this.indicesLowName = (TextView) view.findViewById(R.id.indicesLowName);
            this.indicesVolumeValue = (TextView) view.findViewById(R.id.indicesVolumeValue);
            this.indicestradeValue = (TextView) view.findViewById(R.id.indicestradeValue);
            this.indicesNetChanges = (TextView) view.findViewById(R.id.indicesNetChanges);
            this.indicesNetChangesPerc = (TextView) view.findViewById(R.id.indicesNetChangesPerc);
        }
    }
}
