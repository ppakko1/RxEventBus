package com.jenurope.rxeventbus.sample;

import android.os.Bundle;
import android.widget.TextView;

import com.jenurope.rxeventbus.RxEventBus;
import com.jenurope.rxeventbus.sample.event.Event1;
import com.jenurope.rxeventbus.sample.event.Event2;
import com.jenurope.rxeventbus.sample.event.Event3;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends RxAppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textview);

        findViewById(R.id.button1).setOnClickListener(v -> {
            RxEventBus.getInstance().post(new Event1());
        });

        findViewById(R.id.button2).setOnClickListener(v -> {
            RxEventBus.getInstance().post(new Event2());
        });

        findViewById(R.id.button3).setOnClickListener(v -> {
            RxEventBus.getInstance().post(new Event3());
        });

        observeEvent();
    }

    private void observeEvent() {
        RxEventBus.getInstance()
                .event(Event1.class)
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(event1 -> {
                    textView.setText(event1.getText());
                }, Throwable::printStackTrace);

        RxEventBus.getInstance()
                .event(Event2.class)
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(event2 -> {
                    textView.setText(event2.getText());
                }, Throwable::printStackTrace);

        RxEventBus.getInstance()
                .event(Event3.class)
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(event3 -> {
                    textView.setText(event3.getText());
                }, Throwable::printStackTrace);
    }
}
