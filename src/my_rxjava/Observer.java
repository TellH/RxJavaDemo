package my_rxjava;

public interface Observer<T> {
    void onCompleted();

    void onError(Throwable t);

    void onNext(T var1);
}