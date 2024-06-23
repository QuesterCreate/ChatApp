package code.with.ar.chatapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class messageAdapter(val context: Context, val messageList: ArrayList<message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECIEVED = 1
    val ITEM_SENT = 2


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == 1) {
            //inflate receive


            val view: View = LayoutInflater.from(context).inflate(R.layout.receive, parent, false)

            return ReceivedViewHolder(view)


        } else {
            //inflate sent
            val view: View = LayoutInflater.from(context).inflate(R.layout.sent, parent, false)

            return SentViewHolder(view)


        }


    }


    override fun getItemViewType(position: Int): Int {

        val currentMessage = messageList[position]

        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)) {
            return ITEM_SENT
        } else {
            return ITEM_RECIEVED
        }


    }


    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messageList[position]

        if (holder is SentViewHolder) {
            // Update the sent message view holder
            holder.sentmessage.text = currentMessage.message
            Log.d("MessageAdapter", "Sent message: ${currentMessage.message}")
        } else if (holder is ReceivedViewHolder) {
            // Update the received message view holder
            holder.receivedmessage.text = currentMessage.message
            Log.d("MessageAdapter", "Received message: ${currentMessage.message}")
        }
    }


    class SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sentmessage = itemView.findViewById<TextView>(R.id.sent_message)
    }

    class ReceivedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val receivedmessage = itemView.findViewById<TextView>(R.id.received_message)
    }


}