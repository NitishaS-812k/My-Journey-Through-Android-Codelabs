package com.example.tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int nofTabs;
    public PagerAdapter(FragmentManager fm, int NumofTabs) {
        super(fm);       //constructor of this class, called when new instance of pageadapter is created
        this.nofTabs = NumofTabs;
    }

    @Override
    // fragment is the return type
    public Fragment getItem(int i) {
        Log.i("HEY", "NOOB, THE GETITEM FUNCTION HAS BEEN CALLED");
        switch(i){
            case 0: return new tab1();
            case 1: return new tab2();    //determines the position of the tab selected and returns the respective fragment
            case 2: return new tab3();
            case 3 : return new tab4();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return nofTabs;
    }
}
