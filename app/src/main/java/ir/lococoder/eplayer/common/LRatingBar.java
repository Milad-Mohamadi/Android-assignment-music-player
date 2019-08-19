package ir.lococoder.eplayer.common;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.StyleableRes;
import ir.lococoder.eplayer.R;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)//For backward-compability
public class LRatingBar extends LinearLayout {
  @StyleableRes
  int index0 = 0;
  View rootView;
  ViewGroup ratingbar;
  public onChangeRateListener onChangeRateListener;
  ImageView star_1, star_2, star_3, star_4, star_5;
  public int rate = 0;

 public interface onChangeRateListener{
    void onchangeRate(int rate);
  }

  public LRatingBar(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public void onChangeRate(onChangeRateListener value){
    this.onChangeRateListener=value;
  }
  private void init(Context context, AttributeSet attrs) {
    inflate(context, R.layout.l_rating_bar, this);

    int[] sets = {R.attr.rate};
    TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);
    int rate = typedArray.getInt(R.styleable.LRatingBar_rate,index0);
    typedArray.recycle();

    initComponents();
    setRate(rate);
  }

  private void initComponents() {

    ratingbar= findViewById(R.id.ratingbar);
    star_1= findViewById(R.id.star_1);
    star_2= findViewById(R.id.star_2);
    star_3= findViewById(R.id.star_3);
    star_4= findViewById(R.id.star_4);
    star_5= findViewById(R.id.star_5);


  }

  public void clickable(boolean i){
    if (i){
     star_1.setOnClickListener(new OnClickListener() {
       @Override
       public void onClick(View view) {
         emptyAllStar();
        setRate(1);
        onChangeRateListener.onchangeRate(getRate());
       }
     });
     star_2.setOnClickListener(new OnClickListener() {
       @Override
       public void onClick(View view) {
         emptyAllStar();
        setRate(2);
         onChangeRateListener.onchangeRate(getRate());
       }
     });
     star_3.setOnClickListener(new OnClickListener() {
       @Override
       public void onClick(View view) {
         emptyAllStar();
        setRate(3);
         onChangeRateListener.onchangeRate(getRate());
       }
     });
     star_4.setOnClickListener(new OnClickListener() {
       @Override
       public void onClick(View view) {
         emptyAllStar();
        setRate(4);
         onChangeRateListener.onchangeRate(getRate());
       }
     });
     star_5.setOnClickListener(new OnClickListener() {
       @Override
       public void onClick(View view) {
         emptyAllStar();
        setRate(5);
         onChangeRateListener.onchangeRate(getRate());
       }
     });

    }else {

    }
  }



  public int getRate() {
    return rate;
  }

  public void setRate(int value) {
    rate=value;
    switch (rate){
      case 1:
        star_1.setImageResource(R.drawable.ui_star_filled);
        break;
      case 2:
        star_1.setImageResource(R.drawable.ui_star_filled);
        star_2.setImageResource(R.drawable.ui_star_filled);
        break;
      case 3:
        star_1.setImageResource(R.drawable.ui_star_filled);
        star_2.setImageResource(R.drawable.ui_star_filled);
        star_3.setImageResource(R.drawable.ui_star_filled);
        break;
      case 4:
        star_1.setImageResource(R.drawable.ui_star_filled);
        star_2.setImageResource(R.drawable.ui_star_filled);
        star_3.setImageResource(R.drawable.ui_star_filled);
        star_4.setImageResource(R.drawable.ui_star_filled);
        break;
      case 5:
        star_1.setImageResource(R.drawable.ui_star_filled);
        star_2.setImageResource(R.drawable.ui_star_filled);
        star_3.setImageResource(R.drawable.ui_star_filled);
        star_4.setImageResource(R.drawable.ui_star_filled);
        star_5.setImageResource(R.drawable.ui_star_filled);
        break;
    }
 }

  private void emptyAllStar(){
    star_1.setImageResource(R.drawable.ui_star_empty);
    star_2.setImageResource(R.drawable.ui_star_empty);
    star_3.setImageResource(R.drawable.ui_star_empty);
    star_4.setImageResource(R.drawable.ui_star_empty);
    star_5.setImageResource(R.drawable.ui_star_empty);
  }

}