package checkers;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by spinnosolutions on 10/28/15.
 */
public class Toaster {
    public static void generatemessage (Context con , String s){
        Toast.makeText(con, "" + s, Toast.LENGTH_SHORT).show();

    }
}
