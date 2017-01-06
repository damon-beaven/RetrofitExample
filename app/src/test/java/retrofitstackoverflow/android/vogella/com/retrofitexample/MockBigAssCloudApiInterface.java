package retrofitstackoverflow.android.vogella.com.retrofitexample;

import android.util.Log;

import com.google.gson.Gson;

import retrofit2.Call;
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
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BigAssCloudApiInterface;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAccessToken;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASDevices;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASFirmwareInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASThermostatTypes;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASThermostats;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASThermostatsLinkedDevicesInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserConfirmInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.CloudMessage;

/**
 * Created by zfreeman on 1/6/17.
 */

public class MockBigAssCloudApiInterface implements BigAssCloudApiInterface {

    private final BehaviorDelegate<BigAssCloudApiInterface> delegate;
    private static final String TAG = MockBASCloudAPI.class.getSimpleName();
    public MockBigAssCloudApiInterface(BehaviorDelegate<BigAssCloudApiInterface> service) {
        this.delegate = service;
    }

    @GET("/me")
    public Call<BASUserInfo> getUserInfo(@Header("Authorization") String myToken) {
        BASUserInfo basUserInfo = new BASUserInfo();
        try {
            String userInfoJsonString = ApiTestHelper.getStringFromFile(this, "me.json");
            Gson gson = new Gson();
            basUserInfo = gson.fromJson(userInfoJsonString, BASUserInfo.class);
        } catch (Exception exception) {
            Log.d(TAG, exception.toString());
        }
        return delegate.returningResponse(basUserInfo).getUserInfo(myToken);
    }

    @GET("/devices")
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

    @GET("/thermostats/types")
    public Call<BASThermostatTypes> getThermostatTypes(@Header("Authorization") String myToken) {
        BASThermostatTypes thermostatTypes = new BASThermostatTypes();
        try {
            String thermostatTypesJsonString = ApiTestHelper.getStringFromFile(this, "thermostat-types.json");
            Gson gson = new Gson();
            thermostatTypes = gson.fromJson(thermostatTypesJsonString, BASThermostatTypes.class);
        } catch (Exception exception) {
            Log.d(TAG, exception.toString());
        }
        return  delegate.returningResponse(thermostatTypes).getThermostatTypes(myToken);
    }

    @GET("/thermostats")
    public Call<BASThermostats> getUserThermostats(@Header("Authorization") String myToken) {
        return  delegate.returningResponse(null).getUserThermostats(myToken);
    }

    @GET("/thermostats/links")
    public Call<BASThermostatsLinkedDevicesInfo> getUserThermostatsLinkedDevices(@Header("Authorization") String myToken) {
        return  delegate.returningResponse(null).getUserThermostatsLinkedDevices(myToken);
    }
    @POST("/oauth/access_token")
    @FormUrlEncoded
    public Call<BASAccessToken> loginUser(@Field("client_id") String clientId,
                                          @Field("client_secret") String clientSecret,
                                          @Field("grant_type") String grantType,
                                          @Field("scope") String scope,
                                          @Field("email") String email,
                                          @Field("password") String password) {
        return  delegate.returningResponse(null).loginUser(clientId, clientSecret, grantType, scope, email, password);
    }

    @POST("/oauth/access_token")
    @FormUrlEncoded
    public Call<BASAccessToken> getResetPasswordToken(@Field("client_id") String clientId,
                                                      @Field("client_secret") String clientSecret,
                                                      @Field("grant_type") String grantType,
                                                      @Field("scope") String scope) {
        return  delegate.returningResponse(null).getResetPasswordToken(clientId, clientSecret, grantType, scope);
    }

    @POST("/oauth/access_token")
    @FormUrlEncoded
    public Call<BASAccessToken> getCreateUserToken(@Field("client_id") String clientId,
                                                   @Field("client_secret") String clientSecret,
                                                   @Field("grant_type") String grantType,
                                                   @Field("scope") String scope) {
        return  delegate.returningResponse(null).getCreateUserToken(clientId, clientSecret, grantType, scope);
    }

    @POST("/oauth/access_token")
    @FormUrlEncoded
    public Call<BASAccessToken> getFirmwareDownloadToken(@Field("client_id") String clientId,
                                                         @Field("client_secret") String clientSecret,
                                                         @Field("grant_type") String grantType,
                                                         @Field("scope") String scope) {
        return  delegate.returningResponse(null).getFirmwareDownloadToken(clientId, clientSecret, grantType, scope);
    }


    @POST("/users")
    @FormUrlEncoded
    public Call<BASUserInfo> createUserFromToken(@Header("Authorization") String myToken,
                                                 @Field("email") String email,
                                                 @Field("password") String password,
                                                 @Field("first_name") String firstName,
                                                 @Field("last_name") String lastName) {
        return  delegate.returningResponse(null).createUserFromToken(myToken, email, password, firstName, lastName);
    }

    // each @Field is optional on the cloud side, but we use them all
    // so we don't have to parse to figure out what was changed.
    // @Path userID is required
    @PUT("/users/{user_id}")
    @FormUrlEncoded
    public Call<BASUserInfo> updateUserFromId(@Header("Authorization") String myToken,
                                              @Path("user_id") String userID,
                                              @Field("email") String email,
                                              @Field("password") String password,
                                              @Field("first_name") String firstName,
                                              @Field("last_name") String lastName) {
        return  delegate.returningResponse(null).updateUserFromId(myToken, userID, email, password, firstName, lastName);
    }

    // get create_user token from getCreateUserToken
    // confirmUserFromPin
    // will get a 32 char UUID (reset_token)
    // you will need the reset_token if you want to reset the password
    @PUT("/users/confirm")
    @FormUrlEncoded
    public Call<BASUserConfirmInfo> confirmUserFromPin(@Header("Authorization") String myToken,
                                                       @Field("email") String email,
                                                       @Field("pin") String pin) {
        return  delegate.returningResponse(null).confirmUserFromPin(myToken, email, pin);
    }

    @PUT("/users/password")
    @FormUrlEncoded
    public Call<CloudMessage> resetUserPasswordFromResetToken(@Header("Authorization") String myToken,
                                                              @Field("email") String email,
                                                              @Field("pin") String pin,
                                                              @Field("reset_token") String resetToken,
                                                              @Field("password") String password) {
        return  delegate.returningResponse(null).resetUserPasswordFromResetToken(myToken, email, pin, resetToken, password);
    }

    // get create_user token
    // create the user (registration)
    // getNewPinEmail
    @POST("/users/pin")
    @FormUrlEncoded
    public Call<CloudMessage> getNewPinEmail(@Header("Authorization") String myToken,
                                             @Field("email") String email) {
        return  delegate.returningResponse(null).getNewPinEmail(myToken, email);
    }

    // get getResetPasswordToken for reset_password token
    // call getPasswordEmail with reset_password token
    // call confirmUserFromPin with email and pin
    // call resetUserPassword with email, pin, reset_token (from confirmUserFromPin), password
    @POST("/users/pin")
    @FormUrlEncoded
    public Call<CloudMessage> getResetPasswordEmail(@Header("Authorization") String myToken,
                                                    @Field("email") String email) {
        return  delegate.returningResponse(null).getResetPasswordEmail(myToken, email);
    }


    // Associate a third party thermostat account with BAS account
    // Nest/ecobee/etc.
    @POST("/thermostats")
    @FormUrlEncoded
    public Call<BASThermostats> createUserThermostat(@Header("Authorization") String myToken,
                                                     @Field("thermostat_type_id") String thermostatTypeId,
                                                     @Field("authorization_code") String authorizationCode) {
        return  delegate.returningResponse(null).createUserThermostat(myToken, thermostatTypeId, authorizationCode);
    }

    @DELETE("/thermostats/{token_id}")
//    @FormUrlEncoded
    public Call<CloudMessage> deleteUserThermostat(@Header("Authorization") String myToken,
                                                   @Path("token_id") String tokenID) {
        return  delegate.returningResponse(null).deleteUserThermostat(myToken, tokenID);
    }

//========================not yet implemented========================

// Update Thermostat Climate Settings.  This is a new API
//    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
//    @POST("/thermostats")
//    @FormUrlEncoded
//    Call<CloudMessage> createUserThermostat(@Header("Authorization") String myToken,
//                                        @Field("thermostat_type_id") String thermostatTypeId,
//                                        @Field("authorization_code") String authorizationCode);


    @POST("/thermostats/links")
    @FormUrlEncoded
    public Call<CloudMessage> linkThermostatToDevice(@Header("Authorization") String myToken,
                                                     @Field("device_id") String deviceID,
                                                     @Field("thermostat_id") String thermostatID) {
        return  delegate.returningResponse(null).linkThermostatToDevice(myToken, deviceID, thermostatID);
    }

    @GET("/thermostats/links")
    @FormUrlEncoded
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

    @DELETE("/thermostats/links/{thermostat_id}/{device_id}")
//    @FormUrlEncoded
    public Call<CloudMessage> deleteThermostatLinkToDevice(@Header("Authorization") String myToken,
                                                           @Path("thermostat_id") String thermostatID,
                                                           @Path("device_id") String deviceID) {
        return  delegate.returningResponse(null).deleteThermostatLinkToDevice(myToken, thermostatID, deviceID);
    }
//========================not yet implemented========================

    // Supported FW keys
    //    Old GS1011 Haiku 	    FW000003 	3
    //    Haiku Wall Control 	FW000004 	4
    //    Haiku L Series 	    FW000005 	5
    //    Haiku Light 	        FW000006 	6
    //    Haiku H/I Series 	    FW000007 	7
    // source:
    // file:///C:/gitdev/controls/doc/Big%20Ass%20Solutions%20Wi-Fi%20Ecosystem%20Protocol%20Specification.html
    @GET("/firmware")
    public Call<BASFirmwareInfo> getFirmwareDownloadUrlFromToken(@Header("Authorization") String myToken,
                                                                 @Query("firmware_key") String fwKey) {
        return  delegate.returningResponse(null).getFirmwareDownloadUrlFromToken(myToken, fwKey);
    }



    @DELETE("/users/{user_id}")
//    @FormUrlEncoded
    public Call<CloudMessage> userDelete(@Header("Authorization") String myToken,
                                         @Path("user_id") String userID) {
        return  delegate.returningResponse(null).userDelete(myToken, userID);
    }

}
