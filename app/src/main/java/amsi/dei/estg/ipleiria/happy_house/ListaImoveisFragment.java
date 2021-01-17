package amsi.dei.estg.ipleiria.happy_house;

import android.content.Intent;
import android.os.Bundle;

import amsi.dei.estg.ipleiria.happy_house.listeners.ImoveisListener;
import amsi.dei.estg.ipleiria.happy_house.utils.ImovelJsonParser;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.happy_house.R;
import amsi.dei.estg.ipleiria.happy_house.adaptadores.ListaImovelAdaptador;
import amsi.dei.estg.ipleiria.happy_house.modelos.Imovel;
import amsi.dei.estg.ipleiria.happy_house.modelos.SingletonImovel;


public class ListaImoveisFragment extends Fragment implements ImoveisListener {

    private ListView lvListaImoveis;
    private ArrayList<Imovel>listaImoveis;
    private ListaImovelAdaptador listaImovelAdaptador;
    private MenuItem menuCidadeFiltro, menuQuartosFiltro, menuEstadoFiltro, menuPrecoFiltro;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_lista_imoveis, container, false);
        lvListaImoveis = view.findViewById(R.id.lvListaImoveis);
        //listaImoveis= SingletonImovel.getInstance(getContext()).getImovels();
        //lvListaImoveis.setAdapter(new ListaImovelAdaptador(getContext(),listaImoveis));

        lvListaImoveis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                //Imovel tempImovel = (Imovel) adapterView.getItemAtPosition(i);
                //Toast.makeText(getContext(), "AQUI: " + tempImovel.getId(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), DetalhesImovelActivity.class);
                intent.putExtra(DetalhesImovelActivity.ID, (int) id);
                startActivity(intent);
            }
        });

        SingletonImovel.getInstance(getContext()).setImoveisListener(this);
        SingletonImovel.getInstance(getContext()).getAllImoveisAPI(getContext(), ImovelJsonParser.isConnectionInternet(getContext()));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SingletonImovel.getInstance(getContext()).getAllImoveisAPI(getContext(), ImovelJsonParser.isConnectionInternet(getContext()));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_imovel, menu);

        menuCidadeFiltro = menu.findItem(R.id.itemCidadeFiltro);
        menuQuartosFiltro = menu.findItem(R.id.itemQuartosFiltro);
        menuEstadoFiltro = menu.findItem(R.id.itemEstadoFiltro);
        menuPrecoFiltro = menu.findItem(R.id.itemPrecoFiltro);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.itemCidadeFiltro:
                break;
            case R.id.itemQuartosFiltro:
                break;
            case R.id.itemEstadoFiltro:
                break;
            case R.id.itemPrecoFiltro:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefreshListaImoveis(ArrayList<Imovel> listaImoveis) {
        System.out.println("--> onRefreshListaImoveis: " + listaImoveis);
        if (!listaImoveis.isEmpty()){
            listaImovelAdaptador = new ListaImovelAdaptador(getContext(), listaImoveis);
            lvListaImoveis.setAdapter(new ListaImovelAdaptador(getContext(), listaImoveis));
            listaImovelAdaptador.refresh(listaImoveis);
        }
    }
}