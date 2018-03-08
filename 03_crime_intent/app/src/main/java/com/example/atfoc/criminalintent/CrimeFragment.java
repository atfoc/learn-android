package com.example.atfoc.criminalintent;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Console;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class CrimeFragment extends Fragment
{

	private static String sTAG_UUID_ARG = CrimeFragment.class.getName() + ".TAG.UUID.ARG";

	private Crime mCrime;

	private EditText mEtCrimeTitle;
	private Button mBtnDate;
	private CheckBox mCbSolved;
	private CrimeLab mCrimeLab;

	public static CrimeFragment newInstance(UUID crimeId)
	{
		Bundle arg  = new Bundle();

		arg.putSerializable(sTAG_UUID_ARG, crimeId);

		CrimeFragment f = new CrimeFragment();

		f.setArguments(arg);

		return f;
	}


	public CrimeFragment()
	{
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Bundle arg = getArguments();

		mCrimeLab = CrimeLab.getInstance(getActivity().getApplicationContext());

		UUID  crimeId = (UUID)arg.getSerializable(sTAG_UUID_ARG);

		mCrime = mCrimeLab.getCrime(crimeId);

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

		mEtCrimeTitle.setText(mCrime.getTitle());
		mCbSolved.setChecked(mCrime.isSolved());
		mBtnDate.setText(mCrime.getFormatDate());

		mEtCrimeTitle.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
			{

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
			{

			}

			@Override
			public void afterTextChanged(Editable editable)
			{
				mCrime.setTitle(editable.toString());
			}
		});


		mCbSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b)
			{
				mCrime.setSolved(b);
			}
		});


		mBtnDate.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				FragmentManager fm = getActivity().getFragmentManager();

				DateTimePicker f = DateTimePicker.newInstance(mCrime.getDate());

				f.setTargetFragment(CrimeFragment.this, 0);
				f.show(fm, DateTimePicker.class.getName());
			}
		});
		return v;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(0 == requestCode)
		{
			if(Activity.RESULT_OK ==  resultCode)
			{
				Date d = (Date)data.getSerializableExtra(CrimePickDateFragment.sTAG_DATE_RET);
				mCrime.setDate(d);
				mBtnDate.setText(mCrime.getFormatDate());
			}
			else if(Activity.RESULT_CANCELED == resultCode)
			{
				Log.d("Debug", "user canceld");
			}
			else if(DateTimePicker.sRESAULT_CODE_BACK == resultCode)
			{
				FragmentManager fm = getActivity().getFragmentManager();

				DateTimePicker f = DateTimePicker.newInstance(mCrime.getDate());

				f.setTargetFragment(CrimeFragment.this, 0);
				f.show(fm, DateTimePicker.class.getName());
			}
		}
	}
}
