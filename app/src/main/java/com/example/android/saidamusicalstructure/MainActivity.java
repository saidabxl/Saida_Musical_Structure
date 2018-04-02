package com.example.android.saidamusicalstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView samatarImageView = findViewById(R.id.samatar_image_view);
        samatarImageView.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                Intent samatarIntent = new Intent(MainActivity.this, SamatarSongs.class);
                startActivity(samatarIntent);
            }
        });
        ImageView magoolImageView = findViewById(R.id.magool_image_view);
        magoolImageView.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                Intent magoolIntent = new Intent(MainActivity.this, MagoolSongs.class);
                startActivity(magoolIntent);
            }
        });

        ImageView sahraImageView = findViewById(R.id.sahra_image_view);
        sahraImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sahraIntent = new Intent(MainActivity.this, SahraSongs.class);
                startActivity(sahraIntent);
            }
        });
        ImageView musicImageView = findViewById(R.id.hibo_image_view);
        musicImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent musicIntent = new Intent(MainActivity.this, MusicActivity.class);
                startActivity(musicIntent);
            }
        });


    }
}
