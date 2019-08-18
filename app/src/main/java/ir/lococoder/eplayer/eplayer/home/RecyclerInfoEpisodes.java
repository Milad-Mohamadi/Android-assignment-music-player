package ir.lococoder.eplayer.eplayer.home;


import android.webkit.WebView;

import ir.lococoder.eplayer.common.LRecyclerStruct;


class RecyclerInfoEpisodes extends LRecyclerStruct {

  String title;
  String description;
  String duration;
  String numberOfEpisode;
  String url;
  String playCount;
  String likedCount;
  String mal;
  String guid;
  String liked;

  String image;
  int  color;
  int  position;
  WebView justifyView;
  public RecyclerInfoEpisodes( String title, String description, String duration, int numberOfEpisode, String url, int position, WebView justifyView, String playCount, String likedCount, String mal, String guid, String liked, String image, int color) {
    this.cellType = RecyclerAdapterEpisodes.TYPE_EPISODE;
    this.title=title;
    this.description=description;
    this.numberOfEpisode=numberOfEpisode+"";
    this.duration=duration+"";
    this.url=  url;
    this.position=  position;
    this.justifyView=  justifyView;
    this.playCount=  playCount;
    this.likedCount=  likedCount;
    this.mal=  mal;
    this.guid=  guid;
    this.liked=  liked;
    this.image=  image;
    this.color=  color;
  }
}
