package com.example.atfoc.criminalintent;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CrimeListFragment extends ListFragment
{
	private static String sDebugLog = CrimeListFragment.class.getName() + ".DEBUG_TAG";

	private CrimeLab mCrimeLab;
	private Context mContext;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mContext = getActivity().getApplicationContext();
		mCrimeLab = CrimeLab.getInstance(mContext);

		for(int i = 0; i < 100; ++i)
		{
			Crime c = new Crime();
			c.setSolved(i % 2 == 0);
			c.setTitle("Crime #" + String.valueOf(i));
			mCrimeLab.addCrime(c);
		}
		setListAdapter(new CustomListAdapter(mContext, R.layout.fragment_crime_list_item, mCrimeLab.getmCrimes()));
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		Crime c = (Crime)getListAdapter().getItem(position);
		Toast.makeText(mContext,c.getTitle(), Toast.LENGTH_SHORT).show();
	}

	private static class CustomListAdapter extends ArrayAdapter<Crime>
	{
		private LayoutInflater li;

		public CustomListAdapter(@NonNull Context context, int resource)
		{
			super(context, resource);
			li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		public CustomListAdapter(@NonNull Context context, int resource, int textViewResourceId)
		{
			super(context, resource, textViewResourceId);
			li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		public CustomListAdapter(@NonNull Context context, int resource, @NonNull Crime[] objects)
		{
			super(context, resource, objects);
			li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		public CustomListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull Crime[] objects)
		{
			super(context, resource, textViewResourceId, objects);
			li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		public CustomListAdapter(@NonNull Context context, int resource, @NonNull List<Crime> objects)
		{
			super(context, resource, objects);
			li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		public CustomListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Crime> objects)
		{
			super(context, resource, textViewResourceId, objects);
			li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}


		@NonNull
		@Override
		public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
		{
			if(null == convertView)
			{
				convertView = li.inflate(R.layout.fragment_crime_list_item,parent,false);
				ViewHolder vh  = new ViewHolder(convertView);
				convertView.setTag(vh);
				vh.setCrimeData(getItem(position));

				return convertView;
			}
			else
			{
				ViewHolder vh = (ViewHolder)convertView.getTag();
				vh.setCrimeData(getItem(position));
				return convertView;
			}
		}

		private static class ViewHolder
		{
			private TextView mTvCrimeTitle;
			private TextView mTvCrimeDate;
			private CheckBox mCbCrimeSolved;

			public ViewHolder(View v)
			{
				mTvCrimeTitle = v.findViewById(R.id.fragment_crime_list_item_tv_title);
				mTvCrimeDate= v.findViewById(R.id.fragment_crime_list_item_tv_date);
				mCbCrimeSolved = v.findViewById(R.id.fragment_crime_list_item_cb_solved);
			}

			public void setCrimeData(Crime c)
			{
				mTvCrimeTitle.setText(c.getTitle());
				mTvCrimeDate.setText(c.getFormatDate());
				mCbCrimeSolved.setChecked(c.isSolved());
			}
		}
	}
}

