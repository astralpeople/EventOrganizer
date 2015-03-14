package com.facebook.scrumptious;

public class MyEvents {

	private String mEvent;
	private String mDate;
	private String mTime;
	private String mId;
	public String getmEvent() {
		return mEvent;
	}
	public void setmEvent(String mEvent) {
		this.mEvent = mEvent;
	}
	public String getmDate() {
		return mDate;
	}
	public void setmDate(String mDate) {
		this.mDate = mDate;
	}
	public String getmTime() {
		return mTime;
	}
	public void setmTime(String mTime) {
		this.mTime = mTime;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public MyEvents(String mEvent, String mDate, String mTime, String mId) {
		super();
		this.mEvent = mEvent;
		this.mDate = mDate;
		this.mTime = mTime;
		this.mId = mId;
	}

	

}
