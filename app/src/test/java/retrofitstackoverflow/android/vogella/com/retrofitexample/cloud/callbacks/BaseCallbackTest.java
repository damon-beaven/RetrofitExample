package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.callbacks;

import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;
import retrofitstackoverflow.android.vogella.com.retrofitexample.BuildConfig;
import retrofitstackoverflow.android.vogella.com.retrofitexample.MockBASCloudAPI;
import retrofitstackoverflow.android.vogella.com.retrofitexample.MockFailBASCloudAPI;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASCloudAPI;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASCloudTask;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.CloudMessage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class BaseCallbackTest {
    private BaseCallback<CloudMessage> cloudMessageSubject;
    private BaseCallback<BASUserInfo> basUserInfoSubject;
    private String successMessage;
    private String failMessage;
    private BASCloudAPI mockBasCloudApi;
    private BASCloudAPI mockFailBasCloudApi;

    @Before
    public void setup() {
        BASCloudTask.CloudAsyncResponse delegate = new BASCloudTask.CloudAsyncResponse() {
            @Override
            public void onCloudResponse(Response response) {
                Log.e("hey", "success");
                successMessage = "New Phone. Who dis?";
            }

            @Override
            public void onCloudError(CloudMessage message) {
                Log.e("hey", "error");
                failMessage = message.message;
            }
        };
        BASCloudTask.createService(BASCloudAPI.class);
        cloudMessageSubject = new BaseCallback<>(delegate);
        basUserInfoSubject = new BaseCallback<>(delegate);
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
        mockFailBasCloudApi = new MockFailBASCloudAPI(basCloudAPIBehaviorDelegate);
    }

    @After
    public void teardown() {

    }

    @Test
    public void testOnResponse_WhenGoodResponse_SetsSuccessMessage() throws Exception {
        Call<CloudMessage> getThermostatLinks = mockBasCloudApi.getThermostatLinks("my-token", "device-id", "thermostat-id");
        Response<CloudMessage> getThermostatLinksResponse = getThermostatLinks.execute();

        cloudMessageSubject.onResponse(getThermostatLinks, getThermostatLinksResponse);
        assertThat(successMessage, is(equalTo("New Phone. Who dis?")));
    }

    @Test
    public void testOnResponse_WhenBadResponse_SetsFailMessage() throws Exception {
        Call<CloudMessage> getThermostatLinks = mockFailBasCloudApi.getThermostatLinks("my-token", "device-id", "thermostat-id");
        Response<CloudMessage> getThermostatLinksResponse = getThermostatLinks.execute();

        cloudMessageSubject.onResponse(getThermostatLinks, getThermostatLinksResponse);
        assertThat(failMessage, is(equalTo("utter failure")));
    }

    @Test
    public void testOnResponse_WhenGetUser() throws Exception {
        Call<BASUserInfo> getUserInfo = mockBasCloudApi.getUserInfo("my-token");
        Response<BASUserInfo> getUserInfoResponse = getUserInfo.execute();

        basUserInfoSubject.onResponse(getUserInfo, getUserInfoResponse);
        assertThat(successMessage, is(equalTo("New Phone. Who dis?")));
    }
}
