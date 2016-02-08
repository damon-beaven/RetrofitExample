package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

/**
 * Created by dbeaven on 2/8/2016.
 */
// This is a singleton b/c we should only ever need to auth to one ecobee account.
public class EcobeeAuthInfo {
    private static EcobeeAuthInfo instance = null;

    private EcobeeAuthInfo(){};

    public static EcobeeAuthInfo getInstance()
    {
        if(null == instance)
        {
            instance = new EcobeeAuthInfo();
        }
        return instance;
    }

    //constants
    public final static String ECOBEE_API_KEY = "1Y7P5EnTSfLXNOaoMMcXneCAXNnK5Dtq";
    public final static String SENSEME_REDIRECT_URL = "https://senseme.redirect";
    public final static String ECOBEE_THERMOSTAT_TYPE_ID = "cdb84c3c-1b24-4872-99dd-ecefd58d8cb9";

    public final static String LOGIN_URL =
    "https://api.ecobee.com/authorize?response_type=code&client_id=" + ECOBEE_API_KEY + "&redirect_uri=" + SENSEME_REDIRECT_URL +"&scope=smartRead&state=STATE";

    public String authCode = ""; // will I need this?  something else?

}
