import java.util.ArrayList;

/*
 The IndEvent class :) 
 This class defines the Individual Event object 🎎🤡
 
 */
public class IndEvent implements Event{//extends ChronoInterface implements Event{
	public ArrayList<Competitor> startQueue;// = new ArrayDeque<Competitor>();
	public ArrayList<Competitor> finishQueue;// = new ArrayDeque<Competitor>();
	public ArrayList<Competitor> completed;// = new ArrayDeque<Competitor>();
	
	public IndEvent(){
		startQueue = new ArrayList<Competitor>();
		finishQueue = new ArrayList<Competitor>();
		completed = new ArrayList<Competitor>();
	}
	
	// if the start queue of the race is not empty
	// take out the head of the start queue
	// set the start time of the competitor
	// put the competitor to the finish queue
	@Override
	public boolean add(int competitorNo){
		Competitor temp=new Competitor(competitorNo);
		if(startQueue.contains(temp)||finishQueue.contains(temp)||completed.contains(temp))
			return false;
		startQueue.add(new Competitor(competitorNo));
		return true;
	}
	@Override
	public void start() {
		if(!startQueue.isEmpty()){
			Competitor temp=startQueue.remove(0);
			temp.setStartTime(Time.systemTime.getRunningTime());
			finishQueue.add(temp);
		}
	} 
	// if the finish queue is not empty
	// take out the head of the start queue
	// set the finish time of the competitor
	// put the competitor to the completed queue 
	@Override
	public void finish() {
		if(!finishQueue.isEmpty()){
			Competitor temp=finishQueue.remove(0);
			temp.setFinishTime(Time.systemTime.getRunningTime());
			completed.add(temp);
		}
	}
	@Override
	public void dnf(){
		Competitor temp;
		temp=finishQueue.remove(0);
		temp.dnf=true;
		completed.add(temp);
	}
	@Override
	public void cancel(){
		startQueue.add(0,finishQueue.remove(finishQueue.size()-1));
	}
	@Override
	public String getEventType(){
		return "IND";
	}
	@Override
	public ArrayList<Competitor> getCompleted(){
		return completed;
	}
	@Override
	public void clear(int num){
		Competitor temp=new Competitor(num);
		startQueue.remove(temp);
	}
	@Override
	public boolean swap(){
		if(finishQueue.size()<2)
			return false;
		Competitor temp1=finishQueue.remove(0);
		Competitor temp2=finishQueue.remove(0);
		finishQueue.add(0,temp1);
		finishQueue.add(0,temp2);
		return true;
	}
	public String displayUI(){
		String starting="Racers Queued\n- - - - - - - - - - - - - - - - - - - - -";
		for(int i=2; i>-1;i--){
			if(startQueue.size()>i)
				starting=starting+'\n'+startQueue.get(i).getCompetitorNumber()+"\t00:00.00";
		}
		starting+=">"+'\n';
		
		String running="\nRunning Times\n- - - - - - - - - - - - - - - - - - - - - ";
		for(int i=0; i<finishQueue.size(); i++){
			running=running+'\n'+finishQueue.get(i).getCompetitorNumber()+'\t'+String.format("%.2f", (Time.systemTime.getTime()-finishQueue.get(i).getStartTime()));
		}
		
		String finished="\n\nFinished Times\n- - - - - - - - - - - - - - - - - - - - - ";
		if(!completed.isEmpty())
				finished="\n\nFinished Times\n- - - - - - - - - - - - - - - - - - - - - \n"+completed.get(completed.size()-1).getCompetitorNumber()+'\t'+(completed.get(completed.size()-1).dnf ? "DNF":String.format("%.2f", (completed.get(completed.size()-1).getRaceTime())));
		
		return starting+running+finished;
	}
}
