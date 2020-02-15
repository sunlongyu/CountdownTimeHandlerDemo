package com.example.countdowntimehandlerdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.lang.ref.WeakReference;


public class MainActivity extends AppCompatActivity {

    public static final int COUNTDOWN_CODE = 100001;
    public static final int MAX_TIME = 10;
    public static final int DELAY_MILLIS = 1000;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textview);

        CountdownTimeHandler handler = new CountdownTimeHandler(this);

        Message message = Message.obtain();
        message.what = COUNTDOWN_CODE;
        message.arg1 = MAX_TIME;
        handler.sendMessageDelayed(message, DELAY_MILLIS);


    }

    public static class CountdownTimeHandler extends Handler {

        final WeakReference<MainActivity> weakReference;

        private CountdownTimeHandler(MainActivity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if (msg.what == COUNTDOWN_CODE) {
                MainActivity mainActivity = weakReference.get();
                int value = msg.arg1;
                mainActivity.textView.setText(String.valueOf(value--));
                if (value >= 0) {
                    Message message = Message.obtain();
                    message.what = COUNTDOWN_CODE;
                    message.arg1 = value;
                    sendMessageDelayed(message, DELAY_MILLIS);
                }


            }
        }
    }
}
