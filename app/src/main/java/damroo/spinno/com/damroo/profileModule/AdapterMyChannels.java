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
public class AdapterMyChannels  extends BaseAdapter {

    Context con ;
    ArrayList<String> cahnels = new ArrayList<>();
    ArrayList<String> Channe_img = new ArrayList<>();
    LayoutInflater inflater ;

    public AdapterMyChannels( Context con , ArrayList<String> cahnels  , ArrayList<String> Channe_img){
        this.con = con ;
        this.cahnels = cahnels;
        this.Channe_img = Channe_img ;
        inflater = (LayoutInflater) con.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    }
    @Override
    public int getCount() {
        return cahnels.size();
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
            view = inflater.inflate(R.layout.item_mychannels, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);

        }

        holder.channelname.setText(cahnels.get(position));
        //holder.image.setImageResource(Channe_img[position]);
        if(Channe_img.size()==0){}
        else {
            Picasso.with(con).load("http://keshavgoyal.com/damroo/uploads/user_picture/" + Channe_img.get(position)).into(holder.image);
        }
        return view;
    }

    static class ViewHolder {

        @Bind(R.id.channelname) TextView channelname;
        @Bind(R.id.image) ImageView image;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
