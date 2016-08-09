package operator.transform;

import rx.Observable;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

import java.util.ArrayList;

/**
 * Created by tlh on 2016/8/9.
 */
public class FlatMap {
    public static void main(String[] args) {
//        flatMapSimple();
//        flatMapComplete();
//        flatMapIterable();
        map();
    }

    private static void map() {
        Observable.just(1, 2, 3, 4, 5)
                .map(integer -> "map:" + integer)
                .subscribe(System.out::println);
    }

    private static void flatMapIterable() {
        Observable.just(1, 2, 3, 4, 5).flatMapIterable(integer -> {
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int i = 0; i < integer; i++)
                arrayList.add(integer);
            return arrayList;
        }).subscribe(System.out::println);
    }

    private static void flatMapSimple() {
        Observable.just(1, 2, 3, 4, 5).flatMap(integer -> Observable.just("FlatMap:" + integer))
                .subscribe(System.out::println);
    }

    private static void flatMapComplete() {
        Observable.just(1, 2, 3, 4, 5)
                .flatMap(integer -> {
                    if (integer == 3) throw new RuntimeException(String.valueOf(integer));
                    return Observable.just("FlatMap:" + integer);
                })
                .flatMap(Observable::just,
                        throwable -> Observable.just("Error:" + throwable.getMessage()),
                        (Func0<Observable<?>>) () -> null
                )
                .subscribe(System.out::println);
    }
}
