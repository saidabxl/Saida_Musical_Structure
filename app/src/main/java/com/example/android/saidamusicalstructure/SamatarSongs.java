package com.example.android.saidamusicalstructure;

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

public class SamatarSongs extends AppCompatActivity {
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

        final List<Entity> samatarList = new ArrayList<>();
        samatarList.add(new Samatar(R.string.Somali_Song1, R.drawable.samatar, R.raw.number_1));
        samatarList.add(new Samatar(R.string.Somali_Song2, R.drawable.samatar, R.raw.number_two));
        samatarList.add(new Samatar(R.string.Somali_Song3, R.drawable.samatar, R.raw.number_three));
        samatarList.add(new Samatar(R.string.Somali_Song4, R.drawable.samatar, R.raw.number_four));
        samatarList.add(new Samatar(R.string.Somali_Song5, R.drawable.samatar, R.raw.number_five));
        samatarList.add(new Samatar(R.string.Somali_Song6, R.drawable.samatar, R.raw.number_six));
        samatarList.add(new Samatar(R.string.Somali_Song7, R.drawable.samatar, R.raw.number_seven));
        samatarList.add(new Samatar(R.string.Somali_Song8, R.drawable.samatar, R.raw.number_eight));
        samatarList.add(new Samatar(R.string.Somali_Song9, R.drawable.samatar, R.raw.number_eight));
        samatarList.add(new Samatar(R.string.Somali_Song9, R.drawable.samatar, R.raw.number_nine));
        samatarList.add(new Samatar(R.string.Somali_Song10, R.drawable.samatar, R.raw.number_ten));

        EntityAdapter adaptor = new EntityAdapter(this, samatarList, R.color.category_sahra);
        ListView listView = findViewById(R.id.list_entity);

        listView.setAdapter(adaptor);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                Entity samatar = samatarList.get(i);

                int result = mAudioManager.requestAudioFocus(mAudioFocusChange, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(SamatarSongs.this, samatar.getmSound());
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
