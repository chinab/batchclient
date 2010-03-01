package com.mindstatus.phy;

public class PhysiologyCycle
{
	private double physical;
	
	private double feeling;
	
	private double intelligence;
	
	private long lifeDays;
	
	public long getLifeDays()
	{
		return lifeDays;
	}

	public void setLifeDays(long lifeDays)
	{
		this.lifeDays = lifeDays;
	}

	public double getAverage()
	{
		return (physical+feeling+intelligence)/3.0;
	}

	public double getPhysical()
	{
		return physical;
	}

	public void setPhysical(double physical)
	{
		this.physical = physical;
	}

	public double getFeeling()
	{
		return feeling;
	}

	public void setFeeling(double feeling)
	{
		this.feeling = feeling;
	}

	public double getIntelligence()
	{
		return intelligence;
	}

	public void setIntelligence(double intelligence)
	{
		this.intelligence = intelligence;
	}
}
