package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class Adapter(var records : ArrayList<AudioRecord>, var listener: OnItemClickListener) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener{
        var tvFilename : TextView = itemView.findViewById(R.id.tvFilename)
        var tvMeta : TextView = itemView.findViewById(R.id.tvMeta)

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onClick(p0: View?) {
            var position = absoluteAdapterPosition
            if(position != RecyclerView.NO_POSITION)
                listener.onItemClickListener(position)
        }

        override fun onLongClick(p0: View?): Boolean {
            var position = absoluteAdapterPosition
            if(position != RecyclerView.NO_POSITION)
                listener.onItemLongClickListenerClickListener(position)
            return true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemview_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position != RecyclerView.NO_POSITION){
            var record = records[position]

            var sdf = SimpleDateFormat("dd/MM/yyyy")
            var date = Date(record.timestamp)
            var strDate = sdf.format(date)

            holder.tvFilename.text = record.filename
            holder.tvMeta.text = "${record.duration} $strDate"
        }
    }

    override fun getItemCount(): Int {
        return records.size
    }


}