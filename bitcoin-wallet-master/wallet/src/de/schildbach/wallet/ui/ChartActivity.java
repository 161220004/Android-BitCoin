package de.schildbach.wallet.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.schildbach.wallet.R;

public class ChartActivity extends AbstractWalletActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        Button btn_bar = (Button)findViewById(R.id.button_bar);
        btn_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                changeTextBar();
            }
        });
        Button btn_line = (Button)findViewById(R.id.button_line);
        btn_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                changeTextLine();
            }
        });
        Button btn_save = (Button) findViewById(R.id.button_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                handleReadWrite();
            }
        });
    }
    private void changeTextBar() {
        TextView ts = (TextView)findViewById(R.id.text_show);
        ts.setText("我是假装存在的条形图");
    }
    private void changeTextLine() {
        TextView ts = (TextView)findViewById(R.id.text_show);
        ts.setText("我是假装存在的折线图");
    }
    private void handleReadWrite() {
        startActivity(new Intent(this, ReadWriteActivity.class));
    }
}
