package com.example.powerstrentgh.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.powerstrentgh.ClassAdapter.ChatboxAdapterCLass
import com.example.powerstrentgh.ModelCLass.ModelClassMessage
import com.example.powerstrentgh.R

import com.example.powerstrentgh.databinding.ActivityUserChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class UserChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserChatBinding
    private lateinit var senderID: String
    private lateinit var receiverID: String
    private lateinit var SenderRoom: String
    lateinit var ReciverRoom: String
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var list: ArrayList<ModelClassMessage>
    var storage: FirebaseStorage? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_chat)
        binding =ActivityUserChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        storage = FirebaseStorage.getInstance()
        list = ArrayList();
        firebaseDatabase = FirebaseDatabase.getInstance()
        val receiverintentID = intent.getStringExtra("uid")
        val receivername = intent.getStringExtra("Username")
        val receiverImage = intent.getStringExtra("userimage")
        senderID = FirebaseAuth.getInstance().uid.toString()
        binding.userNameChatsDetailed.text = receivername.toString()
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
                    binding.chatsDetailedRecyclerView.adapter = ChatboxAdapterCLass(this@UserChatActivity, list);
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@UserChatActivity, "Error ", Toast.LENGTH_SHORT).show()
                }

            }
            )


    }
    }
