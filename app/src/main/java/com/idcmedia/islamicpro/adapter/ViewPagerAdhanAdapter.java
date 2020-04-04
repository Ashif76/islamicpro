package com.idcmedia.islamicpro.adapter;


import com.idcmedia.islamicpro.fragment.AdhanPagerFragmentDetails;
import com.idcmedia.islamicpro.fragment.RukyahPagerFragmentDetails;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by Belal on 2/3/2016.
 */
//Extending FragmentStatePagerAdapter
public class ViewPagerAdhanAdapter extends FragmentStatePagerAdapter {

    ArrayList<String> tabCount;

    public ViewPagerAdhanAdapter(FragmentManager fm, ArrayList<String> tabCount) {
        super(fm);
        this.tabCount= tabCount;
    }
 
    @Override
    public Fragment getItem(int position) {
        String tabName = tabCount.get(position);
        AdhanPagerFragmentDetails tab1;
        if (tabName.equalsIgnoreCase("Fazar Adhan")) {
            tab1 = AdhanPagerFragmentDetails.newInstance(1,"fazar_azan.json");

        }else   {
            tab1 = AdhanPagerFragmentDetails.newInstance(1,"common_azan.json");
        }
                return tab1;
    }
 
    @Override
    public int getCount() {
        return tabCount.size();
    }
}