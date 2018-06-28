package com.example.android.loginscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class AdicionarTrocaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText adInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_troca);

        adInfo = findViewById(R.id.adInfo);
        adInfo.clearFocus();

        /** inicializar todas as variáveis */
        initialize();

        /** Indicar que a Action Bar é a toolbar que criámos, tirar o seu título automático e acrescentar a seta para voltar */
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /** Dar uma ação à seta para voltar */
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initialize(){
        toolbar = findViewById(R.id.nav_action);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();

    }
}


