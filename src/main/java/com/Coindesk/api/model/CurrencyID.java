package com.Coindesk.api.model;


public class CurrencyID {
	private String currencyid;

	public String getCurrencyid ()
	{
		return currencyid;
	}

	public void setCurrencyid (String currencyid)
	{
		this.currencyid = currencyid;
	}

	@Override
	public String toString()
	{
		return "ClassPojo [currencyid = "+currencyid+"]";
	}
}