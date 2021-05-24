package com.example.batteryalarm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Ringtone ringtone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        ringtone= RingtoneManager.getRingtone(getApplicationContext(),
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));
        BroadcastReceiver batterylevel= new BroadcastReceiver() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onReceive(Context context, Intent intent) {
                Integer level= intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                textView.setText(level.toString());

                if( level>=95)
                ringtone.play();
                if(level>=20)
                    ringtone.play();


                getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.actionbar));
            }
        };
        registerReceiver(batterylevel,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    public void stopbt(View view){
        Toast.makeText(getApplicationContext(),"stop it",Toast.LENGTH_SHORT).show();
        ringtone.stop();
        ringtone=null;
    }
}