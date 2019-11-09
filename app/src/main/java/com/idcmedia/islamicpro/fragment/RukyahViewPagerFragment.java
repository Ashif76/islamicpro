package com.idcmedia.islamicpro.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.adapter.ViewPagerRukyahAdapter;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


public class RukyahViewPagerFragment extends Fragment implements TabLayout.BaseOnTabSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private TabLayout tabLayout;

    public RukyahViewPagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RukyahViewPagerFragment newInstance(String param1, String param2) {
        RukyahViewPagerFragment fragment = new RukyahViewPagerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_pager_rqyah_fragment_main, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        ArrayList<String> tabList = new ArrayList<>();
        tabList.add("Short");
        tabList.add("Long");
        tabList.add("Complete");
        setTabs(tabList);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        ViewPagerRukyahAdapter adapter = new ViewPagerRukyahAdapter(getActivity().getSupportFragmentManager(), tabList);

        //Adding adapter to pager
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);
        // Inflate the layout for this fragment
        return view;
    }

    private void setTabs(ArrayList<String> tabs) {
        for (int i = 0; i < tabs.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabs.get(i)));
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
