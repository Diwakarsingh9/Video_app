package damroo.spinno.com.damroo.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.R;
import damroo.spinno.com.damroo.profileModule.AdapterMyChannels;
import damroo.spinno.com.damroo.profileModule.MyChannel_Fragment;

/**
 * Created by spinnosolutions on 10/28/15.
 */
public class FragmentInnerChannels extends Fragment {

     public static ListView list ;
    public static LinearLayout llforlist;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inner_channels, container, false);
        ButterKnife.bind(this, v);
        list= (ListView) v.findViewById(R.id.list22);

//        for (int i = 0; i < 12; i++) {
//
//
//            llforlist.addView(ordersview(R.layout.item_mychannels, i,getActivity()));
//        }
//        ViewTreeObserver vto = llforlist.getViewTreeObserver();
//        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                llforlist.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                int width = llforlist.getMeasuredWidth();
//                int height = llforlist.getMeasuredHeight();
//                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width, height);
//                FRAGMENT_HOME.innerfragmentcontainer.setLayoutParams(parms);
//
//            }
//        });



        // arraylist list is in which all data is kept





        setListViewHeightBasedOnChildren(list);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(getActivity(), ChannelActivity.class);
                startActivity(in);
            }
        });

        return v;
    }

    public static FragmentInnerChannels newInstance(String text) {

        FragmentInnerChannels f = new FragmentInnerChannels();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }

    public static View ordersview(int layout_name, int i, FragmentActivity activity) {

        LayoutInflater layoutInflater =
                (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addView = layoutInflater.inflate(layout_name, null);

        final  TextView title = (TextView)addView.findViewById(R.id.channelname);
        final ImageView img = (ImageView)addView.findViewById(R.id.image);
        addView.setTag(i);
        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) addView.getTag();


            }
        });
        title.setText("" + MyChannel_Fragment.channelnames[i]);
       img.setImageResource(MyChannel_Fragment.Channe_img[i]);
        //il.DisplayImage("http://keshavgoyal.com/realtysingh/" + img.get(i), viewHolder.img);
//        Picasso.with(ctc)
//                .load("http://keshavgoyal.com/realtysingh/" + moduleimg.get(i))
//                .placeholder(R.drawable.reall2) // optional
//                .error(R.drawable.reaal2222l)         // optional
//                .into(img);

        return addView ;
    }
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

    @Override
    public void onResume() {
        super.onResume();
        list.setAdapter(new AdapterMyChannels(getActivity(), FRAGMENT_HOME.channel_name1, FRAGMENT_HOME.user_image1));
    }
}
