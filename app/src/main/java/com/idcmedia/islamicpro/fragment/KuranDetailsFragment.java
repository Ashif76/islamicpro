package com.idcmedia.islamicpro.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.Utils;
import com.idcmedia.islamicpro.activity.SettingActivity;
import com.idcmedia.islamicpro.adapter.KuranDetailsFragmentAdapter;
import com.idcmedia.islamicpro.model.DuaStubs;
import com.idcmedia.islamicpro.model.ItemClickListener;
import com.idcmedia.islamicpro.model.KuranSurahData;
import com.idcmedia.islamicpro.model.OnListFragmentInteractionListener;
import com.idcmedia.islamicpro.model.SurahVerse;
import com.idcmedia.islamicpro.utils.JsonConvertUtil;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class KuranDetailsFragment extends Fragment implements ItemClickListener<SurahVerse> {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static KuranSurahData mKuranSurahData;
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public KuranDetailsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static KuranDetailsFragment newInstance(int columnCount, KuranSurahData kuranSurahData) {
        mKuranSurahData = kuranSurahData;
        KuranDetailsFragment fragment = new KuranDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kuran_details_fragment, container, false);

        // Set the adapter
         TextView  tvSurahNoName= view.findViewById(R.id.tv_surah_no_name);
          TextView tvSurahNameArabic=  view.findViewById(R.id.tv_srauah_name_arabic);
        TextView tvTotalVerses=  view.findViewById(R.id.tv_total_verses);
          tvSurahNoName.setText(mKuranSurahData.getNumber()+". "+mKuranSurahData.getEnglishName());
        tvSurahNameArabic.setText(mKuranSurahData.getSurahNameInArabic());
        tvTotalVerses.setText("Verses: "+mKuranSurahData.getNumberOfAyahs());


            Context context = view.getContext();
            RecyclerView recyclerView = view.findViewById(R.id.rv_dua_details);
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
        boolean kuranTransState = Utils.getBooleanSharedPref(getContext(), SettingActivity.KEY_SWITCH_STATE_KURAN);
        recyclerView.setAdapter(new KuranDetailsFragmentAdapter(getContext(),mKuranSurahData.getNumber(),kuranTransState,JsonConvertUtil.getKuranSurahDetails(getContext(),mKuranSurahData.getNumber()).getVerses(), this));
        int surahPosition = Utils.getIntSharedPref(context, Utils.SURAH_POSITION_KEY);
        int intAyatPosition = Utils.getIntSharedPref(getContext(), Utils.AYAT_POSITION_KEY);
        if (intAyatPosition!=0 && surahPosition ==(mKuranSurahData.getNumber()-1) ) {
            recyclerView.scrollToPosition(intAyatPosition);
        }
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }




    @Override
    public void onItemClick(SurahVerse surahVerse) {

    }
}
