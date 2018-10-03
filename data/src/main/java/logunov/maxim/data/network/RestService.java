package logunov.maxim.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Observable;
import logunov.maxim.data.BuildConfig;
import logunov.maxim.data.entity.UserResponse;
import logunov.maxim.domain.entity.UserSignUp;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class RestService {

    private RestApi restApi;
    private Gson gson;
    private ErrorParserTransformer errorParserTransformer;
    private final String URL = "http://junior.balinasoft.com/api/account/";
    private final int TIMEOUT = 10;

    @Inject
    public RestService() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient
                .Builder()
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS);

        // add logs when it's debug
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpBuilder.addInterceptor(logging);
        }

        gson = new GsonBuilder()
                .create();

        this.restApi = new Retrofit
                .Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(URL)
                .client(okHttpBuilder.build())
                .build()
                .create(RestApi.class);

        errorParserTransformer = new ErrorParserTransformer();
    }

    public Observable<UserResponse> signUp(UserSignUp user) {
        return restApi
                .signUp(user)
                .compose(errorParserTransformer.<UserResponse, Throwable>parseHttpError());
    }
}
