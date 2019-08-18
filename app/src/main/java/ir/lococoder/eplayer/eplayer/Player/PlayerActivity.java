package ir.lococoder.eplayer.eplayer.Player;


import android.os.Bundle;

import ir.lococoder.eplayer.R;
import ir.lococoder.eplayer.common.LAppCompatActivity;

public class PlayerActivity extends LAppCompatActivity {

  private Ui ui;



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



//    getBundle();



//    ui.img_next_track.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View view) {
//        ui.txt_total_time.setText(getResources().getString(R.string.loading));
//        ui.txt_start_time.setText("00:00");
//        isNextTrack = true;
//        isPreviusTrack = false;
//        runOnUiThread(new Runnable() {
//          @Override
//          public void run() {
//
//            firstPlay();
//          }
//        });


//      }
//    });
//    ui.img_previews_track.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View view) {
//        ui.txt_total_time.setText(getResources().getString(R.string.loading));
//        ui.txt_start_time.setText("00:00");
//        isPreviusTrack = true;
//        isNextTrack = false;
//        runOnUiThread(new Runnable() {
//          @Override
//          public void run() {
//
//            firstPlay();
//          }
//        });


//      }
//    });
  }

  private void getBundle() {
//    Intent intent = getIntent();
//    Bundle bundle = intent.getExtras();
//    if (intent.hasExtra("from")) from = bundle.getString("from");


  }


  private void loadMp3() {
  }

  private class Ui {
//    ViewGroup rootPlayer;
//    ViewPager viewpager;
//    ImageView img_next_track, img_previews_track;
//    TextView   txt_total_time,txt_start_time;
  }



  @Override
  public void onBackPressed() {
    super.onBackPressed();
    overridePendingTransition( R.anim.slide_up_to_down, R.anim.slide_up_to_down_2);

  }

}
