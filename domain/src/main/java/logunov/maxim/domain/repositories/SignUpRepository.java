package logunov.maxim.domain.repositories;

import io.reactivex.Observable;
import logunov.maxim.domain.entity.User;
import logunov.maxim.domain.entity.UserSignUp;

public interface SignUpRepository {

    Observable<User> signUp(UserSignUp user);

}
