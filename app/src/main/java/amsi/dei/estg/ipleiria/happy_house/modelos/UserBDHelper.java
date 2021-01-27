package amsi.dei.estg.ipleiria.happy_house.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

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
                EMAIL + " TEXT NOT NULL, " +
                PASSWORD_HASH + " TEXT NOT NULL, " +
                TELEMOVEL + " INTEGER  NOT NULL, " +
                FAVORITOS + " TEXT " +
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
        Cursor cursor = this.db.query(TABLE_NAME, new String[]{ID, USERNAME, NIF, EMAIL, PASSWORD_HASH, TELEMOVEL, FAVORITOS},
                null, null, null, null, null);

        if (cursor.moveToFirst()){
            do{
                User auxUser = new User(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9));
                users.add(auxUser);
            }while (cursor.moveToNext());
        }
        return users;
    }

    public void adicionarUserBD(User user) {
        ContentValues values = new ContentValues();
        values.put(USERNAME,user.getUsername());
        values.put(PASSWORD_HASH,user.getPassword_hash());
        values.put(NIF,user.getNif());
        values.put(EMAIL,user.getEmail());
        values.put(TELEMOVEL,user.getTelemovel());
        values.putNull(FAVORITOS);

        this.db.insert(TABLE_NAME, null, values);
    }

    public void removerAllUsersBD() {
        this.db.delete(TABLE_NAME, null, null);
    }
}