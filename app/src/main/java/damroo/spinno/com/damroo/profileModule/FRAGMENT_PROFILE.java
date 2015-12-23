package damroo.spinno.com.damroo.profileModule;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
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

import java.io.ByteArrayOutputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import checkers.Toaster;
import damroo.spinno.com.damroo.API_URL;
import damroo.spinno.com.damroo.FARGMANT_STATUS;
import damroo.spinno.com.damroo.R;
import damroo.spinno.com.damroo.VolleySingleton;
import damroo.spinno.com.damroo.home.FRAGMENT_HOME;
import damroo.spinno.com.damroo.login_module.LoginPrefrences;
import damroo.spinno.com.damroo.login_module.User_info;
import tabindicatorview.PagerSlidingTabStrip;

/**
 * Created by spinnosolutions on 9/15/15.
 */
public class FRAGMENT_PROFILE  extends Fragment {

    @Bind(R.id.viewPager)ViewPager viewPager;
    @Bind(R.id.tabs) PagerSlidingTabStrip tabs;
    @Bind(R.id.profile_img)ImageView profile_img ;
    @Bind(R.id.ediprofile)Button ediprofile ;
    @Bind(R.id.takephoto)ImageView takephoto ;
    @Bind(R.id.subscribers)TextView subscriber ;
    @Bind(R.id.channbelname)TextView channbelname ;
    @Bind(R.id.websitename)TextView websitename ;
    @Bind(R.id.tags)TextView tags ;
    @Bind(R.id.mychannelscount)TextView mychnlcnt ;
  public  static TextView myvideocnt ;
    public  static LinearLayout llforid, llforname,llfortext;
    private static int RESULT_LOAD_IMG = 1;
    private static final int CAMERA_REQUEST = 1888;

    String imgDecodableString;


    StringRequest sr ;
    RequestQueue queue ;
    ProgressDialog progressDialog ;

    LoginPrefrences datastore ;
    User_info userinfo;


    public static Uri selectedImage ;

    public static Uri tempUri ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, v);
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        datastore = new DatastoreBuilder(PreferenceManager.getDefaultSharedPreferences(getActivity())).create(LoginPrefrences.class);
        userinfo = new DatastoreBuilder(PreferenceManager.getDefaultSharedPreferences(getActivity())).create(User_info.class);

        myvideocnt = (TextView)v.findViewById(R.id.myvideoscount);
        Log.e("USER ID ", "" + datastore.user_id().get());
        profile_img.setImageResource(R.drawable.camera_girl);
        llforid = (LinearLayout)v.findViewById(R.id.linearlayoutforid);
        llforname = (LinearLayout)v.findViewById(R.id.linearlayoutfrname);
        llfortext = (LinearLayout)v.findViewById(R.id.llfortext);
        viewPager.setAdapter(new AdapterMyProfilePager(getFragmentManager()));
        tabs.setViewPager(viewPager);
        tabs.setShouldExpand(true);
        tabs.setIndicatorHeight(2);
        tabs.setTextColor(Color.WHITE);
        tabs.setPadding(0, 0, 0, 10);
        tabs.setDividerColor(Color.BLACK);
        tabs.setIndicatorColor(Color.parseColor("#6BBA4C"));
        mychnlcnt.setText(""+ FRAGMENT_HOME.channel_name1.size());



        ediprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity() , EditProfileActivity.class));
            }
        });


        takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String [] array = {"Take Photo" , "Photo Library" , "Cancel"};

                new MaterialDialog.Builder(getActivity())
                        .items(array)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                if(text.equals("Photo Library")){
                                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
                                }else if(text.equals("Take Photo")){
                                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                                }
                            }
                        })
                        .show();
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        FARGMANT_STATUS.profile_fragment  = true ;
        loadData();
        super.onResume();
    }



    @Override
    public void onDestroyView() {
        FARGMANT_STATUS.profile_fragment = false ;
        super.onDestroyView();
    }

    public static FRAGMENT_PROFILE newInstance(String text) {
        FRAGMENT_PROFILE f = new FRAGMENT_PROFILE();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            //////////////  used for camera
            if (requestCode == CAMERA_REQUEST ) {

                Bitmap photo = (Bitmap) data.getExtras().get("data");
                 tempUri = getImageUri(getActivity().getApplicationContext(), photo);
               // File finalFile = new File(getRealPathFromURI(tempUri));
                Intent in = new Intent(getActivity() , PhotoCropActivity.class);
                in.putExtra("image" , getRealPathFromURI(tempUri));
                startActivity(in);

            }


            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && null != data) {

                 selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();


                Intent in = new Intent(getActivity() , PhotoCropActivity.class);

                         in.putExtra("image" , imgDecodableString);
                     startActivity(in);


//                // Set the Image in ImageView after decoding the String
//                profile_img.setImageBitmap(BitmapFactory
//                        .decodeFile(imgDecodableString));

            } else {
                Toast.makeText(getActivity(), "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }




    public Uri getImageUri(Context inContext, Bitmap inImage) {
        Bitmap photo = (Bitmap)inImage;
        photo = Bitmap.createScaledBitmap(photo, 500, 500, false);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 00, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), photo, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().getContentResolver().query(uri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(filePathColumn[0]);
        return cursor.getString(idx);
    }





////////////////////////////////////////////////  parsing Code //////////////////////////
    private void loadData() {
        String url =  API_URL.VIEWUSER+datastore.user_id().get();
        url=url.replace(" ","%20");
        Log.e("Registration url", "" + url);

        sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    CHECK_PROFILE_RESPONSE cpr = new CHECK_PROFILE_RESPONSE();
                    cpr = gson.fromJson(response, CHECK_PROFILE_RESPONSE.class);

                   // Toaster.generatemessage(getActivity(), "user_info" + cpr.result);
                    if (cpr.result.equals("0")) {

                        Toaster.generatemessage(getActivity(), "No Result Found");
                        llforname.setVisibility(View.GONE);
                        llforid.setVisibility(View.GONE);
                        llfortext.setVisibility(View.VISIBLE);

                    } else if (cpr.result.equals("1")) {
                        llforname.setVisibility(View.VISIBLE);
                        llforid.setVisibility(View.VISIBLE);
                        llfortext.setVisibility(View.GONE);
                        USER_DATA_SUCCESS_RESPONSE udsr = new USER_DATA_SUCCESS_RESPONSE();
                        udsr = gson.fromJson(response, USER_DATA_SUCCESS_RESPONSE.class);
                        Log.e("userinfo",""+udsr.udsr.firstname);
                        userinfo.info_id().put("" + udsr.udsr.user_id);
                        userinfo.user_id().put(""+udsr.udsr.user_id);
                        userinfo.public_name().put(""+udsr.udsr.firstname +" "+udsr.udsr.lastname);
                        userinfo.website().put(""+udsr.udsr.website);
                        userinfo.public_info().put(""+udsr.udsr.description);
                        userinfo.personal_name().put(""+udsr.udsr.user_name);
                        userinfo.email().put(""+udsr.udsr.user_mail);
                        userinfo.phone_number().put(""+udsr.udsr.phone);
                        userinfo.user_image().put(""+udsr.udsr.user_image);
                        channbelname.setText("" + udsr.udsr.firstname +" "+udsr.udsr.lastname);
                        websitename.setText("" + udsr.udsr.website);
                        tags.setText("" + udsr.udsr.description);
                        subscriber.setText(""+udsr.udsr.subscriber);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Please check Your internet connection." , Toast.LENGTH_SHORT).show();
            }
        });
        sr.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }


}
