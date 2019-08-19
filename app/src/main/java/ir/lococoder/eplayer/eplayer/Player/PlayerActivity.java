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
import ir.lococoder.eplayer.common.Common;
import ir.lococoder.eplayer.common.LAppCompatActivity;

import static ir.lococoder.eplayer.common.Common.convertMilliSecToHMS;
import static ir.lococoder.eplayer.common.Common.toastFilterPlayer;
import static ir.lococoder.eplayer.eplayer.home.MainActivity.songs;

public class PlayerActivity extends LAppCompatActivity {
  public static   MediaPlayer mediaPlayer;

  private Ui ui;
  private int position;
  private JSONObject song;
  ImageView img_next_track, img_previews_track,img_play,img_profile,img_background;
  TextView txt_total_time,txt_start_time,txt_title,txt_album_title,txt_all_rates,txt_rate;
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
    img_profile=(ImageView)findViewById(R.id.img_profile);
    img_background=(ImageView)findViewById(R.id.img_cover_background);
    txt_title=(TextView) findViewById(R.id.txt_title);
    txt_album_title=(TextView) findViewById(R.id.txt_album_title);
    txt_all_rates=(TextView) findViewById(R.id.txt_all_rates);
    txt_rate=(TextView) findViewById(R.id.txt_rate);
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

    img_play.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        playAndPauseSong();

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
      String bitrate_id = song.getString("bitrate_id");
      String songSource = song.getString("song_source");
      Common.setBlurImageAfterDownloadWithGlide(PlayerActivity.this,hugePic,img_background,R.drawable.ic_android_black_24dp);
      Common.setImageAndCatch(PlayerActivity.this,hugePic,img_profile,R.drawable.ic_android_black_24dp,400,0);
      txt_title.setText(title);
      txt_album_title.setText(titleAlbum);
      txt_all_rates.setText(allRate);
      txt_rate.setText(bitrate_id);
      load(songSource);
    } catch (JSONException e) {
    }
  }

  private void playAndPauseSong() {

    try {
      if (mediaPlayer != null && mediaPlayer.isPlaying()) {
        mediaPlayer.pause();
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        img_play.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
//        endTime = mediaPlayer.getCurrentPosition() + "";
      }
      if (!mediaPlayer.isPlaying()) {
         txt_start_time.setText(convertMilliSecToHMS(mediaPlayer.getCurrentPosition()));
        img_play.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
        mediaPlayer.setVolume(0.5f, 0.5f);
        mediaPlayer.setLooping(false);
        seekBar.setMax(mediaPlayer.getDuration());
        mediaPlayer.start();
        new Thread(runnable).start();
        txt_total_time.setText(convertMilliSecToHMS(mediaPlayer.getDuration()));
      }
      seekBarTouch();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  private Runnable runnable = new Runnable() {
    @Override
    public void run() {
      int currentPosition = mediaPlayer.getCurrentPosition();
      int total = mediaPlayer.getDuration();
      while (mediaPlayer != null && mediaPlayer.isPlaying() && currentPosition < total) {
        try {
          Thread.sleep(1000);
          currentPosition = mediaPlayer.getCurrentPosition();
        } catch (Exception e) {
          return;
        }
        seekBar.setProgress(currentPosition);
//        txt_start_time.setText(convertMilliSecToHMS(mediaPlayer.getCurrentPosition()));

      }
    }
  };
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
      playAndPauseSong();
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
  private void seekBarTouch() {
    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
        txt_start_time.setText(convertMilliSecToHMS(progress));
        double percent = progress / (double) seekBar.getMax();
        int offset = seekBar.getThumbOffset();
        int seekWidth = seekBar.getWidth();
        int labelWidth = txt_start_time.getWidth();
        int val = (int) Math.round(percent * (seekWidth - 2 * offset));
        if (progress > 0 && mediaPlayer != null && !mediaPlayer.isPlaying()) {
          img_play.setImageResource(R.drawable.ic_playlist_play_black_24dp);
          seekBar.setProgress(mediaPlayer.getCurrentPosition());
        }
      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
          mediaPlayer.seekTo(seekBar.getProgress());
        }
      }
    });
  }
}
