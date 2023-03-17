package model;

import java.text.NumberFormat;

public class Interest 
{
	private int years, intType;
	private double rate, principal;
	
	public Interest(int years, double principal, double rate, int intType)
	{
		this.years = years;
		this.rate = rate;
		this.principal = principal;
		this.intType = intType;
	}
	public String[][] tableMaker()
	{
		if (intType == 1)
		{
			String[][] table = new String[2][years];
			table [0] = popYearsRow();
			table [1] = simpleInterestCalc();
			return table;
		}
		else if (intType == 2)
		{
			String[][] table = new String[2][years];
			table [0] = popYearsRow();
			table [1] = compoundInterestCalc();
			return table;
		}
		else
		{
			String[][] table = new String[3][years];
			table [0] = popYearsRow();
			table [1] = simpleInterestCalc();
			table [2] = compoundInterestCalc();
			return table;
		}
	}
	private String[] simpleInterestCalc()
	{
		String[] toReturn = new String[years];
		double value = 0.0;
		for (int i = 0; i < years; i++)
		{
			value = (principal + (principal * (rate/100) * (i + 1)));
			toReturn[i] = NumberFormat.getCurrencyInstance().format(value);
		}
		return toReturn;
	}
	private String[] compoundInterestCalc()
	{
		String[] toReturn = new String[years];
		double value = 0.0;
		for (int i = 0; i < years; i++)
		{
			value = (principal * Math.pow((1 + rate/100), (i + 1)));
			toReturn[i] = NumberFormat.getCurrencyInstance().format(value);
		}
		return toReturn;
	}
	private String[] popYearsRow()
	{
		String[] toReturn = new String[years];
		for (int i = 0; i < years; i++)
		{
			toReturn[i] = "" + (i + 1);
		}
		return toReturn;
	}
}
