package com.example.android.saidamusicalstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by User on 31/03/2018.
 */


public class AlbumContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_content);

        // Find Album song TextView
        TextView song = (TextView) findViewById(R.id.album_song1);

        //add onclick listener to play it
        song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent songIntent = new Intent(AlbumContentActivity.this, NowPlayActivity.class);
                startActivity(songIntent);
            }
        });

        // Find Album song TextView
        song = (TextView) findViewById(R.id.album_song2);

        //add onclick listener to play it
        song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent songIntent = new Intent(AlbumContentActivity.this, NowPlayActivity.class);
                startActivity(songIntent);
            }
        });

        // Find Album song TextView
        song = (TextView) findViewById(R.id.album_song3);



        //add onclick listener to play it
        song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent songIntent = new Intent(AlbumContentActivity.this, NowPlayActivity.class);
                startActivity(songIntent);
            }
        });
    }
}
