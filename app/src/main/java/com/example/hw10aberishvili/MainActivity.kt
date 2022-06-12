package com.example.hw10aberishvili

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.IntentFilter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw10aberishvili.adapters.ActionsAdapter
import com.example.hw10aberishvili.data.SQLHandler
import com.example.hw10aberishvili.receiver.PlugInBroadCastReceiver


class MainActivity : AppCompatActivity() {



    lateinit var receiver: PlugInBroadCastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        init()
    }

    override fun onResume() {
        super.onResume()
        receiver = PlugInBroadCastReceiver()
        val filter = IntentFilter(Intent.ACTION_HEADSET_PLUG)
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        registerReceiver(receiver, filter)
    }
    
    private fun init(){
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        val sqLiteHandler = SQLHandler(this)
        println(sqLiteHandler.getAllActionsRecords())
        recyclerView.adapter = ActionsAdapter(sqLiteHandler.getAllActionsRecords())
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
    }
}