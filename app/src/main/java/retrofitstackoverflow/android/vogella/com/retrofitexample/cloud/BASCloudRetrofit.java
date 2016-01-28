package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAccessToken;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserInfo;

/**
 * Created by dbeaven on 1/26/2016.
 */
public class BASCloudRetrofit {
    private String baseURL = "https://dev.sensemecloud.com"; // dev
//    private String mBaseURL = "https://api.bigassfans.com";  // production
private BASAccessToken myToken = new BASAccessToken();

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

//        Call<BASAccessToken> call = basCloudAPI.loginUser(
//                basAuthInfo.client_id,
//                basAuthInfo.client_secret,
//                basAuthInfo.grant_type,
//                basAuthInfo.scope,
//                basAuthInfo.email,
//                basAuthInfo.password);
        //asynchronous call
        //todo:  need to fix this next call
//        call.enqueue(this);
    }

    private void doUserInfo() {

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
//        .baseUrl("https://api.bigassfans.com")

        // prepare call in Retrofit 2.0
        BASCloudAPI basCloudAPI = retrofit.create(BASCloudAPI.class);

        Call<BASUserInfo> call = basCloudAPI.userInfo("Bearer " + myToken.getAccess_token());
        //asynchronous call
//        call.enqueue(this);
        call.enqueue(new Callback<BASUserInfo>() {
            @Override
            public void onResponse(Response<BASUserInfo> response,
                                   Retrofit retrofit) {
//                updateTextViewFromResponse(response);
//                updateTextView("Success...", response.body().toString());

            }

            @Override
            public void onFailure(Throwable t) {
//                updateTextView(failureString, t.getLocalizedMessage());
            }
        });
    }

}
