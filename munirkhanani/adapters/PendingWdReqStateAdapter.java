package munirkhanani.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.androidnetworking.common.ANConstants;
import com.balsikandar.crashreporter.CrashReporter;
import com.microlinks.Munirkhanani.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executor;
import munirkhanani.model.PendingWdReqStateModel;
import munirkhanani.network.HttpHandler;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class PendingWdReqStateAdapter extends RecyclerView.Adapter<MyMarket> {
    private String accountNo;
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<PendingWdReqStateModel> pendingWdReqStateModelArrayList;
    private String pinCodeNo;
    private int redColor;
    private int selectedPosition = 0;

    public PendingWdReqStateAdapter(Context context, ArrayList<PendingWdReqStateModel> arrayList, String str, String str2) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.pendingWdReqStateModelArrayList = arrayList;
        this.accountNo = str;
        this.pinCodeNo = str2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyMarket onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyMarket(this.inflater.inflate(R.layout.pending_wd_req_list_item, viewGroup, false));
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x00c7, code lost:
        if (r3 == 1) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00c9, code lost:
        if (r3 == 2) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00cb, code lost:
        if (r3 == 3) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00cd, code lost:
        if (r3 == 4) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00d0, code lost:
        r11.cashCancel.setVisibility(0);
        r11.cashCancel.setEnabled(true);
     */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onBindViewHolder(MyMarket myMarket, final int i) {
        try {
            this.redColor = this.context.getResources().getColor(R.color.negative_text_color);
            final PendingWdReqStateModel pendingWdReqStateModel = this.pendingWdReqStateModelArrayList.get(i);
            if (i % 2 == 1) {
                myMarket.cover.setBackgroundColor(this.context.getResources().getColor(R.color.alternate));
            } else {
                myMarket.cover.setBackgroundColor(this.context.getResources().getColor(R.color.white));
            }
            myMarket.penWdReqDate.setText(pendingWdReqStateModel.getDate());
            myMarket.penWdReqAmountReq.setText(pendingWdReqStateModel.getAmountReq());
            myMarket.penWdReqAmountApproved.setText(pendingWdReqStateModel.getAmountAppr());
            String status = pendingWdReqStateModel.getStatus();
            myMarket.tv_status.setText(setStatusValue(status));
            myMarket.tv_status.setTextColor(setStatusColor(pendingWdReqStateModel.getStatus()));
            char c = 65535;
            int hashCode = status.hashCode();
            if (hashCode != 65) {
                if (hashCode != 67) {
                    if (hashCode != 78) {
                        if (hashCode != 80) {
                            if (hashCode == 82 && status.equals("R")) {
                                c = 2;
                            }
                        } else if (status.equals("P")) {
                            c = 4;
                        }
                    } else if (status.equals("N")) {
                        c = 3;
                    }
                } else if (status.equals("C")) {
                    c = 1;
                }
            } else if (status.equals("A")) {
                c = 0;
            }
            myMarket.cashCancel.setVisibility(4);
            myMarket.cashCancel.setEnabled(false);
            myMarket.cashCancel.setOnClickListener(new View.OnClickListener() { // from class: munirkhanani.adapters.PendingWdReqStateAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    cancelPaymentRequest cancelpaymentrequest = new cancelPaymentRequest();
                    Executor executor = AsyncTask.THREAD_POOL_EXECUTOR;
                    cancelpaymentrequest.executeOnExecutor(executor, pendingWdReqStateModel.getSerial_number(), PendingWdReqStateAdapter.this.accountNo, PendingWdReqStateAdapter.this.pinCodeNo, i + "");
                }
            });
        } catch (Exception unused) {
        }
    }

    /* loaded from: classes2.dex */
    public class cancelPaymentRequest extends AsyncTask<String, Void, String> {
        public cancelPaymentRequest() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(String... strArr) {
            try {
                String str = strArr[0];
                String str2 = strArr[1];
                String str3 = strArr[2];
                int parseInt = Integer.parseInt(strArr[3]);
                HttpHandler httpHandler = new HttpHandler(CrashReporter.getContext());
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("sNo", str);
                hashMap.put("account", str2);
                hashMap.put("pin", str3);
                String GetRequestHandler = httpHandler.GetRequestHandler("cancelPayRequest", hashMap);
                if (GetRequestHandler != null) {
                    JSONObject jSONObject = new JSONObject(GetRequestHandler);
                    Boolean valueOf = Boolean.valueOf(jSONObject.getBoolean(ANConstants.SUCCESS));
                    PendingWdReqStateAdapter.this.selectedPosition = parseInt;
                    if (valueOf != null && valueOf.booleanValue()) {
                        PendingWdReqStateModel pendingWdReqStateModel = (PendingWdReqStateModel) PendingWdReqStateAdapter.this.pendingWdReqStateModelArrayList.get(parseInt);
                        pendingWdReqStateModel.setStatus("C");
                        PendingWdReqStateAdapter.this.pendingWdReqStateModelArrayList.set(parseInt, pendingWdReqStateModel);
                        return "";
                    }
                    String string = jSONObject.getString("msg");
                    PendingWdReqStateModel pendingWdReqStateModel2 = (PendingWdReqStateModel) PendingWdReqStateAdapter.this.pendingWdReqStateModelArrayList.get(parseInt);
                    if (string.contains("Cancelled")) {
                        pendingWdReqStateModel2.setStatus("C");
                    } else if (string.contains("Approved")) {
                        pendingWdReqStateModel2.setStatus("A");
                    } else if (string.contains("Rejected")) {
                        pendingWdReqStateModel2.setStatus("R");
                    }
                    PendingWdReqStateAdapter.this.pendingWdReqStateModelArrayList.set(parseInt, pendingWdReqStateModel2);
                    return "";
                }
                return "";
            } catch (Exception e) {
                Log.d("sd", e.getMessage());
                return "";
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            super.onPostExecute((cancelPaymentRequest) str);
            PendingWdReqStateAdapter pendingWdReqStateAdapter = PendingWdReqStateAdapter.this;
            pendingWdReqStateAdapter.notifyItemChanged(pendingWdReqStateAdapter.selectedPosition);
        }
    }

    /* loaded from: classes2.dex */
    private class cancelPaymentRequest2 extends AsyncTask<String, Void, Void> {
        private cancelPaymentRequest2() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(String... strArr) {
            Boolean valueOf;
            try {
                String str = strArr[0];
                String str2 = strArr[1];
                String str3 = strArr[2];
                HttpHandler httpHandler = new HttpHandler(CrashReporter.getContext());
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("sNo", str);
                hashMap.put("account", str2);
                hashMap.put("pin", str3);
                String GetRequestHandler = httpHandler.GetRequestHandler("cancelPayRequest", hashMap);
                if (GetRequestHandler == null || (valueOf = Boolean.valueOf(new JSONObject(GetRequestHandler).getBoolean(ANConstants.SUCCESS))) == null) {
                    return null;
                }
                valueOf.booleanValue();
                return null;
            } catch (Exception unused) {
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            super.onPostExecute((cancelPaymentRequest2) r1);
        }
    }

    private String setStatusValue(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 65:
                if (str.equals("A")) {
                    c = 0;
                    break;
                }
                break;
            case 67:
                if (str.equals("C")) {
                    c = 1;
                    break;
                }
                break;
            case 78:
                if (str.equals("N")) {
                    c = 2;
                    break;
                }
                break;
            case 80:
                if (str.equals("P")) {
                    c = 3;
                    break;
                }
                break;
            case 82:
                if (str.equals("R")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return "Approved";
            case 1:
                return "Canceled";
            case 2:
            case 3:
                return "Pending";
            case 4:
                return "Rejected";
            default:
                return "";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private int setStatusColor(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case 65:
                if (str.equals("A")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 67:
                if (str.equals("C")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 78:
                if (str.equals("N")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 80:
                if (str.equals("P")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 82:
                if (str.equals("R")) {
                    c = 4;
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
            case 2:
            case 3:
                return this.context.getResources().getColor(R.color.positive_text_color);
            case 1:
            case 4:
                return this.context.getResources().getColor(R.color.negative_text_color);
            default:
                return -1;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.pendingWdReqStateModelArrayList.size();
    }

    public void removeItem(int i) {
        this.pendingWdReqStateModelArrayList.remove(i);
        notifyItemRemoved(i);
    }

    /* loaded from: classes2.dex */
    public class MyMarket extends RecyclerView.ViewHolder {
        LinearLayout cashCancel;
        LinearLayout cover;
        TextView penWdReqAmountApproved;
        TextView penWdReqAmountReq;
        TextView penWdReqDate;
        TextView tv_status;

        public MyMarket(View view) {
            super(view);
            this.cover = (LinearLayout) view.findViewById(R.id.cover);
            this.cashCancel = (LinearLayout) view.findViewById(R.id.cashCancel);
            this.penWdReqDate = (TextView) view.findViewById(R.id.penWdReqDate);
            this.penWdReqAmountReq = (TextView) view.findViewById(R.id.penWdReqAmountReq);
            this.penWdReqAmountApproved = (TextView) view.findViewById(R.id.penWdReqAmountApproved);
            this.tv_status = (TextView) view.findViewById(R.id.tv_status);
        }
    }
}
