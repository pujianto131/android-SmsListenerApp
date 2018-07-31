package com.pujianto131.smslistenerapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.print.PrinterId;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnDownload;
    public static final String ACTION_DOWNLOAD_STATUS = "download_status";
    private BroadcastReceiver downloadReciver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("inComming Message");
        btnDownload = (Button)findViewById(R.id.btn_download);
        btnDownload.setOnClickListener(this);
        downloadReciver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Download Selesai", Toast.LENGTH_SHORT).show();
            }
        };
        IntentFilter dowloadFilter = new IntentFilter(ACTION_DOWNLOAD_STATUS);
        registerReceiver(downloadReciver,dowloadFilter);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_download){
            Intent downloadIntent = new Intent(this, DownloadService.class);
            startService(downloadIntent);

        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (downloadReciver != null){
            unregisterReceiver(downloadReciver);
        }
    }
}
