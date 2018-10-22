package tiendung.com.quanlydanhba.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class ContactDataProvider extends ContentProvider {
	
	public static final int CONTACT_QUERY = 0;	
	public static final int INVALID_URI = -1;
	
	public static final String SCHEME = "content";
	public static final String AUTHORITY = "htsi.com.quanlydanhba";

	public static final Uri CONTENT_URI = Uri.parse(SCHEME + "://" + AUTHORITY);

	public static final Uri CONTACT_TABLE_CONTENT_URI = Uri.withAppendedPath(
			CONTENT_URI, ContactDatabaseOpenHelper.TABLE_CONTACT);

	public static final String[] cols = new String[]{ 
		ContactDatabaseOpenHelper.COL_CONTACT_ID,
		ContactDatabaseOpenHelper.COL_CONTACT_BIRTH_DAY,
		ContactDatabaseOpenHelper.COL_CONTACT_GROUP_IDS,
		ContactDatabaseOpenHelper.COL_CONTACT_GROUP_NAMES,
		ContactDatabaseOpenHelper.COL_CONTACT_MAIL,
		ContactDatabaseOpenHelper.COL_CONTACT_NAME,
		ContactDatabaseOpenHelper.COL_CONTACT_PHONE,
		ContactDatabaseOpenHelper.COL_CONTACT_REGION_ID,
		ContactDatabaseOpenHelper.COL_CONTACT_REGION_NAME,
	};

	private ContactDataTestSource dataSource;
	
	private static final UriMatcher sUriMatcher;

	static {

		sUriMatcher = new UriMatcher(0);

		sUriMatcher.addURI(AUTHORITY, ContactDatabaseOpenHelper.TABLE_CONTACT,
				CONTACT_QUERY);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		dataSource = new ContactDataTestSource(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
				
		switch (sUriMatcher.match(uri)) {
		case CONTACT_QUERY:
						
			return dataSource.database.query(ContactDatabaseOpenHelper.TABLE_CONTACT,
					cols, selection, null, null, null, null);

		default:
			break;
		}
		
		return null;
	}

	@Override
	

}
