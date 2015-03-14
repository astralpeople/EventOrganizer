package com.facebook.scrumptious;

import java.util.Vector;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.ParseObject;

public class EventAdapter extends BaseAdapter {

	private Context mContext;

	public EventAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return EventsSingleton.getInstance().getVictor().size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		RelativeLayout rl;

		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		rl = (RelativeLayout) inflater.inflate(R.layout.events_list, parent,
				false);

		TextView name = (TextView) rl.findViewById(R.id.etextView1);
		TextView date = (TextView) rl.findViewById(R.id.textDate);
		TextView time = (TextView) rl.findViewById(R.id.textTime);

		name.setText(EventsSingleton.getInstance().getVictor().get(position)
				.getmEvent().toString());
//		name.setTextColor(Color.WHITE);
		name.setTextSize(20);

		date.setText(EventsSingleton.getInstance().getVictor().get(position)
				.getmDate().toString());
//		date.setTextColor(Color.GRAY);
		date.setTextSize(16);

		time.setText(EventsSingleton.getInstance().getVictor().get(position)
				.getmTime().toString());
//		time.setTextColor(Color.GRAY);
		time.setTextSize(16);

		return rl;
	}

	public void deleteEvent(int position) {
		ParseObject myObject = ParseObject.createWithoutData("Events",
				EventsSingleton.getInstance().getVictor().get(position)
						.getmId().toString());
		myObject.deleteInBackground();
	}
}
