package com.idcmedia.islamicpro.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.Utils;
import com.idcmedia.islamicpro.adapter.CommonListFragmentAdapter;
import com.idcmedia.islamicpro.adapter.KuranListFragmentAdapter;
import com.idcmedia.islamicpro.model.CommonDuaContent;
import com.idcmedia.islamicpro.model.DuaStubs;
import com.idcmedia.islamicpro.model.OnListFragmentInteractionListener;
import com.idcmedia.islamicpro.utils.JsonConvertUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
public class CommonListFragment extends Fragment implements OnListFragmentInteractionListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CommonListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CommonListFragment newInstance(int columnCount) {
        CommonListFragment fragment = new CommonListFragment();
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kuran_list_fragment, container, false);

        // Set the adapter

            Context context = view.getContext();
            RecyclerView recyclerView = view.findViewById(R.id.rv_dua_details);
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

        Map<String, List<CommonDuaContent>> duaData = JsonConvertUtil.getDuaData(getContext(), 0);
        List<String> collect = duaData.keySet().stream().collect(Collectors.toList());
        recyclerView.setAdapter(new CommonListFragmentAdapter(Utils.dummyData(), this));
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
    public void onListFragmentInteraction(DuaStubs item) {
//        Fragment newFragment = KuranDetailsFragment.newInstance(1);
//        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.ll_rukyah_main, newFragment).commit();

        Fragment newFragment = CommonDetailsFragment.newInstance(1);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.ll_rukyah_main, newFragment,"CommonDetailsFragment");
        ft.addToBackStack("null");
        ft.commit();
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
