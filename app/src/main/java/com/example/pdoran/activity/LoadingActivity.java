package com.example.pdoran.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pdoran.R;


public class LoadingActivity extends Activity {

    ImageView LoadingView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        LoadingView = (ImageView)findViewById(R.id.LoadingView);

        Glide.with( this )
                .asGif()    // GIF 로딩
                .load( R.raw.loading)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)    // Glide에서 캐싱한 리소스와 로드할 리소스가 같을때 캐싱된 리소스 사용
                .into( LoadingView );

        startLoading();
    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);  //Loagin화면을 띄운다.
                finish();   //현재 액티비티 종료
            }
        }, 3300); // 화면에 Logo 3.3초간 보이기
    }
}