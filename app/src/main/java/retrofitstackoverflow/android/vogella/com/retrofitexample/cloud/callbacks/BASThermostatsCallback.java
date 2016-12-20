package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.callbacks;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Response;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASCloudTask;

public class BASThermostatsCallback<T> extends BaseCallback<T> {
    private static final String TAG = BASThermostatsCallback.class.getSimpleName();

    public BASThermostatsCallback(BASCloudTask.CloudAsyncResponse delegate) {
        this.mDelegate = delegate;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        super.onResponse(call, response);
    }

}
