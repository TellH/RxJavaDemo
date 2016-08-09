package operator.transform;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 * Created by tlh on 2016/8/9.
 */
public class GroupBy {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5)
                .groupBy(integer -> integer % 2 == 0)
                .subscribe(groupedObservable -> {
                    if (groupedObservable.getKey() == Boolean.TRUE)
                        groupedObservable.subscribe(integer -> {
                            System.out.println("偶数:" + integer);
                        });
                    else groupedObservable.subscribe(integer -> {
                        System.out.println("奇数:" + integer);
                    });
//                    groupedObservable.count().subscribe(integer -> {
//                        System.out.println(groupedObservable.getKey() + " contains " + integer + "members");
//                    });
                });

    }
}
