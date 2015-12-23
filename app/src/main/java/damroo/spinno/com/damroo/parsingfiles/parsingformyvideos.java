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
import damroo.spinno.com.damroo.profileModule.AdapterMyVideoList;
import damroo.spinno.com.damroo.profileModule.FRAGMENT_PROFILE;
import damroo.spinno.com.damroo.profileModule.MyVideos_Fragment;
import damroo.spinno.com.damroo.search_fragment.AdapterListInChannel;
import damroo.spinno.com.damroo.search_fragment.AdapterListInVideo;
import damroo.spinno.com.damroo.search_fragment.FRAGMENT_SEARCH;
import damroo.spinno.com.damroo.settergetter.Innerchannel;
import damroo.spinno.com.damroo.settergetter.Innermyvideos;
import damroo.spinno.com.damroo.settergetter.Innervideo;
import damroo.spinno.com.damroo.settergetter.KeywordInnersettergetter;
import damroo.spinno.com.damroo.settergetter.KeywordOutersettergetter;
import damroo.spinno.com.damroo.settergetter.settergettermyvideosoutter;

/**
 * Created by saifi45 on 11/27/2015.
 */
public class parsingformyvideos {

    public static RequestQueue queue;
    public static StringRequest sr1,sr2;
    public static List<Innermyvideos> data_list_video;

    public static ArrayList<String> content_id = new ArrayList<String>();
    public static ArrayList<String> content_title = new ArrayList<String>();
    public static ArrayList<String> content_thumbnail = new ArrayList<String>();
    public static ArrayList<String> content_desc = new ArrayList<String>();
    public static ArrayList<String> content_categories = new ArrayList<String>();
    public static ArrayList<String> content_budget = new ArrayList<String>();
    public static ArrayList<String> content_time_dur = new ArrayList<String>();
    public static ArrayList<String> content_status = new ArrayList<String>();




    public static void parsing(final Context activity,String s1){



        String locationurl2 = API_URL.myvideos.concat(s1);
        locationurl2=locationurl2.replace(" ","%20");

        Log.e("url", "" + locationurl2);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                MyVideos_Fragment.pb.setVisibility(View.GONE);
                MyVideos_Fragment.myvideo.setVisibility(View.VISIBLE);
                content_id.clear();
                content_budget.clear();
                content_categories.clear();
                content_desc.clear();
                content_status.clear();
                content_thumbnail.clear();
                content_time_dur.clear();
                content_title.clear();


                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    settergettermyvideosoutter received2 = new settergettermyvideosoutter();
                    received2 = gson.fromJson(response, settergettermyvideosoutter.class);


                    data_list_video = received2.innermyvideos;
                    if(received2.result.equals("0")){
                        MyVideos_Fragment.imagnull.setVisibility(View.VISIBLE);
                        MyVideos_Fragment.myvideo.setVisibility(View.GONE);
                    }
                    else {
                        MyVideos_Fragment.imagnull.setVisibility(View.GONE);
                        MyVideos_Fragment.myvideo.setVisibility(View.VISIBLE);
                        for (int i = 0; i < data_list_video.size(); i++) {
                            content_id.add(data_list_video.get(i).content_id);
                            content_budget.add(data_list_video.get(i).content_budget);
                            content_categories.add(data_list_video.get(i).content_categories);
                            content_desc.add(data_list_video.get(i).content_desc);
                            content_status.add(data_list_video.get(i).content_status);
                            content_thumbnail.add(data_list_video.get(i).content_thumbnail);
                            content_time_dur.add(data_list_video.get(i).content_time_dur);
                            content_title.add(data_list_video.get(i).content_title);
                        }
                        FRAGMENT_PROFILE.myvideocnt.setText("" + content_id.size());
                        MyVideos_Fragment.myvideo.setAdapter(new AdapterMyVideoList(activity, content_thumbnail, content_title, content_desc, content_time_dur));
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
        sr2.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr2);
        MyVideos_Fragment.pb.setVisibility(View.VISIBLE);
        MyVideos_Fragment.myvideo.setVisibility(View.GONE);



    }
}
