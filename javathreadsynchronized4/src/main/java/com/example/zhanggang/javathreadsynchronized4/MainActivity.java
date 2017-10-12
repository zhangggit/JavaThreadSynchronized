package com.example.zhanggang.javathreadsynchronized4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/**
 * 关键字synchronized拥有锁重入的功能。所谓锁重入的意思就是：
 * 当一个线程得到一个对象锁后，再次请求此对象锁时可以再次得到该对象的锁的。
 *
 * 可以看到直接调用ThreadDomain1中的打印语句，
 * 这证明了对象可以再次获取自己的内部锁。这种锁重入的机制，也支持在父子类继承的环境中。
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyThread myThread = new MyThread();
        myThread.start();
    }
    public class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            ThreadDomain1 threadDomain1 = new ThreadDomain1();
            threadDomain1.print1();
        }
    }
    public class ThreadDomain1{
        public synchronized void print1(){
            Log.e(TAG, "ThreadDomain1.print1()" );
            print2();
        }
        public synchronized void print2(){
            Log.e(TAG, "ThreadDomain1.print2()" );
            print3();
        }
        public synchronized void print3(){
            Log.e(TAG, "ThreadDomain1.print3()" );
        }
    }


}
