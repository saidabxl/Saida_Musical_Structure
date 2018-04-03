package com.example.android.saidamusicalstructure;


public abstract class Entity {
    public String nameOfSinger;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    public static final int NO_IMAGE_PROVIDED = -1;

    public String nameOfSong;

    public int mSoundId;

    public Entity(String nameOfSinger, String nameOfSong, int imageResourceId, int soundResource) {
        this.nameOfSinger = nameOfSinger;
        this.nameOfSong = nameOfSong;
        this.mImageResourceId = imageResourceId;
        this.mSoundId = soundResource;
    }

    public Entity(String nameOfSinger, String nameOfSong, int mSoundId) {
        this.nameOfSinger = nameOfSinger;
        this.nameOfSong = nameOfSong;
        this.mSoundId = mSoundId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public String getNameOfSinger() {
        return nameOfSinger;
    }

    public String getNameOfSong() {
        return nameOfSong;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public int getmSound() {
        return mSoundId;
    }

}
