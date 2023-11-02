package com.sdtv.trident.ui/*
package com.sdtv.trident.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.sdtv.trident.R

class ExoPlayerActivity : AppCompatActivity() {
    lateinit var exoPlayerView: PlayerView

    // on below line we are creating a
    // variable for exo player
    lateinit var exoPlayer: SimpleExoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exo_player)
        // on below line we are initializing our all variables.
        exoPlayerView = findViewById(R.id.idExoPlayerVIew)
        try {
            // bandwidthmeter is used for
            // getting default bandwidth
            val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()

            // track selector is used to navigate between
            // video using a default seekbar.
            val trackSelector: TrackSelector =
                DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))

            // we are adding our track selector to exoplayer.
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector)

            // we are parsing a video url
            // and parsing its video uri.
            val videoURI: Uri = Uri.parse(videoURL)

            // we are creating a variable for datasource factory
            // and setting its user agent as 'exoplayer_view'
            val dataSourceFactory: DefaultHttpDataSourceFactory =
                DefaultHttpDataSourceFactory("Exoplayer_video")

            // we are creating a variable for extractor factory
            // and setting it to default extractor factory.
            val extractorsFactory: ExtractorsFactory = DefaultExtractorsFactory();

            // we are creating a media source with above variables
            // and passing our event handler as null,
            val mediaSourse: MediaSource =
                ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null)

            // inside our exoplayer view
            // we are setting our player
            exoPlayerView.player = exoPlayer

            // we are preparing our exoplayer
            // with media source.
            exoPlayer.prepare(mediaSourse)

            // we are setting our exoplayer
            // when it is ready.
            exoPlayer.playWhenReady = true


        } catch (e: Exception) {
            // on below line we
            // are handling exception
            e.printStackTrace()
        }
    }
}*/
