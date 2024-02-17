package com.example.powerstrentgh.ClassAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.powerstrentgh.ModelCLass.ModelClassMessage
import com.example.powerstrentgh.R
import com.example.powerstrentgh.databinding.ChatsendLayoutBinding
import com.example.powerstrentgh.databinding.ReceivechatlayoutBinding
import com.google.firebase.auth.FirebaseAuth

class ChatboxAdapterCLass(var context: Context, var list:ArrayList<ModelClassMessage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var Item_sender=1
    var Item_reciver=2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType ==Item_sender){
            SenderviewHolder(LayoutInflater.from(context).inflate(R.layout.chatsend_layout,parent,false))
        }else{
            RevviewHolder(LayoutInflater.from(context).inflate(R.layout.receivechatlayout,parent,false))
        }
    }

    override fun getItemViewType(position:Int):Int {
        return if (FirebaseAuth.getInstance().uid==list[position].senderid)
            Item_sender
        else
            Item_reciver

    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msglist=list[position]
        if(holder.itemViewType== Item_sender){
            val viewHolder=holder as SenderviewHolder
            viewHolder.binding.chatsendID.text=msglist.message
        }else{
            val viewHolder=holder as RevviewHolder
            viewHolder.binding.chatreviceID.text=msglist.message
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
    inner class SenderviewHolder(view : View):RecyclerView.ViewHolder(view){
        var binding= ChatsendLayoutBinding.bind(view)
    }
    inner class RevviewHolder(view :View):RecyclerView.ViewHolder(view){
        var binding= ReceivechatlayoutBinding.bind(view)
    }
}