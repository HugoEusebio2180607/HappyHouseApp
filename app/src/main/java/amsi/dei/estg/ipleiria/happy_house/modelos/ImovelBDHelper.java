package amsi.dei.estg.ipleiria.happy_house.modelos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ImovelBDHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ImovelsBD";
    private static final int DB_VERSION = 1;

    private final SQLiteDatabase db;

    public ImovelBDHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.db = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
