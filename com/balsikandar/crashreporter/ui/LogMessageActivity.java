package com.balsikandar.crashreporter.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.balsikandar.crashreporter.R;
import com.balsikandar.crashreporter.utils.AppUtils;
import com.balsikandar.crashreporter.utils.FileUtils;
import java.io.File;
/* loaded from: classes.dex */
public class LogMessageActivity extends AppCompatActivity {
    private TextView appInfo;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_log_message);
        this.appInfo = (TextView) findViewById(R.id.appInfo);
        Intent intent = getIntent();
        if (intent != null) {
            ((TextView) findViewById(R.id.logMessage)).setText(FileUtils.readFromFile(new File(intent.getStringExtra("LogMessage"))));
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.crash_reporter));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getAppInfo();
    }

    private void getAppInfo() {
        this.appInfo.setText(AppUtils.getDeviceDetails(this));
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.crash_detail_menu, menu);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intent intent = getIntent();
        String stringExtra = intent != null ? intent.getStringExtra("LogMessage") : null;
        if (menuItem.getItemId() == R.id.delete_log) {
            if (FileUtils.delete(stringExtra)) {
                finish();
            }
            return true;
        } else if (menuItem.getItemId() == R.id.share_crash_log) {
            shareCrashReport(stringExtra);
            return true;
        } else {
            return super.onOptionsItemSelected(menuItem);
        }
    }

    private void shareCrashReport(String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("*/*");
        intent.putExtra("android.intent.extra.TEXT", this.appInfo.getText().toString());
        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(str)));
        startActivity(Intent.createChooser(intent, "Share via"));
    }
}
