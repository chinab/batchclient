package com.vicutu.commons.crypto.nor;

import java.util.Calendar;
import java.util.*;

public class CurrentDateTime {
	private Calendar calendar = new GregorianCalendar();
	String currenttimeid;
	String currentdatetime;

	public String currentTimeID()
	{

		Integer DY = new Integer(calendar.get(Calendar.DAY_OF_MONTH));
		Integer MON = new Integer(calendar.get(Calendar.MONTH) + 1);
		Integer HR = new Integer(calendar.get(Calendar.HOUR_OF_DAY));
		Integer MIN = new Integer(calendar.get(Calendar.MINUTE));
		Integer SEC = new Integer(calendar.get(Calendar.SECOND));

		String HRS = HR.toString();
		String DAYS = DY.toString();
		String MONTHS = MON.toString();
		String MINUTES = MIN.toString();
		String SECONDS = SEC.toString();

		if (DAYS.length() <= 1)
			DAYS = "0" + DAYS;
		if (HRS.length() <= 1)
			HRS = "0" + HRS;
		if (MONTHS.length() <= 1)
			MONTHS = "0" + MONTHS;
		if (MINUTES.length() <= 1)
			MINUTES = "0" + MINUTES;
		if (SECONDS.length() <= 1)
			SECONDS = "0" + SECONDS;

		currenttimeid = calendar.get(Calendar.YEAR) + MONTHS + DAYS + HRS
				+ MINUTES + SECONDS;

		return currenttimeid;
	}

	public String currentDateTime() {

		Integer DY = new Integer(calendar.get(Calendar.DAY_OF_MONTH));
		Integer MON = new Integer(calendar.get(Calendar.MONTH) + 1);
		Integer HR = new Integer(calendar.get(Calendar.HOUR_OF_DAY));
		Integer MIN = new Integer(calendar.get(Calendar.MINUTE));
		Integer SEC = new Integer(calendar.get(Calendar.SECOND));

		String HRS = HR.toString();
		String DAYS = DY.toString();
		String MONTHS = MON.toString();
		String MINUTES = MIN.toString();
		String SECONDS = SEC.toString();

		if (DAYS.length() <= 1)
			DAYS = "0" + DAYS;
		if (HRS.length() <= 1)
			HRS = "0" + HRS;
		if (MONTHS.length() <= 1)
			MONTHS = "0" + MONTHS;
		if (MINUTES.length() <= 1)
			MINUTES = "0" + MINUTES;
		if (SECONDS.length() <= 1)
			SECONDS = "0" + SECONDS;

		currentdatetime = calendar.get(Calendar.YEAR) + "-" + MONTHS + "-"
				+ DAYS + " " + HRS + ":" + MINUTES + ":" + SECONDS;
		return currentdatetime;
	}

	public String cDate() {

		Integer DY = new Integer(calendar.get(Calendar.DAY_OF_MONTH));
		Integer MON = new Integer(calendar.get(Calendar.MONTH) + 1);
		Integer HR = new Integer(calendar.get(Calendar.HOUR_OF_DAY));
		Integer MIN = new Integer(calendar.get(Calendar.MINUTE));
		Integer SEC = new Integer(calendar.get(Calendar.SECOND));

		String HRS = HR.toString();
		String DAYS = DY.toString();
		String MONTHS = MON.toString();
		String MINUTES = MIN.toString();
		String SECONDS = SEC.toString();

		if (DAYS.length() <= 1)
			DAYS = "0" + DAYS;
		if (HRS.length() <= 1)
			HRS = "0" + HRS;
		if (MONTHS.length() <= 1)
			MONTHS = "0" + MONTHS;
		if (MINUTES.length() <= 1)
			MINUTES = "0" + MINUTES;
		if (SECONDS.length() <= 1)
			SECONDS = "0" + SECONDS;

		currentdatetime = calendar.get(Calendar.YEAR) + "-" + MONTHS + "-"
				+ DAYS;
		return currentdatetime;
	}

	public String SQLDate() {

		Integer DY = new Integer(calendar.get(Calendar.DAY_OF_MONTH));
		Integer MON = new Integer(calendar.get(Calendar.MONTH) + 1);
		Integer HR = new Integer(calendar.get(Calendar.HOUR_OF_DAY));
		Integer MIN = new Integer(calendar.get(Calendar.MINUTE));
		Integer SEC = new Integer(calendar.get(Calendar.SECOND));

		String HRS = HR.toString();
		String DAYS = DY.toString();
		String MONTHS = MON.toString();
		String MINUTES = MIN.toString();
		String SECONDS = SEC.toString();

		if (DAYS.length() <= 1)
			DAYS = "0" + DAYS;
		if (HRS.length() <= 1)
			HRS = "0" + HRS;
		if (MONTHS.length() <= 1)
			MONTHS = "0" + MONTHS;
		if (MINUTES.length() <= 1)
			MINUTES = "0" + MINUTES;
		if (SECONDS.length() <= 1)
			SECONDS = "0" + SECONDS;

		currentdatetime = calendar.get(Calendar.YEAR) + "-" + MONTHS + "-"
				+ DAYS + " " + HRS + ":" + MINUTES + ":" + SECONDS;
		return currentdatetime;
	}

	public String timeID() // ���ַ��ص�ǰʱ�䣬�м�û�зָ�� ������Ϊ��λ
	{

		Integer DY = new Integer(calendar.get(Calendar.DAY_OF_MONTH));
		Integer MON = new Integer(calendar.get(Calendar.MONTH) + 1);
		Integer HR = new Integer(calendar.get(Calendar.HOUR_OF_DAY));
		Integer MIN = new Integer(calendar.get(Calendar.MINUTE));
		Integer SEC = new Integer(calendar.get(Calendar.SECOND));
		Integer MSEC = new Integer(calendar.get(Calendar.MILLISECOND));

		String HRS = HR.toString();
		String DAYS = DY.toString();
		String MONTHS = MON.toString();
		String MINUTES = MIN.toString();
		String SECONDS = SEC.toString();
		String MILLSEC = MSEC.toString();

		if (DAYS.length() <= 1)
			DAYS = "0" + DAYS;
		if (HRS.length() <= 1)
			HRS = "0" + HRS;
		if (MONTHS.length() <= 1)
			MONTHS = "0" + MONTHS;
		if (MINUTES.length() <= 1)
			MINUTES = "0" + MINUTES;
		if (SECONDS.length() <= 1)
			SECONDS = "0" + SECONDS;
		if (MILLSEC.length() <= 1)
			MILLSEC = "0" + MILLSEC;

		currenttimeid = calendar.get(Calendar.YEAR) + MONTHS + DAYS + HRS
				+ MINUTES + SECONDS + MILLSEC;

		return currenttimeid;
	}

}
