package com.idcmedia.islamicpro.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.Utils;
import com.idcmedia.islamicpro.model.DuaStubs;
import com.idcmedia.islamicpro.model.ItemClickListener;
import com.idcmedia.islamicpro.model.KuranSurahData;
import com.idcmedia.islamicpro.model.OnListFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DuaStubs} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class KuranListFragmentAdapter extends RecyclerView.Adapter<KuranListFragmentAdapter.ViewHolder> {

    private Context context;
    private final ArrayList<KuranSurahData> mValues;
    private final ItemClickListener mListener;

    public KuranListFragmentAdapter(Context context, ArrayList<KuranSurahData> items, ItemClickListener listener) {
        this.context = context;
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kuran_list_fragment_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTvArabic.setText(mValues.get(position).getSurahNameInArabic());
        holder.mTvExplanation.setText(mValues.get(position).getEnglishNameTranslation());
        holder.mTvTranslation.setText(mValues.get(position).getEnglishName());
        holder.mTvSurahNo.setText((position+1)+".");
        int surahPosition = Utils.getIntSharedPref(context, Utils.SURAH_POSITION_KEY);
        if (surahPosition ==position ) {
           holder.tvReadingStatus.setVisibility(View.VISIBLE);
        }else{
            holder.tvReadingStatus.setVisibility(View.GONE);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onItemClick(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTvArabic;
        public final TextView mTvTranslation;
        public final TextView mTvExplanation;
        public final TextView mTvSurahNo;
        public KuranSurahData mItem;
        public TextView tvReadingStatus;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTvArabic = (TextView) view.findViewById(R.id.tv_arabic);
            mTvTranslation = (TextView) view.findViewById(R.id.tv_translation);
            mTvExplanation = (TextView) view.findViewById(R.id.tv_exmplanation);
            mTvSurahNo = (TextView)view.findViewById(R.id.tv_surah_no);
            tvReadingStatus = view.findViewById(R.id.tv_reading_status);
        }

    }
}
