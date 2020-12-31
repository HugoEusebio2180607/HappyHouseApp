package amsi.dei.estg.ipleiria.happy_house;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class DefinicoesFragment extends Fragment {

    private EditText editTextPassword;
    private EditText editTextNewPassword;
    private EditText editTextTelemovel;
    private Button btnGuardar;

    public DefinicoesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_definicoes, container, false);

        editTextPassword = view.findViewById(R.id.etNewPassword);
        editTextNewPassword = view.findViewById(R.id.etPassword);
        editTextTelemovel = view.findViewById(R.id.ettelemovel);
        btnGuardar = view.findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });

        return view;
    }
}