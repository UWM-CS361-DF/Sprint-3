
public class Channel{
	Sensor sensorType;
	public int channelNo;
	public boolean isArmed;

	public Channel(int channelNo){
		this.channelNo=channelNo;
		isArmed=false;
	}
	
	public boolean trig(){//judge whether the channels have been triggered
		if(!isArmed)
			return false;
		else
			return true;
	}
	public boolean tog(){//toggle the channels states
		isArmed=!isArmed;
		return isArmed;
	}
	public Sensor conn(String sensor){//not used yet
		sensorType=new Sensor(sensor);
		return sensorType;
	}
	public Sensor disc(){//not used yet
		sensorType=null;
		return sensorType;
	}
}