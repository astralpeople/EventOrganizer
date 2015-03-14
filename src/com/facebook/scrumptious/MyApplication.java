package com.facebook.scrumptious;

import java.util.List;

import android.app.Application;
import android.util.Log;

import com.facebook.model.GraphPlace;
import com.facebook.model.GraphUser;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.SaveCallback;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		Parse.initialize(this, "pC50qXBC6BQ3b27HKcQ4spxXOG77CmNtqQl86B7E",
				"hpe6QSlNgXgWEZBXfWkRL2dGEqN3c4L38o7eFP7X");

		ParsePush.subscribeInBackground("", new SaveCallback() {
			@Override
			public void done(ParseException e) {
				if (e == null) {
					Log.d("com.parse.push",
							"successfully subscribed to the broadcast channel.");
				} else {
					Log.e("com.parse.push", "failed to subscribe for push", e);
				}
			}
		});
	}

	private List<GraphUser> selectedUsers;
	private GraphPlace selectedPlace;

	public List<GraphUser> getSelectedUsers() {
		return selectedUsers;
	}

	public void setSelectedUsers(List<GraphUser> users) {
		selectedUsers = users;
	}

	public GraphPlace getSelectedPlace() {
		return selectedPlace;
	}

	public void setSelectedPlace(GraphPlace place) {
		this.selectedPlace = place;
	}
}
