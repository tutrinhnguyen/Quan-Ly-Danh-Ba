package tiendung.com.quanlydanhba.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactDatabaseOpenHelper extends SQLiteOpenHelper {
	
	
	public static final String TABLE_CONTACT = "DOI_TAC";
	
	public static final String COL_CONTACT_ID = "_id";
	public static final String COL_CONTACT_NAME = "Ho_ten";
	public static final String COL_CONTACT_PHONE = "Dien_thoai";
	public static final String COL_CONTACT_BIRTH_DAY = "Ngay_sinh";
	public static final String COL_CONTACT_MAIL = "Mail";
	public static final String COL_CONTACT_REGION_ID = "ID_Khu_vuc";
	public static final String COL_CONTACT_REGION_NAME = "Ten_Khu_vuc";
	public static final String COL_CONTACT_GROUP_IDS = "Chuoi_Danh_sach_ID_Nhom";
	public static final String COL_CONTACT_GROUP_NAMES = "Chuoi_Danh_sach_Ten_Nhom";

	
	private static String DB_PATH = "/data/data/htsi.com.quanlydanhba/databases/";
	private static final String DB_NAME = "contact.sqlite";
	
	public ContactDatabaseOpenHelper(Context context) {
				
		super(context, DB_NAME, null, 1);
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	private Context context;
	
	public SQLiteDatabase openDatabase() {
		String path = DB_PATH + DB_NAME;
		return SQLiteDatabase.openDatabase(
				path, null, SQLiteDatabase.OPEN_READONLY);
	}
	
	public void createDatabase() {
		
		boolean checked = checkDatabase();
		if (checked) {
			
		} else {
			this.getReadableDatabase();
			copyDatabase();
		}
		
	}
	
	private boolean checkDatabase() {
		
		SQLiteDatabase checkDB = null;
		
		String path = DB_PATH + DB_NAME;
		try {
		checkDB = SQLiteDatabase.openDatabase(
				path, null, SQLiteDatabase.OPEN_READONLY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (checkDB != null) {
			checkDB.close();
		}
		
		return checkDB != null ? true:false;
	}
	
	private void copyDatabase() {
		
		try {
			InputStream inputStream = context.getAssets().open(DB_NAME);
			String fileName = DB_PATH + DB_NAME;
			OutputStream outputStream = new FileOutputStream(fileName);
			byte[] buffer = new byte[1024];
			int lenght;
			while ((lenght = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, lenght);
			}
			
			outputStream.flush();
			outputStream.close();
			inputStream.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
