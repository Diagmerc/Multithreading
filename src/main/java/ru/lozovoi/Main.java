package ru.lozovoi;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
    static int defaultThreadCount = 3;

    public static void main(String[] args) throws Exception {
        Integer[] storage = {1, 5, 7, 2, 4, 0, 2, 1};
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(defaultThreadCount);
        executorService.scheduleAtFixedRate(new MultiThreadSorter
                (storage, 0, storage.length / 2), 1L, 1L, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(new MultiThreadSorter
                (storage, storage.length / 2, storage.length), 1L, 1L, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(new MultiThreadSorter
                (storage, 0, storage.length), 1L, 1L, TimeUnit.SECONDS);
        executorService.awaitTermination(2, TimeUnit.SECONDS);
        executorService.shutdownNow();
        for (Integer integer : storage) {
            System.out.println(integer);
        }
    }
}
