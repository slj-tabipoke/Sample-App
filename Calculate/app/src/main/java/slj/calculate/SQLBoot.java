package slj.calculate;
/**
 * Created by slj on 2015/06/21.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLBoot extends SQLiteOpenHelper
{
    public SQLBoot(Context context){
        super(context,"Calculates", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // DBの生成
        try {
            db.execSQL("CREATE TABLE Units( id integer primary key, name text not null,delete_flag integer not null, create_date text not null, update_date not null );");
        }
        catch (Exception e)
        {
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
