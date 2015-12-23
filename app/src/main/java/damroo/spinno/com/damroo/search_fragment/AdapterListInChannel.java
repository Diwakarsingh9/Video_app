package damroo.spinno.com.damroo.search_fragment;

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
public class AdapterListInChannel extends BaseAdapter {

    Context con ;
    ArrayList<String> channelnames = new ArrayList<>() ;
    ArrayList<String> channel_images = new ArrayList<>() ;
    LayoutInflater inflater ;


    public AdapterListInChannel (Context con ,ArrayList<String> channelnames ,ArrayList<String> channel_images ){
          this.channel_images.clear();
        this.channelnames.clear();
           this.con = con ;
           this.channelnames = channelnames;
           this.channel_images =channel_images ;
        inflater = (LayoutInflater) con.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    }
    @Override
    public int getCount() {
        return channelnames.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.item_searchinchannels, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.channelname.setText(channelnames.get(position));
      Picasso.with(con).load("http://keshavgoyal.com/damroo/uploads/user_picture/"+channel_images.get(position)).error(R.drawable.male_icon).placeholder(R.drawable.male_icon).into(holder.channelimage);
       // holder.channelimage.setImageResource(channel_images.get(position));
        return view;
    }

    static class ViewHolder {

        @Bind(R.id.channelimage) ImageView channelimage;
        @Bind(R.id.channelname) TextView channelname;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
