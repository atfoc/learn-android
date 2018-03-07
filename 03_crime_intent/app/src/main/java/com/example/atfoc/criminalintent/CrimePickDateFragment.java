package com.example.atfoc.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

public class CrimePickDateFragment extends DialogFragment
{
	public static String sTAG_DATE_ARG =  CrimePickDateFragment.class.getName() + ".TAG.DATE.ARG";
	public static String sTAG_DATE_RET = CrimePickDateFragment.class.getName() + ".TAG.DATE.RET";

	public static CrimePickDateFragment newInstance(Date d)
	{
		Bundle arg = new Bundle();

		arg.putSerializable(sTAG_DATE_ARG, d);

		CrimePickDateFragment f = new CrimePickDateFragment();

		f.setArguments(arg);

		return f;
	}

	private Date mDate;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		mDate = (Date)getArguments().getSerializable(sTAG_DATE_ARG);
	}


	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		final View v = ((LayoutInflater)(getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE))).inflate(R.layout.dialog_fragment_pick_date_layout, null);
		final DatePicker dp = v.findViewById(R.id.dialog_fragment_date_picker);
		final Calendar cal = Calendar.getInstance();
		cal.setTime(mDate);
		dp.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);

		return new AlertDialog.Builder(getActivity())
				.setTitle("Crime Date")
				.setView(v)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialogInterface, int i)
					{

						cal.set(dp.getYear(),dp.getMonth(), dp.getDayOfMonth());
						Date d = cal.getTime();

						Intent in = new Intent();

						in.putExtra(sTAG_DATE_RET, d);

						getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, in);
					}
				})
				.setNegativeButton("Cancle", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialogInterface, int i)
					{
						getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, null);
					}
				})
				.create();
	}
}
