package tiendung.com.quanlydanhba;

import htsi.com.quanlydanhba.R;

import java.util.Locale;

import tiendung.com.quanlydanhba.db.ContactDatabaseOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactAdapter extends CursorAdapter {
	

	public ContactAdapter(Context context, Cursor c ) {
		super(context, c, false);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		Locale locale = Locale.getDefault();
		TextView textName = (TextView) view
				.getTag(R.id.textName);
		String name = cursor.getString(
				cursor.getColumnIndex(
						ContactDatabaseOpenHelper.COL_CONTACT_NAME)).toUpperCase(locale);
		textName.setText(name);

		TextView textPhone = (TextView) view
				.getTag(R.id.textPhone);
		textPhone.setText(cursor.getString(
				cursor.getColumnIndex(
						ContactDatabaseOpenHelper.COL_CONTACT_PHONE)));

		//CheckBox cbFavourite = (CheckBox) view.getTag(R.id.cbFavourite);
		
//		ImageView imgAvatar = (ImageView) view
//				.getTag(R.id.imgAvatar);
//		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
//                R.drawable.android, null);
		//imgAvatar.setImageBitmap(bitmap);
		
	}
	
	public static Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
                Config.ARGB_8888);

        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        paint.setAntiAlias(true);

        int halfWidth = bitmap.getWidth() / 2;
        int halfHeight = bitmap.getHeight() / 2;

        canvas.drawCircle(halfWidth, halfHeight, Math.max(halfWidth, halfHeight), paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));

        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(context);

		View contactItemView = inflater.inflate(R.layout.contact_item_layout, null);

		TextView textName = (TextView) contactItemView
				.findViewById(R.id.textName);
		contactItemView.setTag(R.id.textName, textName);

		TextView textPhone = (TextView) contactItemView.findViewById(R.id.textPhone);
		contactItemView.setTag(R.id.textPhone, textPhone);

		CheckBox cbFavourite = (CheckBox) contactItemView
				.findViewById(R.id.cbFavourite);
		contactItemView.setTag(R.id.cbFavourite, cbFavourite);
		
		ImageView imgAvatar = (ImageView) contactItemView
				.findViewById(R.id.imgAvatar);
		contactItemView.setTag(R.id.imgAvatar, imgAvatar);

		return contactItemView;
	}
	
	/*
	@Override
	public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
		// TODO Auto-generated method stub
		if (getFilterQueryProvider() != null) {
			return getFilterQueryProvider().runQuery(constraint);
		}
		
		FilterQueryProvider filterQueryProvider = new FilterQueryProvider() {
			
			@Override
			public Cursor runQuery(CharSequence constraint) {
				// TODO Auto-generated method stub
				Log.d("HTSI", constraint.toString());
				
				StringBuilder where = new StringBuilder();
			    where.
			    append(ContactDatabaseOpenHelper.COL_CONTACT_NAME)
			    .append(" like '%")
			    .append(constraint).append("%");
			    
				
			    Cursor cursor = managedQuery(ContactDataProvider.CONTENT_URI, ContactDataProvider.cols, 
			    		where.toString(), null, null);
				
				return cursor;
			}
		};
		
		
		return null;
	}*/

	

}
