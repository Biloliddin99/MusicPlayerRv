package com.example.musicplayerrv

import android.content.Intent
import android.media.MediaParser.TrackData
import android.media.MediaPlayer
import android.media.MediaPlayer.TrackInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayerrv.adapters.UserAdapter
import com.example.musicplayerrv.cache.MySharedPreference
import com.example.musicplayerrv.databinding.ActivityMainBinding
import com.example.musicplayerrv.models.User

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var list: ArrayList<User> = ArrayList()
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()

        val itemTouchHelper = object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(dragFlags, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                userAdapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                userAdapter.onItemDismiss(viewHolder.adapterPosition)
            }
        }
        val itemTouch = ItemTouchHelper(itemTouchHelper)
        itemTouch.attachToRecyclerView(binding.rv)
    }

    private fun loadData() {
        MySharedPreference.init(this)
        list = MySharedPreference.stringOb

            list.add(User("Jaloliddin Ahmadaliyev", "Xavotirman"))
            list.add(User("Rema", "Calm Down"))
            list.add(User("Billie Eilish", "Bellyache"))
            list.add(User("Doston Ergashev", "Ko'zmunchog'im"))
            list.add(User("Ozodbek Nazaraliyev ", "Mendirman"))
            list.add(User("Janob Rasul", "Captiva"))

        userAdapter = UserAdapter(this, list, object : UserAdapter.OnMusicClickListener {
            override fun onMusicSelect(user: User, position: Int) {
                val intent = Intent(this@MainActivity, Music::class.java)
                intent.putExtra("position", position)
                startActivity(intent)
            }
        })
        MySharedPreference.stringOb = list
        binding.rv.adapter = userAdapter
    }
}

