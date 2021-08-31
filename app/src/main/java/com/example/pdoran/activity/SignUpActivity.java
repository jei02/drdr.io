package com.example.pdoran.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.pdoran.R;
import com.example.pdoran.activity.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends BasicActivity {

    private static final String TAG = "SignUpActivity"; //생략가능
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance(); //초기화

        findViewById(R.id.signUpButton).setOnClickListener(onClickListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    View.OnClickListener onClickListener = new View.OnClickListener() { // 확장성을 위해서 함수형태로 작성
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.signUpButton:

                    Log.e("클릭", "클릭");
                    signUp(); //회원가입 함수

                    break;
            }
        }
    };

    private void signUp() { //회원가입 함수로 관리

        String email = ((EditText) findViewById(R.id.emailEditText)).getText().toString(); //일반뷰는 getText 사용불가하기때문에 형변환을 위해 (EditText)사용
        String password = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();
        String passwordCheck = ((EditText) findViewById(R.id.passwordCheckEditText)).getText().toString();

        if (email.length() > 0 && passwordCheck.length() > 0) {
            if (password.equals(passwordCheck)) {

                final RelativeLayout loaderLayout = findViewById(R.id.loaderLayout); //로딩 선언
                loaderLayout.setVisibility(View.VISIBLE); //로딩 보이게

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                loaderLayout.setVisibility(View.GONE); //성공시 로딩 안보이게
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    startToast("회원가입에 성공했습니다.");
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class); //로그인 화면으로 이동
                                    startActivity(intent);

                                    //성공시 UI 로직

                                } else { //비밀번호가 6자리 이하면 출력
                                    if (task.getException() != null) {
                                        startToast("비밀번호를 6자리 이상 설정해주세요.");
                                    }
                                }
                            }
                        });

            } else {

                startToast("비밀번호가 일치하지 않습니다.");
            }

        } else {
            startToast("이메일 또는 비밀번호를 입력해주세요.");
        }


    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}