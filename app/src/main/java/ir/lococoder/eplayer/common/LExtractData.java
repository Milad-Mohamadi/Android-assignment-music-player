package ir.lococoder.eplayer.common;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import ir.lococoder.eplayer.R;


public class LExtractData {
  private Activity activity;
  private String data;
  private Listener listener;
  private String status;
  private String response;
  private String request;
  private String userType;

  public interface Listener {
    void isAccepted(String response, String userType);

    void isForbiden(String response);


  }

  public LExtractData(Activity activity) {
    this.activity = activity;
    this.data = data;
  }


  public LExtractData extractReceivedData(String data) {
    Log.i("locoCoder1101", "<=====:" + data);
    Log.i("locoCoder1101", ".................................................................................................................................................................................................................................................");
    try {
      JSONObject received_data = new JSONObject(data);
      status = received_data.getString("status");
      response = received_data.getString("response");
      request = received_data.getString("request");
      userType = received_data.getString("userType");
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return this;
  }

  public LExtractData listener(Listener value) {
    listener = value;
    return this;
  }

  public void read() {
    String data = null;
    switch (status) {
      case ("202"):
        listener.isAccepted(response,userType);
        break;
      case ("203"):
        break;
      case ("304"):
//        listener.isNotModified(response);
        activity.runOnUiThread(new Runnable() {
          @Override
          public void run() {
            LToast customToast=new LToast(activity);
            customToast.toastIcon(R.drawable.ic_android_black_24dp)
              .toastLayout(R.layout.l_toast_dark_just_text)
              .toastText(R.string.toast_error)
              .toastShow();
          }
        });        break;
      case ("400"):
//        listener.isBadRequest(response);
        activity.runOnUiThread(new Runnable() {
          @Override
          public void run() {
            LToast customToast=new LToast(activity);
            customToast.toastIcon(R.drawable.ic_android_black_24dp)
              .toastLayout(R.layout.l_toast_dark_just_text)
              .toastText(R.string.toast_error)
              .toastShow();
          }
        });
        break;
      case ("401"):
//        listener.isUnauthorized(response);
        activity.runOnUiThread(new Runnable() {
          @Override
          public void run() {
            LToast customToast=new LToast(activity);
            customToast.toastIcon(R.drawable.ic_android_black_24dp)
              .toastLayout(R.layout.l_toast_dark_just_text)
              .toastText(R.string.toast_unauthorized)
              .toastShow();
          }
        });
        break;
      case ("403"):
        listener.isForbiden(response);
//
        break;
      case ("404"):
//        listener.isNotFound(response);
        activity.runOnUiThread(new Runnable() {
          @Override
          public void run() {
            LToast customToast=new LToast(activity);
            customToast.toastIcon(R.drawable.ic_android_black_24dp)
              .toastLayout(R.layout.l_toast_dark_just_text)
              .toastText(R.string.toast_error)
              .toastShow();
          }
        });        break;
      case ("406"):
//        listener.isNotAcceptable(response);
        activity.runOnUiThread(new Runnable() {
          @Override
          public void run() {
            LToast customToast=new LToast(activity);
            customToast.toastIcon(R.drawable.ic_android_black_24dp)
              .toastLayout(R.layout.l_toast_dark_just_text)
              .toastText(R.string.toast_error)
              .toastShow();
          }
        });
        break;
      case ("505"):
        activity.runOnUiThread(new Runnable() {
          @Override
          public void run() {
            try {
              activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                  LToast customToast = new LToast(activity);
                  customToast.toastIcon(R.drawable.ic_android_black_24dp)
                    .toastLayout(R.layout.l_toast_dark_just_text)
                    .toastText(R.string.update)
                    .toastShow();
                }
              });
            } catch (Exception e) {
            }
            Uri uri = Uri.parse("https://cafebazaar.ir/app/com.example.moro.locopod/"); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            activity.startActivity(intent);
          }
        });
        break;
    }
  }
}
