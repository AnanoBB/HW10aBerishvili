package com.example.hw10aberishvili.adapters

import com.example.hw10aberishvili.R
import com.example.hw10aberishvili.data.ActionsData



import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ActionsAdapter(private val list: List<ActionsData>) : RecyclerView.Adapter<ActionsAdapter.ResourceViewHolder>() {
    class ResourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var id: TextView = itemView.findViewById(R.id.idTv)
        private var state: TextView = itemView.findViewById(R.id.actionState)
        private var actionTime: TextView = itemView.findViewById(R.id.actionTime)
        private var actionType: TextView = itemView.findViewById(R.id.actionType)

        private lateinit var record: ActionsData

        @SuppressLint("SetTextI18n")
        fun onBind(record: ActionsData){
            this.record = record

            id.text = record.id.toString()
            state.text = record.actionState.toString()
            actionTime.text = record.actionTime
            actionType.text = record.actionType
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.actions_layout, parent, false)

        return ResourceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResourceViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}