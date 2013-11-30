package team.kyb;


import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Settings extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		addPreferencesFromResource(R.xml.preferences);	
		
	}

	
}  // end of public class Settings extends PreferenceActivity {
