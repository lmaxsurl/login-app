package logunov.maxim.loginapp.presentation.base;

public abstract class BaseRouter<A extends BaseActivity> {

    protected A activity;

    public BaseRouter(A activity) {
        this.activity = activity;
    }
}
