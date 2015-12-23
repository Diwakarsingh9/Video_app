package damroo.spinno.com.damroo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.lacronicus.easydatastorelib.DatastoreBuilder;

import damroo.spinno.com.damroo.login_module.LoginPrefrences;
import damroo.spinno.com.damroo.login_module.LoginScreenActivity;

public class SplashScreenActivity extends Activity {

    LoginPrefrences  datastore ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        datastore = new DatastoreBuilder(PreferenceManager.getDefaultSharedPreferences(this)).create(LoginPrefrences.class);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (datastore.user_id().get() != null) {
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                    finish();
                } else {
                    startActivity(new Intent(SplashScreenActivity.this, LoginScreenActivity.class));
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                    finish();
                }
            }
        }, 2000);




    }

}
