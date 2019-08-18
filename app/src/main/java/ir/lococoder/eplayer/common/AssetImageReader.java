package ir.lococoder.eplayer.common;

import android.content.Context;


public class AssetImageReader {
  public static int getImage(Context context, String imageName) {

//    try {
//      InputStream imageInputStream = Base.assetManager.open(imageName);
//      Drawable image = Drawable.createFromStream(imageInputStream, null);
//      return image;
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//
//    return null;

    int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

    return drawableResourceId;
  }
}
