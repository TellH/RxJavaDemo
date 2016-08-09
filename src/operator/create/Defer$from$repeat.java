package operator.create;


import rx.Observable;

/**
 * Created by tlh on 2016/8/8.
 *
 */
public class Defer$from$repeat {
    public static void main(String[] args) {
        Observable.defer(Defer$from$repeat::loadingData).subscribe(System.out::println);
    }

    private static Observable<String> loadingData() {
        String[] data={"a","b","c"};
        try {
            Thread.sleep(2000);
            return Observable.from(data).repeat(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
