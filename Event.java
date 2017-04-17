import java.util.ArrayDeque;

public interface Event{
	public ArrayDeque<Competitor> startQueue = new ArrayDeque<Competitor>();
	public ArrayDeque<Competitor> finishQueue = new ArrayDeque<Competitor>();
	public ArrayDeque<Competitor> completed = new ArrayDeque<Competitor>();
	public boolean add(int competitorNo);
	public void start();
	public void finish();
	public void dnf();
	public void cancel();
	public void clear(int num);
	public boolean swap();
	public String getEventType();
	public ArrayDeque<Competitor> getCompleted();
}
