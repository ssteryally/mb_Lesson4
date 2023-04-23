package ru.mirea.shunyaev.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import ru.mirea.shunyaev.looper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Handler mainThreadHandler = new Handler(android.os.Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {

                Log.d(MainActivity.class.getSimpleName(), "Task execute. This is result: "
                        + msg.getData().getString("result"));

            }
        };
        Looper myLooper = new Looper(mainThreadHandler);
        myLooper.start();

        binding.editText.setText("Мой номер по списку № 28");
        binding.buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString("KEY", "Denis");
                msg.setData(bundle);
                myLooper.mHandler.sendMessage(msg);
            }
        });
    }
}