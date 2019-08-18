package ir.lococoder.eplayer.system.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;
import ir.lococoder.eplayer.common.Common;
import ir.lococoder.eplayer.system.Config;

import static ir.lococoder.eplayer.system.Config.LANG_FA;


public class EditTextLight extends AppCompatEditText {

  public EditTextLight(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  public EditTextLight(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public EditTextLight(Context context) {
    super(context);
    init();
  }

  public void init() {
    if (Common.getSharedParams(getContext(), Config.SHARAED_PARAMS_language).equals(LANG_FA)){
      Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/IRANSansMobile_Light.ttf");
      setTypeface(tf ,1);
    }else {
      Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/IRANSansMobile_Light.ttf");
      setTypeface(tf, 1);
    }
  }
}