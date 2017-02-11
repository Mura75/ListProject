package tutorial.com.listproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText input;

    Button buttonEnter;

    ListView lvWords;

    WordAdapter wordAdapter;

    List<String> list = new ArrayList<>();

    //Классы для хранения примитивных данных в память устройства
    // (int, double, string, long, ...)
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Инициализация
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();


        input = (EditText) findViewById(R.id.etInput);
        buttonEnter = (Button) findViewById(R.id.buttonEnter);
        lvWords = (ListView) findViewById(R.id.lvWords);

        for (int i = 0; i < 10000; i++) {
            list.add("Number: " + i);
        }

        String data = preferences.getString("list", "");

        wordAdapter = new WordAdapter(MainActivity.this, getList(data));
        lvWords.setAdapter(wordAdapter);

        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = input.getText().toString();
                list.add(word);
                Toast.makeText(MainActivity.this, jsonArray(list), Toast.LENGTH_SHORT).show();
                Log.d("My_json", jsonArray(list));
                wordAdapter.notifyDataSetChanged();
                editor.putString("list", jsonArray(list));
                editor.apply();
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


    //Метод для хранения списка в память телефона,
    // так как список не является примитивным типом данных,
    // мы переводим его в примитивный тип данных в String
    // через JsonArray
    //Cхема перевода List -> JsonArray -> String
    public String jsonArray(List<String> list) {
        JSONArray array = new JSONArray();

        //Каждый элемент листа записываем в JsonArray
        for (String str : list) {
            array.put(str);
        }
        return array.toString();
    }


    //Метод для перевода из String в List
    //Cхема перевода String -> JsonArray -> List
    public List<String> getList(String data) {
        try {
            JSONArray array = new JSONArray(data);
            //Каждый элемент JsonArray записывам в List
            for (int i = 0; i < array.length(); i++) {
                list.add(array.get(i).toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
