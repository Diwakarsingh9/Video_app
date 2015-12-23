package damroo.spinno.com.damroo.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.R;
import tabindicatorview.PagerSlidingTabStrip;

/**
 * Created by spinnosolutions on 10/28/15.
 */
public class FragmentViewPager  extends Fragment {

    @Bind(R.id.viewPager)ViewPager viewPager;
    @Bind(R.id.tabs) PagerSlidingTabStrip  tabs ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_viewpager, container, false);
        ButterKnife.bind(this, v);

        viewPager.setAdapter(new AdapterInnerFragment(getActivity().getSupportFragmentManager()));
        tabs.setViewPager(viewPager);
        tabs.setShouldExpand(true);
        tabs.setIndicatorHeight(2);
        tabs.setTextColor(Color.WHITE);
        tabs.setPadding(0, 0, 0, 10);
        tabs.setDividerColor(Color.BLACK);
        tabs.setIndicatorColor(Color.parseColor("#6BBA4C"));
        return v;
    }

    public static FragmentViewPager newInstance(String text) {

        FragmentViewPager f = new FragmentViewPager();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }



}
