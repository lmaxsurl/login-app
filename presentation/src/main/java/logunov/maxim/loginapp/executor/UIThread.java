package logunov.maxim.loginapp.executor;


import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import logunov.maxim.domain.executors.PostExecutionThread;

@Singleton
public class UIThread implements PostExecutionThread {

    @Inject
    public UIThread(){
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
