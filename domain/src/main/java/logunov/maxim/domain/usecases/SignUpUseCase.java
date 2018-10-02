package logunov.maxim.domain.usecases;

import javax.inject.Inject;

import io.reactivex.Completable;
import logunov.maxim.domain.entity.User;
import logunov.maxim.domain.executors.PostExecutionThread;
import logunov.maxim.domain.repositories.SignUpRepository;

public class SignUpUseCase extends BaseUseCase {

    private SignUpRepository signUpRepository;

    @Inject
    public SignUpUseCase(PostExecutionThread postExecutionThread, SignUpRepository signUpRepository) {
        super(postExecutionThread);
        this.signUpRepository = signUpRepository;
    }

    public Completable signUp(User user){
        return signUpRepository
                .signUp(user)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
