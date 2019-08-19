package ir.lococoder.eplayer.common;


import android.Manifest;
import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Pattern;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import ir.lococoder.eplayer.R;
import ir.lococoder.eplayer.system.AssetImageReader;
import ir.lococoder.eplayer.system.Base;
import ir.lococoder.eplayer.system.PernissionRequestHelper;
import jp.wasabeef.blurry.Blurry;

import static android.text.Html.fromHtml;
import static ir.lococoder.eplayer.system.Config.COUNTRY_EN;
import static ir.lococoder.eplayer.system.Config.COUNTRY_FA;
import static ir.lococoder.eplayer.system.Config.LANG_EN;
import static ir.lococoder.eplayer.system.Config.LANG_FA;
import static ir.lococoder.eplayer.system.Config.SHARAED_PARAMS_TOKEN;
import static ir.lococoder.eplayer.system.Config.SHARAED_PARAMS_email;
import static ir.lococoder.eplayer.system.Config.SHARAED_PARAMS_pass;
import static ir.lococoder.eplayer.system.Config.SHARAED_PARAMS_phone;
import static ir.lococoder.eplayer.system.Config.SHARAED_PARAMS_randomNumber;
import static ir.lococoder.eplayer.system.Config.SHARAED_PARAMS_telegram;
import static ir.lococoder.eplayer.system.Config.SHARAED_PARAMS_type;
import static ir.lococoder.eplayer.system.Config.SHARAED_PARAMS_userId;
import static ir.lococoder.eplayer.system.Config.SHARAED_PARAMS_userName;
import static java.lang.Integer.parseInt;


public class Common extends LAppCompatActivity {


  private static final String MY_PREFS_NAME = "EPlayer";
  private static String TAG = "lococoder1101";

  public void closeKeyboardByClick(Context context, EditText edt) {

  }

  public static int displayWidth(Context context) {
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    return size.x;
  }

  public static int displayHeight(Context context) {
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    return size.y;
  }

  public static boolean isEmpty(EditText etText) {
    if (etText.getText().toString().trim().length() > 0)
      return false;

    return true;
  }



  public static String briefNumber(String value) {
    int inputs = 0;
    try {
      inputs = Integer.parseInt(value);
    } catch (Exception e) {
    }
    String output = null;
    if (value.length() >= 4 && value.length() < 7) {
      int abs = (int) Math.abs(inputs / 1000);
      String ceil = (int) inputs % 1000 + "";
      int ceil0 = 0;
      if (ceil.length() > 1) {
//       ceil0=ceil.charAt(0);
        ceil0 = Integer.parseInt(ceil.substring(0, 1));
      }
      if (ceil0 == 0) {
        output = abs + "K";
      } else {
        output = abs + "." + ceil0 + "K";
      }
    } else if (value.length() >= 7 && value.length() < 10) {
      int abs = (int) Math.abs(inputs / 1000000);
      String ceil = (int) inputs % 1000000 + "";
      int ceil0 = 0;
      if (ceil.length() > 1) {
//       ceil0=ceil.charAt(0);
        ceil0 = Integer.parseInt(ceil.substring(0, 1));
      }
      if (ceil0 == 0) {
        output = abs + "M";
      } else {
        output = abs + "." + ceil0 + "M";
      }
    } else {
      output = value;
    }
    return output;
  }


  public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
  }


  public static Bitmap getScreenShot(View view) {
    View screenView = view;
    screenView.setDrawingCacheEnabled(true);
    Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
    screenView.setDrawingCacheEnabled(false);
    return bitmap;
  }

  public static void setLog(String title, String description) {
    Log.i(TAG, title + " ---> " + description);
  }

  public static Bitmap takeScreenShot(View v) {
    v.setDrawingCacheEnabled(true);
    v.buildDrawingCache(true);
    Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
    v.setDrawingCacheEnabled(false);
    return b;

  }

  public static Bitmap takeScreenShotOfRootView(View v) {
    return takeScreenShot(v.getRootView());
  }
  public static Bitmap takeScreenShot(Activity activity) {
    View view = activity.getWindow().getDecorView();
    view.setDrawingCacheEnabled(true);
    view.buildDrawingCache();
    Bitmap b1 = view.getDrawingCache();
    Rect frame = new Rect();
    activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
    int statusBarHeight = frame.top;
    int width = activity.getWindowManager().getDefaultDisplay().getWidth();
    int height = activity.getWindowManager().getDefaultDisplay().getHeight();

    Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height - statusBarHeight);
    view.destroyDrawingCache();
    return b;
  }

  public static void setBlurBackground(Context context, View root, ViewGroup mask, ImageView imageMask) {
    Bitmap bitmap = getScreenShot(root);
    Drawable image = new BitmapDrawable(context.getResources(), bitmap);
    imageMask.setImageDrawable(image);
    mask.setVisibility(View.VISIBLE);
    Blurry.with(context)
      .radius(13)
      .async()
      .sampling(10)
      .color(Color.argb(66, 0, 0, 0))
      .onto(mask);
  }

  public static void setBlur(Context context, ViewGroup root) {
    Blurry.with(context)
      .radius(13)
      .async()
      .sampling(10)
      .color(Color.argb(66, 0, 0, 0))
      .onto(root);
  }

  public static void setBlurHard(Context context, ViewGroup root) {
    Blurry.with(context)
      .radius(20)
      .async()
      .sampling(10)
      .color(Color.argb(88, 0, 0, 0))
      .onto(root);
  }

  public static void deleteBlur(ViewGroup root) {
    Blurry.delete(root);

  }

  Bitmap getViewScreenshot(View v) {
    v.setDrawingCacheEnabled(true);
    Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
    v.setDrawingCacheEnabled(false);

    return b;
  }

  public static void setJustify(Context context, ViewGroup parentText, int text) {
    WebView view = new WebView(context);
    view.setVerticalScrollBarEnabled(true);
    view.setBackgroundColor(Color.argb(0, 0, 0, 0));
    parentText.addView(view);
//    String summary = "<html><body>You scored <b>192</b> points.</body></html>";
    String youtContentStr = String.valueOf(
      fromHtml("<![CDATA[<body  contentEditable=\"false\" style=\"text-align:justify;\">"
        + context.getResources().getString(text)
        + "</body>]]>"));
    view.loadData(youtContentStr, "text/html; charset=utf-8", "utf-8");
    view.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View view) {
        return true;
      }
    });
    view.setLongClickable(false);

//    view.loadData(youtContentStr, "text/html", "utf-8");
  }

  public static WebView setJustify(Context context, String text) {
    try {
      text = text.replace("style", "styl0");
    } catch (Exception e) {

    }

    WebView view = new WebView(context);
    view.setVerticalScrollBarEnabled(true);
    view.setBackgroundColor(Color.argb(0, 0, 0, 0));
//    parentText.addView(view);
    String text1 =
      "<html>" +
        "<head>" +
        "    <meta http-equiv=\"content-type\" content=\"text/html;\" charset=\"UTF-8\">" +
        "    <style>" +
        "                @font-face {" +
        "                    font-family: \"IRANSansMobile\";" +
        "                    src: url('fonts/IRANSansMobile.ttf');" +
        "                }" +
        "                .myClass {" +
        "                font-family:\"IRANSansMobile\";" +
        "                type=text/css;" +
        "                color:#f05" +
        "                contentEditable=false ;" +
        "                text-align:justify ;" +
        "                direction: rtl;" +
        "          }" +
        "    </style>" +
        "</head>" +
        "<body>" +
        "<h3 class=\"myClass\">" +
        text +
        "  </h3>" +
        "</body>" +
        "</html>";
    view.loadDataWithBaseURL("file:///android_asset/", text1, "text/html", "utf-8", null);
    view.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View view) {
        return true;
      }
    });
    view.setLongClickable(false);
    return view;
//    view.loadData(youtContentStr, "text/html", "utf-8");
  }

  public static void setJustify(Context context, ViewGroup parentText, String text, String font) {

    try {
      text = text.replace("style", "styl0");
    } catch (Exception e) {

    }


    //    setLog("text",text);
    parentText.removeAllViews();
    WebView view = new WebView(context);
    view.setVerticalScrollBarEnabled(true);
    view.setBackgroundColor(Color.argb(0, 0, 0, 0));
    parentText.addView(view);
    String text1 =
      "<html>" +
        "<head>" +
        "    <meta http-equiv=\"content-type\" content=\"text/html;\" charset=\"UTF-8\">" +
        "    <style>" +
        "                @font-face { \n" +
        "                    font-family: \"IRANSansMobile_Light\"; \n" +
        "                    src: url('fonts/IRANSansMobile_Light.ttf'); \n" +
        "                } \n" +
        "                .myClass {" +
        "                font-family:\"IRANSansMobile_Light\"; \n" +
        "                type=text/css; \n" +
        "                   line-height: 30px; \n" +
        "                     font-size: 16px;\n" +
        "                  contentEditable=false ; \n" +
        "                text-align:justify ; \n" +
        "                direction: rtl; \n" +
        "          }" +
        "    </style>" +
        "</head>" +
        "<body>" +
        "<h3 class=\"myClass\">" +
        text +
        "  </h3>" +
        "</body>" +
        "</html>";
    view.loadDataWithBaseURL("file:///android_asset/", text1, "text/html", "utf-8", null);
    view.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View view) {
        return true;
      }
    });
    view.setLongClickable(false);
  }

  public static void setJustifyLittle(Context context, ViewGroup parentText, String text, String font) {

    try {
      text = text.replace("style", "styl0");
    } catch (Exception e) {

    }


    //    setLog("text",text);
    parentText.removeAllViews();
    WebView view = new WebView(context);
    view.setVerticalScrollBarEnabled(true);
    view.setBackgroundColor(Color.argb(0, 0, 0, 0));
    parentText.addView(view);
    String text1 =
      "<html>" +
        "<head>" +
        "    <meta http-equiv=\"content-type\" content=\"text/html;\" charset=\"UTF-8\">" +
        "    <style>" +
        "                @font-face { \n" +
        "                    font-family: \"IRANSansMobile_Light\"; \n" +
        "                    src: url('fonts/IRANSansMobile_Light.ttf'); \n" +
        "                } \n" +
        "                .myClass {" +
        "                font-family:\"IRANSansMobile_Light\"; \n" +
        "                type=text/css; \n" +
        "                   line-height: 20px; \n" +
        "                     font-size: 12px;\n" +
        "                  contentEditable=false ; \n" +
        "                text-align:justify ; \n" +
        "                direction: rtl; \n" +
        "          }" +
        "    </style>" +
        "</head>" +
        "<body>" +
        "<h3 class=\"myClass\">" +
        text +
        "  </h3>" +
        "</body>" +
        "</html>";
    view.loadDataWithBaseURL("file:///android_asset/", text1, "text/html", "utf-8", null);
    view.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View view) {
        return true;
      }
    });
    view.setLongClickable(false);
  }

  public static void setJustifyMediumRTL(Context context, ViewGroup parentText, String text, String font) {
    parentText.removeAllViews();
    WebView view = new WebView(context);
    view.setVerticalScrollBarEnabled(true);
    view.setBackgroundColor(Color.argb(0, 0, 0, 0));
    parentText.addView(view);
    String text1 =
      "<html>" +
        "<head>" +
        "    <meta http-equiv=\"content-type\" content=\"text/html;\" charset=\"UTF-8\">" +
        "    <style>" +
        "                @font-face { \n" +
        "                    font-family: \"IRANSansMobile_Medium\"; \n" +
        "                    src: url('fonts/IRANSansMobile_Medium.ttf'); \n" +
        "                } \n" +
        "                .myClass {" +
        "                font-family:\"IRANSansMobile_Medium\"; \n" +
        "                type=text/css; \n" +
        "                line-height: 30px; \n" +
        "                font-size: 16px;\n" +
        "                contentEditable=false ; \n" +
        "                text-align:justify ; \n" +
        "                direction: rtl; \n" +
        "          }" +
        "    </style>" +
        "</head>" +
        "<body>" +
        "<h3 class=\"myClass\">" +
        text +
        "  </h3>" +
        "</body>" +
        "</html>";
    view.loadDataWithBaseURL("file:///android_asset/", text1, "text/html", "utf-8", null);
    view.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View view) {
        return true;
      }
    });
    view.setLongClickable(false);
  }

  public static void setJustifyMediumLTR(Context context, ViewGroup parentText, String text, String font) {
    parentText.removeAllViews();
    WebView view = new WebView(context);
    view.setVerticalScrollBarEnabled(true);
    view.setBackgroundColor(Color.argb(0, 0, 0, 0));
    parentText.addView(view);
    String text1 =
      "<html>" +
        "<head>" +
        "    <meta http-equiv=\"content-type\" content=\"text/html;\" charset=\"UTF-8\">" +
        "    <style>" +
        "                @font-face { \n" +
        "                    font-family: \"IRANSansMobile_Medium\"; \n" +
        "                    src: url('fonts/IRANSansMobile_Medium.ttf'); \n" +
        "                } \n" +
        "                .myClass {" +
        "                font-family:\"IRANSansMobile_Medium\"; \n" +
        "                type=text/css; \n" +
        "                   line-height: 30px; \n" +
        "                     font-size: 16px;\n" +
        "                  contentEditable=false ; \n" +
        "                text-align:justify ; \n" +
        "                direction: ltr; \n" +
        "          }" +
        "    </style>" +
        "</head>" +
        "<body>" +
        "<h3 class=\"myClass\">" +
        text +
        "  </h3>" +
        "</body>" +
        "</html>";
    view.loadDataWithBaseURL("file:///android_asset/", text1, "text/html", "utf-8", null);
    view.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View view) {
        return true;
      }
    });
    view.setLongClickable(false);
  }

  public static void setJustifyWithShadowRTL(Context context, ViewGroup parentText, String text) {
    try {
      text = text.replace("style", "styl0");
    } catch (Exception e) {

    }
    parentText.removeAllViews();
    WebView view = new WebView(context);
    view.setVerticalScrollBarEnabled(true);
    view.setBackgroundColor(Color.argb(0, 0, 0, 0));
    parentText.addView(view);
    String text1 =
      "<html>" +
        "<head>" +
        "    <meta http-equiv=\"content-type\" content=\"text/html;\" charset=\"UTF-8\">" +
        "    <style>" +
        "                @font-face { \n" +
        "                    font-family: \"IRANSansMobile_Light\"; \n" +
        "                    src: url('fonts/IRANSansMobile_Light.ttf'); \n" +
        "                } \n" +
        "                .myClass {" +
        "                font-family:\"IRANSansMobile_Light\"; \n" +
        "                text-shadow: 3px 3px 5px #000;\n" +
        "                type=text/css; \n" +
        "                color: #FFF;; \n" +
        "                line-height: 25px; \n" +
        "                font-size: 14px;\n" +
        "                contentEditable=false ; \n" +
        "                text-align:justify ; \n" +
        "                direction: rtl; \n" +
        "          }" +
        "    </style>" +
        "</head>" +
        "<body>" +
        "<h3 class=\"myClass\">" +
        text +
        "  </h3>" +
        "</body>" +
        "</html>";
    view.loadDataWithBaseURL("file:///android_asset/", text1, "text/html", "utf-8", null);
    view.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View view) {
        return true;
      }
    });
    view.setLongClickable(false);
  }

  public static void setJustifyWithShadowLTR(Context context, ViewGroup parentText, String text) {

    try {
      text = text.replace("style", "styl0");
    } catch (Exception e) {

    }
    parentText.removeAllViews();
    WebView view = new WebView(context);
    view.setVerticalScrollBarEnabled(true);
    view.setBackgroundColor(Color.argb(0, 0, 0, 0));
    parentText.addView(view);
    String text1 =
      "<html>" +
        "<head>" +
        "    <meta http-equiv=\"content-type\" content=\"text/html;\" charset=\"UTF-8\">" +
        "    <style>" +
        "                @font-face { \n" +
        "                    font-family: \"IRANSansMobile_Light\"; \n" +
        "                    src: url('fonts/IRANSansMobile_Light.ttf'); \n" +
        "                } \n" +
        "                .myClass {" +
        "                font-family:\"IRANSansMobile_Light\"; \n" +
        "                text-shadow: 3px 3px 5px #000;\n" +
        "                type=text/css; \n" +
        "                color: #FFF;; \n" +
        "                   line-height: 25px; \n" +
        "                     font-size: 16px;\n" +
        "                  contentEditable=false ; \n" +
        "                text-align:justify ; \n" +
        "                direction: ltr; \n" +
        "          }" +
        "    </style>" +
        "</head>" +
        "<body>" +
        "<h3 class=\"myClass\">" +
        text +
        "  </h3>" +
        "</body>" +
        "</html>";
    view.loadDataWithBaseURL("file:///android_asset/", text1, "text/html", "utf-8", null);
    view.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View view) {
        return true;
      }
    });
    view.setLongClickable(false);
  }

  //---------------------------------------------------------------------------------------------------------------
//  public static int getAverageColor(Context context, String url) {
////    Glide.with(context)
////      .load(url)
////      .asBitmap()
////      .placeholder(R.drawable.ui_episode_image_fail)
////      .diskCacheStrategy(DiskCacheStrategy.ALL)
////      .centerCrop()
////      .override(500, 500)
////      .into(new SimpleTarget<Bitmap>() {
////        @Override
////        public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
////          int averageColor = calculateAverageColor(bitmap, 1);
////          setLog("averageColor", averageColor + "");
////
////        }
////
////      });
////    return averageColor;
//  }


  public static int calculateAverageColor(android.graphics.Bitmap bitmap, int pixelSpacing) {
    int R = 0;
    int G = 0;
    int B = 0;
    int height = bitmap.getHeight();
    int width = bitmap.getWidth();
    int n = 0;
    int[] pixels = new int[width * height];
    bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
    for (int i = 0; i < pixels.length; i += pixelSpacing) {
      int color = pixels[i];
      R += Color.red(color);
      G += Color.green(color);
      B += Color.blue(color);
      n++;
    }
    return Color.rgb(R / n, G / n, B / n);
  }

  //---------------------------------------------------------------------------------------------------------------
  public static String convertMilliSecToHMS(int MilliSecond) {
    int m = 0;
    int h = 0;
    int x = (int) Math.ceil(MilliSecond / 1000f);
    m = (int) Math.ceil(x / 60);
    h = (int) Math.ceil(m / 60);
    m = m % 60;
    x = x % 60;
    String second = "" + x;
    String minute = "" + m;
    String hour = "" + h;
    String separator = ":";
    if (x < 10) {
      second = "0" + x;
    }
    if (m < 10) {
      minute = "0" + m;
    }

    if (h == 0) {
      return (minute + separator + second);
    } else {
      return (hour + separator + minute + separator + second);
    }
  }

  public static String convertMilliSecToH(int MilliSecond) {
    int m = 0;
    int h = 0;
    int x = (int) Math.ceil(MilliSecond / 1000f);
    m = (int) Math.ceil(x / 60);
    h = (int) Math.ceil(m / 60);
    m = m % 60;
    x = x % 60;
    String second = "" + x;
    String minute = "" + m;
    String hour = "" + h;
    String separator = ":";
    if (x < 10) {
      second = "0" + x;
    }
    if (m < 10) {
      minute = "0" + m;
    }

    if (h == 0) {
      return (0 + "");
    } else {
      return hour;
    }
  }

  public static String convertMilliSecToM(int MilliSecond) {
    int m = 0;
    int h = 0;
    int x = (int) Math.ceil(MilliSecond / 1000f);
    m = (int) Math.ceil(x / 60);
    h = (int) Math.ceil(m / 60);
    m = m % 60;
    x = x % 60;
    String second = "" + x;
    String minute = "" + m;
    String hour = "" + h;
    String separator = ":";
    if (x < 10) {
      second = "0" + x;
    }
    if (m < 10) {
      minute = "0" + m;
    }

    if (m == 0) {
      return (0 + "");
    } else {
      return minute;
    }
  }

  public static String convertMilliSecToS(int MilliSecond) {
    int m = 0;
    int h = 0;
    int x = (int) Math.ceil(MilliSecond / 1000f);
    m = (int) Math.ceil(x / 60);
    h = (int) Math.ceil(m / 60);
    m = m % 60;
    x = x % 60;
    String second = "" + x;
    String minute = "" + m;
    String hour = "" + h;
    String separator = ":";
    if (x < 10) {
      second = "0" + x;
    }
    if (m < 10) {
      minute = "0" + m;
    }

    if (x == 0) {
      return ("0");
    } else {
      return second;
    }
  }

  public static String convertMilliSecToD(int MilliSecond) {
    int m = 0;
    int h = 0;
    int x = (int) Math.ceil(MilliSecond / 1000f);
    m = (int) Math.ceil(x / 60);
    h = (int) Math.ceil(m / 60);
    m = m % 60;
    x = x % 60;
    String second = "" + x;
    String minute = "" + m;
    String hour = "" + h;
    String separator = ":";
    if (x < 10) {
      second = "0" + x;
    }
    if (m < 10) {
      minute = "0" + m;
    }

    if (h == 0) {
      return "0";
    } else {
      return h / 24 + "";
    }
  }

  public static int convertAnyStringTimeToMilliSec(String stringTime) {


    int ItemTime = 0;
    int hour = 0;
    int minute = 0;
    int second = 0;

    String[] time = stringTime.split(":");
    switch (time.length) {
      case 1:
        try {

          second = parseInt(time[0]);
        } catch (Exception e) {

        }
        ItemTime = second;
        break;
      case 2:
        try {
          minute = parseInt(time[0]);
          second = parseInt(time[1]);

        } catch (Exception e) {

        }
        ItemTime = (minute * 60) + second;
        break;
      case 3:
        try {
          hour = parseInt(time[0]);
          minute = parseInt(time[1]);
          second = parseInt(time[2]);
        } catch (Exception e) {

        }
        ItemTime = (minute * 60) + (hour * 3600) + second;
        break;
    }
    return ItemTime * 1000;

  }


  public static String getCurrentTime() {

    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
    Date currentLocalTime = cal.getTime();
    DateFormat date = new SimpleDateFormat("HH:mm a");
// you can get seconds by adding  "...:ss" to it
    date.setTimeZone(TimeZone.getTimeZone("GMT+3:30"));

    String localTime = date.format(currentLocalTime);
    return localTime;
  }

  //---------------------------------------------------------------------------------------------------------------
  public static int setRandomColor() {
    Random rnd = new Random();
    return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
  }

  public static String setRandomColorHex() {
    Random rnd = new Random();
    int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    String hexColor = String.format("#%06X", (0xFFFFFF & color));
    return hexColor;
  }

  public static ArrayList<String> setHatariColorHex() {
    ArrayList<String> colors = new ArrayList<String>();
//  int color = Color.argb(255, 256, 256, 256);
    colors.add("#80BD9E");
    colors.add("#89DA59");
    colors.add("#90AFC5");
    colors.add("#AEBD38");
    colors.add("#C4DFE6");
    colors.add("#A2C523");
    colors.add("#6FB98F");
    colors.add("#FFBB00");
    colors.add("#98DBC6");
    colors.add("#F18D9E");
    colors.add("#E6D72A");
    colors.add("#F4CC70");
    colors.add("#F1F1F2");
    colors.add("#A1D6E2");
    colors.add("#EC96A4");
    colors.add("#DFE166");
    colors.add("#EC96A4");
    colors.add("#E6DF44");
    colors.add("#F9DC24");
    colors.add("#FA6775");
    colors.add("#FFD64D");
    colors.add("#E4EA8C");
    colors.add("#F1F3CE");
    colors.add("#CDCDC0");
    colors.add("#31A9BA");
    colors.add("#80BD9E");
    colors.add("#89DA59");
    colors.add("#90AFC5");
    colors.add("#AEBD38");
    colors.add("#C4DFE6");
    colors.add("#A2C523");
    colors.add("#6FB98F");
    colors.add("#FFBB00");
    colors.add("#98DBC6");
    colors.add("#F18D9E");
    colors.add("#E6D72A");
    colors.add("#F4CC70");
    colors.add("#F1F1F2");
    colors.add("#A1D6E2");
    colors.add("#EC96A4");
    colors.add("#DFE166");
    colors.add("#EC96A4");
    colors.add("#E6DF44");
    colors.add("#F9DC24");
    colors.add("#FA6775");
    colors.add("#FFD64D");
    colors.add("#E4EA8C");
    colors.add("#F1F3CE");
    colors.add("#CDCDC0");
    colors.add("#31A9BA");
    colors.add("#80BD9E");
    colors.add("#89DA59");
    colors.add("#90AFC5");
    colors.add("#AEBD38");
    colors.add("#C4DFE6");
    colors.add("#A2C523");
    colors.add("#6FB98F");
    colors.add("#FFBB00");
    colors.add("#98DBC6");
    colors.add("#F18D9E");
    colors.add("#E6D72A");
    colors.add("#F4CC70");
    colors.add("#F1F1F2");
    colors.add("#A1D6E2");
    colors.add("#EC96A4");
    colors.add("#DFE166");
    colors.add("#EC96A4");
    colors.add("#E6DF44");
    colors.add("#F9DC24");
    colors.add("#FA6775");
    colors.add("#FFD64D");
    colors.add("#E4EA8C");
    colors.add("#F1F3CE");
    colors.add("#CDCDC0");
    colors.add("#31A9BA");
    colors.add("#80BD9E");
    colors.add("#89DA59");
    colors.add("#90AFC5");
    colors.add("#AEBD38");
    colors.add("#C4DFE6");
    colors.add("#A2C523");
    colors.add("#6FB98F");
    colors.add("#FFBB00");
    colors.add("#98DBC6");
    colors.add("#F18D9E");
    colors.add("#E6D72A");
    colors.add("#F4CC70");
    colors.add("#F1F1F2");
    colors.add("#A1D6E2");
    colors.add("#EC96A4");
    colors.add("#DFE166");
    colors.add("#EC96A4");
    colors.add("#E6DF44");
    colors.add("#F9DC24");
    colors.add("#FA6775");
    colors.add("#FFD64D");
    colors.add("#E4EA8C");
    colors.add("#F1F3CE");
    colors.add("#CDCDC0");
    colors.add("#31A9BA");
    colors.add("#80BD9E");
    colors.add("#89DA59");
    colors.add("#90AFC5");
    colors.add("#AEBD38");
    colors.add("#C4DFE6");
    colors.add("#A2C523");
    colors.add("#6FB98F");
    colors.add("#FFBB00");
    colors.add("#98DBC6");
    colors.add("#F18D9E");
    colors.add("#E6D72A");
    colors.add("#F4CC70");
    colors.add("#F1F1F2");
    colors.add("#A1D6E2");
    colors.add("#EC96A4");
    colors.add("#DFE166");
    colors.add("#EC96A4");
    colors.add("#E6DF44");
    colors.add("#F9DC24");
    colors.add("#FA6775");
    colors.add("#FFD64D");
    colors.add("#E4EA8C");
    colors.add("#F1F3CE");
    colors.add("#CDCDC0");
    colors.add("#31A9BA");
    return colors;
  }

  //---------------------------------------------------------------------------------------------------------------
  public static int safeLongToInt(long l) {
    if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
      throw new IllegalArgumentException
        (l + " cannot be cast to int without changing its value.");
    }
    return (int) l;
  }

  //---------------------------------------------------------------------------------------------------------------
  public static void delete(File f, String directory) throws IOException {
    String extension = directory.substring(directory.lastIndexOf("."));
    if (f.isDirectory()) {
      for (File c : f.listFiles()) {
        delete(c, directory);
      }
    } else if (f.getAbsolutePath().endsWith(extension)) {
      if (!f.delete()) {
        new FileNotFoundException("Failed to delete file: " + f);
      }
    }
  }

  public static void rotateAnim(View v) {
    Animation anim = new RotateAnimation(-25f, 25f, v.getX() + (v.getWidth() / 2), 1f);
    anim.setFillAfter(true);
    anim.setDuration(1000);
    anim.setRepeatCount(Animation.INFINITE);
    anim.setRepeatMode(Animation.REVERSE);
    v.startAnimation(anim);
  }

  public static void translateAnim(View v) {
    TranslateAnimation anim = new TranslateAnimation(-50, 50, 0, 0);
    //anim.setFillAfter(true);
    anim.setDuration(50);
    anim.setRepeatMode(Animation.REVERSE);
    anim.setRepeatCount(3);
    v.startAnimation(anim);
  }

  public static void alphaAnim(View v) {
    AlphaAnimation anim = new AlphaAnimation(0.0f, 1f);
    anim.setFillAfter(true);
    anim.setDuration(2000);
    v.startAnimation(anim);
  }

  public static void rotateAroundCenter(View v) {
    Animation anim = new RotateAnimation(0.0f, 360.0f,
      Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
      0.5f);
    anim.setDuration(1000);
    anim.setRepeatCount(-1);                // -1 = infinite repeated
    anim.setRepeatMode(Animation.REVERSE); // reverses each repeat
    anim.setFillAfter(true);               // keep rotation after animation

    v.startAnimation(anim);
  }

  public static void circularAnim(View v) {
    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      int cx = v.getWidth() / 2;
      int cy = v.getHeight() / 2;
      Animator anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, 2 * cx);
      anim.setDuration(250);
      anim.start();
    }
  }


  public static void hrefHtmlOutPutLink(TextView textView, String link, String lable) {
    String content = "<b></b> "
      + "<a href=\"" + link + "\">" + lable + "</a> ";
    Spannable s = (Spannable) Html.fromHtml(content);

    for (URLSpan u : s.getSpans(0, s.length(), URLSpan.class)) {
      s.setSpan(new UnderlineSpan() {
        public void updateDrawState(TextPaint tp) {
          tp.setUnderlineText(false);
        }
      }, s.getSpanStart(u), s.getSpanEnd(u), 0);
    }
    textView.setText(s);
    textView.setMovementMethod(LinkMovementMethod.getInstance());
  }

  public static void toastErrorr(final Activity activity) {
    try {


      activity.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          LToast customToast = new LToast(activity);
          customToast.toastIcon(R.drawable.ic_android_black_24dp)
            .toastLayout(R.layout.l_toast_dark_just_text)
            .toastText(R.string.toastFailed)
            .toastShow();
        }
      });
    } catch (Exception e) {

    }
  }

//  public static Float getDensity(Context context) {
//    float x = context.getResources().getDisplayMetrics().density;
//
//    // return 0.75 if it's LDPI
//// return 1.0 if it's MDPI
//// return 1.5 if it's HDPI
//// return 2.0 if it's XHDPI
//// return 3.0 if it's XXHDPI
//// return 4.0 if it's XXXHDPI
//    Float density = null;
//    if (x == 0.75) {
//      density = LDPI;
//    }
//    if (x == 1.0) {
//      density = MDPI;
//    }
//    if (x == 1.5) {
//      density = HDPI;
//    }
//    if (x == 2.0) {
//      density = XHDPI;
//    }
//    if (x == 3.0) {
//      density = XXHDPI;
//    }
//    if (x == 4.0) {
//      density = XXXHDPI;
//    }
//    return density;
//  }

  public static void toastFilter(final Activity activity) {
    try {

      activity.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          LToast customToast = new LToast(activity);
          customToast.toastIcon(R.drawable.ic_android_black_24dp)
            .toastLayout(R.layout.l_toast_dark_just_text)
            .toastText(R.string.toast_filter)
            .toastShow();
        }
      });
    } catch (Exception e) {

    }
  }

  public static void toastFilterPlayer(final Activity activity) {
    try {

      activity.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          LToast customToast = new LToast(activity);
          customToast.toastIcon(R.drawable.ic_android_black_24dp)
            .toastLayout(R.layout.l_toast_dark_just_text)
            .toastText(R.string.toast_filter_play)
            .toastShow();
        }
      });
    } catch (Exception e) {

    }
  }

//  public static void updateRecycler(RecyclerView recycler, RecyclerAdapter adapter, ArrayList arrayList, int position) {
//    recycler.removeViewAt(position);
//    adapter.notifyItemRemoved(position);
//    adapter.notifyItemRangeChanged(position, arrayList.size());
//    adapter.notifyDataSetChanged();
//  }

  public static void toastSuccess(final Activity activity) {
    try {


      activity.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          LToast customToast = new LToast(activity);
          customToast.toastIcon(R.drawable.ic_android_black_24dp)
            .toastLayout(R.layout.l_toast_dark_just_text)
            .toastText(R.string.toast_success)
            .toastShow();
        }
      });
    } catch (Exception e) {

    }
  }

  public static void toastWaiting(final Activity activity) {
    try {


      activity.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          LToast customToast = new LToast(activity);
          customToast.toastIcon(R.drawable.ic_android_black_24dp)
            .toastLayout(R.layout.l_toast_dark_just_text)
            .toastText(R.string.toast_empty)
            .toastShow();
        }
      });
    } catch (Exception e) {

    }
  }

  public static void toastDenied(final Activity activity) {
    try {


      activity.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          LToast customToast = new LToast(activity);
          customToast.toastIcon(R.drawable.ic_android_black_24dp)
            .toastLayout(R.layout.l_toast_dark_just_text)
            .toastText(R.string.toast_forbiden)
            .toastShow();
        }
      });
    } catch (Exception e) {

    }
  }

  public static void initSharedParams(Activity activity, String type, String token, String email, String pass, String phone, String passcode, String telegram, String userName, String userId) {
    SharedPreferences.Editor editor = activity.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
    editor.putString(SHARAED_PARAMS_type, type);
    editor.putString(SHARAED_PARAMS_userId, userId);
    editor.putString(SHARAED_PARAMS_TOKEN, token);
    editor.putString(SHARAED_PARAMS_email, email);
    editor.putString(SHARAED_PARAMS_pass, pass);
    editor.putString(SHARAED_PARAMS_phone, phone);
    editor.putString(SHARAED_PARAMS_randomNumber, passcode);
    editor.putString(SHARAED_PARAMS_telegram, telegram);
    editor.putString(SHARAED_PARAMS_userName, userName);
    editor.apply();
  }

  public static void setSharedParams(Activity activity, String name, String value) {
    SharedPreferences.Editor editor = activity.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
    editor.putString(name, value);
    editor.apply();
  }

  public static String getSharedParams(Activity activity, String value) {
    SharedPreferences prefs = activity.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
    String param = "";
    if (prefs.contains(value)) {
      param = prefs.getString(value, null);
    }
    return param;
  }

  public static String getSharedParams(Context contex, String value) {
    SharedPreferences prefs = contex.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
    String param = "";
    if (prefs.contains(value)) {
      param = prefs.getString(value, null);
    }
    return param;
  }

  public static boolean isNotNull(String value) {
    if (value.equals("") || value.equals("0") || value.equals("null") || value.equals(null)) {
      return false;
    }

    return true;
  }

  public static String isNull(String value) {
    setLog("isNull", value);
    if (value.equals("") || value.equals("0") || value.equals("null") || value == null || value.equals(" ")) {
      return "";
    }
    return value;
  }

  public static String zeroReplaceNull(String value) {
    if (value.equals("") || value.equals("0") || value.equals("null") || value.equals(null)) {
      return "0";
    } else return value;
  }

  public static void setEditTextMaxLength(EditText editText, int length) {
    InputFilter[] FilterArray = new InputFilter[1];
    FilterArray[0] = new InputFilter.LengthFilter(length);
    editText.setFilters(FilterArray);
  }

  public static void toastFailed(final Activity activity) {
    try {


      activity.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          LToast customToast = new LToast(activity);
          customToast.toastIcon(R.drawable.ic_android_black_24dp)
            .toastLayout(R.layout.l_toast_dark_just_text)
            .toastText(R.string.toastFailed)
            .toastShow();
        }
      });
    } catch (Exception e) {

    }
  }

  //=============================================================================================================================================================================
  public static void setLocale(Context context, String lang) {
    Activity activity = (Activity) context;
    LSharedParams lSharedParams = new LSharedParams(activity);
    String country = COUNTRY_FA;
    if (lang.equals(LANG_FA)) {
      country = COUNTRY_FA;
    }
    if (lang.equals(LANG_EN)) {
      country = COUNTRY_EN;
    }
    Locale myLocale = new Locale(lang, country);
    Locale.setDefault(myLocale);
    Resources res = context.getResources();
    DisplayMetrics dm = res.getDisplayMetrics();
    Configuration conf = res.getConfiguration();
    conf.setLayoutDirection(myLocale);
    conf.locale = myLocale;
    res.updateConfiguration(conf, dm);
  }

  //=============================================================================================================================================================================
  //Image load and blur
  public static void setImageAndCatch(final Context context, final String uri, final ImageView imageView, int placeHolder, int size, final float radius) {
    try {
      // not respect aspect ratio
      Glide.with(context)
        .load(uri)
        .asBitmap()
        .placeholder(placeHolder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()
        .error(placeHolder)
        .override(size, size)
        .into(new BitmapImageViewTarget(imageView) {
          @Override
          protected void setResource(Bitmap resource) {
            RoundedBitmapDrawable circularBitmapDrawable =
              RoundedBitmapDrawableFactory.create(context.getResources(), resource);
            circularBitmapDrawable.setCircular(true);
            circularBitmapDrawable.setCornerRadius(radius);
            imageView.setImageDrawable(circularBitmapDrawable);
          }
        });
    } catch (Exception e) {
      setLog("errore  set image and cash", size + uri + radius);
    }
  }

  public static void setImageAndCatchPhone(final Context context, final String uri, final ImageView imageView, int placeHolder, int size, final float radius) {
    Glide.with(context)
      .load(AssetImageReader.getImage(context, uri))
      .asBitmap()
      .placeholder(placeHolder)
      .diskCacheStrategy(DiskCacheStrategy.ALL)
      .centerCrop()
      .override(size, size)
      .into(new BitmapImageViewTarget(imageView) {
        @Override
        protected void setResource(Bitmap resource) {
          RoundedBitmapDrawable circularBitmapDrawable =
            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
          circularBitmapDrawable.setCircular(true);
          circularBitmapDrawable.setCornerRadius(radius);
          imageView.setImageDrawable(circularBitmapDrawable);
        }
      });
  }

  public static void setBlurImageAfterDownloadWithGlide(final Context context, final String uri, final ImageView imageView, int placeHolder) {
    try {


      Glide.with(context)
        .load(uri)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(placeHolder)
        .centerCrop()
        .override(500, 500)
        .listener(new RequestListener<String, GlideDrawable>() {
          @Override
          public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            return false;
          }

          @Override
          public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            new Handler().postDelayed(new Runnable() {
              @Override
              public void run() {
                try {
                  Blurry.with(context)
                    .radius(5)
                    .async()
                    .sampling(5)
                    .color(Color.argb(200, 0, 0,0))
                    .capture(imageView)
                    .into(imageView);
                  Animation anim = AnimationUtils.loadAnimation(context, R.anim.fadein);
                  imageView.setAnimation(anim);
                  anim.setFillAfter(true);
                  anim.start();
                } catch (Exception e) {
                  e.printStackTrace();
                }

              }
            }, 200);
            return false;
          }
        })
        .into(imageView);
    } catch (Exception e) {

    }

  }

  public static DisplayImageOptions imageOptions() {
    DisplayImageOptions option = new DisplayImageOptions.Builder()
      .showImageForEmptyUri(R.drawable.ic_android_black_24dp)
      .showImageOnFail(R.drawable.ic_android_black_24dp)
      .cacheInMemory(false)
      .cacheOnDisk(false)
      .build();

    return option;
  }
  public static void setBlurImageAfterDownloadOld(final Context context, final String uri, final ImageView imageView) {
    final ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
      .defaultDisplayImageOptions(Common.imageOptions())
      .build();
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        ImageLoader imageLoaderPost = ImageLoader.getInstance();
        try {
          if (!imageLoaderPost.isInited())
            imageLoaderPost.init(config);

          imageLoaderPost.displayImage(uri, imageView, Common.imageOptions(), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
              try {
                Blurry.with(context)
                  .radius(10)
                  .async()
                  .sampling(10)
                  .color(Color.argb(66, 0, 0, 50))
                  .capture(view)
                  .into(imageView);
                Animation anim = AnimationUtils.loadAnimation(context, R.anim.fadein);
                imageView.setAnimation(anim);
                anim.setFillAfter(true);
                anim.start();
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
          });
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }, 10);
  }

  //=============================================================================================================================================================================


  public static void setEnglishFontToPersianTextView(Context context, TextView textView, String englishfontName) {
    String text = textView.getText().toString();
    Pattern patternAlphaNumericCheck = Pattern.compile("^[a-zA-Z0-9]*$");
    if (patternAlphaNumericCheck.matcher(text).matches()) {
      Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + englishfontName + ".ttf");
      textView.setTypeface(tf, Typeface.NORMAL);
    }
  }

  //=============================================================================================================================================================================
  public static String getKeyForLocolization(Context context, String categoryGroupID) {
    String categoryDescription = null;
    switch (categoryGroupID) {
//      case "01000":
//        categoryDescription=context.getResources().getString(R.string.category_art);
//        break;
//      case "02000":
//        categoryDescription=context.getResources().getString(R.string.category_business);
//        break;
//      case "03000":
//        categoryDescription=context.getResources().getString(R.string.category_comedy);
//        break;
//      case "04000":
//        categoryDescription=context.getResources().getString(R.string.category_education);
//        break;
//      case "05000":
//        categoryDescription=context.getResources().getString(R.string.category_education);
//        break;
//      case "06000":
//        categoryDescription=context.getResources().getString(R.string.category_government_organizations);
//        break;
//      case "07000":
//        categoryDescription=context.getResources().getString(R.string.category_health);
//        break;
//      case "08000":
//        categoryDescription=context.getResources().getString(R.string.kids);
//        break;
//      case "09000":
//        categoryDescription=context.getResources().getString(R.string.category_music);
//        break;
//      case "10000":
//        categoryDescription=context.getResources().getString(R.string.category_news);
//        break;
//      case "11000":
//        categoryDescription=context.getResources().getString(R.string.category_relligion);
//        break;
//      case "12000":
//        categoryDescription=context.getResources().getString(R.string.category_science_medicine);
//        break;
//      case "13000":
//        categoryDescription=context.getResources().getString(R.string.category_society_culture);
//        break;
//      case "14000":
//        categoryDescription=context.getResources().getString(R.string.category_sports_recreation);
//        break;
//      case "15000":
//        categoryDescription=context.getResources().getString(R.string.category_technology);
//        break;
//      case "16000":
//        categoryDescription=context.getResources().getString(R.string.category_tv_and_film);
//        break;



    }

    return categoryDescription;
  }
  //=============================================================================================================================================================================


  public static void setPersianFontToEnglishTextView(Context context, TextView textView, String pershianFontName) {
    String text = textView.getText().toString();
    Pattern patternAlphaNumericCheck = Pattern.compile("^[a-zA-Z0-9]*$");
    if (!patternAlphaNumericCheck.matcher(text).matches()) {
      Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + pershianFontName + ".ttf");
      textView.setTypeface(tf, Typeface.NORMAL);
    }
  }

  public static void moveTextInTextView(TextView textView) {
    textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
    textView.setSingleLine();
    textView.setMarqueeRepeatLimit(10);
    textView.setFocusable(true);
    textView.setHorizontallyScrolling(true);
    textView.setFocusableInTouchMode(true);
    textView.requestFocus();
  }


  public static void requestForWriteSDCardPermission(final Activity activity) {

    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {

      Base.createAppDirectories();
//        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) {
//
//        }else{
//        }
    } else {
      PernissionRequestHelper request = new PernissionRequestHelper(activity);
      PernissionRequestHelper.OnGrantedListener grantedListenerListener = new PernissionRequestHelper.OnGrantedListener() {
        @Override
        public void onGranted() {
          Base.createAppDirectories();
        }
      };
      PernissionRequestHelper.OnAlreadyGrantedListener onAlreadyGrantedListener = new PernissionRequestHelper.OnAlreadyGrantedListener() {
        @Override
        public void onAlreadyGranted() {
          Base.createOrOpenDatabase();
        }
      };
      PernissionRequestHelper.OnDeniedListener deniedListener = new PernissionRequestHelper.OnDeniedListener() {
        @Override
        public void onDenied() {

          requestForWriteSDCardPermission(activity);

//          new AlertDialog.Builder(activity)
//            .setTitle("Permission Required")
//            .setMessage("Writing to SDCARD required for this app")
//            .setPositiveButton("Ask me again", new DialogInterface.OnClickListener() {
//              @Override
//              public void onClick(DialogInterface dialog, int which) {
//
//
//              }
//            })
//            .create()
//            .show();
        }
      };
      request.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, grantedListenerListener, deniedListener, onAlreadyGrantedListener);
    }
  }
}



