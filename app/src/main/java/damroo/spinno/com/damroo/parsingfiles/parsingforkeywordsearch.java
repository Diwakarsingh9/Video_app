package damroo.spinno.com.damroo.parsingfiles;

import android.content.Context;
import android.util.Log;
import android.view.View;
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

import java.util.ArrayList;
import java.util.List;

import damroo.spinno.com.damroo.API_URL;
import damroo.spinno.com.damroo.VolleySingleton;
import damroo.spinno.com.damroo.home.FRAGMENT_HOME;
import damroo.spinno.com.damroo.search_fragment.AdapterListInChannel;
import damroo.spinno.com.damroo.search_fragment.AdapterListInVideo;
import damroo.spinno.com.damroo.search_fragment.FRAGMENT_SEARCH;
import damroo.spinno.com.damroo.settergetter.Innerchannel;
import damroo.spinno.com.damroo.settergetter.Innervideo;
import damroo.spinno.com.damroo.settergetter.KeywordInnersettergetter;
import damroo.spinno.com.damroo.settergetter.KeywordOutersettergetter;

/**
 * Created by saifi45 on 11/25/2015.
 */
public class parsingforkeywordsearch {


    public static RequestQueue queue;
    public static StringRequest sr1,sr2;
    public static List<Innerchannel> data_list_channel;
    public static List<Innervideo> data_list_video;
    public static ArrayList<String> content_id = new ArrayList<String>();
    public static ArrayList<String> content_title = new ArrayList<String>();
    public static ArrayList<String> content_thumbnail = new ArrayList<String>();
    public static ArrayList<String> content_desc = new ArrayList<String>();
    public static ArrayList<String> content_categories = new ArrayList<String>();
    public static ArrayList<String> content_budget = new ArrayList<String>();
    public static ArrayList<String> content_time_dur = new ArrayList<String>();
    public static ArrayList<String> content_status = new ArrayList<String>();
    public static ArrayList<String> user_mail = new ArrayList<String>();
    public static ArrayList<String> user_name = new ArrayList<String>();
    public static ArrayList<String> phone = new ArrayList<String>();
    public static ArrayList<String> firstname = new ArrayList<String>();
    public static ArrayList<String> lastname = new ArrayList<String>();
    public static ArrayList<String> countery = new ArrayList<String>();
    public static ArrayList<String> channel_name = new ArrayList<String>();
    public static ArrayList<String> user_image = new ArrayList<String>();
    public static ArrayList<String> channel_category = new ArrayList<String>();
    public static ArrayList<String> description = new ArrayList<String>();



    public static void parsing(final Context activity,String s1){



        String locationurl2 = API_URL.keywordsearch.concat(s1);
        locationurl2=locationurl2.replace(" ","%20");

        Log.e("url", "" + locationurl2);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                FRAGMENT_SEARCH.pb.setVisibility(View.GONE);
               FRAGMENT_SEARCH.ll.setVisibility(View.VISIBLE);
                content_id.clear();
                content_budget.clear();
                content_categories.clear();
                content_desc.clear();
                content_status.clear();
                content_thumbnail.clear();
                content_time_dur.clear();
                content_title.clear();
                user_mail.clear();
                user_name.clear();
                phone.clear();
                firstname.clear();
                lastname.clear();
                countery.clear();
                channel_name.clear();
                user_image.clear();
                channel_category.clear();
                description.clear();

                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    KeywordOutersettergetter received2 = new KeywordOutersettergetter();
                    received2 = gson.fromJson(response, KeywordOutersettergetter.class);

                    KeywordInnersettergetter data = new KeywordInnersettergetter();
                    data=received2.keywordInnersettergetter;
                        data_list_channel = data.innerchannels;
                    data_list_video= data.innervideos;
//                    if(data_list_channel.size()==0){
//                        FRAGMENT_SEARCH.greenline.setVisibility(View.GONE);
//                        FRAGMENT_SEARCH.inchannels.setVisibility(View.GONE);
//                        FRAGMENT_SEARCH.list_in_channel.setVisibility(View.GONE);
//                    }
//                    if(data_list_video.size()==0){
//                        FRAGMENT_SEARCH.greenline.setVisibility(View.GONE);
//                        FRAGMENT_SEARCH.invideos.setVisibility(View.GONE);
//                        FRAGMENT_SEARCH.list_in_videos.setVisibility(View.GONE);
//                    }
                        for(int i=0;i<data_list_video.size();i++){
                            content_id.add(data_list_video.get(i).content_id);
                            content_budget.add(data_list_video.get(i).content_budget);
                            content_categories.add(data_list_video.get(i).content_categories);
                            content_desc.add(data_list_video.get(i).content_desc);
                            content_status.add(data_list_video.get(i).content_status);
                            content_thumbnail.add(data_list_video.get(i).content_thumbnail);
                            content_time_dur.add(data_list_video.get(i).content_time_dur);
                            content_title.add(data_list_video.get(i).content_title);
                        }
                    for(int i=0;i<data_list_channel.size();i++){
                        user_mail.add(data_list_channel.get(i).user_mail);
                        user_name.add(data_list_channel.get(i).user_name);
                        phone.add(data_list_channel.get(i).phone);
                        firstname.add(data_list_channel.get(i).firstname);
                        lastname.add(data_list_channel.get(i).lastname);
                        countery.add(data_list_channel.get(i).countery);
                        channel_name.add(data_list_channel.get(i).channel_name);
                        user_image.add(data_list_channel.get(i).user_image);
                        channel_category.add(data_list_channel.get(i).channel_category);
                        description.add(data_list_channel.get(i).description);

                    }
                    FRAGMENT_SEARCH.list_in_channel.setAdapter(new AdapterListInChannel(activity, channel_name, user_image));
                    FRAGMENT_HOME.setListViewHeightBasedOnChildren(FRAGMENT_SEARCH.list_in_channel);
                    FRAGMENT_SEARCH.list_in_videos.setAdapter(new AdapterListInVideo(activity,  content_title, content_desc));
                    FRAGMENT_HOME.setListViewHeightBasedOnChildren(FRAGMENT_SEARCH.list_in_videos);

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
        FRAGMENT_SEARCH.pb.setVisibility(View.VISIBLE);
         FRAGMENT_SEARCH.ll.setVisibility(View.GONE);



    }
}
