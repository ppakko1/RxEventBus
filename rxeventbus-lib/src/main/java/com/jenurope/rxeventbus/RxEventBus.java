package com.jenurope.rxeventbus;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by jinwoopark on 2017. 6. 8..
 */

public class RxEventBus {
    private static volatile  RxEventBus rxEventBus;

    private final Subject<Object> subject;

    private RxEventBus() {
        subject = PublishSubject.create();
    }

    public static RxEventBus getInstance() {
        if (rxEventBus == null) {
            synchronized (RxEventBus.class) {
                if (rxEventBus == null) {
                    rxEventBus = new RxEventBus();
                }
            }
        }
        return rxEventBus;
    }

    public <E> void post(E event) {
        subject.onNext(event);
    }

    public <E> Observable<E> event(Class<E> tClass) {
        return subject.ofType(tClass);
    }

}
