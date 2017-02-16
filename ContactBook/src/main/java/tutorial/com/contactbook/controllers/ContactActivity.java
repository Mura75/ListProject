package tutorial.com.contactbook.controllers;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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




        //Prinimaem sushnost kontakta esli ono est po kluchevomu slovu
        if (getIntent().getParcelableExtra("contact_entity") != null) {
            Log.d("Contact_get_intent", getIntent().getParcelableExtra("contact_entity").toString());
            mainContact = (Contact) getIntent()
                            .getParcelableExtra("contact_entity");

            //Zapolniaem polia dannimi kontakta
            etName.setText(mainContact.getName());
            etPhone.setText(mainContact.getPhoneNumber());
            etEmail.setText(mainContact.getEmail());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contact_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.actionCall) {
            if (mainContact != null
                    && mainContact.getPhoneNumber() != null) {
                Intent intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + mainContact.getPhoneNumber()));
                startActivity(intent);
            }
            else {
                Toast.makeText(ContactActivity.this,
                        "Contact or phone not exist",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            return true;
        }
        else if (item.getItemId() == R.id.actionSMS) {
            if (mainContact != null
                    && mainContact.getPhoneNumber() != null) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:" + mainContact.getPhoneNumber()));
                startActivity(sendIntent);
            }
            else {
                Toast.makeText(ContactActivity.this,
                        "Contact or phone not exist",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            return true;
        }
        else if (item.getItemId() == R.id.actionEmail) {
            if (mainContact != null
                    && mainContact.getEmail() != null) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + mainContact.getEmail()));
                startActivity(emailIntent);
            }
            else {
                Toast.makeText(ContactActivity.this,
                        "Contact or email not exist",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
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
            //Soedinenie s bazoi dannih
            DatabaseConnector connector =
                    new DatabaseConnector(ContactActivity.this);

            //Proveriaem esli sushnost kontakt sushestvuet to obnovliaem ego
            if (mainContact != null) {
                mainContact.setName(name);
                mainContact.setPhoneNumber(phone);
                mainContact.setEmail(email);
                connector.updateContact(mainContact);

                //To be continue...
                //connector.update();
            }
            else {
                //Esli sushnosti kontakta net to sozdaem novii kontakt
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
