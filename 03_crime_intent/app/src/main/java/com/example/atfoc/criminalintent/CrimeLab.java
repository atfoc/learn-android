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
		sCrimeLab = (null == sCrimeLab) ? new CrimeLab(c) : sCrimeLab;
		return sCrimeLab;
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

	public Crime getCrime(int index)
	{
		return mCrimes.get(index);
	}

	public int getSize()
	{
		return mCrimes.size();
	}

	public int getIndexFromId(UUID id)
	{
		int i = 0;
		for(Crime c : mCrimes)
		{
			if(c.getId().equals(id))
			{
				return i;
			}
			++i;
		}

		return -1;
	}
}
