package damroo.spinno.com.damroo.login_module;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by spinnosolutions on 10/23/15.
 */
public class ViewAddredToTray {



    Context con ;


    public ViewAddredToTray (Context con ){
   this.con =  con ;
    }



    public View foraddigview( int layout_name , final String image , final String tittle ) {

        LayoutInflater layoutInflater = (LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addView = layoutInflater.inflate(layout_name, null);


        return  addView ;
    }
}