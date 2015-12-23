package damroo.spinno.com.damroo.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 11/27/2015.
 */
public class ApiforhomescreenOutterSettergetter {
    @SerializedName("result")
    public String result ;


    @SerializedName("data")
    public List<Innerdataapiforhome> data = new ArrayList<Innerdataapiforhome>();
}
