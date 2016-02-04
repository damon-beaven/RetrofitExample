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
public class BASAuth extends BASCloudTask {

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

                //let's save that auth token for subsequent cloud calls!!
                if (goodResponse(response)) {
                    mToken = (BASAccessToken) response.body();
                    handleGoodResponse(response, delegate);
                } else {
                    handleErrorResponse(response, delegate);
                }
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
                if (goodResponse(response)) {
                    mToken = (BASAccessToken)response.body();
                    handleGoodResponse(response, delegate);
                }
                else {
                    handleErrorResponse(response, delegate);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
            }
        });
    }

    public void getResetPasswordToken(final BASAuthInfo basAuthInfo, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASAccessToken> call = mBasCloudAPI.getResetPasswordToken(
                basAuthInfo.client_id,
                basAuthInfo.client_secret,
                "client_credentials",
                "reset_password");

        //asynchronous call
        call.enqueue(new Callback<BASAccessToken>() {
            @Override
            public void onResponse(Response<BASAccessToken> response,
                                   Retrofit retrofit) {
                //let's save that auth token for subsequent cloud calls!!
                if (goodResponse(response)) {
                    mToken = (BASAccessToken)response.body();
                    handleGoodResponse(response, delegate);
                }
                else {
                    handleErrorResponse(response, delegate);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
            }
        });
    }

    public void getFirmwareDownloadToken(final BASAuthInfo basAuthInfo, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASAccessToken> call = mBasCloudAPI.getFirmwareDownloadToken(
                basAuthInfo.client_id,
                basAuthInfo.client_secret,
                "client_credentials",
                "firmware");

        //asynchronous call
        call.enqueue(new Callback<BASAccessToken>() {
            @Override
            public void onResponse(Response<BASAccessToken> response,
                                   Retrofit retrofit) {
                //let's save that auth token for subsequent cloud calls!!
                if (goodResponse(response)) {
                    mToken = (BASAccessToken)response.body();
                    handleGoodResponse(response, delegate);
                }
                else {
                    handleErrorResponse(response, delegate);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
            }
        });
    }
}
