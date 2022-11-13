package munirkhanani.fragments;

import android.app.ProgressDialog;
import android.webkit.WebView;
import android.webkit.WebViewClient;
/* compiled from: AnalyticsFrag.java */
/* loaded from: classes2.dex */
class MyWebViewClient extends WebViewClient {
    private ProgressDialog progressDialog;

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        webView.loadUrl(str);
        return true;
    }

    public MyWebViewClient(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    public MyWebViewClient() {
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        try {
            ProgressDialog progressDialog = this.progressDialog;
            if (progressDialog == null || !progressDialog.isShowing()) {
                return;
            }
            this.progressDialog.dismiss();
        } catch (Exception unused) {
        }
    }
}
