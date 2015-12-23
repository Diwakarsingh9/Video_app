package damroo.spinno.com.damroo.login_module;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import damroo.spinno.com.damroo.home.VIDEO_POJO;

/**
 * Created by spinnosolutions on 10/26/15.
 */
public class VIDEO_SUCCEUCESS_RESULT {

    @SerializedName("result")
    public String result ;


    @SerializedName("Message")
    public List<VIDEO_POJO>  Message = new ArrayList<>();
}
