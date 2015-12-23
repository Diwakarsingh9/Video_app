package damroo.spinno.com.damroo.login_module;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lacronicus.easydatastorelib.DatastoreBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.API_URL;
import damroo.spinno.com.damroo.MainActivity;
import damroo.spinno.com.damroo.R;
import damroo.spinno.com.damroo.VolleySingleton;

public class GenderActivity extends Activity {


    @Bind(R.id.continue_btn)Button continue_btn ;
    @Bind(R.id.male)LinearLayout male ;
    @Bind(R.id.female)LinearLayout female ;

    StringRequest sr ;
    RequestQueue queue ;
    ProgressDialog progressDialog ;

    LoginPrefrences datastore ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
        ButterKnife.bind(this);
        queue = VolleySingleton.getInstance(GenderActivity.this).getRequestQueue();

         datastore = new DatastoreBuilder(PreferenceManager.getDefaultSharedPreferences(this)).create(LoginPrefrences.class);

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginConstants.Gender = "Male";
            }
        });


        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginConstants.Gender = "Female";
            }
        });




        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                doregistrationcall();

            }
        });
    }







    private void doregistrationcall() {
        String url =  API_URL.REGISTREATION_USER+LoginConstants.Email_temp+"&email="+LoginConstants.Email_temp+"&password="+LoginConstants.Password_temp+"&user_role="+LoginConstants.UserRole_temp+"&phone="+LoginConstants.Phone_temp+"&firstname="+LoginConstants.FirstName_temp+"&lastname="+LoginConstants.LastName_temp+"&dob="+LoginConstants.DateOfBirth_temp+"&gender="+LoginConstants.Gender    ;
        url=url.replace(" ","%20");
        Log.d("Registration url" , ""+url);

        sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                CHECK_POJO rs = new CHECK_POJO();
                rs = gson.fromJson(response,CHECK_POJO.class);

                if(rs.result.equals("0")){
                    Toast.makeText(GenderActivity.this , ""+rs.msg , Toast.LENGTH_SHORT).show();
                }else if(rs.result.equals("1")){
                    REGISTRATION_SUCCESS_STATUS  rss = new REGISTRATION_SUCCESS_STATUS();
                    rss = gson.fromJson(response,REGISTRATION_SUCCESS_STATUS.class);

                    ///////  Shared Prefrences ///////
                    datastore.user_id().put(rss.udp.user_id);
                    datastore.user_name().put(rss.udp.user_name);
                    datastore.user_mail().put(rss.udp.user_pass);
                    datastore.user_pass().put(rss.udp.user_pass);
                    datastore.user_role().put(LoginConstants.UserRole_temp);
                    datastore.category().put("to be done");
                    datastore.user_status().put(rss.udp.user_status);
                    datastore.phone().put(rss.udp.phone);
                    datastore.firstname().put(rss.udp.firstname);
                    datastore.lastname().put(rss.udp.lastname);
                    datastore.dob().put(rss.udp.dob);
                    datastore.gender().put(rss.udp.gender);

                    finishpreviousactivities();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(GenderActivity.this , "Network Problem while Registering User. " , Toast.LENGTH_SHORT).show();
            }
        });
        sr.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr);
        progressDialog = new ProgressDialog(GenderActivity.this);
        progressDialog.setMessage("Registering...");
        progressDialog.show();
    }

    private void finishpreviousactivities() {
        LoginScreenActivity.activity.finish();
        SignInTerms.activity.finish();
        EmailPhoneActivity.activity.finish();
        YourNameActivity.activity.finish();
        YourBirthdayActivity.activity.finish();
        ChoosePassword.activity.finish();
        startActivity(new Intent(GenderActivity.this, CategorySelectionActivity.class));
        finish();
    }
}
