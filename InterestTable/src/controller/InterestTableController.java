package controller;

import java.text.NumberFormat;

import model.Interest;

public class InterestTableController 
{
	private int years, intType;
	private double rate, principal;
	private String header;
	
	public InterestTableController(int years, double principal, double rate, int intType)
	{
		this.years = years;
		this.rate = rate;
		this.principal = principal;
		this.intType = intType;
		header = "Principal: " + NumberFormat.getCurrencyInstance().format(principal) + " | Rate: " 
				+ rate + " | Number of Years: " + years + "\nYear | ";
		if (intType == 1)
		{
			header += "Simple Interest Amount\n";
		}
		else if (intType == 2)
		{
			header += "Compound Interest Amount\n";
		}
		else
		{
			header += "Simple Interest Amount | Compund Interest Amount\n";
		}
	}
	public String getTable()
	{
		String toReturn = header;
		Interest toManip = new Interest(years, principal, rate, intType);
		String [][] table = toManip.tableMaker();
		for (int i = 0; i < table[0].length; i++)
		{
			for (int j = 0; j < table.length; j++) 
			{
				toReturn += table[j][i];
				if (j < table.length - 1)
				{
					toReturn += "-->";
				}
				else
				{
					toReturn += "\n";
				}
			}
		}
		return toReturn;
	}
}