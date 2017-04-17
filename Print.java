import java.util.ArrayDeque;
import java.util.List;

public class Print {
	public Print(List<Event> runs){
		for(int i=1;i<runs.size();i++){
			System.out.println("Run " +(i)+" "+runs.get(i).getEventType()+" Event");
			System.out.println("NUM\tTime");
			ArrayDeque<Competitor> temp = runs.get(i).getCompleted();
			for(Competitor competitor:temp){
			System.out.print(competitor.getCompetitorNumber()<0 ? String.format("%05d", -competitor.getCompetitorNumber())+ "\t": competitor.getCompetitorNumber()+ "\t");
			System.out.println(competitor.dnf ? "DNF" : String.format("%.2f", competitor.getRaceTime()));
			}
		}
	}
}
