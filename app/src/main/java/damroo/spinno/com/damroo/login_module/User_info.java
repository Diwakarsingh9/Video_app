package damroo.spinno.com.damroo.login_module;

import com.lacronicus.easydatastorelib.Preference;
import com.lacronicus.easydatastorelib.StringEntry;

/**
 * Created by saifi45 on 12/2/2015.
 */
public interface User_info {
    @Preference("info_id")
    StringEntry info_id();

    @Preference("user_id")
    StringEntry user_id();

    @Preference("public_name")
    StringEntry public_name();

    @Preference("website")
    StringEntry website();

    @Preference("public_info")
    StringEntry public_info();

    @Preference("personal_name")
    StringEntry personal_name();

    @Preference("email")
    StringEntry email();

    @Preference("phone_number")
    StringEntry phone_number();

    @Preference("user_image")
    StringEntry user_image();
}
