package com.idcmedia.islamicpro.adapter;


import com.idcmedia.islamicpro.fragment.RukyahPagerFragmentDetails;
import com.idcmedia.islamicpro.fragment.RukyahViewPagerFragment;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by Belal on 2/3/2016.
 */
//Extending FragmentStatePagerAdapter
public class ViewPagerRukyahAdapter extends FragmentStatePagerAdapter {

    ArrayList<String> tabCount;

    public ViewPagerRukyahAdapter(FragmentManager fm, ArrayList<String> tabCount) {
        super(fm);
        this.tabCount= tabCount;
    }
 
    @Override
    public Fragment getItem(int position) {
        String tabName = tabCount.get(position);
        RukyahPagerFragmentDetails tab1;
        if (tabName.equalsIgnoreCase("short")) {
             tab1 = RukyahPagerFragmentDetails.newInstance(1,"short_rukyah.json");
        }else  if (tabName.equalsIgnoreCase("long")) {
            tab1 = RukyahPagerFragmentDetails.newInstance(1,"long_rquyah.json");
        }else  {
            tab1 = RukyahPagerFragmentDetails.newInstance(1,"complete_rukyah.json");
        }
                return tab1;
    }
 
    @Override
    public int getCount() {
        return tabCount.size();
    }
}