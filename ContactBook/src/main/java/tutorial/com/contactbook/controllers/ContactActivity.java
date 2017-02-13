package tutorial.com.contactbook.controllers;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import tutorial.com.contactbook.R;
import tutorial.com.contactbook.database.DatabaseConnector;
import tutorial.com.contactbook.model.Contact;

public class ContactActivity extends AppCompatActivity {

    private EditText etName, etSurname, etPhone, etEmail;

    private Button buttonSave;

    private CreateOrUpdateContactAsync createOrUpdateContactAsync;

    private Contact mainContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        etName = (EditText) findViewById(R.id.etName);
        etSurname = (EditText) findViewById(R.id.etSurname);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etEmail = (EditText) findViewById(R.id.etEmail);
        buttonSave = (Button) findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                String email = etEmail.getText().toString();

                createOrUpdateContactAsync =
                        new CreateOrUpdateContactAsync(name, "", phone, email);
                createOrUpdateContactAsync.execute();
            }
        });


        Log.d("Contact_get_intent", getIntent().getParcelableExtra("contact_entity").toString());

        //Prinimaem sushnost kontakta esli ono est po kluchevomu slovu
        if (getIntent().getParcelableExtra("contact_entity") != null) {
            mainContact = (Contact) getIntent()
                            .getParcelableExtra("contact_entity");

            //Zapolniaem polia dannimi kontakta
            etName.setText(mainContact.getName());
            etPhone.setText(mainContact.getPhoneNumber());
            etEmail.setText(mainContact.getEmail());
        }
    }

    @Override
    protected void onDestroy() {
        if (createOrUpdateContactAsync != null) {
            createOrUpdateContactAsync.cancel(true);
        }
        super.onDestroy();
    }

    private class CreateOrUpdateContactAsync extends AsyncTask<Void, Void, Void> {

        private String name, surname, phone, email;

        public CreateOrUpdateContactAsync(String name, String surname,
                                          String phone, String email) {
            this.name = name;
            this.surname = surname;
            this.phone = phone;
            this.email = email;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            DatabaseConnector connector =
                    new DatabaseConnector(ContactActivity.this);

            if (mainContact != null) {
                mainContact.setName(name);
                mainContact.setPhoneNumber(phone);
                mainContact.setEmail(email);

                //To be continue...
                //connector.update();
            }
            else {
                //Esli sushnosti net to sozdaem novii kontakt
                Contact contact = new Contact();
                contact.setName(name);
                contact.setPhoneNumber(phone);
                contact.setEmail(email);
                connector.insertContact(contact);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finish();
        }
    }
}
