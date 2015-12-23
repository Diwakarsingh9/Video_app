package damroo.spinno.com.damroo.home;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import damroo.spinno.com.damroo.FARGMANT_STATUS;
import damroo.spinno.com.damroo.MainActivity;
import damroo.spinno.com.damroo.R;
import damroo.spinno.com.damroo.profileModule.FRAGMENT_PROFILE;

public class ChannelActivity extends Activity {
    LinearLayout llforvideos;
//    LinearLayout user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);


        llforvideos =(LinearLayout)findViewById(R.id.layoutforvideos);
        for(int i=0;i<41;i++){

            llforvideos.addView(ordersview(R.layout.itemforvideochannel));
        }

    }
    private View ordersview(int layout_name) {

        LayoutInflater layoutInflater =
                (LayoutInflater)ChannelActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layout_name, null);
        final ImageView cb = (ImageView) addView.findViewById(R.id.image);

        cb.setImageResource(R.drawable.image_3);

        return addView ;
    }

}
