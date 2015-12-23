package damroo.spinno.com.damroo.login_module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greenfrvr.rubberloader.RubberLoaderView;
import com.joooonho.SelectableRoundedImageView;
import com.lacronicus.easydatastorelib.DatastoreBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.API_URL;
import damroo.spinno.com.damroo.MainActivity;
import damroo.spinno.com.damroo.R;
import damroo.spinno.com.damroo.VolleySingleton;
import damroo.spinno.com.damroo.settergetter.Innerchannel;
import damroo.spinno.com.damroo.settergetter.Innerdatacat;
import damroo.spinno.com.damroo.settergetter.KeywordInnersettergetter;
import damroo.spinno.com.damroo.settergetter.KeywordOutersettergetter;
import damroo.spinno.com.damroo.settergetter.settergettercategoriesouter;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class CategorySelectionActivity extends Activity {

    @Bind(R.id.done)TextView done ;
    @Bind(R.id.cancel)TextView cancel ;
    LinearLayout tray ;


    @Bind(R.id.hscrollview)HorizontalScrollView hscrollview ;
    @Bind(R.id.bottomdivider)LinearLayout bottomdivider ;
    @Bind(R.id.loader3)RubberLoaderView loader ;

        public static ArrayList<String>  categry_id = new ArrayList<>();
    public static FeatureCoverFlow mCoverFlow;
    public static CoverFlowAdapter mAdapter;
    public static ArrayList<CoverEntity> mData = new ArrayList<>();
    public static TextSwitcher mTitle;
        String []vg={"Sports.png","Sports.png","Sports.png","Sports.png"};
    public static StringRequest sr ;
    public static RequestQueue queue ;
    public static List<Innerdatacat> datacat;
    public static ArrayList<String> cat_id = new ArrayList<String>();
    public static ArrayList<String> cat_name = new ArrayList<String>();
    public static ArrayList<String> cat_image = new ArrayList<String>();
    public static String sd="";

    CategoryPrefrences  cp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);
        ButterKnife.bind(this);
        queue = VolleySingleton.getInstance(CategorySelectionActivity.this).getRequestQueue();
        cp = new DatastoreBuilder(PreferenceManager.getDefaultSharedPreferences(this)).create(CategoryPrefrences.class);

        tray = (LinearLayout) findViewById(R.id.tray);

        loadcategories();
//        mData.add(new CoverEntity("Sports.png", ""  ));
//        mData.add(new CoverEntity("Sports.png",""));
//        mData.add(new CoverEntity("Sports.png",""));
//        mData.add(new CoverEntity("Sports.png",""));


//












        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<categry_id.size();i++){
                    sd=sd+categry_id.get(i)+",";
                }
                Log.e("category_id",""+sd);
                cp.cat_id().put(""+sd);
                startActivity(new Intent(CategorySelectionActivity.this, MainActivity.class));
                finish();
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategorySelectionActivity.this, MainActivity.class));
                finish();
            }
        });


    }







    private void loadcategories() {
        String url =  API_URL.CATEGORIES ;
        url=url.replace(" ","%20");
        Log.d("Registration url", "" + url);

        sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                  //   showviews();
                cat_id.clear();
                cat_image.clear();
                cat_name.clear();
                mData.clear();
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                settergettercategoriesouter received2 = new settergettercategoriesouter();
                received2 = gson.fromJson(response, settergettercategoriesouter.class);


                datacat = received2.msg;
                for(int i=0;i<datacat.size();i++){
                    cat_id.add(datacat.get(i).cat_id);
                    cat_name.add(datacat.get(i).cat_name);
                    cat_image.add(datacat.get(i).image);

                }
                for(int i=0;i<datacat.size();i++){
                   // Toast.makeText(CategorySelectionActivity.this, ""+cat_image.get(i), Toast.LENGTH_SHORT).show();
                    mData.add(new CoverEntity(cat_image.get(i),""+cat_name.get(i)));

                }
//               mTitle = (TextSwitcher) findViewById(R.id.title);
//                mTitle.setFactory(new ViewSwitcher.ViewFactory() {
//                    @Override
//                    public View makeView() {
//                        LayoutInflater inflater = LayoutInflater.from(CategorySelectionActivity.this);
//                        TextView textView = (TextView) inflater.inflate(R.layout.item_title, null);
//                        return textView;
//                    }
//                });
//                Animation in = AnimationUtils.loadAnimation(CategorySelectionActivity.this, R.anim.slide_in_top);
//                Animation out = AnimationUtils.loadAnimation(CategorySelectionActivity.this, R.anim.slide_out_bottom);
//                mTitle.setInAnimation(in);
//                mTitle.setOutAnimation(out);

                mAdapter = new CoverFlowAdapter(CategorySelectionActivity.this);
                mAdapter.setData(mData);
                mCoverFlow = (FeatureCoverFlow)findViewById(R.id.coverflow);
                mCoverFlow.setAdapter(mAdapter);
                mCoverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                Toast.makeText(CategorySelectionActivity.this,
//                        getResources().getString(mData.get(position).titleResId),
//                       Toast.LENGTH_SHORT).show();
//                Toast.makeText(CategorySelectionActivity.this , "Position " +position, Toast.LENGTH_SHORT ).show();

                        addviewincart(cat_image.get(position),cat_id.get(position),position);

                    }
                });
               // mTitle.setText("" );
                mCoverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
                    @Override
                    public void onScrolledToPosition(int position) {
                      //  mTitle.setText(""+cat_name.get(position));
                    }

                    @Override
                    public void onScrolling() {
                        //mTitle.setText("");
                    }
                });

//
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              //  showviews();
                Toast.makeText(CategorySelectionActivity.this , "Please check Your internet connection." , Toast.LENGTH_SHORT).show();
            }
        });
        sr.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr);
        //hideviews();




    }

    private void addviewincart(String s, String s1, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addView = layoutInflater.inflate(R.layout.image_layout, null);
        SelectableRoundedImageView img = (SelectableRoundedImageView)addView.findViewById(R.id.imageeee);
        Picasso.with(CategorySelectionActivity.this).load("http://keshavgoyal.com/damroo/uploads/cat_icon/" + s).into(img);
        categry_id.add(position, s1);
        addView.setTag(position);
        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) addView.getTag();
                categry_id.remove(pos);
                tray.removeView(addView);
            }
        });

        tray.addView(addView);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CategorySelectionActivity.this, MainActivity.class));
        finish();
    }















//    public void hideviews(){
//        mCoverFlow.setVisibility(View.GONE);
//        mTitle.setVisibility(View.GONE);
//        bottomdivider.setVisibility(View.GONE);
//        hscrollview.setVisibility(View.GONE);
//        loader.startLoading();
//
//    }
//
//    public void showviews(){
//        mCoverFlow.setVisibility(View.VISIBLE);
//        mTitle.setVisibility(View.VISIBLE);
//        bottomdivider.setVisibility(View.VISIBLE);
//        hscrollview.setVisibility(View.VISIBLE);
//        loader.setVisibility(View.GONE);
//    }
}