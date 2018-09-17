package com.example.admin.whatsappstatus;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by admin on 05-09-2018.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position ==0) {
            return new English();
        } else if (position == 1) {
            return new Hindi();
        } else return new Gujrati();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
