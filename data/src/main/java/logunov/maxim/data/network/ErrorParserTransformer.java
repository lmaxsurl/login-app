package logunov.maxim.data.network;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import logunov.maxim.domain.entity.AppError;
import logunov.maxim.domain.entity.ErrorType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ErrorParserTransformer {

    public <T, E extends Throwable> ObservableTransformer<T, T> parseHttpError() {

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {

                return upstream
                        .onErrorResumeNext(new Function<Throwable, ObservableSource<T>>() {
                            @Override
                            public ObservableSource<T> apply(Throwable throwable) {

                                AppError error;
                                if (throwable instanceof HttpException) {
                                    HttpException httpException = (HttpException) throwable;
                                    error = new AppError(throwable.getMessage(),
                                            ErrorType.SERVER_ERROR);
                                    try {
                                        if (httpException.response().errorBody().string().contains("login-already")) {
                                            error = new AppError("Данный логин уже занят",
                                                    ErrorType.INPUT_ERROR);
                                        } else if (httpException.response().errorBody().string().contains("login")) {
                                            error = new AppError("Проверьте правильность ввода логина",
                                                    ErrorType.INPUT_ERROR);
                                        }
                                    } catch (IOException e) {
                                        if (httpException.code() == 400) {
                                            error = new AppError("Проверьте введенные данные",
                                                    ErrorType.INPUT_ERROR);
                                        }
                                    }
                                } else if (throwable instanceof UnknownHostException) {
                                    error = new AppError("Server is not available",
                                            ErrorType.SERVER_IS_NOT_AVAILABLE);
                                } else if (throwable instanceof SocketTimeoutException
                                        || throwable instanceof ConnectException) {
                                    error = new AppError("Internet is not available",
                                            ErrorType.INTERNET_IS_NOT_AVAILABLE);
                                } else {
                                    error = new AppError("Unexpected error",
                                            ErrorType.UNEXPECTED_ERROR);
                                }
                                return Observable.error(error);
                            }
                        });
            }
        };
    }
}
