package team.kyb.iknow;

import java.util.ArrayList;
import java.util.List;

import team.kyb.MainActivity;
import team.kyb.R;
import team.kyb.database.Scriptures;
import android.app.Activity;
import android.content.Intent;
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

public class IKnowGame extends Activity {

//	private Spinner spinnerBook = (Spinner) findViewById(R.id.spinner_book);
//	private Spinner spinnerChapter = (Spinner) findViewById(R.id.spinner_chapter);
//	private Spinner spinnerVerse = (Spinner) findViewById(R.id.spinner_verse);
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		displayVerse();
		
		addItemsOnSpinnerBook();
		addItemsOnSpinnerChapter();
		addItemsOnSpinnerVerse();
		addListenerOnButton();
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

	private void displayVerse()
	{
		String exampleText = getResources().getString(R.string.joke_honesty) ;
		TextView textview_text = (TextView)  findViewById(R.id.textview_text);
		textview_text.setText(exampleText);
	}
	
	private void addItemsOnSpinnerBook()
	{
		Spinner spinnerBook = (Spinner) findViewById(R.id.spinner_book);
		List<String> list = new ArrayList<String>();
		list.add("book new 0");
		list.add("book new 1");
		list.add("book new 2");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerBook.setAdapter(dataAdapter);
	}
	
	private void addItemsOnSpinnerChapter()
	{
		Spinner spinnerChapter = (Spinner) findViewById(R.id.spinner_chapter);
		List<String> list = new ArrayList<String>();
		list.add("chapter new 0");
		list.add("chapter new 1");
		list.add("chapter new 2");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerChapter.setAdapter(dataAdapter);
	}
	
	private void addItemsOnSpinnerVerse()
	{
		Spinner spinnerVerse = (Spinner) findViewById(R.id.spinner_verse);
		List<String> list = new ArrayList<String>();
		list.add("verse new 0");
		list.add("verse new 1");
		list.add("verse new 2");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerVerse.setAdapter(dataAdapter);
	}
	
	private void addListenerOnButton()
	{
		final Spinner spinnerBook = (Spinner) findViewById(R.id.spinner_book);
		final Spinner spinnerChapter = (Spinner) findViewById(R.id.spinner_chapter);
		final Spinner spinnerVerse = (Spinner) findViewById(R.id.spinner_verse);
		
		Button buttonSubmit = (Button) findViewById(R.id.button_submit);
		
		final String correctBook = "book new 0";
		final String correctChapter = "chapter new 0";
		final String correctVerse = "verse new 0";
		
		
		
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
				
				String userBook = String.valueOf(spinnerBook.getSelectedItem());
				String userChapter = String.valueOf(spinnerChapter.getSelectedItem());
				String userVerse = String.valueOf(spinnerVerse.getSelectedItem());
				
				if ( correctBook.equals(userBook) == true )
				{
					Toast.makeText(IKnowGame.this, 
							"Correct: " +
									"\nSpinnerBook : "+ String.valueOf(spinnerBook.getSelectedItem()) +  
									"\nSpinnerChapter : "+ String.valueOf(spinnerChapter.getSelectedItem()) + 
									"\nSpinnerVerse : "+ String.valueOf(spinnerVerse.getSelectedItem()) 
									, Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(IKnowGame.this, 
							"Incorrect: " +
									"\nSpinnerBook : "+ String.valueOf(spinnerBook.getSelectedItem()) + 
									"\nSpinnerChapter : "+ String.valueOf(spinnerChapter.getSelectedItem()) + 
									"\nSpinnerVerse : "+ String.valueOf(spinnerVerse.getSelectedItem()) 
									, Toast.LENGTH_SHORT).show();
				}

				
	
			}
		});
		
		
	}	
	
}
