package frame;

public class Observable<T> {
    protected OnAttach<T> onAttach;

    public Observable(OnAttach<T> onAttach) {
        this.onAttach = onAttach;
    }

    public static <T> Observable<T> create(OnAttach<T> onAttach) {
        return new Observable<>(onAttach);
    }


    public void attach(Observer<T> observer) {
        onAttach.call(observer);
    }

    public <R> Observable<R> map(IFun<? super T, ? extends R> fun) {
        OperatorMap<T,R> operatorMap = new OperatorMap<>(fun);
        //根据操作符生成新的Observable,并返回,以便实现链式操作
        return lift(operatorMap);

    }

    public interface OnAttach<T> {
        void call(Observer<? super T> observer);
    }

    //重点,该方法的实现了方法链.
    //用Operator实现Observer的转化
    //将OnAttach事件传递下去。
    //当到了最初那个Observable的OnAttach时，才调用Observer的update，进行数据流的转化
    //因为中间的Observer是在Operator的call方法中创建的，在这些Observer的update方法中进行数据流的转化
    //并回调call方法参数中的Observer的update方法，将数据传递下去，最终传到最开始订阅传进去的那个Observer。
    public <R> Observable<R> lift(final Operator<? extends R, ? super T> operator) {
        return new Observable<>(new OnAttach<R>() {
            @Override
            public void call(Observer<? super R> observer) {
                Observer<? super T> observerConverted = operator.call(observer);
                Observable.this.onAttach.call(observerConverted);
            }
        });
    }

    public <R> Observable<R> filter(IFun<? super T, Boolean> fun) {
        return lift(new OperatorFilter(fun));
    }

    //1.操作符接口
    public interface Operator<R, T> extends IFun<Observer<? super R>, Observer<? super T>> {}

}