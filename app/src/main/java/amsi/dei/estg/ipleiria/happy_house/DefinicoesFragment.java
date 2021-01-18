package amsi.dei.estg.ipleiria.happy_house;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class DefinicoesFragment extends Fragment {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextTelemovel;
    private Button btnGuardar;

    public static final String SECCAO_INFO_USER = "SECCAO_INFO_USER";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public DefinicoesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_definicoes, container, false);

        editTextUsername = view.findViewById(R.id.etUsername);
        editTextPassword = view.findViewById(R.id.etPassword);
        editTextTelemovel = view.findViewById(R.id.ettelemovel);
        btnGuardar = view.findViewById(R.id.btnGuardar);

        sharedPreferences = this.getActivity().getSharedPreferences(SECCAO_INFO_USER, Context.MODE_PRIVATE);

        String username = sharedPreferences.getString(SECCAO_INFO_USER, null);
        String password = sharedPreferences.getString(SECCAO_INFO_USER, null);
        //int telemovel = sharedPreferences.getInt(SECCAO_INFO_USER, -1);

        //editTextUsername.setHint(username);
        editTextPassword.setHint(password);
        //editTextTelemovel.setHint(telemovel);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextUsername.length()>0 && editTextUsername.getHint().toString().length()>0 && editTextPassword.length()>0 && editTextPassword.getHint().toString().length()>0 && editTextTelemovel.length()>0 && editTextTelemovel.getHint().toString().length()>0 ){

                }
            }
        });

        return view;
    }

}