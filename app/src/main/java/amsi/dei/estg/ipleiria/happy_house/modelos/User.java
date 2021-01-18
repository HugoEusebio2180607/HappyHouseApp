package amsi.dei.estg.ipleiria.happy_house.modelos;

public class User {

    private int id,telemovel,nif;
    private String username,email,password_hash,favoritos;

    public User(int id, String username, String password_hash, int telemovel, int nif, String email) {
    }

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

    public void setFavoritos(String favoritos) { this.favoritos = favoritos; }

    public User(int i, String s, String string, int cursorInt) {
        this.id = id;
        this.username = username;
        this.nif = nif;
        this.email = email;
        this.password_hash = password_hash;
        this.telemovel = telemovel;
        this.favoritos = favoritos;
    }
}