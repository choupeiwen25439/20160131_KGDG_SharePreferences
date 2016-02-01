package com.chou.polly.phonebook;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 將 xml 餵給 activity
        setContentView(R.layout.activity_main);

        // 找到按鈕(記得檢查一下是否記得給學號)
        Button addBtn = (Button) findViewById(R.id.btn_add);

        // 設定按鈕的監聽者
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 使用者點擊後監聽者會跑來通知 onClick 了！
                saveData();
            }
        });

        // 設定按鈕的監聽者
        Button readBtn = (Button) findViewById(R.id.btn_read);
        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
            }
        });
    }

    public void readData() {
        EditText readEditText = (EditText) findViewById(R.id.et_read);
        String name = readEditText.getText().toString();

        // 檢查使用者是否沒輸入東西
        if(TextUtils.isEmpty(name)) {
            // 跳出提示字
            Toast.makeText(this, getString(R.string.input_hint), Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences familyTable = getSharedPreferences("family", 0);
            String result = familyTable.getString(name, null);

            TextView resultTextView = (TextView) findViewById(R.id.tv_result);
            if(result == null) {
                Toast.makeText(this, getString(R.string.result_not_found), Toast.LENGTH_SHORT).show();
            }else {
                // 將結果丟給文字框 (建議將字串放置 string.xml)
                resultTextView.setText(name + "的電話號碼是 = " + result);
            }
        }
    }

    public void saveData() {
        EditText nameEditText = (EditText) findViewById(R.id.et_name);
        EditText phoneEditText = (EditText) findViewById(R.id.et_phone);

        String name = nameEditText.getText().toString();
        String phone = phoneEditText.getText().toString();

        // 找到 family 這張表
        SharedPreferences familyTable = getSharedPreferences("family", 0);
        familyTable.edit()
                .putString(name, phone) // 新增資料
                .apply();
    }
}
