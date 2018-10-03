package logunov.maxim.loginapp.presentation.screens;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.Editable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import logunov.maxim.domain.entity.User;
import logunov.maxim.domain.entity.UserSignUp;
import logunov.maxim.domain.usecases.SignUpUseCase;
import logunov.maxim.loginapp.app.App;
import logunov.maxim.loginapp.presentation.base.BaseViewModel;

public class MainActivityViewModel extends BaseViewModel<MainActivityRouter> {

    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");
    public ObservableField<String> error = new ObservableField<>("");
    public ObservableBoolean logo = new ObservableBoolean(true);
    private boolean emailError = false;
    private boolean passwordError = false;

    private Consumer<User> doOnNext = new Consumer<User>() {
        @Override
        public void accept(User user) {
            //do something with data and show that user sign up successfully
        }
    };

    private Consumer<Throwable> doOnError = new Consumer<Throwable>() {
        @Override
        public void accept(Throwable e) {
            error.set(e.getMessage());
        }
    };

    @Inject
    public SignUpUseCase signUpUseCase;

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    public void onClick() {
        if (!emailError && !passwordError)
            getCompositeDisposable().add(
                    signUpUseCase
                            .signUp(email.get(), password.get())
                            .subscribe(doOnNext, doOnError));
    }

    public void afterEmailChanged(Editable s) {
        clearError();
        router.generateHints(s.toString());
        emailError = !checkEmail(s.toString());
    }

    public void afterPasswordChanged(Editable s) {
        clearError();
        passwordError = !checkPassword(s.toString());

    }

    private void clearError() {
        error.set("");
    }

    private boolean checkPassword(String s) {
        if (s.length() == 0) {
            error.set("Поля не должны быть пустыми!");
            return false;
        }
        if (8 > s.length() || s.length() > 500) {
            error.set("Пароль должен состоять из 8-500 символов!");
            return false;
        }
        return true;
    }

    private boolean checkEmail(String s) {
        if (s.length() == 0) {
            error.set("Поля не должны быть пустыми!");
            return false;
        }
        if (4 > s.length() || s.length() > 32) {
            error.set("Длина должна быть от 4 до 32 символов!");
            return false;
        }
        if (!s.contains("@")) {
            error.set("Пропущен символ @!");
            return false;
        }
        if (s.startsWith("@")) {
            error.set("Пропущено имя почтового адреса!");
            return false;
        }
        if (s.endsWith("@")) {
            error.set("Пропущено доменное имя сервера!");
            return false;
        }
        String[] arr = s.split("@");
        if (arr.length != 2) {
            error.set("Введено несколько символов '@'");
            return false;
        }
        if (arr[0].endsWith(".") || arr[1].startsWith(".")) {
            error.set("Точка не должна стоять радом с '@'");
            return false;
        }
        String buff = arr[1];
        arr = buff.split("\\.");
        if (arr.length < 2) {
            error.set("Неверно доменное имя сервера!");
            return false;
        }
        if (arr[0].length() < 2 || arr[1].length() < 2) {
            error.set("Неверно доменное имя сервера!");
            return false;
        }
        return true;
    }
}
