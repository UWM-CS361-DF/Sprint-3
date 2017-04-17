import java.util.ArrayDeque;

public class ParIndEvent implements Event{
	public ArrayDeque<Competitor> startQueue;// = new ArrayDeque<Competitor>();
	public ArrayDeque<Competitor> finishQueue;// = new ArrayDeque<Competitor>();
	public ArrayDeque<Competitor> completed;// = new ArrayDeque<Competitor>();
	
	public ParIndEvent(){
		startQueue = new ArrayDeque<Competitor>();
		finishQueue = new ArrayDeque<Competitor>();
		completed = new ArrayDeque<Competitor>();
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
			Competitor temp=startQueue.remove();
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
			Competitor temp=finishQueue.remove();
			temp.setFinishTime(Time.systemTime.getRunningTime());
			completed.add(temp);
		}
	}
	@Override
	public void dnf(){
		Competitor temp;
		temp=finishQueue.remove();
		temp.dnf=true;
		completed.add(temp);
	}
	@Override
	public void cancel(){
		startQueue.addFirst(finishQueue.removeLast());
	}
	@Override
	public void clear(int num){
		
	}
	@Override
	public void swap(){
		
	}
	@Override
	public String getEventType(){
		return "PARIND";
	}
	@Override
	public ArrayDeque<Competitor> getCompleted(){
		return completed;
	}
}
