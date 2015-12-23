package damroo.spinno.com.damroo.search_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.R;
import damroo.spinno.com.damroo.parsingfiles.parsingforkeywordsearch;

/**
 * Created by spinnosolutions on 9/15/15.
 */
public class FRAGMENT_SEARCH extends Fragment {

  public static ListView list_in_channel ;
    public static  ListView list_in_videos ;
    public static ProgressBar pb;
    public static LinearLayout ll;
    public static TextView inchannels,invideos;
    public static View greenline;
    @Bind(R.id.searchkeyword)EditText searchkeyword ;

    String [] channelnames = {"Honey singh FAN Club" , "Honey Singh Rap Club" , "Honey Star" , "Honey Best" , "Honey Stage Performances"};
    int [] channel_images = {R.drawable.honey_a,R.drawable.honey_b,R.drawable.honey_c,R.drawable.honey_d,R.drawable.honey_d,};

    String [] videoname = {"Bring Me back" ,"International Villager" , "Angreji beat"};
    String [] artistname = {"Honey Singh" , "Honey Singh" , "HONEY SINGH "};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, v);
        list_in_channel = (ListView) v.findViewById(R.id.list_in_channel);
        list_in_videos = (ListView) v.findViewById(R.id.list_in_videos);
        pb = (ProgressBar) v.findViewById(R.id.progressBar);
//        inchannels = (TextView) v.findViewById(R.id.inchannel);
//        invideos = (TextView) v.findViewById(R.id.invideos);
//        greenline = (View)v.findViewById(R.id.greenline);
        ll = (LinearLayout) v.findViewById(R.id.layoutforsearch);
        searchkeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               //parsingfor
                parsingforkeywordsearch.parsing(getActivity(),""+s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
//        list_in_channel.setAdapter(new AdapterListInChannel(getActivity(), channelnames, channel_images));
//        setListViewHeightBasedOnChildren(list_in_channel);
//
//        list_in_videos.setAdapter(new AdapterListInVideo(getActivity(), videoname, artistname));
//        setListViewHeightBasedOnChildren(list_in_videos);

        return v;
    }

    public static FRAGMENT_SEARCH newInstance(String text) {

        FRAGMENT_SEARCH f = new FRAGMENT_SEARCH();
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




}
