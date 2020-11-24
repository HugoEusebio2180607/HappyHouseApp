package amsi.dei.estg.ipleiria.happy_house.modelos;

import android.content.Context;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.happy_house.R;

public class SingletonImovel {

    private static SingletonImovel instance = null;
    private ArrayList<Imovel> imovels;

    public static synchronized SingletonImovel getInstance(Context context){
        if (instance == null){
            instance = new SingletonImovel(context);
        }
        return  instance;
    }

    private  SingletonImovel(Context context){
        gerarImovel();
    }

    private void gerarImovel() {
        imovels = new ArrayList<>();
        imovels.add(new Imovel(1,231,3,2,2, R.drawable.casa,"leiria","Renovado","Bom estado","rua de leiria","3100-507",3243242,"Sim"));
        imovels.add(new Imovel(2,233,4,7,3,R.drawable.casa,"leiria","Usado","Otima","rua de coimbra","3100-504",33443242,"Sim"));
        imovels.add(new Imovel(3,241,3,2,2,R.drawable.casa,"leiria","Renovado,","Bom estado","rua de leiria","3100-507",3243242,"Sim"));
    }

    public ArrayList<Imovel> getImovels(){
        return  new ArrayList<>(imovels);
    }

    public Imovel getImovel(int id){
        for (Imovel i: imovels)
            if (i.getId()==id)
                return i;
        return null;
    }

}
