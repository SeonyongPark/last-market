package com.jphr.lastmarket.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.jphr.lastmarket.R
import com.jphr.lastmarket.activity.MainActivity
import com.jphr.lastmarket.adapter.ChatListAdapter
import com.jphr.lastmarket.databinding.FragmentChatBinding
import com.jphr.lastmarket.databinding.FragmentChatListBinding
import com.jphr.lastmarket.viewmodel.MainViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChatFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentChatBinding
    private lateinit var mainActivity: MainActivity
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity=context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment'
        binding=FragmentChatBinding.inflate(inflater,container,false)
        var prefs=requireActivity().getSharedPreferences("user_info", AppCompatActivity.MODE_PRIVATE)
        var token =prefs.getString("token","")!!
        var userId=prefs.getLong("user_id",0)

        var chatDTO=mainViewModel.getChatDTO()
        if(chatDTO?.seller==userId.toString()){//내가 seller 일 때
            binding.nickname.text=chatDTO.buyer
        }else{
            binding.nickname.text=chatDTO?.seller
        }




        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChatFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}