package retrofitstackoverflow.android.vogella.com.retrofitexample;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zfreeman on 1/6/17.
 */

public class TestNetComponent implements NetComponent {
    @Override
    public Retrofit retrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://test.com")
                .build();
        return retrofit;
    }
    @Override
    public OkHttpClient okHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .dispatcher(new Dispatcher(new AbstractExecutorService() {
                    private boolean shutingDown = false;
                    private boolean terminated = false;
                    @Override
                    public void shutdown() {
                        this.shutingDown = true;
                        this.terminated = true;
                    }

                    @NonNull
                    @Override
                    public List<Runnable> shutdownNow() {
                        return new ArrayList<>();
                    }

                    @Override
                    public boolean isShutdown() {
                        return this.shutingDown;
                    }

                    @Override
                    public boolean isTerminated() {
                        return this.terminated;
                    }

                    @Override
                    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
                        return false;
                    }

                    @Override
                    public void execute(Runnable command) {
                        command.run();
                    }
                }))
                .build();
        return client;
    }
    @Override
    public SharedPreferences sharedPreferences() {
        return Mockito.mock(SharedPreferences.class);
    }
}
