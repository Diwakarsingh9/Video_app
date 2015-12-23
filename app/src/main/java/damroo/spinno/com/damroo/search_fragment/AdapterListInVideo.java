package damroo.spinno.com.damroo.search_fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.R;

/**
 * Created by spinnosolutions on 9/15/15.
 */
public class AdapterListInVideo extends BaseAdapter {

    Context con ;
    ArrayList<String> videoname = new ArrayList<>();
    ArrayList<String> artistname = new ArrayList<>() ;
    LayoutInflater inflater ;

    public AdapterListInVideo( Context con ,ArrayList<String> videoname , ArrayList<String> artistname ){
        this.videoname.clear();
        this.artistname.clear();
        this.con = con  ;
       this.videoname = videoname;
       this.artistname  = artistname ;
        inflater = (LayoutInflater) con.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    }

    @Override
    public int getCount() {
        return videoname.size();
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
            view = inflater.inflate(R.layout.item_searchinvideo, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.videoname.setText(videoname.get(position)+" - ");
        holder.artistname.setText(artistname.get(position));
        return view;
    }

    static class ViewHolder {

        @Bind(R.id.videoname) TextView videoname;
        @Bind(R.id.artistname) TextView artistname;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
