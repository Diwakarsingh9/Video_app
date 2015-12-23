package damroo.spinno.com.damroo.parsingfiles;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import damroo.spinno.com.damroo.API_URL;
import damroo.spinno.com.damroo.VolleySingleton;
import damroo.spinno.com.damroo.home.FRAGMENT_HOME;
import damroo.spinno.com.damroo.search_fragment.AdapterListInChannel;
import damroo.spinno.com.damroo.search_fragment.AdapterListInVideo;
import damroo.spinno.com.damroo.search_fragment.FRAGMENT_SEARCH;
import damroo.spinno.com.damroo.settergetter.Innerchannel;
import damroo.spinno.com.damroo.settergetter.Innersubscribed;
import damroo.spinno.com.damroo.settergetter.KeywordInnersettergetter;
import damroo.spinno.com.damroo.settergetter.KeywordOutersettergetter;
import damroo.spinno.com.damroo.settergetter.settergetterforrecommendedouter;
import damroo.spinno.com.damroo.settergetter.settergettersubscribedoutter;

/**
 * Created by saifi45 on 12/1/2015.
 */
public class parsingforsubscribed {
    public static RequestQueue queue11;
    public static StringRequest sr1,sr21;
    public static List<Innersubscribed> data_list_channel1;
    public static ArrayList<String> user_mail1 = new ArrayList<String>();
    public static ArrayList<String> user_name1 = new ArrayList<String>();
    public static ArrayList<String> phone1 = new ArrayList<String>();
    public static ArrayList<String> firstname1 = new ArrayList<String>();
    public static ArrayList<String> lastname1 = new ArrayList<String>();
    public static ArrayList<String> countery1 = new ArrayList<String>();
    public static ArrayList<String> channel_name1 = new ArrayList<String>();
    public static ArrayList<String> user_image1 = new ArrayList<String>();
    public static ArrayList<String> channel_category1 = new ArrayList<String>();
    public static ArrayList<String> description1 = new ArrayList<String>();

    public static void parsing1(final Context activity,String s1){



        String locationurl2 = API_URL.subscribedchannel.concat(s1);
        locationurl2=locationurl2.replace(" ","%20");

        Log.e("url", "" + locationurl2);
        queue11 = VolleySingleton.getInstance(activity).getRequestQueue();
        sr21 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                user_mail1.clear();
                user_name1.clear();
                phone1.clear();
                firstname1.clear();
                lastname1.clear();
                countery1.clear();
                channel_name1.clear();
                user_image1.clear();
                channel_category1.clear();
                description1.clear();

                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    settergettersubscribedoutter received2 = new settergettersubscribedoutter();
                    received2 = gson.fromJson(response, settergettersubscribedoutter.class);


                    data_list_channel1 = received2.data;

//
                    for(int i=0;i<data_list_channel1.size();i++){
                        user_mail1.add(data_list_channel1.get(i).user_mail);
                        user_name1.add(data_list_channel1.get(i).user_name);
                        phone1.add(data_list_channel1.get(i).phone);
                        firstname1.add(data_list_channel1.get(i).firstname);
                        lastname1.add(data_list_channel1.get(i).lastname);
                        countery1.add(data_list_channel1.get(i).countery);
                        channel_name1.add(data_list_channel1.get(i).channel_name);
                        user_image1.add(data_list_channel1.get(i).user_image);
                        channel_category1.add(data_list_channel1.get(i).channel_category);
                        description1.add(data_list_channel1.get(i).description);

                    }


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
        sr21.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue11.add(sr21);




    }
}
