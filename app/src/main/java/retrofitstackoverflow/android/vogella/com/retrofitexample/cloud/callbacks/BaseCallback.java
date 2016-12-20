package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.callbacks;

import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASCloudTask;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.CloudMessage;

public class BaseCallback<T> implements Callback<T> {
    protected BASCloudTask.CloudAsyncResponse mDelegate;

    public BaseCallback(BASCloudTask.CloudAsyncResponse delegate) {
        this.mDelegate = delegate;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        if (goodResponse(response)) {
            handleGoodResponse(response, mDelegate);
        }
        else {
            handleErrorResponse(response, mDelegate);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.wtf("hey man", t.toString());
        //not even sure if Retrofit 2.0 calls this anymore...we can
        //add another call if it actually does
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
}
