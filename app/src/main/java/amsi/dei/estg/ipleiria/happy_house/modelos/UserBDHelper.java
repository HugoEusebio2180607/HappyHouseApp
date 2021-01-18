package amsi.dei.estg.ipleiria.happy_house.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class UserBDHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "UserBD";
    private static final int DB_VERSION = 2;
    private static final String TABLE_NAME = "User";

    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String NIF = "nif";
    private static final String EMAIL = "email";
    private static final String PASSWORD_HASH = "password_hash";
    private static final String TELEMOVEL = "telemovel";
    private static final String FAVORITOS = "favoritos";


    private final SQLiteDatabase db;

    public UserBDHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUserTable = "CREATE TABLE " + TABLE_NAME +
                "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERNAME + " TEXT NOT NULL, " +
                NIF + " INTEGER NOT NULL, " +
                EMAIL + " STRING NOT NULL, " +
                PASSWORD_HASH + " STRING NOT NULL, " +
                TELEMOVEL + " INTEGER NOT NULL, " +
                FAVORITOS + "TEXT  NOT NULL" +
                ");";
        sqLiteDatabase.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public ArrayList<User> getAllUserBD(){

        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = this.db.query(TABLE_NAME, new String[]{ID, USERNAME,NIF,EMAIL,PASSWORD_HASH,TELEMOVEL},
                null, null, null, null, null);

        if (cursor.moveToFirst()){
            do{
                User auxUser = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5));
                users.add(auxUser);
            }while (cursor.moveToNext());
        }
        return users;
    }

    public void adicionarUserBD(User user) {
        ContentValues values = new ContentValues();
        values.put(USERNAME,user.getUsername());
        values.put(PASSWORD_HASH,user.getPassword_hash());
        values.put(TELEMOVEL,user.getTelemovel());

        this.db.insert(TABLE_NAME, null, values);
    }

    public void removerAllUsersBD() {
        this.db.delete(TABLE_NAME, null, null);
    }
}