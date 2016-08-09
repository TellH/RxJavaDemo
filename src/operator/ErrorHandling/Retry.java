package operator.ErrorHandling;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

import java.util.concurrent.TimeUnit;

/**
 * Created by tlh on 2016/8/9.
 */
public class Retry {
    private static Observable<Integer> retryObserver() {
        return createObserver().retry(2);
    }

    private static Observable<Integer> retryWhenObserver() {
//        createObserver().retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
//            @Override
//            public Observable<?> call(Observable<? extends Throwable> observable) {
//                return observable.zipWith(Observable.just(1, 2, 3),
//                        (throwable, integer) -> throwable.getMessage() + integer)
//                        .flatMap(throwableMsg -> {
//                            System.out.println(throwableMsg);
//                            return Observable.timer(1, TimeUnit.SECONDS);
//                        });
//            }
//        });
        return createObserver().retryWhen(observable -> observable.zipWith(Observable.just(1, 2, 3),
                (throwable, integer) -> throwable.getMessage() + integer)
                .flatMap(throwableStr -> {
                    System.out.println(throwableStr);
                    return Observable.empty();
                }));
    }

    private static Observable<Integer> createObserver() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                System.out.println("subscribe");
                for (int i = 0; i < 3; i++) {
                    if (i == 2) {
                        subscriber.onError(new Exception("Exception-"));
                    } else {
                        subscriber.onNext(i);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        retryWhenObserver().subscribe(System.out::println);
    }
}
