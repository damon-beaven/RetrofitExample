package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import retrofit2.Call;
import retrofit2.Callback;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.callbacks.BaseCallback;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASFirmwareInfo;

/**
 * Created by dbeaven on 2/1/2016.
 */
public class BASFirmware extends BASCloudTask{
    private static final String TAG = BASFirmware.class.getSimpleName();

    private Callback<BASFirmwareInfo> mFirmwareInfoCallback = new BaseCallback<>(this.mDelegate);

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
