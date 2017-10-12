package com.example.zhanggang.javathreadsynchronized;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements Runnable {

    private static final String TAG = "TAG";
    private static int number;

    public MainActivity() {
        number = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity mainActivity = new MainActivity();
        Thread thread1 = new Thread(mainActivity, "Thread1");
        Thread thread2 = new Thread(mainActivity, "Thread2");
        thread1.start();
        thread2.start();
    }

    /**
     * 原因解释：因为代码块加了同步锁，
     * 所以，线程Thread1把方法执行完，
     * 再执行的Thread2的方法，
     * 而不会出现结果交叉
     */
    @Override
    public void run() {
        synchronized (this){   //修饰一个代码块
            for (int i = 0; i < 3; i++) {
                Log.e(TAG, "run: "+Thread.currentThread().getName()+"："+(number++));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
