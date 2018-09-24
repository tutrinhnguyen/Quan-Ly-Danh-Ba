package tiendung.com.quanlydanhba;

import java.util.Locale;

import tiendung.com.quanlydanhba.db.ContactDataProvider;
import tiendung.com.quanlydanhba.db.ContactDatabaseOpenHelper;
import tiendung.com.quanlydanhba.obj.Contact;

import htsi.com.quanlydanhba.R;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ContactDetailActivity extends ActionBarActivity {
	
	private Contact contact;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_detail_layout);

		
		Intent intent = getIntent();
		
		long contactID = intent.getExtras().getLong("ID");
		
		Cursor cursor = getContentResolver().query(ContactDataProvider.CONTACT_TABLE_CONTENT_URI, ContactDataProvider.cols,
				ContactDatabaseOpenHelper.COL_CONTACT_ID + " = " + contactID, null, null);
		if (cursor != null) {
			Log.d("HTSI", "" + cursor.getCount());
			cursor.moveToFirst();
		}
		contact = Contact.convertCursorToOjb(cursor);
		
		Log.d("HTSI", contact.toString());
		
		initLayout();
	}
	
	public void initLayout() {
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle(contact.getName().toUpperCase(Locale.getDefault()));
		
		TextView textPhoneNumber = (TextView)findViewById(R.id.textPhoneNumber);
		textPhoneNumber.setText(contact.getPhone());
		
		TextView textMail = (TextView)findViewById(R.id.textMail);
		textMail.setText(contact.getMail());
		
		TextView textGroup = (TextView)findViewById(R.id.textGroup);
		textGroup.setText(contact.getGroup());
		
		TextView textRegion = (TextView)findViewById(R.id.textRegion);
		textRegion.setText(contact.getRegion());
		
		TextView textBirthDay = (TextView)findViewById(R.id.textBirthDay);
		textBirthDay.setText(contact.getBirthDay());
	}
	
	public void onPhoneAction(View view) {
		
		Intent intent = new Intent();
		
		switch (view.getId()) {
		case R.id.callPhone:
			intent.setAction(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:" + contact.getPhone()));
			break;
			
		case R.id.sendSMS:
			// intent.setAction(Intent.ACTION_SEND);
			intent.setData(Uri.parse("smsto:" + contact.getPhone()));
			break;
			
		case R.id.sendMail:
			intent.setAction(Intent.ACTION_SENDTO);
			intent.setData(Uri.parse("mailto: " + contact.getMail()));
			break;

		default:
			break;
		}
		
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.contact_detail_menu, menu);		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == android.R.id.home) {
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		if (item.getItemId() == R.id.action_editContact) {
			Intent intent = new Intent(this, AddOrEditContactActivity.class);
			intent.putExtra(AddOrEditContactActivity.ADD_OR_EDIT_CONTACT,
					AddOrEditContactActivity.EDIT_CONTACT_KEY);
			intent.putExtra("ID", contact.getId());
			startActivity(intent);			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
