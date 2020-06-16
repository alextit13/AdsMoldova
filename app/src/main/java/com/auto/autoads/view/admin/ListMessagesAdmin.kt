package com.auto.autoads.view.admin

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.auto.autoads.R
import com.auto.autoads.model.utils.Message
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_admin_message_list.*
import kotlinx.android.synthetic.main.item_message.view.*
import java.text.SimpleDateFormat
import java.util.*

class ListMessagesAdmin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_message_list)

        FirebaseDatabase.getInstance().getReference("admin_messages")
            .addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        val list = mutableListOf<Message>()
                        snapshot.children.forEach {
                            list.add(it.getValue(Message::class.java) ?: return)
                        }
                        rvMessageAdmin.adapter = MessageAdminAdapter(list, {
                            val clipboard =
                                getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText("Copyed text", it.toString())
                            clipboard.primaryClip = clip
                            Toast.makeText(
                                this@ListMessagesAdmin,
                                "Сообщение скопировано в буфер обмена",
                                Toast.LENGTH_LONG
                            ).show()
                        }, {
                            // todo this
                        })
                    }
                }
            )
    }

    inner class MessageAdminAdapter(
        val list: MutableList<Message>,
        private val clickCallback: (Message) -> Unit,
        private val longCallback: (Message) -> Unit
    ) :
        RecyclerView.Adapter<MessageAdminAdapter.HolderMessageAdminAdapter>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): HolderMessageAdminAdapter =
            HolderMessageAdminAdapter(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_message,
                    parent,
                    false
                )
            )

        override fun getItemCount(): Int = list.size

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: HolderMessageAdminAdapter, position: Int) {
            val message = list[position]
            holder.apply {
                title.text = "Тема: ${message.title}"
                body.text = "Сообщение: ${message.body}"
                email.text = "Email: ${message.email}"
                name.text = "Имя: ${message.senderName}"
                time.text = SimpleDateFormat(
                    "dd.MM.yyyy hh:mm",
                    Locale.ROOT
                ).format(Date(message.id.toLong()))
                itemView.setOnClickListener {
                    clickCallback.invoke(message)
                }
                itemView.setOnLongClickListener {
                    longCallback.invoke(message)
                    true
                }
            }
        }

        inner class HolderMessageAdminAdapter(rootView: View) : RecyclerView.ViewHolder(rootView) {
            val title: TextView = rootView.tvTitleMessage
            val body: TextView = rootView.tvBodyMessage
            val email: TextView = rootView.tvEmailMessage
            val name: TextView = rootView.tvNameMessage
            val time: TextView = rootView.tvTimeMessage
        }
    }
}