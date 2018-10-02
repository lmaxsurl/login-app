package logunov.maxim.loginapp.presentation.screens;

import android.databinding.ObservableField;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import logunov.maxim.domain.entity.User;
import logunov.maxim.domain.usecases.SignUpUseCase;
import logunov.maxim.loginapp.app.App;
import logunov.maxim.loginapp.presentation.base.BaseViewModel;

public class MainActivityViewModel extends BaseViewModel<MainActivityRouter> {

    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");

    @Inject
    public SignUpUseCase signUpUseCase;

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    public void onClick(){
        signUpUseCase
                .signUp(new User(email.get(), password.get()))
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onComplete() {
                        router.showErrorMessage("DONE!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        router.showErrorMessage(e.getLocalizedMessage());
                    }
                });
    }
}
