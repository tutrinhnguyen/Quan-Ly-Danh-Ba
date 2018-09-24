package tiendung.com.quanlydanhba;

import tiendung.com.quanlydanhba.db.ContactDataProvider;
import tiendung.com.quanlydanhba.db.ContactDatabaseOpenHelper;
import htsi.com.quanlydanhba.R;
import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.Toast;

public class ContactFragment extends Fragment implements 
		LoaderCallbacks<Cursor>, OnQueryTextListener, OnItemClickListener, OnItemLongClickListener {
	
	
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	private ListView listView;
	private ContactAdapter adapter;
	private SearchView mSearchView;
	private MenuItem searchMenuItem;

	public MenuItem getSearchMenuItem() {
	    return searchMenuItem;
	}
	
	public static final String QUERY_KEY = "query";
	public static final int KEY_ALL = 0;
	public static final int KEY_FAMILY = 1;
	public static final int KEY_COLLEAGUE = 2;
	public static final int KEY_FRIEND = 3;
	public static final int KEY_PARTNER = 4;

	private int sectionNumber;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static ContactFragment newInstance(int sectionNumber) {
		ContactFragment fragment = new ContactFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public ContactFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		
		listView = (ListView)rootView.findViewById(R.id.listOfContact);
		adapter = new ContactAdapter(getActivity(), null);
		listView.setAdapter(adapter);
		
		sectionNumber = this.getArguments().getInt(ARG_SECTION_NUMBER);
		
		this.getActivity().getSupportLoaderManager().initLoader(
				sectionNumber,
				this.getArguments(), this);
		
	
		setHasOptionsMenu(true);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		listView.setOnItemClickListener(this);
		listView.setOnItemLongClickListener(this);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		inflater.inflate(R.menu.search_view, menu);
		searchMenuItem = menu.findItem(R.id.action_search);
		mSearchView = (SearchView) searchMenuItem.getActionView();
		mSearchView.setOnQueryTextListener(this);
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == R.id.action_addContact) {
			Intent intent = new Intent(getActivity(), AddOrEditContactActivity.class);
			intent.putExtra(AddOrEditContactActivity.ADD_OR_EDIT_CONTACT,
					AddOrEditContactActivity.ADD_CONTACT_KEY);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle bundle) {
		// TODO Auto-generated method stub
		String selection = null;
		
		
		if (bundle != null) {
			int query = bundle.getInt(ARG_SECTION_NUMBER);
			String where = bundle.getString(QUERY_KEY);
			
			if (where != null) {				
				if (query > 0)
					selection = ContactDatabaseOpenHelper.COL_CONTACT_GROUP_IDS + 
					" like " + "'%" + query + "%' and ( " + where + " )";
				else
					selection =  where;				
			} else {				
				if (query > 0)
					selection = ContactDatabaseOpenHelper.COL_CONTACT_GROUP_IDS + 
					" like " + "'%" + query + "%'";				
			}
			String log = "Query statement: ";
			if (selection != null)
				log += selection;
			
			Log.d("HTSI", log);
			Log.d("HTSI", "Query = " + query);
		}			
		
		return new CursorLoader(getActivity(), ContactDataProvider.CONTACT_TABLE_CONTENT_URI, null,
					selection, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
		// TODO Auto-generated method stub
		this.adapter.changeCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		this.adapter.changeCursor(null);
	}

	@Override
	public boolean onQueryTextChange(String textSearch) {
		// TODO Auto-generated method stub
		//Log.i("HTSI", textSearch);
		this.adapter.setFilterQueryProvider(new FilterQueryProvider() {
			
			@Override
			public Cursor runQuery(CharSequence constraint) {
				// TODO Auto-generated method stub
				Log.d("HTSI", constraint.toString());
				
				StringBuilder where = new StringBuilder();
			    where.append(ContactDatabaseOpenHelper.COL_CONTACT_NAME)
			    .append(" like '%")
			    .append(constraint).append("%' or ");
			    
			    where.append(ContactDatabaseOpenHelper.COL_CONTACT_PHONE)
			    .append(" like '%")
			    .append(constraint).append("%' or ");
			    
			    where.append(ContactDatabaseOpenHelper.COL_CONTACT_MAIL)
			    .append(" like '%")
			    .append(constraint).append("%'");
			    
			    
			    
			    Bundle bundle = new Bundle();
			    bundle.putInt(ARG_SECTION_NUMBER, sectionNumber);
			    bundle.putString(QUERY_KEY, where.toString());
			    
			    ContactFragment.this.getLoaderManager().restartLoader(0, bundle, ContactFragment.this);
				
				return null;
			}
		});
		this.adapter.getFilterQueryProvider().runQuery(textSearch);
		return true;
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	public boolean onQueryTextSubmit(String arg0) {
		// TODO Auto-generated method stub
		MenuItem menuItem = getSearchMenuItem();
		if (menuItem != null)
			menuItem.collapseActionView();
		return true;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long id) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), "ID: " + id, Toast.LENGTH_LONG).show();
		Intent intent = new Intent(getActivity(), ContactDetailActivity.class);
		intent.putExtra("ID", id);
		startActivity(intent);
	}

}
