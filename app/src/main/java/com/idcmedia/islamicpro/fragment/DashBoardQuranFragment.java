package com.idcmedia.islamicpro.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdView;
import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.Utils;
import com.idcmedia.islamicpro.activity.KuranFullReadingMainActivity;
import com.idcmedia.islamicpro.activity.KuranMainActivity;
import com.idcmedia.islamicpro.activity.KuranReadMainActivity;
import com.idcmedia.islamicpro.adapter.DashBoardDuaFragmentAdapter;
import com.idcmedia.islamicpro.adapter.DashBoardQuranFragmentAdapter;
import com.idcmedia.islamicpro.model.DashBoardDuaItem;
import com.idcmedia.islamicpro.model.DashBoardDuaStubs;
import com.idcmedia.islamicpro.model.GridSpacingItemDecoration;
import com.idcmedia.islamicpro.model.ItemClickListener;
import com.idcmedia.islamicpro.model.OnListFragmentInteractionListener;
import com.idcmedia.islamicpro.utils.JsonConvertUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class DashBoardQuranFragment extends Fragment implements ItemClickListener<DashBoardDuaItem> {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private OnListFragmentInteractionListener mListener;
    private AdView adView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DashBoardQuranFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static DashBoardQuranFragment newInstance(int columnCount) {
        DashBoardQuranFragment fragment = new DashBoardQuranFragment();
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
        View view = inflater.inflate(R.layout.dashboard_dua_fragment, container, false);

        // Set the adapter
        adView = view.findViewById(R.id.adView);
        Utils.loadAdd(adView);
        Utils.setTimeForAdd(getActivity(),adView);
        RecyclerView recyclerView = view.findViewById(R.id.rv_home);
//        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        } else {
//            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        }
        int spanCount =3; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = false;
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        DashBoardDuaStubs dashBoardStubs = new DashBoardDuaStubs();
         List<DashBoardDuaItem> dashBoardData = new ArrayList<>();
        DashBoardDuaItem dashBoardDuaItem1 = new DashBoardDuaItem();
        DashBoardDuaItem dashBoardDuaItem2 = new DashBoardDuaItem();

        dashBoardDuaItem1.setCategoryName("Quran by para");
        dashBoardDuaItem2.setCategoryName("Quran by surah");
        dashBoardDuaItem1.setId(1);
        dashBoardDuaItem2.setId(2);
        dashBoardData.add(dashBoardDuaItem1);
        dashBoardData.add(dashBoardDuaItem2);
        dashBoardStubs.setDashBoardData(dashBoardData);

        recyclerView.setAdapter(new DashBoardQuranFragmentAdapter(dashBoardStubs.getDashBoardData(), this));

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
    public void onItemClick(DashBoardDuaItem item) {
//        Fragment newFragment;
        Intent intent;
        if(item.getId()==1){
             intent = new Intent(getActivity(),KuranFullReadingMainActivity.class);
//             newFragment = KuranInstallationListFragment.newInstance(1);
        }else{
            intent = new Intent(getActivity(), KuranMainActivity.class);
        }
       startActivity(intent);
//        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.ll_rukyah_main, newFragment,"CommonListFragment");
//        ft.addToBackStack("null");
//        ft.commit();
    }


}
