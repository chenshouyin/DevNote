package com.example.csy.netrequest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.csy.netrequest.utils.Dbug;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btGet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addActions();
    }

    private void addActions() {
        btGet = (Button) findViewById(R.id.btGet);
        btGet.setOnClickListener(this);
    }


    /**
     * OkHttpClient做Get请求
     * @param view
     */
    public void doGet(View view) throws IOException {
        //1.拿到OkHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();

        //2.构造Request
        Request.Builder mBuilder = new Request.Builder();
        Request mRequest = mBuilder.get().url("https://github.com/chenshouyin").build();

        //3.将Request封装成call
        Call mCall = mOkHttpClient.newCall(mRequest);

        //4.同步或者异步执行call
        //Response execute = mCall.execute();//同步
         mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Dbug.e(getClass().getSimpleName(),"===onFailure=="+e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Dbug.e(getClass().getSimpleName(),"===onResponse=="+response);
                Dbug.e(getClass().getSimpleName(),"===onResponse==message=="+response.message());
                Dbug.e(getClass().getSimpleName(),"===onResponse==isSuccessful=="+response.isSuccessful());
            }
        });//异步
    }

    @Override
    public void onClick(View v) {
        if (v==btGet){
            try {
                doGet(v);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
