package ru.lozovoi;

public class MultiThreadSorter implements Sorter {

    Integer[] storage;
    int start_element;

    int end_element;

    public MultiThreadSorter(Integer[] storage, int start_element, int end_element) {
        this.storage = storage;
        this.start_element = start_element;
        this.end_element = end_element;
    }

    public synchronized void sort() {
        boolean isSort = false;
        int temp;
        while (!isSort) {
            isSort = true;
            for (int i = start_element; i < end_element - 1; i++) {
                if (storage[i] > storage[i + 1]) {
                    isSort = false;
                    temp = storage[i];
                    storage[i] = storage[i +1];
                    storage[i + 1] =temp;
                }
            }
        }
    }


    @Override
    public void run() {
        sort();
        System.out.println("Started " + Thread.currentThread().getName());
    }
}
