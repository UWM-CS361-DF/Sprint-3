import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

//******************************************************
// The ChronoInterface class acts as a literal interface
// for the ChronoTimer. This has all of the methods that
// could be directly related to the interface buttons to 
// generate a command.(i.e the tog "button" is pressed
// it relates to tog() method. The chronoInterface then 
// determines what to do with the information provided
//******************************************************

public class ChronoInterface {
	public static ChronoInterface chronoTimer;// = new ChronoInterface();
	List<Channel> channels = new ArrayList<Channel>(9);//0 will be an empty channel location for ease of assigning
	Power power = new Power();
	boolean runInProgress=true;
	int runNum=1;
	ArrayList<Event> runs= new ArrayList<Event>();
	Gson g = new Gson();
	String json;
	GUI gui;

	public ChronoInterface(GUI gui){
		channels.add(0,null);
		for(int i=1;i<9;i++){
			channels.add(i,new Channel(i));
		}
		runs.add(0,null);
		runs.add(runNum, new IndEvent());
		Time.systemTime.setTime();
		this.gui=gui;
	}
	public void power(){
		println(power.power() ? "Power On" : "Power Off");		
	}
	public void time(String time){
		if(power.powerStatus){
			Time.systemTime.setTime(Time.systemTime.toSeconds(time));
			println("Set Time to "+ Time.systemTime.toString(Time.systemTime.getRunningTime()));
		}
	}
	public void dnf(){
		if(power.powerStatus)
			runs.get(runNum).dnf();
	}
	public void cancel(){
		if(power.powerStatus)
			runs.get(runNum).cancel();
	}
	public void tog(String channel){
		if(power.powerStatus)
			println(channels.get(Integer.parseInt(channel)).tog() ? "Channel "+channel+" is Armed" : "Channel "+channel+" is not Armed");
	}
	public void trig(String channel){
		if(power.powerStatus)
			if(channels.get(Integer.parseInt(channel)).trig()){
				println("Triggered Channel "+channel);
				if(Integer.parseInt(channel)%2==0)
					runs.get(runNum).finish();
				else
					runs.get(runNum).start();
			}
			else
				println("Unable to Trigger Channel "+channel);		
	}
	public void start(){
		if(power.powerStatus)
			trig("1");
	}
	public void finish(){
		if(power.powerStatus)
			trig("2");
	}
	public void conn(String sensor, String channel){
		if(power.powerStatus)
			channels.get(Integer.parseInt(channel)).conn(sensor);
	}
	public void disc(String channel){
		if(power.powerStatus)
			channels.get(Integer.parseInt(channel)).disc(Integer.parseInt(channel));
	}
	public void event(String type){
		if(power.powerStatus)
		runs.remove(runNum);
		switch (type) {
		case "IND":
			runs.add(runNum, new IndEvent());
			println("Created "+type+" event");
			return;
		case "PARIND":
			runs.add(runNum, new ParIndEvent());
			println("Created "+type+" event");
			return;
		case "GRP":
			break;
		case "PARGRP":
			break;
		}
		println("Failed to create "+type+" event");
	}
	public void newrun(){
		if(power.powerStatus)
			if(!runInProgress){
				runNum++;
				runs.add(runNum, new IndEvent());
				runInProgress=true;
				println("Created Run "+runNum);
			}
			else
				println("Run "+runNum+" Still In Progress");
	}
	public void endrun() throws Exception{
		if(power.powerStatus){
			new Export(runs.get(runNum),runNum);
			runInProgress=false;
			println("Ended Run "+(runNum));
		}
	}
	public void print(){
		if(power.powerStatus)
			new Print(runs);
	}
	public void export(String run) throws Exception{
		if(power.powerStatus){
			new Export(runs.get(runNum),runNum);
			runInProgress=false;
		}
	}
	public void num(String number){
		if(power.powerStatus)
			println(runs.get(runNum).add(Integer.parseInt(number)) ? "Added "+number+" to Race queue": "Failed to add "+number+" to Race queue");
	}
	public void clr(String number){
		if(power.powerStatus)
			runs.get(runNum).clear(Integer.parseInt(number));
	}
	public void swap(){
		if(power.powerStatus)
			runs.get(runNum).swap();
	}
	public void println(String out){
		System.out.println(out);
		//gui.output(out+'\n');
	}
	public void print(String out){
		System.out.print(out);
		//gui.output(out);
	}
	public String displayRun(){
		return runs.get(runNum).displayUI();
	}
}