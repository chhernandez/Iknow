package team.kyb.database;


import team.kyb.R;
import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
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
	
	
}  // end of public class UpdateScripture extends Activity {
