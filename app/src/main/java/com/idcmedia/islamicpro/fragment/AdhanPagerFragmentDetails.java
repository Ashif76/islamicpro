package com.idcmedia.islamicpro.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.adapter.AdhanPagerFragmentDetailsAdapter;
import com.idcmedia.islamicpro.adapter.RukyahPagerFragmentDetailsAdapter;
import com.idcmedia.islamicpro.model.DuaStubs;
import com.idcmedia.islamicpro.model.OnListFragmentInteractionListener;
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
public class AdhanPagerFragmentDetails extends Fragment implements OnListFragmentInteractionListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private  String rukyahFilName;
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AdhanPagerFragmentDetails() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AdhanPagerFragmentDetails newInstance(int columnCount, String rukyahFilName) {

        AdhanPagerFragmentDetails fragment = new AdhanPagerFragmentDetails();
        fragment.rukyahFilName = rukyahFilName;
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
        View view = inflater.inflate(R.layout.rukyah_details_fragment, container, false);

        // Set the adapter

            Context context = view.getContext();
            RecyclerView recyclerView = view.findViewById(R.id.rv_dua_details);
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

//        ArrayList<DuaStubs> duaStubs = new ArrayList<>();
//            DuaStubs duaStubs1 = new DuaStubs();
//            duaStubs1.setTextArabic("لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربيةلوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية ");
//            duaStubs1.setTextExplanation("Explanation");
//            duaStubs1.setVerse("askdjfadf");
//            duaStubs.add(duaStubs1);
        recyclerView.setAdapter(new AdhanPagerFragmentDetailsAdapter(JsonConvertUtil.getAdhan(getContext(),rukyahFilName).getContent(), this));
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
