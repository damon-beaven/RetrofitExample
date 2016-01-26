package retrofitstackoverflow.android.vogella.com.retrofitexample;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAccessToken;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserInfo;

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

//    Call<BASAccessToken> loginUser(@Body BASAuthInfo user);
}
