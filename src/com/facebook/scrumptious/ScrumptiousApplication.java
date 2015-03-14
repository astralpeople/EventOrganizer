
package com.facebook.scrumptious;

import android.app.Application;
import com.facebook.model.GraphPlace;
import com.facebook.model.GraphUser;
import com.parse.Parse;

import java.util.List;


public class ScrumptiousApplication extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		Parse.initialize(this, "pC50qXBC6BQ3b27HKcQ4spxXOG77CmNtqQl86B7E", "hpe6QSlNgXgWEZBXfWkRL2dGEqN3c4L38o7eFP7X");
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
