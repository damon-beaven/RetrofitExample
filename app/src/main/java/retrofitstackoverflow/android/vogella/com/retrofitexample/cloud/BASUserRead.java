package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserInfo;

/**
 * Created by dbeaven on 1/27/2016.
 */
public class BASUserRead extends BASCloudTask {

    public void execute(BASAuthInfo basAuthInfo, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

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
                .baseUrl(mBaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        // prepare call in Retrofit 2.0
        BASCloudAPI basCloudAPI = retrofit.create(BASCloudAPI.class);

        Call<BASUserInfo> call = basCloudAPI.userInfo("Bearer " + mToken.getAccess_token());

        //asynchronous call
        call.enqueue(new Callback<BASUserInfo>() {
            @Override
            public void onResponse(Response<BASUserInfo> response,
                                   Retrofit retrofit) {
                delegate.onCloudResponse(response);
            }

            @Override
            public void onFailure(Throwable t) {
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
            }
        });

    }
//    private void onResponse(Response<BASAccessToken> response) {
//        // callback to the parent class to send the result
//        mDelegate.onResponse(response);
//    }
//
//    private void onResponseToCaller(Response<BASAccessToken> response) {
//        super.onResponse(response);
//    }
}
