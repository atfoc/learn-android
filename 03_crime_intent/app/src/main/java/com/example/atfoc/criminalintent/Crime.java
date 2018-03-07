package com.example.atfoc.criminalintent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Crime
{
	private UUID mId;
	private String mTitle;
	private Date mDate;
	private boolean mSolved;
	private SimpleDateFormat df;


	public Crime()
	{
		mId = UUID.randomUUID();
		mDate = new Date();
		mSolved = false;
		df = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
	}

	public void setTitle(String mTitle)
	{
		this.mTitle = mTitle;
	}

	public void setDate(Date mDate)
	{
		this.mDate = mDate;
	}

	public void setSolved(boolean mSolved)
	{
		this.mSolved = mSolved;
	}

	public UUID getId()
	{
		return mId;
	}

	public String getTitle()
	{
		return mTitle;
	}

	public Date getDate()
	{
		return mDate;
	}

	public boolean isSolved()
	{
		return mSolved;
	}

	public String getFormatDate()
	{
		return df.format(mDate);
	}
}
