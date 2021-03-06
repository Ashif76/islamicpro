package com.idcmedia.islamicpro.adapter;


import com.idcmedia.islamicpro.fragment.RukyahPagerFragmentDetails;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by Belal on 2/3/2016.
 */
//Extending FragmentStatePagerAdapter
public class ViewPagerAzkarAdapter extends FragmentStatePagerAdapter {

    ArrayList<String> tabCount;

    public ViewPagerAzkarAdapter(FragmentManager fm, ArrayList<String> tabCount) {
        super(fm);
        this.tabCount= tabCount;
    }
 
    @Override
    public Fragment getItem(int position) {
//        RukyahPagerFragmentDetails tab1 = RukyahPagerFragmentDetails.newInstance(1, "short_rukyah.json");
//                return tab1;

        String tabName = tabCount.get(position);
        RukyahPagerFragmentDetails tab1;
        if (tabName.equalsIgnoreCase("Fazar")) {
            tab1 = RukyahPagerFragmentDetails.newInstance(1,"azkar_fazar.json");
        }else  if (tabName.equalsIgnoreCase("Ashar")) {
            tab1 = RukyahPagerFragmentDetails.newInstance(1,"azkar_ashar.json");
        }else  {
            tab1 = RukyahPagerFragmentDetails.newInstance(1,"azkar_magr.json");
        }
        return tab1;
    }
 
    @Override
    public int getCount() {
        return tabCount.size();
    }
}