package munirkhanani.activities;

import android.app.ProgressDialog;
import android.webkit.WebView;
import android.webkit.WebViewClient;
/* compiled from: AccountOpeningActivity.java */
/* loaded from: classes2.dex */
class MyWebViewClient2 extends WebViewClient {
    private ProgressDialog progressDialog;
    private WebView simpleWebView;

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        webView.loadUrl(str);
        return true;
    }

    public MyWebViewClient2(ProgressDialog progressDialog, WebView webView) {
        this.progressDialog = progressDialog;
        this.simpleWebView = webView;
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    public MyWebViewClient2() {
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
            this.simpleWebView.setVisibility(0);
        } catch (Exception unused) {
        }
    }
}
