package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import retrofit.Call;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAccessToken;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.CloudMessage;

/**
 * Created by dbeaven on 1/25/2016.
 */
public interface BASCloudAPI {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

//    @GET("/users/{username}")
//    Call<User> getUser(@Path("username") String username);
//
    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
    @GET("/me")
    Call<BASUserInfo> userInfo(@Header("Authorization") String myToken);

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
    Call<BASAccessToken> getCreateUserToken(@Field("client_id") String clientId,
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


    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
    @POST("/users")
    @FormUrlEncoded
    Call<BASUserInfo> userCreate(@Header("Authorization") String myToken,
                                 @Field("password") String password,
                                 @Field("email") String email,
                                 @Field("first_name") String firstName,
                                 @Field("last_name") String lastName);

    @Headers({"Accept: application/vnd.bigassfans.v1+json"})
    @DELETE("/users/{user_id}")
//    @FormUrlEncoded
    Call<CloudMessage> userDelete(@Header("Authorization") String myToken,
                                 @Path("user_id") String userID);

//    Call<BASAccessToken> loginUser(@Body BASAuthInfo user);
}
