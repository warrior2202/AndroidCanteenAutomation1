package com.example.user.catteemtestapp.Activities.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.catteemtestapp.Activities.MainActivity.MainActivity;
import com.example.user.catteemtestapp.Model.LoginRespone;
import com.example.user.catteemtestapp.R;
import com.example.user.catteemtestapp.rest.ApiClient;
import com.example.user.catteemtestapp.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    Button Login;
    private List<LoginRespone> loginRespone;
    EditText Login_number,login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        Login_number = findViewById( R.id.Login_number );
        login_password = findViewById( R.id.Login_password );
        String email = Login_number.getText().toString().trim();
        String password = login_password.getText().toString().trim();

        ApiInterface apiService = ApiClient.getClient().create( ApiInterface.class );
        Call<LoginRespone> call = apiService.userLogin(email,password);
        call.enqueue( new Callback<LoginRespone>() {
            @Override
            public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {

            }

            @Override
            public void onFailure(Call<LoginRespone> call, Throwable t) {

            }
        } );

        Login = findViewById( R.id.Login_Button );
        Login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent( Login.this, MainActivity.class );
                startActivity( login );
            }
        } );
    }
}
