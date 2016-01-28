package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import android.content.Context;
import android.widget.Toast;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAccessToken;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.CloudMessage;

/**
 * Created by dbeaven on 1/27/2016.
 */
public class BASUserDelete extends BASCloudTask {


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
                delegate.onCloudResponse(response);
            }

            @Override
            public void onFailure(Throwable t) {
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
//                Toast.makeText(Context., "got it", Toast.LENGTH_SHORT).show();
                ;

//                delegate.onCloudResponse(response);
            }
        });
    }

}