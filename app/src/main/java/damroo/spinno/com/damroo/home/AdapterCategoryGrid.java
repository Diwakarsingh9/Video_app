package damroo.spinno.com.damroo.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import damroo.spinno.com.damroo.R;

/**
 * Created by spinnosolutions on 10/28/15.
 */
public class AdapterCategoryGrid  extends BaseAdapter {


    Context con ;
    ArrayList<String> cat_name = new ArrayList<>();
    LayoutInflater inflater ;



    public AdapterCategoryGrid(Context con, ArrayList<String> cat_name){
        this.con = con ;
        this.cat_name=cat_name;
        inflater = (LayoutInflater) con.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    }

    @Override
    public int getCount() {
        return cat_name.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.item_category_grid, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
            ImageView img = (ImageView)view.findViewById(R.id.categoryimage);
            if(cat_name.size()==0){}
            else {
                Picasso.with(con).load("http://keshavgoyal.com/damroo/uploads/cat_icon/" + cat_name.get(position)).into(img);
            }
        }

        return view;
    }


    static class ViewHolder {

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);

        }
    }
}