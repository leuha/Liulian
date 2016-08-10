package com.helloworld.liulian;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;

import com.helloworld.liulian.bean.User;
import com.helloworld.liulian.model.UserModel;
import com.helloworld.liulian.model.BaseModel;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;


public class RegisterActivity extends FragmentActivity {

    TextView register_username = (TextView)findViewById(R.id.id_register_username);
    TextView register_secret = (TextView)findViewById(R.id.id_register_secret);
    TextView register_secretagain = (TextView)findViewById(R.id.id_register_secretagain);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    void onBtnRegister(View view)
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
