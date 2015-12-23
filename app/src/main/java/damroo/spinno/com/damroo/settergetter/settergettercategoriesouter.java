package damroo.spinno.com.damroo.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 11/30/2015.
 */
public class settergettercategoriesouter {

    @SerializedName("result")
    public String result ;


    @SerializedName("msg")
    public List<Innerdatacat> msg = new ArrayList<Innerdatacat>();
}
