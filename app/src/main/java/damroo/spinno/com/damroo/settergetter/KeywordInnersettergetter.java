package damroo.spinno.com.damroo.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 11/25/2015.
 */
public class KeywordInnersettergetter {
    @SerializedName("channel")
    public List<Innerchannel> innerchannels = new ArrayList<Innerchannel>();


    @SerializedName("video")
    public List<Innervideo> innervideos = new ArrayList<Innervideo>();
}
