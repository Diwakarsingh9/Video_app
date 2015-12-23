package damroo.spinno.com.damroo.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 12/1/2015.
 */
public class settergetterforrecommendedouter {
    @SerializedName("result")
    public String result ;


    @SerializedName("data")
    public List<Innerchannel> data = new ArrayList<Innerchannel>();
}
