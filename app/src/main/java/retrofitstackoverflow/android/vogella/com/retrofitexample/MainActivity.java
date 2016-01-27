package retrofitstackoverflow.android.vogella.com.retrofitexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.Response;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASCloudTask;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASUserAuth;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASUserRead;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAccessToken;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserInfo;

public class MainActivity extends Activity { //implements Callback<BASAccessToken> {
//    private String baseURL = "https://dev.sensemecloud.com"; // dev
//    private String mBaseURL = "https://api.bigassfans.com";  // production
    private String mBaseURL;
    private String successString = "Success...>> ";
    private String failureString = "Failure...>> ";
    private String returncodeString = "Return code...>> ";
    protected BASAccessToken myToken = new BASAccessToken();
    protected BASUserInfo myUserInfo = new BASUserInfo();
    private BASAuthInfo basAuthInfo = new BASAuthInfo();

    private static final String TAG = MainActivity.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    private void doAuth() {
//
//
////        // Define the interceptor, add authentication headers
////        Interceptor interceptor = new Interceptor() {
////            @Override
////            public Response intercept(Chain chain) throws IOException {
////                Request newRequest = chain.request().newBuilder().addHeader("User-Agent", "Retrofit-Sample-App").build();
////                return chain.proceed(newRequest);
////            }
////        };
//
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        // set your desired log level
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient httpClient = new OkHttpClient();
//        // add your other interceptors
////        httpClient.interceptors().add(interceptor);
//        // add logging as last interceptor
//        httpClient.interceptors().add(logging);  // <-- this is the important line!
//
//        setProgressBarIndeterminateVisibility(true);
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(httpClient)
//                .build();
//
//        // prepare call in Retrofit 2.0
//        BASCloudAPI basCloudAPI = retrofit.create(BASCloudAPI.class);
//
//        Call<BASAccessToken> call = basCloudAPI.loginUser(
//                basAuthInfo.client_id,
//                basAuthInfo.client_secret,
//                basAuthInfo.grant_type,
//                basAuthInfo.scope,
//                basAuthInfo.email,
//                basAuthInfo.password);
//        //asynchronous call
//        call.enqueue(this);
//
//        // synchronous call would be with execute, in this case you
//        // would have to perform this outside the main thread
//        // call.execute()
//
//        // to cancel a running request
//        // call.cancel();
//        // calls can only be used once but you can easily clone them
//        //Call<StackOverflowQuestions> c = call.clone();
//        //c.enqueue(this);
//    }

//    private void doUserInfo() {
//
//        BASAuthInfo basAuthInfo = new BASAuthInfo();
//
////        // Define the interceptor, add authentication headers
////        Interceptor interceptor = new Interceptor() {
////            @Override
////            public Response intercept(Chain chain) throws IOException {
////                Request newRequest = chain.request().newBuilder().addHeader("User-Agent", "Retrofit-Sample-App").build();
////                return chain.proceed(newRequest);
////            }
////        };
//
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        // set your desired log level
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient httpClient = new OkHttpClient();
//        // add your other interceptors
////        httpClient.interceptors().add(interceptor);
//        // add logging as last interceptor
//        httpClient.interceptors().add(logging);  // <-- this is the important line!
//
//        setProgressBarIndeterminateVisibility(true);
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(httpClient)
//                .build();
////        .baseUrl("https://api.bigassfans.com")
//
//        // prepare call in Retrofit 2.0
//        BASCloudAPI basCloudAPI = retrofit.create(BASCloudAPI.class);
//
//        Call<BASUserInfo> call = basCloudAPI.userInfo("Bearer " + myToken.getAccess_token());
//        //asynchronous call
////        call.enqueue(this);
//        call.enqueue(new Callback<BASUserInfo>() {
//            @Override
//            public void onResponse(Response<BASUserInfo> response,
//                                   Retrofit retrofit) {
//                updateTextViewFromResponse(response);
////                updateTextView("Success...", response.body().toString());
//
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                updateTextView(failureString, t.getLocalizedMessage());
//            }
//        });
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return true;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_auth:
                doNewAuth();
                return true;
            case R.id.menu_userInfo:
                doNewUserInfo();
                return true;
            case R.id.menu_userDevices:
                doNewAuth();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void doNewAuth() {
        BASUserAuth userAuth = new BASUserAuth();
        mBaseURL = userAuth.getBaseURL();

        userAuth.execute(basAuthInfo, new BASCloudTask.CloudAsyncResponse() {

            @Override
            public void onCloudResponse(Response response) {
                Toast.makeText(MainActivity.this, "got it", Toast.LENGTH_SHORT).show();
                updateTextViewFromResponse(response);
                //you have to know what the object "should" be to do your cast
                if (response.body() != null) myToken = (BASAccessToken)response.body();
            }
        });
    }

    private void doNewUserInfo() {
        BASUserRead userRead = new BASUserRead();
        mBaseURL = userRead.getBaseURL();

        userRead.execute(basAuthInfo, new BASCloudTask.CloudAsyncResponse() {

            @Override
            public void onCloudResponse(Response response) {
                Toast.makeText(MainActivity.this, "got it", Toast.LENGTH_SHORT).show();
                updateTextViewFromResponse(response);
                //you have to know what the object "should" be to do your cast
                if (response.body() != null) myUserInfo = (BASUserInfo)response.body();
            }
        });
    }
//    @Override
//    public void onResponse(Response<BASAccessToken> response, Retrofit retrofit) {
////        updateTextView(successString, response.body().toString());
//        updateTextViewFromResponse(response);
//        if (response.body() != null) myToken = response.body();
//    }
//
//    @Override
//    public void onFailure(Throwable t) {
////        Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//        updateTextView(failureString, t.getLocalizedMessage());
//    }

    private void updateTextView(String text, String text2) {
        TextView myTitle = (TextView) findViewById(R.id.textView2);
        TextView myTextView = (TextView) findViewById(R.id.textView);

        myTitle.setText(text);
        myTextView.setText(text2);
    }

    private void updateTextViewFromResponse(Response response) {
        if (response.body() != null)
        {
            updateTextView(successString + mBaseURL, response.body().toString());
        }
        else {
            updateTextView(returncodeString + mBaseURL, response.message());
        }
    }
}