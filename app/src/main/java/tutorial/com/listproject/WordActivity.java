package tutorial.com.listproject;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class WordActivity extends AppCompatActivity {

    TextView word;

    Switch aSwitch;

    //Класс который нужен для хранения простых данных в память телефона
    // (int, double, boolean, String, float, long)
    // Данные в памяти хранятся ключ-значение
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    boolean switchState;

    Button buttonCount, buttonClear;
    TextView tvCounter;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        //Инициализация
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        //Берем значение из памяти для Switch, если ее нет то по умолчанию она выключена
        switchState = preferences.getBoolean("switch", false);

        word = (TextView) findViewById(R.id.tvShowWord);
        aSwitch = (Switch) findViewById(R.id.switchNotif);
        aSwitch.setChecked(switchState);

        String data = getIntent().getStringExtra("data");
        word.setText(data);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    Toast.makeText(WordActivity.this, "On", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(WordActivity.this, "Off", Toast.LENGTH_SHORT).show();
                }
                //Сохраняем в памяти состояние Switch
                editor.putBoolean("switch", b);
                editor.apply();
            }
        });


        buttonCount = (Button) findViewById(R.id.buttonCount);
        buttonClear = (Button) findViewById(R.id.buttonClear);
        tvCounter = (TextView) findViewById(R.id.tvCounter);

        //Показываем сохраненное значение счетчика из памяти устройства
        count = preferences.getInt("cnt", 0);
        tvCounter.setText("Count: " + count);

        //При нажатии на кнопку, увеличиваем значение счетчика
        buttonCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Увеличение значение счетчика на 1
                count = count + 1;
                //Выводим результат
                tvCounter.setText("Count: " + count);
                //Сохраняем результат с ключем "cnt"
                editor.putInt("cnt", count);
                editor.apply();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Удаляем с памяти значение счетчика
                editor.remove("cnt");
                editor.apply();
                tvCounter.setText("");
            }
        });
    }
}
