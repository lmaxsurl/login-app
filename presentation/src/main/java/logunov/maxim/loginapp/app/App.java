package logunov.maxim.loginapp.app;

import android.app.Application;
import com.squareup.leakcanary.LeakCanary;
import logunov.maxim.loginapp.injection.AppComponent;
import logunov.maxim.loginapp.injection.DaggerAppComponent;

public class App extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent(){
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .build();
        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);
    }
}
