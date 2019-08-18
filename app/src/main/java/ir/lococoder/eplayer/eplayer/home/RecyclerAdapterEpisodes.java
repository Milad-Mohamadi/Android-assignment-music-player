package ir.lococoder.eplayer.eplayer.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import ir.lococoder.eplayer.R;
import ir.lococoder.eplayer.common.LRecyclerStruct;

import static ir.lococoder.eplayer.common.Common.setImageAndCatch;


public class RecyclerAdapterEpisodes extends RecyclerView.Adapter<RecyclerAdapterEpisodes.ViewHolder> {
  static final int TYPE_EPISODE = 0;
  ArrayList<LRecyclerStruct> list;
  Context context;
  Activity activity;
  int percen5 = 0;
  int percen4 = 0;
  int percen3 = 0;
  int percen2 = 0;
  int percen1 = 0;

  public RecyclerAdapterEpisodes(Context context, ArrayList<LRecyclerStruct> list) {
    this.list = list;
    this.context = context;
    activity = (Activity) context;
  }

  @Override
  public int getItemViewType(int position) {
    LRecyclerStruct item = list.get(position);
    return item.cellType;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    int layoutId = TYPE_EPISODE;
    switch (viewType) {
      case TYPE_EPISODE:
        layoutId = R.layout.cell_list_music;
        break;
    }
    View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    ViewHolder viewHolder = new ViewHolder(view);
    return viewHolder;
  }


  @SuppressLint("ClickableViewAccessibility")
  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    int cellType = holder.getItemViewType();
    final LRecyclerStruct feedItem = list.get(position);
    switch (cellType) {
      case TYPE_EPISODE:
        final RecyclerInfoEpisodes value_item = (RecyclerInfoEpisodes) list.get(position);
        holder.txt_episode_name.setText(value_item.title);
//        setJustify(context,holder.root_description,value_item.description);
        holder.txt_number_of_episode.setText(value_item.numberOfEpisode);
        holder.txt_episode_duration.setText(value_item.duration);
        setImageAndCatch(activity, value_item.image, holder.img_episode, R.drawable.ic_error_outline_black_24dp, 80, 5f);

//        Glide.with(context)
//          .load(value_item.image)
//          .asBitmap()
//          .centerCrop()
//          .into(new BitmapImageViewTarget(holder.img_episode) {
//            @Override
//            protected void setResource(Bitmap resource) {
//              RoundedBitmapDrawable circularBitmapDrawable =
//                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//              circularBitmapDrawable.setCircular(true);
//              circularBitmapDrawable.setCornerRadius(0f);
//              holder.img_episode.setImageDrawable(circularBitmapDrawable);
//            }
//          });
        holder.root.setBackgroundResource(value_item.color);
        holder.root.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent = new Intent(activity, MainActivity.class);
            intent.putExtra("feed", value_item.url);
            intent.putExtra("position", value_item.position);
            intent.putExtra("playCount", value_item.playCount);
            intent.putExtra("guid", value_item.guid);
            intent.putExtra("mal", value_item.mal);
            intent.putExtra("liked", value_item.liked);
            intent.putExtra("likedCount", value_item.likedCount);
            activity.startActivity(intent);
            activity.overridePendingTransition( R.anim.slide_down_to_up, R.anim.slide_down_to_up_2);

          }
        });

        break;

    }

  }


  @Override
  public int getItemCount() {
    return list.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView txt_time, txt_user_name, txt_review, txt_subscribed, txt_1_star, txt_2_star, txt_3_star, txt_4_star, txt_5_star, txt_follower, txt_play, txt_rate_count, txt_average_rate, txt_episode_name, txt_description, txt_show_author, txt_show_category, txt_show_name, txt_show_average_item_time, txt_show_item_count, txt_section, txt_number_of_episode, txt_episode_duration;
    ImageView img_show, img_episode;
    ViewGroup root, root_description;
    ProgressBar progressBar1, progressBar2, progressBar3, progressBar4, progressBar5;

    public ViewHolder(View itemView) {
      super(itemView);


      root = (ViewGroup) itemView.findViewById(R.id.root);
      txt_episode_name = (TextView) itemView.findViewById(R.id.txt_episode_name);
      txt_number_of_episode = (TextView) itemView.findViewById(R.id.txt_number_of_episode);
      txt_episode_duration = (TextView) itemView.findViewById(R.id.txt_episode_duration);
      img_episode = (ImageView) itemView.findViewById(R.id.img_episode);



    }
  }


}
