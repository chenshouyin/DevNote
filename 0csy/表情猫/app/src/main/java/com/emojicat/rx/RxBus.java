package com.emojicat.rx;

/**
 * Created by chenshouyin on 17/4/25.
 */

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by csy on 2017/04/25.
 *
 * use:RxBus.getDefault().send(new TapEvent());

 *
 */
public class RxBus {

    private static volatile RxBus mDefaultInstance;

    private RxBus() {
    }

    public static RxBus getDefault() {
        if (mDefaultInstance == null) {
            synchronized (RxBus.class) {
                if (mDefaultInstance == null) {
                    mDefaultInstance = new RxBus();
                }
            }
        }
        return mDefaultInstance;
    }

    private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

    public void send(Object o) {
        _bus.onNext(o);
    }

    public Observable<Object> toObservable() {
        return _bus;
    }


//接收
//    RxBus.getDefault().toObservable().subscribe(new Action1<Object>() {
//        @Override
//        public void call(Object event) {
//            if (event instanceof TapEvent) {
//                //do something
//            }else if(event instanceof otherEvent){
//                //do otherthing
//            }
//        }
//    });
}