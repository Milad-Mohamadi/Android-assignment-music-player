package ir.lococoder.eplayer.system.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;
import ir.lococoder.eplayer.common.Common;
import ir.lococoder.eplayer.system.Config;

import static ir.lococoder.eplayer.system.Config.LANG_FA;


public class EditTextExtraBold extends AppCompatEditText {

  public EditTextExtraBold(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  public EditTextExtraBold(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public EditTextExtraBold(Context context) {
    super(context);
    init();
  }

  public void init() {
    if (Common.getSharedParams(getContext(), Config.SHARAED_PARAMS_language).equals(LANG_FA)){
      Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/IRANSansMobile_Bold.ttf");
      setTypeface(tf ,1);
    }else {
      Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-ExtraBold.ttf");
      setTypeface(tf, 1);
    }
  }
}