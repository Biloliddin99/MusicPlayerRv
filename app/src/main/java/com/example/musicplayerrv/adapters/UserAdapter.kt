package com.example.musicplayerrv.adapters

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayerrv.R
import com.example.musicplayerrv.databinding.ItemRvBinding
import com.example.musicplayerrv.models.User
import com.example.musicplayerrv.utils.ItemTouchHelperAdapter
import java.util.Collections

class UserAdapter(
    val context: Context,
    private val list: ArrayList<User>,
    val musicClickListener: OnMusicClickListener
) : RecyclerView.Adapter<UserAdapter.Vh>(),
    ItemTouchHelperAdapter {

    inner class Vh(private val itemRvBinding: ItemRvBinding) :
        RecyclerView.ViewHolder(itemRvBinding.root) {

        fun onBind(user: User) {
            itemRvBinding.name.text = user.name
            itemRvBinding.music.text = user.music

            itemRvBinding.root.setOnClickListener {
                musicClickListener.onMusicSelect(user, this.position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition > toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(list, i, i + 1)
            }
        } else {
            for (i in fromPosition until toPosition + 1) {
                Collections.swap(list, i, i)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    interface OnMusicClickListener {
        fun onMusicSelect(user: User, position: Int)
    }
}