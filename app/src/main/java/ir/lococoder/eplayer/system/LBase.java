package ir.lococoder.eplayer.system;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import java.lang.reflect.Field;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.TransitionRes;

public class LBase extends Application {
  private static Context context;
  private static Activity currentActivity;
  private static LayoutInflater layoutInflater;
  private static TransitionInflater transitionInflater;
  private static Handler handler;
  private static DisplayMetrics displayMetrics;
  private static LBase base;

  @Override
  public void onCreate() {
    super.onCreate();
    base = this;
    context = getApplicationContext();
    handler = new Handler();
    displayMetrics = getResources().getDisplayMetrics();
    layoutInflater = LayoutInflater.from(context);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      transitionInflater = TransitionInflater.from(context);
    }
  }

  private void actionbarMenuBugResolve() {
    try {
      ViewConfiguration config = ViewConfiguration.get(this);
      Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
      if (menuKeyField != null) {
        menuKeyField.setAccessible(true);
        menuKeyField.setBoolean(config, false);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public static LBase get() {
    return base;
  }

  public static Context getContext() {
    if (currentActivity != null) {
      return currentActivity;
    }

    return context;
  }

  public static void setCurrentActivity(Activity activity) {
    currentActivity = activity;
  }

  public static Activity getCurrentActivity() {
    return currentActivity;
  }

  public static LayoutInflater getLayoutInflater() {
    return layoutInflater;
  }

  public static TransitionInflater getTransitionInflater() {
    return transitionInflater;
  }

  public static Transition inflateTransition(@TransitionRes int res) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      return transitionInflater.inflateTransition(res);
    }

    return null;
  }

  public static View inflateLayout(@LayoutRes int res) {
    return layoutInflater.inflate(res, null);
  }

  public static View inflateLayout(@LayoutRes int res, @Nullable ViewGroup root) {
    return layoutInflater.inflate(res, root);
  }

  public static Handler getHandler() {
    return handler;
  }

  public static DisplayMetrics getDisplayMetrics() {
    return displayMetrics;
  }
}
