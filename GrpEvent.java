import java.util.ArrayDeque;

public class GrpEvent implements Event{
	public ArrayDeque<Competitor> startQueue;// = new ArrayDeque<Competitor>();
	public ArrayDeque<Competitor> finishQueue;// = new ArrayDeque<Competitor>();
	public ArrayDeque<Competitor> completed;// = new ArrayDeque<Competitor>();
	private double startTime;

	public GrpEvent(){
		startQueue = new ArrayDeque<Competitor>();
		finishQueue = new ArrayDeque<Competitor>();
		completed = new ArrayDeque<Competitor>();
		startTime=0;
	}
	@Override
	public boolean add(int competitorNo) {
		Competitor temp=new Competitor(competitorNo);
		if(completed.contains(temp)||ChronoInterface.chronoTimer.runInProgress)
			return false;
		temp=completed.remove();
		if(temp.getCompetitorNumber()<0){
			temp.setCompetitorNumber(competitorNo);
			completed.add(temp);
			return true;
		}
		completed.push(temp);
		return false;
	}

	@Override
	public void start() {
		if(startTime==0)
			startTime=Time.systemTime.getRunningTime();
	}

	@Override
	public void finish() {
		Competitor temp=new Competitor(-(completed.size()+1));
		temp.setFinishTime(Time.systemTime.getRunningTime());
		temp.setStartTime(startTime);
		completed.add(temp);
	}

	@Override
	public void dnf() {
		Competitor temp=new Competitor(-(completed.size()+1));
		temp.dnf=true;
		completed.add(temp);
	}

	@Override
	public void cancel() {	
	}

	@Override
	public void clear(int num) {
	}

	@Override
	public boolean swap() {
		return false;	
	}

	@Override
	public String getEventType() {
		return "GRP";
	}

	@Override
	public ArrayDeque<Competitor> getCompleted() {
		return completed;
	}

}
