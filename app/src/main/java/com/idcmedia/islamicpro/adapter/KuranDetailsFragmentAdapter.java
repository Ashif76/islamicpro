package com.idcmedia.islamicpro.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.Utils;
import com.idcmedia.islamicpro.model.ItemClickListener;
import com.idcmedia.islamicpro.model.OnListFragmentInteractionListener;
import com.idcmedia.islamicpro.model.SurahVerse;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * {@link RecyclerView.Adapter} that can display a {@link com.idcmedia.islamicpro.model.DuaStubs} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class KuranDetailsFragmentAdapter extends  RecyclerView.Adapter<KuranDetailsFragmentAdapter.ViewHolder> {
    private Context context;
    private int mSurahNumber;
    private boolean kuranTransState;
    private final ArrayList<SurahVerse> mValues;
    private final ItemClickListener mListener;

    public KuranDetailsFragmentAdapter(Context context, int number, boolean kuranTransState, ArrayList<SurahVerse> items, ItemClickListener listener) {
        this.context = context;
        mSurahNumber = number-1;
        this.kuranTransState = kuranTransState;
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kuran_detaile_new_fragment_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTvArabic.setText(mValues.get(position).getVerseArabicText());
        holder.mTvExplanation.setText(mValues.get(position).getEnglishTranslation());

        holder.mTvTranslation.setText(position+1+". "+mValues.get(position).getEnglishTranslation());
        if (kuranTransState){
            holder.mTvTranslation.setVisibility(View.VISIBLE);
        }else{
            holder.mTvTranslation.setVisibility(View.GONE);
        }
        int surahPosition = Utils.getIntSharedPref(context, Utils.SURAH_POSITION_KEY);
        int intAyatPosition = Utils.getIntSharedPref(context, Utils.AYAT_POSITION_KEY);
        if (intAyatPosition!=0 && intAyatPosition == position && surahPosition ==mSurahNumber ) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#999999"));
        }else{
            holder.cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }


        holder.llMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {

                    Utils.setIntSharedPref(context,Utils.SURAH_POSITION_KEY, mSurahNumber);
                    Utils.setIntSharedPref(context,Utils.AYAT_POSITION_KEY,position);
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    Snackbar snackbar = Snackbar
                            .make(holder.mView, "Verse has been saved. You can resume your reading from here later.", Snackbar.LENGTH_SHORT);
//					snackbar = Snackbar.make(view, "Page has been marked you can resume latter...", Snackbar.LENGTH_SHORT);
//                    View snackBarView = snackbar.getView();
//                    snackBarView.setBackgroundColor(context.getResources().getColor(R.color.holo_blue));
//
                    snackbar.show();
//                    Toast.makeText(context,"Verse has been saved. You can resume your reading from here later.",Toast.LENGTH_LONG).show();
                    notifyDataSetChanged();
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
        public SurahVerse mItem;
        public LinearLayout llMark;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTvArabic = (TextView) view.findViewById(R.id.tv_ayat);
            cardView = view.findViewById(R.id.card_view);
            llMark = view.findViewById(R.id.ll_action);
            mTvTranslation = (TextView) view.findViewById(R.id.tv_translation);
            mTvExplanation = (TextView) view.findViewById(R.id.tv_exmplanation);
        }

    }
}
