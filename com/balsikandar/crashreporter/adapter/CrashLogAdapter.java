package com.balsikandar.crashreporter.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.balsikandar.crashreporter.R;
import com.balsikandar.crashreporter.ui.LogMessageActivity;
import com.balsikandar.crashreporter.utils.FileUtils;
import java.io.File;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class CrashLogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<File> crashFileList;

    public CrashLogAdapter(Context context, ArrayList<File> arrayList) {
        this.context = context;
        this.crashFileList = arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CrashLogViewHolder(LayoutInflater.from(this.context).inflate(R.layout.custom_item, (ViewGroup) null));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((CrashLogViewHolder) viewHolder).setUpViewHolder(this.context, this.crashFileList.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.crashFileList.size();
    }

    public void updateList(ArrayList<File> arrayList) {
        this.crashFileList = arrayList;
        notifyDataSetChanged();
    }

    /* loaded from: classes.dex */
    private class CrashLogViewHolder extends RecyclerView.ViewHolder {
        private TextView messageLogTime;
        private TextView textViewMsg;

        CrashLogViewHolder(View view) {
            super(view);
            this.messageLogTime = (TextView) view.findViewById(R.id.messageLogTime);
            this.textViewMsg = (TextView) view.findViewById(R.id.textViewMsg);
        }

        void setUpViewHolder(final Context context, File file) {
            final String absolutePath = file.getAbsolutePath();
            this.messageLogTime.setText(file.getName().replaceAll("[a-zA-Z_.]", ""));
            this.textViewMsg.setText(FileUtils.readFirstLineFromFile(new File(absolutePath)));
            this.textViewMsg.setOnClickListener(new View.OnClickListener() { // from class: com.balsikandar.crashreporter.adapter.CrashLogAdapter.CrashLogViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Intent intent = new Intent(context, LogMessageActivity.class);
                    intent.putExtra("LogMessage", absolutePath);
                    context.startActivity(intent);
                }
            });
        }
    }
}
