package com.shop.shoppingwishlist;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryAdapter extends BaseAdapter{

	Context context;
	List<Integer> categoriesPic;
    List<String> categoriesText;
	
	
	
	public CategoryAdapter(Context context) {
		
		categoriesPic = new ArrayList<Integer>();
		
		categoriesPic.add(0, R.drawable.clothes);
		categoriesPic.add(1, R.drawable.computer);
		categoriesPic.add(2, R.drawable.dining);
		categoriesPic.add(3, R.drawable.ebook);
		categoriesPic.add(4, R.drawable.movies);
		categoriesPic.add(5, R.drawable.pets);
		categoriesPic.add(6, R.drawable.travel);
		categoriesPic.add(7, R.drawable.video_games);
		categoriesPic.add(8, R.drawable.no_category);
		
		categoriesText = new ArrayList<String>();
		
		categoriesText.add(0, "Clothes");
		categoriesText.add(1, "Computers");
		categoriesText.add(2, "Dining");
		categoriesText.add(3, "eBooks");
		categoriesText.add(4, "Movies");
		categoriesText.add(5, "Pets");
		categoriesText.add(6, "Travel");
		categoriesText.add(7, "Video Games");
		categoriesText.add(8, "No Category");
		
		this.context = context;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return categoriesPic.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return categoriesPic.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		// Inflating and retrieving the gridview and its pictures
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View grid;	
	
		//grid = new View(context);
		
		//grid = inflater.inflate(R.layout.wish_layout, null);	
	
		if(convertView == null){
			
			grid = new View(context);
							
			grid = inflater.inflate(R.layout.wish_layout, null);
			
			categoriesPic = new ArrayList<Integer>();
			
			categoriesPic.add(R.drawable.clothes);
			categoriesPic.add(R.drawable.computer);
			categoriesPic.add(R.drawable.dining);
			categoriesPic.add(R.drawable.ebook);
			categoriesPic.add(R.drawable.movies);
			categoriesPic.add(R.drawable.pets);
			categoriesPic.add(R.drawable.travel);
			categoriesPic.add(R.drawable.video_games);
			categoriesPic.add(R.drawable.no_category);
			
			categoriesText = new ArrayList<String>();
			
			categoriesText.add(0, "Clothes");
			categoriesText.add(1, "Computers");
			categoriesText.add(2, "Dining");
			categoriesText.add(3, "eBooks");
			categoriesText.add(4, "Movies");
			categoriesText.add(5, "Pets");
			categoriesText.add(6, "Travel");
			categoriesText.add(7, "Video Games");
			categoriesText.add(8, "No Category");
			
			// inflating view and the corresponding textView and ImageView
			
			TextView tv = (TextView) grid.findViewById(R.id.electronicsText);
			tv.setText(categoriesText.get(position));
			
			 ImageView iv = (ImageView) grid.findViewById(R.id.electronicsPic);
			
			String categoryText = categoriesText.get(position);
			
			if (categoryText.equals("Clothes"))
				iv.setImageResource(categoriesPic.get(0));
			else if (categoryText.equals("Computers"))
				iv.setImageResource(categoriesPic.get(1));
			else if (categoryText.equals("Dining"))
				iv.setImageResource(categoriesPic.get(2));
			else if (categoryText.equals("eBooks"))
				iv.setImageResource(categoriesPic.get(3));
			else if (categoryText.equals("Movies"))
				iv.setImageResource(categoriesPic.get(4));
			else if (categoryText.equals("Pets"))
				iv.setImageResource(categoriesPic.get(5));
			else if (categoryText.equals("Travel"))
				iv.setImageResource(categoriesPic.get(6));
			else if (categoryText.equals("Video Games"))
				iv.setImageResource(categoriesPic.get(7));
			else
				iv.setImageResource(R.drawable.no_category);
			
			
			
		}
		
		else {
			grid = convertView;
			
		}
		
		return grid;
		
	}

}
