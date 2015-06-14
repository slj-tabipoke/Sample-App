package com.example.slj.testdroid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by slj on 2015/06/14.
 */
public class ModelMax {

    public Context TestDroidContext;

    // �R���X�g���N�^�[
    // CLASS����������鎞�ɌĂяo���Amain activity�̕ۑ��ƁA�f�[�^��1���o�^����
    public ModelMax(Context context)
    {
        TestDroidContext = context; // Main activity��Context��ۑ�

        // DB�Ƀf�[�^���������1���o�^
        SqlLight helper = new SqlLight(TestDroidContext);               /* DB�̃C���X�^���X���� */
        SQLiteDatabase db = helper.getReadableDatabase();                  /* �ǂݍ��݉\��DB���C���X�^���X���� */
        try{
            Cursor cursor = db.rawQuery(this.getSelectString(), null);
            // DB�Ƀf�[�^���������1���o�^
            if( cursor.getCount() == 0 )
            {
                db.execSQL(this.getInsertString());         // INSERT�����擾���f�[�^��o�^
            }
        }
        finally {
            // DB�ւ̐ڑ���ؒf
            db.close();
        }
    }

    // Max�l���擾
    public String getMax(){
        // DB�ڑ��C���X�^���X����
        SqlLight helper = new SqlLight(TestDroidContext);     // DB�̃C���X�^���X����
        SQLiteDatabase db = helper.getReadableDatabase();        // �ǂݍ��݉\��DB���C���X�^���X����
        String ret = new String("0");                           // SQL�̌��ʂ�0�ɏ�����

        try{
            // DB��QUERY�𓊂���
            Cursor cursor = db.rawQuery(this.getSelectString(), null);

            // SELECT�����f�[�^���擾
            while (cursor.moveToNext()) {
                ret = String.valueOf(cursor.getInt(1));
                break;
            }
        }
        finally {
            // DB�ւ̐ڑ���ؒf
            db.close();
        }
        return ret; // ���s���ʂ�ԋp
    }

    // �f�[�^��1���擾����SQL���𐶐�
    private String getSelectString()
    {
        //SQL�쐬
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT");
        sql.append(" id");
        sql.append(" ,max");
        sql.append(" FROM MAX");
        sql.append(" LIMIT 1;");
        return sql.toString();
    }

    // �f�[�^��1���o�^����SQL������
    private String getInsertString() {
        StringBuilder sqlInsert = new StringBuilder();
        sqlInsert.append(" insert into MAX");
        sqlInsert.append(" VALUES(");
        sqlInsert.append(" 1,");
        sqlInsert.append(" 2");
        sqlInsert.append(" );");
        return sqlInsert.toString();
    }
}
