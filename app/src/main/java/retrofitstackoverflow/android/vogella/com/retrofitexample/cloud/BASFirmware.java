package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASFirmwareInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserInfo;

/**
 * Created by dbeaven on 2/1/2016.
 */
public class BASFirmware extends BASCloudTask{
    private static final String TAG = BASFirmware.class.getSimpleName();

    private Callback<BASFirmwareInfo> mFirmwareInfoCallback = new Callback<BASFirmwareInfo>() {
        @Override
        public void onResponse(Call<BASFirmwareInfo> call, Response<BASFirmwareInfo> response) {
            if (goodResponse(response)) {
                handleGoodResponse(response, mDelegate);
            }
            else {
                handleErrorResponse(response, mDelegate);
            }
        }

        @Override
        public void onFailure(Call<BASFirmwareInfo> call, Throwable t) {
            Log.wtf(TAG, t.toString());
            //not even sure if Retrofit 2.0 calls this anymore...we can
            //add another call if it actually does
        }
    };

    public void getFirmwareDownloadUrlFromToken(BASAuthInfo basAuthInfo,
                                                String fwKey,
                                                final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASFirmwareInfo> call = mBasCloudAPI.getFirmwareDownloadUrlFromToken(
                "Bearer " + mToken.getAccess_token(),
                fwKey);

        //asynchronous call
        call.enqueue(this.mFirmwareInfoCallback);
    }
}
