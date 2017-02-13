package tutorial.com.contactbook.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import tutorial.com.contactbook.model.Contact;

/**
 * Created by Murager on 11.02.2017.
 */

public class DatabaseConnector {

    //Название базы данных
    public static final String DATABASE_NAME = "CONTACT_DB";

    //Название таблицы
    public static final String TABLE_NAME = "CONTACT";

    //Версия базы данных
    public static final int DATABASE_VERSION = 2;

    //Столбец для ID
    public static final String ID = "ID";

    //Столбец для Имени
    public static final String NAME = "NAME";

    //Столбец для Телефона
    public static final String PHONE = "PHONE";

    //Столбец для Почты
    public static final String EMAIL = "EMAIL";


    //Создание таблицы (SQL команда для создания таблицы)
    public static final String CREATE_TABLE =
            "CREATE TABLE if not exists " + TABLE_NAME +
            " (" + ID + " integer primary key autoincrement, " +
                    NAME + " TEXT, " +
                    PHONE + " TEXT, " +
                    EMAIL + " TEXT" + ");";


    //Класс для работы с базой данных
    private SQLiteDatabase database;

    //Класс для создания базы данных
    private DatabaseHelper helper;

    //Конструктор класса
    public DatabaseConnector(Context context) {
        helper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Открываем базу данных
    public void open() {
        database = helper.getWritableDatabase();
    }


    //Закрываем базу данных
    public void close() {
        if (database != null) {
            database.close();
        }
    }


    //Записываем данные нового контакта в БД (база данных)
    public void insertContact(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(NAME, contact.getName());
        values.put(PHONE, contact.getPhoneNumber());
        values.put(EMAIL, contact.getEmail());

        open();
        database.insert(TABLE_NAME, null, values);
        close();
    }


    //Достаем все данные с БД и записываем в спискок
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME;

        open();
        Cursor cursor = database.rawQuery(query, null);

        while (cursor.moveToNext()) {
            Contact contact = new Contact();
            contact.setId(cursor.getInt(0));
            contact.setName(cursor.getString(1));
            contact.setPhoneNumber(cursor.getString(2));
            contact.setEmail(cursor.getString(3));
            contactList.add(contact);
        }
        close();
        return contactList;
    }

    //Класс для создание и обновление базы данных
    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory,
                              int version) {
            super(context, name, factory, version);
        }

        //Создание базы данных
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }

        //Upgrade базы данных
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }
}
