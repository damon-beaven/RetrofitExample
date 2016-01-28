package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.lang.annotation.Annotation;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Converter;
import retrofit.Response;
import retrofit.Retrofit;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAccessToken;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.CloudMessage;

/**
 * Created by dbeaven on 1/27/2016.
 */
public class BASUserAuth extends BASCloudTask { //implements Callback<BASAccessToken> {

    public void loginExistingUser(BASAuthInfo basAuthInfo, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASAccessToken> call = mBasCloudAPI.loginUser(
                basAuthInfo.client_id,
                basAuthInfo.client_secret,
                "password",
                "read_user,write_user",
                basAuthInfo.email,
                basAuthInfo.password);

        //asynchronous call
        call.enqueue(new Callback<BASAccessToken>() {
            @Override
            public void onResponse(Response<BASAccessToken> response,
                                   Retrofit retrofit) {

                CloudMessage cloudMessage = new CloudMessage();

                //let's save that auth token for subsequent cloud calls!!
                if (response.body() != null) {
                    mToken = (BASAccessToken)response.body();
                }
                else {
                    Converter<ResponseBody, CloudMessage> converter =
                            mRetrofit.responseConverter(CloudMessage.class, new Annotation[0]);
                    try {
                        cloudMessage = converter.convert(response.errorBody());
                    } catch (IOException e) {
                        ;
                    }
                }

                cloudMessage.toString();

                delegate.onCloudResponse(response);
            }

            @Override
            public void onFailure(Throwable t) {
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
            }
        });
    }

    public void getCreateUserToken(final BASAuthInfo basAuthInfo, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASAccessToken> call = mBasCloudAPI.getCreateUserToken(
                basAuthInfo.client_id,
                basAuthInfo.client_secret,
                "client_credentials",
                "create_user");

        //asynchronous call
        call.enqueue(new Callback<BASAccessToken>() {
            @Override
            public void onResponse(Response<BASAccessToken> response,
                                   Retrofit retrofit) {
                //let's save that auth token for subsequent cloud calls!!
                if (response.body() != null) {
                    mToken = (BASAccessToken)response.body();
                }
                delegate.onCloudResponse(response);
            }

            @Override
            public void onFailure(Throwable t) {
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
            }
        });
    }
}
