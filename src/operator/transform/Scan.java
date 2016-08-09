package operator.transform;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;

/**
 * Created by tlh on 2016/8/9.
 */
public class Scan {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5)
                .scan(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer sum, Integer item) {
                        return sum + item;
                    }
                }).subscribe(System.out::println);
    }
}
