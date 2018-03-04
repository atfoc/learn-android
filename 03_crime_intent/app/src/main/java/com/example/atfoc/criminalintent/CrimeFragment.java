package com.example.atfoc.criminalintent;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class CrimeFragment extends Fragment
{

	private Crime mCrime;

	private EditText mEtCrimeTitle;
	private Button mBtnDate;
	private CheckBox mCbSolved;

	public CrimeFragment()
	{
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mCrime  = new Crime();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.fragment_crime, container, false);

		mEtCrimeTitle = v.findViewById(R.id.fragment_crime_et_crime_title);
		mBtnDate = v.findViewById(R.id.fragment_crime_btn_date_value);
		mCbSolved = v.findViewById(R.id.fragment_crime_cb_solved);

		mBtnDate.setText(mCrime.getFormatDate());


		return v;
	}
}
