package com.example.hw10aberishvili.receiver


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.hw10aberishvili.data.ActionsData
import com.example.hw10aberishvili.data.SQLHandler
import java.sql.Timestamp


class PlugInBroadCastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val isHeadPhonePluggedIn  = intent?.getBooleanExtra("state",false) ?: return

        if (intent.action == Intent.ACTION_HEADSET_PLUG) {
            when (isHeadPhonePluggedIn) {
                true -> Toast.makeText(context, "HeadPhones Are connected", Toast.LENGTH_SHORT).show()
                false -> Toast.makeText(context, "Headphones have been disconnected", Toast.LENGTH_SHORT).show()
            }

        }

        if (intent.action == Intent.ACTION_POWER_CONNECTED) {
            Toast.makeText(context, "battery is charging", Toast.LENGTH_SHORT).show()

        }else{
            Toast.makeText(context, "battery is not charging", Toast.LENGTH_SHORT).show()
        }


        if (intent.action == Intent.ACTION_HEADSET_PLUG || intent.action == Intent.ACTION_POWER_CONNECTED || intent.action == Intent.ACTION_POWER_DISCONNECTED){
            val sqLiteHandler = SQLHandler(context)
            val status = sqLiteHandler.addActions(
                ActionsData((0..1000).random(),
                intent.getIntExtra("state", 1), Timestamp(System.currentTimeMillis()).toString(), intent.action.toString())
            )

        }


    }
}