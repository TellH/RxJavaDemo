package operator.ErrorHandling;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by tlh on 2016/8/9.
 */
public class Catch {
    private static Observable<String> onErrorReturnObserver() {
        return createObserver().onErrorReturn(throwable -> "An error has bean caught.");
    }

    private static Observable<String> onErrorResumeNextObserver() {
        return createObserver().onErrorResumeNext(Observable.just("7", "8", "9"));
    }

    private static Observable<String> createObserver() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 1; i <= 6; i++) {
                    if (i < 3) {
                        subscriber.onNext("onNext:" + i);
                    } else {
                        subscriber.onError(new Throwable("Throw error"));
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
//        onErrorReturnObserver()
        onErrorResumeNextObserver()
                .subscribe(System.out::println);
    }
}
