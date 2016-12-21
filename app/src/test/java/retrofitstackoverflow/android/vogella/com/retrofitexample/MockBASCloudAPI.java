package retrofitstackoverflow.android.vogella.com.retrofitexample;

import android.util.Log;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.mock.BehaviorDelegate;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASCloudAPI;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.callbacks.BASAccessTokenCallback;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAccessToken;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASDevices;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASFirmwareInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASThermostatTypes;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASThermostats;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASThermostatsLinkedDevicesInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserConfirmInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.CloudMessage;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.User;

public class MockBASCloudAPI implements BASCloudAPI {
    private final BehaviorDelegate<BASCloudAPI> delegate;
    private static final String TAG = MockBASCloudAPI.class.getSimpleName();
    public MockBASCloudAPI(BehaviorDelegate<BASCloudAPI> service) {
        this.delegate = service;
    }

    @Override
    public Call<BASUserInfo> getUserInfo(@Header("Authorization") String myToken) {
        BASUserInfo basUserInfo = new BASUserInfo();
        try {
            String userInfoJsonString = ApiTestHelper.getStringFromFile(this, "me.json");
            Gson gson = new Gson();
            basUserInfo = gson.fromJson(userInfoJsonString, BASUserInfo.class);
        } catch (Exception exception) {
            Log.d(TAG, exception.toString());
        }
        return  delegate.returningResponse(basUserInfo).getUserInfo(myToken);
    }

    public Call<BASDevices> getUserDevices(@Header("Authorization") String myToken) {
        BASDevices basDevices = new BASDevices();
        try {
            String userDevicesJsonString = ApiTestHelper.getStringFromFile(this, "user-devices.json");
            Gson gson = new Gson();
            basDevices = gson.fromJson(userDevicesJsonString, BASDevices.class);
        } catch (Exception exception) {
            Log.d(TAG, exception.toString());
        }
        return  delegate.returningResponse(basDevices).getUserDevices(myToken);
    }

    public Call<BASThermostatTypes> getThermostatTypes(@Header("Authorization") String myToken) {
        return  delegate.returningResponse(null).getThermostatTypes(myToken);
    }

    public Call<BASThermostats> getUserThermostats(@Header("Authorization") String myToken) {
        return  delegate.returningResponse(null).getUserThermostats(myToken);
    }

    public Call<BASThermostatsLinkedDevicesInfo> getUserThermostatsLinkedDevices(@Header("Authorization") String myToken) {
        return  delegate.returningResponse(null).getUserThermostatsLinkedDevices(myToken);
    }

    public Call<BASAccessToken> loginUser(@Field("client_id") String clientId,
                                   @Field("client_secret") String clientSecret,
                                   @Field("grant_type") String grantType,
                                   @Field("scope") String scope,
                                   @Field("email") String email,
                                   @Field("password") String password) {
        return  delegate.returningResponse(null).loginUser(clientId, clientSecret, grantType, scope, email, password);
    }

    public Call<BASAccessToken> getResetPasswordToken(@Field("client_id") String clientId,
                                               @Field("client_secret") String clientSecret,
                                               @Field("grant_type") String grantType,
                                               @Field("scope") String scope) {
        return  delegate.returningResponse(null).getResetPasswordToken(clientId, clientSecret, grantType, scope);
    }

    public Call<BASAccessToken> getCreateUserToken(@Field("client_id") String clientId,
                                            @Field("client_secret") String clientSecret,
                                            @Field("grant_type") String grantType,
                                            @Field("scope") String scope) {
        return  delegate.returningResponse(null).getCreateUserToken(clientId, clientSecret, grantType, scope);
    }

    public Call<BASAccessToken> getFirmwareDownloadToken(@Field("client_id") String clientId,
                                                  @Field("client_secret") String clientSecret,
                                                  @Field("grant_type") String grantType,
                                                  @Field("scope") String scope) {
        return  delegate.returningResponse(null).getFirmwareDownloadToken(clientId, clientSecret, grantType, scope);
    }


    public Call<BASUserInfo> createUserFromToken(@Header("Authorization") String myToken,
                                          @Field("email") String email,
                                          @Field("password") String password,
                                          @Field("first_name") String firstName,
                                          @Field("last_name") String lastName) {
        return  delegate.returningResponse(null).createUserFromToken(myToken, email, password, firstName, lastName);
    }

    public Call<BASUserInfo> updateUserFromId(@Header("Authorization") String myToken,
                                       @Path("user_id") String userID,
                                       @Field("email") String email,
                                       @Field("password") String password,
                                       @Field("first_name") String firstName,
                                       @Field("last_name") String lastName) {
        return  delegate.returningResponse(null).updateUserFromId(myToken, userID, email, password, firstName, lastName);
    }

    public Call<BASUserConfirmInfo> confirmUserFromPin(@Header("Authorization") String myToken,
                                                @Field("email") String email,
                                                @Field("pin") String pin) {
        return  delegate.returningResponse(null).confirmUserFromPin(myToken, email, pin);
    }

    public Call<CloudMessage> resetUserPasswordFromResetToken(@Header("Authorization") String myToken,
                                                       @Field("email") String email,
                                                       @Field("pin") String pin,
                                                       @Field("reset_token") String resetToken,
                                                       @Field("password") String password) {
        return  delegate.returningResponse(null).resetUserPasswordFromResetToken(myToken, email, pin, resetToken, password);
    }

    public Call<CloudMessage> getNewPinEmail(@Header("Authorization") String myToken,
                                      @Field("email") String email) {
        return  delegate.returningResponse(null).getNewPinEmail(myToken, email);
    }


    public Call<CloudMessage> getResetPasswordEmail(@Header("Authorization") String myToken,
                                             @Field("email") String email) {
        return  delegate.returningResponse(null).getResetPasswordEmail(myToken, email);
    }


    public Call<BASThermostats> createUserThermostat(@Header("Authorization") String myToken,
                                              @Field("thermostat_type_id") String thermostatTypeId,
                                              @Field("authorization_code") String authorizationCode) {
        return  delegate.returningResponse(null).createUserThermostat(myToken, thermostatTypeId, authorizationCode);
    }

    public Call<CloudMessage> deleteUserThermostat(@Header("Authorization") String myToken,
                                            @Path("token_id") String tokenID) {
        return  delegate.returningResponse(null).deleteUserThermostat(myToken, tokenID);
    }

    public Call<CloudMessage> linkThermostatToDevice(@Header("Authorization") String myToken,
                                              @Field("device_id") String deviceID,
                                              @Field("thermostat_id") String thermostatID) {
        return  delegate.returningResponse(null).linkThermostatToDevice(myToken, deviceID, thermostatID);
    }

    public Call<CloudMessage> getThermostatLinks(@Header("Authorization") String myToken,
                                          @Field("device_id") String deviceID,
                                          @Field("thermostat_id") String thermostatID) {
        CloudMessage cloudMessage = new CloudMessage();
        cloudMessage.success = "not sure";
        cloudMessage.message = "i'm messing around here";
        cloudMessage.status_code = "okay";
        cloudMessage.code = "200";

        return  delegate.returningResponse(cloudMessage).getThermostatLinks(myToken, deviceID, thermostatID);
    }

    public Call<CloudMessage> deleteThermostatLinkToDevice(@Header("Authorization") String myToken,
                                                    @Path("thermostat_id") String thermostatID,
                                                    @Path("device_id") String deviceID) {
        return  delegate.returningResponse(null).deleteThermostatLinkToDevice(myToken, thermostatID, deviceID);
    }

    public Call<BASFirmwareInfo> getFirmwareDownloadUrlFromToken(@Header("Authorization") String myToken,
                                                          @Query("firmware_key") String fwKey) {
        return  delegate.returningResponse(null).getFirmwareDownloadUrlFromToken(myToken, fwKey);
    }


    public Call<CloudMessage> userDelete(@Header("Authorization") String myToken,
                                  @Path("user_id") String userID) {
        return  delegate.returningResponse(null).userDelete(myToken, userID);
    }

}
