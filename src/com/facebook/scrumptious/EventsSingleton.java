package com.facebook.scrumptious;

import java.util.Vector;

public class EventsSingleton {

	private static EventsSingleton instance = null;
	private Vector<MyEvents> victor;

	public Vector<MyEvents> getVictor() {
		return victor;
	}

	public static EventsSingleton getInstance() {
		if (instance == null) {
			instance = new EventsSingleton();
		}
		return instance;
	}

	private EventsSingleton() {
		victor = new Vector<MyEvents>();
	}
}