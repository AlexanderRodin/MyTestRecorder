package com.example.testrecorder.player

import android.app.Application
import android.net.Uri
import androidx.lifecycle.*
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util

//TODO Add playback and delete files

class PlayerViewModel(itemPath: String, application: Application) : AndroidViewModel(application),
    LifecycleObserver {
    private val _player = MutableLiveData<Player?>()
    val player: LiveData<Player?> get() = _player
    private var contentPosition = 0L
    private var playWhenReady = true
    var itemPath: String? = itemPath

    init {
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onForegrounded() {
        setUpPlayer()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onBackgrounded() {
        releaseExoPlayer()
    }

    private fun setUpPlayer() {
//        val dataSourceFactory = DefaultDataSourceFactory(
//            getApplication(),
//            Util.getUserAgent(getApplication(), "recorder")
//        )
//
//       * val mediaSource = ExtractorMediaSource.Factory(dataSourceFactory)

//        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
//        *    .setExtractorsFactory(DefaultExtractorsFactory())
//         *   .createMediaSource(Uri.parse(itemPath))

//     *   val player = ExoPlayerFactory.newSimpleInstance(getApplication())
//        val player = ExoPlayer.Builder(getApplication()).build()
//        player.prepare(mediaSource)
//        player.playWhenReady = playWhenReady
//        player.seekTo(contentPosition)
//
//        this._player.value = player
    }

    private fun releaseExoPlayer() {
        val player = _player.value ?: return
        this._player.value = null

        contentPosition = player.contentPosition
        playWhenReady = player.playWhenReady
        player.release()
    }

    override fun onCleared() {
        super.onCleared()
        releaseExoPlayer()
        ProcessLifecycleOwner.get().lifecycle.removeObserver(this)
    }
//    private fun setUpPlayer() {
//        val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()
//        val mediaSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
//            .createMediaSource(MediaItem.fromUri(itemPath.toString()))
//        val exoPlayer = ExoPlayer.Builder(getApplication()).build()
//        exoPlayer.setMediaSource(mediaSource)
//        exoPlayer.prepare()
//    }

}