package amsi.dei.estg.ipleiria.happy_house;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.happy_house.R;


public class ListaImoveisFragment extends Fragment {

    private ListView lvListaImoveis;
    //private ArrayList<Imoveis>listaImoveis;

    public ListaImoveisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_imoveis, container, false);
        lvListaImoveis = view.findViewById(R.id.lvListaImoveis);
        return view;
    }
}