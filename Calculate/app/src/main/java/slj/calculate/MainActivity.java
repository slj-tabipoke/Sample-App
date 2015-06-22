package slj.calculate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
    private String[] item_list = {"アイテム1","アイテム2","アイテム3"};

    /** アプリケーションのContext */
    private static MainActivity instance;  /* Contextの宣言 */

    // コンストラクター
    // CLASS自身のインスタンスの保存
    public MainActivity() {
        instance = this;
    }

    // CLASS自身のインスタンスの取得
    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ModelUnits units = new ModelUnits(this);  /* Units Modelの生成 */

        // PULLDOWNの取得
        final Spinner spin = (Spinner)findViewById(R.id.select_box);
        final EditText edit = (EditText)findViewById(R.id.editText);

        // PULLDOWNに設定するデータを初期化
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, units.findAll());

        // PUllDOWNへデータを設定
        spin.setAdapter(adapter);

        // Spinnerイベントの設定
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent,View view, int position, long id)
            {
                Spinner spinner = (Spinner) parent;
                String item = edit.getText() + (String) spinner.getSelectedItem();
                Toast.makeText(MainActivity.this,String.format("%sが選択されました。", item),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this,
                        "onNothingSelected", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
