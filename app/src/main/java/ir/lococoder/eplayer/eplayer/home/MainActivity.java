package ir.lococoder.eplayer.eplayer.home;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.lococoder.eplayer.R;
import ir.lococoder.eplayer.common.Common;
import ir.lococoder.eplayer.common.LAppCompatActivity;
import ir.lococoder.eplayer.common.LExtractData;
import ir.lococoder.eplayer.common.LRecyclerStruct;
import ir.lococoder.eplayer.common.LWS;


import static ir.lococoder.eplayer.common.Common.setLog;


public class MainActivity extends LAppCompatActivity {

  private Ui ui;
  private int color;
  RecyclerView recycler_music;
  ArrayList<LRecyclerStruct> recyclerInformation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ui = new Ui();
    new Founder(MainActivity.this)
      .noActionbar()
      .extractUi(ui)
      .contentView(R.layout.activity_main)
      .requestFeatures()
      .build();

    recycler_music=(RecyclerView)findViewById(R.id.recycler_music);
    recycler_music.setLayoutManager(new LinearLayoutManager(MainActivity.this));
      recyclerInformation = new ArrayList<>();
    getPodcastData();
  }

  private class Ui {
//  TextView txt_episode_count,txt_show_name;
//  ImageView img_show;
  }


  public void getPodcastData() {
    LWS lws = new LWS();
    JSONObject jsonObject = new JSONObject();
    lws
//      .url("http://185.105.237.64:8787/api/mobile/PlayList/Get/1/20/0")
      .url("https://pod.cor30.com/example")
      .inputArguments(jsonObject)
      .enableCache(false)

      .listener(new LWS.Listener() {
        @Override
        public void onSuccess(String data) {
          setLog("received data :", data);
          LExtractData extractData = new LExtractData(MainActivity.this);
          extractData.extractReceivedData(data)
            .listener(new LExtractData.Listener() {
              @Override
              public void isAccepted(final String song_list, final String billboard, String error_code) {
                runOnUiThread(new Runnable() {
                  @Override
                  public void run() {


                try {
                  JSONArray songs = new JSONArray(song_list);
                  for(int i=0;i<songs.length();i++){

                  color = i % 2 == 0 ? R.color.colorBackgroundRecyclerLight : R.color.colorBackgroundRecyclerDark;
                    JSONObject song = songs.getJSONObject(i);
                    String special_type = song.getString("special_type");
                    String pic_huge=song.getString("pic_huge");
                    String ting_uid=song.getString("ting_uid");
                    String pic_premium=song.getString("pic_premium");
                    String havehigh=song.getString("havehigh");
                    String si_proxycompany=song.getString("si_proxycompany");
                    String author=song.getString("author");
                    String toneid=song.getString("toneid");
                    String has_mv=song.getString("has_mv");
                    String song_id=song.getString("song_id");
                    String title=song.getString("title");
                    String artist_id=song.getString("artist_id");
                    String artistInfo=song.getString("artistInfo");
                    String songListId=song.getString("songListId");
                    String songList=song.getString("songList");
                    String lrclink=song.getString("lrclink");
                    String relate_status=song.getString("relate_status");
                    String learn=song.getString("learn");
                    String pic_big=song.getString("pic_big");
                    String play_type=song.getString("play_type");
                    String album_id=song.getString("album_id");
                    String bitrate_id=song.getString("bitrate_id");
                    String bitrate=song.getString("bitrate");
                    String pic_radio=song.getString("pic_radio");
                    String bitrate_fee=song.getString("bitrate_fee");
                    String song_source=song.getString("song_source");
                    String all_artist_id=song.getString("all_artist_id");
                    String all_artist_ting_uid=song.getString("all_artist_ting_uid");
                    String piao_id=song.getString("piao_id");
                    String charge=song.getString("charge");
                    String copy_type=song.getString("copy_type");
                    String all_rate=song.getString("all_rate");
                    String korean_bb_song=song.getString("korean_bb_song");
                    String is_first_publish=song.getString("is_first_publish");
                    String has_mv_mobile=song.getString("has_mv_mobile");
                    String album_title=song.getString("album_title");
                    String pic_small=song.getString("pic_small");
                    String album_no=song.getString("album_no");
                    String resource_type_ext=song.getString("resource_type_ext");
                    int id=Integer.parseInt(song.getString("id"));


                    recyclerInformation.add(new RecyclerInfo(title,album_title,song_source,pic_small,pic_radio,pic_big,pic_premium,pic_huge,lrclink,id,color));
                    RecyclerAdapter recyclerAdapter = new RecyclerAdapter(MainActivity.this, recyclerInformation);
                    recycler_music.setAdapter(recyclerAdapter);

                  }
                  JSONObject billboars = new JSONObject(billboard);
                    String billboard_type=billboars.getString("billboard_type");
                    String billboard_no=billboars.getString("billboard_no");
                    String update_date=billboars.getString("update_date");
                    String billboard_songnum=billboars.getString("billboard_songnum");
                    String havemore=billboars.getString("havemore");
                    String name=billboars.getString("name");
                    String comment=billboars.getString("comment");
                    String pic_s192=billboars.getString("pic_s192");
                    String pic_s640=billboars.getString("pic_s640");
                    String pic_s444=billboars.getString("pic_s444");
                    String pic_s260=billboars.getString("pic_s260");
                    String pic_s210=billboars.getString("pic_s210");
                    String web_url=billboars.getString("web_url");
                    String color=billboars.getString("color");
                    String bg_color=billboars.getString("bg_color");
                    String bg_pic=billboars.getString("bg_pic");
                    String id=billboars.getString("id");





                } catch (JSONException e1) {
                  e1.printStackTrace();
                }
                  }
                });
              }

              @Override
              public void isForbiden(String response) {
                Common.toastDenied(MainActivity.this);
              }
            }).read();
        }

        @Override
        public void onFail(int statusCode) {

        }
      })
      .read();
  }
}
