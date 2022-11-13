package munirkhanani.helpers;

import android.graphics.Canvas;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import munirkhanani.adapters.MarketWatchAdapter;
/* loaded from: classes2.dex */
public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private RecyclerItemTouchHelperListener listener;

    /* loaded from: classes2.dex */
    public interface RecyclerItemTouchHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int i, int i2);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        return true;
    }

    public RecyclerItemTouchHelper(int i, int i2, RecyclerItemTouchHelperListener recyclerItemTouchHelperListener) {
        super(i, i2);
        this.listener = recyclerItemTouchHelperListener;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder != null) {
            getDefaultUIUtil().onSelected(((MarketWatchAdapter.NewMyMarket) viewHolder).viewForeground);
        }
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onChildDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        getDefaultUIUtil().onDrawOver(canvas, recyclerView, ((MarketWatchAdapter.NewMyMarket) viewHolder).viewForeground, f, f2, i, z);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        getDefaultUIUtil().clearView(((MarketWatchAdapter.NewMyMarket) viewHolder).viewForeground);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        getDefaultUIUtil().onDraw(canvas, recyclerView, ((MarketWatchAdapter.NewMyMarket) viewHolder).viewForeground, f, f2, i, z);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        this.listener.onSwiped(viewHolder, i, viewHolder.getAdapterPosition());
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int convertToAbsoluteDirection(int i, int i2) {
        return super.convertToAbsoluteDirection(i, i2);
    }
}
