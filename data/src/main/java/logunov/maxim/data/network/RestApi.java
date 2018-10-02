package logunov.maxim.data.network;

import io.reactivex.Completable;
import logunov.maxim.domain.entity.User;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestApi {

    @POST("signup")
    Completable signUp(@Body User user);

}
