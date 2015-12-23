package damroo.spinno.com.damroo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.home.FRAGMENT_HOME;
import damroo.spinno.com.damroo.profileModule.FRAGMENT_PROFILE;
import damroo.spinno.com.damroo.search_fragment.FRAGMENT_SEARCH;
import damroo.spinno.com.damroo.search_fragment.SearchFragmentConstants;
import damroo.spinno.com.damroo.settingsfragment.FRAGMENT_SETTINGS;
import damroo.spinno.com.damroo.settingsfragment.Settings_fragment_constants;

public class MainActivity extends FragmentActivity {

    @Bind(R.id.profile)LinearLayout profile ;
    @Bind(R.id.search)LinearLayout search ;

    @Bind(R.id.containersearch)View search_fragment ;
    //@Bind(R.id.containersettings)View setting_fragment ;

        public  static  MainActivity mainActivity;
    @Bind(R.id.damroo)LinearLayout  damroo  ;

    public static FrameLayout setting_fragment;


    public static android.support.v4.app.FragmentManager fragmentManager  ;
    public static  android.support.v4.app.FragmentTransaction fragmentTransaction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainActivity = MainActivity.this;
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        setting_fragment =(FrameLayout)findViewById(R.id.containersettings);

        fragmentManager = getSupportFragmentManager();

        changefragment(new FRAGMENT_HOME(), "fragment_home");


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FARGMANT_STATUS.profile_fragment == false) {
                    changefragment(new FRAGMENT_PROFILE(), "fragment_profile");
                } else if (FARGMANT_STATUS.profile_fragment == true) {
                    fragmentManager.popBackStack();
                }

            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Settings_fragment_constants.SearchFragmentConstants == true){

                    hideSettingsFragment();

                    if(SearchFragmentConstants.isSearchFragmentIsVisible == false){
                        showSearchfragment();
                    }else if(SearchFragmentConstants.isSearchFragmentIsVisible == true){
                        hideSearchFragment();
                    }
                }else {


                    if(SearchFragmentConstants.isSearchFragmentIsVisible == false){
                        showSearchfragment();
                    }else if(SearchFragmentConstants.isSearchFragmentIsVisible == true){
                        hideSearchFragment();
                    }
                }


            }
        });


        damroo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(SearchFragmentConstants.isSearchFragmentIsVisible == true){

                    hideSearchFragment();
                    if(Settings_fragment_constants.SearchFragmentConstants == false){
                        showSettingsfragment();
                    }else if(Settings_fragment_constants.SearchFragmentConstants == true){
                        hideSettingsFragment();
                    }
                }else {
                    if(Settings_fragment_constants.SearchFragmentConstants == false){
                        showSettingsfragment();
                    }else if(Settings_fragment_constants.SearchFragmentConstants == true){
                        hideSettingsFragment();
                    }
                }



            }
        });
    }

    public static void changefragment(Fragment fragment, String fname) {

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(fname);
        fragmentTransaction.commit();
    }








///////////////////////   show and hide search fragment
    public void showSearchfragment (){


        search_fragment.setVisibility(View.VISIBLE);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containersearch, new FRAGMENT_SEARCH());
        YoYo.with(Techniques.FadeInDown).duration(300).playOn(search_fragment);
        fragmentTransaction.commit();
        SearchFragmentConstants.isSearchFragmentIsVisible = true ;
    }

    public void hideSearchFragment (){
        YoYo.with(Techniques.FadeOutUp).duration(300).playOn(search_fragment);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                search_fragment.setVisibility(View.GONE);
                SearchFragmentConstants.isSearchFragmentIsVisible = false ;
            }
        }, 400);

    }




///////////////////////   show and hide setting fragment


    public void showSettingsfragment (){


        setting_fragment.setVisibility(View.VISIBLE);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containersettings, new FRAGMENT_SETTINGS());
        YoYo.with(Techniques.FadeInDown).duration(300).playOn(setting_fragment);
        fragmentTransaction.commit();
        Settings_fragment_constants.SearchFragmentConstants = true ;
    }

    public void hideSettingsFragment (){
        YoYo.with(Techniques.FadeOutUp).duration(300).playOn(setting_fragment);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setting_fragment.setVisibility(View.GONE);
                Settings_fragment_constants.SearchFragmentConstants = false ;
            }
        }, 400);

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
      if(FARGMANT_STATUS.homefragment == true) {
          finish();
        }else {
          ///   show previous fragment
          super.onBackPressed();
      }
    }
}