package com.example.colin.smstest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static TextView view;

    BroadcastReceiver reciever = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    Log.i("SMS", "RECIEVED INTENT: " + intent.getAction());

                    Bundle smsBundle = intent.getExtras();

            Object[] psudObject = (Object[]) smsBundle.get("pdus");

            if (psudObject!=null){


                for (int i = 0; i<psudObject.length; i++){
                    SmsMessage message = SmsMessage.createFromPdu((byte[]) psudObject[i]);

                    String messageText = message.getDisplayMessageBody();
                    String senderText = message.getDisplayOriginatingAddress();

                    Toast toast = Toast.makeText(context, "Sender is "+senderText+" and message is "+messageText, Toast.LENGTH_SHORT);

                    toast.show();

                    Log.i("SMS", "SMS RECEIVED");

                }
            }


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (TextView) findViewById(R.id.test);
    }

    public static void updateText(String text){
        view.setText(text);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
