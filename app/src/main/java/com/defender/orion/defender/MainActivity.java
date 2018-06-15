package com.defender.orion.defender;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;



public class MainActivity extends Activity {

    ImageButton imageButton;
    ImageButton imageButton2;
    TextView textview;
    TextView textview2;
    Button button;
    IntentFilter intentfilter;
    int deviceStatus;
    String currentBatteryStatus="";
    int batteryLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textview = (TextView)findViewById(R.id.textViewBatteryStatus);
        textview2 = (TextView)findViewById(R.id.textViewStatus);
        intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        MainActivity.this.registerReceiver(broadcastreceiver,intentfilter);
        addListenerOnButton();
        addListenerOnButton2();

    }

    public void addListenerOnButton() {
        imageButton2 = (ImageButton) findViewById(R.id.imageButton);
        imageButton2.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getBaseContext(),   clean.class);
                startActivity(myIntent);



            }


        });

    }

    public void addListenerOnButton2() {
        imageButton = (ImageButton) findViewById(R.id.imageButton2);
        imageButton.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View view) {


                Toast.makeText(MainActivity.this, "v1.4 by @mkenzo8(Twitter & Telegram)",
                        Toast.LENGTH_LONG).show();



            }


        });

    }



    private BroadcastReceiver broadcastreceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            deviceStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int batteryLevel=(int)(((float)level / (float)scale) * 100.0f);

            if(deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING){

                textview.setText(batteryLevel+"");
                textview2.setText("Charging");
            }

            if(deviceStatus == BatteryManager.BATTERY_STATUS_DISCHARGING){

                textview.setText(batteryLevel+"");
                textview2.setText("Discharging");
            }

            if (deviceStatus == BatteryManager.BATTERY_STATUS_FULL){

                textview.setText(batteryLevel+"");
                textview2.setText("Full charged");
            }

            if(deviceStatus == BatteryManager.BATTERY_STATUS_UNKNOWN){

                textview.setText(batteryLevel+"");
                textview2.setText("Unknown");
            }


            if (deviceStatus == BatteryManager.BATTERY_STATUS_NOT_CHARGING){

                textview.setText(batteryLevel+"");
                textview2.setText("Not charging");

            }

        }
    };
}
