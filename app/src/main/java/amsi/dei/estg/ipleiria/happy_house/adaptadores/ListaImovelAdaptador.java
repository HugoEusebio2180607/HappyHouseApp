package amsi.dei.estg.ipleiria.happy_house.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.happy_house.R;
import amsi.dei.estg.ipleiria.happy_house.modelos.Imovel;

public class ListaImovelAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Imovel> imovels;

    public  ListaImovelAdaptador(Context context,ArrayList<Imovel> imovels){
        this.context = context;
        this.imovels = imovels;
    }


    @Override
    public int getCount() { return imovels.size(); }

    @Override
    public Object getItem(int position) { return  imovels.get(position); }

    @Override
    public long getItemId(int position) { return imovels.get(position).getId(); }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        if (inflater==null)
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertview==null)
            convertview=inflater.inflate(R.layout.activity_listade_casas,null);

        ViewHolderLista viewHolder=(ViewHolderLista)convertview.getTag();
        if (viewHolder==null){
            viewHolder= new ViewHolderLista(convertview);
            convertview.setTag(viewHolder);
        }

        viewHolder.update(imovels.get(position));

        return convertview;
    }

    public void refresh(ArrayList<Imovel> listaImoveis) {
        this.imovels = listaImoveis;
        notifyDataSetChanged();
    }

    private class ViewHolderLista{
        private TextView tvCidade, tvnwc, tvnquartos ,tvEstado;
        private ImageView imgImovel;

        public ViewHolderLista(View view){
            tvCidade=view.findViewById(R.id.tvCidade);
            tvnwc=view.findViewById(R.id.tvnwc);
            tvnquartos=view.findViewById(R.id.tvnquartos);
            tvEstado=view.findViewById(R.id.tvEstado);
            imgImovel=view.findViewById(R.id.imgImovel);
        }

        private void update(Imovel imovel){
            tvCidade.setText(imovel.getCidade());
            tvnwc.setText(""+imovel.getNwc());
            tvnquartos.setText(""+imovel.getNquartos());
            tvEstado.setText(imovel.getEstado());
            //imgImovel.setImageResource(imovel.getImagem());

            Glide.with(context)
                    .load(R.drawable.casa)
                    .placeholder(R.drawable.logohappyhouse)
                    .thumbnail(0f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgImovel);

        }
    }
}
