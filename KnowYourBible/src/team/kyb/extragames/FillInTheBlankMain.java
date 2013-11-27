package team.kyb.extragames;

import team.kyb.MainActivity;
import team.kyb.R;
import team.kyb.animationAPI.AnimationHelper;
import team.kyb.animationAPI.LoseEffect;
import team.kyb.animationAPI.WinEffect;
import team.kyb.database.DatabaseConnector;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class FillInTheBlankMain extends Activity {
	DatabaseConnector database = new DatabaseConnector(this);

	private AnimationHelper animationHelper = new AnimationHelper();
	
	private Button mNewGame, mCheckGame;

	private TextView mWord1, mWord2, mWord3, mWord4, mScripture;

	private FillInTheBlankGame mGame;

	private EditText mOrder1, mOrder2, mOrder3, mOrder4;
	
	private String scripture;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.filling_the_blank);
		
		mNewGame = (Button) findViewById(R.id.newGameFill);
		mNewGame.setOnClickListener(mClickNewGame);
		
		mCheckGame = (Button) findViewById(R.id.submitFill);
		mNewGame.setOnClickListener(mClickCheck);

		mWord1 = (TextView) findViewById(R.id.word1);
		mWord2 = (TextView) findViewById(R.id.word2);
		mWord3 = (TextView) findViewById(R.id.word3);
		mWord4 = (TextView) findViewById(R.id.word4);
		
		mScripture = (TextView) findViewById(R.id.scripture_fill);
		
		mOrder1 = (EditText) findViewById(R.id.order1);
		mOrder2 = (EditText) findViewById(R.id.order2);
		mOrder3 = (EditText) findViewById(R.id.order3);
		mOrder4 = (EditText) findViewById(R.id.order4);
		
		
		database.open();
		ScriptureForGameHelper scriptureHelper = new ScriptureForGameHelper(database.getRandomScriptureForGame());
		database.close();

		scripture = scriptureHelper.getScriptureFull();

		mGame = new FillInTheBlankGame(scripture);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hangman_menu, menu);
		return true;
	}

	// handle choice from options menu
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// create a new Intent to launch
		Intent exitHangman = 
				new Intent(this, MainActivity.class);
		startActivity(exitHangman); 
		return super.onOptionsItemSelected(item); 
	} 	

	public void loseEffect() {
		Intent intent = new Intent(this, LoseEffect.class);
		startActivity(intent);
		mNewGame.setVisibility(1);
	}

	public void winEffect() {
		Intent intent = new Intent(this, WinEffect.class);
		startActivity(intent);
		mNewGame.setVisibility(1);
	}
	
	private OnClickListener mClickCheck = new OnClickListener() {

		@Override
		public void onClick(View v) {
			//get values from EditText
		}
	};
	
	private OnClickListener mClickNewGame = new OnClickListener() {

		@Override
		public void onClick(View v) {
			finish();
			startActivity(getIntent());
		}
	};
	

}
