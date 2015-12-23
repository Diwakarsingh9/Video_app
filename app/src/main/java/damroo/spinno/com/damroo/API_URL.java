package damroo.spinno.com.damroo;

/**
 * Created by spinnosolutions on 10/23/15.
 */
public class API_URL {
    public static String REGISTREATION_USER = "http://keshavgoyal.com/damroo/api/sign_up.php?user_name=" ;   //&email=   &password=   &user_role=  &phone=  &firstname=    &lastname=    &dob=    &gender=";
    public static String LOGIN = "http://keshavgoyal.com/damroo/api/login.php?email=";  // &password=
    public static String CATEGORIES = "http://keshavgoyal.com/damroo/api/category.php";
    public static String ALL_VIDEOS = "http://keshavgoyal.com/damroo/api/view_video.php";


    public static String THUMBNAIL_BASE_URI = "http://keshavgoyal.com/damroo/uploads/thumbnail/";
    public static String VIDEO_BASE_URI = "http://keshavgoyal.com/damroo/uploads/";



///////////////  PROFILE MODULE
    public static String EDITPROFILE = "http://keshavgoyal.com/damroo/api/edit_user_info.php?user_id=";  // &public_name=   &website=   &public_info=   &personal_name=   &email=   &phone_number=

    public static String VIEWUSER= "http://keshavgoyal.com/damroo/api/view_user_info.php?user_id=";

    public static String UPLOAD_PROFILE_PIC = "http://keshavgoyal.com/damroo/api/edit_photo.php?";

    public static String CHANGE_PASSWORD = "http://keshavgoyal.com/damroo/api/change_pass.php?user_id=";  //  &current_pass=  &new_pass=

    public static String keywordsearch = "http://keshavgoyal.com/damroo/api/search.php?keyword=";


    public static String api_for_homescreen = "http://keshavgoyal.com/damroo/api/home_screen.php?category_id=";

    public  static  String api_for_homescreen2 = "&user_id=";

    public  static  String myvideos = "http://keshavgoyal.com/damroo/api/my_video.php?user_id=";

    public  static  String likeunlike = "http://keshavgoyal.com/damroo/api/video_likes.php?video_id=";

    public  static  String likeunlike1 = "&user_id=";

    public  static  String likeunlike2 = "&status=";

    public  static  String subscribedchannel = "http://keshavgoyal.com/damroo/api/subscribe_channel_list.php?user_id=";

    public  static  String recommended_channel = "http://keshavgoyal.com/damroo/api/recommended_channel.php?category_id=";
}
