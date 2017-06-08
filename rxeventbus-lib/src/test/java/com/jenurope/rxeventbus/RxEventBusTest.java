package com.jenurope.rxeventbus;

import org.junit.Test;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RxEventBusTest {
    @Test
    public void post_subscribe_test1() throws Exception {
        Disposable disposable1 = RxEventBus.getInstance()
                .event(Event1.class)
                .subscribe(new Consumer<Event1>() {
                    @Override
                    public void accept(@NonNull Event1 event1) throws Exception {
                        assertTrue(event1.getText().equals(new Event1().getText()));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        assertTrue(false);
                    }
                });

        RxEventBus.getInstance().post(new Event1());
        RxEventBus.getInstance().post(new Event2());

        disposable1.dispose();
    }

    @Test
    public void post_subscribe_test2() throws Exception {
        Disposable disposable2 = RxEventBus.getInstance()
                .event(Event2.class)
                .subscribe(new Consumer<Event2>() {
                    @Override
                    public void accept(@NonNull Event2 event2) throws Exception {
                        assertTrue(event2.getText().equals(new Event2().getText()));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        assertTrue(false);
                    }
                });

        RxEventBus.getInstance().post(new Event1());
        RxEventBus.getInstance().post(new Event2());

        disposable2.dispose();
    }

    @Test
    public void disposeTest() throws Exception {
        Disposable disposable = RxEventBus.getInstance()
                .event(Event1.class)
                .subscribe();

        RxEventBus.getInstance().post(new Event1());

        assertFalse(disposable.isDisposed());
        disposable.dispose();
        assertTrue(disposable.isDisposed());
    }

    private class Event1 {
        private String text = "event1";

        public String getText() {
            return text;
        }
    }

    private class Event2 {
        private String text = "event2";

        public String getText() {
            return text;
        }
    }
}