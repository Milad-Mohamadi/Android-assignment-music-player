package ir.lococoder.eplayer.system;

import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

import ir.lococoder.eplayer.common.MyDatabaseHelper;


public class Base extends LBase {
  public static Base app;

  public static AssetManager assetManager;
  public static SQLiteDatabase database;
  public static final String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
  public static final String BRAND_DIR = SDCARD + "/LocoCoder";
  public static final String DOWNLODED_FILE = BRAND_DIR + "/downloadedFile";
  public static final String APP_DIR = BRAND_DIR + "/database";
  public static final String DB_DIR = APP_DIR + "/db";

  @Override
  public void onCreate() {
    super.onCreate();

    app = this;
    assetManager = getAssets();
    ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
      .build();
    ImageLoader.getInstance().init(config);
  }

  public static void createAppDirectories() {
    File dbDir = new File(DB_DIR);
    if (!dbDir.exists()) {
      boolean wasCreated = dbDir.mkdirs();
    }

    File downloadedDir = new File(DOWNLODED_FILE);
    if (!downloadedDir.exists()) {
      downloadedDir.mkdirs();
    }
    createOrOpenDatabase();
  }

  public static void createOrOpenDatabase() {
    if (database != null) {
      return;
    }
    try{
    MyDatabaseHelper dbHelper = new MyDatabaseHelper(getContext());
    database = dbHelper.getWritableDatabase();
    }catch (Exception e){

    }
  }





}

