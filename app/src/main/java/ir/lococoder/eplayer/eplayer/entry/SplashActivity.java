package ir.lococoder.eplayer.eplayer.entry;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ir.lococoder.eplayer.R;
import ir.lococoder.eplayer.common.LAppCompatActivity;
import ir.lococoder.eplayer.eplayer.home.MainActivity;

import static ir.lococoder.eplayer.common.Common.alphaAnim;


public class SplashActivity extends LAppCompatActivity {

  TextView txt_state;
  ViewGroup lyt_logo;
  Handler ha = new Handler();
  ImageView img_logo;
  private boolean isInitToStart = false;


  private class Ui {

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Ui ui = new Ui();
    new Founder(this)
      .extractUi(ui)
      .noTitlebar()
      .contentView(R.layout.activity_splash)
      .noActionbar()
      .requestFeatures()
      .build();

    txt_state = (TextView) findViewById(R.id.txt_state);
    img_logo = (ImageView) findViewById(R.id.img_logo);
    lyt_logo = (ViewGroup) findViewById(R.id.lyt_logo);
    alphaAnim(img_logo);
    alphaAnim(lyt_logo);

    checkConnection();




}

  private void checkAndStart() {
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        if (isInitToStart) {
          startApp();
        } else {
          checkAndStart();
          isInitToStart = true;
        }
      }
    }, 2500);
  }

  //=========================================================================================================================
  private void checkConnection() {
    if (isConnectedToInternet()) {
      txt_state.setText("Please Wait ,Connecting to server...");
      ha.removeCallbacks(myRunnable);
      isInitToStart=true;
      checkAndStart();
    } else {
      txt_state.setText("Check your Internet Connection");
      ha.postDelayed(myRunnable, 5000);
    }
  }

  private Runnable myRunnable = new Runnable() {
    @Override
    public void run() {
      checkConnection();
    }

  };

  //=========================================================================================================================



  //=========================================================================================================================
  public void versionDeprecated() {

  }

  //=========================================================================================================================
  protected void startApp() {
//    sendAndDeleteEpisodePartDB(splash.this);
    if (isInitToStart) {
      Intent intent = new Intent(SplashActivity.this, MainActivity.class);
      startActivity(intent);
      overridePendingTransition(R.anim.fadein, R.anim.fade_out);

    }
    SplashActivity.this.finish();
  }

  //=========================================================================================================================
  public boolean isConnectedToInternet() {
    ConnectivityManager connectivity = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    if (connectivity != null) {
      NetworkInfo[] info = connectivity.getAllNetworkInfo();
      if (info != null)
        for (int i = 0; i < info.length; i++)
          if (info[i].getState() == NetworkInfo.State.CONNECTED) {
            return true;
          }
    }
    return false;
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    isInitToStart = false;
  }
}
