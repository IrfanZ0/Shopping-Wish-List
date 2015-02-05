package com.shop.shoppingwishlist;

import java.util.ArrayList;
import java.util.List;

import android.R.color;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class Shop extends Activity {

	// This is Irfan's fabulous project 2
	static List<Integer> categoriesPic;
	static List<String> categoriesText;
	
	ImageView imgGrid;
	ImageView category1;
	ImageView category2;
	ImageView category3;
	Button nextBTN, newList;
	TextView w1Text, w2Text, w3Text;
	LayoutInflater inflator;
    Context context;
    static EditText NAME;
    Spinner spinName;
 
 static Integer dImage1, dImage2, dImage3;
//DragEventListener dragListener;
String[] mimeTypes = new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN};
static GridView gv;
ClipDescription cdClothes, cdComputer, cdDining, cdEbook, cdMovies, cdPets, cdTravel, cdVideoGames;
ClipData clothesData,computerData, diningData, ebookData, moviesData, petsData, travelData, videoGamesData;
ClipData.Item clothes_item, computers_item, dining_item, ebook_item, movies_item, pets_item, travel_item, videoGames_item;
static int[] dCatArray = new int[]{R.drawable.clothes, R.drawable.computer, R.drawable.dining, R.drawable.ebook, R.drawable.movies, R.drawable.pets, R.drawable.travel, R.drawable.video_games};
static String[] TAGS = new String[3];
static int position;
static View imgCat;
static CharSequence t1;
static CharSequence t2;
static CharSequence t3;
ClipData data = null;
static int rID1, rID2, rID3;
Drawable magicBeam;
CategoryOnItemLongClickListener cLongClickListener;
public Item magicbeam_item;
public ClipData magicbeamData;
public CharSequence noCategoryTag;
public Item noCategory_item;
public ClipData noCategoryData;
ArrayAdapter<String> nameAdapter;
List<String> mName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);
		
		mName = new ArrayList<String>();

		spinName = (Spinner) findViewById(R.id.spinName);
		
		nameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mName);
		
		NAME = (EditText) findViewById(R.id.nameID);
		
		newList = (Button) findViewById(R.id.NewList);
		
		newList.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// adding email to spinner
				mName.add(NAME.getText().toString());
				nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinName.setAdapter(nameAdapter);
				 				
			}
		});
		
		
		
		spinName.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String selected_name = spinName.getSelectedItem().toString();
				JSONclasses.readJSON search_name = new JSONclasses.readJSON();
				search_name.execute("http://www.mediaandroid.byethost17.com/wishjson.json", selected_name);
				
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		categoriesPic = new ArrayList<Integer>();
		categoriesText = new ArrayList<String>();
		
		categoriesPic.add(0, R.drawable.clothes);
		categoriesPic.add(1, R.drawable.computer);
		categoriesPic.add(2, R.drawable.dining);
		categoriesPic.add(3, R.drawable.ebook);
		categoriesPic.add(4, R.drawable.movies);
		categoriesPic.add(5, R.drawable.pets);
		categoriesPic.add(6, R.drawable.travel);
		categoriesPic.add(7, R.drawable.video_games);
		categoriesPic.add(8, R.drawable.no_category);
		
		
		categoriesText.add(0, "Clothes");
		categoriesText.add(1, "Computers");
		categoriesText.add(2, "Dining");
		categoriesText.add(3, "eBooks");
		categoriesText.add(4, "Movies");
		categoriesText.add(5, "Pets");
		categoriesText.add(6, "Travel");
		categoriesText.add(7, "Video Games");
		categoriesText.add(8, "No Category");
		
		
		
		
		w1Text = (TextView) findViewById (R.id.wish1Text);
		w2Text = (TextView) findViewById (R.id.wish2Text);
		w3Text = (TextView) findViewById (R.id.wish3Text);
		
		category1 = (ImageView) findViewById (R.id.wish1Cat);
		
				
		category2 = (ImageView) findViewById (R.id.wish2Cat);
		
		
		
		category3 = (ImageView) findViewById (R.id.wish3Cat);
		
		
		
		nextBTN = (Button) findViewById(R.id.buttonNext);
		
		nextBTN.setOnClickListener(new View.OnClickListener(){
				
			
			@Override
			public void onClick(View v) {
							
				
				// click to jump to next class WishList
				Intent wlIntent = new Intent(Shop.this, WishList.class);
				startActivity(wlIntent);
				
			}
		});
		
		
		

		gv = (GridView) findViewById(R.id.gridView1);
		gv.setAdapter(new CategoryAdapter(this));
		
		
				
		Category1DragEventListener cDragListener1 = new Category1DragEventListener();
		Category2DragEventListener cDragListener2 = new Category2DragEventListener();
		Category3DragEventListener cDragListener3 = new Category3DragEventListener();
		
		category1.setOnDragListener(cDragListener1);
		category2.setOnDragListener(cDragListener2);
		category3.setOnDragListener(cDragListener3);
		
		cLongClickListener = new CategoryOnItemLongClickListener();
		
		gv.setOnItemLongClickListener(cLongClickListener);
		
		
	}
	
	
	
	public class Category1DragEventListener implements View.OnDragListener{

		@Override
		public boolean onDrag(View v, DragEvent event) {
			
			final int action = event.getAction();
			switch (action){
			
			case DragEvent.ACTION_DRAG_STARTED:
				
				if(event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)){
				
					if (v.isHovered()){
						v.setBackgroundColor(color.holo_blue_light);
						v.invalidate();
					}
					
					
					
					break;
				}
				
		
				
			case DragEvent.ACTION_DRAG_ENTERED:
				
				Object o1 = gv.getAdapter().getItem(position);
				
				if (o1 instanceof Integer)
					dImage1 = (Integer) o1 ;
				
			
				break;
				
			
				
				
			case DragEvent.ACTION_DROP:
				// Depending of the three ImageViews hovered over by shadow, that ImageView 
				// shall obtain the drawable.
				
				
				
				
					
					category1.setImageResource(dImage1);
					category1.setVisibility(View.VISIBLE);
					
				
				
				
				 t1 = data.getItemAt(0).getText();
				 
				
					
					
				
				
				return true;
				
			case DragEvent.ACTION_DRAG_EXITED:
				
				v.setVisibility(View.VISIBLE);
				
				break;
				
			case DragEvent.ACTION_DRAG_LOCATION:
				
				 
				break;
		
			}
			
			return true;
			
		}
		
	}
	
	public class Category2DragEventListener implements View.OnDragListener{

		@Override
		public boolean onDrag(View v, DragEvent event) {
			
			final int action = event.getAction();
			switch (action){
			
			case DragEvent.ACTION_DRAG_STARTED:
				
				if(event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)){
				
										
					if (v.isHovered()){
						v.setBackgroundColor(color.holo_green_light);
						v.invalidate();
					}
					
					
					
					break;
				}
				
			
			case DragEvent.ACTION_DRAG_ENTERED:
				
				Object o2 = gv.getAdapter().getItem(position);
				
				if (o2 instanceof Integer)
					dImage2 = (Integer) o2;
				
			
				break;
				
			
				
				
			case DragEvent.ACTION_DROP:
				// Depending of the three ImageViews hovered over by shadow, that ImageView 
				// shall obtain the drawable.
				
				
				category2.setImageResource(dImage2);
				category2.setVisibility(View.VISIBLE);
				t2 = data.getItemAt(0).getText();
				
				 
				 
				
				
				
				
				
				return true;
			case DragEvent.ACTION_DRAG_EXITED:
				
				
				v.setVisibility(View.VISIBLE);
				
				
				break;
				
			case DragEvent.ACTION_DRAG_LOCATION:
				 
				break;
		
			}
			return true;
			
		}
		
	}
	
	
	public class Category3DragEventListener implements View.OnDragListener{

		@Override
		public boolean onDrag(View v, DragEvent event) {
			
			final int action = event.getAction();
			switch (action){
			
			case DragEvent.ACTION_DRAG_STARTED:
				
				if(event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)){
				
										
					if (v.isHovered()){
						v.setBackgroundColor(color.holo_orange_light);
						v.invalidate();
					}
					
					
					break;
				}
				
				
			case DragEvent.ACTION_DRAG_ENTERED:
				
				Object o3 = gv.getAdapter().getItem(position);
				
				if (o3 instanceof Integer)
				dImage3 = (Integer) o3;
				

				break;
				
					
				
			case DragEvent.ACTION_DROP:
				// Depending of the three ImageViews hovered over by shadow, that ImageView 
				// shall obtain the drawable.
				
				
				category3.setImageResource(dImage3);
				category3.setVisibility(View.VISIBLE);
				t3 = data.getItemAt(0).getText();
				
				 
				
				return true;
				
			case DragEvent.ACTION_DRAG_EXITED:
				
				v.setVisibility(View.VISIBLE);
				
				
				
				break;
				
			case DragEvent.ACTION_DRAG_LOCATION:
				 
				break;
		
			}
			return true;
			
		}
		
	}
	
	
	public class CategoryOnItemLongClickListener implements AdapterView.OnItemLongClickListener{

		@Override
		public boolean onItemLongClick(AdapterView<?> av, View v,
				int pos, long posID) {
			
			//ImageView convertView = (ImageView)v.findViewById(R.id.electronicsPic);
			// find the item in GridView that was long clicked, create a drag shadow of the view, and start the drag
			position = pos;
			
				
			CharSequence clothesTag = "Clothes";
			CharSequence computersTag = "Computers";
			CharSequence diningTag = "Dining";
			CharSequence ebookTag = "eBooks";
			CharSequence moviesTag = "Movies";
			CharSequence petsTag = "Pets";
			CharSequence travelTag = "Travel";
			CharSequence videogamesTag = "Video Games";
			CharSequence noCategoryTag = "No Category";
			
			
			
			
			
			View.DragShadowBuilder categoriesShadow;
				
				Integer dr = (Integer)av.getItemAtPosition(pos);
				
			switch(dr){
			case R.drawable.clothes:
				v.setTag(clothesTag);
			    clothesTag = (CharSequence) v.getTag();
				clothes_item = new ClipData.Item(clothesTag);
				CharSequence clothesText = clothes_item.coerceToText(context);
				clothesData = new ClipData(clothesText, mimeTypes, clothes_item);
				data = clothesData;
				v.setBackgroundResource(R.drawable.clothes);
				v.setVisibility(View.INVISIBLE);
				categoriesShadow = new CategoryShadowBuilder(v);
				v.startDrag(data, categoriesShadow, null, 0);
				
				break;
			case R.drawable.computer:
				v.setTag(computersTag);
				computersTag = (CharSequence) v.getTag();
				computers_item = new ClipData.Item(computersTag);
				CharSequence computersText = computers_item.coerceToText(context);
				computerData = new ClipData(computersText, mimeTypes, computers_item);
				data = computerData;
				v.setBackgroundResource(R.drawable.computer);
				v.setVisibility(View.INVISIBLE);
				categoriesShadow = new CategoryShadowBuilder(v);
				v.startDrag(data, categoriesShadow, null, 0);
				
				break;
			case R.drawable.dining:
				v.setTag(diningTag);
				diningTag = (CharSequence) v.getTag();
				dining_item = new ClipData.Item(diningTag);
				CharSequence diningText = dining_item.coerceToText(context);
				diningData = new ClipData(diningText, mimeTypes, dining_item);
				data = diningData;
				v.setBackgroundResource(R.drawable.dining);
				v.setVisibility(View.INVISIBLE);
				categoriesShadow = new CategoryShadowBuilder(v);
				v.startDrag(data, categoriesShadow, null, 0);
				
				break;
			case R.drawable.ebook:
				v.setTag(ebookTag);
				ebookTag = (CharSequence) v.getTag();
				ebook_item = new ClipData.Item(ebookTag);
				CharSequence eBookText = ebook_item.coerceToText(context);
				ebookData = new ClipData(eBookText, mimeTypes, ebook_item);
				data = ebookData;
				v.setBackgroundResource(R.drawable.ebook);
				v.setVisibility(View.INVISIBLE);
				categoriesShadow = new CategoryShadowBuilder(v);
				v.startDrag(data, categoriesShadow, null, 0);
				
				break;
			case R.drawable.movies:
				v.setTag(moviesTag);
				moviesTag = (CharSequence) v.getTag();
				movies_item = new ClipData.Item(moviesTag);
				CharSequence moviesText = movies_item.coerceToText(context);
				moviesData = new ClipData(moviesText, mimeTypes, movies_item);
				data = moviesData;
				v.setBackgroundResource(R.drawable.movies);
				v.setVisibility(View.INVISIBLE);
				categoriesShadow = new CategoryShadowBuilder(v);
				v.startDrag(data, categoriesShadow, null, 0);
				
				break;
			case R.drawable.pets:
				v.setTag(petsTag);
				petsTag = (CharSequence) v.getTag();
				pets_item = new ClipData.Item(petsTag);
				CharSequence petsText = pets_item.coerceToText(context);
				petsData = new ClipData(petsText, mimeTypes, pets_item);
				data = petsData;
				v.setBackgroundResource(R.drawable.pets);
				v.setVisibility(View.INVISIBLE);
				categoriesShadow = new CategoryShadowBuilder(v);
				v.startDrag(data, categoriesShadow, null, 0);
				
				break;
			case R.drawable.travel:
				v.setTag(travelTag);
				travelTag = (CharSequence) v.getTag();
				travel_item = new ClipData.Item(travelTag);
				CharSequence travelText = travel_item.coerceToText(context);
				travelData = new ClipData(travelText, mimeTypes, travel_item);
				data = travelData;
				v.setBackgroundResource(R.drawable.travel);
				v.setVisibility(View.INVISIBLE);
				categoriesShadow = new CategoryShadowBuilder(v);
				v.startDrag(data, categoriesShadow, null, 0);
				
				break;
			case R.drawable.video_games:
				v.setTag(videogamesTag);
				videogamesTag = (CharSequence) v.getTag();
				videoGames_item = new ClipData.Item(videogamesTag);
				CharSequence videogamesText = videoGames_item.coerceToText(context);
				videoGamesData = new ClipData(videogamesText, mimeTypes, videoGames_item);
				data = videoGamesData;
				v.setBackgroundResource(R.drawable.video_games);
				v.setVisibility(View.INVISIBLE);
				categoriesShadow = new CategoryShadowBuilder(v);
				v.startDrag(data, categoriesShadow, null, 0);
				
				break;
				
			case R.drawable.no_category:
				v.setTag(noCategoryTag);
				noCategoryTag = (CharSequence) v.getTag();
				noCategory_item = new ClipData.Item(noCategoryTag);
				CharSequence noCategoryText = noCategory_item.coerceToText(context);
				noCategoryData = new ClipData(noCategoryText, mimeTypes, noCategory_item);
				data = noCategoryData;
				v.setScaleX(0.5f);
				v.setScaleY(0.5f);
				v.setVisibility(View.VISIBLE);
				v.setBackgroundResource(R.drawable.no_category);
				
				categoriesShadow = new CategoryShadowBuilder(v);
				v.startDrag(data, categoriesShadow, null, 0);
				
				
				break;
			}
				
				
								
			return true;
		}

}

	public static class CategoryShadowBuilder extends View.DragShadowBuilder {
		
		static Drawable dShadowImage;
		int width;
		int height;
		
		public CategoryShadowBuilder(View v) {
			
			super(v);
			
			
			dShadowImage = v.getBackground();
			 
			
					 
		}

		@Override
		public void onProvideShadowMetrics (Point size, Point touch){
	    // Defines local variables
	    
			
			
	    // Sets the width of the shadow to half the width of the original View
	    width = getView().getWidth() / 4;

	    // Sets the height of the shadow to half the height of the original View
	    height = getView().getHeight() / 4;

	    // The drag shadow is a ColorDrawable. This sets its dimensions to be the same as the
	    // Canvas that the system will provide. As a result, the drag shadow will fill the
	    // Canvas.
	    dShadowImage.setBounds(0, 0, width, height);

	    // Sets the size parameter's width and height values. These get back to the system
	    // through the size parameter.
	    size.set(width, height);

	    // Sets the touch point's position to be in the middle of the drag shadow
	    touch.set(width / 2, height / 2);
	}
		
		// Defines a callback that draws the drag shadow in a Canvas that the system constructs
	    // from the dimensions passed in onProvideShadowMetrics().
	    @Override
	    public void onDrawShadow(Canvas canvas) {

	       
	        dShadowImage.draw(canvas);


		}
		
	}
		
	
	

}
