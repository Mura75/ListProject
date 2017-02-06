package tutorial.com.listproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WordActivity extends AppCompatActivity {

    TextView word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        word = (TextView) findViewById(R.id.tvShowWord);

        String data = getIntent().getStringExtra("data");
        word.setText(data);
    }
}
