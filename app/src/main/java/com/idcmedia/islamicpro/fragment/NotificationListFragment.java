package com.idcmedia.islamicpro.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.Utils;
import com.idcmedia.islamicpro.adapter.DuaDetailsFragmentAdapter;
import com.idcmedia.islamicpro.model.CommonDuaContent;
import com.idcmedia.islamicpro.model.CommonDuaStubs;
import com.idcmedia.islamicpro.model.DuaStubs;
import com.idcmedia.islamicpro.model.OnListFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
public class NotificationListFragment extends Fragment implements OnListFragmentInteractionListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static List<CommonDuaContent> duaList;
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NotificationListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NotificationListFragment newInstance(int columnCount, List<CommonDuaContent> duaList) {
        NotificationListFragment.duaList = duaList;
        NotificationListFragment fragment = new NotificationListFragment();
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
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notification_list_fragment, container, false);

        // Set the adapter

            Context context = view.getContext();

             recyclerView = view.findViewById(R.id.rv_dua_details);
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            loadData();

        return view;
    }

    private void loadData() {
        List<CommonDuaContent> commonDuaContentsList = new ArrayList<>();

        SharedPreferences stringSharedPrefAll = Utils.getStringSharedPrefAll(getContext());
        Map<String, String> all = (Map<String, String>) stringSharedPrefAll.getAll();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(Map.Entry<String, String> entry:all.entrySet()){
                    String[] val =  entry.getValue().split("###");
                    String title = val[0];
                    String arabicText = val[1];
                    String pronounciation= "";
                    String englishExplanation = "";
                    String source="";
                    if(val.length>2) {
                        pronounciation = val[2];
                    }
                    if(val.length>3) {
                         englishExplanation = val[3];
                    }
                    if(val.length>4) {
                         source = val[4];
                    }

                    CommonDuaContent commonDuaContent =  new CommonDuaContent();
                    commonDuaContent.setArabicText(arabicText);
                    commonDuaContent.setHeading(title);
                    commonDuaContent.setEnglishExplanation(englishExplanation);
                    commonDuaContent.setPronounciation(pronounciation);
                    commonDuaContent.setSource(source);
                    commonDuaContentsList.add(commonDuaContent);


                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAdapter(new DuaDetailsFragmentAdapter(commonDuaContentsList, NotificationListFragment.this));
                    }
                });
            }
        }).start();

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
}
