package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.lang.annotation.Annotation;

import retrofit.Converter;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAccessToken;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.CloudMessage;

/**
 * Created by dbeaven on 1/27/2016.
 */

    //  Implementation in the parent class should look something like this
    //  NOTE:  "implements AsyncResponse"
    //         "processFinish()..."
    //          Call the task normally with ".execute();"
//public class MainActivity implements AsyncResponse{
//
//    BASCloudTash asyncTask =new BASCloudTask(new AsyncResponse(){
//
//        @Override
//        void processFinish(String output){
//            //Here you will receive the result fired from async class
//            //of onPostExecute(result) method.
//        }
//    }).execute();
//
//}

public abstract class BASCloudTask { // extends AsyncTask<Object, Integer, Object> {

    public String getBaseURL() {
        return mBaseURL;
    }

    public BASAuthInfo getAuthInfo() {
        return mAuthInfo;
    }

    //    private String mBaseURL = "https://api.bigassfans.com";  // production
    protected String mBaseURL = "https://dev.sensemecloud.com"; // dev
    protected static BASAccessToken mToken = new BASAccessToken();
    protected BASAuthInfo mAuthInfo = new BASAuthInfo();
    protected Retrofit mRetrofit;
    protected BASCloudAPI mBasCloudAPI;

    public CloudAsyncResponse mDelegate = null;

    /**
     * Constructors
     */
    protected BASCloudTask() {
//        BASAuthInfo mAuthInfo = new BASAuthInfo();

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

        mRetrofit = new Retrofit.Builder()
                .baseUrl(mBaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        // prepare call in Retrofit 2.0
        mBasCloudAPI = mRetrofit.create(BASCloudAPI.class);

    }

    protected boolean goodResponse(Response<?> response) {
        return response.body() != null;
    }

    protected void handleGoodResponse(Response<?> response, CloudAsyncResponse delegate) {
        delegate.onCloudResponse(response);
    }

    // In Retrofit 2.0 we need need to parse out our errors.  I go ahead and
    // convert them to a CloudMessage which is custom for our purposes.
    // There may be a way to add something like this as an interceptor
    // but I didn't dig too much into that b/c this works just the same
    // and the impact to the user of these classes is no different.  We still need
    // a way to pass errors back to them in a different delegate API like onCloudError()
    protected void handleErrorResponse(Response<?> response, CloudAsyncResponse delegate) {
        CloudMessage cloudMessage = new CloudMessage();

        // need to create a converter for the error message
        //NOTE: found this example on https://futurestud.io/blog/retrofit-2-simple-error-handling
        // Some simple changes got it working
        Converter<ResponseBody, CloudMessage> converter =
                mRetrofit.responseConverter(CloudMessage.class, new Annotation[0]);

        // Let's try to get the error message our of the response
        try {
            cloudMessage = converter.convert(response.errorBody());
            delegate.onCloudError(cloudMessage);
        } catch (IOException e) {
            cloudMessage.message = "IOException processing error response.";
            delegate.onCloudError(cloudMessage);
        }
    }

//    protected BASCloudTask(CloudAsyncResponse mDelegate){
//        this.mDelegate = mDelegate;
//    }

    // This is how the caller will get the result of the operation
    // The calling class should "implements" CloudAsyncResponse and  the proper "@Override" will
    // be called when the process is done.  Hence, the parent gets the "result"
    // onCloudResponse - successful request
    // onCloudError - something went wrong, check the message
    public interface CloudAsyncResponse {
        void onCloudResponse(Response response);
        void onCloudError(CloudMessage message);
    }

//    public abstract void onResponse(Response response);

//    @Override
//    protected void onCloudResponse(Response response) {
//        // callback to the parent class to send the result
//        mDelegate.onCloudResponse(response);
//    }
}
