package com.example.android.loginscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.loginscreen.database.models.Empresa;
import com.example.android.loginscreen.database.models.History;
import com.example.android.loginscreen.database.models.Schedule;
import com.example.android.loginscreen.database.models.User;
import com.example.android.loginscreen.database.rest.APIClient;
import com.example.android.loginscreen.database.rest.APIInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tvregistar, tvesquecer;
    private EditText etEmail, etPassword;
    private CardView cardEntrar;
    private String email, password;
    private FirebaseAuth firebaseAuth;
    private User user;

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
                /**
                 * Login firebase
                 */
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show();

                            User user = getUser(firebaseAuth);
                            /** ToDo: meter um loading screen aqui*/
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            intent.putExtra("user", user);
                            finish();
                            startActivity(intent);
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

    /**
     * Obter os dados do utilizador
     **/
    private User getUser(FirebaseAuth firebaseAuth) {
        String email = firebaseAuth.getCurrentUser().getEmail();

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<User> call = apiInterface.getUserByEmail(email);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                ArrayList<Schedule> schedules = response.body().getSchedules();
                ArrayList<Empresa> empresas = response.body().getEmpresas();
                ArrayList<History> histories = response.body().getHistory();
                user = new User(
                        response.body().getId(),
                        response.body().getName(),
                        response.body().getEmail(),
                        response.body().getTelnum(),
                        schedules,
                        empresas,
                        response.body().getPreferencia(),
                        histories);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return user;
    }


}
