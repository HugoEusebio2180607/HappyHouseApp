package amsi.dei.estg.ipleiria.happy_house;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistarActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etEmail;
    private EditText etTelefone;
    private EditText etPassword;
    private String email;

    private static String URL_REGISTER ="http://10.0.2.2:8888/user/";

    private Button btn_registar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);


        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        etTelefone=findViewById(R.id.etTelefone);
        etNome=findViewById(R.id.etNome);
        btn_registar=findViewById(R.id.btn_registar);


        btn_registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registar();
            }
        });


    }


    private void Registar(){


        final String nome = this.etNome.getText().toString().trim();
        final String email = this.etEmail.getText().toString().trim();
        final String password = this.etPassword.getText().toString().trim();
        final String telemovel = this.etTelefone.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(RegistarActivity.this,response,Toast.LENGTH_SHORT).show();

                        }
                    },
                    new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegistarActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("nome",nome);
                params.put("email",email);
                params.put("password",password);
                params.put("telemovel",telemovel);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    /*public void onClickRegistar(View view) {

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
*/




}