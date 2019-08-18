package ir.lococoder.eplayer.common;

import android.app.Activity;

import static ir.lococoder.eplayer.common.Common.getSharedParams;
import static ir.lococoder.eplayer.common.Config.COUNTRY_FA;
import static ir.lococoder.eplayer.common.Config.LANG_FA;
import static ir.lococoder.eplayer.common.Config.SHARAED_PARAMS_country;
import static ir.lococoder.eplayer.common.Config.SHARAED_PARAMS_email;
import static ir.lococoder.eplayer.common.Config.SHARAED_PARAMS_explorerData;
import static ir.lococoder.eplayer.common.Config.SHARAED_PARAMS_firstTime;
import static ir.lococoder.eplayer.common.Config.SHARAED_PARAMS_image;
import static ir.lococoder.eplayer.common.Config.SHARAED_PARAMS_language;
import static ir.lococoder.eplayer.common.Config.SHARAED_PARAMS_level;
import static ir.lococoder.eplayer.common.Config.SHARAED_PARAMS_pass;
import static ir.lococoder.eplayer.common.Config.SHARAED_PARAMS_phone;
import static ir.lococoder.eplayer.common.Config.SHARAED_PARAMS_randomNumber;
import static ir.lococoder.eplayer.common.Config.SHARAED_PARAMS_telegram;
import static ir.lococoder.eplayer.common.Config.SHARAED_PARAMS_TOKEN;
import static ir.lococoder.eplayer.common.Config.SHARAED_PARAMS_type;
import static ir.lococoder.eplayer.common.Config.SHARAED_PARAMS_userName;

public class LSharedParams {
  public String token;
  public String type;
  public String email;
  public String password;
  public String phone;
  public String randomNumber;
  public String telegram;
  public String userName;
  public String image;
  public String firstTime="0";
  public String level;
  public String language=LANG_FA;
  public String country=COUNTRY_FA;
  public String explorerData="";

  public LSharedParams(Activity activity) {
    token = getSharedParams(activity, SHARAED_PARAMS_TOKEN);
    type = getSharedParams(activity, SHARAED_PARAMS_type);
    email = getSharedParams(activity, SHARAED_PARAMS_email);
    phone = getSharedParams(activity, SHARAED_PARAMS_phone);
    randomNumber = getSharedParams(activity, SHARAED_PARAMS_randomNumber);
    telegram = getSharedParams(activity, SHARAED_PARAMS_telegram);
    password = getSharedParams(activity, SHARAED_PARAMS_pass);
    userName = getSharedParams(activity,SHARAED_PARAMS_userName);
    image = getSharedParams(activity,SHARAED_PARAMS_image);
    firstTime = getSharedParams(activity,SHARAED_PARAMS_firstTime);
    level = getSharedParams(activity,SHARAED_PARAMS_level);
    language = getSharedParams(activity,SHARAED_PARAMS_language);
    country = getSharedParams(activity,SHARAED_PARAMS_country);
    explorerData = getSharedParams(activity,SHARAED_PARAMS_explorerData);
//    setLog("===============================================================================================================================================================================================================================================================================================","" );
//    setLog("token         ====",token );
//    setLog("type          ====",type );
//    setLog("email         ====",email );
//    setLog("password      ====", password);
//    setLog("phone         ====",phone );
//    setLog("randomNumber  ====", randomNumber);
//    setLog("telegram      ====",telegram );
//    setLog("userName      ====",userName );
//    setLog("image         ====",image );
//    setLog("image         ====",image );
//    setLog("language      ====",language );
  }

}
