package com.facebook.scrumptious;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Events extends Activity {

	ListView mEventsList;
	EventAdapter mEventAdapter;
	private ProgressDialog mPd;
	private int mPosition;

	private int mMode = VIEW;
//	public final static int OFF = 0;
	public final static int UPDATE = 1;
	public final static int DELETE = 2;
	public final static int VIEW = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events);

		ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> parseObjects, ParseException e) {
				if (e == null) {
					EventsSingleton.getInstance().getVictor().clear();
					for (ParseObject parse : parseObjects) {
						EventsSingleton
								.getInstance()
								.getVictor()
								.add(new MyEvents(parse.getString("title"),
										parse.getString("date"), parse
												.getString("time"), parse
												.getObjectId()));
						mEventsList.invalidateViews();
						mEventsList.invalidate();
					}
				} else {
					Toast.makeText(getApplicationContext(),
							"Connection Failed", Toast.LENGTH_SHORT).show();
				}
			}
		});

		mEventAdapter = new EventAdapter(getApplicationContext());
		mEventsList = (ListView) findViewById(R.id.events_list);
		mEventsList.setAdapter(mEventAdapter);

		mEventsList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				switch (mMode) {
				case UPDATE:
					mPosition = position;
					Intent intent = new Intent(getApplicationContext(),
							EditActivity.class);
					intent.putExtra("mId", EventsSingleton.getInstance()
							.getVictor().get(position).getmId());
					startActivityForResult(intent, 1);
					break;

				case DELETE:
					mEventAdapter.deleteEvent(position);
					mEventsList.invalidateViews();
					mEventsList.invalidate();
					break;
				case VIEW:
					mPosition = position;
					Intent intent2 = new Intent(getApplicationContext(),
							InfoActivity.class);
					intent2.putExtra("mId", EventsSingleton.getInstance()
							.getVictor().get(position).getmId());
					startActivityForResult(intent2, 2);
					break;
				default:
					break;
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.e_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_delete:
			mMode = DELETE;
			break;
		case R.id.action_update:
			mMode = UPDATE;
			break;
		case R.id.action_view:
			mMode = VIEW;
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}