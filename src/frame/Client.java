package frame;

public class Client {
    public static void main(String[] args) {

        //注册关系,简化了手动通知观察者的过程
        Observable.OnAttach<String> onAttach0 = new Observable.OnAttach<String>() {
            @Override
            public void call(Observer<? super String> observer) {
                observer.update("test");
            }
        };

        //被观察者
        Observable<String> observable0 = Observable.create(onAttach0);

        Observable<String> observable1 = observable0.map(new IFun<String, String>() {

            @Override
            public String call(String s) {
                return s + "_0to1";
            }
        });

        Observable<String> observable2 = observable1.map(new IFun<String, String>() {

            @Override
            public String call(String s) {
                return s + "_1to2";
            }
        });

        //观察者
        Observer<String> observer0 = new Observer<String>() {
            @Override
            public void update(String t) {
                System.out.println(t);
            }
        };

        //将观察者注册到被观察者上
        observable2.attach(observer0);

    }
}
