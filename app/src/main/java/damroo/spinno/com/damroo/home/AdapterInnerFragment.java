package damroo.spinno.com.damroo.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by spinnosolutions on 10/28/15.
 */
public class AdapterInnerFragment extends FragmentStatePagerAdapter {

    String [] TITLES = {"Categories" , "Channels"};

    public AdapterInnerFragment(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch(pos) {

            case 0: return FragmentInnerCategories.newInstance("FirstFragment, Instance 1");
            case 1: return FragmentInnerChannels.newInstance("SecondFragment, Instance 1");
            default: return FragmentInnerChannels.newInstance("ThirdFragment, Default");
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }


    @Override
    public int getCount() {
        return 2;
    }
}
