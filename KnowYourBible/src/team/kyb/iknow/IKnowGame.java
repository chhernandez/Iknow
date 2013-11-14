package team.kyb.iknow;

import java.util.ArrayList;
import java.util.List;

import team.kyb.MainActivity;
import team.kyb.R;
import team.kyb.database.DatabaseConnector;
import team.kyb.database.Scriptures;
import team.kyb.extragames.ScriptureForGameHelper;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SimpleCursorAdapter;
import android.widget.CursorAdapter;
import android.database.Cursor;

public class IKnowGame extends Activity {

//	private Spinner spinnerBook = (Spinner) findViewById(R.id.spinner_book);
//	private Spinner spinnerChapter = (Spinner) findViewById(R.id.spinner_chapter);
//	private Spinner spinnerVerse = (Spinner) findViewById(R.id.spinner_verse);
	
	DatabaseConnector database = new DatabaseConnector(this);
	
	private String userBook, userChapter, userVerse;
	private String correctBook, correctChapter, correctVerse;
	private Spinner spinnerBook, spinnerChapter, spinnerVerse;
	private TextView tv_game_status, tv_numCorrect, tv_numAttempts;
	private int numCorrect, numAttempts;
	private String verseToDisplay;
	private TextView tv_displayVerse;
	private ArrayList<String> listVerses;
	private int numVerses;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.iknow_layout);
		
		numAttempts = 0;
		numCorrect = 0;
		
		spinnerBook = (Spinner) findViewById(R.id.spinner_book);
		spinnerChapter = (Spinner) findViewById(R.id.spinner_chapter);
		spinnerVerse = (Spinner) findViewById(R.id.spinner_verse);
		
		
		// populating spinners
		addItemsOnSpinnerBook();
		addItemsOnSpinnerChapter();
		addItemsOnSpinnerVerse();
		
		// GET RANDOM VERSE !!!

		database.open();
		ScriptureForGameHelper scripture = new ScriptureForGameHelper(database.getRandomScriptureForGame());
		verseToDisplay = scripture.getPassage();
		
		// Uncomment when have all spinners populated
		correctBook = scripture.getBook();
		correctChapter = String.valueOf(scripture.getChapter());
		correctVerse = String.valueOf(scripture.getVerse());
		
		
//		numVerses = database.getAllVerses().getCount();
//		verseToDisplay = String.valueOf(numVerses);
		
//		Cursor cursorVerses = database.getAllVerses();
//		ScriptureForGameHelper verseI = new ScriptureForGameHelper(cursorVerses);
//		verseToDisplay = String.valueOf(verseI.getVerse());
		
		database.close();
		displayVerse(verseToDisplay);
		
		// Delete when have uncomment above and have all spinners populated --------
		// Get correct book, chapter, verse to check user's answer
//		correctBook = "book 0";
//		correctChapter = "chapter 0";
//		correctVerse = "verse 0";
		// --------------------------------------------------------------------------

		checkUserAnswer(correctBook, correctChapter, correctVerse);
		nextListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.iknow_menu, menu);
		return true;
	}

	// handle choice from options menu
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// create a new Intent to launch
		Intent exitIKnow = 
				new Intent(this, MainActivity.class);
		startActivity(exitIKnow); 
		return super.onOptionsItemSelected(item); 
	} 	

	public void displayVerse( String verseToDisplay)
	{
		TextView textview_text = (TextView)  findViewById(R.id.textview_text);
		textview_text.setText(verseToDisplay);
	}
	
	private void addItemsOnSpinnerBook()
	{
//		Spinner spinnerBook = (Spinner) findViewById(R.id.spinner_book);
//		List<String> list = new ArrayList<String>();
//		list.add("book 0");
//		list.add("book 1");
//		list.add("book 2");
//		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		Resources res = getResources();
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,res.getStringArray(R.array.all_books_array));
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerBook.setAdapter(dataAdapter);
	}
	
	private void addItemsOnSpinnerChapter()
	{
//		Spinner spinnerChapter = (Spinner) findViewById(R.id.spinner_chapter);
		List<String> list = new ArrayList<String>();
//		list.add("chapter 0");
//		list.add("chapter 1");
//		list.add("chapter 2");
		for (int i = 1; i<=150; i++)
		{
			list.add(String.valueOf(i));
		}
	
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerChapter.setAdapter(dataAdapter);
	}
	
	private void addItemsOnSpinnerVerse()
	{
//		Spinner spinnerVerse = (Spinner) findViewById(R.id.spinner_verse);
		List<String> list = new ArrayList<String>();
//		list.add("verse 0");
//		list.add("verse 1");
//		list.add("verse 2");
		for (int i = 1; i<=176; i++)
		{
			list.add(String.valueOf(i));
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerVerse.setAdapter(dataAdapter);
	}
	
	private void checkUserAnswer(final String correctBook, final String correctChapter, final String correctVerse)
	{
//		final Spinner spinnerBook = (Spinner) findViewById(R.id.spinner_book);
//		final Spinner spinnerChapter = (Spinner) findViewById(R.id.spinner_chapter);
//		final Spinner spinnerVerse = (Spinner) findViewById(R.id.spinner_verse);
		
		final Button buttonSubmit = (Button) findViewById(R.id.button_submit);
		

		
		
		buttonSubmit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
//				Toast.makeText(MainActivity.this, 
//						"OnClickListener : " +
////								"\nSpinnerBook : "+ String.valueOf(spinnerBook.getSelectedItem()) + 
////								"\nSpinnerChapter : "+ String.valueOf(spinnerChapter.getSelectedItem()) + 
////								"\nSpinnerVerse : "+ String.valueOf(spinnerVerse.getSelectedItem()) 
//								"\nSpinnerBook : "+ String.valueOf(spinnerBook.getSelectedItem()) + 
//								"\nSpinnerChapter : "+ String.valueOf(spinnerChapter.getSelectedItem()) + 
//								"\nSpinnerVerse : "+ String.valueOf(spinnerVerse.getSelectedItem()) 
//								, Toast.LENGTH_SHORT).show();
				
				numAttempts++;
				
				userBook = String.valueOf(spinnerBook.getSelectedItem());
				userChapter = String.valueOf(spinnerChapter.getSelectedItem());
				userVerse = String.valueOf(spinnerVerse.getSelectedItem());
				
				tv_game_status = (TextView) findViewById(R.id.game_status);
				tv_numCorrect = (TextView) findViewById(R.id.numCorrect);
				tv_numAttempts = (TextView) findViewById(R.id.numAttempts);
				
				
				
				
				if ( correctBook.equals(userBook) && correctChapter.equals(userChapter) && correctVerse.equals(userVerse))
				{
//					Toast.makeText(IKnowGame.this, 
//							"Correct: " +
//									"\nSpinnerBook : "+ String.valueOf(spinnerBook.getSelectedItem()) +  
//									"\nSpinnerChapter : "+ String.valueOf(spinnerChapter.getSelectedItem()) + 
//									"\nSpinnerVerse : "+ String.valueOf(spinnerVerse.getSelectedItem()) 
//									, Toast.LENGTH_SHORT).show();
					tv_game_status.setText("Correct !");
					numCorrect++;
					tv_numCorrect.setText(String.valueOf(numCorrect));
					tv_numAttempts.setText(String.valueOf(numAttempts));
					
					buttonSubmit.setEnabled(false);
				}
				else
				{
//					Toast.makeText(IKnowGame.this, 
//							"Incorrect: " +
//									"\nSpinnerBook : "+ String.valueOf(spinnerBook.getSelectedItem()) + 
//									"\nSpinnerChapter : "+ String.valueOf(spinnerChapter.getSelectedItem()) + 
//									"\nSpinnerVerse : "+ String.valueOf(spinnerVerse.getSelectedItem()) 
//									, Toast.LENGTH_SHORT).show();
					tv_game_status.setText("Incorrect ! ");
					tv_numAttempts.setText(String.valueOf(numAttempts));
				}

				
	
			}
		});
		
		
	}	
	
	private void nextListener()
	{
		Button buttonNextIKnow = (Button) findViewById(R.id.next_iknow);
		

		
		
		buttonNextIKnow.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// GET RANDOM VERSE !!!
				database.open();
				ScriptureForGameHelper scripture = new ScriptureForGameHelper(database.getRandomScriptureForGame());
				verseToDisplay = scripture.getPassage();
				
				// Uncomment when have all spinners populated
				correctBook = scripture.getBook();
				correctChapter = String.valueOf(scripture.getChapter());
				correctVerse = String.valueOf(scripture.getVerse());
				database.close();
				
				Button buttonSubmit = (Button) findViewById(R.id.button_submit);
				buttonSubmit.setEnabled(true);
				
				verseToDisplay = scripture.getPassage();
				displayVerse(verseToDisplay);
				tv_game_status.setText("");

				checkUserAnswer(correctBook, correctChapter, correctVerse);
	
			}
		});
		
		
	}	
	
}