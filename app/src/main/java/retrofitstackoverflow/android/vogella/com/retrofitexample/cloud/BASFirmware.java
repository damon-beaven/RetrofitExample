package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.callbacks.BASFirmwareInfoCallback;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASFirmwareInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserInfo;

/**
 * Created by dbeaven on 2/1/2016.
 */
public class BASFirmware extends BASCloudTask{
    private static final String TAG = BASFirmware.class.getSimpleName();

    private Callback<BASFirmwareInfo> mFirmwareInfoCallback = new BASFirmwareInfoCallback<>(this.mDelegate);

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
