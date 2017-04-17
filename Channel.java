
public class Channel{
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
	public void conn(String sensor){//not used yet
		
	}
	public void disc(int channelNo){//not used yet
		
	}
}