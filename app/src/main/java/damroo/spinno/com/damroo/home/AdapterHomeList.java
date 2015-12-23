package damroo.spinno.com.damroo.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import damroo.spinno.com.damroo.API_URL;
import damroo.spinno.com.damroo.R;
import damroo.spinno.com.damroo.parsingfiles.parsingforlikeunlikevideos;
import imageLoading.ImageLoader;

public class AdapterHomeList extends BaseSwipeAdapter {

    private Context mContext;
    ArrayList<String> cont_id = new ArrayList<>();
    ArrayList<String> content_title = new ArrayList<>();
    ArrayList<String> content_thumbnail = new ArrayList<>();
    ArrayList<String> content_tags = new ArrayList<>();
    ArrayList<String> likestatus = new ArrayList<>();
    ImageLoader imagerloader ;


    public AdapterHomeList(Context mContext, ArrayList<String> content_title, ArrayList<String> content_thumbnail, ArrayList<String> content_tags, ArrayList<String> like_status, ArrayList<String> content_id) {
        this.mContext = mContext;
        this.content_title = content_title ;
        this.content_thumbnail = content_thumbnail ;
        this.content_tags = content_tags ;
        this.likestatus = like_status ;
        this.cont_id = content_id ;
        imagerloader = new ImageLoader(mContext.getApplicationContext());
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.listview_item, null);

        SwipeLayout swipeLayout = (SwipeLayout)v.findViewById(getSwipeLayoutResourceId(position));
                    swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.heart_img_btn));
            }
        });

        swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
            }
        });

//        v.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext, "click delete", Toast.LENGTH_SHORT).show();
//            }
//        });

        return v;
    }

    @Override
    public void fillValues(final int position, View convertView) {
        ImageView bannerimage = (ImageView)convertView.findViewById(R.id.bannerimage);
        TextView title = (TextView) convertView.findViewById(R.id.titles);
        TextView tags = (TextView) convertView.findViewById(R.id.tags);
        final ImageView heartimage = (ImageView)convertView.findViewById(R.id.heart_img_btn);
       heartimage.setTag(position);
        heartimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) heartimage.getTag();
                if(likestatus.get(pos).equals("1")){
                    parsingforlikeunlikevideos.parsing(mContext,cont_id.get(pos),"0" );
                    //likestatus.add(pos,"0");

                }
                else{
                    parsingforlikeunlikevideos.parsing(mContext,cont_id.get(pos),"1" );
                    //likestatus.add(pos,"1");

                }

            }
        });
     //   imagerloader.DisplayImage(API_URL.THUMBNAIL_BASE_URI + content_thumbnail.get(position), bannerimage);
        Picasso.with(mContext).load(API_URL.THUMBNAIL_BASE_URI + content_thumbnail.get(position)).into(bannerimage);
        title.setText(""+content_title.get(position));
        tags.setText(""+content_tags.get(position));

    }

    @Override
    public int getCount() {
        return content_title.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
