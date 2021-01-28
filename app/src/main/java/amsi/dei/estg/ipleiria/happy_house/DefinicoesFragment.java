package amsi.dei.estg.ipleiria.happy_house;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import amsi.dei.estg.ipleiria.happy_house.modelos.SingletonImovel;
import amsi.dei.estg.ipleiria.happy_house.modelos.User;
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

    public static final String CHAVE_ID = "ID";
    public static final String SECCAO_INFO_USER = "SECCAO_INFO_USER";
    public static final String CHAVE_USERNAME = "USERNAME";
    public static final String CHAVE_PASSWORD = "PASSWORD";
    public static final String CHAVE_TELEMOVEL = "TELEMOVEL";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private User user;

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

        String username = sharedPreferences.getString(CHAVE_USERNAME, null);
        String password = sharedPreferences.getString(CHAVE_PASSWORD, null);
        int telemovel = sharedPreferences.getInt(CHAVE_TELEMOVEL, -1);
        int id = sharedPreferences.getInt(CHAVE_ID, -1);

        editTextUsername.setHint(username);
        editTextPassword.setHint(password);
        editTextTelemovel.setHint(String.valueOf(telemovel));

        user = SingletonImovel.getInstance(getContext()).getUser(id);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextUsername.length()>0 || editTextUsername.getHint().toString().length()>0 && editTextPassword.length()>0 || editTextPassword.getHint().toString().length()>0 && editTextTelemovel.length()>0 || editTextTelemovel.getHint().toString().length()>0 ){
                    SingletonImovel.getInstance(getContext()).editarUserAPI(editarUser(), getContext());
                    editTextUsername.getText().clear();
                    editTextPassword.getText().clear();
                    editTextTelemovel.getText().clear();
                }
            }
        });

        return view;
    }

    private User editarUser(){
        if (editTextUsername.getText().toString().isEmpty()){
            user.setUsername(user.getUsername());
        } else {
            user.setUsername(editTextUsername.getText().toString());
        }

        if (editTextPassword.getText().toString().isEmpty()){
            user.setPassword_hash(user.getPassword_hash());
        } else {
            user.setPassword_hash(editTextPassword.getText().toString());
        }

        if (editTextTelemovel.getText().toString().isEmpty()){
            user.setTelemovel(user.getTelemovel());
        } else {
            user.setTelemovel(Integer.parseInt(editTextTelemovel.getText().toString()));
        }

        return user;
    }

}