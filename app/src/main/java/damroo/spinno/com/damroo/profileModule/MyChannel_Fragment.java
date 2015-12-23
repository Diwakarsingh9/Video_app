package damroo.spinno.com.damroo.profileModule;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.R;
import damroo.spinno.com.damroo.home.ChannelActivity;
import damroo.spinno.com.damroo.home.FRAGMENT_HOME;

/**
 * Created by spinnosolutions on 9/15/15.
 */
public class MyChannel_Fragment extends Fragment {

    public static String [] channelnames = {"Rihana Videos" , "Chip munks" , "Italian Food" , "Bombay rockers" , "Technoholic" , "Wild Rockers","Rihana Videos" , "Chip munks" , "Italian Food" , "Bombay rockers" , "Technoholic" , "Wild Rockers"};
    public static int [] Channe_img = {R.drawable.ci_a ,R.drawable.ci_b ,R.drawable.ci_c ,R.drawable.ci_d ,R.drawable.ci_e ,R.drawable.ci_f ,R.drawable.ci_a ,R.drawable.ci_b ,R.drawable.ci_c ,R.drawable.ci_d ,R.drawable.ci_e ,R.drawable.ci_f };

    public  static ListView list ;
    public  static ImageView imgnull11 ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mychannel, container, false);

        ButterKnife.bind(this , v);
        list =(ListView)v.findViewById(R.id.list);
        imgnull11 =(ImageView)v.findViewById(R.id.imgnull11);
        if(FRAGMENT_HOME.channel_name1.size()==0){
            MyChannel_Fragment.imgnull11.setVisibility(View.VISIBLE);
            MyChannel_Fragment.list.setVisibility(View.GONE);
        }
        else {
            MyChannel_Fragment.imgnull11.setVisibility(View.GONE);
            MyChannel_Fragment.list.setVisibility(View.VISIBLE);
            list.setAdapter(new AdapterMyChannels(getActivity(), FRAGMENT_HOME.channel_name1, FRAGMENT_HOME.user_image1));
        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(getActivity(),ChannelActivity.class);
                startActivity(in);
                //Toast.makeText(getActivity(), "chla", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    public static MyChannel_Fragment newInstance(String text) {

        MyChannel_Fragment f = new MyChannel_Fragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    @Override
    public void onResume() {
        super.onResume();
        //Toast.makeText(getActivity(), "mychannel"+FRAGMENT_HOME.channel_name1.size(), Toast.LENGTH_SHORT).show();
        if(FRAGMENT_HOME.channel_name1.size()==0){
            MyChannel_Fragment.imgnull11.setVisibility(View.VISIBLE);
            MyChannel_Fragment.list.setVisibility(View.GONE);
        }
        else {
            MyChannel_Fragment.imgnull11.setVisibility(View.GONE);
            MyChannel_Fragment.list.setVisibility(View.VISIBLE);
            list.setAdapter(new AdapterMyChannels(getActivity(), FRAGMENT_HOME.channel_name1, FRAGMENT_HOME.user_image1));
        }
    }
}