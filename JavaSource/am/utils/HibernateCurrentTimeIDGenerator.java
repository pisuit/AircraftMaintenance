package am.utils;

import java.io.Serializable;
import java.util.Calendar;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class HibernateCurrentTimeIDGenerator implements IdentifierGenerator {
	private static int microValue = 0;
	public Serializable generate(SessionImplementor arg0, Object arg1) throws HibernateException {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		return Long.valueOf(getYear(cal)+getMonth(cal)+getDay(cal)+getHour(cal)+getMinute(cal)+getSecond(cal)+ getMillis(cal)+getMicro());
	}
	
	private long getYear(Calendar aCal) {
		return ((long)aCal.get(Calendar.YEAR))*100*100*100*100*100*1000*100;
	}

	private long getMonth(Calendar aCal) {
		return ((long)aCal.get(Calendar.MONTH)+1)*100*100*100*100*1000*100;
	}

	private long getDay(Calendar aCal) {
		return ((long)aCal.get(Calendar.DAY_OF_MONTH))*100*100*100*1000*100;
	}

	private long getHour(Calendar aCal) {
		return ((long)aCal.get(Calendar.HOUR_OF_DAY))*100*100*1000*100;
	}

	private long getMinute(Calendar aCal) {
		return ((long)aCal.get(Calendar.MINUTE))*100*1000*100;
	}

	private long getSecond(Calendar aCal) {
		return ((long)aCal.get(Calendar.SECOND))*1000*100;
	}

	private long getMillis(Calendar aCal) {
		return ((long)aCal.get(Calendar.MILLISECOND))*100;
	}

	private long getMicro() {
		if (microValue == 100 ) microValue = 0;
		return microValue++;
	}

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		//System.out.println(cal.getTimeInMillis());
		//double nano = System.nanoTime();
		//System.out.println(nano);
		//System.out.println(nano % Math.pow(10, 6)/1000);
		HibernateCurrentTimeIDGenerator iden =   new HibernateCurrentTimeIDGenerator();
//		System.out.println(iden.getMicro());
//		System.out.println(iden.getMillis(cal));
//		System.out.println(iden.getSecond(cal));
//		System.out.println(iden.getMinute(cal));
//		System.out.println(iden.getHour(cal));
//		System.out.println(iden.getDay(cal));
//		System.out.println(iden.getMonth(cal));
//		System.out.println(iden.getYear(cal));
		System.out.println(iden.generate(null, null));
		

	}
}
