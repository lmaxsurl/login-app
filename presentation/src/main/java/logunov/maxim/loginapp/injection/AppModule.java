package logunov.maxim.loginapp.injection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import logunov.maxim.data.network.RestService;
import logunov.maxim.data.repositories.SignUpRepositoryImpl;
import logunov.maxim.domain.executors.PostExecutionThread;
import logunov.maxim.domain.repositories.SignUpRepository;
import logunov.maxim.loginapp.executor.UIThread;

@Module
public class AppModule {

    @Provides
    @Singleton
    public static SignUpRepository provideSignUpRepository(RestService restService){
        return new SignUpRepositoryImpl(restService);
    }

    @Provides
    @Singleton
    public static RestService provideRestService(){
        return new RestService();
    }

    @Provides
    @Singleton
    public static PostExecutionThread provideUIThread(UIThread uiThread){
        return uiThread;
    }

}
