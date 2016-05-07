package com.example.colin.smstest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Colin on 2015-11-21.
 */
public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("SMS", "RECIEVED INTENT: "+intent.getAction());

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
}
