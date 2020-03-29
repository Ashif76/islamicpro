package com.idcmedia.islamicpro.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.adapter.SymptomsDetailsListFragmentAdapter;
import com.idcmedia.islamicpro.adapter.SymptomsListFragmentAdapter;
import com.idcmedia.islamicpro.model.ItemClickListener;
import com.idcmedia.islamicpro.model.OnListFragmentInteractionListener;
import com.idcmedia.islamicpro.utils.JsonConvertUtil;
import com.idcmedia.islamicpro.utils.Symptoms;
import com.idcmedia.islamicpro.utils.SymptomsData;

import java.util.ArrayList;

import androidx.annotation.RequiresApi;
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
public class SymtomsDetailsListFragment extends Fragment implements ItemClickListener<Symptoms> {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static ArrayList<String> symptoms;
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SymtomsDetailsListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SymtomsDetailsListFragment newInstance(int columnCount, ArrayList<String> symptoms) {
        SymtomsDetailsListFragment.symptoms = symptoms;
        SymtomsDetailsListFragment fragment = new SymtomsDetailsListFragment();
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
    SymptomsData duaData;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dua_list_fragment, container, false);

        // Set the adapter

            Context context = view.getContext();
            RecyclerView recyclerView = view.findViewById(R.id.rv_dua_details);
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }



        recyclerView.setAdapter(new SymptomsDetailsListFragmentAdapter(symptoms, this));
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

//    @Override
//    public void onListFragmentInteraction(DuaStubs item) {
////        Fragment newFragment = KuranDetailsFragment.newInstance(1);
////        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
////        ft.replace(R.id.ll_rukyah_main, newFragment).commit();
//
//        Fragment newFragment = CommonDetailsFragment.newInstance(1);
//        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.ll_rukyah_main, newFragment,"CommonDetailsFragment");
//        ft.addToBackStack("null");
//        ft.commit();
//    }

    @Override
    public void onItemClick(Symptoms duaName) {



//        Fragment newFragment = SymtomsDetailsListFragment.newInstance(1);
//        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.ll_rukyah_main, newFragment,"DuaDetailsFragment");
//        ft.addToBackStack("null");
//        ft.commit();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
