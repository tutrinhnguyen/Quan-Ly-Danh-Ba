package tiendung.com.quanlydanhba.obj;

import tiendung.com.quanlydanhba.db.ContactDatabaseOpenHelper;
import android.database.Cursor;


public class Contact {
	
	private int id;
	private String name;
	private int phone;
	private String mail;
	private String birthDay;
	private String regionName;
	private int regionId;
	private String groupName;
	private int[] groupId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getRegion() {
		return regionName;
	}
	public void setRegion(String regionName) {
		this.regionName = regionName;
	}
	public String getGroup() {
		return groupName;
	}
	public void setGroup(String groupName) {
		this.groupName = groupName;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public int[] getGroupId() {
		return groupId;
	}
	public void setGroupId(int[] groupId) {
		this.groupId = groupId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Name: " + name +
				"- Phone: " + phone +
				"- Mail: " + mail +
				"- BirthDay: " + birthDay +
				"- Region: " + regionName +
				"- Group: " + groupName;
	}
	
	
	public static Contact convertCursorToOjb(Cursor cursor) {
		// TODO Auto-generated method stub
		Contact contact = new Contact();
		
		contact.setBirthDay(cursor.getString(
				cursor.getColumnIndex(
						ContactDatabaseOpenHelper.COL_CONTACT_BIRTH_DAY)).split(" ")[0]);
		
		contact.setGroup(ContactDataStore.stringToGroupArray(
				cursor.getString(
						cursor.getColumnIndex(
								ContactDatabaseOpenHelper.COL_CONTACT_GROUP_NAMES))));
		
		// Debug
		contact.setGroup(cursor.getString(
						cursor.getColumnIndex(
								ContactDatabaseOpenHelper.COL_CONTACT_GROUP_IDS)));
		
		contact.setId(cursor.getLong(
				cursor.getColumnIndex(
						ContactDatabaseOpenHelper.COL_CONTACT_ID)));
		
		contact.setMail(cursor.getString(
				cursor.getColumnIndex(
						ContactDatabaseOpenHelper.COL_CONTACT_MAIL)));
				
		contact.setName(cursor.getString(
				cursor.getColumnIndex(
						ContactDatabaseOpenHelper.COL_CONTACT_NAME)));
		
		contact.setPhone(cursor.getString(
				cursor.getColumnIndex(
						ContactDatabaseOpenHelper.COL_CONTACT_PHONE)));
		
		contact.setRegion(cursor.getString(
				cursor.getColumnIndex(
						ContactDatabaseOpenHelper.COL_CONTACT_REGION_NAME)));
		
		contact.setRegionId(cursor.getInt(
				cursor.getColumnIndex(
						ContactDatabaseOpenHelper.COL_CONTACT_REGION_ID)));
		
		
		return contact;
	}
}
