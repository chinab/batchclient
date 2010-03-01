package com.mindstatus.phy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.time.DateUtils;

public class PhysiologyCycleUtils
{
	public static PhysiologyCycle computePhysiologyCycle(Date birthday, Date now)
	{
		long remainDays = getRemainDays(birthday, now);
		PhysiologyCycle pc = new PhysiologyCycle();
		pc.setLifeDays(remainDays);
		pc.setPhysical(Double.valueOf(50.0 * Math.sin(2.0 * Math.PI / 23.0 * remainDays) + 50.0).doubleValue());
		pc.setIntelligence(Double.valueOf(50.0 * Math.sin(2.0 * Math.PI / 33.0 * remainDays) + 50.0).doubleValue());
		pc.setFeeling(Double.valueOf(50.0 * Math.sin(2.0 * Math.PI / 28.0 * remainDays) + 50.0).doubleValue());
		return pc;
	}

	public static PhysiologyCycle computePhysiologyCycle(Date birthday)
	{
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return computePhysiologyCycle(birthday, c.getTime());
	}

	private static long getRemainDays(Date dayA, Date dayB)
	{
		long l = dayB.getTime() - dayA.getTime();
		return l / 60 / 60 / 1000 / 24;
	}

	public static Date[] getSeriesMothDays(Date current)
	{
		List<Date> days = new ArrayList<Date>(30);
		for (int i = -15; i <= 15; i++)
		{
			days.add(DateUtils.addDays(current, i));
		}
		return (Date[]) days.toArray(new Date[days.size()]);
	}

	public static int computeAgeByBirthday(Date birthday)
	{
		 Calendar cal = Calendar.getInstance();

	        if (cal.before(birthday)) {
	            throw new IllegalArgumentException(
	                "The birthDay is before Now.It's unbelievable!");
	        }

	        int yearNow = cal.get(Calendar.YEAR);
	        int monthNow = cal.get(Calendar.MONTH);
	        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
	        cal.setTime(birthday);

	        int yearBirth = cal.get(Calendar.YEAR);
	        int monthBirth = cal.get(Calendar.MONTH);
	        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

	        int age = yearNow - yearBirth;

	        if (monthNow <= monthBirth) {
	            if (monthNow == monthBirth) {
	                //monthNow==monthBirth
	                if (dayOfMonthNow < dayOfMonthBirth) {
	                    age--;
	                } else {
	                    //do nothing
	                }
	            } else {
	                //monthNow>monthBirth
	                age--;
	            }
	        } else {
	            //monthNow<monthBirth
	            //donothing
	        }

	        return age;
	}
	
	public static void main(String[] args) throws Exception
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		Date birthday = sdf.parse("1982-08-11");
		Date now = sdf.parse("2009-04-15");
		PhysiologyCycle pc = computePhysiologyCycle(birthday, now);
		System.out.println(pc.getFeeling());
		System.out.println(pc.getIntelligence());
		System.out.println(pc.getPhysical());
	}
	
	
}
