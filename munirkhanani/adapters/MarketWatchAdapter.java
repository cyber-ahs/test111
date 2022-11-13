package munirkhanani.adapters;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.balsikandar.crashreporter.CrashReporter;
import com.microlinks.Munirkhanani.R;
import com.tooltip.Tooltip;
import java.util.ArrayList;
import java.util.HashMap;
import munirkhanani.activities.LoginActivity;
import munirkhanani.dbmodel.DbContract;
import munirkhanani.fragments.CustomeWatchFrag;
import munirkhanani.fragments.Mbo_Mbp_Frag;
import munirkhanani.fragments.OrderTicketFrags;
import munirkhanani.helpers.Constants;
import munirkhanani.model.Market;
import munirkhanani.model.UpperLowerPriceModel;
import munirkhanani.network.HttpHandler;
import org.slf4j.Marker;
/* loaded from: classes2.dex */
public class MarketWatchAdapter extends RecyclerView.Adapter<NewMyMarket> {
    private ValueAnimator colorAnimation;
    private Context context;
    private int ddd;
    private NewMyMarket holder;
    ImageView iv_marketTrend;
    ArrayList<Market> mMarketData;
    Market marketData;
    ArrayList<Market> oldMarketData;
    private int expandedPosition = -1;
    private int lastPosition = -1;
    Boolean isClickAble = true;

    public MarketWatchAdapter(Context context, ArrayList<Market> arrayList, ArrayList<Market> arrayList2) {
        this.context = context;
        this.mMarketData = arrayList;
        this.oldMarketData = arrayList2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public NewMyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return null;
                }
                return new NewMyMarket(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_list_items_new, viewGroup, false));
            }
            return new NewMyMarket(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.collapse_list_item_market, viewGroup, false));
        }
        return new NewMyMarket(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_list_item_simple_screen, viewGroup, false));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        char c;
        String str = CustomeWatchFrag.viewTypeMarketScreen;
        str.hashCode();
        switch (str.hashCode()) {
            case -1113290645:
                if (str.equals("Advance Theme")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -648561897:
                if (str.equals("Basic Theme")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1591448027:
                if (str.equals("Simple Theme")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return 2;
            case 1:
                return 0;
            case 2:
                return 1;
            default:
                return -1;
        }
    }

    private void setAnim(int i, int i2, final NewMyMarket newMyMarket) {
        ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), Integer.valueOf(i), Integer.valueOf(i2));
        this.colorAnimation = ofObject;
        ofObject.setDuration(3000L);
        this.colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: munirkhanani.adapters.MarketWatchAdapter.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                newMyMarket.cover.setBackgroundColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        });
        this.colorAnimation.start();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(NewMyMarket newMyMarket, final int i) {
        ArrayList<Market> arrayList;
        try {
            newMyMarket.itemView.setTag(Integer.valueOf(i));
            this.marketData = this.mMarketData.get(i);
            if (i % 2 == 1) {
                newMyMarket.cover.setBackgroundColor(this.context.getResources().getColor(R.color.white));
            } else {
                newMyMarket.cover.setBackgroundColor(this.context.getResources().getColor(R.color.white));
            }
            newMyMarket.marketSymbolCode.setText(this.marketData.getSymbolCode());
            if (newMyMarket.iv_mosque != null && LoginActivity.ShariaDictionary != null) {
                HashMap<String, String> hashMap = LoginActivity.ShariaDictionary;
                String str = hashMap.get(this.marketData.getSymbolCode() + "-" + this.marketData.getMarketCode());
                if (str != null && !str.equals("null") && !str.equals("N") && str.equals("Y")) {
                    newMyMarket.iv_mosque.setVisibility(0);
                } else {
                    newMyMarket.iv_mosque.setVisibility(4);
                }
            }
            if (!Constants.isFullRowBlink.booleanValue() && (arrayList = this.oldMarketData) != null && arrayList.size() > 0) {
                callingAnimation(this.marketData.getBuyPriceColor(), newMyMarket.buyPrice, this.marketData.getBuyPri(), this.oldMarketData.get(0).getBuyPri());
                callingAnimation(this.marketData.getBuyVolumeColor(), newMyMarket.buyVolume, this.marketData.getBuyVol(), this.oldMarketData.get(0).getBuyVol());
                callingAnimation(this.marketData.getSellPriceColor(), newMyMarket.sellPrice, this.marketData.getSellPri(), this.oldMarketData.get(0).getSellPri());
                callingAnimation(this.marketData.getSellVolumeColor(), newMyMarket.sellVolume, this.marketData.getSellVol(), this.oldMarketData.get(0).getSellVol());
            }
            if (this.marketData.getSymbolName() != null && !this.marketData.getSymbolName().equals("null")) {
                newMyMarket.marketSymbolName.setText(this.marketData.getSymbolName());
            } else {
                newMyMarket.marketSymbolName.setText("");
            }
            newMyMarket.marketMarketCode.setText(this.marketData.getMarketCode());
            TextView textView = newMyMarket.marketLastPrice;
            textView.setText("" + this.marketData.getLastPrice());
            TextView textView2 = newMyMarket.marketNetChange;
            textView2.setText("" + this.marketData.getNetChange());
            TextView textView3 = newMyMarket.buyPrice;
            textView3.setText("" + this.marketData.getBuyPri());
            TextView textView4 = newMyMarket.buyVolume;
            textView4.setText("" + this.marketData.getBuyVol());
            TextView textView5 = newMyMarket.sellPrice;
            textView5.setText("" + this.marketData.getSellPri());
            TextView textView6 = newMyMarket.sellVolume;
            textView6.setText("" + this.marketData.getSellVol());
            newMyMarket.lastTradeVol.setText(this.marketData.getLastVolume());
            newMyMarket.changePerc.setTextColor(this.marketData.getColor());
            newMyMarket.marketNetChange.setTextColor(this.marketData.getColor());
            newMyMarket.marketLastPrice.setTextColor(this.marketData.getColor());
            newMyMarket.marketMarketCode.setTextColor(this.marketData.getColor());
            newMyMarket.marketSymbolCode.setTextColor(this.marketData.getColor());
            if (this.marketData.getSymbolCode().equals("ASCR")) {
                Log.d("marketdata_color", "color: " + this.marketData.getColor());
            }
            HashMap<String, UpperLowerPriceModel> hashMap2 = HttpHandler.Upper_lowerCap;
            UpperLowerPriceModel upperLowerPriceModel = hashMap2.get(this.marketData.getMarketCode() + "_" + this.marketData.getSymbolCode());
            if (upperLowerPriceModel != null) {
                newMyMarket.upperPri.setText(upperLowerPriceModel.getUpperPri().replaceAll("null", "0"));
                newMyMarket.lowerPri.setText(upperLowerPriceModel.getLowerPri().replaceAll("null", "0"));
                if (this.marketData.getLastPrice() != null && !this.marketData.getLastPrice().equals("0.00")) {
                    if (upperLowerPriceModel.getUpperPri().replaceAll("null", "0").equals(this.marketData.getLastPrice()) && this.marketData.getLastPrice().equals(this.marketData.getBuyPri()) && this.marketData.getSellVol().equals("0") && !this.marketData.getBuyVol().equals("0")) {
                        newMyMarket.marketSymbolCode.setBackgroundColor(this.context.getResources().getColor(R.color.positive_header_line_background_color));
                    } else if (upperLowerPriceModel.getLowerPri().replaceAll("null", "0").equals(this.marketData.getLastPrice()) && this.marketData.getLastPrice().equals(this.marketData.getSellPri()) && this.marketData.getBuyVol().equals("0") && !this.marketData.getSellVol().equals("0")) {
                        newMyMarket.marketSymbolCode.setBackgroundColor(this.context.getResources().getColor(R.color.negative_header_line_background_color));
                    } else {
                        newMyMarket.marketSymbolCode.setBackgroundColor(this.context.getResources().getColor(R.color.trans));
                    }
                } else {
                    newMyMarket.marketSymbolCode.setBackgroundColor(this.context.getResources().getColor(R.color.trans));
                }
            }
            if (CustomeWatchFrag.viewTypeMarketScreen.equals("Basic Theme")) {
                if (this.marketData.getChangePerc() > 0.0d) {
                    if (newMyMarket.iv_marketTrend != null) {
                        newMyMarket.iv_marketTrend.setBackground(CrashReporter.getContext().getResources().getDrawable(R.drawable.trending_up_ic));
                    }
                    TextView textView7 = newMyMarket.changePerc;
                    textView7.setText("(+" + this.marketData.getChangePerc() + "%)");
                    TextView textView8 = newMyMarket.marketNetChange;
                    textView8.setText(Marker.ANY_NON_NULL_MARKER + this.marketData.getNetChange());
                } else {
                    if (newMyMarket.iv_marketTrend != null) {
                        if (this.marketData.getChangePerc() < 0.0d) {
                            newMyMarket.iv_marketTrend.setBackground(CrashReporter.getContext().getResources().getDrawable(R.drawable.trending_down_ic));
                        } else {
                            newMyMarket.iv_marketTrend.setBackground(CrashReporter.getContext().getResources().getDrawable(R.drawable.trending_flat_ic));
                        }
                    }
                    TextView textView9 = newMyMarket.changePerc;
                    textView9.setText("(" + this.marketData.getChangePerc() + "%)");
                }
                if (this.marketData.isUpdateRow()) {
                    this.marketData = this.mMarketData.get(i);
                    setAnim(this.marketData.getHighLightedColor(), ((ColorDrawable) newMyMarket.cover.getBackground()).getColor(), newMyMarket);
                    this.marketData.setUpdateRow(false);
                }
            } else {
                TextView textView10 = newMyMarket.changePerc;
                textView10.setText("" + this.marketData.getChangePerc() + "%");
                if (this.marketData.getSymbolCode().length() > 8) {
                    newMyMarket.marketSymbolCode.setTextSize(1, 12.0f);
                } else {
                    newMyMarket.marketSymbolCode.setTextSize(1, 16.0f);
                }
            }
            TextView textView11 = newMyMarket.high;
            textView11.setText("Hi: " + this.marketData.getHigh());
            TextView textView12 = newMyMarket.low;
            textView12.setText("Lo: " + this.marketData.getLow());
            if (this.marketData.getLastVolume() != null) {
                this.marketData.getLastVolume().equals("0.00");
            }
            if (this.isClickAble.booleanValue()) {
                newMyMarket.buy_sell_layout.setOnClickListener(new View.OnClickListener() { // from class: munirkhanani.adapters.MarketWatchAdapter.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        try {
                            MarketWatchAdapter marketWatchAdapter = MarketWatchAdapter.this;
                            marketWatchAdapter.marketData = marketWatchAdapter.mMarketData.get(i);
                            Constants.marketWatchSelectedPosition = i;
                            OrderTicketFrags orderTicketFrags = new OrderTicketFrags();
                            Bundle bundle = new Bundle();
                            bundle.putString("marketSym", MarketWatchAdapter.this.marketData.getSymbolCode());
                            bundle.putString(DbContract.marketCode.TABLE_NAME, MarketWatchAdapter.this.marketData.getMarketCode());
                            if (MarketWatchAdapter.this.marketData.getSymbolName() != null) {
                                bundle.putString("marketSymName", MarketWatchAdapter.this.marketData.getSymbolName());
                            } else {
                                bundle.putString("marketSymName", "");
                            }
                            bundle.putString("marketBidPri", MarketWatchAdapter.this.marketData.getBuyPri());
                            bundle.putString("marketBidVol", MarketWatchAdapter.this.marketData.getBuyVol());
                            bundle.putString("marketSellPri", MarketWatchAdapter.this.marketData.getSellPri());
                            bundle.putString("marketSellVol", MarketWatchAdapter.this.marketData.getSellVol());
                            bundle.putString("marketLastTradeVol", MarketWatchAdapter.this.marketData.getLastVolume());
                            bundle.putString("marketLastPrice", MarketWatchAdapter.this.marketData.getLastPrice());
                            bundle.putString("marketNetChange", "" + MarketWatchAdapter.this.marketData.getNetChange());
                            bundle.putString("marketChangePerc", "" + MarketWatchAdapter.this.marketData.getChangePerc());
                            bundle.putString("marketHigh", MarketWatchAdapter.this.marketData.getHigh());
                            bundle.putString("marketLow", MarketWatchAdapter.this.marketData.getLow());
                            bundle.putString("buy", "BUY");
                            orderTicketFrags.setArguments(bundle);
                            ((AppCompatActivity) MarketWatchAdapter.this.context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, orderTicketFrags).addToBackStack("fragment").commit();
                        } catch (Exception unused) {
                        }
                    }
                });
                newMyMarket.marketSymbolName.setOnClickListener(new View.OnClickListener() { // from class: munirkhanani.adapters.MarketWatchAdapter.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        try {
                            MarketWatchAdapter marketWatchAdapter = MarketWatchAdapter.this;
                            marketWatchAdapter.marketData = marketWatchAdapter.mMarketData.get(i);
                            Constants.marketWatchSelectedPosition = i;
                            final Tooltip build = new Tooltip.Builder(view, (int) R.style.Tooltip2).setCancelable(true).setDismissOnClick(true).setBackgroundColor(MarketWatchAdapter.this.context.getResources().getColor(R.color.white)).setCornerRadius(20.0f).setGravity(80).setText(MarketWatchAdapter.this.marketData.getSymbolName() != null ? MarketWatchAdapter.this.marketData.getSymbolName() : "").build();
                            build.show();
                            new Handler().postDelayed(new Runnable() { // from class: munirkhanani.adapters.MarketWatchAdapter.3.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    build.dismiss();
                                }
                            }, 2000L);
                        } catch (Exception unused) {
                        }
                    }
                });
                newMyMarket.marketSymbolCode.setOnClickListener(new View.OnClickListener() { // from class: munirkhanani.adapters.MarketWatchAdapter.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        try {
                            Constants.marketWatchSelectedPosition = i;
                            MarketWatchAdapter marketWatchAdapter = MarketWatchAdapter.this;
                            marketWatchAdapter.marketData = marketWatchAdapter.mMarketData.get(i);
                            Mbo_Mbp_Frag mbo_Mbp_Frag = new Mbo_Mbp_Frag();
                            Bundle bundle = new Bundle();
                            bundle.putString("marketSym", MarketWatchAdapter.this.marketData.getSymbolCode());
                            bundle.putString(DbContract.marketCode.TABLE_NAME, MarketWatchAdapter.this.marketData.getMarketCode());
                            if (MarketWatchAdapter.this.marketData.getSymbolName() != null) {
                                bundle.putString("marketSymName", MarketWatchAdapter.this.marketData.getSymbolName());
                            } else {
                                bundle.putString("marketSymName", "");
                            }
                            bundle.putString("marketBidPri", MarketWatchAdapter.this.marketData.getBuyPri());
                            bundle.putString("marketBidVol", MarketWatchAdapter.this.marketData.getBuyVol());
                            bundle.putString("marketSellPri", MarketWatchAdapter.this.marketData.getSellPri());
                            bundle.putString("marketSellVol", MarketWatchAdapter.this.marketData.getSellVol());
                            bundle.putString("marketLastTradeVol", MarketWatchAdapter.this.marketData.getLastVolume());
                            bundle.putString("marketLastPrice", MarketWatchAdapter.this.marketData.getLastPrice());
                            bundle.putString("marketNetChange", "" + MarketWatchAdapter.this.marketData.getNetChange());
                            bundle.putString("marketChangePerc", "" + MarketWatchAdapter.this.marketData.getChangePerc());
                            bundle.putString("marketHigh", MarketWatchAdapter.this.marketData.getHigh());
                            bundle.putString("marketLow", MarketWatchAdapter.this.marketData.getLow());
                            bundle.putString("sell_bg", "SELL");
                            mbo_Mbp_Frag.setArguments(bundle);
                            ((AppCompatActivity) MarketWatchAdapter.this.context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mbo_Mbp_Frag).addToBackStack("fragment").commit();
                        } catch (Exception unused) {
                        }
                    }
                });
            }
            if (this.marketData.isUpdateRow() && Constants.isFullRowBlink.booleanValue()) {
                this.marketData = this.mMarketData.get(i);
                setAnim(this.marketData.getHighLightedColor(), ((ColorDrawable) newMyMarket.cover.getBackground()).getColor(), newMyMarket);
                this.marketData.setUpdateRow(false);
            }
        } catch (Exception e) {
            Log.d("Exception", e.getMessage());
        }
    }

    private void callingAnimation(final int i, final TextView textView, String str, String str2) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: munirkhanani.adapters.MarketWatchAdapter.5
            @Override // java.lang.Runnable
            public void run() {
                MarketWatchAdapter marketWatchAdapter = MarketWatchAdapter.this;
                marketWatchAdapter.setAnimonTextView(i, marketWatchAdapter.context.getResources().getColor(R.color.trans), textView);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAnimonTextView(final int i, final int i2, final TextView textView) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: munirkhanani.adapters.MarketWatchAdapter.6
            @Override // java.lang.Runnable
            public void run() {
                MarketWatchAdapter.this.colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), Integer.valueOf(i), Integer.valueOf(i2));
                MarketWatchAdapter.this.colorAnimation.setDuration(6000L);
                MarketWatchAdapter.this.colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: munirkhanani.adapters.MarketWatchAdapter.6.1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        textView.setBackgroundColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
                    }
                });
                MarketWatchAdapter.this.colorAnimation.start();
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<Market> arrayList = this.mMarketData;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    /* loaded from: classes2.dex */
    public class NewMyMarket extends RecyclerView.ViewHolder {
        LinearLayout bottomView;
        LinearLayout buyLayout;
        TextView buyPrice;
        TextView buyVolume;
        public RelativeLayout buy_sell_layout;
        TextView changePerc;
        LinearLayout cover;
        TextView high;
        ImageView iv_marketTrend;
        ImageView iv_mosque;
        TextView lastTradeVol;
        TextView low;
        TextView lowerPri;
        TextView marketLastPrice;
        TextView marketMarketCode;
        TextView marketNetChange;
        TextView marketSymbolCode;
        TextView marketSymbolName;
        LinearLayout sellLayout;
        TextView sellPrice;
        TextView sellVolume;
        TextView upperPri;
        public RelativeLayout viewBackground;
        public RelativeLayout viewForeground;

        public NewMyMarket(View view) {
            super(view);
            this.viewBackground = (RelativeLayout) view.findViewById(R.id.view_background);
            this.viewForeground = (RelativeLayout) view.findViewById(R.id.view_foreground);
            this.buy_sell_layout = (RelativeLayout) view.findViewById(R.id.buy_sell_layout);
            this.cover = (LinearLayout) view.findViewById(R.id.cover);
            this.bottomView = (LinearLayout) view.findViewById(R.id.bottomView);
            this.buyLayout = (LinearLayout) view.findViewById(R.id.buyLayout);
            this.sellLayout = (LinearLayout) view.findViewById(R.id.sellLayout);
            this.high = (TextView) view.findViewById(R.id.high);
            this.low = (TextView) view.findViewById(R.id.low);
            this.marketSymbolCode = (TextView) view.findViewById(R.id.symbolCode);
            this.marketSymbolName = (TextView) view.findViewById(R.id.symbolName);
            this.marketMarketCode = (TextView) view.findViewById(R.id.marketCode);
            this.marketLastPrice = (TextView) view.findViewById(R.id.lastPri);
            this.buyPrice = (TextView) view.findViewById(R.id.buyPri);
            this.buyVolume = (TextView) view.findViewById(R.id.buyVol);
            this.sellPrice = (TextView) view.findViewById(R.id.sellPri);
            this.sellVolume = (TextView) view.findViewById(R.id.sellVol);
            this.marketNetChange = (TextView) view.findViewById(R.id.marketNetChange);
            this.changePerc = (TextView) view.findViewById(R.id.changePerc);
            this.lastTradeVol = (TextView) view.findViewById(R.id.totalVol);
            this.upperPri = (TextView) view.findViewById(R.id.upperPri);
            this.lowerPri = (TextView) view.findViewById(R.id.lowerPri);
            this.iv_mosque = (ImageView) view.findViewById(R.id.iv_mosque);
            this.iv_marketTrend = (ImageView) view.findViewById(R.id.iv_marketTrend);
        }
    }

    public void removeItem(int i) {
        this.mMarketData.remove(i);
        notifyItemRemoved(i);
    }

    public void restoreItem(Market market, int i) {
        this.mMarketData.add(i, market);
        notifyItemInserted(i);
    }

    public void addItem() {
        notifyDataSetChanged();
    }
}
