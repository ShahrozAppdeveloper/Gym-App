package com.example.powerstrentgh.Developer.TrainerPanel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.powerstrentgh.Developer.UserPanel.Adapter.ChatboxAdapterCLass
import com.example.powerstrentgh.ModelCLass.ModelClassMessage
import com.example.powerstrentgh.R
import com.example.powerstrentgh.databinding.ActivityMemberChatWithTrainnerBinding
import com.example.powerstrentgh.databinding.ActivityTrainnerChatBinding

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class TrainnerChatActivity : AppCompatActivity() {
    private lateinit var binding:ActivityTrainnerChatBinding
    private lateinit var senderID: String
    private lateinit var receiverID: String
    private lateinit var SenderRoom: String
    lateinit var ReciverRoom: String
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var list: ArrayList<ModelClassMessage>
    var storage: FirebaseStorage? = null
    private lateinit var adapter: ChatboxAdapterCLass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainnerChatBinding.inflate(layoutInflater);
        setContentView(binding.root)
        storage = FirebaseStorage.getInstance()
        list = ArrayList();



        firebaseDatabase = FirebaseDatabase.getInstance()
        val receiverintentID = intent.getStringExtra("TeacherUserId")
        val receivername = intent.getStringExtra("TeacherName")
        val receiverImage = intent.getStringExtra("TeacherImage")

        senderID = FirebaseAuth.getInstance().uid.toString()
        binding.teacherChatUserName.text = receivername.toString()

        Glide.with(this)
            .load(receiverImage)
            .placeholder(R.drawable.user_pro)
            .into(binding.teacherChatImage)

        receiverID = receiverintentID.toString()
        Toast.makeText(
            applicationContext,
            "userID" + senderID + "receiver" + receiverintentID,
            Toast.LENGTH_SHORT
        ).show();
        SenderRoom = senderID + receiverintentID
        ReciverRoom = receiverintentID + senderID
        binding.sendimageID.setOnClickListener {
            if (binding.edmsgsendID.text.toString().isEmpty()) {
                Toast.makeText(applicationContext, "please Enter Text", Toast.LENGTH_SHORT).show()
            } else {
                val msg = ModelClassMessage(senderID, binding.edmsgsendID.text.toString())
                val randomkey = firebaseDatabase.reference.push().key
                firebaseDatabase.reference.child("Chats").child(SenderRoom).child("message")
                    .child(randomkey!!).setValue(msg).addOnSuccessListener {

                        firebaseDatabase.reference.child("Chats").child(ReciverRoom)
                            .child("message")
                            .child(randomkey!!).setValue(msg).addOnSuccessListener {
                                binding.edmsgsendID.text = null
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
        // show the messages
        firebaseDatabase.reference.child("Chats").child(SenderRoom).child("message")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    list.clear()
                    for (snap1 in snapshot.children) {
                        val data = snap1.getValue(ModelClassMessage::class.java)
                        list.add(data!!)
                    }
                    adapter = ChatboxAdapterCLass(this@TrainnerChatActivity, list) // Initialize adapter
                    binding.rvuserchatID.adapter = adapter
                    adapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@TrainnerChatActivity, "Error ", Toast.LENGTH_SHORT)
                        .show()
                }

            }
            )
    }
    }
