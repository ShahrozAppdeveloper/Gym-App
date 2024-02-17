package com.example.powerstrentgh.Trainner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.powerstrentgh.ClassAdapter.ChatboxAdapterCLass
import com.example.powerstrentgh.ModelCLass.ModelClassMessage
import com.example.powerstrentgh.R
import com.example.powerstrentgh.databinding.ActivityTrainerChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class TrainerChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTrainerChatBinding
    private lateinit var senderID: String
    private lateinit var receiverID: String
    private lateinit var SenderRoom: String
    lateinit var ReciverRoom: String
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var list: ArrayList<ModelClassMessage>
    private lateinit var imageview:ImageView
    var storage: FirebaseStorage? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityTrainerChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        storage = FirebaseStorage.getInstance()
        list = ArrayList();
        firebaseDatabase = FirebaseDatabase.getInstance()
        val receiverintentID = intent.getStringExtra("uid")
        val receivername = intent.getStringExtra("Username")
        val receiverImage = intent.getStringExtra("userimage")
        senderID = FirebaseAuth.getInstance().uid.toString()
        binding.userNameChatsDetaileds.text = receivername.toString()
        receiverID = receiverintentID.toString()
        Glide.with(this)
            .load(receiverImage)
            .into(binding.userImageChatsDetailed)

//        Toast.makeText(
//            applicationContext,
//            "userID" + senderID + "receiver" + receiverintentID,
//            Toast.LENGTH_SHORT
//        ).show();
        SenderRoom = senderID + receiverintentID
        ReciverRoom = receiverintentID + senderID
        binding.sendImageChatsDetailed.setOnClickListener {
            if (binding.enterMessageChatsDetailed.text.toString().isEmpty()) {
                Toast.makeText(applicationContext, "please Enter Text", Toast.LENGTH_SHORT).show()
            } else {
                val msg = ModelClassMessage(senderID, binding.enterMessageChatsDetailed.text.toString())
                val randomkey = firebaseDatabase.reference.push().key
                firebaseDatabase.reference.child("Chats").child(SenderRoom).child("message")
                    .child(randomkey!!).setValue(msg).addOnSuccessListener {

                        firebaseDatabase.reference.child("Chats").child(ReciverRoom)
                            .child("message")
                            .child(randomkey!!).setValue(msg).addOnSuccessListener {
                                binding.enterMessageChatsDetailed.text = null
                                Toast.makeText(
                                    applicationContext,
                                    "Message Sent",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                    }
            }
        }

        firebaseDatabase.reference.child("Chats").child(SenderRoom).child("message")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    list.clear()
                    for (snap1 in snapshot.children) {
                        val data = snap1.getValue(ModelClassMessage::class.java)
                        list.add(data!!)
                    }
                    binding.chatsDetailedRecyclerView.adapter = ChatboxAdapterCLass(this@TrainerChatActivity, list);
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@TrainerChatActivity, "Error ", Toast.LENGTH_SHORT).show()
                }

            }
            )


    }
}