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
						getResources().getString(R.string.passage_a1),
						getResources().getString(R.string.passage_b1), 
						Integer.parseInt(getResources().getString(R.string.passage_c1)), 
						Integer.parseInt(getResources().getString(R.string.passage_d1))
				);
				databaseConnector.insertScripture(
						getResources().getString(R.string.passage_a2),
						getResources().getString(R.string.passage_b2), 
						Integer.parseInt(getResources().getString(R.string.passage_c2)), 
						Integer.parseInt(getResources().getString(R.string.passage_d2))
				);
				databaseConnector.insertScripture(
						getResources().getString(R.string.passage_a3),
						getResources().getString(R.string.passage_b3), 
						Integer.parseInt(getResources().getString(R.string.passage_c3)), 
						Integer.parseInt(getResources().getString(R.string.passage_d3))
				);
				databaseConnector.insertScripture(
						getResources().getString(R.string.passage_a4),
						getResources().getString(R.string.passage_b4), 
						Integer.parseInt(getResources().getString(R.string.passage_c4)), 
						Integer.parseInt(getResources().getString(R.string.passage_d4))
				);								
  				databaseConnector.insertScripture(
						getResources().getString(R.string.passage_a5),
						getResources().getString(R.string.passage_b5), 
						Integer.parseInt(getResources().getString(R.string.passage_c5)), 
						Integer.parseInt(getResources().getString(R.string.passage_d5))
				);
				databaseConnector.insertScripture(
						getResources().getString(R.string.passage_a6),
						getResources().getString(R.string.passage_b6), 
						Integer.parseInt(getResources().getString(R.string.passage_c6)), 
						Integer.parseInt(getResources().getString(R.string.passage_d6))
				);
				databaseConnector.insertScripture(
						getResources().getString(R.string.passage_a7),
						getResources().getString(R.string.passage_b7), 
						Integer.parseInt(getResources().getString(R.string.passage_c7)), 
						Integer.parseInt(getResources().getString(R.string.passage_d7))
				);
				databaseConnector.insertScripture(
						getResources().getString(R.string.passage_a8),
						getResources().getString(R.string.passage_b8), 
						Integer.parseInt(getResources().getString(R.string.passage_c8)), 
						Integer.parseInt(getResources().getString(R.string.passage_d8))
				);					
				databaseConnector.insertScripture(
						getResources().getString(R.string.passage_a9),
						getResources().getString(R.string.passage_b9), 
						Integer.parseInt(getResources().getString(R.string.passage_c9)), 
						Integer.parseInt(getResources().getString(R.string.passage_d9))
				);								
  				databaseConnector.insertScripture(
						getResources().getString(R.string.passage_a10),
						getResources().getString(R.string.passage_b10), 
						Integer.parseInt(getResources().getString(R.string.passage_c10)), 
						Integer.parseInt(getResources().getString(R.string.passage_d10))
				);
				databaseConnector.insertScripture(
						getResources().getString(R.string.passage_a11),
						getResources().getString(R.string.passage_b11), 
						Integer.parseInt(getResources().getString(R.string.passage_c11)), 
						Integer.parseInt(getResources().getString(R.string.passage_d11))
				);
				databaseConnector.insertScripture(
						getResources().getString(R.string.passage_a12),
						getResources().getString(R.string.passage_b12), 
						Integer.parseInt(getResources().getString(R.string.passage_c12)), 
						Integer.parseInt(getResources().getString(R.string.passage_d12))
				);
				databaseConnector.insertScripture(
						getResources().getString(R.string.passage_a13),
						getResources().getString(R.string.passage_b13), 
						Integer.parseInt(getResources().getString(R.string.passage_c13)), 
						Integer.parseInt(getResources().getString(R.string.passage_d13))
				);					
	    }

	} // end of private void preInsertScripture(){
	
	
	
} // end of public class MainActivity extends Activity {
