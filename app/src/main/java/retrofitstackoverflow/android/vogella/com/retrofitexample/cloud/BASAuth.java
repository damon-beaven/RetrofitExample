package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import android.util.Log;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAccessToken;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.CloudMessage;

/**
 * Created by dbeaven on 1/27/2016.
 */
public class BASAuth extends BASCloudTask {
    private static final String TAG = BASAuth.class.getSimpleName();

    private Callback<BASAccessToken> mAccessTokenCallback = new Callback<BASAccessToken>() {
        @Override
        public void onResponse(Call<BASAccessToken> call,
                               Response<BASAccessToken> response) {

            //let's save that auth token for subsequent cloud calls!!
            if (goodResponse(response)) {
                mToken = (BASAccessToken) response.body();
                handleGoodResponse(response, mDelegate);
            } else {
                handleErrorResponse(response, mDelegate);
            }
        }

        @Override
        public void onFailure(Call<BASAccessToken> call, Throwable t) {
            Log.wtf(TAG, t.toString());
            //not even sure if Retrofit 2.0 calls this anymore...we can
            //add another call if it actually does
        }
    };

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
        call.enqueue(this.mAccessTokenCallback);
    }

    public void getCreateUserToken(final BASAuthInfo basAuthInfo, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASAccessToken> call = mBasCloudAPI.getCreateUserToken(
                basAuthInfo.client_id,
                basAuthInfo.client_secret,
                "client_credentials",
                "create_user");

        //asynchronous call
        call.enqueue(this.mAccessTokenCallback);
    }

    public void getResetPasswordToken(final BASAuthInfo basAuthInfo, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASAccessToken> call = mBasCloudAPI.getResetPasswordToken(
                basAuthInfo.client_id,
                basAuthInfo.client_secret,
                "client_credentials",
                "reset_password");

        //asynchronous call
        call.enqueue(this.mAccessTokenCallback);
    }

    public void getFirmwareDownloadToken(final BASAuthInfo basAuthInfo, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASAccessToken> call = mBasCloudAPI.getFirmwareDownloadToken(
                basAuthInfo.client_id,
                basAuthInfo.client_secret,
                "client_credentials",
                "firmware");

        //asynchronous call
        call.enqueue(this.mAccessTokenCallback);
    }
}
