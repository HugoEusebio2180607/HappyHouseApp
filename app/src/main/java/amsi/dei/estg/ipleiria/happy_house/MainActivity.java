package amsi.dei.estg.ipleiria.happy_house;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private FragmentManager fragmentManager;

    public static final String CHAVE_EMAIL = "EMAIL";
    public static final String SECCAO_INFO_USER = "SECCAO_INFO_USER";
    private String email = "";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        drawer.addDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(this);

        carregarCabecalho();

        fragmentManager = getSupportFragmentManager();

        carregamentoFragmentoInicial();
    }

    private void carregarCabecalho() {
        sharedPreferences = getSharedPreferences(SECCAO_INFO_USER, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        email = getIntent().getStringExtra(CHAVE_EMAIL).toString();

        if (email == null) {
            email = sharedPreferences.getString(SECCAO_INFO_USER, "Não Existe");
        } else {
            editor.putString(SECCAO_INFO_USER, email);
            editor.apply();
        }

        View view = navigationView.getHeaderView(0);
        TextView textView_user = view.findViewById(R.id.tvEmail);
        textView_user.setText(email);
    }

    private void carregamentoFragmentoInicial() {
        navigationView.setCheckedItem(R.id.nav_imoveis);
        Fragment fragment = new ListaImoveisFragment();
        fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
        setTitle("Imóveis");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.nav_imoveis:
                fragment = new ListaImoveisFragment();
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_historico_compras:
                //fragment = new ;
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_favoritos:
                //fragment = new ;
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_historico_visitas:
                //fragment = new ;
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_definicoes:
                //fragment = new ;
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_logout:
                //fazer logout
                break;
        }

        return false;
    }
}