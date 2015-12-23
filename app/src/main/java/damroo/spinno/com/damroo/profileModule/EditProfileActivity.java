package damroo.spinno.com.damroo.profileModule;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
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
import checkers.Toaster;
import damroo.spinno.com.damroo.API_URL;
import damroo.spinno.com.damroo.R;
import damroo.spinno.com.damroo.VolleySingleton;
import damroo.spinno.com.damroo.login_module.LoginPrefrences;
import damroo.spinno.com.damroo.login_module.User_info;

public class EditProfileActivity extends Activity {


    @Bind(R.id.channelname)AnyEditTextView channelname ;
    @Bind(R.id.website)AnyEditTextView website ;
    @Bind(R.id.tags)AnyEditTextView tags ;


    @Bind(R.id.username)AnyEditTextView username ;
    @Bind(R.id.useremail)AnyEditTextView useremail ;
    @Bind(R.id.userphone)AnyEditTextView userphone ;

    @Bind(R.id.changepassword)LinearLayout changepassword ;

    @Bind(R.id.cancel)LinearLayout cancel ;
    @Bind(R.id.done)LinearLayout done ;

    StringRequest sr ;
    RequestQueue queue ;
    ProgressDialog progressDialog ;
    User_info userinfo;
    LoginPrefrences datastore ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        queue = VolleySingleton.getInstance(EditProfileActivity.this).getRequestQueue();
        datastore = new DatastoreBuilder(PreferenceManager.getDefaultSharedPreferences(this)).create(LoginPrefrences.class);
        userinfo = new DatastoreBuilder(PreferenceManager.getDefaultSharedPreferences(this)).create(User_info.class);

        if(userinfo.public_name()+""==null){
            channelname.setHint("Public Name");
            website.setHint("Website");
            tags.setHint("About Yourself");
            username.setHint("Private Name");
            useremail.setHint("E-Mail Address");
            userphone.setHint("Mobile Number");

        }
        else {
            channelname.setText("" + datastore.firstname().get()+" "+datastore.lastname().get());
            website.setText("" + datastore.website().get());
            tags.setText("" + datastore.description().get());
            username.setText("" + datastore.user_name().get());
            useremail.setText("" + datastore.user_mail().get());
            userphone.setText("" + datastore.phone().get());
        }
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editdetails();
            }
        });



        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditProfileActivity.this , EditPasswordActivity.class));
                overridePendingTransition(R.anim.fade_in_center , R.anim.no_sliding);
            }
        });



    }

    private void editdetails() {

        String url =  API_URL.EDITPROFILE +datastore.user_id().get()+"&public_name="+channelname.getText().toString()+"&website="+website.getText().toString()+"&public_info="+tags.getText().toString()+" &personal_name="+username.getText().toString()+"&email="+useremail.getText().toString()+"&phone_number="+userphone.getText().toString();
        url=url.replace(" ","%20");
        Log.e("EDIT PROFILE  URL", "" + url);

        sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                EDIT_REESPONSE  er  = new EDIT_REESPONSE();
                er = gson.fromJson(response , EDIT_REESPONSE.class);

                if(er.result.equals("0")){
                    Toaster.generatemessage(EditProfileActivity.this , ""+er.msg);
                }else if (er.result.equals("1")){
                    Toaster.generatemessage(EditProfileActivity.this , ""+er.msg);
                    finish();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(EditProfileActivity.this , "Please check Your internet connection." , Toast.LENGTH_SHORT).show();
            }
        });
        sr.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr);
        progressDialog = new ProgressDialog(EditProfileActivity.this);
        progressDialog.setMessage("Saving Your Information...");
        progressDialog.show();


    }







}
