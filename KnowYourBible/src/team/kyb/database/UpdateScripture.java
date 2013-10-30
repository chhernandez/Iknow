package team.kyb.database;


import team.kyb.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class UpdateScripture extends Activity {

	private long rowID;
	private TextView passage;
	private TextView book;
	private TextView chapter;
	private TextView colon2;
	private TextView verse;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.scripture_update);	
		passage = (TextView) findViewById(R.id.passageTextView);
		book = (TextView) findViewById(R.id.bookTextView);		
		chapter = (TextView) findViewById(R.id.chapterTextView);
		colon2 = (TextView) findViewById(R.id.colon2);
		verse = (TextView) findViewById(R.id.verseTextView);	
		
		//get the selected scripture's row id
		Bundle extras = getIntent().getExtras();
		rowID = extras.getLong(Scriptures.ROW_ID);
		
	} // end of public void onCreate(Bundle savedInstanceState){
	
	//called when the activity is first created
	@Override
	protected void onResume(){
		super.onResume();
		
		//create new LoadScriptureTask and execute it
		new LoadScriptureTask().execute(rowID);
	}
	
	// performs database query outside GUI thread
	private class LoadScriptureTask extends AsyncTask<Long, Object, Cursor> {
		
		DatabaseConnector databaseConnector = new DatabaseConnector(UpdateScripture.this);
		
		// perform the database access
		@Override
		protected Cursor doInBackground(Long...params){
			databaseConnector.open();
			
			//get a cursor containing all data on given entry
			return databaseConnector.getOneScripture(params[0]);
			
		}
		
		// use the Cursor returned from the doInBackground method
		@Override
		protected void onPostExecute(Cursor result) {
			super.onPostExecute(result);

			result.moveToFirst();
			
			// get the column index for each data item
			int passageIndex = result.getColumnIndex("passage");
			int bookIndex = result.getColumnIndex("book");
			int chapterIndex = result.getColumnIndex("chapter");
			int verseIndex = result.getColumnIndex("verse");
			
			//fill TextViews with the retrieved data
			passage.setText(result.getString(passageIndex));
			book.setText(result.getString(bookIndex));
			chapter.setText(result.getString(chapterIndex));
			colon2.setText(":");
			verse.setText(result.getString(verseIndex));
			
			result.close();
			databaseConnector.close();
			
		}
		
	} // end of 	private class LoadScriptureTask extends AsyncTask<Long, Object, Cursor> {
	
	//create the menu for the update scripture
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		
		inflater.inflate(R.menu.update_menu, menu);
		/*inflater.inflate(R.menu.scriptures_menu, menu);	*/
		
		return true;
	}

	
	//handle choice from options menu
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case R.id.editscripture:
			// create an Intent to launch the AddEditScripture Activity
			Intent addEditScripture = new Intent(this, AddEditScripture.class);
			
			addEditScripture.putExtra(Scriptures.ROW_ID, rowID);
			addEditScripture.putExtra("passage", passage.getText());
			addEditScripture.putExtra("book", book.getText());
			addEditScripture.putExtra("chapter", chapter.getText());
			addEditScripture.putExtra("verse", verse.getText());
			
			Log.d("edit scripture", "row id:" + rowID);	
			Log.d("edit scripture", "passage:" + passage.getText());	
			Log.d("edit scripture", "book:" + book.getText());
			Log.d("edit scripture", "chapter:" + chapter.getText());
			Log.d("edit scripture", "verse:" + verse.getText());  
		
			
			startActivity(addEditScripture);
			return true;
		case R.id.deletescripture:
			deleteScripture();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void deleteScripture() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(UpdateScripture.this);
		
		builder.setTitle(R.string.confirmDelete);
		builder.setMessage(R.string.confirmDeleteMessage);
		
		// provide an OK button
	//	builder.setPositiveButton(R.string.button_delete, listener)
		
	}
	
}  // end of public class UpdateScripture extends Activity {
