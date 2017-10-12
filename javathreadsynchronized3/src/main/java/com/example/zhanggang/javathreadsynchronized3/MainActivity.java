package com.example.zhanggang.javathreadsynchronized3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements Runnable{

    private static final String TAG = "TAG";
    private static int number;

    public MainActivity() {
        number=0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity mainActivity = new MainActivity();
        MainActivity mainActivity2 = new MainActivity();
        Thread thread1 = new Thread(mainActivity, "Thread1");
        Thread thread2 = new Thread(mainActivity2, "Thread2");
        thread1.start();
        thread2.start();
    }

    @Override
    public void run() {
        method();
    }

    /**
     * 原因解释：虽然线程thread1和thread2里边用的不是一个MainActivity，
     * 但是线程thread1和thread2，还是按照先后顺序执行完的method方法，
     * 原因就是synchronized关键字加在了静态方法上，
     * 而加在静态方法上的作用范围是：这个类的所有对象
     */
    //修饰一个静态方法
    private synchronized static void method() {
        for (int i = 0; i < 3; i++) {
            try {
                Log.e(TAG, "method: "+Thread.currentThread().getName()+"："+(number++));

                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
