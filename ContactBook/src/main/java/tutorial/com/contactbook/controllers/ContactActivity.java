package tutorial.com.contactbook.controllers;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import tutorial.com.contactbook.R;
import tutorial.com.contactbook.database.DatabaseConnector;
import tutorial.com.contactbook.model.Contact;

public class ContactActivity extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_REQUEST = 300;
    private static final int GALLERY_PERMISSION_REQUEST = 301;

    private static final int CAMERA_REQUEST = 400;
    private static final int GALLERY_REQUEST = 401;

    private CircleImageView ivAvatar;

    private EditText etName, etSurname, etPhone, etEmail;

    private TextInputLayout tilName, tilPhone;

    private Button buttonSave;

    private CreateOrUpdateContactAsync createOrUpdateContactAsync;

    private Contact mainContact;

    private Bitmap bitmapUserAvatar;

    private String imageRealPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        ivAvatar = (CircleImageView) findViewById(R.id.ivAvatar);
        etName = (EditText) findViewById(R.id.etName);
        etSurname = (EditText) findViewById(R.id.etSurname);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etEmail = (EditText) findViewById(R.id.etEmail);
        buttonSave = (Button) findViewById(R.id.buttonSave);

        tilName = (TextInputLayout)findViewById(R.id.tilName);
        tilPhone = (TextInputLayout) findViewById(R.id.tilPhone);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                String email = etEmail.getText().toString();

                if (name == null || name.length() == 0) {
                    tilName.setError("Name is empty");
                }

                if (TextUtils.isEmpty(phone)) {
                    tilPhone.setError("Phone is empty");
                }

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone)){
                    createOrUpdateContactAsync =
                            new CreateOrUpdateContactAsync(name, "", phone, email);
                    createOrUpdateContactAsync.execute();
                }

            }
        });

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    tilName.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isVersion23()) {
                    getCameraAndGalleyPermission();
                }
                else {
                    avatarDialog();
                }
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
            if (mainContact != null && !TextUtils.isEmpty(mainContact.getPhoto())) {
                ivAvatar.setImageURI(Uri.parse(mainContact.getPhoto()));
            }
        }
    }


    public void avatarDialog() {

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        new String[]{"Camera", "Gallery"});

        AlertDialog avatarDialog =
                    new AlertDialog.Builder(this)
                    .setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {

                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, CAMERA_REQUEST);

//                                if (isVersion23() == true) {
//                                    getCameraPermission();
//                                }
//                                else {
//
//                                }
                            }
                            else if (which == 1) {

                                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                intent.setType("image/*");
                                startActivityForResult(intent, GALLERY_REQUEST);


//                                if (isVersion23() == true) {
//                                    getGalleryPermission();
//                                }
//                                else {
//                                    Intent intent = new Intent(Intent.ACTION_PICK);
//                                    startActivityForResult(intent, GALLERY_REQUEST);
//                                }
                            }
                        }
                    })
                    .create();

        avatarDialog.show();
    }

    private boolean isVersion23() {
        return Build.VERSION.SDK_INT >= 23;
    }


    private void getCameraAndGalleyPermission() {

        boolean hasCameraAndGalleyPermission =
                (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA)) == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED;

        if (hasCameraAndGalleyPermission == true) {
            avatarDialog();
        }
        else {
            requestPermissions(
                    new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE},
                    CAMERA_PERMISSION_REQUEST);
        }
    }

    private void getCameraPermission() {

        boolean hasCameraPermission =
                (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA)) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED;

        if (hasCameraPermission == true) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_REQUEST);
        }
        else {
            requestPermissions(
                    new String[]{Manifest.permission.CAMERA,
                                 Manifest.permission.READ_EXTERNAL_STORAGE},
                    CAMERA_PERMISSION_REQUEST);
        }
    }

    private void getGalleryPermission() {
        boolean hasGalleryPermission =
                (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE))
                        == PackageManager.PERMISSION_GRANTED;


        if (hasGalleryPermission == true) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY_REQUEST);
        }
        else {
            requestPermissions(
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    GALLERY_PERMISSION_REQUEST);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        List<String> stringList = new ArrayList<String>(Arrays.asList(permissions));
        List<Integer> integerList = new ArrayList<Integer>();

        for (int i = 0; i < grantResults.length; i++) {
            integerList.add(grantResults[i]);
        }


        Log.d("My_camera_permission", requestCode +
                "\n" + stringList.toString() +
                "\n" + integerList.toString());

        if (requestCode == CAMERA_PERMISSION_REQUEST) {
            avatarDialog();
//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            startActivityForResult(intent, CAMERA_REQUEST);
        }
        else {
//            requestPermissions(
//                    new String[]{Manifest.permission.CAMERA,
//                            Manifest.permission.READ_EXTERNAL_STORAGE},
//                    CAMERA_PERMISSION_REQUEST);
        }

//        if (requestCode == GALLERY_PERMISSION_REQUEST) {
//            Intent intent = new Intent(Intent.ACTION_PICK);
//            startActivityForResult(intent, GALLERY_REQUEST);
//        }
//        else {
//            requestPermissions(
//                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                    GALLERY_PERMISSION_REQUEST);
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                Log.d("Camera_result_data", data.getExtras().get("data").toString());
                bitmapUserAvatar = (Bitmap) data.getExtras().get("data");
                ivAvatar.setImageBitmap(bitmapUserAvatar);

                String path = MediaStore.Images.Media.insertImage(this.getContentResolver(),
                        bitmapUserAvatar, "avatar", "desc");

                imageRealPath = getRealPath(path);

                Log.d("Image_camera_path", path + "\n" + getRealPath(path));


            }
            else if (requestCode == GALLERY_REQUEST) {
                if (data != null) {
                    Log.d("Gallery_result_data", data.getData().toString());

                    try {
                        bitmapUserAvatar = MediaStore.Images.Media.getBitmap(this.getContentResolver(),
                                data.getData());
                        ivAvatar.setImageBitmap(bitmapUserAvatar);

                        String path = MediaStore.Images.Media.insertImage(this.getContentResolver(),
                                bitmapUserAvatar, "avatar", "desc");

                        imageRealPath = getRealPath(path);

                        Log.d("Image_camera_path", MediaStore.Images.Media.insertImage(this.getContentResolver(),
                                bitmapUserAvatar, "avatar", "desc"));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                }
                else {
                    Log.d("Gallery_result_data", "Null");
                }
            }
    }


    private String getRealPath(String path) {
        Cursor cursor = getContentResolver().query(Uri.parse(path), null, null, null, null);
        cursor.moveToFirst();
        int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(index);
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

            if (bitmapUserAvatar != null) {
                Log.d("Image_size", bitmapUserAvatar.getByteCount() + "");
            }

            //Proveriaem esli sushnost kontakt sushestvuet to obnovliaem ego
            if (mainContact != null) {
                mainContact.setName(name);
                mainContact.setPhoneNumber(phone);
                mainContact.setEmail(email);
                if (imageRealPath != null) {
                    mainContact.setPhoto(imageRealPath);
                }
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
                if (imageRealPath != null) {
                    contact.setPhoto(imageRealPath);
                }
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
