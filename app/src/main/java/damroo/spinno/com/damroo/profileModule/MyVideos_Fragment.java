package damroo.spinno.com.damroo.profileModule;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.lacronicus.easydatastorelib.DatastoreBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.R;
import damroo.spinno.com.damroo.login_module.LoginPrefrences;
import damroo.spinno.com.damroo.parsingfiles.parsingformyvideos;

/**
 * Created by spinnosolutions on 9/15/15.
 */
public class MyVideos_Fragment extends Fragment {

        LoginPrefrences datastore;
    public static ListView myvideo ;
    public static ProgressBar pb;
    public static ImageView imagnull;
    public static int [] images = {R.drawable.camera_girl_a,R.drawable.camera_girl_b,R.drawable.camera_girl_c,R.drawable.camera_girl_d,R.drawable.camera_girl_e,R.drawable.camera_girl_a,R.drawable.camera_girl_b,R.drawable.camera_girl_c};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_myvideo, container, false);

        ButterKnife.bind(this ,v);
        myvideo = (ListView)v.findViewById(R.id.myvideo);
        pb = (ProgressBar)v.findViewById(R.id.progressBar);
        imagnull = (ImageView)v.findViewById(R.id.imgnull);
        datastore = new DatastoreBuilder(PreferenceManager.getDefaultSharedPreferences(getActivity())).create(LoginPrefrences.class);

        //myvideo.setAdapter(new AdapterMyVideoList(getActivity() , images));

        return v;
    }

    public static MyVideos_Fragment newInstance(String text) {

        MyVideos_Fragment f = new MyVideos_Fragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    @Override
    public void onResume() {
        super.onResume();
        parsingformyvideos.parsing(getActivity(),""+datastore.user_id().get());
    }
}
