package tiendung.com.quanlydanhba;

import tiendung.com.quanlydanhba.db.ContactDataProvider;
import tiendung.com.quanlydanhba.db.ContactDatabaseOpenHelper;
import tiendung.com.quanlydanhba.obj.Contact;
import htsi.com.quanlydanhba.R;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class AddOrEditContactActivity extends ActionBarActivity implements OnCheckedChangeListener {
	
	public static final String ADD_OR_EDIT_CONTACT = "keyContact";
	public static final int ADD_CONTACT_KEY = 10;
	public static final int EDIT_CONTACT_KEY = 20;

	int flagFromIntent;
	private EditText editName, editPhoneNumber, editEmail;
	private Button btnAddGroup, btnBirthDay;
	private RadioGroup rgRegion;
	
	private Contact contact;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.contact_add_or_edit_layout);
		
		initLayout();
		
		Intent intent = getIntent();
		if (intent.getExtras() != null) {
			flagFromIntent = intent.getExtras().getInt(ADD_OR_EDIT_CONTACT);
			
			if (flagFromIntent == ADD_CONTACT_KEY) {
				contact = new Contact();
			} 
			
			if (flagFromIntent == EDIT_CONTACT_KEY ){
				
				long contactID = intent.getExtras().getLong("ID");
				Cursor cursor = getContentResolver().query(ContactDataProvider.CONTACT_TABLE_CONTENT_URI, ContactDataProvider.cols,
						ContactDatabaseOpenHelper.COL_CONTACT_ID + " = " + contactID, null, null);
				if (cursor != null) {
					Log.d("HTSI", "" + cursor.getCount());
					cursor.moveToFirst();
				}
				contact = Contact.convertCursorToOjb(cursor);
				initDataForLayout();
			}
		}				
	}
	
	public void initLayout() {
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(flagFromIntent == 10 ? R.string.title_activity_add : 
			R.string.title_activity_edit);
		
		editName = (EditText)findViewById(R.id.editName);
		editEmail = (EditText)findViewById(R.id.editEmail);
		editPhoneNumber = (EditText)findViewById(R.id.editPhoneNumber);
		
		btnAddGroup = (Button)findViewById(R.id.btnAddGroup); 
		btnBirthDay = (Button)findViewById(R.id.btnBirthDay);
		
		rgRegion = (RadioGroup)findViewById(R.id.rgRegion);
		rgRegion.setOnCheckedChangeListener(this);
	}
	
	public void initDataForLayout() {
		
		editName.setText(contact.getName());
		editEmail.setText(contact.getMail());
		editPhoneNumber.setText(contact.getPhone());
		
		btnAddGroup.setText(contact.getGroup());
		btnBirthDay.setText(contact.getBirthDay());
		
		((RadioButton)rgRegion.getChildAt(contact.getRegionId() - 1)).setChecked(true);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == R.id.action_saveContact) {
			if (TextUtils.isEmpty(editName.getText())) {
				Toast.makeText(this, R.string.noName, Toast.LENGTH_LONG).show();
				return true;
			}
			contact.setBirthDay(btnBirthDay.getText().toString());
			contact.setGroup(btnAddGroup.getText().toString());
			//contact.set
			contact.setMail(editEmail.getText().toString());
			contact.setName(editName.getText().toString());
			contact.setPhone(editPhoneNumber.getText().toString());
			
			// getContentResolver().update(uri, values, where, selectionArgs)
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.save_menu, menu);
		return true;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		int regionID = 0, resRegionName = 0;
		switch (checkedId) {
		case R.id.radioNorth:
			regionID = 1;
			resRegionName = R.string.north;
			break;			
		case R.id.radioCentral:
			regionID = 1;
			resRegionName = R.string.central;
			break;
			
		case R.id.radioSouth:
			regionID = 1;
			resRegionName = R.string.south;
			break;

		default:
			break;
		}
		contact.setRegionId(regionID);
		contact.setRegion(getString(resRegionName));
	}

}
