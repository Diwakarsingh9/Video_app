package damroo.spinno.com.damroo.profileModule;

import com.google.gson.annotations.SerializedName;

/**
 * Created by spinnosolutions on 11/6/15.
 */
public class USER_DATA_SUCCESS_RESPONSE {
    @SerializedName("msg")
    public String result ;


    @SerializedName("data")
    public USER_DATA_RESPONSE  udsr = new USER_DATA_RESPONSE();
}
