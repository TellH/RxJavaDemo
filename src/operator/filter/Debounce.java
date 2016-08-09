package operator.filter;

import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.TimeUnit;

/**
 * Created by tlh on 2016/8/9.
 */
public class Debounce {
    public static void main(String[] args) {
//        throttleWithTimeout();
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).debounce(integer -> {
            return Observable.create(new Observable.OnSubscribe<Integer>() {
                @Override
                public void call(Subscriber<? super Integer> subscriber) {
                    if (integer % 2 == 0 && !subscriber.isUnsubscribed()) {
//                        System.out.println("complete:" + integer);
//                        subscriber.onNext(integer);
                        subscriber.onCompleted();
                    }
                    else System.out.println("filter" + integer);
                }
            });
        }).subscribe(System.out::println);
    }

    private static void throttleWithTimeout() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(i);
                    }
                    int sleep = 199;
                    if (i == 3) {
                        sleep = 300;
                    }
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                subscriber.onCompleted();
            }
        }).throttleWithTimeout(200, TimeUnit.MILLISECONDS).subscribe(System.out::println);
    }
}
