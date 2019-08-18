package ir.lococoder.eplayer.system.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class Led extends AppCompatTextView {

  public Led(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  public Led(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public Led(Context context) {
    super(context);
    init();
  }

  public void init() {
    Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/advanced_dot_digital-7.ttf");
    setTypeface(tf ,1);

  }
}