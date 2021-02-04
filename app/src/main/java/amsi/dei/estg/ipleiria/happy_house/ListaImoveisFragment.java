package amsi.dei.estg.ipleiria.happy_house;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;

import amsi.dei.estg.ipleiria.happy_house.listeners.ImoveisListener;
import amsi.dei.estg.ipleiria.happy_house.utils.ImovelJsonParser;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import amsi.dei.estg.ipleiria.happy_house.R;
import amsi.dei.estg.ipleiria.happy_house.adaptadores.ListaImovelAdaptador;
import amsi.dei.estg.ipleiria.happy_house.modelos.Imovel;
import amsi.dei.estg.ipleiria.happy_house.modelos.SingletonImovel;


public class ListaImoveisFragment extends Fragment implements ImoveisListener {

    private ListView lvListaImoveis;
    private ArrayList<Imovel>listaImoveis;
    private ListaImovelAdaptador listaImovelAdaptador;
    private MenuItem menuCidadeFiltro, menuQuartosFiltro, menuEstadoFiltro, menuPrecoFiltro;
    private String[] listaCidades, nQuartos, nEstado;
    private boolean[] checkedCidades, checkedQuartos, checkedEstado;
    private ArrayList<Integer> userCidades = new ArrayList<>();
    private ArrayList<Integer> userQuartos = new ArrayList<>();
    private ArrayList<Integer> userEstado = new ArrayList<>();

    File root;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_lista_imoveis, container, false);
        lvListaImoveis = view.findViewById(R.id.lvListaImoveis);
        //listaImoveis= SingletonImovel.getInstance(getContext()).getImovels();
        //lvListaImoveis.setAdapter(new ListaImovelAdaptador(getContext(),listaImoveis));

        root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);


        listaCidades = getResources().getStringArray(R.array.cidades);
        checkedCidades = new boolean[listaCidades.length];
        nQuartos = getResources().getStringArray(R.array.quartos);
        checkedQuartos = new boolean[nQuartos.length];
        nEstado = getResources().getStringArray(R.array.estadoList);
        checkedEstado = new boolean[nEstado.length];


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
        //menuPrecoFiltro = menu.findItem(R.id.itemPrecoFiltro);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        //int id = item.getItemId();


        switch (item.getItemId()) {
            case R.id.itemCidadeFiltro:
                 new AlertDialog.Builder(getContext())
                        .setTitle(R.string.cidade)
                        .setMultiChoiceItems(listaCidades, checkedCidades, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                                int idx = userCidades.indexOf(position);
                                System.out.println(idx);
                                if (idx == -1 && isChecked) {
                                    userCidades.add(position);
                                } else {
                                    userCidades.remove(idx);
                                }
                            }
                        })
                        .setCancelable(false)
                        .setPositiveButton(R.string.confirmar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {

                                ArrayList<Imovel> tempLista = new ArrayList<>();
                                ArrayList<String> auxLista = new ArrayList<>();

                                if (userCidades == null){
                                    dialogInterface.dismiss();
                                } else {

                                    ArrayList<String> list = new ArrayList<>(Arrays.asList("Aveiro", "Beja", "Braga", "Bragança", "Castelo Branco", "Coimbra", "Évora", "Faro", "Guarda", "Leiria", "Lisboa", "Portalegre", "Porto", "Santarém", "Setúbal", "Viana Do Castelo", "Vila Real", "Viseu"));

                                    for (int i=0; i<userCidades.size(); i++){
                                            String aux = list.get(userCidades.get(i));
                                            auxLista.add(aux);

                                    }
                                    System.out.println("--->"+ auxLista);

                                    for (Imovel tempImovel : SingletonImovel.getInstance(getContext()).getImoveisBD()){
                                        for (String cidade : auxLista){
                                            if (tempImovel.getCidade().toLowerCase().contentEquals(cidade.toLowerCase())){
                                                tempLista.add(tempImovel);
                                            }
                                            /*if (tempImovel.getCidade().compareToIgnoreCase(cidade) == 0){
                                                tempLista.add(tempImovel);
                                            }*/
                                        }

                                    }
                                    System.out.println("templist -->"+tempLista);

                                }

                                if (auxLista.isEmpty()){
                                    SingletonImovel.getInstance(getContext()).getAllImoveisAPI(getContext(), ImovelJsonParser.isConnectionInternet(getContext()));
                                } else {
                                    lvListaImoveis.setAdapter(new ListaImovelAdaptador(getContext(), tempLista));
                                }


                            }
                        })
                        .setNeutralButton(R.string.limpar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                if (userCidades == null){
                                    dialogInterface.dismiss();
                                } else {
                                    for (int i = 0; i < checkedCidades.length; i++) {
                                        checkedCidades[i] = false;
                                        userCidades.clear();
                                    }
                                }
                                System.out.println("sai");
                                SingletonImovel.getInstance(getContext()).getAllImoveisAPI(getContext(), ImovelJsonParser.isConnectionInternet(getContext()));
                            }
                        })
                         .setNegativeButton("PDF", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialogInterface, int i) {
                                 createPDF();
                                 dialogInterface.dismiss();
                             }
                         })
                         .show();



                break;
            case R.id.itemQuartosFiltro:
                Toast.makeText(getContext(), "Quartos", Toast.LENGTH_SHORT).show();

                new AlertDialog.Builder(getContext())
                        .setTitle(R.string.quartos)
                        .setMultiChoiceItems(nQuartos, checkedQuartos, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                                int idx = userQuartos.indexOf(position);
                                if (idx == -1 && isChecked){
                                    userQuartos.add(position);
                                } else {
                                    userQuartos.remove(idx);
                                }
                            }
                        })
                        .setCancelable(false)
                        .setPositiveButton(R.string.confirmar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {

                                ArrayList<Imovel> tempLista = new ArrayList<>();
                                ArrayList<Integer> auxLista = new ArrayList<>();

                                if (userQuartos == null){
                                    dialogInterface.dismiss();
                                } else {

                                    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));

                                    for (int i=0; i<userQuartos.size(); i++){
                                        int aux = list.get(userQuartos.get(i));
                                        auxLista.add(aux);

                                    }
                                    System.out.println("--->"+ auxLista);

                                    for (Imovel tempImovel : SingletonImovel.getInstance(getContext()).getImoveisBD()){
                                        for(int quarto : auxLista){
                                            if (tempImovel.getNquartos() == quarto){
                                                tempLista.add(tempImovel);
                                            }
                                        }
                                    }
                                }
                                if (auxLista.isEmpty()){
                                    SingletonImovel.getInstance(getContext()).getAllImoveisAPI(getContext(), ImovelJsonParser.isConnectionInternet(getContext()));
                                } else {
                                    lvListaImoveis.setAdapter(new ListaImovelAdaptador(getContext(), tempLista));
                                }


                            }
                        })
                        .setNeutralButton(R.string.limpar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                if (userQuartos == null){
                                    dialogInterface.dismiss();
                                } else {
                                    for (int i = 0; i < checkedQuartos.length; i++) {
                                        checkedQuartos[i] = false;
                                        userQuartos.clear();
                                    }
                                }
                                SingletonImovel.getInstance(getContext()).getAllImoveisAPI(getContext(), ImovelJsonParser.isConnectionInternet(getContext()));
                            }
                        })
                        .show();

                break;
            case R.id.itemEstadoFiltro:
                Toast.makeText(getContext(), "Estado", Toast.LENGTH_SHORT).show();

                new AlertDialog.Builder(getContext())
                        .setTitle(R.string.estado)
                        .setMultiChoiceItems(nEstado, checkedEstado, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                                int idx = userEstado.indexOf(position);
                                if (idx == -1 && isChecked){
                                    userEstado.add(position);
                                } else {
                                    userEstado.remove(idx);
                                }
                            }
                        })
                        .setCancelable(false)
                        .setPositiveButton(R.string.confirmar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {

                                ArrayList<Imovel> tempLista = new ArrayList<>();
                                ArrayList<String> auxLista = new ArrayList<>();

                                if (userEstado == null){
                                    dialogInterface.dismiss();
                                } else {

                                    ArrayList<String> list = new ArrayList<>(Arrays.asList("Novo", "Usado", "Renovado"));

                                    for (int i=0; i<userEstado.size(); i++){
                                        String aux = list.get(userEstado.get(i));
                                        auxLista.add(aux);

                                    }
                                    System.out.println("--->"+ auxLista);

                                    for (Imovel tempImovel : SingletonImovel.getInstance(getContext()).getImoveisBD()){
                                        for(String estado : auxLista){
                                            if (tempImovel.getEstado().equals(estado)){
                                                tempLista.add(tempImovel);
                                            }
                                        }
                                    }
                                }
                                if (auxLista.isEmpty()){
                                    SingletonImovel.getInstance(getContext()).getAllImoveisAPI(getContext(), ImovelJsonParser.isConnectionInternet(getContext()));
                                } else {
                                    lvListaImoveis.setAdapter(new ListaImovelAdaptador(getContext(), tempLista));
                                }


                            }
                        })
                        .setNeutralButton(R.string.limpar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                if (userEstado == null){
                                    dialogInterface.dismiss();
                                } else {
                                    for (int i = 0; i < checkedEstado.length; i++) {
                                        checkedEstado[i] = false;
                                        userEstado.clear();
                                    }
                                }
                                SingletonImovel.getInstance(getContext()).getAllImoveisAPI(getContext(), ImovelJsonParser.isConnectionInternet(getContext()));
                            }
                        })
                        .show();

                break;
            /*case R.id.itemPrecoFiltro:
                Toast.makeText(getContext(), "Preco", Toast.LENGTH_SHORT).show();
                break;*/
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

    public void createPDF(){

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);

        // Create a new font object selecting one of the PDF base fonts
        PDFont font = PDType1Font.HELVETICA;

        try{
            // Define a content stream for adding to the PDF
            PDPageContentStream content = new PDPageContentStream(doc,page);

            // Write listaCidades
            content.beginText();
            content.setFont(font, 20);

            content.newLineAtOffset(20, 450);
            content.setLeading(18f);

            for (String cidade : listaCidades) {
                content.showText(cidade);
                content.newLine();
            }

            content.endText();

            // Make sure that the content stream is closed:
            content.close();

            // Save the final pdf document to a file
            String path = root.getAbsolutePath() + "/Created.pdf";
            doc.save(path);
            doc.close();
            Toast.makeText(getActivity(), "PDF Guardado em " + path, Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Log.e("PdfBox-Android-Sample", "Exception thrown while creating PDF", e);
        }
    }
}