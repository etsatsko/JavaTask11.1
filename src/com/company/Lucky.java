package com.company;

public class Lucky {
    private static int x = 0;
    private static int count = 0;

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                int copyX;
                synchronized (Lucky.class) {
                    if (x >= 999999) break;
                    else copyX = x++;
                }
                if ((copyX % 10) + (copyX / 10) % 10 + (copyX / 100) % 10 == (copyX / 1000)
                        % 10 + (copyX / 10000) % 10 + (copyX / 100000) % 10) {
                    System.out.println(copyX);
                    count++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
    }
}