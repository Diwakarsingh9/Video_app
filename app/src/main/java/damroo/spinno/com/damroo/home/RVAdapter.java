package damroo.spinno.com.damroo.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import checkers.Toaster;
import damroo.spinno.com.damroo.R;

/**
 * Created by spinnosolutions on 11/2/15.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{



    List<ListItem> items;
    Context  con ;

    RVAdapter(List<ListItem> items  , Context con ){
        this.items = items;
        this.con = con ;
        Toaster.generatemessage(con, "Size of array list  " + items.size());
    }



    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        holder.personPhoto.setImageResource(items.get(position).photoId);
        Toaster.generatemessage(con, ""+items.get(position).photoId);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        ImageView personPhoto;

        PersonViewHolder(View itemView) {
            super(itemView);
            personPhoto = (ImageView)itemView.findViewById(R.id.bannerimage);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
