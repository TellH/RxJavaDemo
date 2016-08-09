package operator.combine;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by tlh on 2016/8/9.
 */
public class Zip {
    private Observable<String> zipWithObserver() {
        return createObserver(2).zipWith(createObserver(3), (s1, s2) -> s1 + "-" + s2);
    }

    private Observable<String> zipWithIterableObserver() {
        return Observable
                .zip(createObserver(2), createObserver(3), createObserver(4), (s1, s2, s3) -> s1 + "-" + s2 + "-" + s3);
    }

    private Observable<String> createObserver(int index) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 1; i <= index; i++) {
                    System.out.println("emitted:" + index + "-" + i);
                    subscriber.onNext(index + "-" + i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
