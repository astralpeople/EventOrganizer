package com.facebook.scrumptious;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class InfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);

		Intent intent = getIntent();
		final String mId = intent.getStringExtra("mId");

		ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
		query.getInBackground(mId, new GetCallback<ParseObject>() {
			public void done(ParseObject events, ParseException e) {
				if (e == null) {
					TextView title = (TextView) findViewById(R.id.textTitle);
					TextView date = (TextView) findViewById(R.id.textDate);
					TextView time = (TextView) findViewById(R.id.textTime);
					TextView description = (TextView) findViewById(R.id.textDescription);
					
					title.setText(events.getString("title"));
					date.setText(events.getString("date"));
					time.setText(events.getString("time"));
					description.setText(events.getString("description"));
				} else {
					Toast.makeText(getApplicationContext(),
							"Connection Failed", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
