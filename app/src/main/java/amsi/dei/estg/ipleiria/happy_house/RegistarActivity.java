package amsi.dei.estg.ipleiria.happy_house;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

public class RegistarActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etEmail;
    private EditText etTelefone;
    private EditText etPassword;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);

        setTitle("Registar");

        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        etTelefone=findViewById(R.id.etTelefone);
        etNome=findViewById(R.id.etNome);
    }

    public void onClickRegistar(View view) {

        email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String telefone =etTelefone.getText().toString();
        String nome =etNome.getText().toString();

        if (!isEmailValido(email)) {
            etEmail.setError("Email Invalido");
            return;
        }
        if (!isPasswordValida(password)) {
            etEmail.setError("Email Invalido");
            return;
        }
        if(!isTelefoneValida(telefone)){
            etTelefone.setError("Telefone Invalido");
            return;
        }

        Intent intent = new Intent(this,LoginActivity.class);
        intent.putExtra(LoginActivity.EMAIL,email);
        startActivity(intent);
        finish();
    }

    private boolean isTelefoneValida(String telefone) {
        return  Patterns.PHONE.matcher(telefone).matches();
    }

    private boolean isPasswordValida(String password) {
        if(password.isEmpty()){
            return false;
        }
        return password.length()>=4;
    }

    private boolean isEmailValido(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }





}