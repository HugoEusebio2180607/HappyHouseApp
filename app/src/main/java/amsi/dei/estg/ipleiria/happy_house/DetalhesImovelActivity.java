package amsi.dei.estg.ipleiria.happy_house;

import amsi.dei.estg.ipleiria.happy_house.modelos.Imovel;
import amsi.dei.estg.ipleiria.happy_house.modelos.SingletonImovel;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetalhesImovelActivity extends AppCompatActivity /*implements OnMapReadyCallback*/ {

    public static final String ID = "ID";
    private Imovel imovel;
    private TextView tvEstado, tvArea, tvWCs, tvQuartos, tvGaragem, tvPiso;
    private ImageView imgCapa;
    private Button btnMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_imovel);

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync((OnMapReadyCallback) this);

        int id = getIntent().getIntExtra(ID, -1);
        imovel = SingletonImovel.getInstance(getApplicationContext()).getImovel(id);


        tvEstado = findViewById(R.id.tvEstado);
        tvArea = findViewById(R.id.tvArea);
        tvWCs = findViewById(R.id.tvWC);
        tvQuartos = findViewById(R.id.tvQuartos);
        tvGaragem = findViewById(R.id.tvGaragem);
        tvPiso = findViewById(R.id.tvPiso);
        imgCapa = findViewById(R.id.imageView2);

        btnMapa = findViewById(R.id.btnMap);
        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                //intent.putExtra(MapsActivity.GPS, gps);
                startActivity(intent);
            }
        });

        setTitle("Detalhes: ");
        //carregarDetalhesCasa();
    }

    private void carregarDetalhesCasa() {

        tvEstado.setText(imovel.getEstado());
        tvArea.setText(imovel.getArea());
        tvWCs.setText(imovel.getNwc());
        tvQuartos.setText(imovel.getNquartos());
        tvGaragem.setText(imovel.getGaragem());
        tvPiso.setText(imovel.getPiso());
        imgCapa.setImageResource(imovel.getImagem());

    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        // Add a marker in Sydney, Australia,
//        // and move the map's camera to the same location.
//        LatLng sydney = new LatLng(-33.852, 151.211);
//        googleMap.addMarker(new MarkerOptions().position(sydney)
//                .title("Marker in Sydney"));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//    }
}