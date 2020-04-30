package com.idcmedia.islamicpro.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.model.DashBoardData;
import com.idcmedia.islamicpro.model.DashBoardDuaItem;
import com.idcmedia.islamicpro.model.ItemClickListener;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DashBoardData} and makes a call to the
 * TODO: Replace the implementation with code for your data type.
 */
public class DashBoardDuaFragmentAdapter extends RecyclerView.Adapter<DashBoardDuaFragmentAdapter.ViewHolder> {

    private final List<DashBoardDuaItem> mValues;
    private final ItemClickListener mListener;

    public DashBoardDuaFragmentAdapter(List<DashBoardDuaItem> items, ItemClickListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dashboard_dua_activity_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTvTitle.setText(mValues.get(position).getName());
        holder.mNoOfChapters.setText(mValues.get(position).getNoOfChapter()+" Chapters");
        setIconForDashBoard(mValues.get(position).getId(),holder.mTvIcon);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    DashBoardDuaItem mItem = holder.mItem;
                    mListener.onItemClick(mItem);
                }
            }
        });
    }

    private void setIconForDashBoard(int id, ImageView mTvIcon) {
        switch (id){
            case 1:{
               mTvIcon.setImageResource(R.drawable.dua_1);
                break;
            } case 2:{
                mTvIcon.setImageResource(R.drawable.dua_1);
                break;
            } case 3:{
                mTvIcon.setImageResource(R.drawable.ayte_shifa);
                break;
            } case 4:{
                mTvIcon.setImageResource(R.drawable.azkar_1);
                break;
            } case 5:{
                mTvIcon.setImageResource(R.drawable.adhan_1);
                break;
            } case 6:{
                mTvIcon.setImageResource(R.drawable.symton);
                break;
            } case 7:{
                mTvIcon.setImageResource(R.drawable.name_1);
                break;
            }default:{
                mTvIcon.setImageResource(R.drawable.name_1);
                break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mTvIcon;
        public final TextView mTvTitle;
        public final TextView mNoOfChapters;
        public final RelativeLayout mRlItem;
        public DashBoardDuaItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTvIcon = (ImageView) view.findViewById(R.id.iv_dashboard_icon);
            mTvTitle = (TextView) view.findViewById(R.id.tv_title);
            mRlItem = (RelativeLayout) view.findViewById(R.id.rl_item);
            mNoOfChapters = view.findViewById(R.id.tv_no_of_chapter);
        }

    }
}
