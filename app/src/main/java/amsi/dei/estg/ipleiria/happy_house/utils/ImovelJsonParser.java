package amsi.dei.estg.ipleiria.happy_house.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.happy_house.modelos.Imovel;

public class ImovelJsonParser {
    public static ArrayList<Imovel> parserJsonImoveis(JSONArray response, Context context){
        System.out.println("--> PARSER LISTA IMOVEIS: " + response);
        ArrayList<Imovel> tempListaImoveis = new ArrayList<Imovel>();

        try {
            for (int i= 0; i < response.length(); i++){
                JSONObject imovel = (JSONObject) response.get(i);
                int id = imovel.getInt("id");
                String estado = imovel.getString("estado");
                int area = imovel.getInt("area");
                int n_quartos = imovel.getInt("n_quartos");
                int n_wc = imovel.getInt("n_wc");
                int preco = imovel.getInt("preco");
                String descricao = imovel.getString("descricao");
                int garagem = imovel.getInt("garagem");
                int piso = imovel.getInt("piso");
                String morada = imovel.getString("morada");
                String codigo_postal = imovel.getString("codigo_postal");
                String cidade = imovel.getString("cidade");
                String latitude = imovel.getString("latitude");
                String longitude = imovel.getString("longitude");
                int id_user = imovel.getInt("id_user");

                Imovel auxImovel = new Imovel(id, estado, area, n_quartos, n_wc, preco, descricao,  garagem, piso, morada, codigo_postal, cidade, latitude, longitude, id_user);
                tempListaImoveis.add(auxImovel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return tempListaImoveis;

    }

    public static Imovel parserJsonImoveis(String response, Context context){
        System.out.println("--> PARSER ADICIONAR: " + response);
        Imovel auxImovel = null;

        try {
            JSONObject imovel = new JSONObject(response);

            int id = imovel.getInt("id");
            String estado = imovel.getString("estado");
            int area = imovel.getInt("area");
            int n_quartos = imovel.getInt("n_quartos");
            int n_wc = imovel.getInt("n_wc");
            int preco = imovel.getInt("preco");
            String descricao = imovel.getString("descricao");
            int garagem = imovel.getInt("garagem");
            int piso = imovel.getInt("piso");
            String morada = imovel.getString("morada");
            String codigo_postal = imovel.getString("codigo_postal");
            String cidade = imovel.getString("cidade");
            String latitude = imovel.getString("latitude");
            String longitude = imovel.getString("longitude");
            int id_user = imovel.getInt("id_user");

            auxImovel = new Imovel(id, estado, area, n_quartos, n_wc, preco, descricao,  garagem, piso, morada, codigo_postal, cidade, latitude, longitude, id_user);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return auxImovel;
    }

    public static boolean isConnectionInternet (Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
