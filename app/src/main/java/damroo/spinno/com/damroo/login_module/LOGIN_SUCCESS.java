package damroo.spinno.com.damroo.login_module;

import com.google.gson.annotations.SerializedName;

/**
 * Created by spinnosolutions on 10/24/15.
 */
public class LOGIN_SUCCESS {

    @SerializedName("result")
    public String result ;


    @SerializedName("msg")
    public String msg ;


    @SerializedName("details")
    public USER_DETAILS_POJO details = new USER_DETAILS_POJO() ;
}
