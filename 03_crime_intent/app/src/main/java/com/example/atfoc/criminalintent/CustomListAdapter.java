package com.example.atfoc.criminalintent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;


public  class CustomListAdapter extends ArrayAdapter<Crime>
{
	private LayoutInflater li;

	public CustomListAdapter(@NonNull Context context, int resource)
	{
		super(context, resource);
		li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public CustomListAdapter(@NonNull Context context, int resource, int textViewResourceId)
	{
		super(context, resource, textViewResourceId);
		li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public CustomListAdapter(@NonNull Context context, int resource, @NonNull Crime[] objects)
	{
		super(context, resource, objects);
		li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public CustomListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull Crime[] objects)
	{
		super(context, resource, textViewResourceId, objects);
		li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public CustomListAdapter(@NonNull Context context, int resource, @NonNull List<Crime> objects)
	{
		super(context, resource, objects);
		li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public CustomListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Crime> objects)
	{
		super(context, resource, textViewResourceId, objects);
		li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}


	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
	{
		if (null == convertView)
		{
			convertView = li.inflate(R.layout.fragment_crime_list_item, parent, false);
			ViewHolder vh = new ViewHolder(convertView);
			convertView.setTag(vh);
			vh.setCrimeData(getItem(position));

			return convertView;
		} else
		{
			ViewHolder vh = (ViewHolder) convertView.getTag();
			vh.setCrimeData(getItem(position));
			return convertView;
		}
	}

	private static class ViewHolder
	{
		private TextView mTvCrimeTitle;
		private TextView mTvCrimeDate;
		private CheckBox mCbCrimeSolved;
		private Crime mCrime;

		public ViewHolder(View v)
		{
			mCrime = null;
			mTvCrimeTitle = v.findViewById(R.id.fragment_crime_list_item_tv_title);
			mTvCrimeDate = v.findViewById(R.id.fragment_crime_list_item_tv_date);
			mCbCrimeSolved = v.findViewById(R.id.fragment_crime_list_item_cb_solved);
			mCbCrimeSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
			{
				@Override
				public void onCheckedChanged(CompoundButton compoundButton, boolean b)
				{
					if(null != mCrime)
					{
						mCrime.setSolved(b);
					}
				}
			});
		}

		public void setCrimeData(Crime c)
		{
			mCrime = c;
			mTvCrimeTitle.setText(c.getTitle());
			mTvCrimeDate.setText(c.getFormatDate());
			mCbCrimeSolved.setChecked(c.isSolved());
		}
	}
}
