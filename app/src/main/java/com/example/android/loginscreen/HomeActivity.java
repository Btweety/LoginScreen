package com.example.android.loginscreen;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.MenuItem;

import com.example.android.loginscreen.fragments.DefinicoesFragment;
import com.example.android.loginscreen.fragments.HomePageFragment;
import com.example.android.loginscreen.fragments.HorariosFragment;
import com.example.android.loginscreen.fragments.PedidosTrocaFragment;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
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

        /** Instancia o fragmento correspondente à opção do menu **/
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                Class fragmentClass = null;
                /** Selecionar que fragmento criar com base ao menu escolhido **/
                switch (item.getItemId()){
                    case R.id.nav_item_home:
                        fragmentClass = HomePageFragment.class;
                        break;
                    case R.id.nav_item_horario:
                        fragmentClass = HorariosFragment.class;
                        break;
                    case R.id.nav_item_pedidostroca:
                        fragmentClass = PedidosTrocaFragment.class;
                        break;
                    case R.id.nav_item_teste:
                        startActivity(new Intent(HomeActivity.this, TrocaActivity.class));
                        break;
                    case R.id.nav_item_teste2:
                        startActivity(new Intent(HomeActivity.this, SubmeterTrocaActivity.class));
                        break;
                    case R.id.nav_item_settings:
                        fragmentClass = DefinicoesFragment.class;
                        break;
                    case R.id.nav_item_logout:
                        FirebaseAuth.getInstance().signOut();
                        finish();
                        startActivity(new Intent(HomeActivity.this, MainActivity.class));
                        break;

                }
                /** instanciar o fragmento **/
                try {
                    fragment = (Fragment) fragmentClass.newInstance();

                    /** colocar o fragmento no container da pagina principal **/
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }



                /** fecha o menu **/
                drawerLayout.closeDrawers();
                return true;
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new HomePageFragment());
        transaction.commit();
    }

    @Override
    public boolean moveDatabaseFrom(Context sourceContext, String name) {
        return super.moveDatabaseFrom(sourceContext, name);
    }



    private void initialize(){
        navigationView = findViewById(R.id.navView);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.nav_action);
    }
}
