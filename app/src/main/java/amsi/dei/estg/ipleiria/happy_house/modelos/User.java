package amsi.dei.estg.ipleiria.happy_house.modelos;

import java.util.Date;

public class User {

    private int id,telemovel,nif;
    private String username,email,password_hash,favoritos,auth_Key,created_at,updated_at;

    public int getId() {return id; }

    public void setId(int id) { this.id = id; }

    public String getUsername() {return username; }

    public void setUsername(String username) { this.username = username; }

    public int getTelemovel() { return telemovel; }

    public void setTelemovel(int telemovel) { this.telemovel = telemovel; }

    public int getNif() { return nif; }

    public void setNif(int nif) { this.nif = nif; }

    public String getPassword_hash() { return password_hash; }

    public void setPassword_hash(String password_hash) { this.password_hash = password_hash;}

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getFavoritos() { return favoritos; }

    public String getAuth_Key() {
        return auth_Key;
    }

    public void setAuthKey(String auth_Key) {
        this.auth_Key = auth_Key;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setFavoritos(String favoritos) { this.favoritos = favoritos; }

    public User(int id, String username, int nif, String email, String password_hash, int telemovel, String favoritos, String auth_Key, String created_at, String updated_at) {
        this.id = id;
        this.username = username;
        this.nif = nif;
        this.email = email;
        this.password_hash = password_hash;
        this.telemovel = telemovel;
        this.favoritos = favoritos;
        this.auth_Key = auth_Key;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}