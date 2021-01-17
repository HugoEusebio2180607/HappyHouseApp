package amsi.dei.estg.ipleiria.happy_house.modelos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ImagensBDHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ImagensBD";
    private static final int DB_VERSION = 2;
    private static final String TABLE_NAME = "Imagens";

    private final SQLiteDatabase database;

    public ImagensBDHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
