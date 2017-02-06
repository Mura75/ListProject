package tutorial.com.listproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText input;

    Button buttonEnter;

    ListView lvWords;

    WordAdapter wordAdapter;

    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText) findViewById(R.id.etInput);
        buttonEnter = (Button) findViewById(R.id.buttonEnter);
        lvWords = (ListView) findViewById(R.id.lvWords);

        list.add("qwerty");
        list.add("asdf");

        wordAdapter = new WordAdapter(MainActivity.this, list);
        lvWords.setAdapter(wordAdapter);

        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = input.getText().toString();
                list.add(word);
                wordAdapter.notifyDataSetChanged();
                input.setText("");

            }
        });


        lvWords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String textForShow = list.get(i);
                Toast.makeText(MainActivity.this,
                        textForShow,
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, WordActivity.class);
                intent.putExtra("data", textForShow);
                startActivity(intent);
            }
        });
    }
}
