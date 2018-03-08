package com.example.atfoc.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.DatePicker;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by atfoc on 3/7/18.
 */

public class DateTimePicker extends DialogFragment
{
	public static String sTAG_DATE_ARG = DateTimePicker.class.getName() + ".TAG.DATE.ARG";
	public static int sRESAULT_CODE_BACK = 201213;

	private Date mDate;

	public  static DateTimePicker newInstance(Date date)
	{
		Bundle arg = new Bundle();

		arg.putSerializable(sTAG_DATE_ARG, date);

		DateTimePicker f = new DateTimePicker();

		f.setArguments(arg);

		return f;
	}


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Bundle arg = getArguments();

		Date date = (Date)arg.getSerializable(sTAG_DATE_ARG);
		mDate = date;
	}


	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		return new AlertDialog.Builder(getActivity())
				.setTitle("Select Time or Date")
				.setItems(R.array.dialog_fragment_date_time_picker_array, new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialogInterface, int i)
					{
						if(0 == i)
						{
							CrimePickTimeFragment f = CrimePickTimeFragment.newInstance(mDate);

							f.setTargetFragment(getTargetFragment(), getTargetRequestCode());
							f.setOnKeyListener(new DialogInterface.OnKeyListener()
							{
								@Override
								public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent)
								{

									if(keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK)
									{

										getTargetFragment().onActivityResult(getTargetRequestCode(), sRESAULT_CODE_BACK, null);

										dialogInterface.dismiss();

										return true;
									}

									return false;
								}
							});
							f.show(getFragmentManager(), CrimePickTimeFragment.class.getName());
						}
						else if(1 == i)
						{
							CrimePickDateFragment f = CrimePickDateFragment.newInstance(mDate);
							f.setTargetFragment(getTargetFragment(), getTargetRequestCode());
							f.setOnKeyListener(new DialogInterface.OnKeyListener()
							{
								@Override
								public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent)
								{

									if(keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK)
									{

										getTargetFragment().onActivityResult(getTargetRequestCode(), sRESAULT_CODE_BACK, null);

										dialogInterface.dismiss();

										return true;
									}

									return false;
								}
							});
							f.show(getFragmentManager(), CrimePickDateFragment.class.getName());
						}
					}
				}).create();


	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		Log.d("test", ""+resultCode);
	}
}


















