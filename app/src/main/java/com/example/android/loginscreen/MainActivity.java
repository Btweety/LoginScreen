package com.example.android.loginscreen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextView tvregistar, tvesquecer;
    private EditText etEmail, etPassword;
    private CardView cardEntrar;
    private String email, password;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        firebaseAuth = FirebaseAuth.getInstance();

        /**Verificar se algum utilizador já está logged in e se estiver mandá-lo automaticamente para a Home Activity */
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }


        /**
         *  botão de login
         */
        cardEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();

                if(email.isEmpty()){
                    Toast.makeText(MainActivity.this, "Introduza o Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Introduza a Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(MainActivity.this, HomeActivity.class));
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Email ou Password errados", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        /**
         * Ir para a pagina de registo
         */
        tvregistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this, RegistarActivity.class));
            }
        });

        /**
         * Ir para a pagina de esqueceu password
         */
        tvesquecer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), EsquecerActivity.class));
            }
        });
    }

    private void initialize(){
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        cardEntrar = findViewById(R.id.cardEntrar);
        tvregistar = findViewById(R.id.tv_registar);
        tvesquecer = findViewById(R.id.tvEsquecer);
    }


}
