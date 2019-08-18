package ir.lococoder.eplayer.common;

import android.app.Activity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


public class LExtractData {
  private Activity activity;
  private String data;
  private Listener listener;
  private String status;
  private String song_list;
  private String billboard;
  private String error_code;

  public interface Listener {
    void isAccepted(String song_list, String billboard, String error_code);

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
      song_list = received_data.getString("song_list");
      billboard = received_data.getString("billboard");
      error_code = received_data.getString("error_code");
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
        listener.isAccepted(song_list,billboard, error_code);
//    switch (status) {
//      case ("202"):
//        break;
//      case ("203"):
//        break;
//      case ("304"):
////        listener.isNotModified(song_list);
//        activity.runOnUiThread(new Runnable() {
//          @Override
//          public void run() {
//            LToast customToast=new LToast(activity);
//            customToast.toastIcon(R.drawable.ic_android_black_24dp)
//              .toastLayout(R.layout.l_toast_dark_just_text)
//              .toastText(R.string.toast_error)
//              .toastShow();
//          }
//        });        break;
//      case ("400"):
////        listener.isBadRequest(song_list);
//        activity.runOnUiThread(new Runnable() {
//          @Override
//          public void run() {
//            LToast customToast=new LToast(activity);
//            customToast.toastIcon(R.drawable.ic_android_black_24dp)
//              .toastLayout(R.layout.l_toast_dark_just_text)
//              .toastText(R.string.toast_error)
//              .toastShow();
//          }
//        });
//        break;
//      case ("401"):
////        listener.isUnauthorized(song_list);
//        activity.runOnUiThread(new Runnable() {
//          @Override
//          public void run() {
//            LToast customToast=new LToast(activity);
//            customToast.toastIcon(R.drawable.ic_android_black_24dp)
//              .toastLayout(R.layout.l_toast_dark_just_text)
//              .toastText(R.string.toast_unauthorized)
//              .toastShow();
//          }
//        });
//        break;
//      case ("403"):
//        listener.isForbiden(song_list);
////
//        break;
//      case ("404"):
////        listener.isNotFound(song_list);
//        activity.runOnUiThread(new Runnable() {
//          @Override
//          public void run() {
//            LToast customToast=new LToast(activity);
//            customToast.toastIcon(R.drawable.ic_android_black_24dp)
//              .toastLayout(R.layout.l_toast_dark_just_text)
//              .toastText(R.string.toast_error)
//              .toastShow();
//          }
//        });        break;
//      case ("406"):
////        listener.isNotAcceptable(song_list);
//        activity.runOnUiThread(new Runnable() {
//          @Override
//          public void run() {
//            LToast customToast=new LToast(activity);
//            customToast.toastIcon(R.drawable.ic_android_black_24dp)
//              .toastLayout(R.layout.l_toast_dark_just_text)
//              .toastText(R.string.toast_error)
//              .toastShow();
//          }
//        });
//        break;
//      case ("505"):
//        activity.runOnUiThread(new Runnable() {
//          @Override
//          public void run() {
//            try {
//              activity.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                  LToast customToast = new LToast(activity);
//                  customToast.toastIcon(R.drawable.ic_android_black_24dp)
//                    .toastLayout(R.layout.l_toast_dark_just_text)
//                    .toastText(R.string.update)
//                    .toastShow();
//                }
//              });
//            } catch (Exception e) {
//            }
//            Uri uri = Uri.parse("https://cafebazaar.ir/app/com.example.moro.locopod/"); // missing 'http://' will cause crashed
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            activity.startActivity(intent);
//          }
//        });
//        break;
//    }
  }
}
