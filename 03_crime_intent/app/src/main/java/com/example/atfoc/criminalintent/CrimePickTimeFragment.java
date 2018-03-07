package com.example.atfoc.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by atfoc on 3/7/18.
 */

public class CrimePickTimeFragment extends DialogFragment
{

	public static String sTAG_DATE_ARG = CrimePickTimeFragment.class.getName() + ".TAG.DATE.ARG";
	public static String sTAG_DATE_RET = CrimePickDateFragment.sTAG_DATE_RET;

	private Date mDate;

	public static CrimePickTimeFragment newInstance(Date d)
	{
		Bundle arg = new Bundle();

		arg.putSerializable(sTAG_DATE_ARG, d);

		CrimePickTimeFragment f = new CrimePickTimeFragment();
		f.setArguments(arg);
		return f;
	}
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		mDate = (Date)(getArguments().getSerializable(sTAG_DATE_ARG));
	}


	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		LayoutInflater li = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		final View v = li.inflate(R.layout.dialog_fragment_time_picker, null);


		return new AlertDialog.Builder(getActivity())
				.setView(v)
				.setPositiveButton("Accept", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialogInterface, int i)
					{
						TimePicker tp = v.findViewById(R.id.dialog_fragment_time_picker);
						Calendar cal = Calendar.getInstance();
						cal.setTime(mDate);
						if(Build.VERSION.SDK_INT < 23)
						{
							cal.set(Calendar.HOUR, tp.getCurrentHour());
							cal.set(Calendar.MINUTE, tp.getCurrentMinute());
						}
						else
						{
							cal.set(Calendar.HOUR, tp.getHour());
							cal.set(Calendar.MINUTE, tp.getMinute());
						}

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
				}).create();
	}
}
