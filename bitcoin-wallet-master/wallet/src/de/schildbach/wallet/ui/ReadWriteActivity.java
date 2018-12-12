package de.schildbach.wallet.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import de.schildbach.wallet.R;

public class ReadWriteActivity extends AbstractWalletActivity {

    private EditText txt_str;
    private EditText txt_int;
    private TextView tv_res;
    private boolean haveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_write);
        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.clear(); // 每次打开界面，清空以前的数据
        haveData = false;
        editor.commit();
        txt_str = (EditText) findViewById(R.id.text_str);
        txt_int = (EditText) findViewById(R.id.text_int);
        tv_res = (TextView) findViewById(R.id.text_result);
        tv_res.setText("");
        tv_res.setMovementMethod(ScrollingMovementMethod.getInstance());
        Button btn_rd = (Button) findViewById(R.id.button_read);
        btn_rd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                readText();
            }
        });
        Button btn_wt = (Button) findViewById(R.id.button_write);
        btn_wt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                writeText();
            }
        });
    }
    protected boolean outofIntRange(String str) {
        if (str.length() > 10) return true;
        else if (str.length() == 10) return (str.compareTo("2147483647") > 0);
        else return false;
    }
    private void writeText() {
        tv_res.setText("");
        if (txt_str.length() == 0 || txt_int.length() == 0) {
            Toast.makeText(this, "说点什么吧~", Toast.LENGTH_LONG).show();
            return;
        }
        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.putString("str", txt_str.getText().toString());
        String strUnsigned = txt_int.getText().toString();
        if(outofIntRange(strUnsigned)) {
            Toast.makeText(this, "这个整数有点大...", Toast.LENGTH_LONG).show();
            return;
        }
        editor.putInt("int", Integer.parseInt(strUnsigned));
        haveData = true;
        editor.commit();
        Toast.makeText(this, "你的问候我们收到了！" , Toast.LENGTH_LONG).show();
    }
    private void readText() {
        txt_str.setText("");
        txt_int.setText("");
        if (!haveData){
            Toast.makeText(this, "我我我什么都不知道QAQ" , Toast.LENGTH_LONG).show();
            return;
        }
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        String str = sp.getString("str", "");
        int val = sp.getInt("int", 0);
        tv_res.setText(String.format("可爱的字符串说：\n%s \n不可爱的整数说：\n%s", str, String.valueOf(val)));
    }
}
