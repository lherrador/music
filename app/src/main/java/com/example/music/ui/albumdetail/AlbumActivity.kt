package com.example.music.ui.albumdetail

import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.music.R
import com.example.music.databinding.AlbumDetailBinding
import com.example.music.databinding.AlbumListActivityMainBinding
import com.example.music.ui.albumdetail.adapter.TrackListAdapter
import com.example.music.ui.albumlist.adapter.AlbumListAdapter
import com.example.music.viewModel.AlbumListViewModel
import com.example.music.viewModel.AlbumViewModel
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AlbumActivity : AppCompatActivity() {

    private lateinit var trackListAdapter : TrackListAdapter
    private var id: Long = -1L
    private val albumViewModel: AlbumViewModel by viewModel { parametersOf(id) }
    private val compositeDisposable = CompositeDisposable()
    private lateinit var binding : AlbumDetailBinding
    private var mediaPlayer: MediaPlayer? = null

    companion object {
        const val ID = "ID"
        fun start(context: Context, id: Long) {
            val intent = Intent(context, AlbumActivity::class.java)
            intent.putExtra(ID, id)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AlbumDetailBinding.inflate(layoutInflater)

        trackListAdapter = TrackListAdapter {
            //TODO create an external class to manage the player
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer().apply {
                setDataSource(it)
                prepare()
                start()
            }
        }
        binding.recyclerView.adapter = trackListAdapter

        setContentView(R.layout.album_detail)

        intent.extras?.let { extras ->
            id = extras.getLong(ID)
        }

        setContentView(binding.root)
    }


    override fun onResume() {
        compositeDisposable.add(
            albumViewModel.albumUIDatasource.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { uiData ->
                    binding.collapsing.title = uiData.description
                    trackListAdapter.submitList(uiData.trackList)
                    Picasso.get()
                        .load(uiData.image)
                        .placeholder(R.drawable.emty_sate_album)
                        .into(binding.image)
                })
        super.onResume()
    }

    override fun onStop() {
        compositeDisposable.dispose()
        mediaPlayer?.release()
        super.onStop()
    }
}