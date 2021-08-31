package com.example.pdoran.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.pdoran.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends BasicActivity {


    private FirebaseAuth auth; //파이어베이스 인증 객체
    private static final int REQ_SIGN_Email = 130; //이메일 로그인 결과 코드
    private Button btn_register, btn_LoCheck, btn_reset;
    private static final int loginCode = 100;
    private EditText eId, ePwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();


        // 회원가입 버튼 누르면 넘어감
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        //비밀번호 재설정
        btn_reset = findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PasswordResetActivity.class);
                startActivity(intent);
            }
        });


        btn_LoCheck = findViewById(R.id.btn_LoCheck);
        eId = (EditText) findViewById(R.id.text_LoId);
        ePwd = (EditText) findViewById(R.id.text_LoPwd);

        // login 확인 버튼
        btn_LoCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = eId.getText().toString().trim();
                String pwd = ePwd.getText().toString().trim();

                if (id.length() > 0 && pwd.length() > 0) {
                    final RelativeLayout loaderLayout = findViewById(R.id.loaderLayout); //로딩 선언
                    loaderLayout.setVisibility(View.VISIBLE); //로딩 보이게
                    auth.signInWithEmailAndPassword(id, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            loaderLayout.setVisibility(View.GONE); //성공시 로딩 안보이게
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class)); //MainActivity2: 메인 페이지

                            } else {
                                Toast.makeText(LoginActivity.this, "로그인에 실패했습니다." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "이메일 또는 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}