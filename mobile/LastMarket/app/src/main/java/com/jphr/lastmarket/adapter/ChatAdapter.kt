package com.jphr.lastmarket.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jphr.lastmarket.R
import com.jphr.lastmarket.databinding.ItemChatMessageBinding
import com.jphr.lastmarket.dto.*
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "LatestOrderAdapter_싸피"
class ChatAdapter(val context: Context) :RecyclerView.Adapter<ChatAdapter.ChatListHolder>(){
    var list : ChatListDTO? =null
    var myId:Long=0

    inner class ChatListHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindInfo(chat: ChatLog){
            if(chat.sender==myId.toString()){//내가 보낸 채팅
                binding.my.visibility=View.VISIBLE
                binding.my.text=chat.msg
                binding.other.visibility=View.GONE

            }else{
                binding.my.visibility=View.GONE
                binding.other.visibility=View.VISIBLE
                binding.other.text=chat.msg
            }

        }
    }
    private lateinit var binding: ItemChatMessageBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListHolder {
        binding = ItemChatMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ChatListHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ChatListHolder, position: Int) {
//        holder.bind()

        holder.apply {
            list?.chatLogs?.get(position)?.let { bindInfo(it) }
            //클릭연결
            itemView.setOnClickListener{
                itemClickListner.onClick(it, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return 10.coerceAtMost(list!!.chatLogs.size)
    }

    //클릭 인터페이스 정의 사용하는 곳에서 만들어준다.
    interface ItemClickListener {
        fun onClick(view: View,  position: Int)
    }
    //클릭리스너 선언
    private lateinit var itemClickListner: ItemClickListener
    //클릭리스너 등록 매소드
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }

}