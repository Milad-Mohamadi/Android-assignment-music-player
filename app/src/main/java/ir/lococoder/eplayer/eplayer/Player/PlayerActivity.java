package ir.lococoder.eplayer.eplayer.Player;


import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import ir.lococoder.eplayer.R;
import ir.lococoder.eplayer.common.LAppCompatActivity;

import static ir.lococoder.eplayer.common.Common.convertMilliSecToHMS;
import static ir.lococoder.eplayer.common.Common.setLog;
import static ir.lococoder.eplayer.common.Common.toastFilterPlayer;
import static ir.lococoder.eplayer.eplayer.home.MainActivity.songs;

public class PlayerActivity extends LAppCompatActivity {
  public  MediaPlayer mediaPlayer;

  private Ui ui;
  private int position;
  private JSONObject song;
  ImageView img_next_track, img_previews_track,img_play;
  TextView txt_total_time,txt_start_time;
  SeekBar seekBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ui = new Ui();
    new Founder(PlayerActivity.this)
      .noActionbar()
      .extractUi(ui)
      .contentView(R.layout.activity_player)
      .requestFeatures()
      .build();

    img_next_track=(ImageView)findViewById(R.id.img_next_track);
    img_previews_track=(ImageView)findViewById(R.id.img_previews_track);
    img_play=(ImageView)findViewById(R.id.img_play);
    txt_total_time=(TextView) findViewById(R.id.txt_total_time);
    txt_start_time=(TextView) findViewById(R.id.txt_start_time);
    seekBar=(SeekBar) findViewById(R.id.seekBar);
getBundle();
    setData();




    img_next_track.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (position<songs.length()-1){
          position+=1;
        }else if (position==songs.length()-1){
          position=0;
        }
        setData();

      }
    });
    img_previews_track.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
       if (position>0){
         position-=1;
       }else if (position==0){
         position=songs.length();
       }
        setData();

      }
    });
  }

  private void setData() {
    try {
      song=songs.getJSONObject(position);
      String hugePic = song.getString("pic_huge");
      String bigPic = song.getString("pic_big");
      String title = song.getString("title");
      String titleAlbum = song.getString("album_title");
      String allRate = song.getString("all_rate");
      String songSource = song.getString("song_source");

      load(songSource);
    } catch (JSONException e) {
    }
  }

  private void playSong() {

    try {
      if (mediaPlayer != null && mediaPlayer.isPlaying()) {
        mediaPlayer.pause();
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        img_play.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
//        endTime = mediaPlayer.getCurrentPosition() + "";

      }
      if (!mediaPlayer.isPlaying()) {
//        startTime = mediaPlayer.getCurrentPosition() + "";
//        setLog("play", "11");
        img_play.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
        mediaPlayer.setVolume(0.5f, 0.5f);
        mediaPlayer.setLooping(false);
        seekBar.setMax(mediaPlayer.getDuration());
        mediaPlayer.start();
//        new Thread(runnable).start();
        txt_total_time.setText(convertMilliSecToHMS(mediaPlayer.getDuration()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void getBundle() {
    Intent intent = getIntent();
    Bundle bundle = intent.getExtras();
    if (intent.hasExtra("position")) position = bundle.getInt("position");


  }


  private void loadMp3() {
  }

  private class Ui {

  }

  private void load(String songSource) {
    mediaPlayer = new MediaPlayer();
    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    try {
      mediaPlayer.setDataSource(songSource);

    } catch (IllegalArgumentException | SecurityException | IllegalStateException e) {
      toastFilterPlayer(PlayerActivity.this);


    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      mediaPlayer.prepare();
      playSong();
    } catch (IllegalStateException | IOException e) {
//      Toast.makeText(activity, activity.getResources().getString(R.string.toast_filter_play), Toast.LENGTH_LONG).show();
      toastFilterPlayer(PlayerActivity.this);

    }
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    overridePendingTransition( R.anim.slide_up_to_down, R.anim.slide_up_to_down_2);

  }

}
