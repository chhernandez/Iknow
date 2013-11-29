package team.kyb;

import java.text.SimpleDateFormat;
import java.util.Random;

import team.kyb.database.DatabaseConnector;
import team.kyb.extragames.ScriptureForGameHelper;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.Html;
import android.util.Log;
import android.widget.RemoteViews;
import team.kyb.R;

public class DailyScriptureWidget extends AppWidgetProvider{

	public static final String DEBUG_TAG = "kyb widget";
	
	//DatabaseConnector database = new DatabaseConnector(DailyScriptureWidget.this);		
	
	private String wPassage, wBook, wChapter, wVerse;	
	String mylocale = java.util.Locale.getDefault().getLanguage();	
	
	
	@Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		

/*		database.open();
		ScriptureForGameHelper scripture = new ScriptureForGameHelper(database.getRandomScriptureForGame());
		
		
		wPassage = scripture.getPassage();
		wBook = scripture.getBook();
		wChapter = String.valueOf(scripture.getChapter());
		wVerse = String.valueOf(scripture.getVerse());*/
	
		
		long date = System.currentTimeMillis(); 

		SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMM d, yyyy");
		String dateString = sdf.format(date);   		
		

		Resources res = context.getResources();
		
		wPassage = res.getString(R.string.passage_a2);
		wBook = res.getString(R.string.passage_b2);
		wChapter = res.getString(R.string.passage_c2);
		wVerse = res.getString(R.string.passage_d2);
	
		
/*		String fulltext = "<b>" + wPassage + "</b>" + "<br>" +
				"<br>" + wBook + " " + wChapter
				+ " : " + wVerse;	*/		
		
		String[] mytextarray = new String[4];
		
		mytextarray[0] = "<b>" + res.getString(R.string.passage_a1) + "</b>" + "<br>" +
				"<br>" + res.getString(R.string.passage_b1) + " " + res.getString(R.string.passage_c1)
				+ " : " + res.getString(R.string.passage_d1);
		mytextarray[1] = "<b>" + res.getString(R.string.passage_a2) + "</b>" + "<br>" +
				"<br>" + res.getString(R.string.passage_b2) + " " + res.getString(R.string.passage_c2)
				+ " : " + res.getString(R.string.passage_d2);		
		mytextarray[2] = "<b>" + res.getString(R.string.passage_a3) + "</b>" + "<br>" +
				"<br>" + res.getString(R.string.passage_b3) + " " + res.getString(R.string.passage_c3)
				+ " : " + res.getString(R.string.passage_d3);	
		mytextarray[3] = "<b>" + res.getString(R.string.passage_a4) + "</b>" + "<br>" +
				"<br>" + res.getString(R.string.passage_b4) + " " + res.getString(R.string.passage_c4)
				+ " : " + res.getString(R.string.passage_d4);		
		
		int idx = new Random().nextInt(mytextarray.length);	
		
		String fulltext = (mytextarray[idx]);		
		
		//fill TextViews with the retrieved data
		
		// allpassage.setText(Html.fromHtml(fulltext));		
		
		// loop through all instances of this widget
		for (int appWidgetId : appWidgetIds){
			
			Log.d("KYB Widget", "Updating widget " + appWidgetId);
			
            // Create an Intent to launch KYB from spash screen
            Intent intent = new Intent(context, SplashScreen.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
			
			
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.kyb_widget);
			views.setTextViewText(R.id.dateTextView, dateString);
			views.setTextViewText(R.id.allpassageTextView, Html.fromHtml(fulltext));
	        views.setOnClickPendingIntent(R.id.kyb_icon, pendingIntent);
	        
            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
            
		}// end of for (int appWidgetId : appWidgetIds){
		
	//	database.close();		
		
	}
	
}
