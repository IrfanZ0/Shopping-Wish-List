package com.shop.shoppingwishlist;

import java.util.ArrayList;
import java.util.List;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class StackWidgetService extends RemoteViewsService{

	public class StackRemoteViewsFactory implements RemoteViewsFactory {
		
		private Context mContext;
	    List<String> wishes1 = new ArrayList<String>();
	    List<String> wishes2 = new ArrayList<String>();
	    List<String> wishes3 = new ArrayList<String>();
	    List<String> pictures = new ArrayList<String>();
	    List<String> names = new ArrayList<String>();

		public StackRemoteViewsFactory(Context applicationContext, Intent intent) {
			// TODO Auto-generated constructor stub
			 mContext = applicationContext;
		     intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		}

		@Override
		public void onCreate() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onDataSetChanged() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			wishes1.clear();
			wishes2.clear();
			wishes3.clear();
			pictures.clear();
			names.clear();
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return names.size();
		}

		@Override
		public RemoteViews getViewAt(int position) {
			// Adding values obtained from JSON classes into Lists
			
			for (int i = 0; i < getCount(); i+=3){
				wishes1.add(JSONclasses.wishes[i]);
				wishes2.add(JSONclasses.wishes[i + 1]);
				wishes3.add(JSONclasses.wishes[i + 2]);
			}
			
			for (int j = 0; j < getCount(); j++){
				pictures.add(JSONclasses.urlPic[j]);
				names.add(JSONclasses.names[j]);
			}
			
			RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.wish_stack_view);
			
			Bitmap bmpPic = BitmapFactory.decodeFile(pictures.get(position));
			rv.setImageViewBitmap(R.id.pictureID, bmpPic);
			
			rv.setTextViewText(R.id.nameID, names.get(position));
			rv.setTextViewText(R.id.wish1, wishes1.get(position));
			rv.setTextViewText(R.id.wish2, wishes2.get(position));
			rv.setTextViewText(R.id.wish3, wishes3.get(position));
			
			return rv;
		}

		@Override
		public RemoteViews getLoadingView() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getViewTypeCount() {
			// TODO Auto-generated method stub
			return 1;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return true;
		}

	}

	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		// TODO Auto-generated method stub
		return new StackRemoteViewsFactory(this.getApplicationContext(), intent);
	}

}
