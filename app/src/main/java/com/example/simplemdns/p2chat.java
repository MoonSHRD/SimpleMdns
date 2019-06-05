package com.example.simplemdns;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//import java.util.concurrent.*;
import android.os.AsyncTask;

//import p2mobile.P2mobile;
import p2mobile.*;

public class p2chat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p2chat);
      //  P2mobile.start();
        BackTask magic = new BackTask();
        magic.execute();

    }



    class BackTask extends AsyncTask < Void, Void, Void > {

        @Override
        protected Void doInBackground(Void... voids) {

            P2mobile.start();
          //  p2mobile.StreamApi
            return null;

        }
    }



    /*
     // NOTE - this is for java 7
    Runnable task = new Runnable() {
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        }
    };
    */



    /*
    new Thread(() -> {
    //Do whatever
        }).start();


        Runnable task = () -> {
    String threadName = Thread.currentThread().getName();
    System.out.println("Hello " + threadName);
    };

    task.run();

    Thread thread = new Thread(task);
    thread.start();

    System.out.println("Done!");




     */



}
