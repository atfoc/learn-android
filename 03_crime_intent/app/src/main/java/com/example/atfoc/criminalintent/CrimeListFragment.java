package com.example.atfoc.criminalintent;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
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
	public void onResume()
	{
		super.onResume();
		((CustomListAdapter)getListAdapter()).notifyDataSetChanged();
	}

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

		Intent i = new Intent(mContext, CrimeActivity.class);
		i.putExtra(CrimeActivity.sTAG_CRIME_UUDI_INTENT_ARG, c.getId());
		startActivity(i);
	}
}

