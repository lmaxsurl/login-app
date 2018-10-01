package logunov.maxim.data.network;

import com.google.gson.Gson;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import logunov.maxim.domain.entity.AppError;
import logunov.maxim.domain.entity.ErrorType;
import retrofit2.HttpException;

public class ErrorParserTransformer {

    private Gson gson;

    public ErrorParserTransformer(Gson gson) {
        this.gson = gson;
    }

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
                                    error = new AppError(httpException.getMessage(),
                                            ErrorType.SERVER_ERROR);

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
