package damroo.spinno.com.damroo.login_module;

import com.lacronicus.easydatastorelib.Preference;
import com.lacronicus.easydatastorelib.StringEntry;

/**
 * Created by spinnosolutions on 10/23/15.
 */
public interface LoginPrefrences {

    @Preference("user_id")
    StringEntry user_id();

    @Preference("user_name")
    StringEntry user_name();

    @Preference("user_mail")
    StringEntry user_mail();

    @Preference("user_pass")
    StringEntry user_pass();

    @Preference("user_role")
    StringEntry user_role();

    @Preference("category")
    StringEntry category();

    @Preference("user_status")
    StringEntry user_status();

    @Preference("phone")
    StringEntry phone();

    @Preference("firstname")
    StringEntry firstname();

    @Preference("lastname")
    StringEntry lastname();

    @Preference("dob")
    StringEntry dob();

    @Preference("gender")
    StringEntry gender();

    @Preference("channel_name")
    StringEntry channelname();

    @Preference("website")
    StringEntry website();

    @Preference("description")
    StringEntry description();


    @Preference("userimage")
    StringEntry userimage();

}
