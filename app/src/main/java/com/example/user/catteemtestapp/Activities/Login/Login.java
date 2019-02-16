package com.example.user.catteemtestapp.Activities.Login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    private List<loginpogo> loginpogo;
    public static String wallet ;

    EditText Login_number,login_password;
    private String contact,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        Login_number = findViewById( R.id.Login_number );
        login_password = findViewById( R.id.Login_password );
         contact = Login_number.getText().toString().trim();
         password = login_password.getText().toString().trim();
        Login = findViewById( R.id.Login_Button );

        Login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                contact = Login_number.getText().toString().trim();
                password = login_password.getText().toString().trim();
                ApiInterface apiService = ApiClient.getClient().create( ApiInterface.class );
                Call<List<loginpogo>> call = apiService.login( "1234567890","manan" );
//                Call<List<loginpogo>> call = apiService.login( contact,password );
                call.enqueue( new Callback<List<com.example.user.catteemtestapp.Activities.Login.loginpogo>>() {
                    @Override
                    public void onResponse(Call<List<com.example.user.catteemtestapp.Activities.Login.loginpogo>> call, Response<List<com.example.user.catteemtestapp.Activities.Login.loginpogo>> response) {
                        loginpogo =response.body();
                        wallet = loginpogo.get( 0 ).getUser_wallet_amount();
                        String status = loginpogo.get( 0 ).getStatus();
                        if (status.equals( "1" )){

                            Intent login = new Intent( Login.this, MainActivity.class );
                            login.putExtra( "nameuser",loginpogo.get( 0 ).getUser_name() );
                            login.putExtra( "iduser",loginpogo.get( 0 ).getUser_id() );
                            login.putExtra( "wallet",loginpogo.get( 0 ).getUser_wallet_amount() );
                            startActivity( login );


                        }
                        else {
                            Snackbar.make( v, "Enter a correct password or a correct phone number", Snackbar.LENGTH_LONG )
                                    .setAction( "Action", null ).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<com.example.user.catteemtestapp.Activities.Login.loginpogo>> call, Throwable t) {
                        Toast.makeText( getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG ).show();

                    }
                } );




            }
        } );



//        Login.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent login = new Intent( Login.this, MainActivity.class );
//                startActivity( login );
//            }
//        } );
    }
}
