package amsi.dei.estg.ipleiria.happy_house.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import java.util.ArrayList;

public class ImovelBDHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ImovelsBD";
    private static final int DB_VERSION = 2;
    private static final String TABLE_NAME = "Imovel";

    private static final String ID = "id";
    private static final String ESTADO = "estado";
    private static final String AREA = "area";
    private static final String N_QUARTOS = "n_quartos";
    private static final String N_WC = "n_wc";
    private static final String PRECO = "preco";
    private static final String DESCRICAO = "descricao";
    private static final String GARAGEM = "garagem";
    private static final String PISO = "piso";
    private static final String MORADA = "morada";
    private static final String CODIGO_POSTAL = "codigo_postal";
    private static final String CIDADE = "cidade";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String ID_USER = "id_user";


    private final SQLiteDatabase db;

    public ImovelBDHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.db = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createImovelTable = "CREATE TABLE " + TABLE_NAME +
                "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ESTADO + " TEXT NOT NULL, " +
                AREA + " INTEGER NOT NULL, " +
                N_QUARTOS + " INTEGER NOT NULL, " +
                N_WC + " INTEGER NOT NULL, " +
                PRECO + " DOUBLE NOT NULL, " +
                DESCRICAO + " TEXT NOT NULL, " +
                GARAGEM + " INTEGER NOT NULL, " +
                PISO + " INTEGER NOT NULL, " +
                MORADA + " TEXT NOT NULL, " +
                CODIGO_POSTAL + " TEXT NOT NULL, " +
                CIDADE + " TEXT NOT NULL, " +
                LATITUDE + " TEXT NOT NULL, " +
                LONGITUDE + " TEXT NOT NULL, " +
                ID_USER + " INTEGER NOT NULL" +
                ");";
        sqLiteDatabase.execSQL(createImovelTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public ArrayList<Imovel> getAllImoveisBD(){

        ArrayList<Imovel> imoveis = new ArrayList<>();
        Cursor cursor = this.db.query(TABLE_NAME, new String[]{ID, ESTADO, AREA, N_QUARTOS, N_WC, PRECO, DESCRICAO, GARAGEM, PISO, MORADA, CODIGO_POSTAL, CIDADE, LATITUDE, LONGITUDE, ID_USER},
                null, null, null, null, null);

        if (cursor.moveToFirst()){
            do{
                Imovel auxImovel = new Imovel(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getString(6),
                        cursor.getInt(7), cursor.getInt(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getInt(14));
                imoveis.add(auxImovel);
            }while (cursor.moveToNext());
        }
        return imoveis;
    }

    public void adicionarImovelBD(Imovel imovel){
        ContentValues values = new ContentValues();
        values.put(ESTADO, imovel.getEstado());
        values.put(AREA, imovel.getArea());
        values.put(N_QUARTOS, imovel.getNquartos());
        values.put(N_WC, imovel.getNwc());
        values.put(PRECO, imovel.getPreco());
        values.put(DESCRICAO, imovel.getDescricao());
        values.put(GARAGEM, imovel.getGaragem());
        values.put(PISO, imovel.getPiso());
        values.put(MORADA, imovel.getMorada());
        values.put(CODIGO_POSTAL, imovel.getCodigo_postal());
        values.put(CIDADE, imovel.getCidade());
        values.put(LATITUDE, imovel.getLatitude());
        values.put(LONGITUDE, imovel.getLongitude());
        values.put(ID_USER, imovel.getId_user());

        this.db.insert(TABLE_NAME, null, values);
    }

    public void removerAllImoveisBD(){
        this.db.delete(TABLE_NAME, null, null);
    }
}
