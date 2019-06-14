package com.roachfu.tutorial.cas;

/**
 * @author roach
 * @date 2019/5/16
 */
public class CasTest {

    private static int count = 0;

    public static void main(String[] args) {

        for (int i = 0; i < 200; i++){
            count();
        }
    }

    private static void count(){
        for (int i = 0; i < 2; i++){
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int j = 0; j< 100; j++){
                    synchronized (CasTest.class) {
                        count++;
                    }
                }
            }).start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("count = " + count);
    }
}
