package com.hencoder.a25_thread_interaction;

public class CustomizableThreadDemo implements TestDemo {
    private CustomizableThread thread = new CustomizableThread();

    @Override
    public void runTest() {
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    class CustomizableThread extends Thread {
        Looper looper = new Looper();

        @Override
        public void run() {
            looper.loop();
        }
    }

    class Looper {
        private volatile boolean quit;
        private Runnable task;

        synchronized void setTask(Runnable task) {
            this.task = task;
        }

        void quit() {
            quit = true;
        }

        void loop() {
            while (!quit) {
                synchronized (this) {
                    if (task != null) {
                        task.run();
                        task = null;
                    }
                }
            }
        }
    }
}
