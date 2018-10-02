package logunov.maxim.data.repositories;

import javax.inject.Inject;

import io.reactivex.Completable;
import logunov.maxim.data.network.RestService;
import logunov.maxim.domain.entity.User;
import logunov.maxim.domain.repositories.SignUpRepository;

public class SignUpRepositoryImpl implements SignUpRepository {

    private RestService restService;

    @Inject
    public SignUpRepositoryImpl(RestService restService) {
        this.restService = restService;
    }

    @Override
    public Completable signUp(User user) {
        return restService.signUp(user);
    }
}
