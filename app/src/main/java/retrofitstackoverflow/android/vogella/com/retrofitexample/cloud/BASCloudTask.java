package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import android.os.AsyncTask;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.lang.annotation.Annotation;

import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAccessToken;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserConfirmInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.CloudMessage;

public abstract class BASCloudTask { // extends AsyncTask<Object, Integer, Object> {
    /////////////////////////////////////////////////////////////////////////////
    //NOTE:  Any static members are intentional.  This allows the abstract base
    // class to act as a conduit for derived classes to "share" information.
    // This could be a problem if we were to try to work with two different user
    // accounts at the same time.  Information like access tokens, PINs, etc. could
    // be overwritten causing failures with cloud communications.
    /////////////////////////////////////////////////////////////////////////////

    public static final String API_BASE_URL = "https://dev.sensemecloud.com"; // dev
    protected static BASAccessToken mToken = new BASAccessToken();
    protected static BASUserConfirmInfo mConfirmInfo = new BASUserConfirmInfo(); // set in confirmUserFromPin()
                                                                                 // used in getResetPasswordEmail()
    protected static String mPasswordResetPin; // if we have seen a password reset pin we will hold it
                                               // for the subsequent password reset call.
    protected static BASAuthInfo mAuthInfo = new BASAuthInfo();
    public BASAuthInfo getAuthInfo() {
        return mAuthInfo;
    }

    private static OkHttpClient.Builder sHttpClient = new OkHttpClient.Builder();
    private static Interceptor sHeaderInterceptor =  new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("Accept", "application/vnd.bigassfans.v1+json")
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        }
    };
    private static Retrofit.Builder sRetrofitBuilder = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
    .addConverterFactory(GsonConverterFactory.create());
    protected static Retrofit sRetrofit;
    protected BASCloudAPI mBasCloudAPI;

    public CloudAsyncResponse mDelegate = null;

    /**
     * Constructors
     */
    protected BASCloudTask() {
        // prepare call in Retrofit 2.0
        mBasCloudAPI = createService(BASCloudAPI.class);

    }

    public static <S> S createService(Class<S> serviceClass) {
        sHttpClient.addInterceptor(sHeaderInterceptor);
        sRetrofit = sRetrofitBuilder.client(sHttpClient.build()).build();
        return sRetrofit.create(serviceClass);
    }

    protected boolean goodResponse(Response<?> response) {
        return response.body() != null;
    }

    protected void handleGoodResponse(Response<?> response, BASCloudTask.CloudAsyncResponse delegate) {
        delegate.onCloudResponse(response);
    }

    // In Retrofit 2.0 we need need to parse out our errors.  I go ahead and
    // convert them to a CloudMessage which is custom for our purposes.
    // There may be a way to add something like this as an interceptor
    // but I didn't dig too much into that b/c this works just the same
    // and the impact to the user of these classes is no different.  We still need
    // a way to pass errors back to them in a different delegate API like onCloudError()
    protected void handleErrorResponse(Response<?> response, BASCloudTask.CloudAsyncResponse delegate) {
        CloudMessage cloudMessage = new CloudMessage();

        // need to create a converter for the error message
        //NOTE: found this example on https://futurestud.io/blog/retrofit-2-simple-error-handling
        // Some simple changes got it working
        Converter<ResponseBody, CloudMessage> converter =
                BASCloudTask.getRetrofit().responseBodyConverter(CloudMessage.class, new Annotation[0]);

        // Let's try to get the error message out of the response
        try {
            cloudMessage = converter.convert(response.errorBody());
            delegate.onCloudError(cloudMessage);
        } catch (IOException e) {
            cloudMessage.message = "IOException processing error response.";
            delegate.onCloudError(cloudMessage);
        }
    }

    public static Retrofit getRetrofit() {
        return sRetrofit;
    }

    public interface CloudAsyncResponse {
        void onCloudResponse(Response response);
        void onCloudError(CloudMessage message);
    }
}
