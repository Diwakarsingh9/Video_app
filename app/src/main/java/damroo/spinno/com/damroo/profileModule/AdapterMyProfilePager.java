package damroo.spinno.com.damroo.profileModule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


/**
 * Created by spinnosolutions on 9/15/15.
 */
public class AdapterMyProfilePager extends FragmentStatePagerAdapter {


    private final String[] TITLES = {  "My Videos", "My Channels", "My Wallet" };


    public AdapterMyProfilePager(FragmentManager fm) {
        super(fm);
    }






    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }



    @Override
    public Fragment getItem(int pos) {
        switch(pos) {

            case 0: return MyVideos_Fragment.newInstance("MyVideo_Fragment");
            case 1: return MyChannel_Fragment.newInstance("MyChannel_Fragment");
            case 2: return MyWallet_Fragment.newInstance("MyWallet_Fragment");
            default: return MyWallet_Fragment.newInstance("MyWallet_Fragment");
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}