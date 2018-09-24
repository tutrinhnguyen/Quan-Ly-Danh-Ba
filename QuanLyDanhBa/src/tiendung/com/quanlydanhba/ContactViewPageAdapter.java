package tiendung.com.quanlydanhba;

import htsi.com.quanlydanhba.R;

import java.util.Locale;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ContactViewPageAdapter extends FragmentPagerAdapter {
	
	private Context context;

	public ContactViewPageAdapter(Context context, FragmentManager fm) {
		super(fm);
		this.context = context;
	}

	@Override
	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		// Return a PlaceholderFragment (defined as a static inner class
		// below).
		return ContactFragment.newInstance(position);
	}

	@Override
	public int getCount() {
		return 5;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Locale l = Locale.getDefault();
		switch (position) {
		case 0:
			return context.getString(R.string.title_all).toUpperCase(l);
		case 1:
			return context.getString(R.string.title_family).toUpperCase(l);
		case 2:
			return context.getString(R.string.title_colleague).toUpperCase(l);
		case 3:
			return context.getString(R.string.title_friend).toUpperCase(l);
		case 4:
			return context.getString(R.string.title_partner).toUpperCase(l);
			
		}
		
		return null;
	}
}
