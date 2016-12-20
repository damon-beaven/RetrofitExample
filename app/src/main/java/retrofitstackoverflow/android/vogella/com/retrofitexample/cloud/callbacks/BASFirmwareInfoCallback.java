package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.callbacks;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Response;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASCloudTask;

public class BASFirmwareInfoCallback<T> extends BaseCallback<T> {
    private static final String TAG = BASFirmwareInfoCallback.class.getSimpleName();

    public BASFirmwareInfoCallback(BASCloudTask.CloudAsyncResponse delegate) {
        this.mDelegate = delegate;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        if (goodResponse(response)) {
            handleGoodResponse(response, mDelegate);
        }
        else {
            handleErrorResponse(response, mDelegate);
        }
    }

}
