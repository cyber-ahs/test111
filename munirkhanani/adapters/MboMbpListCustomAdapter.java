package munirkhanani.adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.microlinks.Munirkhanani.R;
import java.util.List;
import munirkhanani.dbmodel.DBHelper;
import munirkhanani.interfaces.OnItemClickListener;
import munirkhanani.model.MboMbpCustomModel;
/* loaded from: classes2.dex */
public class MboMbpListCustomAdapter extends RecyclerView.Adapter<MyMarket> {
    private Context context;
    private DBHelper dbHelper;
    private int intOldSelectedItem = 0;
    private OnItemClickListener listener;
    public SQLiteDatabase mDb;
    private MboMbpCustomModel mboMbpCustomModel;
    private List<MboMbpCustomModel> mboMbpCustomModelList;

    public MboMbpListCustomAdapter(Context context, OnItemClickListener onItemClickListener, List<MboMbpCustomModel> list) {
        this.context = context;
        this.mboMbpCustomModelList = list;
        this.listener = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        return new MyMarket(layoutInflater != null ? layoutInflater.inflate(R.layout.mbp_mbo_list_item_custom, viewGroup, false) : null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final MyMarket myMarket, final int i) {
        try {
            this.mboMbpCustomModel = this.mboMbpCustomModelList.get(i);
            myMarket.showSymbolItem.setText(this.mboMbpCustomModel.getSymName());
            int i2 = this.intOldSelectedItem;
            if (i2 == i) {
                if (i2 == 0) {
                    myMarket.showSymbolItem.setBackground(this.context.getResources().getDrawable(R.drawable.custom_save));
                    myMarket.showSymbolItem.setTextColor(this.context.getResources().getColor(R.color.white));
                    this.listener.onItemClick(this.mboMbpCustomModel, i);
                }
                myMarket.showSymbolItem.setBackground(this.context.getResources().getDrawable(R.drawable.custom_save));
                myMarket.showSymbolItem.setTextColor(this.context.getResources().getColor(R.color.white));
            } else {
                myMarket.showSymbolItem.setBackground(this.context.getResources().getDrawable(R.drawable.cutome_edittext));
                myMarket.showSymbolItem.setTextColor(this.context.getResources().getColor(R.color.black));
            }
            myMarket.showSymbolItem.setOnClickListener(new View.OnClickListener() { // from class: munirkhanani.adapters.MboMbpListCustomAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    try {
                        MboMbpListCustomAdapter mboMbpListCustomAdapter = MboMbpListCustomAdapter.this;
                        mboMbpListCustomAdapter.mboMbpCustomModel = (MboMbpCustomModel) mboMbpListCustomAdapter.mboMbpCustomModelList.get(i);
                        MboMbpListCustomAdapter.this.intOldSelectedItem = myMarket.getLayoutPosition();
                        MboMbpListCustomAdapter.this.listener.onItemClick(MboMbpListCustomAdapter.this.mboMbpCustomModel, i);
                    } catch (IndexOutOfBoundsException unused) {
                        MboMbpListCustomAdapter.this.notifyDataSetChanged();
                    }
                    MboMbpListCustomAdapter.this.notifyDataSetChanged();
                }
            });
            myMarket.redcrossicon.setOnClickListener(new View.OnClickListener() { // from class: munirkhanani.adapters.MboMbpListCustomAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    try {
                        if (MboMbpListCustomAdapter.this.mboMbpCustomModelList == null || MboMbpListCustomAdapter.this.mboMbpCustomModelList.size() <= 1) {
                            return;
                        }
                        MboMbpListCustomAdapter mboMbpListCustomAdapter = MboMbpListCustomAdapter.this;
                        mboMbpListCustomAdapter.mboMbpCustomModel = (MboMbpCustomModel) mboMbpListCustomAdapter.mboMbpCustomModelList.get(i);
                        MboMbpListCustomAdapter.this.listener.onItemClick(MboMbpListCustomAdapter.this.mboMbpCustomModel, i);
                        myMarket.showSymbolItem.performClick();
                        MboMbpListCustomAdapter.this.dbHelper = new DBHelper(MboMbpListCustomAdapter.this.context);
                        MboMbpListCustomAdapter mboMbpListCustomAdapter2 = MboMbpListCustomAdapter.this;
                        mboMbpListCustomAdapter2.mDb = mboMbpListCustomAdapter2.dbHelper.getWritableDatabase();
                        SQLiteDatabase sQLiteDatabase = MboMbpListCustomAdapter.this.mDb;
                        sQLiteDatabase.execSQL("DELETE FROM mbombpCustomList WHERE SYMBOL_CODE='" + MboMbpListCustomAdapter.this.mboMbpCustomModel.getSymName() + "'");
                        MboMbpListCustomAdapter.this.mDb.close();
                        MboMbpListCustomAdapter.this.dbHelper.close();
                        MboMbpListCustomAdapter.this.removeItem(i);
                    } catch (IndexOutOfBoundsException unused) {
                        MboMbpListCustomAdapter.this.notifyDataSetChanged();
                    }
                }
            });
        } catch (Exception unused) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MboMbpCustomModel> list = this.mboMbpCustomModelList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* loaded from: classes2.dex */
    public class MyMarket extends RecyclerView.ViewHolder {
        ImageView redcrossicon;
        TextView showSymbolItem;

        public MyMarket(View view) {
            super(view);
            this.showSymbolItem = (TextView) view.findViewById(R.id.showSymbolItem);
            this.redcrossicon = (ImageView) view.findViewById(R.id.redCrossIcon);
        }
    }

    public void setOnClick(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }

    public void removeItem(int i) {
        this.mboMbpCustomModelList.remove(i);
        notifyItemRemoved(i);
    }
}
