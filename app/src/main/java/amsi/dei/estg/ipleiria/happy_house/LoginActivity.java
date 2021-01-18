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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import amsi.dei.estg.ipleiria.happy_house.modelos.SingletonImovel;
import amsi.dei.estg.ipleiria.happy_house.modelos.User;
import amsi.dei.estg.ipleiria.happy_house.utils.ImovelJsonParser;
import amsi.dei.estg.ipleiria.happy_house.utils.UserJsonParser;

public class LoginActivity extends AppCompatActivity {
    public static final String EMAIL = "EMAIL";
    private ArrayList<User>auxUser = new ArrayList<>();
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button btn_login;

    private static String URL_LOGIN ="http://10.0.2.2:8888/user/";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView btn=findViewById(R.id.tvLogin);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(LoginActivity.this,RegistarActivity.class));
            }
        });

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPW);
        btn_login=findViewById(R.id.btn_registar);

        SingletonImovel.getInstance(this).getAllUsersAPI(this, UserJsonParser.isConnectionInternet(this));



        /*btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUsername = editTextUsername.getText().toString().trim();
                String mPass = editTextPassword.getText().toString().trim();

                for(User tempUser: SingletonImovel.getInstance(getApplicationContext()).getUsersBD()){
                    auxUser.add(tempUser);
                }

                if (!mUsername.isEmpty() || !mPass.isEmpty()){
                   // Login(mUsername,mPass);
                    for(User checkUser: SingletonImovel.getInstance(getApplicationContext()).getUsersBD()){
                       if (checkUser.getUsername().toLowerCase().contentEquals(mUsername.toLowerCase())){
                           int position = -1;
                           position=auxUser.indexOf(mUsername);
                           Toast.makeText(LoginActivity.this, "username", Toast.LENGTH_SHORT).show();
                          if (checkUser.getPassword_hash().toLowerCase().contentEquals(mPass.toLowerCase())){
                              int positionpass = -1;
                              positionpass=auxUser.indexOf(mPass);
                              Toast.makeText(LoginActivity.this, "password", Toast.LENGTH_SHORT).show();
                              if (position==positionpass){
                                  Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                  intent.putExtra(MainActivity.CHAVE_USERNAME, mUsername);
                                  startActivity(intent);
                                  finish();
                              }
                           }

                       }
                    }
                }else {
                    editTextUsername.setError("erro");
                    editTextPassword.setError("erro");
                }
            }


        });*/
    }

    private void Login(final String username, final String password){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String sucess =jsonObject.getString("sucess");
                            JSONArray jsonArray = jsonObject.getJSONArray("");


                                for (int i  = 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String username = object.getString("username").trim();
                                    String password =object.getString("password").trim();

                                    Toast.makeText(LoginActivity.this,"boa" + username,Toast.LENGTH_SHORT).show();
                                }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this,"Error" +e.toString(),Toast.LENGTH_SHORT).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,"Error" +error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void onClickLogin(View view) {

        String mUsername = editTextUsername.getText().toString().trim();
        String mPass = editTextPassword.getText().toString().trim();

        for(User tempUser: SingletonImovel.getInstance(getApplicationContext()).getUsersBD()){
            auxUser.add(tempUser);
        }

        if (!mUsername.isEmpty() || !mPass.isEmpty()){
            // Login(mUsername,mPass);
            for(User checkUser: SingletonImovel.getInstance(getApplicationContext()).getUsersBD()){
                if (checkUser.getUsername().toLowerCase().contentEquals(mUsername.toLowerCase())){
                    int position = -1;
                    position=auxUser.indexOf(mUsername);
                    Toast.makeText(LoginActivity.this, "username", Toast.LENGTH_SHORT).show();
                    if (checkUser.getPassword_hash().toLowerCase().contentEquals(mPass.toLowerCase())){
                        int positionpass = -1;
                        positionpass=auxUser.indexOf(mPass);
                        int mTelemovel = checkUser.getTelemovel();
                        Toast.makeText(LoginActivity.this, "password", Toast.LENGTH_SHORT).show();
                        if (position==positionpass){
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            intent.putExtra(MainActivity.CHAVE_USERNAME, mUsername);
                            intent.putExtra(MainActivity.CHAVE_PASSWORD, mPass);
                            intent.putExtra(MainActivity.CHAVE_TELEMOVEL, mTelemovel);
                            startActivity(intent);
                            finish();
                        }
                    }

                }
            }
        }else {
            editTextUsername.setError("erro");
            editTextPassword.setError("erro");
        }



        /*Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.CHAVE_EMAIL, email);
        startActivity(intent);
        finish();*/

    }
/*
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
    }*/
}