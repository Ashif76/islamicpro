package com.idcmedia.islamicpro.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.fragment.RukyahPagerFragmentDetails;
import com.idcmedia.islamicpro.model.DuaStubs;
import com.idcmedia.islamicpro.model.OnListFragmentInteractionListener;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DuaStubs} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class RukyahPagerFragmentDetailsAdapter extends RecyclerView.Adapter<RukyahPagerFragmentDetailsAdapter.ViewHolder> {

    private final List<DuaStubs> mValues;
    private final OnListFragmentInteractionListener mListener;

    public RukyahPagerFragmentDetailsAdapter(List<DuaStubs> items, RukyahPagerFragmentDetails listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rukyah_details_fragment_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTvAyat.setText(mValues.get(position).textArabic);
        holder.mTvExplanation.setText(mValues.get(position).textExplanation);
        holder.mTvTranslation.setText(mValues.get(position).textTranslation);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
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
        public final TextView mTvAyat;
        public final TextView mTvTranslation;
        public final TextView mTvExplanation;
        public DuaStubs mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTvAyat = (TextView) view.findViewById(R.id.tv_ayat);
            mTvTranslation = (TextView) view.findViewById(R.id.tv_translation);
            mTvExplanation = (TextView) view.findViewById(R.id.tv_exmplanation);
        }

    }
}
