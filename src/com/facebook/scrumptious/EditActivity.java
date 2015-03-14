package com.facebook.scrumptious;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class EditActivity extends Activity {

	Calendar c = Calendar.getInstance();

	TextView user;
	int DIALOG_TIME = 1;
	int myHour = c.get(Calendar.HOUR_OF_DAY);
	int myMinute = c.get(Calendar.MINUTE);
	TextView tvTime;

	int DIALOG_DATE = 2;
	int myYear = c.get(Calendar.YEAR);
	int myMonth = c.get(Calendar.MONTH);
	int myDay = c.get(Calendar.DAY_OF_MONTH);
	TextView tvDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		Intent intent = getIntent();
		String title = intent.getStringExtra("title");

		tvTime = (TextView) findViewById(R.id.tvTime);
		tvDate = (TextView) findViewById(R.id.tvDate);

		Button createEvent = (Button) findViewById(R.id.createEvent);

		createEvent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = getIntent();
				String mId = intent.getStringExtra("mId");

				ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
				query.getInBackground(mId, new GetCallback<ParseObject>() {
					public void done(ParseObject events, ParseException e) {
						if (e == null) {
							TextView title = (TextView) findViewById(R.id.editTitle);
							String date = myDay + "." + myMonth + "." + myYear;
							String time = myHour + ":" + myMinute;
							TextView description = (TextView) findViewById(R.id.editDescription);

							events.put("title", title.getText().toString());
							events.put("date", date.toString());
							events.put("time", time.toString());
							events.put("description", description.getText()
									.toString());
							events.saveInBackground();

							startActivity(new Intent(getApplicationContext(),
									Events.class));
						}
					}
				});
			}
		});
	}

	// Time

	public void time(View view) {
		showDialog(DIALOG_TIME);
	}

	protected Dialog onCreateDialog(int id) {
		if (id == DIALOG_TIME) {
			TimePickerDialog time = new TimePickerDialog(this, myTimeCallBack,
					myHour, myMinute, true);
			return time;
		}
		if (id == DIALOG_DATE) {
			DatePickerDialog date = new DatePickerDialog(this, myDataCallBack,
					myYear, myMonth, myDay);
			return date;
		}
		return super.onCreateDialog(id);
	}

	OnTimeSetListener myTimeCallBack = new OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			myHour = hourOfDay;
			myMinute = minute;
			tvTime.setText("Время начала: " + myHour + ":" + myMinute);
		}
	};

	// Data

	public void date(View view) {
		showDialog(DIALOG_DATE);
	}

	OnDateSetListener myDataCallBack = new OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			myYear = year;
			myMonth = monthOfYear + 1;
			myDay = dayOfMonth;
			tvDate.setText("Дата проведения: " + myDay + "." + myMonth + "."
					+ myYear);
		}
	};
}
