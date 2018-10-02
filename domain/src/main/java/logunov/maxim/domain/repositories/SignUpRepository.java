package logunov.maxim.domain.repositories;

import io.reactivex.Completable;
import logunov.maxim.domain.entity.User;

public interface SignUpRepository {

    Completable signUp(User user);

}
