package damroo.spinno.com.damroo.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 12/2/2015.
 */
public class settergettersubscribedoutter {
    @SerializedName("result")
    public String result ;


    @SerializedName("data")
    public List<Innersubscribed> data = new ArrayList<Innersubscribed>();
}
