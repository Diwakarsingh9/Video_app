package damroo.spinno.com.damroo.settingsfragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.lacronicus.easydatastorelib.DatastoreBuilder;

import butterknife.ButterKnife;
import damroo.spinno.com.damroo.MainActivity;
import damroo.spinno.com.damroo.R;
import damroo.spinno.com.damroo.login_module.LoginPrefrences;
import damroo.spinno.com.damroo.login_module.LoginScreenActivity;

/**
 * Created by spinnosolutions on 11/2/15.
 */
public class FRAGMENT_SETTINGS extends Fragment {

    LoginPrefrences datastore ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, v);
        datastore = new DatastoreBuilder(PreferenceManager.getDefaultSharedPreferences(getActivity())).create(LoginPrefrences.class);

        LinearLayout scrollupfrag = (LinearLayout)v.findViewById(R.id.scrollup);
        TextView logout = (TextView)v.findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mainActivity.finish();
                Intent i = new Intent(getActivity(), LoginScreenActivity.class);
                startActivity(i);
                datastore.user_id().put(null);
            }
        });
        scrollupfrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.FadeOutUp).duration(300).playOn(MainActivity.setting_fragment);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.setting_fragment.setVisibility(View.GONE);
                        Settings_fragment_constants.SearchFragmentConstants = false ;
                    }
                }, 400);
            }
        });


        return v;
    }

    public static FRAGMENT_SETTINGS newInstance(String text) {

        FRAGMENT_SETTINGS f = new FRAGMENT_SETTINGS();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }







}
