package retrofitstackoverflow.android.vogella.com.retrofitexample;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends ListActivity implements Callback<BASAccessToken> {

    private static final String TAG = MainActivity.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        ArrayAdapter<Question> arrayAdapter =
                new ArrayAdapter<Question>(this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        new ArrayList<Question>());
        setListAdapter(arrayAdapter);
        setProgressBarIndeterminateVisibility(true);
        setProgressBarVisibility(true);
    }

    private void doIt() {

        BASAuthInfo basAuthInfo = new BASAuthInfo();

        // Define the interceptor, add authentication headers
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader("User-Agent", "Retrofit-Sample-App").build();
                return chain.proceed(newRequest);
            }
        };


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient();
        // add your other interceptors �
        httpClient.interceptors().add(interceptor);
        // add logging as last interceptor
        httpClient.interceptors().add(logging);  // <-- this is the important line!

        setProgressBarIndeterminateVisibility(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.bigassfans.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        // prepare call in Retrofit 2.0
        BASCloudAPI basCloudAPI = retrofit.create(BASCloudAPI.class);

        Call<BASAccessToken> call = basCloudAPI.loginUser(basAuthInfo);
        //asynchronous call
        call.enqueue(this);

//        // prepare call in Retrofit 2.0
//        StackOverflowAPI stackOverflowAPI = retrofit.create(StackOverflowAPI.class);
//
//        Call<StackOverflowQuestions> call = stackOverflowAPI.loadQuestions("android");
//        //asynchronous call
//        call.enqueue(this);

        // synchronous call would be with execute, in this case you
        // would have to perform this outside the main thread
        // call.execute()

        // to cancel a running request
        // call.cancel();
        // calls can only be used once but you can easily clone them
        //Call<StackOverflowQuestions> c = call.clone();
        //c.enqueue(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return true;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        doIt();
        return true;
    }

    @Override
    public void onResponse(Response<BASAccessToken> response, Retrofit retrofit) {
//        setProgressBarIndeterminateVisibility(false);
//        ArrayAdapter<Question> adapter = (ArrayAdapter<Question>) getListAdapter();
//        adapter.clear();
//        adapter.addAll(response.body().items);

        Toast.makeText(this.getApplicationContext(), response.message(), Toast.LENGTH_LONG);
    }

//    @Override
//    public void onResponse(Response<StackOverflowQuestions> response, Retrofit retrofit) {
//        setProgressBarIndeterminateVisibility(false);
//        ArrayAdapter<Question> adapter = (ArrayAdapter<Question>) getListAdapter();
//        adapter.clear();
//        adapter.addAll(response.body().items);
//    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
}