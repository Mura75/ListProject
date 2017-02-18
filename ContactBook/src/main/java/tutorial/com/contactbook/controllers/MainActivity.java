package tutorial.com.contactbook.controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import tutorial.com.contactbook.R;
import tutorial.com.contactbook.database.DatabaseConnector;
import tutorial.com.contactbook.model.Contact;

public class MainActivity extends AppCompatActivity {

    private ListView lvContact;

    private ContactAdapter contactAdapter;

    private GetAllContactAsync getAllContactAsync;
    private RemoveContact removeContact;

    List<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvContact = (ListView) findViewById(R.id.lvContact);


        //Obrabativaem klic elementa spiske
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Otpravliaem sushnost' kontakta v drugoe okno
                Contact contact = contactList.get(position);
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                intent.putExtra("contact_entity", contact);
                startActivity(intent);
            }
        });

        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contactToRemove = contactList.get(position);
                showRemoveContactDialog(contactToRemove);
                return true;
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        getAllContactAsync = new GetAllContactAsync();
        getAllContactAsync.execute();
    }


    private void showRemoveContactDialog(final Contact contact) {
        AlertDialog alertDialog =
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Do you want remove " + contact.getName() + " ?")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                removeContact = new RemoveContact(contact);
                                removeContact.execute();
                            }
                        })
                        .create();
        alertDialog.show();
    }


    //Privazivaem menu k Activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contact_list_menu, menu);
        return true;
    }


    //Obrabativaem klik knopki v menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.actionCreate) {
            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
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


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //В фоновом режиме достаем список всех контактов
            DatabaseConnector connector = new DatabaseConnector(MainActivity.this);
            Log.d("Contact_list_bg", connector.getAllContacts().toString());
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

    private class RemoveContact extends AsyncTask<Void, Void, Void> {

        private Contact contact;

        public RemoveContact(Contact contact) {
            this.contact = contact;
        }

        @Override
        protected Void doInBackground(Void... params) {
            DatabaseConnector connector =
                    new DatabaseConnector(MainActivity.this);
            connector.removeContact(contact);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            contactList.remove(contact);
            contactAdapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this,
                        "Contact removed",
                        Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
