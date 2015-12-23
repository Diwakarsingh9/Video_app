package damroo.spinno.com.damroo.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
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
import com.github.florent37.materialleanback.MaterialLeanBack;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.lacronicus.easydatastorelib.DatastoreBuilder;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.API_URL;
import damroo.spinno.com.damroo.FARGMANT_STATUS;
import damroo.spinno.com.damroo.R;
import damroo.spinno.com.damroo.VolleySingleton;
import damroo.spinno.com.damroo.login_module.CategoryPrefrences;
import damroo.spinno.com.damroo.login_module.CategorySelectionActivity;
import damroo.spinno.com.damroo.login_module.CoverEntity;
import damroo.spinno.com.damroo.login_module.CoverFlowAdapter;

import damroo.spinno.com.damroo.login_module.LoginPrefrences;
import damroo.spinno.com.damroo.profileModule.AdapterMyChannels;
import damroo.spinno.com.damroo.profileModule.MyChannel_Fragment;
import damroo.spinno.com.damroo.settergetter.ApiforhomescreenOutterSettergetter;
import damroo.spinno.com.damroo.settergetter.Innerchannel;
import damroo.spinno.com.damroo.settergetter.Innerdataapiforhome;
import damroo.spinno.com.damroo.settergetter.Innerdatacat;
import damroo.spinno.com.damroo.settergetter.Innersubscribed;
import damroo.spinno.com.damroo.settergetter.settergettercategoriesouter;
import damroo.spinno.com.damroo.settergetter.settergetterforrecommendedouter;
import damroo.spinno.com.damroo.settergetter.settergettersubscribedoutter;
import damroo.spinno.com.damroo.videoview.VideoViewActivity;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

/**
 * Created by spinnosolutions on 9/15/15.
 */
public class FRAGMENT_HOME extends Fragment {

    public static ViewPager viewPager;

    com.astuetz.PagerSlidingTabStrip tabs;
    LinearLayout llforheader;
  // @Bind(R.id.homelist)RecyclerView homelist;
  public static ListView homelist;
    @Bind(R.id.materialLeanBack)MaterialLeanBack materialLeanBack;
    @Bind(R.id.sliding_layout )SlidingUpPanelLayout sliding_layout ;
   // @Bind(R.id.category_carousal_container )LinearLayout category_carousal_container ;
   // @Bind(R.id.innerfragmentcontainer )LinearLayout innerfragmentcontainer ;
   // public static LinearLayout innerfragmentcontainer;
        public  static  TextView channelsfragheader, categoriesfragheader;

    private List<ListItem> listitems;


    private FeatureCoverFlow mCoverFlow;
    private damroo.spinno.com.damroo.login_module.CoverFlowAdapter mAdapter;
    private ArrayList<damroo.spinno.com.damroo.login_module.CoverEntity> mData = new ArrayList<>(0);
    private TextSwitcher mTitle;

    String []vg={"Sports.png","Sports.png","Sports.png","Sports.png"};
    static String s="";
    static String s1="";
    public static List<Innerdataapiforhome> data1;

    public static Context mContext;
   public static LinearLayout lv1;
    public static List<Innerdatacat> datacat;
    public static ArrayList<String> cat_id = new ArrayList<String>();
    public static ArrayList<String> cat_name = new ArrayList<String>();
    public static ArrayList<String> cat_image = new ArrayList<String>();
    public static  StringRequest sr ;
    public static  RequestQueue queue ,queue111;
    public static  ProgressDialog progressDialog ;
    public static  ArrayList<String> content_id= new ArrayList<>();
    public static ArrayList<String> content_itewm= new ArrayList<>();
    public static ArrayList<String> content_title = new ArrayList<>();
    public static ArrayList<String> content_thumbnail = new ArrayList<>();
    public static ArrayList<String> content_tags = new ArrayList<>();
    public static  ArrayList<String> like_status = new ArrayList<>();
    public static RequestQueue queue2;
    public static StringRequest sr2;
    public static List<Innerchannel> data_list_channel;
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
    static LoginPrefrences datastore ;
    static CategoryPrefrences cp ;
//////////////////   COver Flow entities


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Toast.makeText(getActivity() , "On Attach" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Toast.makeText(getActivity() , "On Create" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.slidingup, container, false);
        ButterKnife.bind(this, v);
        mContext = getActivity();
       // innerfragmentcontainer =(LinearLayout)v.findViewById(R.id.innerfragmentcontainer);
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        datastore = new DatastoreBuilder(PreferenceManager.getDefaultSharedPreferences(getActivity())).create(LoginPrefrences.class);

        cp = new DatastoreBuilder(PreferenceManager.getDefaultSharedPreferences(getActivity())).create(CategoryPrefrences.class);

        viewPager = (ViewPager) v.findViewById(R.id.view22);

        tabs = (com.astuetz.PagerSlidingTabStrip) v.findViewById(R.id.tabs);
        categoriesfragheader =(TextView)v.findViewById(R.id.catg);
        channelsfragheader =(TextView)v.findViewById(R.id.chnnl);

                homelist =(ListView)v.findViewById(R.id.homelist);
     //lv1 = (LinearLayout) v.findViewById(R.id.list22);
        llforheader = (LinearLayout)v.findViewById(R.id.layoutheader);
        viewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        tabs.setViewPager(viewPager);
        tabs.setShouldExpand(true);
        tabs.setClickable(false);
        tabs.setIndicatorHeight(2);
        tabs.setTextColor(Color.parseColor("#ffffff"));
        //tabs.setUnderlineColor(Color.parseColor("#5DD24D"));
        tabs.setIndicatorColor(Color.parseColor("#5DD24D"));
        tabs.setDividerColor(Color.parseColor("#282828"));

        if(cp.cat_id().get()==null){
            s="";
        }
        else {
            s=cp.cat_id().get();
        }
        loaddata();
        categoriesfragheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
                forvisiblecolor(categoriesfragheader);
                forinvisiblecolor(channelsfragheader);
            }
        });
        channelsfragheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
                forvisiblecolor(channelsfragheader);
                forinvisiblecolor(categoriesfragheader);
            }
        });
        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    forvisiblecolor(categoriesfragheader);
                    forinvisiblecolor(channelsfragheader);

                } else {
                    forvisiblecolor(channelsfragheader);
                    forinvisiblecolor(categoriesfragheader);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//////////////////////////   home list

        homelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(), VideoViewActivity.class));
                HomeConstants.VideoLink = content_itewm.get(i);
                getActivity().overridePendingTransition(R.anim.pull_in_right, R.anim.no_sliding);
            }
        });









////////////////////////////////////////// Seting inner fragment in sliding pannel
//        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.innerfragmentcontainer, new FragmentViewPager());
//        fragmentTransaction.addToBackStack("Fragment View pager");
//        fragmentTransaction.commit();







/////////////////////////////////////////// MATERIAL LEANBACK

       parsing(getActivity(),""+s);
        parsing1(getActivity(), ""+datastore.user_id().get());
//        materialLeanBack.setAdapter(new MaterialLeanBack.Adapter<TestViewHolder>() {
//            @Override
//            public int getLineCount() {
//                return 1;
//            }
//
//            @Override
//            public int getCellsCount(int line) {
//                return 10;
//            }
//
//            @Override
//            public TestViewHolder onCreateViewHolder(ViewGroup viewGroup, int line) {
//                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_test, viewGroup, false);
//
//
//                return new TestViewHolder(view);
//            }
//
//            @Override
//            public void onBindViewHolder(final TestViewHolder viewHolder, int i) {
//                viewHolder.textView.setText("test " + i);
//                int currentposition=i;
//                viewHolder.imageView.setTag(currentposition);
//                String url = "http://www.lorempixel.com/40" + viewHolder.row + "/40" + viewHolder.cell + "/";
//                Picasso.with(viewHolder.imageView.getContext()).load(url).into(viewHolder.imageView);
//
//                viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int btnvalue=Integer.parseInt(viewHolder.imageView.getTag().toString());
//                       Intent in = new Intent(getActivity(),ChannelActivity.class);
//                        startActivity(in);
//                    }
//                });
//            }
//
////            @Override
////            public String getTitleForRow(int row) {
////                return "Line " + row;
////            }
//
//        });

////////////////////////////////////////////////










/////////////////////////////////////////// CATEGORIES

//               mData.add(new CoverEntity(R.drawable.banner_a, "First Image"));
//        mData.add(new CoverEntity(R.drawable.banner_b, "Second one"));
//        mData.add(new CoverEntity(R.drawable.banner_c, " Third Image"));
//        mData.add(new CoverEntity(R.drawable.banner_d,  " Fourth one "));

        //mTitle = (TextSwitcher) v.findViewById(R.id.title);
        mCoverFlow = (FeatureCoverFlow)v.findViewById(R.id.coverflow);
        loadcategories();
//        mTitle.setFactory(new ViewSwitcher.ViewFactory() {
//            @Override
//            public View makeView() {
//                LayoutInflater inflater = LayoutInflater.from(getActivity());
//                TextView textView = (TextView) inflater.inflate(R.layout.item_title, null);
//                return textView;
//            }
//        });
//        Animation in = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_top);
//        Animation out = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_bottom);
//        mTitle.setInAnimation(in);
//        mTitle.setOutAnimation(out);
//
//       // mAdapter = new damroo.spinno.com.damroo.login_module.CoverFlowAdapter(getActivity(), vg, vg);
//        //mAdapter.setData(mData);
//
//        mCoverFlow.setAdapter(mAdapter);
//
//        mCoverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
//
//        mCoverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
//            @Override
//            public void onScrolledToPosition(int position) {
//                mTitle.setText("The text will set here");
//            }
//
//            @Override
//            public void onScrolling() {
//                mTitle.setText("");
//            }
//        });





//////////////////////////////////////////  SLIDING PANNEL WORKING
        sliding_layout.setPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View view, float v) {

            }

            @Override
            public void onPanelCollapsed(View view) {

//                sliding_layout.setPanelHeight(250);
//                category_carousal_container.animate().translationY(0).setDuration(100);
//                innerfragmentcontainer.animate().translationY(0).setDuration(100);
                llforheader.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in));
                llforheader.setVisibility(View.VISIBLE);

            }

            @Override
            public void onPanelExpanded(View view) {
                llforheader.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fade_out));
                llforheader.setVisibility(View.GONE);
//                category_carousal_container.animate().translationY(-category_carousal_container.getHeight()).setDuration(300);
//                innerfragmentcontainer.animate().translationY(-category_carousal_container.getHeight()).setDuration(300);
           }

            @Override
            public void onPanelAnchored(View view) {

            }

            @Override
            public void onPanelHidden(View view) {

            }
        });




        return v;
    }
    private void forvisiblecolor(TextView layoutcolor) {
        categoriesfragheader.setTextColor(Color.parseColor("#ecf0f1"));
        channelsfragheader.setTextColor(Color.parseColor("#ecf0f1"));
        layoutcolor.setTextColor(Color.parseColor("#5DD24D"));
    }
    private void forinvisiblecolor(TextView layoutgrey) {
        categoriesfragheader.setTextColor(Color.parseColor("#5DD24D"));
        channelsfragheader.setTextColor(Color.parseColor("#5DD24D"));
        layoutgrey.setTextColor(Color.parseColor("#ecf0f1"));
    }
    public static void loaddata() {
        if(cp.cat_id().get()==null){
            s1="";
        }
        else {
            s1=cp.cat_id().get();
        }
        String url =  API_URL.api_for_homescreen.concat(""+s1).concat(API_URL.api_for_homescreen2).concat(""+datastore.user_id().get());
        url=url.replace(" ","%20");
        Log.e("videos", "" + url);

        sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();


                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                ApiforhomescreenOutterSettergetter vsr = new ApiforhomescreenOutterSettergetter();
                vsr = gson.fromJson(response , ApiforhomescreenOutterSettergetter.class);


                content_itewm.clear();
                content_title.clear();
                content_thumbnail.clear();
                content_tags.clear();
                like_status.clear();
                 data1=   vsr.data;

                for(int i = 0 ; i<data1.size() ; i++){
                    content_id.add(data1.get(i).content_id);
                    content_itewm.add(data1.get(i).content_item);
                    content_title.add(data1.get(i).content_title);
                    content_thumbnail.add(data1.get(i).content_thumbnail);
                    content_tags.add(data1.get(i).content_tags);
                    like_status.add(data1.get(i).like_status);
                }
//                for (int i = 0; i < content_itewm.size(); i++) {
//
//
//                    lv1.addView(ordersview(R.layout.listview_item, i,content_title , content_thumbnail , content_tags, getActivity()));
//                }

//                listitems = new ArrayList<>();
//                listitems.add(new ListItem(R.drawable.banner_a));
//                listitems.add(new ListItem(R.drawable.banner_b));
//                listitems.add(new ListItem(R.drawable.banner_c));
//                homelist.setAdapter(new RVAdapter(listitems , getActivity()));
                homelist.setAdapter(new AdapterHomeList(mContext, content_title , content_thumbnail , content_tags,like_status,content_id));
                setListViewHeightBasedOnChildren(homelist);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(mContext , "Please check Your internet connection." , Toast.LENGTH_SHORT).show();

            }
        });
        sr.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr);
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Loading Videos");
        progressDialog.show();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // Toast.makeText(getActivity() , "On Activity Created" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
       // Toast.makeText(getActivity() , "On Start" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        FARGMANT_STATUS.homefragment = true ;
       // Toast.makeText(getActivity() , "On Resume" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        FARGMANT_STATUS.homefragment = false ;
      //  Toast.makeText(getActivity() , "On Pause" , Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onStop() {
        super.onStop();
     //   Toast.makeText(getActivity() , "On Stop" , Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    //    Toast.makeText(getActivity() , "On Destroy View" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // Toast.makeText(getActivity() , "On Destroy" , Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDetach() {
        super.onDetach();
       // Toast.makeText(getActivity() , "On Detach" , Toast.LENGTH_SHORT).show();
    }

    public static FRAGMENT_HOME newInstance(String text) {

        FRAGMENT_HOME f = new FRAGMENT_HOME();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }


    /****Method for Setting the Height of the ListView dynamically.
     **** Hack to fix the issue of not showing all the items of the ListView
     **** when placed inside a ScrollView  ****/
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, AbsListView.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter {
        String messages;
        private final String[] TITLES = {"",""};
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            //Fragment frag = null;
            switch (position) {

                case 0:

                    return FragmentInnerCategories.newInstance("Categories");
                case 1:

                    return FragmentInnerChannels.newInstance("Channels");



                default:
                    return null;

            }
        }



    }
    private void loadcategories() {
        String url =  API_URL.CATEGORIES ;
        url=url.replace(" ","%20");
        Log.d("Registration url", "" + url);
        queue111 = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        sr1 = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
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
                FragmentInnerCategories.grid.setAdapter(new AdapterCategoryGrid(getActivity(),cat_image));
                for(int i=0;i<datacat.size();i++){
                    // Toast.makeText(CategorySelectionActivity.this, ""+cat_image.get(i), Toast.LENGTH_SHORT).show();
                    mData.add(new CoverEntity(cat_image.get(i),""+cat_name.get(i)));

                }

//                mTitle.setFactory(new ViewSwitcher.ViewFactory() {
//                    @Override
//                    public View makeView() {
//                        LayoutInflater inflater = LayoutInflater.from(getActivity());
//                        TextView textView = (TextView) inflater.inflate(R.layout.item_title, null);
//                        return textView;
//                    }
//                });
//                Animation in = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_top);
//                Animation out = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_bottom);
//                mTitle.setInAnimation(in);
//                mTitle.setOutAnimation(out);

                mAdapter = new CoverFlowAdapter(mContext);
        mAdapter.setData(mData);

                mCoverFlow.setAdapter(mAdapter);
                mCoverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                Toast.makeText(CategorySelectionActivity.this,
//                        getResources().getString(mData.get(position).titleResId),
//                       Toast.LENGTH_SHORT).show();
//                Toast.makeText(CategorySelectionActivity.this , "Position " +position, Toast.LENGTH_SHORT ).show();



                    }
                });
                //mTitle.setText(""+cat_name.get(0) );
                mCoverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
                    @Override
                    public void onScrolledToPosition(int position) {
                       // mTitle.setText(""+cat_name.get(position));
                    }

                    @Override
                    public void onScrolling() {
                       // mTitle.setText("");
                    }
                });

//
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  showviews();
                Toast.makeText(getActivity() , "Please check Your internet connection." , Toast.LENGTH_SHORT).show();
            }
        });
        sr1.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue111.add(sr1);
        //hideviews();




    }





        public  void parsing(final Context activity,String s1){



            String locationurl2 = API_URL.recommended_channel.concat(s1);
            locationurl2=locationurl2.replace(" ","%20");

            Log.e("url", "" + locationurl2);
            queue2 = VolleySingleton.getInstance(activity).getRequestQueue();
            sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {



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
                        settergetterforrecommendedouter received2 = new settergetterforrecommendedouter();
                        received2 = gson.fromJson(response, settergetterforrecommendedouter.class);


                        data_list_channel = received2.data;

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
                        materialLeanBack.setAdapter(new MaterialLeanBack.Adapter<TestViewHolder>() {
                            @Override
                            public int getLineCount() {
                                return 1;
                            }

                            @Override
                            public int getCellsCount(int line) {
                                return data_list_channel.size();
                            }

                            @Override
                            public TestViewHolder onCreateViewHolder(ViewGroup viewGroup, int line) {
                                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_test, viewGroup, false);


                                return new TestViewHolder(view);
                            }

                            @Override
                            public void onBindViewHolder(final TestViewHolder viewHolder, int i) {
                                viewHolder.textView.setText(channel_name.get(i));
                                int currentposition=i;
                                viewHolder.imageView.setTag(currentposition);
                               // String url = "http://www.lorempixel.com/40" + viewHolder.row + "/40" + viewHolder.cell + "/";
                                String url = "http://keshavgoyal.com/damroo/uploads/user_picture/"+user_image.get(i);
                                Picasso.with(viewHolder.imageView.getContext()).load(url).into(viewHolder.imageView);

                                viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        int btnvalue=Integer.parseInt(viewHolder.imageView.getTag().toString());
                                        Intent in = new Intent(getActivity(),ChannelActivity.class);
                                        startActivity(in);
                                    }
                                });
                            }

//            @Override
//            public String getTitleForRow(int row) {
//                return "Line " + row;
//            }

                        });

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
            queue2.add(sr2);




        }


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
                    FragmentInnerChannels.list.setAdapter(new AdapterMyChannels(activity, channel_name1, user_image1));
                   // MyChannel_Fragment.list.setAdapter(new AdapterMyChannels(activity, FRAGMENT_HOME.channel_name1, FRAGMENT_HOME.user_image1));

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
