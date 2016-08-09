package operator.combine;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by tlh on 2016/8/9.
 */
public class Merge {
    private static Observable<Integer> mergeObserver() {
        return Observable.merge(Observable.just(1, 2, 3), Observable.just(4, 5, 6));
    }

    private static Observable<Integer> mergeDelayErrorObserver() {
        return Observable.mergeDelayError(Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                    if (i == 3) {
                        subscriber.onError(new Throwable("error"));
                    }
                    subscriber.onNext(i);
                }
            }
        }), Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                    subscriber.onNext(5 + i);
                }
                subscriber.onCompleted();
            }
        }));
    }

    public static void main(String[] args) {

    }
}
