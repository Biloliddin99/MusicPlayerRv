package com.example.musicplayerrv

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.musicplayerrv.cache.MySharedPreference
import com.example.musicplayerrv.databinding.ActivityMusicBinding
import com.example.musicplayerrv.models.User

class Music : AppCompatActivity() {
    private lateinit var binding: ActivityMusicBinding
    private var list: ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val position = intent.getIntExtra("position", 0)
        MySharedPreference.init(this)

        list = MySharedPreference.stringOb
        when (list[position].music) {
            "Ko'zmunchog'im" -> {
                val mediaPlayer1 = MediaPlayer.create(this, R.raw.doston)
                mediaPlayer1.start()
                binding.btn.setOnClickListener {
                    mediaPlayer1.stop()
                }
            }
            "Captiva" -> {
                val mediaPlayer1 = MediaPlayer.create(this, R.raw.ras)
                mediaPlayer1.start()
                binding.btn.setOnClickListener {
                    mediaPlayer1.stop()
                }
            }
            "Mendirman" -> {
                val mediaPlayer1 = MediaPlayer.create(this, R.raw.mendirman)
                mediaPlayer1.start()
                binding.btn.setOnClickListener {
                    mediaPlayer1.stop()
                }
            }
            "Xavotirman" -> {
                val mediaPlayer1 = MediaPlayer.create(this, R.raw.xavotirman)
                mediaPlayer1.start()
                binding.btn.setOnClickListener {
                    mediaPlayer1.stop()
                }
            }

            "Calm Down" -> {
                val mediaPlayer1 = MediaPlayer.create(this, R.raw.rema)
                mediaPlayer1.start()
                binding.btn.setOnClickListener {
                    mediaPlayer1.stop()
                }
            }

            "Bellyache" -> {
                val mediaPlayer1 = MediaPlayer.create(this, R.raw.bellyache)
                mediaPlayer1.start()
                binding.btn.setOnClickListener {
                    mediaPlayer1.stop()
                }
            }

        }
        binding.seekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })


    }
}