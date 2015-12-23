package damroo.spinno.com.damroo.login_module;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.lacronicus.easydatastorelib.DatastoreBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.API_URL;
import damroo.spinno.com.damroo.MainActivity;
import damroo.spinno.com.damroo.R;
import damroo.spinno.com.damroo.VolleySingleton;


public class LoginScreenActivity extends Activity {

    @Bind(R.id.login)LinearLayout login ;
    @Bind(R.id.signup) TextView sign_up ;

    @Bind(R.id.username) TextView username ;
    @Bind(R.id.password) TextView password ;
    public static  Activity activity ;

    StringRequest sr ;
    RequestQueue queue ;
    ProgressDialog progressDialog ;

    LoginPrefrences datastore ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        ButterKnife.bind(this);
        activity = this ;
        datastore = new DatastoreBuilder(PreferenceManager.getDefaultSharedPreferences(this)).create(LoginPrefrences.class);
        queue = VolleySingleton.getInstance(LoginScreenActivity.this).getRequestQueue();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(username.getText().toString().equals("")  ||  password.getText().toString().equals("")){
                    Toast.makeText(LoginScreenActivity.this , "Please enter correct user name and password please." , Toast.LENGTH_SHORT).show();
                }else {
                    dologincall();
                }


            }
        });


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginScreenActivity.this, SignInTerms.class));

            }
        });
    }

    private void dologincall() {
        String url =  API_URL.LOGIN+username.getText().toString()+"&password="+password.getText().toString();
        url=url.replace(" ","%20");
        Log.e("Registration url", "" + url);

        sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();

                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    CHECK_POJO rs = new CHECK_POJO();
                    rs = gson.fromJson(response,CHECK_POJO.class);

                    if(rs.result.equals("0")){
                        Toast.makeText(LoginScreenActivity.this , ""+rs.msg , Toast.LENGTH_SHORT).show();
                    }else if(rs.result.equals("1")){
                        LOGIN_SUCCESS  ls = new LOGIN_SUCCESS();
                        ls = gson.fromJson(response,LOGIN_SUCCESS.class);

                        ///////  Shared Prefrences ///////
                        Toast.makeText(LoginScreenActivity.this, ""+ls.details.website+"  "+ls.details.description, Toast.LENGTH_SHORT).show();
                        datastore.user_id().put(ls.details.user_id);
                        datastore.user_name().put(ls.details.user_name);
                        datastore.user_mail().put(ls.details.user_mail);
                        datastore.user_pass().put(ls.details.user_pass);
                        datastore.user_role().put(LoginConstants.UserRole_temp);
                        datastore.category().put("to be done");
                        datastore.user_status().put(ls.details.user_status);
                        datastore.phone().put(ls.details.phone);
                        datastore.firstname().put(ls.details.firstname);
                        datastore.lastname().put(ls.details.lastname);
                        datastore.dob().put(ls.details.dob);
                        datastore.gender().put(ls.details.gender);
                        datastore.website().put(ls.details.website);
                        datastore.description().put(ls.details.description);
                        datastore.userimage().put(ls.details.user_image);
                        startActivity(new Intent(LoginScreenActivity.this, MainActivity.class));
                        finish();

                    }
                } catch (Exception e) {
                    Log.e("exception", "" + e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(LoginScreenActivity.this , "Please check Your internet connection." , Toast.LENGTH_SHORT).show();
            }
        });
        sr.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr);
        progressDialog = new ProgressDialog(LoginScreenActivity.this);
        progressDialog.setMessage("Sighning in...");
        progressDialog.show();
    }
}



//   http://keshavgoyal.com/damroo/api/login.php?email=samirdemo@gmail&password=12345678
//   http://keshavgoyal.com/damroo/api/login.php?email=samirdemo@gmail.com&password=12345678