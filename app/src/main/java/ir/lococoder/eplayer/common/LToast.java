package ir.lococoder.eplayer.common;


import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import ir.lococoder.eplayer.R;


public class LToast {
  Activity activity;
  private int layoutId;
  private int imageId;
  private int text;

  public LToast(Activity activity) {
    this.activity = activity;
  }

  public LToast toastLayout(int layoutId) {
    this.layoutId = layoutId;
    return this;
  }

  public LToast toastIcon(int imageId) {
    this.imageId = imageId;
    return this;
  }

  public LToast toastText(int text) {
    this.text = text;
    return this;
  }

  public void toastShow() {
    try{

    LayoutInflater inflater = activity.getLayoutInflater();
    View layout = inflater.inflate(layoutId,
      (ViewGroup) activity.findViewById(R.id.toast_layout_root));
    ImageView image = (ImageView) layout.findViewById(R.id.image);
    TextView textView = (TextView) layout.findViewById(R.id.text);
    Glide.with(activity)
      .load(imageId)
      .asBitmap()
      .centerCrop()
      .into(image);
    textView.setText(text);
    Toast toast = new Toast(activity);
    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
    toast.setDuration(Toast.LENGTH_LONG);
    toast.setView(layout);
    toast.show();
    }catch (Exception e){

    }
  }
}

