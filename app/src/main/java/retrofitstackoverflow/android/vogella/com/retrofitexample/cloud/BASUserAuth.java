package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAccessToken;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;

/**
 * Created by dbeaven on 1/27/2016.
 */
public class BASUserAuth extends BASCloudTask { //implements Callback<BASAccessToken> {

    public void execute(BASAuthInfo basAuthInfo, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASAccessToken> call = mBasCloudAPI.loginUser(
                basAuthInfo.client_id,
                basAuthInfo.client_secret,
                basAuthInfo.grant_type,
                basAuthInfo.scope,
                basAuthInfo.email,
                basAuthInfo.password);

        //asynchronous call
        call.enqueue(new Callback<BASAccessToken>() {
            @Override
            public void onResponse(Response<BASAccessToken> response,
                                   Retrofit retrofit) {
                //let's save that auth token for subsequent cloud calls!!
                if (response.body() != null) mToken = (BASAccessToken)response.body();
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
