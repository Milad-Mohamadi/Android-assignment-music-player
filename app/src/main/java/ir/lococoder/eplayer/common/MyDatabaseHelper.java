package ir.lococoder.eplayer.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ir.lococoder.eplayer.system.Base;


public class MyDatabaseHelper extends SQLiteOpenHelper {
  public static final String DB_NAME = "lococoder_db.sqlite";
  public static final int DB_VERSION = 2;


  public MyDatabaseHelper(Context context) {
    super(context, Base.DB_DIR + "/" + DB_NAME, null, DB_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    createStateEpisodes(db);
    createUserToEpisodePart(db);
    createDownload(db);
    createNotifications(db);
    createHistory(db);
  }


  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    if (oldVersion == 1 && newVersion == 2) {
      db.execSQL("DROP TABLE  userToEpisodePart");
      db.execSQL("DROP TABLE  download");
      db.execSQL("DROP TABLE  stateEpisodes");
      db.execSQL("DROP TABLE  history");
      createUserToEpisodePart(db);
      createStateEpisodes(db);
      createDownload(db);
      createNotifications(db);
      createHistory(db);
    }
  }

  private void createUserToEpisodePart(SQLiteDatabase db) {
    String query =
      "CREATE TABLE 'userToEpisodePart' (" +
        "'guid' TEXT  NOT NULL , " +
        "'startTime' TEXT," +
        "'endTime' TEXT ," +
        "'playTime' TEXT ," +
        "'totalTime' TEXT ," +
        " PRIMARY KEY ('guid','startTime')" +
        ")";
    db.execSQL(query);
  }
  private void createHistory(SQLiteDatabase db) {
    String query =
      "CREATE TABLE 'history' (" +
        "'guid' TEXT  NOT NULL , " +
        "'image' TEXT  NOT NULL , " +
        "'podcastName' TEXT  NOT NULL , " +
        "'episodeName' TEXT  NOT NULL , " +
        "'date' TEXT  NOT NULL , " +
        " PRIMARY KEY ('guid')" +
        ")";
    db.execSQL(query);
  }

  private void createDownload(SQLiteDatabase db) {
    String query =
      "CREATE TABLE 'download' (" +
        "'directory' TEXT  NOT NULL , " +
        "'rssUrl' TEXT  NOT NULL , " +
        "'guid' TEXT  NOT NULL , " +
        "'liked' TEXT," +
        "'mal' TEXT ," +
        "'volume' TEXT ," +
        "'episodeName' TEXT ," +
        "'podcastName' TEXT ," +
        "'imageUrl' TEXT ," +
        "'playCount' TEXT ," +
        "'likedCount' TEXT ," +
        "'duration' TEXT ," +
        "'description' TEXT ," +
        " PRIMARY KEY ('guid','rssUrl')" +
        ")";
    db.execSQL(query);
  }

  private void createStateEpisodes(SQLiteDatabase db) {
    String query =
      "CREATE TABLE 'stateEpisodes' (" +
        "'guid' TEXT  NOT NULL , " +
        "'endTime' TEXT NOT NULL" +
        ")";
    db.execSQL(query);
  }

  private void createNotifications(SQLiteDatabase db) {
    String query =
      "CREATE TABLE 'notifications' (" +
        "'type' TEXT, " +
        "'isNew' TEXT, " +
        "'rssUrl' TEXT, " +
        "'guid' TEXT, " +
        "'imageUrl' TEXT, " +
        "'time' TEXT, " +
        "'id' TEXT, " +
        "'podcastName' TEXT, " +
        "'episodeName' TEXT, " +
        "'userName' TEXT, " +
        "'description' TEXT, " +
        "'param1' TEXT, " +
        "'param2' TEXT, " +
        "'param3' TEXT, " +
        "'body' TEXT, " +
        "'title' TEXT, " +
        "'click_action' TEXT " +
        ")";
    db.execSQL(query);
  }


}
