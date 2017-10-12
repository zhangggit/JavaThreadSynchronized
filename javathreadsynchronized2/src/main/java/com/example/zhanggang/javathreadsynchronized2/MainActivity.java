package com.example.zhanggang.javathreadsynchronized2;

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
     * 修饰一个方法：被修饰的方法称为同步方法，
     * 其作用的范围是整个方法，作用的对象是调用这个方法的对象
     */
    @Override
    public synchronized void run() {  //修饰一个方法
        for (int i = 0; i < 3; i++) {
            Log.e(TAG, "run: " + Thread.currentThread().getName() + "：" + (number++));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
