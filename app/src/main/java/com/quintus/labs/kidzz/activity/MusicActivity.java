package com.quintus.labs.kidzz.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.quintus.labs.kidzz.R;

public class MusicActivity extends AppCompatActivity {
    static final String AUDIO_PATH = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_MUSIC)+"/babyshark.mp3";
    private MediaPlayer mediaPlayer;
    private int playbackPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
    }
    public void doClick(View view){
        switch(view.getId()){
            case R.id.buttonPlay:
                try{
                    playLocalAudio();
                }catch(Exception e){
                    e.printStackTrace();
                }
            case R.id.buttonPause:
                if(mediaPlayer != null && mediaPlayer.isPlaying()){
                    playbackPosition = mediaPlayer.getCurrentPosition();
                    mediaPlayer.pause();
                }
                break;
            case R.id.buttonRestart:
                if(mediaPlayer != null && !mediaPlayer.isPlaying()){
                    mediaPlayer.seekTo(playbackPosition);
                    mediaPlayer.start();
                }
                break;
            case R.id.buttonStop:
                if(mediaPlayer != null ){
                    mediaPlayer.stop();
                    playbackPosition = 0;
                }
                break;
        }
    }
    private void playAudio(String url) throws Exception
    {
        killMediaPlayer();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDataSource(url);
        mediaPlayer.prepareAsync();
    }
    private void playLocalAudio() throws Exception{
        mediaPlayer=MediaPlayer.create(this,R.raw.babyshark);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.start();
    }
    private void onPrepared(MediaPlayer mp){
        mp.start();
    }
    private void killMediaPlayer(){
        if(mediaPlayer!=null){
            try{
                mediaPlayer.release();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
