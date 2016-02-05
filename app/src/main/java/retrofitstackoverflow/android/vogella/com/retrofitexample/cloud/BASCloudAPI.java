package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
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
 * Created by dbeaven on 1/25/2016.
 */
public interface BASCloudAPI {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

//    @GET("/users/{username}")
//    Call<User> getUserConfirmation(@Path("username") String username);
//
    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
    @GET("/me")
    Call<BASUserInfo> getUserInfo(@Header("Authorization") String myToken);

    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
    @GET("/devices")
    Call<BASDevices> getUserDevices(@Header("Authorization") String myToken);

    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
    @GET("/thermostats/types")
    Call<BASThermostatTypes> getThermostatTypes(@Header("Authorization") String myToken);

    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
    @GET("/thermostats")
    Call<BASThermostats> getUserThermostats(@Header("Authorization") String myToken);

    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
    @GET("/thermostats/links")
    Call<BASThermostatsLinkedDevicesInfo> getUserThermostatsLinkedDevices(@Header("Authorization") String myToken);

    @POST("/oauth/access_token")
    @FormUrlEncoded
    Call<BASAccessToken> loginUser(@Field("client_id") String clientId,
                                   @Field("client_secret") String clientSecret,
                                   @Field("grant_type") String grantType,
                                   @Field("scope") String scope,
                                   @Field("email") String email,
                                   @Field("password") String password);

    @POST("/oauth/access_token")
    @FormUrlEncoded
    Call<BASAccessToken> getResetPasswordToken(@Field("client_id") String clientId,
                                               @Field("client_secret") String clientSecret,
                                               @Field("grant_type") String grantType,
                                               @Field("scope") String scope);

    @POST("/oauth/access_token")
    @FormUrlEncoded
    Call<BASAccessToken> getCreateUserToken(@Field("client_id") String clientId,
                                   @Field("client_secret") String clientSecret,
                                   @Field("grant_type") String grantType,
                                   @Field("scope") String scope);

    @POST("/oauth/access_token")
    @FormUrlEncoded
    Call<BASAccessToken> getFirmwareDownloadToken(@Field("client_id") String clientId,
                                            @Field("client_secret") String clientSecret,
                                            @Field("grant_type") String grantType,
                                            @Field("scope") String scope);


    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
    @POST("/users")
    @FormUrlEncoded
    Call<BASUserInfo> createUserFromToken(@Header("Authorization") String myToken,
                                             @Field("email") String email,
                                             @Field("password") String password,
                                             @Field("first_name") String firstName,
                                             @Field("last_name") String lastName);

    // each @Field is optional on the cloud side, but we use them all
    // so we don't have to parse to figure out what was changed.
    // @Path userID is required
    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
    @PUT("/users/{user_id}")
    @FormUrlEncoded
    Call<BASUserInfo> updateUserFromId(@Header("Authorization") String myToken,
                                       @Path("user_id") String userID,
                                       @Field("email") String email,
                                       @Field("password") String password,
                                       @Field("first_name") String firstName,
                                       @Field("last_name") String lastName);

    // get create_user token from getCreateUserToken
    // confirmUserFromPin
    // will get a 32 char UUID (reset_token)
    // you will need the reset_token if you want to reset the password
    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
    @PUT("/users/confirm")
    @FormUrlEncoded
    Call<BASUserConfirmInfo> confirmUserFromPin(@Header("Authorization") String myToken,
                                       @Field("email") String email,
                                       @Field("pin") String pin);

    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
    @PUT("/users/password")
    @FormUrlEncoded
    Call<CloudMessage> resetUserPasswordFromResetToken(@Header("Authorization") String myToken,
                                                @Field("email") String email,
                                                @Field("pin") String pin,
                                                @Field("reset_token") String resetToken,
                                                @Field("password") String password);

    // get create_user token
    // create the user (registration)
    // getNewPinEmail
    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
    @POST("/users/pin")
    @FormUrlEncoded
    Call<CloudMessage> getNewPinEmail(@Header("Authorization") String myToken,
                                            @Field("email") String email);

    // get getResetPasswordToken for reset_password token
    // call getPasswordEmail with reset_password token
    // call confirmUserFromPin with email and pin
    // call resetUserPassword with email, pin, reset_token (from confirmUserFromPin), password
    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
    @POST("/users/pin")
    @FormUrlEncoded
    Call<CloudMessage> getResetPasswordEmail(@Header("Authorization") String myToken,
                                      @Field("email") String email);


//========================not yet implemented
    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
    @POST("/thermostats")
    @FormUrlEncoded
    Call<CloudMessage> createThermostat(@Header("Authorization") String myToken,
                                        @Field("thermostat_type_id") String thermostatTypeId,
                                        @Field("authorization_code") String authorizationCode);

    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
    @DELETE("/thermostats/{token_id}")
//    @FormUrlEncoded
    Call<CloudMessage> deleteThermostat(@Header("Authorization") String myToken,
                                        @Path("token_id") String tokenID);

    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
    @POST("/thermostats/links")
    @FormUrlEncoded
    Call<CloudMessage> linkThermostatToDevice(@Header("Authorization") String myToken,
                                              @Field("device_id") String deviceID,
                                              @Field("thermostat_id") String thermostatID);

    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
    @GET("/thermostats/links")
    @FormUrlEncoded
    Call<CloudMessage> getThermostatLinks(@Header("Authorization") String myToken,
                                              @Field("device_id") String deviceID,
                                              @Field("thermostat_id") String thermostatID);

    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
    @DELETE("/thermostats/links/{thermostat_id}/{device_id}")
//    @FormUrlEncoded
    Call<CloudMessage> deleteThermostatLinkToDevice(@Header("Authorization") String myToken,
                                                    @Path("thermostat_id") String thermostatID,
                                                    @Path("device_id") String deviceID);
//========================not yet implemented

    // Supported FW keys
    //    Old GS1011 Haiku 	    FW000003 	3
    //    Haiku Wall Control 	FW000004 	4
    //    Haiku L Series 	    FW000005 	5
    //    Haiku Light 	        FW000006 	6
    //    Haiku H/I Series 	    FW000007 	7
    // source:
    // file:///C:/gitdev/controls/doc/Big%20Ass%20Solutions%20Wi-Fi%20Ecosystem%20Protocol%20Specification.html
    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
    @GET("/firmware")
    Call<BASFirmwareInfo> getFirmwareDownloadUrlFromToken(@Header("Authorization") String myToken,
                                                     @Query("firmware_key") String fwKey);

    // todo: You can't actually create a user with just one call
    // but eventually we'd like BASCloudTask/BASCloudUser to expose
    // a single interface that will get the token and create the user
    // with just one method call.
    // Would like to do the same for any operation that requires
    // more than one single REST API.
    // getCreateUserToken
    // createUserFromToken
//    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
//    @POST("/users")
//    @FormUrlEncoded
//    Call<BASUserInfo> userCreate(@Header("Authorization") String myToken,
//                                 @Field("password") String password,
//                                 @Field("email") String email,
//                                 @Field("first_name") String firstName,
//                                 @Field("last_name") String lastName);

    // todo: You can't actually update a user with just one call
    // but eventually we'd like BASCloudTask/BASCloudUser to expose
    // a single interface that will get the token and update the user
    // with just one method call.
    // Would like to do the same for any operation that requires
    // more than one single REST API.
    // loginUser (for the bearer token)
    // getUserInfo (for the user ID)
    // updateUserFromToken (do I need to get an actual update token? NO...we can use the login bearer token.)
//    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
//    @POST("/users")
//    @FormUrlEncoded
//    Call<BASUserInfo> userUpdate(@Header("Authorization") String myToken,
//                                 @Field("password") String password,
//                                 @Field("email") String email,
//                                 @Field("first_name") String firstName,
//                                 @Field("last_name") String lastName);

    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
    @DELETE("/users/{user_id}")
//    @FormUrlEncoded
    Call<CloudMessage> userDelete(@Header("Authorization") String myToken,
                                 @Path("user_id") String userID);

//    Call<BASAccessToken> loginUser(@Body BASAuthInfo user);
    //create user
    // get create user token
    // create user
    // confirm user
}
