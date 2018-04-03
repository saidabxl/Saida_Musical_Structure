package com.example.android.saidamusicalstructure;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MagoolSongs extends AppCompatActivity {
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

        final List<Entity> magoolList = new ArrayList<>();
        magoolList.add(new Magool("Magool", "Song Name", R.drawable.magool, R.raw.number_1));
        magoolList.add(new Magool("Magool", "Song Name", R.drawable.magool, R.raw.number_two));
        magoolList.add(new Magool("Magool", "Song Name", R.drawable.magool, R.raw.number_three));
        magoolList.add(new Magool("Magool", "Song Name", R.drawable.magool, R.raw.number_four));
        magoolList.add(new Magool("Magool", "Song Name", R.drawable.magool, R.raw.number_five));
        magoolList.add(new Magool("Magool", "Song Name", R.drawable.magool, R.raw.number_six));
        magoolList.add(new Magool("Artist", "Song Name", R.drawable.magool, R.raw.number_seven));

        EntityAdapter adaptor = new EntityAdapter(this, magoolList, R.color.category_magool);
        ListView listView = findViewById(R.id.list_entity);

        listView.setAdapter(adaptor);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.FROYO)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                Entity magool = magoolList.get(i);

                int result = mAudioManager.requestAudioFocus(mAudioFocusChange, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(MagoolSongs.this, magool.getmSound());
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

    @TargetApi(Build.VERSION_CODES.FROYO)
    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;

            mAudioManager.abandonAudioFocus(mAudioFocusChange);
        }
    }
}
