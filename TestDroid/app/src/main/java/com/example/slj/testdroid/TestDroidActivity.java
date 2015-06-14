package com.example.slj.testdroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TestDroidActivity extends ActionBarActivity
{
    /** アプリケーションのContext */
    private static TestDroidActivity instance;  /* Contextの宣言 */

    public TestDroidActivity() {
        instance = this;
    }

    public static TestDroidActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 切替回数値の設定
        setContentView(R.layout.activity_test_droid);
        TextView text = (TextView)findViewById(R.id.textView2);
        ModelMax max = new ModelMax(this);  /* Max Modelの生成 */
        text.setText(max.getMax());          /* Max値の取得し、large textに設定 */

        // 画像のウィジェットの初期化
        final ImageView img = (ImageView) this.findViewById(R.id.img1);
        Button btn = (Button) this.findViewById(R.id.button);
        btn.setOnClickListener(new OnClickListener() {// ボタンをクリックしたときに働いてくれるオブジェクト
            public int max_counter = 0;     /* イメージを着替えるMAX値 */
            public int counter = 0;          /* ユーザーがクリックした回数を保持する変数 */
            public int imge_swicher = 0;    /* イメージを切替スイッチ遠陬 */

            @Override
            public void onClick(View v) {               // ボタンをクリックした時に実行されるコード
                this.counter++;
                ModelMax max = new ModelMax(TestDroidActivity.getInstance());   // DBからMAXを値を取得するクラスを初期化
                this.max_counter = Integer.valueOf(max.getMax());             // MAX値を設定

                if( this.max_counter == this.counter )
                {
                    // 背景がタイルの画像に切替
                    if( this.imge_swicher == 0 ) {
                        img.setImageResource(R.drawable.images);
                        this.imge_swicher = 1;
                    }
                    // 通常の画像に切替
                    else
                    {
                        img.setImageResource(R.drawable.logo11w);
                        this.imge_swicher = 0;
                    }
                    this.counter = 0;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_droid, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
