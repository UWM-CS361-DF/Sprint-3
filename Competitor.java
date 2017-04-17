/*
 The competitor class :)
 This class defines the competitor object 
 Each competitor has its nunber, start time, and finish time. 
 The competitor object has also a boolean value 'dnf' which indicates whether the competitor "did not finish" the race! 😹
 */

public class Competitor {
	int competitorNo;
	double startTime;
	double finishTime;
	boolean dnf;
	
	public Competitor(int competitorNo){
		this.competitorNo = competitorNo;
	}
	public void setStartTime(double time){
		startTime = time;
	}
	public void setFinishTime(double time){
		finishTime = time;
	}
	public double getStartTime(){
		return startTime;
	}
	public double getFinishTime(){
		return finishTime;
	}
	public double getRaceTime(){
		return finishTime-startTime;
	}
	public int getCompetitorNumber(){
		return competitorNo;
	}
	@Override
	public boolean equals(Object c){
		return ((Competitor)c).getCompetitorNumber()==this.competitorNo;
	}
}
