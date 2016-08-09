package frame;

public interface IFun<T, R> {
    R call(T t);
}