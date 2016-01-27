package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserInfo;

/**
 * Created by dbeaven on 1/27/2016.
 */
public class BASUserCreate extends BASCloudTask{

    public void execute(BASAuthInfo basAuthInfo, final BASCloudTask.CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASUserInfo> call = mBasCloudAPI.userInfo("Bearer " + mToken.getAccess_token());

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
}
