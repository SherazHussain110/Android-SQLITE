package sheraz.dataappsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper
{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyDatabase";
    private static final String TABLE_NAME = "StudentDatabase";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "Name";
    private static final String COL_3 = "FatherName";
    private static final String COL_4 = "PhoneNumber";
    private static final String COL_5 = "EmailAddress";

    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT,"
                + COL_3 + " TEXT," + COL_4 + " TEXT," + COL_5 + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //inserting data

    void addContact(UserContacts contact)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_2, contact.getUserName());
        values.put(COL_3, contact.getFatherName());
        values.put(COL_4, contact.getPhoneNumber());
        values.put(COL_5, contact.getEmailAddress());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }



    public List<UserContacts> getAllContacts() {

        List<UserContacts> contactList = new ArrayList<UserContacts>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserContacts contact = new UserContacts();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setUserName(cursor.getString(1));
                contact.setFatherName(cursor.getString(2));
                contact.setPhoneNumber(cursor.getString(3));
                contact.setEmailAddress(cursor.getString(4));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    /////////Delete the records//////////
    public void deleteContact(UserContacts contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME , COL_1 + "=?" , new String[] {
                String.valueOf(contact.getId())});
        db.close();
    }

    public int updateContact(UserContacts contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_2 , contact.getUserName());
        values.put(COL_3 , contact.getFatherName());
        values.put(COL_4 ,contact.getPhoneNumber());
        values.put(COL_5 ,contact.getEmailAddress());

        return db.update(TABLE_NAME ,values , COL_1 + "=?" ,new String[] {String.valueOf(contact.getId())});
    }

//    public boolean updateData(String id,String name,String father_name,String phone_number,String email)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COL_1,id);
//        values.put(COL_2,name);
//        values.put(COL_3,father_name);
//        values.put(COL_4,phone_number);
//        values.put(COL_5,email);
//        db.update(TABLE_NAME, values, "ID = ?",new String[] {id});
//        return true;
//    }

}
