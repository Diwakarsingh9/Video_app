package damroo.spinno.com.damroo.profileModule;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.edmodo.cropper.CropImageView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lacronicus.easydatastorelib.DatastoreBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import checkers.Toaster;
import damroo.spinno.com.damroo.API_URL;
import damroo.spinno.com.damroo.R;
import damroo.spinno.com.damroo.VolleySingleton;
import damroo.spinno.com.damroo.login_module.CHECK_POJO;
import damroo.spinno.com.damroo.login_module.LoginPrefrences;

public class PhotoCropActivity extends Activity {


    @Bind(R.id.cropimageview)CropImageView cropimageview ;
    @Bind(R.id.cancel)TextView cancel ;
    @Bind(R.id.done)TextView done ;
    @Bind(R.id.testimage)ImageView testimage ;

    String decodedimage ;

    StringRequest sr ;
    RequestQueue queue ;
    ProgressDialog progressDialog ;

    LoginPrefrences datastore ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_crop);
        ButterKnife.bind(this);
        datastore = new DatastoreBuilder(PreferenceManager.getDefaultSharedPreferences(this)).create(LoginPrefrences.class);
        queue = VolleySingleton.getInstance(PhotoCropActivity.this).getRequestQueue();

            decodedimage = getIntent().getStringExtra("image");
            cropimageview.setImageBitmap(BitmapFactory.decodeFile(decodedimage));

        //cropimageview.setImageResource(R.drawable.image_3);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });





        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

////////////////////////////////////////////Encoding of image in base64 String
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                cropimageview.getCroppedImage().compress(Bitmap.CompressFormat.JPEG, 00, baos); //crop image view is view provided by library and get cropped image returns Bitmap image
                byte[] b = baos.toByteArray();

                String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

                PostImage(encodedImage);
                testimage.setImageBitmap(cropimageview.getCroppedImage());
            }
        });
    }

















/////////////////////////////////////////  method used for sending the image
    private void sendimagetopserver( String image) {
        final ProgressDialog pDialog = new ProgressDialog(PhotoCropActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        RequestParams params = new RequestParams();




            try {
                params.put("user_id", datastore.user_id().get());
                params.put("user_photo", image);
            } catch (Exception e) {
                // TODO: handle exception
                Toast.makeText(getApplicationContext(), "file not found", Toast.LENGTH_SHORT).show();

            }


        AsyncHttpClient client = new AsyncHttpClient();

        client.post("" + API_URL.UPLOAD_PROFILE_PIC, params,
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode,
                                          org.apache.http.Header[] headers,
                                          byte[] responseBody) {
                        // TODO Auto-generated method stub
                        if (pDialog.isShowing()) {
                            pDialog.dismiss();
                        }

                        String response = new String(responseBody);
                        Log.d("Response", response);
                        Toaster.generatemessage(PhotoCropActivity.this, "" + response);

/////////////////////////////////////////////////////////////////////////////////////////
//                        try {
//
//
//                            JSONObject jsonObj = new JSONObject(response);
//
//                            String result = jsonObj.getString("result");
//                            String status = jsonObj.getString("status");
//                            Toast.makeText(getApplicationContext(), "" + result, Toast.LENGTH_SHORT).show();
//                            if (status.equals("success")) {
//                                parsingformyphotos.parsingforphotos(Photoactivity.this);
//                            }
//
//                            // Getting JSON Array node
//                            Log.i("result ki ", "" + status);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
////////////////////////////////////////////////////////////////////////////////////////////////////

                    }

                    @Override
                    public void onFailure(int arg0, org.apache.http.Header[] arg1, byte[] arg2, Throwable arg3) {
                        // TODO Auto-generated method stub
                        if (pDialog.isShowing()) {
                            pDialog.dismiss();
                        }
                        Log.d("Status", "Failure");
                    }

                });

    }



    public void PostImage(final String imageencoded) {

        String url = API_URL.UPLOAD_PROFILE_PIC;

        StringRequest ps = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                CHECK_POJO rs = new CHECK_POJO();
                  rs = gson.fromJson(response , CHECK_POJO.class);
                if(rs.result.equals("0")){

                    Toaster.generatemessage(PhotoCropActivity.this , ""+rs.msg
                    );
                }else if(rs.result.equals("1")){
                    Toaster.generatemessage(PhotoCropActivity.this , ""+rs.msg
                    );
                    finish();
                    overridePendingTransition( R.anim.no_sliding  ,  R.anim.fade_out_center);
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "" + error, Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", "14");
                params.put("user_photo", imageencoded);
                return params;
            }


        };

        queue.add(ps);
    }

}
