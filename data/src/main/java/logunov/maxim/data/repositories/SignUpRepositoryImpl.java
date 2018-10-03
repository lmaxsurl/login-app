package logunov.maxim.data.repositories;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import logunov.maxim.data.entity.UserResponse;
import logunov.maxim.data.network.RestService;
import logunov.maxim.domain.entity.User;
import logunov.maxim.domain.entity.UserSignUp;
import logunov.maxim.domain.repositories.SignUpRepository;

public class SignUpRepositoryImpl implements SignUpRepository {

    private RestService restService;

    @Inject
    public SignUpRepositoryImpl(RestService restService) {
        this.restService = restService;
    }

    @Override
    public Observable<User> signUp(UserSignUp user) {
        return restService
                .signUp(user)
                .map(new Function<UserResponse, User>() {
                    @Override
                    public User apply(UserResponse userResponse) {
                        return mapUser(userResponse);
                    }
                });
    }

    private User mapUser(UserResponse userResponse) {
        return new User(userResponse.getLogin(), userResponse.getUserId());
    }
}
