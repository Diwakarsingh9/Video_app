package damroo.spinno.com.damroo.profileModule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.R;

/**
 * Created by spinnosolutions on 9/15/15.
 */
public class AdapterMyVideoList extends BaseAdapter {
    Context con ;
    ArrayList<String> images = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> descp = new ArrayList<>();
    ArrayList<String> duration = new ArrayList<>();
    LayoutInflater inflater ;



    public AdapterMyVideoList(Context activity, ArrayList<String> content_thumbnail, ArrayList<String> content_title, ArrayList<String> content_desc, ArrayList<String> content_time_dur){
      this.con = activity ;
        this.images = content_thumbnail;
        this.title = content_title;
        this.descp = content_desc;
        this.duration = content_time_dur;
        inflater = (LayoutInflater) con.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    }
    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class Holder {

         ImageView bannerimage;
  TextView name;
       TextView descp;
         TextView duration;




    }
    @Override public View getView(final int position, View view, ViewGroup parent) {
       final Holder holder;

            view = inflater.inflate(R.layout.item_myvideos, parent, false);

            holder = new Holder();
            view.setTag(holder);
            holder.bannerimage = (ImageView)view.findViewById(R.id.bannerimage);
            holder.name = (TextView)view.findViewById(R.id.title);
            holder.descp = (TextView)view.findViewById(R.id.descp);
            holder.duration = (TextView)view.findViewById(R.id.time);
            holder.name.setText("" + title.get(position));
            holder.descp.setText(""+descp.get(position));
            holder.duration.setText(""+duration.get(position));
            Picasso.with(con).load("http://keshavgoyal.com/damroo/uploads/thumbnail/"+images.get(position)).
                    error(R.drawable.bird).
                    placeholder(R.drawable.bird).into(holder.bannerimage);




        return view;
    }


}
