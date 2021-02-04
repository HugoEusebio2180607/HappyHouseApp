package amsi.dei.estg.ipleiria.happy_house;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
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

    public static final String CHAVE_ID = "ID";
    public static final String CHAVE_USERNAME = "USERNAME";
    public static final String CHAVE_PASSWORD = "PASSWORD";
    public static final String CHAVE_TELEMOVEL = "TELEMOVEL";
    public static final String SECCAO_INFO_USER = "SECCAO_INFO_USER";
    private String username = "";
    private String password = "";
    private int telemovel, id;

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
        sharedPreferences = getApplicationContext().getSharedPreferences(SECCAO_INFO_USER, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        username = getIntent().getStringExtra(CHAVE_USERNAME);
        password = getIntent().getStringExtra(CHAVE_PASSWORD);
        telemovel = getIntent().getIntExtra(CHAVE_TELEMOVEL, 0);
        id = getIntent().getIntExtra(CHAVE_ID, 0);

        if (username == null) {
            username = sharedPreferences.getString(SECCAO_INFO_USER, "Não Existe");
        } else {
            editor.putString(CHAVE_USERNAME, username);
            editor.putString(CHAVE_PASSWORD, password);
            editor.putInt(CHAVE_TELEMOVEL, telemovel);
            editor.putInt(CHAVE_ID, id);
            editor.apply();
        }

        View view = navigationView.getHeaderView(0);
        TextView textView_user = view.findViewById(R.id.tvEmail);
        textView_user.setText(username);
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
            /*case R.id.nav_historico_compras:
                fragment = new HistoricoCompraAluguerFragment();
                setTitle(menuItem.getTitle());
                break;*/
            case R.id.nav_favoritos:
                fragment = new FavoritosFragment();
                setTitle(menuItem.getTitle());
                break;
            /*case R.id.nav_historico_visitas:
                fragment = new HistoricoVisitasFragment();
                setTitle(menuItem.getTitle());
                break;*/
            case R.id.nav_definicoes:
                fragment = new DefinicoesFragment();
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_logout:
                System.exit(0);
                break;
        }

        if (fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}