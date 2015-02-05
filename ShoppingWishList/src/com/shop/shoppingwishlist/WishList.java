package com.shop.shoppingwishlist;

import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.StackView;

public class WishList extends FragmentActivity {
	
	
	Spinner spin1, spin2, spin3;
	Shop s;
	int rID1, rID2, rID3;
	String[] Clothes_cat;
	String[] Computers_cat;
	String[] Dining_cat;
	String[] eBooks_cat;
	String[] Movies_cat;
	String[] Pets_cat;
	String[] Travel_cat;
	String[] VideoGames_cat;
	String[] Categories;
	int resID1, resID2, resID3;
	Context contextWish;
	CharSequence clothesText = "Clothes";
	CharSequence computersText = "Computers";
	CharSequence diningText = "Dining";
	CharSequence ebookText = "eBooks";
	CharSequence moviesText = "Movies";
	CharSequence petsText = "Pets";
	CharSequence travelText = "Travel";
	CharSequence videogamesText = "Video Games";
	public static String WishList = "WishList";
	EditText fName, lName;
	String first, last, wish1, wish2, wish3;
	static AsyncTask<String, Integer, String[]> existvalues;
	AsyncTask<String, Integer, Long> addValuesID;
	ArrayAdapter<String> contactsAdapter;
	ArrayAdapter<String>wishAdapter;
	static ArrayAdapter<CharSequence> s1adapter;
	static ArrayAdapter<CharSequence> s2adapter;
	static ArrayAdapter<CharSequence> s3adapter;
	static SimpleCursorAdapter wlAdapter;
	ArrayList<String>contacts;
	GestureDetectorCompat mDetector;
	static String str1, str2, str3;
	String name, itemContacts;
	String[] data;
	StackView sv;
	
	public WishList(){
		
	}
	
	public WishList(Context context){
		this.contextWish = context;
		
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wish_list);
		
		
		
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		mDetector = new GestureDetectorCompat(this, new myGestureListener());
		
		
		contextWish = getApplicationContext();
		
		
		spin1 = (Spinner) findViewById (R.id.category1);
		spin2 = (Spinner) findViewById(R.id.category2);
		 spin3 = (Spinner) findViewById(R.id.category3);
		// fName = (EditText)findViewById(R.id.firstName);
		// lName = (EditText)findViewById(R.id.lastName);
		 
								
		 
		// collecting drawables from the three ImageViews in Shop class and using that to set text to individual EditTexts.
		
		Integer d1 = Shop.dImage1;
		if (d1 == R.drawable.clothes){
			
			s1adapter = ArrayAdapter.createFromResource(this, R.array.Clothes, android.R.layout.simple_spinner_item);
			
			s1adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin1.setAdapter(s1adapter);
		}
		else if (d1 == R.drawable.computer){
			
			s1adapter = ArrayAdapter.createFromResource(this, R.array.Computers, android.R.layout.simple_spinner_item);
			
			s1adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin1.setSelection(0);
			
			spin1.setAdapter(s1adapter);
		}
		else if (d1 == R.drawable.dining){
			
			
			s1adapter = ArrayAdapter.createFromResource(this, R.array.Dining, android.R.layout.simple_spinner_item);
			
			s1adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin1.setSelection(0);
			
			spin1.setAdapter(s1adapter);
		}
		else if (d1 == R.drawable.ebook){
			
			s1adapter = ArrayAdapter.createFromResource(this, R.array.eBooks, android.R.layout.simple_spinner_item);
			
			s1adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin1.setSelection(0);
			
			spin1.setAdapter(s1adapter);
		}
		else if (d1 == R.drawable.movies){
			
			
			s1adapter = ArrayAdapter.createFromResource(this, R.array.Movies, android.R.layout.simple_spinner_item);
			
			s1adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin1.setSelection(0);
			
			spin1.setAdapter(s1adapter);
		}
		else if (d1 == R.drawable.pets){
						
			s1adapter = ArrayAdapter.createFromResource(this, R.array.Pets, android.R.layout.simple_spinner_item);
			
			s1adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin1.setSelection(0);
			
			spin1.setAdapter(s1adapter);
		}
		else if (d1 == R.drawable.travel){
			
			
			s1adapter = ArrayAdapter.createFromResource(this, R.array.Travel, android.R.layout.simple_spinner_item);
			
			s1adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin1.setSelection(0);
			
			spin1.setAdapter(s1adapter);
		}
		else if (d1 == R.drawable.video_games){
		
			s1adapter = ArrayAdapter.createFromResource(this, R.array.VideoGames, android.R.layout.simple_spinner_item);
			
			s1adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin1.setSelection(0);
			
			spin1.setAdapter(s1adapter);
		}
		else{
			// ImageView 1 (Category 1) has Magic Beam image.  If so add null to spinner
			
			s1adapter = ArrayAdapter.createFromResource(this, R.array.Empty, android.R.layout.simple_spinner_item);
			
			s1adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin1.setSelection(0);
			
			spin1.setAdapter(s1adapter);
			
			
			
			
		}
			
		
		
		spin1.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> av, View v,
					int pos, long posID) {
				// TODO Auto-generated method stub
				
							
					String selectedItem = spin1.getSelectedItem().toString();
					String parentItem = spin1.getAdapter().getItem(0).toString();
					
				if (selectedItem.contains("Levis")){
					selectedItem.toLowerCase(Locale.US);
															
					String uriString = "http://www.macys.com/m/campaign/levis/levis?cm_mmc=VanityUrl-_-levis-_-n-_-n";
					
					Uri uriclothes = Uri.parse(uriString);
					Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
					startActivity(intentclothes);
				}
				
				else if (selectedItem.contains("Dockers")){
					
					String uriString = "http://www1.macys.com/shop/search?keyword=dockers#cm_pv=slp";
					
					Uri uriclothes = Uri.parse(uriString);
					Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
					startActivity(intentclothes);
				}
				
				else if (selectedItem.contains("Calvin Klein")){
					
					
					String uriString = "http://www1.macys.com/shop/search?keyword=calvin+klein#cm_pv=slp";
					
					Uri uriclothes = Uri.parse(uriString);
					Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
					startActivity(intentclothes);
				}
				
				else if (selectedItem.contains("Guess")){
					
					
					String uriString = "http://www1.macys.com/shop/search?keyword=guess#cm_pv=slp";
					
					Uri uriclothes = Uri.parse(uriString);
					Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
					startActivity(intentclothes);
				}
				
				else if (selectedItem.contains("Lucky Brand")){
					
					
					String uriString = "http://www1.macys.com/shop/search?keyword=lucky+brand#cm_pv=slp";
					
					Uri uriclothes = Uri.parse(uriString);
					Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
					startActivity(intentclothes);
				}
				
				else if (selectedItem.contains("Nike")){
					
					
					String uriString = "http://www1.macys.com/shop/search?keyword=nike#cm_pv=slp";
					
					Uri uriclothes = Uri.parse(uriString);
					Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
					startActivity(intentclothes);
				}
				
				else if (selectedItem.contains("Carter")){
					
					
					String uriString = "http://www1.macys.com/shop/search?keyword=carter%27s#cm_pv=slp";
					
					Uri uriclothes = Uri.parse(uriString);
					Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
					startActivity(intentclothes);
				}
				
				else if (selectedItem.contains("Tommy Hilfiger")){
					
					
					
					String uriString = "http://www1.macys.com/shop/search?keyword=tommy+hilfiger#cm_pv=slp";
					
					Uri uriclothes = Uri.parse(uriString);
					Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
					startActivity(intentclothes);
				}
				
						
				else if (selectedItem.contains("Sony")){
					
					
					String uriString = "http://www.bestbuy.com/site/Brands/Sony-Store/cat15063.c?id=cat15063&pageType=REDIRECT&issolr=1&searchterm=Sony";
					
					Uri uricomputers = Uri.parse(uriString);
					Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
					startActivity(intentcomputers);
					
				}
				 
				 else if (selectedItem.contains("MacBook Air")){
					 
						String uriString = "http://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=15&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&fs=saas&saas=saas&keys=keys&st=MacBook+Air";
						
						Uri uricomputers = Uri.parse(uriString);
						Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
						startActivity(intentcomputers);
						
					}
				 
				 else if (selectedItem.contains("Apple")){
						
						
						String uriString = "http://www.bestbuy.com/site/Brands/Apple/pcmcat128500050005.c?id=pcmcat128500050005&pageType=REDIRECT&issolr=1&searchterm=Apple";
						
						Uri uricomputers = Uri.parse(uriString);
						Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
						startActivity(intentcomputers);
						
					}
				 
				 else if (selectedItem.contains("Samsung")){
						
						
						String uriString = "http://www.bestbuy.com/site/Brands/Samsung/pcmcat140800050115.c?id=pcmcat140800050115&pageType=REDIRECT&issolr=1&searchterm=Samsung";
						Uri uricomputers = Uri.parse(uriString);
						Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
						startActivity(intentcomputers);
						
					}
				 
				 else if (selectedItem.contains("Acer")){
						
						
						String uriString = "http://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=15&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&fs=saas&saas=saas&keys=keys&st=Acer";
						
						Uri uricomputers = Uri.parse(uriString);
						Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
						startActivity(intentcomputers);
						
					}
				 
				 else if (selectedItem.contains("Asus")){
						
						
						String uriString = "http://www.bestbuy.com/site/Brands/Asus/pcmcat190000050006.c?id=pcmcat190000050006&pageType=REDIRECT&issolr=1&searchterm=Asus";
						
						Uri uricomputers = Uri.parse(uriString);
						Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
						startActivity(intentcomputers);
						
					}
				
				 else if (selectedItem.contains("Lenovo")){
						
						
						String uriString = "http://www.bestbuy.com/site/Brands/Lenovo/pcmcat230600050000.c?id=pcmcat230600050000&pageType=REDIRECT&issolr=1&searchterm=Lenovo";
						
						Uri uricomputers = Uri.parse(uriString);
						Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
						startActivity(intentcomputers);
						
					}
				
				 else if (selectedItem.contains("Toshiba")){
						
						
						String uriString = "http://www.bestbuy.com/site/Brands/Toshiba/pcmcat136800050058.c?id=pcmcat136800050058&pageType=REDIRECT&issolr=1&searchterm=Toshiba";
						
						Uri uricomputers = Uri.parse(uriString);
						Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
						startActivity(intentcomputers);
						
					}
				
						
				else if (selectedItem.contains("Papa Johns")){
				
					
					
					String uriString = "http://www.papajohns.com/index.html";
					
					Uri uridining = Uri.parse(uriString);
					Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
					startActivity(intentdining);
				}
				
				else if (selectedItem.contains("BJs Restaurants")){
					
					
					
					
					
					String uriString = "http://www.bjsrestaurants.com/";
					
					Uri uridining = Uri.parse(uriString);
					Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
					startActivity(intentdining);
				}
				
				else if (selectedItem.contains("Dennys")){
					
					
					String uriString = "http://www.dennys.com/";
					
					Uri uridining = Uri.parse(uriString);
					Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
					startActivity(intentdining);
				}
				
				else if (selectedItem.contains("California Sushi Roll")){
					

					
					String uriString = "http://www.california-sushi-roll.com/";
					
					Uri uridining = Uri.parse(uriString);
					Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
					startActivity(intentdining);
				}
				
				else if (selectedItem.contains("Taco Bell")){
					
					
					String uriString = "www.tacobell.com/";
					
					Uri uridining = Uri.parse(uriString);
					Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
					startActivity(intentdining);
				}
				
				else if (selectedItem.contains("The Cheesecake Factory")){
					
				
					
					String uriString = "http://www.thecheesecakefactory.com/";
					
					Uri uridining = Uri.parse(uriString);
					Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
					startActivity(intentdining);
				}
				
				else if (selectedItem.contains("Pizza Hut")){
					
					
					
					String uriString = "http://www.pizzahut.com/";
					
					Uri uridining = Uri.parse(uriString);
					Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
					startActivity(intentdining);
				}
				
				else if (selectedItem.contains("Carls Jr")){
					
					
					
					String uriString = "http://www.carlsjr.com/";
					
					Uri uridining = Uri.parse(uriString);
					Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
					startActivity(intentdining);
				}
				
				else if (selectedItem.contains("Romance") && parentItem.contains("eBooks")){
					
					
					String uriString = "http://www.ebooks.com/subjects/Romance/";
					
					Uri uriebooks = Uri.parse(uriString);
					Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
					startActivity(intentebooks);
				}
				
				
				else if (selectedItem.contains("Science Fiction") && parentItem.contains("eBooks")){
									
					
					
									String uriString = "http://www.ebooks.com/subjects/Science-Fiction/";
									
									Uri uriebooks = Uri.parse(uriString);
									Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
									startActivity(intentebooks);
								}
								
				else if (selectedItem.contains("Technology") && parentItem.contains("eBooks")){
					
					
					String uriString = "http://www.ebooks.com/subjects/Technology/";
					
					Uri uriebooks = Uri.parse(uriString);
					Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
					startActivity(intentebooks);
				}
								
				else if (selectedItem.contains("Business") && parentItem.contains("eBooks")){
					
					
					String uriString = "http://www.ebooks.com/subjects/Business/";
					
					Uri uriebooks = Uri.parse(uriString);
					Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
					startActivity(intentebooks);
				}
								
				else if (selectedItem.contains("Health") && parentItem.contains("eBooks")){
					
					
					String uriString = "http://www.ebooks.com/subjects/Health/";
					
					Uri uriebooks = Uri.parse(uriString);
					Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
					startActivity(intentebooks);
				}
								
				else if (selectedItem.contains("History") && parentItem.contains("eBooks")){
					
					
					String uriString = "http://www.ebooks.com/subjects/History/";
					
					Uri uriebooks = Uri.parse(uriString);
					Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
					startActivity(intentebooks);
				}
								
				else if (selectedItem.contains("Mystery") && parentItem.contains("eBooks")){
					
					
					String uriString = "http://www.ebooks.com/subjects/Mystery/";
					
					Uri uriebooks = Uri.parse(uriString);
					Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
					startActivity(intentebooks);
				}
								
				else if (selectedItem.contains("Childrens") && parentItem.contains("eBooks")){
					
					
					String uriString = "http://www.ebooks.com/subjects/Childrens-young-adult-fiction/";
					
					Uri uriebooks = Uri.parse(uriString);
					Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
					startActivity(intentebooks);
				}
				
				
				
				else if (selectedItem.contains("Science Fiction") && parentItem.contains("Movies")){
					
					
					
					String uriString = "https://www.amctheatres.com/";
					
					Uri urimovies = Uri.parse(uriString);
					Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
					startActivity(intentmovies);
				}
				
				else if (selectedItem.contains("Mystery") && parentItem.contains("Movies")){
									
									
									
									String uriString = "https://www.amctheatres.com/";
									
									Uri urimovies = Uri.parse(uriString);
									Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
									startActivity(intentmovies);
								}
								
				else if (selectedItem.contains("Horror") && parentItem.contains("Movies")){
					
					
					
					String uriString = "https://www.amctheatres.com/";
					
					Uri urimovies = Uri.parse(uriString);
					Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
					startActivity(intentmovies);
				}
								
				else if (selectedItem.contains("Comedy") && parentItem.contains("Movies")){
					
					
					
					String uriString = "https://www.amctheatres.com/";
					
					Uri urimovies = Uri.parse(uriString);
					Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
					startActivity(intentmovies);
				}
								
				else if (selectedItem.contains("Romantic") && parentItem.contains("Movies")){
					
					
					
					String uriString = "https://www.amctheatres.com/";
					
					Uri urimovies = Uri.parse(uriString);
					Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
					startActivity(intentmovies);
				}
								
				else if (selectedItem.contains("Children's") && parentItem.contains("Movies")){
					
					
					
					String uriString ="https://www.amctheatres.com/";
					
					Uri urimovies = Uri.parse(uriString);
					Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
					startActivity(intentmovies);
				}
								
				else if (selectedItem.contains("Family") && parentItem.contains("Movies")){
					
					
					
					String uriString = "http://www.amctheatres.com/";
					
					Uri urimovies = Uri.parse(uriString);
					Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
					startActivity(intentmovies);
				}
								
				else if (selectedItem.contains("Action") && parentItem.contains("Movies")){
					
					
					
					String uriString = "https://www.amctheatres.com/";
					
					Uri urimovies = Uri.parse(uriString);
					Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
					startActivity(intentmovies);
				}
				
				
				
				else if (selectedItem.contains("Cat")){
				
					
					
					String uriString = "http://www.petco.com/Cat-Home.aspx";
					
					Uri uripets = Uri.parse(uriString);
					Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
					startActivity(intentpets);
				}
				
				else if (selectedItem.contains("Dog")){
								
									
									
									String uriString = "http://www.petco.com/Dog-Home.aspx";
									
									Uri uripets = Uri.parse(uriString);
									Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
									startActivity(intentpets);
								}
								
				else if (selectedItem.contains("Bird")){
					
					
					
					String uriString = "http://www.petco.com/Bird-Home.aspx";
					
					Uri uripets = Uri.parse(uriString);
					Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
					startActivity(intentpets);
				}
								
				else if (selectedItem.contains("Fish")){
					
					
					String uriString = "http://www.petco.com/Fish-Home.aspx";
					
					Uri uripets = Uri.parse(uriString);
					Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
					startActivity(intentpets);
				}
								
				else if (selectedItem.contains("Rabbits")){
					
					
					
					String uriString = "http://www.petco.com/Rabbits-Home.aspx?CoreSearch=Rabbit";
					
					Uri uripets = Uri.parse(uriString);
					Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
					startActivity(intentpets);
				}
								
				else if (selectedItem.contains("Hamsters")){
					
					
					
					String uriString = "http://www.petco.com/HamstersHome.aspx?CoreSearch=Hamsters";
					
					Uri uripets = Uri.parse(uriString);
					Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
					startActivity(intentpets);
				}
								
				else if (selectedItem.contains("Turtles")){
					
					
					
					String uriString = "http://www.petco.com/Turtles-Home.aspx?CoreSearch=Turtles";
					
					Uri uripets = Uri.parse(uriString);
					Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
					startActivity(intentpets);
				}
								
				else if (selectedItem.contains("Snakes")){
					
					
					
					String uriString = "http://www.petco.com/Snakes-Home.aspx?CoreSearch=Snakes";
					
					Uri uripets = Uri.parse(uriString);
					Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
					startActivity(intentpets);
				}
				
				else if (selectedItem.contains("Universal Studios")){
					
					
					String uriString = "http://www.universalstudios.com/";
					
					Uri uritravel = Uri.parse(uriString);
					Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
					startActivity(intenttravel);
				}
				
				else if (selectedItem.contains("Magic Mountain")){
									
					
					
					String uriString = "https://www.sixflags.com";
					
					Uri uritravel = Uri.parse(uriString);
					Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
					startActivity(intenttravel);
				}
								
				else if (selectedItem.contains("Sea World")){
					
					
					
					String uriString = "https://www.seaworld.com/";
					
					Uri uritravel = Uri.parse(uriString);
					Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
					startActivity(intenttravel);
				}
								
				else if (selectedItem.contains("Legoland")){
					
					
					
					String uriString = "https://www.legoland.com/";
					
					Uri uritravel = Uri.parse(uriString);
					Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
					startActivity(intenttravel);
				}
								
				else if (selectedItem.contains("Disneyland")){
					
					
					
					String uriString = "https://www.disneyland.com/";
					
					Uri uritravel = Uri.parse(uriString);
					Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
					startActivity(intenttravel);
				}
								
				else if (selectedItem.contains("San Diego Wild Animal Park")){
					
					
					String uriString = "http://www.sdzsafaripark.org/";
					
					Uri uritravel = Uri.parse(uriString);
					Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
					startActivity(intenttravel);
				}
								
				else if (selectedItem.contains("Disney California")){
					
					
					
					String uriString = "http://disneyland.disney.go.com/destinations/disney-california-adventure/";
					
					Uri uritravel = Uri.parse(uriString);
					Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
					startActivity(intenttravel);
				}
								
				else if (selectedItem.contains("Hurricane Harbor")){
					
					
					
					String uriString = "https://www.sixflags.com/hurricaneharborla";
					
					Uri uritravel = Uri.parse(uriString);
					Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
					startActivity(intenttravel);
				}
				
				else if (selectedItem.contains("Playstation 4")){
					
										
					String uriString = "http://www.bestbuy.com/site/Video-Games/PlayStation-4-PS4/pcmcat295700050012.c?id=pcmcat295700050012&pageType=REDIRECT&issolr=1&searchterm=Playstation%204";
					
					Uri urivideogames = Uri.parse(uriString);
					Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
					startActivity(intentvideogames);
				}
				
				else if (selectedItem.contains("XBOX")){
					
					
					String uriString = "http://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=15&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&fs=saas&saas=saas&keys=keys&st=XBOX";
					
					Uri urivideogames = Uri.parse(uriString);
					Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
					startActivity(intentvideogames);
				}
				
				else if (selectedItem.contains("Nintendo Wii U")){
					
					
					String uriString = "http://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=15&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&fs=saas&saas=saas&keys=keys&st=Nintendo+Wii+U";
					
					Uri urivideogames = Uri.parse(uriString);
					Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
					startActivity(intentvideogames);
				}
				
				else if (selectedItem.contains("Playstation 3")){
					
					
					String uriString = "http://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=15&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&fs=saas&saas=saas&keys=keys&st=Playstation+3";
					
					Uri urivideogames = Uri.parse(uriString);
					Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
					startActivity(intentvideogames);
				}
				
				else if (selectedItem.contains("Nintendo Wii")){
					
					
					String uriString = "http://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=15&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&fs=saas&saas=saas&keys=keys&st=Nintendo+Wii";
					
					Uri urivideogames = Uri.parse(uriString);
					Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
					startActivity(intentvideogames);
				}
				
				else if (selectedItem.contains("Android")){
					
					
					String uriString = "https://play.google.com/store";
					
					Uri urivideogames = Uri.parse(uriString);
					Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
					startActivity(intentvideogames);
				}
				
				else if (selectedItem.contains("iPhone")){
					
					
					String uriString = "http://store.apple.com/us";
					
					Uri urivideogames = Uri.parse(uriString);
					Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
					startActivity(intentvideogames);
				}
				
				else if (selectedItem.contains("PC")){
					
					
					String uriString = "http://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=15&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&fs=saas&saas=saas&keys=keys&st=PC+games";
					
					Uri urivideogames = Uri.parse(uriString);
					Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
					startActivity(intentvideogames);
				}
				
				else{
					
				}
				
					
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> av) {
				// TODO Auto-generated method stub
				
			}
			
		});
						
		Integer d2 = Shop.dImage2;
		if (d2 == R.drawable.clothes){
			
			
			s2adapter = ArrayAdapter.createFromResource(this, R.array.Clothes, android.R.layout.simple_spinner_item);
			
			s2adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin2.setSelection(0);
			
			spin2.setAdapter(s2adapter);
		}
		else if (d2 == R.drawable.computer){
			
			
			s2adapter = ArrayAdapter.createFromResource(this, R.array.Computers, android.R.layout.simple_spinner_item);
			
			s2adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spin2.setSelection(0);
			
			spin2.setAdapter(s2adapter);
		}
		else if (d2 == R.drawable.dining){
			
			
			s2adapter = ArrayAdapter.createFromResource(this, R.array.Dining, android.R.layout.simple_spinner_item);
			
			s2adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin2.setSelection(0);
			
			spin2.setAdapter(s2adapter);
		}
		else if (d2 == R.drawable.ebook){
			
			
			s2adapter = ArrayAdapter.createFromResource(this, R.array.eBooks, android.R.layout.simple_spinner_item);
			
			s2adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin2.setSelection(0);
			
			spin2.setAdapter(s2adapter);
		}
		else if (d2 == R.drawable.movies){
			
			
			s2adapter = ArrayAdapter.createFromResource(this, R.array.Movies, android.R.layout.simple_spinner_item);
			
			s2adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin2.setSelection(0);
			
			spin2.setAdapter(s2adapter);
		}
		else if (d2 == R.drawable.pets){
			
			
			s2adapter = ArrayAdapter.createFromResource(this, R.array.Pets, android.R.layout.simple_spinner_item);
			
			s2adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin2.setSelection(0);
			
			spin2.setAdapter(s2adapter);
		}
		else if (d2 == R.drawable.travel){
			
			
			s2adapter = ArrayAdapter.createFromResource(this, R.array.Travel, android.R.layout.simple_spinner_item);
			
			s2adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin2.setSelection(0);
			
			spin2.setAdapter(s2adapter);
		}
		
		else if (d2 == R.drawable.video_games){
			
			s2adapter = ArrayAdapter.createFromResource(this, R.array.VideoGames, android.R.layout.simple_spinner_item);
			
			s2adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin2.setSelection(0);
			
			spin2.setAdapter(s2adapter);
		}
		else{
			
			
			
			
			s2adapter = ArrayAdapter.createFromResource(this, R.array.Empty, android.R.layout.simple_spinner_item);
			
			s2adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin2.setSelection(0);
			
			spin2.setAdapter(s2adapter);
	
		}
		
		
		
		
		
		
		spin2.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> av, View v,
					int pos, long posID) {
				// TODO Auto-generated method stub
				
									
				
				String selectedItem = spin2.getSelectedItem().toString();
				String parentItem = spin2.getAdapter().getItem(0).toString();
				
			if (selectedItem.contains("Levis")){
				selectedItem.toLowerCase(Locale.US);
														
				String uriString = "http://www.macys.com/m/campaign/levis/levis?cm_mmc=VanityUrl-_-levis-_-n-_-n";
				
				Uri uriclothes = Uri.parse(uriString);
				Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
				startActivity(intentclothes);
			}
			
			else if (selectedItem.contains("Dockers")){
				
				
				String uriString = "http://www1.macys.com/shop/search?keyword=dockers#cm_pv=slp";
				
				Uri uriclothes = Uri.parse(uriString);
				Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
				startActivity(intentclothes);
			}
			
			else if (selectedItem.contains("Calvin Klein")){
				
				
				String uriString = "http://www1.macys.com/shop/search?keyword=calvin+klein#cm_pv=slp";
				
				Uri uriclothes = Uri.parse(uriString);
				Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
				startActivity(intentclothes);
			}
			
			else if (selectedItem.contains("Guess")){
				
				
				String uriString = "http://www1.macys.com/shop/search?keyword=guess#cm_pv=slp";
				
				Uri uriclothes = Uri.parse(uriString);
				Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
				startActivity(intentclothes);
			}
			
			else if (selectedItem.contains("Lucky Brand")){
				
				
				String uriString = "http://www1.macys.com/shop/search?keyword=lucky+brand#cm_pv=slp";
				
				Uri uriclothes = Uri.parse(uriString);
				Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
				startActivity(intentclothes);
			}
			
			else if (selectedItem.contains("Nike")){
				
				
				String uriString = "http://www1.macys.com/shop/search?keyword=nike#cm_pv=slp";
				
				Uri uriclothes = Uri.parse(uriString);
				Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
				startActivity(intentclothes);
			}
			
			else if (selectedItem.contains("Carter")){
				
				
				String uriString = "http://www1.macys.com/shop/search?keyword=carter%27s#cm_pv=slp";
				
				Uri uriclothes = Uri.parse(uriString);
				Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
				startActivity(intentclothes);
			}
			
			else if (selectedItem.contains("Tommy Hilfiger")){
				
				
				
				String uriString = "http://www1.macys.com/shop/search?keyword=tommy+hilfiger#cm_pv=slp";
				
				Uri uriclothes = Uri.parse(uriString);
				Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
				startActivity(intentclothes);
			}
			
					
			else if (selectedItem.contains("Sony")){
				
				
				String uriString = "http://www.bestbuy.com/site/Brands/Sony-Store/cat15063.c?id=cat15063&pageType=REDIRECT&issolr=1&searchterm=Sony";
				
				Uri uricomputers = Uri.parse(uriString);
				Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
				startActivity(intentcomputers);
				
			}
			 
			 else if (selectedItem.contains("MacBook Air")){
				 
					String uriString = "http://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=15&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&fs=saas&saas=saas&keys=keys&st=macbook+air";
					
					Uri uricomputers = Uri.parse(uriString);
					Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
					startActivity(intentcomputers);
					
				}
			 
			 else if (selectedItem.contains("Apple")){
					
					
					String uriString = "http://www.bestbuy.com/site/Brands/Apple/pcmcat128500050005.c?id=pcmcat128500050005&pageType=REDIRECT&issolr=1&searchterm=Apple";
					
					Uri uricomputers = Uri.parse(uriString);
					Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
					startActivity(intentcomputers);
					
				}
			 
			 else if (selectedItem.contains("Samsung")){
					
					
					String uriString = "http://www.bestbuy.com/site/Brands/Samsung/pcmcat140800050115.c?id=pcmcat140800050115&pageType=REDIRECT&issolr=1&searchterm=Samsung";
					Uri uricomputers = Uri.parse(uriString);
					Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
					startActivity(intentcomputers);
					
				}
			 
			 else if (selectedItem.contains("Acer")){
					
					
					String uriString = "http://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=15&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&fs=saas&saas=saas&keys=keys&st=Acer";
					
					Uri uricomputers = Uri.parse(uriString);
					Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
					startActivity(intentcomputers);
					
				}
			 
			 else if (selectedItem.contains("Asus")){
					
					
					String uriString = "http://www.bestbuy.com/site/Brands/Asus/pcmcat190000050006.c?id=pcmcat190000050006&pageType=REDIRECT&issolr=1&searchterm=Asus";
					
					Uri uricomputers = Uri.parse(uriString);
					Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
					startActivity(intentcomputers);
					
				}
			
			 else if (selectedItem.contains("Lenovo")){
					
					
					String uriString = "http://www.bestbuy.com/site/Brands/Lenovo/pcmcat230600050000.c?id=pcmcat230600050000&pageType=REDIRECT&issolr=1&searchterm=Lenovo";
					
					Uri uricomputers = Uri.parse(uriString);
					Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
					startActivity(intentcomputers);
					
				}
			
			 else if (selectedItem.contains("Toshiba")){
					
					
					String uriString = "http://www.bestbuy.com/site/Brands/Toshiba/pcmcat136800050058.c?id=pcmcat136800050058&pageType=REDIRECT&issolr=1&searchterm=Toshiba";
					
					Uri uricomputers = Uri.parse(uriString);
					Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
					startActivity(intentcomputers);
					
				}
			
					
			else if (selectedItem.contains("Papa Johns")){
			
				
				
				String uriString = "http://www.papajohns.com/index.html";
				
				Uri uridining = Uri.parse(uriString);
				Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
				startActivity(intentdining);
			}
			
			else if (selectedItem.contains("BJs Restaurants")){
				
				
				
				
				
				String uriString = "http://www.bjsrestaurants.com/";
				
				Uri uridining = Uri.parse(uriString);
				Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
				startActivity(intentdining);
			}
			
			else if (selectedItem.contains("Dennys")){
				
				
				String uriString = "http://www.dennys.com/";
				
				Uri uridining = Uri.parse(uriString);
				Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
				startActivity(intentdining);
			}
			
			else if (selectedItem.contains("California Sushi Roll")){
				

				
				String uriString = "http://www.california-sushi-roll.com/";
				
				Uri uridining = Uri.parse(uriString);
				Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
				startActivity(intentdining);
			}
			
			else if (selectedItem.contains("Taco Bell")){
				
				
				String uriString = "www.tacobell.com/";
				
				Uri uridining = Uri.parse(uriString);
				Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
				startActivity(intentdining);
			}
			
			else if (selectedItem.contains("The Cheesecake Factory")){
				
			
				
				String uriString = "http://www.thecheesecakefactory.com/";
				
				Uri uridining = Uri.parse(uriString);
				Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
				startActivity(intentdining);
			}
			
			else if (selectedItem.contains("Pizza Hut")){
				
				
				
				String uriString = "http://www.pizzahut.com/";
				
				Uri uridining = Uri.parse(uriString);
				Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
				startActivity(intentdining);
			}
			
			else if (selectedItem.contains("Carls Jr")){
				
				
				
				String uriString = "http://www.carlsjr.com/";
				
				Uri uridining = Uri.parse(uriString);
				Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
				startActivity(intentdining);
			}
			
			else if (selectedItem.contains("Romance") && parentItem.contains("eBooks")){
				
				
				String uriString = "http://www.ebooks.com/subjects/Romance/";
				
				Uri uriebooks = Uri.parse(uriString);
				Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
				startActivity(intentebooks);
			}
			
			
			else if (selectedItem.contains("Science Fiction") && parentItem.contains("eBooks")){
								
				
				
								String uriString = "http://www.ebooks.com/subjects/Science-Fiction/";
								
								Uri uriebooks = Uri.parse(uriString);
								Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
								startActivity(intentebooks);
							}
							
			else if (selectedItem.contains("Technology") && parentItem.contains("eBooks")){
				
				
				String uriString = "http://www.ebooks.com/subjects/Technology/";
				
				Uri uriebooks = Uri.parse(uriString);
				Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
				startActivity(intentebooks);
			}
							
			else if (selectedItem.contains("Business") && parentItem.contains("eBooks")){
				
				
				String uriString = "http://www.ebooks.com/subjects/Business/";
				
				Uri uriebooks = Uri.parse(uriString);
				Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
				startActivity(intentebooks);
			}
							
			else if (selectedItem.contains("Health") && parentItem.contains("eBooks")){
				
				
				String uriString = "http://www.ebooks.com/subjects/Health/";
				
				Uri uriebooks = Uri.parse(uriString);
				Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
				startActivity(intentebooks);
			}
							
			else if (selectedItem.contains("History") && parentItem.contains("eBooks")){
				
				
				String uriString = "http://www.ebooks.com/subjects/History/";
				
				Uri uriebooks = Uri.parse(uriString);
				Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
				startActivity(intentebooks);
			}
							
			else if (selectedItem.contains("Mystery") && parentItem.contains("eBooks")){
				
				
				String uriString = "http://www.ebooks.com/subjects/Mystery/";
				
				Uri uriebooks = Uri.parse(uriString);
				Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
				startActivity(intentebooks);
			}
							
			else if (selectedItem.contains("Childrens") && parentItem.contains("eBooks")){
				
				
				String uriString = "http://www.ebooks.com/subjects/Childrens-young-adult-fiction/";
				
				Uri uriebooks = Uri.parse(uriString);
				Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
				startActivity(intentebooks);
			}
			
			
			
			else if (selectedItem.contains("Science Fiction") && parentItem.contains("Movies")){
				
				
				
				String uriString = "https://www.amctheatres.com/";
				
				Uri urimovies = Uri.parse(uriString);
				Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
				startActivity(intentmovies);
			}
			
			else if (selectedItem.contains("Mystery") && parentItem.contains("Movies")){
								
								
								
								String uriString = "https://www.amctheatres.com/";
								
								Uri urimovies = Uri.parse(uriString);
								Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
								startActivity(intentmovies);
							}
							
			else if (selectedItem.contains("Horror") && parentItem.contains("Movies")){
				
				
				
				String uriString = "https://www.amctheatres.com/";
				
				Uri urimovies = Uri.parse(uriString);
				Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
				startActivity(intentmovies);
			}
							
			else if (selectedItem.contains("Comedy") && parentItem.contains("Movies")){
				
				
				
				String uriString = "https://www.amctheatres.com/";
				
				Uri urimovies = Uri.parse(uriString);
				Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
				startActivity(intentmovies);
			}
							
			else if (selectedItem.contains("Romantic") && parentItem.contains("Movies")){
				
				
				
				String uriString = "https://www.amctheatres.com/";
				
				Uri urimovies = Uri.parse(uriString);
				Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
				startActivity(intentmovies);
			}
							
			else if (selectedItem.contains("Children's") && parentItem.contains("Movies")){
				
				
				
				String uriString ="https://www.amctheatres.com/";
				
				Uri urimovies = Uri.parse(uriString);
				Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
				startActivity(intentmovies);
			}
							
			else if (selectedItem.contains("Family") && parentItem.contains("Movies")){
				
				
				
				String uriString = "http://www.amctheatres.com/";
				
				Uri urimovies = Uri.parse(uriString);
				Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
				startActivity(intentmovies);
			}
							
			else if (selectedItem.contains("Action") && parentItem.contains("Movies")){
				
				
				
				String uriString = "https://www.amctheatres.com/";
				
				Uri urimovies = Uri.parse(uriString);
				Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
				startActivity(intentmovies);
			}
			
			
			
			else if (selectedItem.contains("Cat")){
			
				
				
				String uriString = "http://www.petco.com/Cat-Home.aspx";
				
				Uri uripets = Uri.parse(uriString);
				Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
				startActivity(intentpets);
			}
			
			else if (selectedItem.contains("Dog")){
							
								
								
								String uriString = "http://www.petco.com/Dog-Home.aspx";
								
								Uri uripets = Uri.parse(uriString);
								Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
								startActivity(intentpets);
							}
							
			else if (selectedItem.contains("Bird")){
				
				
				
				String uriString = "http://www.petco.com/Bird-Home.aspx";
				
				Uri uripets = Uri.parse(uriString);
				Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
				startActivity(intentpets);
			}
							
			else if (selectedItem.contains("Fish")){
				
				
				String uriString = "http://www.petco.com/Fish-Home.aspx";
				
				Uri uripets = Uri.parse(uriString);
				Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
				startActivity(intentpets);
			}
							
			else if (selectedItem.contains("Rabbits")){
				
				
				
				String uriString = "http://www.petco.com/Rabbits-Home.aspx?CoreSearch=Rabbit";
				
				Uri uripets = Uri.parse(uriString);
				Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
				startActivity(intentpets);
			}
							
			else if (selectedItem.contains("Hamsters")){
				
				
				
				String uriString = "http://www.petco.com/HamstersHome.aspx?CoreSearch=Hamsters";
				
				Uri uripets = Uri.parse(uriString);
				Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
				startActivity(intentpets);
			}
							
			else if (selectedItem.contains("Turtles")){
				
				
				
				String uriString = "http://www.petco.com/Turtles-Home.aspx?CoreSearch=Turtles";
				
				Uri uripets = Uri.parse(uriString);
				Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
				startActivity(intentpets);
			}
							
			else if (selectedItem.contains("Snakes")){
				
				
				
				String uriString = "http://www.petco.com/Snakes-Home.aspx?CoreSearch=Snakes";
				
				Uri uripets = Uri.parse(uriString);
				Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
				startActivity(intentpets);
			}
			
			else if (selectedItem.contains("Universal Studios")){
				
				
				String uriString = "http://www.universalstudios.com/";
				
				Uri uritravel = Uri.parse(uriString);
				Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
				startActivity(intenttravel);
			}
			
			else if (selectedItem.contains("Magic Mountain")){
								
				
				
				String uriString = "https://www.sixflags.com";
				
				Uri uritravel = Uri.parse(uriString);
				Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
				startActivity(intenttravel);
			}
							
			else if (selectedItem.contains("Sea World")){
				
				
				
				String uriString = "https://www.seaworld.com/";
				
				Uri uritravel = Uri.parse(uriString);
				Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
				startActivity(intenttravel);
			}
							
			else if (selectedItem.contains("Legoland")){
				
				
				
				String uriString = "https://www.legoland.com/";
				
				Uri uritravel = Uri.parse(uriString);
				Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
				startActivity(intenttravel);
			}
							
			else if (selectedItem.contains("Disneyland")){
				
				
				
				String uriString = "https://www.disneyland.com/";
				
				Uri uritravel = Uri.parse(uriString);
				Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
				startActivity(intenttravel);
			}
							
			else if (selectedItem.contains("San Diego Wild Animal Park")){
				
				
				String uriString = "http://www.sdzsafaripark.org/";
				
				Uri uritravel = Uri.parse(uriString);
				Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
				startActivity(intenttravel);
			}
							
			else if (selectedItem.contains("Disney California")){
				
				
				
				String uriString = "http://disneyland.disney.go.com/destinations/disney-california-adventure/";
				
				Uri uritravel = Uri.parse(uriString);
				Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
				startActivity(intenttravel);
			}
							
			else if (selectedItem.contains("Hurricane Harbor")){
				
				
				
				String uriString = "https://www.sixflags.com/hurricaneharborla";
				
				Uri uritravel = Uri.parse(uriString);
				Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
				startActivity(intenttravel);
			}
			
			else if (selectedItem.contains("Playstation 4")){
				
									
				String uriString = "http://www.bestbuy.com/site/Video-Games/PlayStation-4-PS4/pcmcat295700050012.c?id=pcmcat295700050012&pageType=REDIRECT&issolr=1&searchterm=Playstation%204";
				
				Uri urivideogames = Uri.parse(uriString);
				Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
				startActivity(intentvideogames);
			}
			
			else if (selectedItem.contains("XBOX")){
				
				
				String uriString = "http://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=15&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&fs=saas&saas=saas&keys=keys&st=XBOX";
				
				Uri urivideogames = Uri.parse(uriString);
				Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
				startActivity(intentvideogames);
			}
			
			else if (selectedItem.contains("Nintendo Wii U")){
				
				
				String uriString = "http://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=15&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&fs=saas&saas=saas&keys=keys&st=Nintendo+Wii+U";
				
				Uri urivideogames = Uri.parse(uriString);
				Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
				startActivity(intentvideogames);
			}
			
			else if (selectedItem.contains("Playstation 3")){
				
				
				String uriString = "http://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=15&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&fs=saas&saas=saas&keys=keys&st=Playstation+3";
				
				Uri urivideogames = Uri.parse(uriString);
				Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
				startActivity(intentvideogames);
			}
			
			else if (selectedItem.contains("Nintendo Wii")){
				
				
				String uriString = "http://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=15&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&fs=saas&saas=saas&keys=keys&st=Nintendo+Wii";
				
				Uri urivideogames = Uri.parse(uriString);
				Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
				startActivity(intentvideogames);
			}
			
			else if (selectedItem.contains("Android")){
				
				
				String uriString = "https://play.google.com/store";
				
				Uri urivideogames = Uri.parse(uriString);
				Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
				startActivity(intentvideogames);
			}
			
			else if (selectedItem.contains("iPhone")){
				
				
				String uriString = "http://store.apple.com/us";
				
				Uri urivideogames = Uri.parse(uriString);
				Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
				startActivity(intentvideogames);
			}
			
			else if (selectedItem.contains("PC")){
				
				
				String uriString = "http://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=15&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&fs=saas&saas=saas&keys=keys&st=PC+games";
				
				Uri urivideogames = Uri.parse(uriString);
				Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
				startActivity(intentvideogames);
			}
			
			else{
				
			}
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> av) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
				
	   Integer d3 = Shop.dImage3;
	    
	    if (d3 == R.drawable.clothes){
			
			s3adapter = ArrayAdapter.createFromResource(this, R.array.Clothes, android.R.layout.simple_spinner_item);
			
			s3adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin3.setSelection(0);
			
			spin3.setAdapter(s3adapter);
		}
		else if (d3 == R.drawable.computer){
			
			
			s3adapter = ArrayAdapter.createFromResource(this, R.array.Computers, android.R.layout.simple_spinner_item);
			
			s3adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin3.setSelection(0);
			
			spin3.setAdapter(s3adapter);
		}
		else if (d3 == R.drawable.dining){
			
			
			s3adapter = ArrayAdapter.createFromResource(this, R.array.Dining, android.R.layout.simple_spinner_item);
			
			s3adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin3.setSelection(0);
			
			spin3.setAdapter(s3adapter);
		}
		else if (d3 == R.drawable.ebook){
			
			
			s3adapter = ArrayAdapter.createFromResource(this, R.array.eBooks, android.R.layout.simple_spinner_item);
			
			s3adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin3.setSelection(0);
			
			spin3.setAdapter(s3adapter);
		}
		else if (d3 == R.drawable.movies){
			
			
			s3adapter = ArrayAdapter.createFromResource(this, R.array.Movies, android.R.layout.simple_spinner_item);
			
			s3adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin3.setSelection(0);
			
			spin3.setAdapter(s3adapter);
		}
		else if (d3 == R.drawable.pets){
			
			
			s3adapter = ArrayAdapter.createFromResource(this, R.array.Pets, android.R.layout.simple_spinner_item);
			
			s3adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin3.setSelection(0);
			
			spin3.setAdapter(s3adapter);
		}
		else if (d3 == R.drawable.travel){
			
			
			s3adapter = ArrayAdapter.createFromResource(this, R.array.Travel, android.R.layout.simple_spinner_item);
			
			s3adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin3.setSelection(0);
			
			spin3.setAdapter(s3adapter);
		}
		else if (d3 == R.drawable.video_games){
			
			s3adapter = ArrayAdapter.createFromResource(this, R.array.VideoGames, android.R.layout.simple_spinner_item);
			
			s3adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin3.setSelection(0);
			
			spin3.setAdapter(s3adapter);		
		}
		else{
			
						
			s3adapter = ArrayAdapter.createFromResource(this, R.array.Empty, android.R.layout.simple_spinner_item);
			
			s3adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spin3.setSelection(0);
			
			spin3.setAdapter(s3adapter);
			
		}
		
		

		
		
		spin3.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> av, View v,
					int pos, long posID) {
				// TODO Auto-generated method stub
				
				
				String selectedItem = spin3.getSelectedItem().toString();
				String parentItem = spin3.getAdapter().getItem(0).toString();
				
			if (selectedItem.contains("Levis")){
				selectedItem.toLowerCase(Locale.US);
														
				String uriString = "http://www.macys.com/m/campaign/levis/levis?cm_mmc=VanityUrl-_-levis-_-n-_-n";
				
				Uri uriclothes = Uri.parse(uriString);
				Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
				startActivity(intentclothes);
			}
			
			else if (selectedItem.contains("Dockers")){
				
				
				String uriString = "http://www1.macys.com/shop/search?keyword=dockers#cm_pv=slp";
				
				Uri uriclothes = Uri.parse(uriString);
				Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
				startActivity(intentclothes);
			}
			
			else if (selectedItem.contains("Calvin Klein")){
				
				
				String uriString = "http://www1.macys.com/shop/search?keyword=calvin+klein#cm_pv=slp";
				
				Uri uriclothes = Uri.parse(uriString);
				Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
				startActivity(intentclothes);
			}
			
			else if (selectedItem.contains("Guess")){
				
				
				String uriString = "http://www1.macys.com/shop/search?keyword=guess#cm_pv=slp";
				
				Uri uriclothes = Uri.parse(uriString);
				Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
				startActivity(intentclothes);
			}
			
			else if (selectedItem.contains("Lucky Brand")){
				
				
				String uriString = "http://www1.macys.com/shop/search?keyword=lucky+brand#cm_pv=slp";
				
				Uri uriclothes = Uri.parse(uriString);
				Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
				startActivity(intentclothes);
			}
			
			else if (selectedItem.contains("Nike")){
				
				
				String uriString = "http://www1.macys.com/shop/search?keyword=nike#cm_pv=slp";
				
				Uri uriclothes = Uri.parse(uriString);
				Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
				startActivity(intentclothes);
			}
			
			else if (selectedItem.contains("Carter")){
				
				
				String uriString = "http://www1.macys.com/shop/search?keyword=carter%27s#cm_pv=slp";
				
				Uri uriclothes = Uri.parse(uriString);
				Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
				startActivity(intentclothes);
			}
			
			else if (selectedItem.contains("Tommy Hilfiger")){
				
				
				
				String uriString = "http://www1.macys.com/shop/search?keyword=tommy+hilfiger#cm_pv=slp";
				
				Uri uriclothes = Uri.parse(uriString);
				Intent intentclothes = new Intent(Intent.ACTION_VIEW, uriclothes);
				startActivity(intentclothes);
			}
			
					
			else if (selectedItem.contains("Sony")){
				
				
				String uriString = "http://www.bestbuy.com/site/Brands/Sony-Store/cat15063.c?id=cat15063&pageType=REDIRECT&issolr=1&searchterm=Sony";
				
				Uri uricomputers = Uri.parse(uriString);
				Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
				startActivity(intentcomputers);
				
			}
			 
			 else if (selectedItem.contains("MacBook Air")){
				 
					String uriString = "http://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=15&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&fs=saas&saas=saas&keys=keys&st=MacBook+Air";
					
					Uri uricomputers = Uri.parse(uriString);
					Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
					startActivity(intentcomputers);
					
				}
			 
			 else if (selectedItem.contains("Apple")){
					
					
					String uriString = "http://www.bestbuy.com/site/Brands/Apple/pcmcat128500050005.c?id=pcmcat128500050005&pageType=REDIRECT&issolr=1&searchterm=Apple";
					
					Uri uricomputers = Uri.parse(uriString);
					Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
					startActivity(intentcomputers);
					
				}
			 
			 else if (selectedItem.contains("Samsung")){
					
					
					String uriString = "http://www.bestbuy.com/site/Brands/Samsung/pcmcat140800050115.c?id=pcmcat140800050115&pageType=REDIRECT&issolr=1&searchterm=Samsung";
					Uri uricomputers = Uri.parse(uriString);
					Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
					startActivity(intentcomputers);
					
				}
			 
			 else if (selectedItem.contains("Acer")){
					
					
					String uriString = "http://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=15&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&fs=saas&saas=saas&keys=keys&st=Acer";
					
					Uri uricomputers = Uri.parse(uriString);
					Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
					startActivity(intentcomputers);
					
				}
			 
			 else if (selectedItem.contains("Asus")){
					
					
					String uriString = "http://www.bestbuy.com/site/Brands/Asus/pcmcat190000050006.c?id=pcmcat190000050006&pageType=REDIRECT&issolr=1&searchterm=Asus";
					
					Uri uricomputers = Uri.parse(uriString);
					Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
					startActivity(intentcomputers);
					
				}
			
			 else if (selectedItem.contains("Lenovo")){
					
					
					String uriString = "http://www.bestbuy.com/site/Brands/Lenovo/pcmcat230600050000.c?id=pcmcat230600050000&pageType=REDIRECT&issolr=1&searchterm=Lenovo";
					
					Uri uricomputers = Uri.parse(uriString);
					Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
					startActivity(intentcomputers);
					
				}
			
			 else if (selectedItem.contains("Toshiba")){
					
					
					String uriString = "http://www.bestbuy.com/site/Brands/Toshiba/pcmcat136800050058.c?id=pcmcat136800050058&pageType=REDIRECT&issolr=1&searchterm=Toshiba";
					
					Uri uricomputers = Uri.parse(uriString);
					Intent intentcomputers = new Intent(Intent.ACTION_VIEW, uricomputers);
					startActivity(intentcomputers);
					
				}
			
					
			else if (selectedItem.contains("Papa Johns")){
			
				
				
				String uriString = "http://www.papajohns.com/index.html";
				
				Uri uridining = Uri.parse(uriString);
				Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
				startActivity(intentdining);
			}
			
			else if (selectedItem.contains("BJs Restaurants")){
				
				
				
				
				
				String uriString = "http://www.bjsrestaurants.com/";
				
				Uri uridining = Uri.parse(uriString);
				Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
				startActivity(intentdining);
			}
			
			else if (selectedItem.contains("Dennys")){
				
				
				String uriString = "http://www.dennys.com/";
				
				Uri uridining = Uri.parse(uriString);
				Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
				startActivity(intentdining);
			}
			
			else if (selectedItem.contains("California Sushi Roll")){
				

				
				String uriString = "http://www.california-sushi-roll.com/";
				
				Uri uridining = Uri.parse(uriString);
				Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
				startActivity(intentdining);
			}
			
			else if (selectedItem.contains("Taco Bell")){
				
				
				String uriString = "www.tacobell.com/";
				
				Uri uridining = Uri.parse(uriString);
				Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
				startActivity(intentdining);
			}
			
			else if (selectedItem.contains("The Cheesecake Factory")){
				
			
				
				String uriString = "http://www.thecheesecakefactory.com/";
				
				Uri uridining = Uri.parse(uriString);
				Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
				startActivity(intentdining);
			}
			
			else if (selectedItem.contains("Pizza Hut")){
				
				
				
				String uriString = "http://www.pizzahut.com/";
				
				Uri uridining = Uri.parse(uriString);
				Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
				startActivity(intentdining);
			}
			
			else if (selectedItem.contains("Carls Jr")){
				
				
				
				String uriString = "http://www.carlsjr.com/";
				
				Uri uridining = Uri.parse(uriString);
				Intent intentdining = new Intent(Intent.ACTION_VIEW, uridining);
				startActivity(intentdining);
			}
			
			else if (selectedItem.contains("Romance") && parentItem.contains("eBooks")){
				
				
				String uriString = "http://www.ebooks.com/subjects/Romance/";
				
				Uri uriebooks = Uri.parse(uriString);
				Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
				startActivity(intentebooks);
			}
			
			
			else if (selectedItem.contains("Science Fiction") && parentItem.contains("eBooks")){
								
				
				
								String uriString = "http://www.ebooks.com/subjects/Science-Fiction/";
								
								Uri uriebooks = Uri.parse(uriString);
								Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
								startActivity(intentebooks);
							}
							
			else if (selectedItem.contains("Technology") && parentItem.contains("eBooks")){
				
				
				String uriString = "http://www.ebooks.com/subjects/Technology/";
				
				Uri uriebooks = Uri.parse(uriString);
				Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
				startActivity(intentebooks);
			}
							
			else if (selectedItem.contains("Business") && parentItem.contains("eBooks")){
				
				
				String uriString = "http://www.ebooks.com/subjects/Business/";
				
				Uri uriebooks = Uri.parse(uriString);
				Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
				startActivity(intentebooks);
			}
							
			else if (selectedItem.contains("Health") && parentItem.contains("eBooks")){
				
				
				String uriString = "http://www.ebooks.com/subjects/Health/";
				
				Uri uriebooks = Uri.parse(uriString);
				Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
				startActivity(intentebooks);
			}
							
			else if (selectedItem.contains("History") && parentItem.contains("eBooks")){
				
				
				String uriString = "http://www.ebooks.com/subjects/History/";
				
				Uri uriebooks = Uri.parse(uriString);
				Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
				startActivity(intentebooks);
			}
							
			else if (selectedItem.contains("Mystery") && parentItem.contains("eBooks")){
				
				
				String uriString = "http://www.ebooks.com/subjects/Mystery/";
				
				Uri uriebooks = Uri.parse(uriString);
				Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
				startActivity(intentebooks);
			}
							
			else if (selectedItem.contains("Childrens") && parentItem.contains("eBooks")){
				
				
				String uriString = "http://www.ebooks.com/subjects/Childrens-young-adult-fiction/";
				
				Uri uriebooks = Uri.parse(uriString);
				Intent intentebooks = new Intent(Intent.ACTION_VIEW, uriebooks);
				startActivity(intentebooks);
			}
			
			
			
			else if (selectedItem.contains("Science Fiction") && parentItem.contains("Movies")){
				
				
				
				String uriString = "https://www.amctheatres.com/";
				
				Uri urimovies = Uri.parse(uriString);
				Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
				startActivity(intentmovies);
			}
			
			else if (selectedItem.contains("Mystery") && parentItem.contains("Movies")){
								
								
								
								String uriString = "https://www.amctheatres.com/";
								
								Uri urimovies = Uri.parse(uriString);
								Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
								startActivity(intentmovies);
							}
							
			else if (selectedItem.contains("Horror") && parentItem.contains("Movies")){
				
				
				
				String uriString = "https://www.amctheatres.com/";
				
				Uri urimovies = Uri.parse(uriString);
				Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
				startActivity(intentmovies);
			}
							
			else if (selectedItem.contains("Comedy") && parentItem.contains("Movies")){
				
				
				
				String uriString = "https://www.amctheatres.com/";
				
				Uri urimovies = Uri.parse(uriString);
				Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
				startActivity(intentmovies);
			}
							
			else if (selectedItem.contains("Romantic") && parentItem.contains("Movies")){
				
				
				
				String uriString = "https://www.amctheatres.com/";
				
				Uri urimovies = Uri.parse(uriString);
				Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
				startActivity(intentmovies);
			}
							
			else if (selectedItem.contains("Children's") && parentItem.contains("Movies")){
				
				
				
				String uriString ="https://www.amctheatres.com/";
				
				Uri urimovies = Uri.parse(uriString);
				Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
				startActivity(intentmovies);
			}
							
			else if (selectedItem.contains("Family") && parentItem.contains("Movies")){
				
				
				
				String uriString = "http://www.amctheatres.com/";
				
				Uri urimovies = Uri.parse(uriString);
				Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
				startActivity(intentmovies);
			}
							
			else if (selectedItem.contains("Action") && parentItem.contains("Movies")){
				
				
				
				String uriString = "https://www.amctheatres.com/";
				
				Uri urimovies = Uri.parse(uriString);
				Intent intentmovies = new Intent(Intent.ACTION_VIEW, urimovies);
				startActivity(intentmovies);
			}
			
			
			
			else if (selectedItem.contains("Cat")){
			
				
				
				String uriString = "http://www.petco.com/Cat-Home.aspx";
				
				Uri uripets = Uri.parse(uriString);
				Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
				startActivity(intentpets);
			}
			
			else if (selectedItem.contains("Dog")){
							
								
								
								String uriString = "http://www.petco.com/Dog-Home.aspx";
								
								Uri uripets = Uri.parse(uriString);
								Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
								startActivity(intentpets);
							}
							
			else if (selectedItem.contains("Bird")){
				
				
				
				String uriString = "http://www.petco.com/Bird-Home.aspx";
				
				Uri uripets = Uri.parse(uriString);
				Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
				startActivity(intentpets);
			}
							
			else if (selectedItem.contains("Fish")){
				
				
				String uriString = "http://www.petco.com/Fish-Home.aspx";
				
				Uri uripets = Uri.parse(uriString);
				Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
				startActivity(intentpets);
			}
							
			else if (selectedItem.contains("Rabbits")){
				
				
				
				String uriString = "http://www.petco.com/Rabbits-Home.aspx?CoreSearch=Rabbit";
				
				Uri uripets = Uri.parse(uriString);
				Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
				startActivity(intentpets);
			}
							
			else if (selectedItem.contains("Hamsters")){
				
				
				
				String uriString = "http://www.petco.com/HamstersHome.aspx?CoreSearch=Hamsters";
				
				Uri uripets = Uri.parse(uriString);
				Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
				startActivity(intentpets);
			}
							
			else if (selectedItem.contains("Turtles")){
				
				
				
				String uriString = "http://www.petco.com/Turtles-Home.aspx?CoreSearch=Turtles";
				
				Uri uripets = Uri.parse(uriString);
				Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
				startActivity(intentpets);
			}
							
			else if (selectedItem.contains("Snakes")){
				
				
				
				String uriString = "http://www.petco.com/Snakes-Home.aspx?CoreSearch=Snakes";
				
				Uri uripets = Uri.parse(uriString);
				Intent intentpets = new Intent(Intent.ACTION_VIEW, uripets);
				startActivity(intentpets);
			}
			
			else if (selectedItem.contains("Universal Studios")){
				
				
				String uriString = "http://www.universalstudios.com/";
				
				Uri uritravel = Uri.parse(uriString);
				Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
				startActivity(intenttravel);
			}
			
			else if (selectedItem.contains("Magic Mountain")){
								
				
				
				String uriString = "https://www.sixflags.com";
				
				Uri uritravel = Uri.parse(uriString);
				Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
				startActivity(intenttravel);
			}
							
			else if (selectedItem.contains("Sea World")){
				
				
				
				String uriString = "https://www.seaworld.com/";
				
				Uri uritravel = Uri.parse(uriString);
				Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
				startActivity(intenttravel);
			}
							
			else if (selectedItem.contains("Legoland")){
				
				
				
				String uriString = "https://www.legoland.com/";
				
				Uri uritravel = Uri.parse(uriString);
				Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
				startActivity(intenttravel);
			}
							
			else if (selectedItem.contains("Disneyland")){
				
				
				
				String uriString = "https://www.disneyland.com/";
				
				Uri uritravel = Uri.parse(uriString);
				Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
				startActivity(intenttravel);
			}
							
			else if (selectedItem.contains("San Diego Wild Animal Park")){
				
				
				String uriString = "http://www.sdzsafaripark.org/";
				
				Uri uritravel = Uri.parse(uriString);
				Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
				startActivity(intenttravel);
			}
							
			else if (selectedItem.contains("Disney California")){
				
				
				
				String uriString = "http://disneyland.disney.go.com/destinations/disney-california-adventure/";
				
				Uri uritravel = Uri.parse(uriString);
				Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
				startActivity(intenttravel);
			}
							
			else if (selectedItem.contains("Hurricane Harbor")){
				
				
				
				String uriString = "https://www.sixflags.com/hurricaneharborla";
				
				Uri uritravel = Uri.parse(uriString);
				Intent intenttravel = new Intent(Intent.ACTION_VIEW, uritravel);
				startActivity(intenttravel);
			}
			
			else if (selectedItem.contains("Playstation 4")){
				
									
				String uriString = "http://www.bestbuy.com/site/Video-Games/PlayStation-4-PS4/pcmcat295700050012.c?id=pcmcat295700050012&pageType=REDIRECT&issolr=1&searchterm=Playstation%204";
				
				Uri urivideogames = Uri.parse(uriString);
				Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
				startActivity(intentvideogames);
			}
			
			else if (selectedItem.contains("XBOX")){
				
				
				String uriString = "http://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=15&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&fs=saas&saas=saas&keys=keys&st=XBOX";
				
				Uri urivideogames = Uri.parse(uriString);
				Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
				startActivity(intentvideogames);
			}
			
			else if (selectedItem.contains("Nintendo Wii U")){
				
				
				String uriString = "http://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=15&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&fs=saas&saas=saas&keys=keys&st=Nintendo+Wii+U";
				
				Uri urivideogames = Uri.parse(uriString);
				Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
				startActivity(intentvideogames);
			}
			
			else if (selectedItem.contains("Playstation 3")){
				
				
				String uriString = "http://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=15&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&fs=saas&saas=saas&keys=keys&st=Playstation+3";
				
				Uri urivideogames = Uri.parse(uriString);
				Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
				startActivity(intentvideogames);
			}
			
			else if (selectedItem.contains("Nintendo Wii")){
				
				
				String uriString = "http://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=15&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&fs=saas&saas=saas&keys=keys&st=Nintendo+Wii";
				
				Uri urivideogames = Uri.parse(uriString);
				Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
				startActivity(intentvideogames);
			}
			
			else if (selectedItem.contains("Android")){
				
				
				String uriString = "https://play.google.com/store";
				
				Uri urivideogames = Uri.parse(uriString);
				Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
				startActivity(intentvideogames);
			}
			
			else if (selectedItem.contains("iPhone")){
				
				
				String uriString = "http://store.apple.com/us";
				
				Uri urivideogames = Uri.parse(uriString);
				Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
				startActivity(intentvideogames);
			}
			
			else if (selectedItem.contains("PC")){
				
				
				String uriString = "http://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&_dynSessConf=&id=pcat17071&type=page&sc=Global&cp=1&nrp=15&sp=&qp=&list=n&iht=y&usc=All+Categories&ks=960&fs=saas&saas=saas&keys=keys&st=PC+games";
				
				Uri urivideogames = Uri.parse(uriString);
				Intent intentvideogames = new Intent(Intent.ACTION_VIEW, urivideogames);
				startActivity(intentvideogames);
			}
			
			else{
				
			}
							
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> av) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
	}
	
	public class myGestureListener extends GestureDetector.SimpleOnGestureListener{
		
		
		
	//	private String[] data = new String[5];





		public myGestureListener(){
		//	this.data  = values;
		}
		
		@Override
		public boolean onDown(MotionEvent e){
			return true;
		}
		
		@Override
		public boolean onSingleTapUp(MotionEvent e){
			// read first name and last name from EditText and then add as Tab label
			
			
			
			
			
			
			return true;
		}
		
		

		@Override
		public boolean onDoubleTap(MotionEvent e){
			
			str1 = (String) spin1.getSelectedItem();
			str2 = (String)spin2.getSelectedItem();
			str3 = (String)spin3.getSelectedItem();
			
				
				
			return true;
			
		}
	}

	
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	        NavUtils.navigateUpFromSameTask(this);
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wish_list, menu);
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent e){
		this.mDetector.onTouchEvent(e);
		return super.onTouchEvent(e);
		
	}
	
	public String getName(){
		
		String first = fName.getText().toString();
		String last = lName.getText().toString();
			
		name = first + " " + last;
		
		return name;
	}



}
