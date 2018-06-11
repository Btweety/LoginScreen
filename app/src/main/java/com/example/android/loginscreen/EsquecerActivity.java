package com.example.android.loginscreen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class EsquecerActivity extends AppCompatActivity {

    private EditText etMail;
    private ImageView ivBackArrow;
    private CardView cvEnviarPass;
    private String email;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esquecer);
        initialize();
        firebaseAuth = FirebaseAuth.getInstance();
        /**
         * botão de enviar a password
         */
        cvEnviarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etMail.getText().toString();
                if(email.isEmpty()){
                    Toast.makeText(EsquecerActivity.this, "Introduza o Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            backButton();
                            Toast.makeText(EsquecerActivity.this, "O email de redefinição de password foi enviado para: \n" + email, Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(EsquecerActivity.this, "Verifique se inseriu o seu email corretamente", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        /**
         * Seta de voltar atras
         */
        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButton();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        backButton();
    }

    /**
     * função para retornar para o ecra anterior
     */
    private void backButton(){
        finish();
        startActivity(new Intent(EsquecerActivity.this, MainActivity.class));
    }

    private void initialize(){
        cvEnviarPass = findViewById(R.id.envPass);
        etMail = findViewById(R.id.etEmailEsquecer);
        ivBackArrow = findViewById(R.id.backArrowEsquecer);
    }
}
