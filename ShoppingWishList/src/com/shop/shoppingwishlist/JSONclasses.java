package com.shop.shoppingwishlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


public class JSONclasses{

static String e_mail = "";
static String name = "";
static String wish1 = "";
static String wish2 = "";
static String wish3 = "";
static String urlpic = "";
static String[] wishes = new String[30];
static String[] email = new String[10];
static String[] names = new String[10];
static String[] urlPic = new String[10];
static String dataJSON = "";
static Context context;
static String name_pick = "";


public static class readJSON extends AsyncTask<String, Void, String>{



@Override
protected String doInBackground(String... params) {


HttpClient client = new DefaultHttpClient();
try {
URI uriInFile = URI.create(params[0]);
HttpGet hGet = new HttpGet(uriInFile);
HttpResponse hResp = client.execute(hGet);
int responseCode = hResp.getStatusLine().getStatusCode();
Log.d("Shop", "responseCode: " + responseCode);

if (responseCode == 200){
HttpEntity e = hResp.getEntity();
dataJSON = EntityUtils.toString(e);
			
}
else{
RuntimeException runE = new RuntimeException("Error connecting with server");
runE.printStackTrace();
}


}


catch (ClientProtocolException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

return dataJSON;



}

@Override
protected void onPostExecute(String result) {

// retrieving full name from EditText and collapsing into one long string
final String etName = Shop.NAME.getText().toString();

int spaceInt = etName.indexOf(" ");
String FirstNameID = etName.substring(0, spaceInt);

String LastNameID = etName.substring(spaceInt + 1, etName.length());

final String FullNameID = FirstNameID.concat(LastNameID);

// passing on both Json string result and fullNameID to locate array

JSONObject jObj;
try {
jObj = new JSONObject(result);
JSONArray jArr = jObj.getJSONArray(FullNameID);


Toast.makeText(context, "Searching for " + etName.toString(), Toast.LENGTH_SHORT).show();



if(jArr.toString().equals(FullNameID)){

Toast.makeText(context, etName.toString() + " has been found.", Toast.LENGTH_SHORT).show();	


for (int j = 0; j < jArr.length(); j++){

JSONObject kObj = jArr.getJSONObject(j);

e_mail = kObj.getString("email");
email[j] = e_mail;

name = kObj.getString("name");

if (name.matches(FirstNameID.toString()))
{
	Shop.NAME.setText("Welcome " + name.toString());
	
}

names[j] = name;


wish1 = kObj.getString("wish1");
wishes[j] = wish1;

wish2 = kObj.getString("wish2");
wishes[j + 1] = wish2;

wish3 = kObj.getString("wish3");
wishes[j + 2] = wish3;

urlpic = kObj.getString("urlPic");
urlPic[j] = urlpic;
}
}
else{

Toast.makeText(context, etName.toString() + " not found. Creating new list...", Toast.LENGTH_SHORT).show();	


writeJSON write_json = new writeJSON();

write_json.execute("http://www.mediaandroid.byethost17.com/wishjson.json", dataJSON);
}
} catch (JSONException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

		
}

}




}



class writeJSON extends AsyncTask<String, Void, String>{

InputStream in;
String json = "";
String result = "";

@Override
protected String doInBackground(String... params) {

HttpClient client = new DefaultHttpClient();
try {
URI uriOutFile = URI.create(params[0]);
HttpPost hPost = new HttpPost(uriOutFile);
JSONObject jo = new JSONObject(params[1]);
JSONArray jArr = new JSONArray();

	
jArr.put(jo.accumulate("name", ""));

jArr.put(jo.accumulate("DOB", ""));

jArr.put(jo.accumulate("urlPic", ""));

jArr.put(jo.accumulate("email", ""));

jArr.put(jo.accumulate("wish1", ""));

jArr.put(jo.accumulate("wish2", ""));

jArr.put(jo.accumulate("wish3", ""));


json = jArr.toString();
StringEntity se = new StringEntity(json);
hPost.setEntity(se);
hPost.setHeader("Accept","application/json");
hPost.setHeader("Content-type", "application/json");
HttpResponse hResponse = client.execute(hPost);
in = hResponse.getEntity().getContent();
BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(in));
String line = "";

while((line = bufferedReader.readLine()) != null)
result += line;

in.close();



} catch (ClientProtocolException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (JSONException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return result;
}

@Override
protected void onPostExecute(String result) {

super.onPostExecute(result);


}




}




