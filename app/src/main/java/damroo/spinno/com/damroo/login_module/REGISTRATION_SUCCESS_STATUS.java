package damroo.spinno.com.damroo.login_module;

import com.google.gson.annotations.SerializedName;

/**
 * Created by spinnosolutions on 10/23/15.
 */
public class REGISTRATION_SUCCESS_STATUS {

    @SerializedName("result")
    public String result ;


    @SerializedName("msg")
    public String msg ;


    @SerializedName("Details")
    public USER_DETAILS_POJO udp = new USER_DETAILS_POJO() ;


}
