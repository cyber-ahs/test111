package munirkhanani.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.microlinks.Munirkhanani.R;
/* loaded from: classes2.dex */
public class CustomDialog {
    private Activity activity;
    private AlertDialog alertDialog;

    public CustomDialog(Activity activity) {
        this.activity = activity;
    }

    public void startLoadingDialog(String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        View inflate = this.activity.getLayoutInflater().inflate(R.layout.custom_dialog_progress, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.tv_progress_dialog)).setText(str);
        builder.setView(inflate);
        builder.setCancelable(false);
        AlertDialog create = builder.create();
        this.alertDialog = create;
        create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.alertDialog.show();
    }

    public void dismissDialog() {
        this.alertDialog.dismiss();
    }
}
