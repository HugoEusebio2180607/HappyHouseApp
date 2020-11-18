package amsi.dei.estg.ipleiria.happy_house;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    public static final String EMAIL = "EMAIL";

    private EditText editTextEmail;
    private EditText editTextPassword;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView btn=findViewById(R.id.tvtLogin);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(LoginActivity.this,RegistarActivity.class));
            }
        });

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPW);
    }

    public void onClickLogin(View view) {

        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (!isEmailValido(email)){
            editTextEmail.setError(getString(R.string.msg_valid_email));
            return;
        }

        if (!isPasswordValida(password)) {
            editTextPassword.setError(getString(R.string.msg_valid_pw));
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.CHAVE_EMAIL, email);
        startActivity(intent);
        finish();

    }

    public boolean isEmailValido(String email){
        if (email == null) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isPasswordValida(String password){
        if (password == null) {
            return false;
        }
        return password.length() > 4;
    }
}