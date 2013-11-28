package team.kyb;

import team.kyb.database.DatabaseConnector;
import team.kyb.extragames.ScriptureForGameHelper;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
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
		

		wPassage = "As the last enemy, death is to be brought to nothing.";
		wBook = "1 Corinthians";
		wChapter = "15";
		wVerse = "26";
	

		String fulltext = "<b>" + wPassage + "</b>" + "<br>" +
				"<br>" + wBook + " " + wChapter
				+ " : " + wVerse;			
		
		//fill TextViews with the retrieved data
		
		// allpassage.setText(Html.fromHtml(fulltext));		
		
		// loop through all instances of this widget
		for (int appWidgetId : appWidgetIds){
			
			Log.d("KYB Widget", "Updating widget " + appWidgetId);
			
            // Create an Intent to launch KYB from spash screen
            Intent intent = new Intent(context, SplashScreen.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
			
			
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.kyb_widget);
			
			views.setTextViewText(R.id.allpassageTextView, Html.fromHtml(fulltext));
	        views.setOnClickPendingIntent(R.id.kyb_icon, pendingIntent);
	        
            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
            
		}// end of for (int appWidgetId : appWidgetIds){
		
	//	database.close();		
		
	}
	
}
