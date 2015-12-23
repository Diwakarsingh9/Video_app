package damroo.spinno.com.damroo.parsingfiles;

import android.content.Context;
import android.util.Log;
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

import damroo.spinno.com.damroo.API_URL;
import damroo.spinno.com.damroo.VolleySingleton;
import damroo.spinno.com.damroo.home.FRAGMENT_HOME;
import damroo.spinno.com.damroo.settergetter.settergetterlikeunlike;

/**
 * Created by saifi45 on 11/30/2015.
 */
public class parsingforlikeunlikevideos {

    public static RequestQueue queue;
    public static StringRequest sr1,sr2;





    public static void parsing(final Context activity, String s1, String s){



        String locationurl2 = API_URL.likeunlike.concat(s1).concat(API_URL.likeunlike1).concat("14").concat(API_URL.likeunlike2).concat(s);
        locationurl2=locationurl2.replace(" ", "%20");

        Log.e("url", "" + locationurl2);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    settergetterlikeunlike received2 = new settergetterlikeunlike();
                    received2 = gson.fromJson(response, settergetterlikeunlike.class);

                    Toast.makeText(activity, ""+received2.message, Toast.LENGTH_SHORT).show();
                    FRAGMENT_HOME.loaddata();


                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    Log.e("exception", "" + e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        sr2.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr2);




    }
}
