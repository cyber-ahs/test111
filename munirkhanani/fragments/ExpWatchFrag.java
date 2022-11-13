package munirkhanani.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.microlinks.Munirkhanani.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import munirkhanani.activities.LoginActivity;
import munirkhanani.activities.MenuActivity;
import munirkhanani.adapters.ClientCodeListAdapter;
import munirkhanani.helpers.Constants;
import munirkhanani.helpers.FragmentCategoryExpWatch;
import munirkhanani.helpers.WrapContentLinearLayoutManager;
import munirkhanani.interfaces.SendHeaderName;
import munirkhanani.interfaces.ShowMessageAccountPosition;
import munirkhanani.model.BuyingPowerModel;
import munirkhanani.model.ClientsCodeModel;
import munirkhanani.model.OpenPositionModel;
import munirkhanani.model.ViewAccountInfoModel;
import munirkhanani.model.ViewSummaryModel;
import munirkhanani.model.ViewSummaryModelFour;
import munirkhanani.model.ViewSummaryModelThree;
import munirkhanani.model.ViewSummaryModelTwo;
import munirkhanani.network.HttpHandler;
/* loaded from: classes2.dex */
public class ExpWatchFrag extends Fragment {
    public static String clientCodeItem;
    EditText accountClientsCode;
    ClientCodeListAdapter clientCodeListAdapter;
    String defaultClientCode;
    ProgressDialog dialogLoadExp;
    private FragmentCategoryExpWatch fc;
    RecyclerView recyclerViewClientCodeList;
    Button refreshBtn;
    private SendHeaderName sendHeaderName;
    ShowMessageAccountPosition showMessageAccountPosition;
    SharedPreferences userDefaultAccountPrefs;
    private ViewPager viewPager;
    public static List<OpenPositionModel> OpenPositionArrayList = new ArrayList();
    public static List<ViewSummaryModel> viewSummaryModelList = new ArrayList();
    public static List<ViewSummaryModelTwo> viewSummaryModelListTwo = new ArrayList();
    public static List<ViewSummaryModelThree> viewSummaryModelListThree = new ArrayList();
    public static List<ViewSummaryModelFour> viewSummaryModelListFour = new ArrayList();
    public static List<BuyingPowerData> buyingPowerDataArrayList = new ArrayList();
    public static List<ViewAccountInfoModel> viewAccountInfoModels = new ArrayList();
    private boolean canPushOrder = false;
    private Boolean isShowActivity = false;
    private TextWatcher filterTextWatcherViewPosition = new TextWatcher() { // from class: munirkhanani.fragments.ExpWatchFrag.3
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (ExpWatchFrag.this.clientCodeListAdapter != null) {
                ExpWatchFrag.this.clientCodeListAdapter.getFilter().filter(charSequence.toString());
            }
            if (ExpWatchFrag.this.accountClientsCode.getText().toString().isEmpty()) {
                ExpWatchFrag.this.refreshBtn.setEnabled(false);
                ExpWatchFrag.this.refreshBtn.setBackgroundDrawable(ExpWatchFrag.this.getResources().getDrawable(R.drawable.disable_icon));
                return;
            }
            ExpWatchFrag.this.refreshBtn.setBackgroundDrawable(ExpWatchFrag.this.getResources().getDrawable(R.drawable.refresh_icon));
            ExpWatchFrag.this.refreshBtn.setEnabled(true);
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            String obj = editable.toString();
            if (obj.equals("") || obj.isEmpty()) {
                ExpWatchFrag.this.recyclerViewClientCodeList.setVisibility(8);
            } else if (LoginActivity.staticClientCode != null) {
                ExpWatchFrag.this.recyclerViewClientCodeList.setVisibility(8);
            } else {
                ExpWatchFrag.this.recyclerViewClientCodeList.setVisibility(0);
            }
        }
    };
    private ClientCodeListAdapter.OnItemClickListener clickListener = new ClientCodeListAdapter.OnItemClickListener() { // from class: munirkhanani.fragments.ExpWatchFrag.5
        @Override // munirkhanani.adapters.ClientCodeListAdapter.OnItemClickListener
        public void onItemClick(ClientsCodeModel clientsCodeModel) {
            ExpWatchFrag.clientCodeItem = clientsCodeModel.getClientsCode();
            ExpWatchFrag.this.accountClientsCode.setText(ExpWatchFrag.clientCodeItem);
            ExpWatchFrag.this.recyclerViewClientCodeList.setVisibility(8);
            ExpWatchFrag.this.accountClientsCode.clearFocus();
            if (!ExpWatchFrag.this.accountClientsCode.getText().toString().equals("")) {
                ExpWatchFrag.this.closeKeyboard();
            }
            ExpWatchFrag.this.accountClientsCode.setBackground(ExpWatchFrag.this.getResources().getDrawable(R.drawable.cutome_watch_border));
            if (ExpWatchFrag.this.showMessageAccountPosition != null) {
                ExpWatchFrag.this.getExpDataFromApi(ExpWatchFrag.clientCodeItem);
            }
        }
    };

    /* loaded from: classes2.dex */
    public static class BuyingPowerData {
        public BuyingPowerModel FirstObject;
        public BuyingPowerModel LastObject;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_exp_watch, viewGroup, false);
        try {
            this.fc = new FragmentCategoryExpWatch(getActivity(), getFragmentManager());
            this.viewPager = (ViewPager) inflate.findViewById(R.id.viewPagerExpWatch);
            ((TabLayout) inflate.findViewById(R.id.tabsExpWatch)).setupWithViewPager(this.viewPager);
            this.viewPager.setOffscreenPageLimit(5);
            this.viewPager.setAdapter(this.fc);
            this.refreshBtn = (Button) inflate.findViewById(R.id.refreshBtn);
            EditText editText = (EditText) inflate.findViewById(R.id.accountClientsCodeView);
            this.accountClientsCode = editText;
            editText.setAllCaps(true);
            this.accountClientsCode.addTextChangedListener(this.filterTextWatcherViewPosition);
            this.recyclerViewClientCodeList = (RecyclerView) inflate.findViewById(R.id.clientsCodeListRecyclerView);
            viewSummaryModelList.clear();
            viewSummaryModelListTwo.clear();
            viewSummaryModelListThree.clear();
            viewSummaryModelListFour.clear();
            buyingPowerDataArrayList.clear();
            viewAccountInfoModels.clear();
            this.refreshBtn.setOnClickListener(new View.OnClickListener() { // from class: munirkhanani.fragments.ExpWatchFrag.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Constants.accountPositionSelectedPosition = 0;
                    if (!ExpWatchFrag.this.accountClientsCode.getText().toString().equals("")) {
                        ExpWatchFrag.this.closeKeyboard();
                    }
                    ExpWatchFrag.this.call();
                }
            });
        } catch (Exception unused) {
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString("Exposure");
            String string2 = arguments.getString("clientCodeItem");
            Log.e("", "clientCodeItem" + clientCodeItem + "" + string);
            this.clientCodeListAdapter = new ClientCodeListAdapter(getActivity(), LoginActivity.clientCodesList, this.clickListener);
            this.recyclerViewClientCodeList.setNestedScrollingEnabled(true);
            this.recyclerViewClientCodeList.setLayoutManager(new WrapContentLinearLayoutManager(getActivity(), 1, false));
            this.recyclerViewClientCodeList.setAdapter(this.clientCodeListAdapter);
            this.accountClientsCode.setEnabled(true);
            if (string.equals("Loaded")) {
                this.viewPager.setCurrentItem(1);
                this.accountClientsCode.setText(string2);
                this.recyclerViewClientCodeList.setVisibility(8);
            } else {
                Log.e("", "clientCodeItem" + clientCodeItem + "" + string);
            }
        } else {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserDefaultAccount", 0);
            this.userDefaultAccountPrefs = sharedPreferences;
            this.defaultClientCode = sharedPreferences.getString("userDefaultAccount", "");
            Log.e("", "defaultClientCode" + this.defaultClientCode);
            getDataFromLogin();
        }
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        this.dialogLoadExp = progressDialog;
        progressDialog.setCancelable(false);
        if (!this.accountClientsCode.getText().toString().equals("")) {
            call();
            this.accountClientsCode.selectAll();
        } else {
            this.accountClientsCode.requestFocus();
        }
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void call() {
        closeKeyboard();
        String obj = this.accountClientsCode.getText().toString();
        clientCodeItem = obj;
        if (obj.isEmpty()) {
            ordersAlert("Account Required !", "");
            this.refreshBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.disable_icon));
            this.accountClientsCode.setBackground(getResources().getDrawable(R.drawable.error_border));
            this.canPushOrder = false;
        } else if (isClientCodeValid(clientCodeItem)) {
            this.accountClientsCode.setBackground(getResources().getDrawable(R.drawable.cutome_watch_border));
            this.canPushOrder = true;
            this.refreshBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.refresh_icon));
            this.accountClientsCode.setBackground(getResources().getDrawable(R.drawable.cutome_watch_border));
            if (!this.canPushOrder || this.showMessageAccountPosition == null) {
                return;
            }
            getExpDataFromApi(clientCodeItem);
            closeKeyboard();
        } else {
            this.canPushOrder = false;
            ordersAlert(" Account is not Valid !", "");
            this.accountClientsCode.setBackground(getResources().getDrawable(R.drawable.error_border));
        }
    }

    private void ordersAlert(String str, String str2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.msg_alerts, (ViewGroup) null);
        builder.setView(inflate);
        final AlertDialog create = builder.create();
        create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        ((TextView) inflate.findViewById(R.id.subtitleHead)).setText(str + "\n" + str2);
        create.show();
        new Handler().postDelayed(new Runnable() { // from class: munirkhanani.fragments.ExpWatchFrag.2
            @Override // java.lang.Runnable
            public void run() {
                AlertDialog alertDialog = create;
                if (alertDialog == null || !alertDialog.isShowing()) {
                    return;
                }
                create.dismiss();
            }
        }, 3000L);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.showMessageAccountPosition = (ShowMessageAccountPosition) activity;
        setHeaderName((MenuActivity) getActivity());
    }

    public void setHeaderName(SendHeaderName sendHeaderName) {
        this.sendHeaderName = sendHeaderName;
        sendHeaderName.headerDataSet("Account Position", 0);
    }

    private void getDataFromLogin() {
        this.clientCodeListAdapter = new ClientCodeListAdapter(getActivity(), LoginActivity.clientCodesList, this.clickListener);
        this.recyclerViewClientCodeList.setNestedScrollingEnabled(true);
        this.recyclerViewClientCodeList.setLayoutManager(new WrapContentLinearLayoutManager(getActivity(), 1, false));
        this.recyclerViewClientCodeList.setAdapter(this.clientCodeListAdapter);
        this.accountClientsCode.setEnabled(true);
        if (LoginActivity.staticClientCode == null) {
            Log.e("", "defaultClientCode" + this.defaultClientCode);
            this.accountClientsCode.setText(this.defaultClientCode);
            this.recyclerViewClientCodeList.setVisibility(8);
            this.accountClientsCode.setEnabled(true);
            closeKeyboard();
            return;
        }
        this.accountClientsCode.setText(LoginActivity.staticClientCode);
        clientCodeItem = LoginActivity.staticClientCode;
        this.accountClientsCode.setEnabled(false);
        if (LoginActivity.clientCodesList.size() == 0 && LoginActivity.staticClientCode == null) {
            this.accountClientsCode.setEnabled(false);
            closeKeyboard();
            this.accountClientsCode.setBackground(getResources().getDrawable(R.drawable.cutome_save_disable));
        }
    }

    private boolean isClientCodeValid(String str) {
        try {
            return LoginActivity.clientsCodeModelHashMap.containsKey(str);
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getExpDataFromApi(final String str) {
        new HttpHandler(getContext());
        ProgressDialog progressDialog = this.dialogLoadExp;
        if (progressDialog != null) {
            progressDialog.show();
        }
        new HashMap().put("account", str);
        requireActivity().runOnUiThread(new Runnable() { // from class: munirkhanani.fragments.ExpWatchFrag.4
            @Override // java.lang.Runnable
            public void run() {
                new GetClientExposure().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, str);
            }
        });
    }

    /* loaded from: classes2.dex */
    private class GetClientExposure extends AsyncTask<String, Void, Void> {
        private GetClientExposure() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(String... strArr) {
            try {
                String str = strArr[0];
                HttpHandler httpHandler = new HttpHandler(ExpWatchFrag.this.getContext());
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("account", str);
                String GetRequestHandler = httpHandler.GetRequestHandler(Constants.getClientExposureUrl, hashMap);
                Log.d("getclientExposure_API", GetRequestHandler);
                ExpWatchFrag.this.showMessageAccountPosition.onShowMessagesAccountPosition(GetRequestHandler);
                ExpWatchFrag.this.dialogLoadExp.dismiss();
                if (ExpWatchFrag.this.accountClientsCode == null || !ExpWatchFrag.this.accountClientsCode.getText().toString().equals("")) {
                    return null;
                }
                ExpWatchFrag.this.closeKeyboard();
                return null;
            } catch (Exception unused) {
                return null;
            }
        }
    }

    public void showSoftKeyboard() {
        try {
            ((InputMethodManager) getContext().getSystemService("input_method")).toggleSoftInput(2, 0);
        } catch (Exception unused) {
        }
    }

    public void closeKeyboard() {
        try {
            ((InputMethodManager) getContext().getSystemService("input_method")).toggleSoftInput(1, 0);
        } catch (Exception unused) {
        }
    }
}
