package com.helloworld.liulian;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;

import com.helloworld.liulian.Bean.User;
import com.helloworld.liulian.model.UserModel;
import com.helloworld.liulian.model.BaseModel;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;


public class RegisterActivity extends FragmentActivity {

    EditText register_username = null;
    EditText register_secret = null;
    EditText register_secretagain = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register_username = (EditText)findViewById(R.id.id_register_username);
        register_secret = (EditText)findViewById(R.id.id_register_secret);
        register_secretagain = (EditText)findViewById(R.id.id_register_secretagain);
    }

    public void OnRegisterClick(View view)
    {
        UserModel.getInstance().register(register_username.getText().toString(),
                register_secret.getText().toString(),
                register_secretagain.getText().toString(),
                new LogInListener() {
            @Override
            public void done(Object o, BmobException e) {
                if(e==null){
                    //EventBus.getDefault().post(new FinishEvent());
                    //startActivity(MainActivity.class, null, true);
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    if(e.getErrorCode()== BaseModel.CODE_NOT_EQUAL){
                        register_secretagain.setText("");
                    }
                    //toast(e.getMessage()+"("+e.getErrorCode()+")");
                }
            }
        });

    }
}
