package logunov.maxim.domain.usecases;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import logunov.maxim.domain.executors.ExecutionThread;
import logunov.maxim.domain.executors.PostExecutionThread;

public abstract class BaseUseCase {

    protected Scheduler executionThread;
    protected Scheduler postExecutionThread;

    public BaseUseCase(Scheduler executionThread, Scheduler postExecutionThread) {
        this.executionThread = executionThread;
        this.postExecutionThread = postExecutionThread;
    }

    public BaseUseCase(ExecutionThread threadExecutor, PostExecutionThread postExecutionThread){
        this.executionThread = Schedulers.from(threadExecutor);
        this.postExecutionThread = postExecutionThread.getScheduler();
    }

    public BaseUseCase(PostExecutionThread postExecutionThread){
        this.executionThread = Schedulers.io();
        this.postExecutionThread = postExecutionThread.getScheduler();
    }

}
