package tiendung.com.quanlydanhba.db;


import java.util.ArrayList;

import tiendung.com.quanlydanhba.obj.Contact;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ContactDataTestSource {
	
	ContactDatabaseOpenHelper helper;
	public SQLiteDatabase database;
	
	public ContactDataTestSource(Context context) {
		// TODO Auto-generated constructor stub
		helper = new ContactDatabaseOpenHelper(context);
		
		helper.createDatabase();
		database = helper.openDatabase();
	}
	
	public ArrayList<Contact> getAllContacts() {
		
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		
		Cursor cursor = database.query(ContactDatabaseOpenHelper.TABLE_CONTACT,
				null, null, null, null, null, null);
		
		cursor.moveToFirst();
		
		while (!cursor.isAfterLast()) {
			
			Contact contact = Contact.convertCursorToOjb(cursor);
			cursor.moveToNext();
			contacts.add(contact);
		}
				
		return contacts;
	}

}
