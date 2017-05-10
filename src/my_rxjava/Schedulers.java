package my_rxjava;

import java.util.concurrent.Executors;

/**
 * Created by tlh on 2017/5/9.
 */
public class Schedulers {
    private static final Scheduler ioScheduler = new Scheduler(Executors.newSingleThreadExecutor());

    public static Scheduler io() {
        return ioScheduler;
    }
}
