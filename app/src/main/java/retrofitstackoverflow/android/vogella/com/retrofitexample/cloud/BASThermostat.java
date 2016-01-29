package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASThermostatTypes;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserInfo;

/**
 * Created by dbeaven on 1/29/2016.
 */

public class BASThermostat extends BASCloudTask {

    public void getThermostatTypes(BASAuthInfo basAuthInfo, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASThermostatTypes> call = mBasCloudAPI.getThermostatTypes("Bearer " + mToken.getAccess_token());

        //asynchronous call
        call.enqueue(new Callback<BASThermostatTypes>() {
            @Override
            public void onResponse(Response<BASThermostatTypes> response,
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
