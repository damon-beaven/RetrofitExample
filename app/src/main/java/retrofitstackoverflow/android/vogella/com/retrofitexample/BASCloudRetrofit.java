package retrofitstackoverflow.android.vogella.com.retrofitexample;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAccessToken;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;

/**
 * Created by dbeaven on 1/26/2016.
 */
public class BASCloudRetrofit {
    private String baseURL = "https://dev.sensemecloud.com"; // dev
//    private String baseURL = "https://api.bigassfans.com";  // production
//----------------------------------------
private static BASCloudRetrofit instance = null;

    //----------------------------------------
    // BASCloudRetrofit as a Singleton
    // This class uses Retrofit to exercise REST interfaces to the BAS Cloud
    private BASCloudRetrofit()
    {
    }

    public static BASCloudRetrofit get()
    {
        if(null == instance)
        {
            instance = new BASCloudRetrofit();
        }
        return instance;
    }
    //----------------------------------------

    private void doAuth() {

        BASAuthInfo basAuthInfo = new BASAuthInfo();

//        // Define the interceptor, add authentication headers
//        Interceptor interceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request newRequest = chain.request().newBuilder().addHeader("User-Agent", "Retrofit-Sample-App").build();
//                return chain.proceed(newRequest);
//            }
//        };

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient();
        // add your other interceptors
//        httpClient.interceptors().add(interceptor);
        // add logging as last interceptor
        httpClient.interceptors().add(logging);  // <-- this is the important line!

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        // prepare call in Retrofit 2.0
        BASCloudAPI basCloudAPI = retrofit.create(BASCloudAPI.class);

        Call<BASAccessToken> call = basCloudAPI.loginUser(
                basAuthInfo.client_id,
                basAuthInfo.client_secret,
                basAuthInfo.grant_type,
                basAuthInfo.scope,
                basAuthInfo.email,
                basAuthInfo.password);
        //asynchronous call
        //todo:  need to fix this next call
//        call.enqueue(this);
    }
}
