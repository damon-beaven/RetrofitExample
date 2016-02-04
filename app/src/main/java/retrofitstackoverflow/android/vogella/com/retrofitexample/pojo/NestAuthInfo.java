package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

/**
 * Created by dbeaven on 2/4/2016.
 */
public class NestAuthInfo {
    private static NestAuthInfo instance = null;

    private NestAuthInfo(){};

    public static NestAuthInfo getInstance()
    {
        if(null == instance)
        {
            instance = new NestAuthInfo();
        }
        return instance;
    }


    //production
//    public String client_id = "3420ad89-0f07-42a9-b3d0-b25a83906812";
//    public String client_secret = "MGYE5QNFxyzcHJ2znKmpBbffC";
//    public String client_secret = "1Y7P5EnTSfLXNOaoMMcXneCAXNnK5Dtq"; //ecobee api key
//    <string name="NEST_API_KEY" translatable="false">3420ad89-0f07-42a9-b3d0-b25a83906812</string>
//    <string name="NEST_API_SECRET" translatable="false">MGYE5QNFxyzcHJ2znKmpBbffC</string>
//    <string name="ECOBEE_API_KEY" translatable="false">1Y7P5EnTSfLXNOaoMMcXneCAXNnK5Dtq</string>


//example URLs
    //public final static String NEST_LOGIN_URL =
            // "https://home.nest.com/login/oauth2?client_id=3420ad89-0f07-42a9-b3d0-b25a83906812&state=STATE";
    //public final static String NEST_ACCESS_TOKEN_URL  =
    // "https://api.home.nest.com/oauth2/access_token?code=%s&client_id=3420ad89-0f07-42a9-b3d0-b25a83906812&client_secret=MGYE5QNFxyzcHJ2znKmpBbffC&grant_type=authorization_code";

//    public final static String NEST_LOGIN_URL   =
//            "https://home.nest.com/login/oauth2?client_id=" + BAFStringLib.string(R.string.NEST_API_KEY) + "&state=STATE";
//    public final static String NEST_ACCESS_TOKEN_URL    =
//            "https://api.home.nest.com/oauth2/access_token?code=%s&client_id=" + BAFStringLib.string(R.string.NEST_API_KEY) + "&client_secret=" + BAFStringLib.string(R.string.NEST_API_SECRET) + "&grant_type=authorization_code";
    // which translates too...
    // https://api.home.nest.com/oauth2/access_token?code=your_code
    //    &client_id=your_nest_api_client_id
    //    &client_secret=your_nest_api_client_secret
    //    &grant_type=authorization_code

    //dev
    public String client_id = "9e9c8fe4-34db-418a-83bb-d837d57a01fd";
    public String client_secret = "Iv7vuCYkJ8qrOWW9tnmG5fMZX";
    public String loginUrl =
            "https://home.nest.com/login/oauth2?client_id=" + client_id +"&state=FOO";
    public String redirectUrl = "https://senseme.redirect";
//    <string name="NEST_API_KEY" translatable="false">9e9c8fe4-34db-418a-83bb-d837d57a01fd</string>
//    <string name="NEST_API_SECRET" translatable="false">Iv7vuCYkJ8qrOWW9tnmG5fMZX</string>

    //the redirect that I got from...
    //https://home.nest.com/login/oauth2?client_id=9e9c8fe4-34db-418a-83bb-d837d57a01fd&state=FOO
    //I got this with the code...
    //https://senseme.redirect/?state=FOO&code=MKGTGJKEXQYL4M7M

    public String accessToken = "";

}