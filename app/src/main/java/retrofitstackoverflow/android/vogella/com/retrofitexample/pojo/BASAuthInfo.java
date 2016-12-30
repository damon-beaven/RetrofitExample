package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

/**
 * Created by dbeaven on 1/25/2016.
 */
public class BASAuthInfo {
//    public String client_id = "sTpLXNu9XoF1AVxNAgLIM7Zl9fPDkJcsPGe2KxXm";         //production
//    public String client_secret = "AEnOBVYm8RQPfRVPwi9EBQjKkG3pbB7DtHigJtkv";     //production
    public static final String client_id = "zdonJbObthC6sRCwGy7z7lxLS7AvrbPr7IBUdVTH";           //dev
    public static final String client_secret = "wE4njcvWBEWh7Nrlyn2qG8IRLHVtW8ZsImAc2WXS";       //dev
    public static final String GRANT_TYPE = "password";
    public static final String SCOPE = "read_user,write_user";

//    public String grant_type = "password";
//    public String scope = "read_user,write_user";
    public String email = "damon.beaven@bigasssolutions.com";       //dev
    public String password = "qwertyui";                            //dev
    public String first_name = "Damon";
    public String last_name = "Beaven";
//    public String email = "dbeaven@gmail.com";                    //production
//    public String password = "blues2770";                         //production
}
