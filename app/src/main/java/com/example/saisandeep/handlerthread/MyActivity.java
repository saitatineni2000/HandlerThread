package com.example.saisandeep.handlerthread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;


public class MyActivity extends ActionBarActivity {

    Thread thread;
    Handler handler;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        pb= (ProgressBar) findViewById(R.id.progressBar1);

        thread=new Thread(new ThreadBackground());
        thread.start();

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                pb.setProgress(msg.arg1);
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class ThreadBackground implements Runnable{

        @Override
        public void run() {

           // Message message=Message.obtain();
            for(int i=0;i<100;i++)
            {
                Message message=Message.obtain();
                message.arg1=i;
                handler.sendMessage(message);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
