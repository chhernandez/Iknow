package team.kyb.extragames;

import team.kyb.R;
import team.kyb.animationAPI.AnimationHelper;
import team.kyb.animationAPI.LoseEffect;
import team.kyb.database.DatabaseConnector;
import team.kyb.database.RandomScripture;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class HangmanMain extends Activity {
	DatabaseConnector database = new DatabaseConnector(this);

	private AnimationHelper animationHelper = new AnimationHelper();

	private Button mKeyboard[] = new Button[26];

	private TextView mWord, mGuessLeft, mStatus;

	private HangmanGame mGame;

	private int trackImage = 0;

	private int mHangmanView[] = new int[] { R.drawable.hangman0,
			R.drawable.hangman1, R.drawable.hangman2, R.drawable.hangman3,
			R.drawable.hangman4, R.drawable.hangman5, R.drawable.hangman6 };

	private ImageView mHangman[] = new ImageView[7];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hangman_layout);

		mWord = (TextView) findViewById(R.id.word_hang_man);
		mGuessLeft = (TextView) findViewById(R.id.countValue);
		mStatus = (TextView) findViewById(R.id.status);

		mHangman[0] = (ImageView) findViewById(R.id.hangmanView00);
		mHangman[1] = (ImageView) findViewById(R.id.hangmanView01);
		mHangman[2] = (ImageView) findViewById(R.id.hangmanView02);
		mHangman[3] = (ImageView) findViewById(R.id.hangmanView03);
		mHangman[4] = (ImageView) findViewById(R.id.hangmanView04);
		mHangman[5] = (ImageView) findViewById(R.id.hangmanView05);
		mHangman[6] = (ImageView) findViewById(R.id.hangmanView06);

		mHangman[0].setBackgroundResource(mHangmanView[trackImage]);
		setNextFiveHangManView();

		declaringKeyboard();
		
		database.open();

		String temp = database.getRandomScriptureForGame().getString(1);
		mStatus.setText(temp);

		mGame = new HangmanGame("khuong", 6);

	}

	private void setNextFiveHangManView() {
		for (int i = 1; i < mHangman.length; i++) {
			mHangman[i].setBackgroundResource(mHangmanView[i]);
			mHangman[i].setVisibility(View.GONE);
		}

	}

	private void declaringKeyboard() {
		mKeyboard[0] = (Button) findViewById(R.id.a);
		mKeyboard[1] = (Button) findViewById(R.id.b);
		mKeyboard[2] = (Button) findViewById(R.id.c);
		mKeyboard[3] = (Button) findViewById(R.id.d);
		mKeyboard[4] = (Button) findViewById(R.id.e);
		mKeyboard[5] = (Button) findViewById(R.id.f);
		mKeyboard[6] = (Button) findViewById(R.id.g);
		mKeyboard[7] = (Button) findViewById(R.id.h);
		mKeyboard[8] = (Button) findViewById(R.id.i);
		mKeyboard[9] = (Button) findViewById(R.id.j);
		mKeyboard[10] = (Button) findViewById(R.id.k);
		mKeyboard[11] = (Button) findViewById(R.id.l);
		mKeyboard[12] = (Button) findViewById(R.id.m);
		mKeyboard[13] = (Button) findViewById(R.id.n);
		mKeyboard[14] = (Button) findViewById(R.id.o);
		mKeyboard[15] = (Button) findViewById(R.id.p);
		mKeyboard[16] = (Button) findViewById(R.id.q);
		mKeyboard[17] = (Button) findViewById(R.id.r);
		mKeyboard[18] = (Button) findViewById(R.id.s);
		mKeyboard[19] = (Button) findViewById(R.id.t);
		mKeyboard[20] = (Button) findViewById(R.id.u);
		mKeyboard[21] = (Button) findViewById(R.id.v);
		mKeyboard[22] = (Button) findViewById(R.id.w);
		mKeyboard[23] = (Button) findViewById(R.id.x);
		mKeyboard[24] = (Button) findViewById(R.id.y);
		mKeyboard[25] = (Button) findViewById(R.id.z);

		for (int i = 0; i < mKeyboard.length; i++) {
			mKeyboard[i].setEnabled(true);
			mKeyboard[i].setOnClickListener(new ButtonClickListener(i));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class ButtonClickListener implements View.OnClickListener {
		char pressedLetter;
		int location;

		public ButtonClickListener(int i) {
			this.location = i;
			this.pressedLetter = (char) (i + 97);
		}

		public void onClick(View view) {
			if (mKeyboard[location].isEnabled() && !mGame.gameOver()) {
				mKeyboard[location].setEnabled(false);
				int wrong = mGame.makeGuess(pressedLetter);
				if (wrong == 0) {
					animationHelper.applyRotation(mHangman[trackImage],
							mHangman[++trackImage], 0, 90);
					Log.d("FLIP", "Value of trackImage " + trackImage);
				}
				mGuessLeft.setText("" + mGame.numGuessesRemaining());
				mWord.setText(mGame.displayGameState());
				if (mGame.gameOver()) {
					if (mGame.isWin()) {
						mStatus.setText("YOU WIN");
					} else {
						mStatus.setText("YOU LOSE");
						loseEffect();

					}
				} else {
					mStatus.setText("CONTINUE !");
				}
			}
		}
	}

	public void loseEffect() {
		Intent intent = new Intent(this, LoseEffect.class);
		startActivity(intent);

	}

	public void winEffect() {

	}

}
