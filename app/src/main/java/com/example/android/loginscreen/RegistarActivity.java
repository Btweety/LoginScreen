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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class RegistarActivity extends AppCompatActivity {

    private EditText etNome, etMail, etCodigo, etPass, etConfEmail;
    private CardView cardRegistar;
    private ImageView ivBackArrow;
    private String nome, email, password, confemail, codigo;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);
        initialize();
        firebaseAuth = FirebaseAuth.getInstance();

        /**
         * botão de registar
         */
        cardRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = etNome.getText().toString();
                email = etMail.getText().toString();
                password = etPass.getText().toString();
                confemail = etConfEmail.getText().toString();
                codigo = etCodigo.getText().toString();

                if(nome.isEmpty()){
                    Toast.makeText(RegistarActivity.this, "Introduza o Nome", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(email.isEmpty() || confemail.isEmpty()){
                    Toast.makeText(RegistarActivity.this, "Introduza o Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(codigo.isEmpty()){
                    Toast.makeText(RegistarActivity.this, "Introduza o Código", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.isEmpty()){
                    Toast.makeText(RegistarActivity.this, "Introduza a Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(email.equals(confemail)) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                backButton();
                                Toast.makeText(RegistarActivity.this, "O utilizador foi registado com sucesso", Toast.LENGTH_SHORT).show();
                            }else{
                                try {
                                    throw task.getException();
                                } catch(FirebaseAuthWeakPasswordException e) {
                                    Toast.makeText(RegistarActivity.this, "A password é demasiado curta", Toast.LENGTH_SHORT).show();

                                } catch(FirebaseAuthUserCollisionException e) {
                                    Toast.makeText(RegistarActivity.this, "Este email já está a ser utilizado", Toast.LENGTH_SHORT).show();

                                } catch(FirebaseAuthInvalidCredentialsException e){
                                    Toast.makeText(RegistarActivity.this, "Email inválido", Toast.LENGTH_SHORT).show();

                                } catch(Exception e) {
                                    Toast.makeText(RegistarActivity.this, "Não foi possivel criar conta", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }else{
                    Toast.makeText(RegistarActivity.this, "O email e a sua confirmação não são identicas", Toast.LENGTH_SHORT).show();
                    etPass.setText("");
                    etConfEmail.setText("");
                    etMail.setText("");
                }
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
     * Função para retornar para o ecra anterior
     */
    private void backButton(){
        finish();
        startActivity(new Intent(RegistarActivity.this, MainActivity.class));
    }

    /**
     * Inicializa todas as variáveis
     */
    private void initialize(){
        etNome = findViewById(R.id.etNome);
        etMail = findViewById(R.id.etEmail);
        etCodigo = findViewById(R.id.etCodigo);
        etPass = findViewById(R.id.etPass);
        etConfEmail = findViewById(R.id.etConfEmail);
        cardRegistar = findViewById(R.id.cardConf);
        ivBackArrow = findViewById(R.id.backArrow);
    }
}
