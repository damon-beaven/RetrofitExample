package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.callbacks;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Response;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASCloudTask;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAccessToken;

public class BASAccessTokenCallback<T> extends BaseCallback<T> {
    private static final String TAG = BASAccessTokenCallback.class.getSimpleName();
    protected static BASAccessToken sBasAccessToken = new BASAccessToken();

    public BASAccessTokenCallback(BASCloudTask.CloudAsyncResponse delegate) {
        this.mDelegate = delegate;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        if (goodResponse(response)) {
            sBasAccessToken = (BASAccessToken) response.body();
            handleGoodResponse(response, mDelegate);
        }
        else {
            handleErrorResponse(response, mDelegate);
        }
    }
}
