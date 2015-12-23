package damroo.spinno.com.damroo.login_module;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.joooonho.SelectableRoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import damroo.spinno.com.damroo.API_URL;
import damroo.spinno.com.damroo.R;

/**
 * Created by spinnosolutions on 10/23/15.
 */
public class CoverFlowAdapter extends BaseAdapter {
        ArrayList<String> vg1 = new ArrayList<>();
    ArrayList<String> vg2= new ArrayList<>();
    private ArrayList<CoverEntity> mData = new ArrayList<>();
    private Context mContext;

//    public CoverFlowAdapter(Context context, ArrayList<String> vg, ArrayList<String> strings) {
//        mContext = context;
//        vg1=vg;
//        vg2=strings;
//    }
    public CoverFlowAdapter(Context context) {
        mContext = context;

    }
    public void setData(ArrayList<CoverEntity> data) {
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int pos) {
        return mData.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_coverflow, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) rowView.findViewById(R.id.label);
            viewHolder.image = (SelectableRoundedImageView) rowView
                    .findViewById(R.id.image);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        //Toast.makeText(mContext, "" +"http://keshavgoyal.com/damroo/uploads/cat_icon/" + mData.get(position).imageResId , Toast.LENGTH_SHORT).show();

        Picasso.with(mContext).load("http://keshavgoyal.com/damroo/uploads/cat_icon/" + mData.get(position).imageResId).into(holder.image);
      // holder.image.setImageResource(mData.get(position).imageResId);
        holder.text.setText(mData.get(position).titleResId);

        return rowView;
    }


    static class ViewHolder {
        public TextView text;
        public SelectableRoundedImageView image;
    }
}
