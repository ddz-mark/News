package com.dudaizhong.news.base.utils.rxUtils;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * RxBus
 * Created by YoKeyword on 2015/6/17.
 */
public class RxBus {

    // 主题
    private final Subject<Object, Object> bus;

    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    private RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    // 静态内部类
    private static class SingletonHolder {
        public final static RxBus sInstance = new RxBus();
    }

    // 单例RxBus
    public static RxBus getDefault() {
        return SingletonHolder.sInstance;
    }

    // 提供了一个新的事件
    public void post(Object o) {
        bus.onNext(o);
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Observable<T> toObserverable(Class<T> eventType) {
        return bus.ofType(eventType);
    }
}

