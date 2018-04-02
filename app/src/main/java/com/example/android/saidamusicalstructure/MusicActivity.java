package com.example.android.saidamusicalstructure;

/**
 * Created by User on 02/04/2018.
 */

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MusicActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private AudioManager mAudioManager;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    private AudioManager.OnAudioFocusChangeListener mAudioFocusChange = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entity_list);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final List<Entity> songList = new ArrayList<>();
        songList.add(new Music(R.string.Somali_Song1, R.raw.number_1));
        songList.add(new Music(R.string.Somali_Song2, R.raw.number_two));
        songList.add(new Music(R.string.Somali_Song3, R.raw.number_three));
        songList.add(new Music(R.string.Somali_Song4, R.raw.number_four));
        songList.add(new Music(R.string.Somali_Song5, R.raw.number_five));
        songList.add(new Music(R.string.Somali_Song6, R.raw.number_six));
        songList.add(new Music(R.string.Somali_Song7, R.raw.number_seven));
        songList.add(new Music(R.string.Somali_Song8, R.raw.number_eight));
        songList.add(new Music(R.string.Somali_Song9, R.raw.number_nine));
        songList.add(new Music(R.string.Somali_Song10, R.raw.number_ten));


        EntityAdapter adaptor = new EntityAdapter(this, songList, R.color.category_song);
        ListView listView = findViewById(R.id.list_entity);

        listView.setAdapter(adaptor);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                Entity song = songList.get(i);

                int result = mAudioManager.requestAudioFocus(mAudioFocusChange, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(MusicActivity.this, song.getmSound());
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;

            mAudioManager.abandonAudioFocus(mAudioFocusChange);
        }
    }
}
