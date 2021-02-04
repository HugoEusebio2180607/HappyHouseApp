package amsi.dei.estg.ipleiria.happy_house;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import amsi.dei.estg.ipleiria.happy_house.adaptadores.ListaImovelAdaptador;
import amsi.dei.estg.ipleiria.happy_house.modelos.Imovel;
import amsi.dei.estg.ipleiria.happy_house.modelos.SingletonImovel;
import amsi.dei.estg.ipleiria.happy_house.modelos.User;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class FavoritosFragment extends Fragment {

    private ListView lvListaImoveis;
    private ArrayList<Imovel> listaImoveis;
    private ArrayList<String> favoritos;
    private ArrayList<Imovel> listaImoveisFavoritos;

    private User user;

    private SharedPreferences sharedPreferences;
    public static final String SECCAO_INFO_USER = "SECCAO_INFO_USER";
    public static final String CHAVE_ID = "ID";
    public static final String CHAVE_FAVORITOS = "FAVORITOS";

    public FavoritosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);
        lvListaImoveis = view.findViewById(R.id.lvListaFavoritos);
        listaImoveis= SingletonImovel.getInstance(getContext()).getImovels();


        sharedPreferences = this.getActivity().getSharedPreferences(SECCAO_INFO_USER, Context.MODE_PRIVATE);
        String listFav = sharedPreferences.getString(CHAVE_FAVORITOS, null);
        int idUser = sharedPreferences.getInt(CHAVE_ID, -1);

        user = SingletonImovel.getInstance(getContext()).getUser(idUser);

        if (user.getFavoritos() == null){
            favoritos = new ArrayList<>();
        } else {
            favoritos = new ArrayList<>(Arrays.asList(user.getFavoritos().split(", ")));
            for (Imovel imovel : listaImoveis) {
                String idImovel = String.valueOf(imovel.getId());
                for (String fav : favoritos) {
                    if (fav.equals(idImovel)){
                        if (listaImoveisFavoritos == null){
                            listaImoveisFavoritos = new ArrayList<>();
                            listaImoveisFavoritos.add(imovel);
                        } else {
                            listaImoveisFavoritos.add(imovel);
                        }
                    }
                }
            }
        }
        if (listaImoveisFavoritos == null) {
            listaImoveisFavoritos = new ArrayList<>();
        } else {
            lvListaImoveis.setAdapter(new ListaImovelAdaptador(getContext(),listaImoveisFavoritos));
        }



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

        return view;
    }
}