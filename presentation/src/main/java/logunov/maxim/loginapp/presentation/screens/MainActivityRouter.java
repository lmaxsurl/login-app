package logunov.maxim.loginapp.presentation.screens;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import logunov.maxim.loginapp.R;
import logunov.maxim.loginapp.presentation.base.BaseRouter;

public class MainActivityRouter extends BaseRouter<MainActivity> {

    private ArrayAdapter<String> adapter;
    private List<String> domains = Arrays.asList("gmail.com", "gmail.co.ua", "mail.ru", "tut.by",
            "yahoo.com", "yandex.ru", "yandex.by");

    public MainActivityRouter(MainActivity activity) {
        super(activity);
        adapter = new ArrayAdapter<>(
                activity, R.layout.support_simple_spinner_dropdown_item, new ArrayList<String>());
    }

    private void setItems(List<String> items){
        adapter.addAll(items);
        adapter.notifyDataSetChanged();
    }

    public ArrayAdapter<String> getAdapter() {
        return adapter;
    }

    public void generateHints(String s) {
        adapter.clear();
        List<String> hints = new ArrayList<>();
        if(s.endsWith("@")){
            for(String domain : domains){
                hints.add(s + domain);
            }
        } else if(s.contains("@")){
            String[] loginAndDomain = s.split("@");
            for(String domain: domains){
                if(domain.startsWith(loginAndDomain[1]))
                    hints.add(loginAndDomain[0] + "@" + domain);
            }
        }
        setItems(hints);
    }
}
