package com.example.atfoc.criminalintent;


import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

public class CrimeLab
{
	private static CrimeLab sCrimeLab = null;

	private ArrayList<Crime> mCrimes;
	private Context mContext;

	private CrimeLab(Context mContext)
	{
		mCrimes = new ArrayList<>();
		this.mContext = mContext;
	}

	public static CrimeLab getInstance(Context c)
	{
		return (null == sCrimeLab) ? new CrimeLab(c) : sCrimeLab;
	}

	public void addCrime(Crime c)
	{
		mCrimes.add(c);
	}

	public Crime getCrime(UUID u)
	{
		for(Crime c : mCrimes)
		{
			if(c.getId().equals(u))
			{
				return c;
			}
		}

		return null;
	}

	public ArrayList<Crime> getmCrimes()
	{
		return mCrimes;
	}
}
