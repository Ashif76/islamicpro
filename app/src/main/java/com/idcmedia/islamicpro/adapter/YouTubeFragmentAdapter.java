package com.idcmedia.islamicpro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.model.ItemClickListener;
import com.idcmedia.islamicpro.model.YouTubeDataItems;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class YouTubeFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;


    public List<YouTubeDataItems> mItemList;
    private Context mContext;
    private ItemClickListener itemClickListener;


    public YouTubeFragmentAdapter(List<YouTubeDataItems> itemList, Context context, ItemClickListener itemClickListener) {

        mItemList = itemList;
        this.mContext = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_youtube_adapter, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof ItemViewHolder) {
            populateItemRows((ItemViewHolder) viewHolder, position);
        } else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, position);
        }

    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return mItemList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        ImageView youTubeThumbnailView;
        ImageView ivWhatsapp;
         View mRootView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mRootView = itemView;
            this.textViewName = (TextView) itemView.findViewById(R.id.title);
            this.youTubeThumbnailView = itemView.findViewById(R.id.youtube_thumbnail_image);
//            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.news_image);
//            this.ivWhatsapp = (ImageView)itemView.findViewById(R.id.iv_whatsapp);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed

    }

    private void populateItemRows(ItemViewHolder viewHolder, int position) {

        final YouTubeDataItems item = mItemList.get(position);
        viewHolder.textViewName.setText(item.getVideoDetails().getTitle());
        String urlToImage = item.getVideoDetails().getThumbnails().getHighThumbnail().getUrl();
        Glide.with(mContext).load(urlToImage)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into( viewHolder.youTubeThumbnailView);
        viewHolder.mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(item);
            }
        });

//        viewHolder.ivWhatsapp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                itemClickListener.onWhatsAppShare(item);
//            }
//        });

    }


}
