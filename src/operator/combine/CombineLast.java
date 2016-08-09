package operator.combine;

import rx.Observable;
import rx.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tlh on 2016/8/9.
 */
public class CombineLast {
    private static Observable<Integer> createObserver(int index) {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 1; i < 6; i++) {
                    subscriber.onNext(i * index);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private static Observable<Integer> combineLatestObserver() {
        return Observable.combineLatest(createObserver(1), createObserver(2), (num1, num2) -> {
            System.out.println("left:" + num1 + " right:" + num2);
            return num1 + num2;
        });
    }


    private static Observable<Integer> combineListObserver() {
        List<Observable<Integer>> list = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            list.add(createObserver(i));
        }
        return Observable.combineLatest(list, args -> {
            int temp = 0;
            for (Object i : args) {
                temp += (Integer) i;
            }
            return temp;
        });
    }
    public static void main(String[] args) {
        combineListObserver()
                .subscribe(System.out::println);
    }
}
