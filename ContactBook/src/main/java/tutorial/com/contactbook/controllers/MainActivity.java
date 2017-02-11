package tutorial.com.contactbook.controllers;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import tutorial.com.contactbook.R;
import tutorial.com.contactbook.database.DatabaseConnector;
import tutorial.com.contactbook.model.Contact;

public class MainActivity extends AppCompatActivity {

    private ListView lvContact;

    private ContactAdapter contactAdapter;

    private GetAllContactAsync getAllContactAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvContact = (ListView) findViewById(R.id.lvContact);

        getAllContactAsync = new GetAllContactAsync();
        getAllContactAsync.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getAllContactAsync != null) {
            getAllContactAsync.cancel(true);
        }
    }

    //Класс для работы в фоновом режиме
    private class GetAllContactAsync extends AsyncTask<Void, Void, Void> {

        List<Contact> contactList;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //В фоновом режиме достаем список всех контактов
            DatabaseConnector connector = new DatabaseConnector(MainActivity.this);
            contactList = connector.getAllContacts();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //Показываем все данные в списке, для юзера
            contactAdapter = new ContactAdapter(MainActivity.this, contactList);
            lvContact.setAdapter(contactAdapter);
        }
    }
}
