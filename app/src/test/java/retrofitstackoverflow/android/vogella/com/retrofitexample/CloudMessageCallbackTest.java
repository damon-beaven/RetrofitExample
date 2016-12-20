package retrofitstackoverflow.android.vogella.com.retrofitexample;

import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASCloudAPI;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASCloudTask;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.callbacks.CloudMessageCallback;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.CloudMessage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class CloudMessageCallbackTest {
    private CloudMessageCallback<CloudMessage> subject;
    String successMessage;
    String failMessage;
    BASCloudAPI mockBasCloudApi;

    @Before
    public void setup() {
        BASCloudTask.CloudAsyncResponse delegate = new BASCloudTask.CloudAsyncResponse() {
            @Override
            public void onCloudResponse(Response response) {
                Log.e("hey", "success");
                successMessage = response.message();
            }

            @Override
            public void onCloudError(CloudMessage message) {
                Log.e("hey", "error");
                failMessage = message.message;
            }
        };
        subject = new CloudMessageCallback<>(delegate);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://test.com")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NetworkBehavior behavior = NetworkBehavior.create();
        MockRetrofit mockRetrofit = new MockRetrofit
                .Builder(retrofit)
                .networkBehavior(behavior)
                .build();
        BehaviorDelegate<BASCloudAPI> basCloudAPIBehaviorDelegate = mockRetrofit.create(BASCloudAPI.class);
        mockBasCloudApi = new MockBASCloudAPI(basCloudAPIBehaviorDelegate);
    }

    @After
    public void teardown() {

    }

    @Test
    public void testOnResponse_WhenGoodResponse_SetsSuccessMessageToOK() throws Exception {
        Call<CloudMessage> getThermostatLinks = mockBasCloudApi.getThermostatLinks("my-token", "device-id", "thermostat-id");
        Response<CloudMessage> getThermostatLinksResponse = getThermostatLinks.execute();

        subject.onResponse(getThermostatLinks, getThermostatLinksResponse);
        assertThat(successMessage, is(equalTo("OK")));
    }
}
