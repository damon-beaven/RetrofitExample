package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASDevices;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.CloudMessage;

/**
 * Created by dbeaven on 1/27/2016.
 */
public class BASUser extends BASCloudTask{

    public void createUserFromToken(BASAuthInfo basAuthInfo, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASUserInfo> call = mBasCloudAPI.createUserFromToken(
                "Bearer " + mToken.getAccess_token(),
                basAuthInfo.email,
                basAuthInfo.password,
                basAuthInfo.first_name,
                basAuthInfo.last_name);

        //asynchronous call
        call.enqueue(new Callback<BASUserInfo>() {
            @Override
            public void onResponse(Response<BASUserInfo> response,
                                   Retrofit retrofit) {
                //let's save that auth token for subsequent cloud calls!!
                if (goodResponse(response)) {
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

    public void getExistingUserInfo(BASAuthInfo basAuthInfo, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASUserInfo> call = mBasCloudAPI.getUserInfo("Bearer " + mToken.getAccess_token());

        //asynchronous call
        call.enqueue(new Callback<BASUserInfo>() {
            @Override
            public void onResponse(Response<BASUserInfo> response,
                                   Retrofit retrofit) {

                if (goodResponse(response)) {
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

    public void getExistingUserDevices(BASAuthInfo basAuthInfo, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASDevices> call = mBasCloudAPI.getUserDevices("Bearer " + mToken.getAccess_token());

        //asynchronous call
        call.enqueue(new Callback<BASDevices>() {
            @Override
            public void onResponse(Response<BASDevices> response,
                                   Retrofit retrofit) {

                if (goodResponse(response)) {
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

    public void getExistingUserThermostats(BASAuthInfo basAuthInfo, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASDevices> call = mBasCloudAPI.getUserDevices("Bearer " + mToken.getAccess_token());

        //asynchronous call
        call.enqueue(new Callback<BASDevices>() {
            @Override
            public void onResponse(Response<BASDevices> response,
                                   Retrofit retrofit) {

                if (goodResponse(response)) {
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

    public void deleteExistingUser(BASUserInfo basUserInfo, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<CloudMessage> call = mBasCloudAPI.userDelete(
                "Bearer " + mToken.getAccess_token(),
                basUserInfo.getUser().getId());

        //asynchronous call
        call.enqueue(new Callback<CloudMessage>() {
            @Override
            public void onResponse(Response<CloudMessage> response,
                                   Retrofit retrofit) {

                if (goodResponse(response)) {
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
