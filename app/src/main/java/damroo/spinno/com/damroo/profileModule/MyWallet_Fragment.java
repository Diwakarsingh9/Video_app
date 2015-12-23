package damroo.spinno.com.damroo.profileModule;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.R;

/**
 * Created by spinnosolutions on 9/15/15.
 */
public class MyWallet_Fragment extends Fragment {

    @Bind(R.id.list)ListView list ;
    String [] walletsnames = {"Nike Shoes" ,"Forest Essential - Aroma Creams" , "Good Farth Candles" , "Turbo Feeds" , "Quality Works" , "National Geography "};
    String [] walletmoney = {"6" , "4" , "8" , "23" , "32" , "4"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mywallet, container, false);

        ButterKnife.bind(this ,v);

        list.setAdapter(new AdapterMyWallet(getActivity() , walletsnames ,walletmoney));
        return v;
    }

    public static MyWallet_Fragment newInstance(String text) {

        MyWallet_Fragment f = new MyWallet_Fragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}