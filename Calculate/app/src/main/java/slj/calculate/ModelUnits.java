package slj.calculate;
/**
 * Created by slj on 2015/06/21.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ModelUnits
{
    public Context CalculateContext;
    // コンストラクター
    // CLASSが生成される時に呼び出し、main activityの保存と、データを1件登録する
    public ModelUnits(Context context)
    {
        CalculateContext = context; // Main activityのContextを保存
        // DBにデータが無ければ1件登録
        SQLBoot helper = new SQLBoot(CalculateContext);               /* DBのインスタンス生成 */
        SQLiteDatabase db = helper.getReadableDatabase();                  /* 読み込み可能なDBをインスタンス生成 */
        try{
            Cursor cursor = db.rawQuery(this.getSelectString(), null);
            // DBにデータが無ければ1件登録
            if( cursor.getCount() == 0 )
            {
                // 初期データの登録
                db.execSQL("INSERT INTO Units values(null,'cm', 0, datetime('now', 'localtime'), datetime('now', 'localtime'));");
                db.execSQL("INSERT INTO Units values(null,'L',  0, datetime('now', 'localtime'), datetime('now', 'localtime'));");
                db.execSQL("INSERT INTO Units values(null,'Kg', 0, datetime('now', 'localtime'), datetime('now', 'localtime'));");
            }
        }
        finally {
            // DBへの接続を切断
            db.close();
        }
    }

    // Max値を取得
    public String[] findAll(){
        // DB接続インスタンス生成
        SQLBoot helper = new SQLBoot(CalculateContext);  // DBのインスタンス生成
        SQLiteDatabase db = helper.getReadableDatabase();   // 読み込み可能なDBをインスタンス生成
        String[] ret;                                       // 変数の宣言
        int i = 0;
        try{
            // DBへQUERYを投げる
            Cursor cursor = db.rawQuery(this.getSelectString(), null);
            ret = new String[cursor.getCount()];
            // SELECTしたデータを取得
            while (cursor.moveToNext()) {
                ret[i] =  " - " + cursor.getString(0) + " - ";
                i++;
            }
        }
        finally {
            // DBへの接続を切断
            db.close();
        }
        return  ret;// 実行結果を返却
    }
    // データを1件取得するSQL文を生成
    private String getSelectString()
    {
        //SQL作成
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT");
        sql.append(" name");
        sql.append(" FROM Units");
        sql.append(" WHERE delete_flag = 0 ORDER BY id;");
        return sql.toString();
    }


}
