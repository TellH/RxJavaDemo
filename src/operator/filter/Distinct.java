package operator.filter;

import rx.Observable;

/**
 * Created by tlh on 2016/8/9.
 */
public class Distinct {
    public static void main(String[] args) {
//        distinct();
        Observable.just(1, 2, 3, 4, 5, 4, 3, 1, 1,11).distinctUntilChanged().subscribe(System.out::println);
    }

    private static void distinct() {
        Observable.just(1, 2, 3, 4, 5, 4, 3, 1, 1,11).distinct().subscribe(System.out::println);
    }
}
