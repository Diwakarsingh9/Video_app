package damroo.spinno.com.damroo.settergetter;

import com.google.gson.annotations.SerializedName;

/**
 * Created by saifi45 on 11/25/2015.
 */
public class KeywordOutersettergetter {

    @SerializedName("result")
    public String result;

    @SerializedName("data")
    public KeywordInnersettergetter keywordInnersettergetter = new KeywordInnersettergetter();
}
