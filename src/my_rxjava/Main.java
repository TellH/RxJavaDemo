package my_rxjava;

/**
 * Created by tlh on 2017/5/9.
 */
public class Main {
    public static void main(String[] args) {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    subscriber.onNext(i);
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onNext(Integer var1) {
                System.out.println(var1);
            }
        });

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    subscriber.onNext(i);
                }
            }
        }).map(new Observable.Transformer<Integer, String>() {
            @Override
            public String call(Integer from) {
                return "maping " + from;
            }
        }).subscribe(new SimpleSubscriber<String>() {
            @Override
            public void onNext(String var1) {
                System.out.println(var1);
            }
        });

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                System.out.println("OnSubscribe@ " + Thread.currentThread().getName());
                subscriber.onNext(1);
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe(new SimpleSubscriber<Integer>() {
                    @Override
                    public void onNext(Integer var1) {
                        System.out.println("Subscriber@ " + Thread.currentThread().getName());
                        System.out.println(var1);
                    }
                });
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                System.out.println("OnSubscribe@ " + Thread.currentThread().getName());
                subscriber.onNext(1);
            }
        })
                .observeOn(Schedulers.io())
                .subscribe(new SimpleSubscriber<Integer>() {
                    @Override
                    public void onNext(Integer var1) {
                        System.out.println("Subscriber@ " + Thread.currentThread().getName());
                        System.out.println(var1);
                    }
                });
    }

    private abstract static class SimpleSubscriber<T> extends Subscriber<T> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable t) {

        }
    }
}
