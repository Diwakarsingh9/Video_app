package damroo.spinno.com.damroo.profileModule;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ctrlplusz.anytextview.AnyEditTextView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lacronicus.easydatastorelib.DatastoreBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.API_URL;
import damroo.spinno.com.damroo.R;
import damroo.spinno.com.damroo.VolleySingleton;
import damroo.spinno.com.damroo.login_module.CHECK_POJO;
import damroo.spinno.com.damroo.login_module.LoginPrefrences;

public class EditPasswordActivity extends Activity {

    @Bind(R.id.oldpassword)AnyEditTextView oldpassword ;
    @Bind(R.id.newpassword)AnyEditTextView newpassword ;
    @Bind(R.id.changepassword)TextView changepassword ;
    @Bind(R.id.cancel)TextView cancel ;
    @Bind(R.id.changepasswordmessage)TextView changepasswordmessage ;

    StringRequest sr ;
    RequestQueue queue ;
    ProgressDialog progressDialog ;

    LoginPrefrences datastore ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);
        ButterKnife.bind(this);

        datastore = new DatastoreBuilder(PreferenceManager.getDefaultSharedPreferences(this)).create(LoginPrefrences.class);
        queue = VolleySingleton.getInstance(EditPasswordActivity.this).getRequestQueue();




        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(oldpassword.getText().toString().equals("") ||  newpassword.getText().toString().equals("")){

                    changepasswordmessage.setText(" Please enter the details first.");
                }else {
                    changepasswordcall();
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.no_sliding , R.anim.fade_out_center);
            }
        });


    }

    private void changepasswordcall() {
        String url =  API_URL.CHANGE_PASSWORD+datastore.user_id().get()+"&current_pass="+oldpassword.getText().toString()+"&new_pass="+newpassword.getText().toString();
        url=url.replace(" ","%20");
        Log.d("Change Password URL ", "" + url);

        sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                CHECK_POJO cp  = new CHECK_POJO();
                cp = gson.fromJson(response , CHECK_POJO.class);

                changepasswordmessage.setText("" + cp.msg);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(EditPasswordActivity.this , "Please check Your internet connection." , Toast.LENGTH_SHORT).show();
            }
        });
        sr.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr);
        progressDialog = new ProgressDialog(EditPasswordActivity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
    }

    }


