package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.callbacks;


import android.util.Log;

import retrofit2.Call;
import retrofit2.Response;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASCloudTask;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserConfirmInfo;

public class BASUserConfirmInfoCallback<T> extends BaseCallback<T> {
    private static final String TAG = BASUserConfirmInfoCallback.class.getSimpleName();
    private BASUserConfirmInfo mConfirmInfo;

    public BASUserConfirmInfoCallback(BASCloudTask.CloudAsyncResponse delegate) {
        this.mDelegate = delegate;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        if (goodResponse(response)) {
            mConfirmInfo = (BASUserConfirmInfo)response.body();
            handleGoodResponse(response, mDelegate);
        }
        else {
            handleErrorResponse(response, mDelegate);
        }
    }

}
