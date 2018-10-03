package logunov.maxim.data.network;

import io.reactivex.Completable;
import io.reactivex.Observable;
import logunov.maxim.data.entity.UserResponse;
import logunov.maxim.domain.entity.UserSignUp;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestApi {

    @POST("signup")
    Observable<UserResponse> signUp(@Body UserSignUp user);

}
