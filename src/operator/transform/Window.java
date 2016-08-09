package operator.transform;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by tlh on 2016/8/9.
 */
public class Window {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6)
                .window(3)
                .subscribe(observable -> {
                    System.out.println("new window:");
                    observable.subscribe(System.out::println);
                });
    }
}
