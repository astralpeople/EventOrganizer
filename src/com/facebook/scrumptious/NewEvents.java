package com.facebook.scrumptious;

import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

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

import com.parse.ParseObject;
import com.parse.ParsePush;

public class NewEvents extends Activity {

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
	TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new);

		user = (TextView) findViewById(R.id.userNameView);
		tvTime = (TextView) findViewById(R.id.tvTime);
		tvDate = (TextView) findViewById(R.id.tvDate);

		final Intent intent = getIntent();
		String uName = intent.getStringExtra("userNameView");

		user.setText(uName);

		Button createEvent = (Button) findViewById(R.id.createEvent);

		createEvent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ParseObject po = new ParseObject("Events");

				title = (TextView) findViewById(R.id.editTitle);
				String date = myDay + "." + myMonth + "." + myYear;
				String time = myHour + ":" + myMinute;
				TextView description = (TextView) findViewById(R.id.editDescription);

//				po.put("user", intent.getStringExtra("userNameView"));
				po.put("title", title.getText().toString());
				po.put("date", date.toString());
				po.put("time", time.toString());
				po.put("description", description.getText().toString());
				po.put("done", false);

				po.saveInBackground();

				JSONObject data = new JSONObject();
				try {
					data.put("action", "com.example.UPDATE_STATUS");
					data.put("alert", title.getText().toString());
					data.put("badge", "Increment");

					ParsePush push = new ParsePush();
					// push.setChannel("newEvents");
					push.setData(data);
					push.sendInBackground();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				startActivity(new Intent(getApplicationContext(),
						MainActivity.class));

			}
		});

		// Button push = (Button) findViewById(R.id.testpush);
		//
		// push.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// title = (TextView) findViewById(R.id.editTitle);
		//
		// JSONObject data = new JSONObject();
		// try {
		// data.put("action", "com.example.UPDATE_STATUS");
		// data.put("alert", title.getText().toString());
		// data.put("badge", "Increment");
		//
		// ParsePush push = new ParsePush();
		// // push.setChannel("newEvents");
		// push.setData(data);
		// push.sendInBackground();
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// });
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
