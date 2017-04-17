import java.text.SimpleDateFormat;
import java.util.Calendar;
/* This class is mainly about to set system time and get the race 
 * time via subtracting. Also it transfer the format to fit to the 
 * given units of time, which is "hundredth of seconds".
 */
public class Time {
	public static Time systemTime = new Time();
	String[] time;
	private double hour;
	private double minute;
	private double second;
	private double clock;
	
	public void setTime(){
		clock=systemTime.toSeconds(new SimpleDateFormat("HH:mm:ss.SSS").format(Calendar.getInstance().getTime()));
	}
	public void setTime(double time){
		clock = time;
	}
	public double getClockTime(){//get current time
		return clock;
	}
	public double getRunningTime(){//get the race time
		return clock;
	}
	public String toString(double time){//parse to String format
		int hour = (int) (time/3600);
		int min = (int) ((time-hour*3600)/60);
		double second = (double) (time-hour*3600-min*60);
		return String.format("%02d", hour)+":"+String.format("%02d", min)+":"+String.format("%.2f",second);
	}
	public double toSeconds(String time){//parse time to seconds format	
		this.time= time.split(":");
		hour=Integer.parseInt(this.time[0]);
		minute=Integer.parseInt(this.time[1]);
		second=Float.parseFloat(this.time[2]);
		return (double) (hour*3600+minute*60+second);
	}
}
