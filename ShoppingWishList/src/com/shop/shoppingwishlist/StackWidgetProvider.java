package com.shop.shoppingwishlist;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

public class StackWidgetProvider extends AppWidgetProvider{
	
	 @Override
	    public void onDeleted(Context context, int[] appWidgetIds) {
	        super.onDeleted(context, appWidgetIds);
	    }

	    @Override
	    public void onDisabled(Context context) {
	        super.onDisabled(context);
	    }

	    @Override
	    public void onEnabled(Context context) {
	        super.onEnabled(context);
	    }

	    @Override
	    public void onReceive(Context context, Intent intent) {
	        super.onReceive(context, intent);
	    }

	    @Override
	    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
	        for (int i = 0; i < appWidgetIds.length; ++i) {
	            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_wish_list);

	            // set intent for widget service that will create the views
	            Intent serviceIntent = new Intent(context, StackWidgetService.class);
	            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
	            serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME))); // embed extras so they don't get ignored
	            remoteViews.setRemoteAdapter (R.id.stackWidgetView, serviceIntent);
	            remoteViews.setEmptyView(R.id.stackWidgetView, R.id.stackWidgetEmptyView);
	            
	           
	            
	            // update widget
	            appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
	        }
	        super.onUpdate(context, appWidgetManager, appWidgetIds);
	    }

}
