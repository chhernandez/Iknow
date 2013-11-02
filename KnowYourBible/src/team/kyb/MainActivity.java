package team.kyb;


import team.kyb.R;
import team.kyb.database.DatabaseConnector;
import team.kyb.database.Scriptures;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;

public class MainActivity extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// preinsert the scripture to the database using a separate thread
		preScriptureTask.execute((Object[]) null); 		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	// handle choice from options menu
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// create a new Intent to launch
		Intent listScriptures = 
				new Intent(this, Scriptures.class);
		startActivity(listScriptures); 
		return super.onOptionsItemSelected(item); 
	} 	
	
/*	@Override
	protected void onResume() {
		super.onResume(); 


	} */		
	
	
	
	AsyncTask<Object, Object, Object> preScriptureTask = 
			new AsyncTask<Object, Object, Object>() {

		@Override
		protected Object doInBackground(Object... params) { 

			preInsertScripture(); 
			return null;
		} 

		@Override
		protected void onPostExecute(Object result) { 
			//finish(); 
			//databaseConnector.close();
		} 
	}; 

	private void preInsertScripture(){
		
		//get DatabaseConnector to interact with the SQLite database
		DatabaseConnector databaseConnector = new DatabaseConnector(this);
		
		boolean dbExist = databaseConnector.checkDataBase();	
		
		if (dbExist){
	    	  // do nothing database already exists
				Log.d("dbExist", "True");	
	    } else {
	    	  Log.d("dbExist", "False");
	    	  
				databaseConnector.insertScripture(
						"Throw all your anxiety on him, because he cares for you.",
			    		  "1 Peter", 5, 7);	    	
				databaseConnector.insertScripture(
						"Happy are the pure in heart, since they will see God.",
			    		  "Matthew", 5, 8);	 
				databaseConnector.insertScripture(
						"In the beginning God created the heavens and the earth.",
			    		  "Genesis", 1, 1);	    	
				databaseConnector.insertScripture(
						"Serve Jehovah with rejoicing. Come in before him with a joyful cry.",
			    		  "Psalms", 100, 2);	
				databaseConnector.insertScripture(
						"As the last enemy, death is to be brought to nothing.",
			    		  "1 Corinthians", 15, 26);	 
				databaseConnector.insertScripture(
						"Always rejoice in [the] Lord. Once more I will say, Rejoice!",
			    		  "Philippians", 4, 4);	 
				databaseConnector.insertScripture(
						"Not many of you should become teachers, my brothers, knowing that we shall receive heavier judgment.",
			    		  "James", 3, 1);	 
				databaseConnector.insertScripture(
						"You were bought with a price; stop becoming slaves of men.",
			    		  "1 Corinthians", 7, 23);	
				databaseConnector.insertScripture(
						"So, then, let us not sleep on as to rest do, but let us stay awake and keep our senses.",
			    		  "1 Thessalonians", 5, 6);	 
				databaseConnector.insertScripture(
						"Even those troubled with unclean spirits were cured.",
			    		  "Luke", 6, 18);	 
				databaseConnector.insertScripture(
						"Well, then, have I become YOUR enemy because I tell YOU the truth?",
			    		  "Galatians", 4, 16);					
	    }

	} // end of private void preInsertScripture(){
	
	
	
} // end of public class MainActivity extends Activity {
