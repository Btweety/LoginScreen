package com.example.android.loginscreen;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /** Inicializar cada uma das variáveis com findViewById */
        initialize();

        /** Indicar que a Action Bar é a toolbar que criámos e tirar o seu título automático */
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        /** Criar o Toggle do Navigation Drawer para indicar se ele está aberto ou não*/
        actionBarDrawerToggle = new ActionBarDrawerToggle(HomeActivity.this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        /** Centrar o texto de cada item do Navigation Drawer */
        for (int positionOfMenuItem = 0; positionOfMenuItem < navigationView.getMenu().size(); positionOfMenuItem++) {
            MenuItem item = navigationView.getMenu().getItem(positionOfMenuItem);

            SpannableString s = new SpannableString(item.getTitle().toString());
            s.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, s.length(), 0);

            item.setTitle(s);
        }


    }

    private void initialize(){
        navigationView = findViewById(R.id.navView);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.nav_action);
    }
}
