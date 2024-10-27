package com.example.locationsharingapp_dipti_ict_amad_l4_04_16.Adapter_Dipti_Ict_Amad_L4_04_16

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.locationsharingapp_dipti_ict_amad_l4_04_16.Model_Dipti_Ict_Amad_L4_04_16.User_Dipti_Ict_Amad_L4_04_16
import com.example.locationsharingapp_dipti_ict_amad_l4_04_16.databinding.ItemUserDiptiIctAmadL40416Binding

class UserAdapter_Dipti_Ict_Amad_L4_04_16(private var userList: List<User_Dipti_Ict_Amad_L4_04_16>): RecyclerView.Adapter<UserAdapter_Dipti_Ict_Amad_L4_04_16.UserViewHolder>() {
    class UserViewHolder(private val binding: ItemUserDiptiIctAmadL40416Binding):RecyclerView.ViewHolder(binding.root){
        fun bind(user:User_Dipti_Ict_Amad_L4_04_16){
            binding.apply {
                binding.displayNameTxt.text = user.displayName
                binding.emailTxt.text = user.email
                binding.locationTxt.text = user.location
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemUserDiptiIctAmadL40416Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]

        holder.bind(user)

    }
    fun updateData(newList: List<User_Dipti_Ict_Amad_L4_04_16>) {
        userList = newList
        notifyDataSetChanged()
    }
}