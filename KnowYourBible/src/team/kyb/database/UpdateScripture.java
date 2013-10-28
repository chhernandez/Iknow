package team.kyb.database;


import team.kyb.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class UpdateScripture extends Activity {

	private long rowID;
	private TextView passage;
	private TextView book;
	private TextView chapter;
	private TextView verse;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.scripture_update);	
		passage = (TextView) findViewById(R.id.passageTextView);
		book = (TextView) findViewById(R.id.bookTextView);		
		chapter = (TextView) findViewById(R.id.chapterTextView);
		verse = (TextView) findViewById(R.id.verseTextView);	
		
		//get the selected scripture's row id
		Bundle extras = getIntent().getExtras();
		rowID = extras.getLong(Scriptures.ROW_ID);
		
	} // end of public void onCreate(Bundle savedInstanceState){
	
	
	
}  // end of public class UpdateScripture extends Activity {
