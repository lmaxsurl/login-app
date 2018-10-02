package logunov.maxim.loginapp.injection;

import javax.inject.Singleton;
import dagger.Component;
import logunov.maxim.loginapp.presentation.screens.MainActivityViewModel;

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    void runInject(MainActivityViewModel mainActivityViewModel);
}
