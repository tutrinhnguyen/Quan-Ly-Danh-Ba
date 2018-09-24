package tiendung.com.quanlydanhba.obj;

import tiendung.com.quanlydanhba.db.ContactDatabaseOpenHelper;
import android.content.ContentValues;


public class ContactDataStore {
	
//	public static int[] stringToArray(String groupIds) {
//		
//	}

	public static String stringToGroupArray(String group) {
		
		String[] sGroupIDs = group.split("###");
		StringBuilder builder = new StringBuilder();
				
		for(int i=0; i<sGroupIDs.length; i++) {			
			builder.append(sGroupIDs[i]);
			if (i < (sGroupIDs.length - 1))
				builder.append(" - ");			
		}
		
		return builder.toString();
	}
	
	public static ContentValues createContentValues(Contact contact) {
		
		ContentValues values = new ContentValues();
		
		values.put(ContactDatabaseOpenHelper.COL_CONTACT_BIRTH_DAY, contact.getBirthDay());
		//values.put(ContactDatabaseOpenHelper.COL_CONTACT_GROUP_IDS, contact.get);
		values.put(ContactDatabaseOpenHelper.COL_CONTACT_GROUP_NAMES, contact.getBirthDay());
		values.put(ContactDatabaseOpenHelper.COL_CONTACT_MAIL, contact.getBirthDay());
		values.put(ContactDatabaseOpenHelper.COL_CONTACT_NAME, contact.getBirthDay());

		return values;
	}

}
