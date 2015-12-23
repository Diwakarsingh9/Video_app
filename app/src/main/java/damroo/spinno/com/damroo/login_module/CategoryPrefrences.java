package damroo.spinno.com.damroo.login_module;

import com.lacronicus.easydatastorelib.Preference;
import com.lacronicus.easydatastorelib.StringEntry;

/**
 * Created by saifi45 on 12/2/2015.
 */
public interface CategoryPrefrences {

    @Preference("cat_id")
    StringEntry cat_id();
}
