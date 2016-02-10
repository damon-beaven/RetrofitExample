package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import android.util.Log;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASFirmwareInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserInfo;

/**
 * Created by dbeaven on 2/1/2016.
 */
public class BASFirmware extends BASCloudTask{
    private static final String TAG = BASFirmware.class.getSimpleName();

    public void getFirmwareDownloadUrlFromToken(BASAuthInfo basAuthInfo,
                                                String fwKey,
                                                final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASFirmwareInfo> call = mBasCloudAPI.getFirmwareDownloadUrlFromToken(
                "Bearer " + mToken.getAccess_token(),
                fwKey);

        //asynchronous call
        call.enqueue(new Callback<BASFirmwareInfo>() {
            @Override
            public void onResponse(Response<BASFirmwareInfo> response,
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
                Log.wtf(TAG, t.toString());
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
            }
        });
    }
}
