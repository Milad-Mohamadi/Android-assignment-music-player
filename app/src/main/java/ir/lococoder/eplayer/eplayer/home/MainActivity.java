package ir.lococoder.eplayer.eplayer.home;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ir.lococoder.eplayer.R;
import ir.lococoder.eplayer.common.Common;
import ir.lococoder.eplayer.common.LAppCompatActivity;
import ir.lococoder.eplayer.common.LExtractData;
import ir.lococoder.eplayer.common.LWS;
import ir.lococoder.eplayer.common.LWSS;


import static ir.lococoder.eplayer.common.Common.setLog;


public class MainActivity extends LAppCompatActivity {
  private String feed = "https://anchor.fm/s/6810cbc/podcast/rss";
  private JSONObject dataPodcast;
  private JSONObject receivedData;
  private String playCount;
  private String likedCount;
  private String mal;
  private String subscribe = "0";

  private JSONObject lastEpisode;
  private JSONObject rating;
  private String liked;
  private Ui ui;
  private int color;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ui = new Ui();
    new Founder(this)
      .noActionbar()
      .extractUi(ui)
      .contentView(R.layout.activity_main)
      .requestFeatures()
      .build();


    getPodcastData();
  }

  private class Ui {
//  RecyclerView recycler_episodes;
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
              public void isAccepted(String song_list, String billboard, String error_code) {

              }

              @Override
              public void isForbiden(String response) {
                Common.toastDenied(MainActivity.this);
              }
            }).read();
        }

        @Override
        public void onFail(int statusCode) {
          setLog("connection failed :", "asd");

        }
      })
      .read();
  }
}
