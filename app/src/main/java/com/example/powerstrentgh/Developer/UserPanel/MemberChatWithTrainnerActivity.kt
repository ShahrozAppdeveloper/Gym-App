package com.example.powerstrentgh.Developer.UserPanel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.powerstrentgh.Developer.UserPanel.Adapter.ChatboxAdapterCLass
import com.example.powerstrentgh.ModelCLass.ModelClassMessage
import com.example.powerstrentgh.R
import com.example.powerstrentgh.databinding.ActivityMemberChatWithTrainnerBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class MemberChatWithTrainnerActivity : AppCompatActivity() {
   private lateinit var Binding:ActivityMemberChatWithTrainnerBinding
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
        Binding = ActivityMemberChatWithTrainnerBinding.inflate(layoutInflater);
        setContentView(Binding.root)

        storage = FirebaseStorage.getInstance()
        list = ArrayList();

        firebaseDatabase = FirebaseDatabase.getInstance()
        val receiverintentID = intent.getStringExtra("trainnerId")
        val receivername = intent.getStringExtra("trainnername")
        val receiverImage = intent.getStringExtra("trainnerImage")

        senderID = FirebaseAuth.getInstance().uid.toString()
        Binding.teacherChatUserName.text = receivername.toString()

        Glide.with(this@MemberChatWithTrainnerActivity)
            .load(receiverImage)
            .placeholder(R.drawable.user_pro)
            .into(Binding.teacherChatImage)

        receiverID = receiverintentID.toString()
        Toast.makeText(
            applicationContext,
            "userID" + senderID + "receiver" + receiverintentID,
            Toast.LENGTH_SHORT
        ).show();
        SenderRoom = senderID + receiverintentID
        ReciverRoom = receiverintentID + senderID
        Binding.sendimageID.setOnClickListener {
            if (Binding.edmsgsendID.text.toString().isEmpty()) {
                Toast.makeText(applicationContext, "please Enter Text", Toast.LENGTH_SHORT).show()
            } else {
                val msg = ModelClassMessage(senderID, Binding.edmsgsendID.text.toString())
                val randomkey = firebaseDatabase.reference.push().key
                firebaseDatabase.reference.child("Chats").child(SenderRoom).child("message")
                    .child(randomkey!!).setValue(msg).addOnSuccessListener {

                        firebaseDatabase.reference.child("Chats").child(ReciverRoom)
                            .child("message")
                            .child(randomkey!!).setValue(msg).addOnSuccessListener {
                                Binding.edmsgsendID.text = null
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
                    adapter = ChatboxAdapterCLass(this@MemberChatWithTrainnerActivity, list) // Initialize adapter
                    Binding.rvuserchatID.adapter = adapter
                    adapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@MemberChatWithTrainnerActivity, "Error ", Toast.LENGTH_SHORT)
                        .show()
                }

            }
            )
    }
}