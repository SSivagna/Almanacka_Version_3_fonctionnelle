package com.almanacka.planning;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import com.mysql.ConnectionToDB;
//import com.mysql.CreateInputLesonFromDB;

public class OutputLesson
{
	private InputLesson _input;
	private String _lessonId;
	private Boolean _isLocked;
	private String _placeWrapId;
	private Date _begDate;
	private Date _endDate;
	private String _intensityId;
	private String _monitorId;
	private String _host;
	
	/*public OutputLesson (InputLesson input)
	{
		if(_input != null /*&& input.getIsLocked() == true )
		{
			_input = input;
			_lessonId=_input.getLessonId();
			_isLocked=input.getIsInputLocked();
			_placeWrapId=_input.getPlaceWrapId();
			_begDate=_input.getBegDate();
			_endDate=_input.getEndDate();
			_intensityId = _input.getIntensities().get(0).GetChoiceId();
			_monitorId = _input.getMonitors().get(0).GetChoiceId();
			_host = _input.getHosts().get(0).GetChoiceId();
		}
		else 
			System.out.println("Argument passé est nul!!!!");
	}*/
	
	//Ctor pour méthode CreateOuputPlanning
	public OutputLesson (InputLesson input, int index)
	{
		/*if(_input != null /*&& input.getIsLocked() == true)
		{*/
			_input = input;
			_lessonId=_input.getLessonId();
			_isLocked=input.getIsInputLocked();
			_placeWrapId=_input.getPlaceWrapId();
			_begDate=_input.getBegDate();
			_endDate=_input.getEndDate();
			_intensityId = _input.getIntensities().get(index).GetChoiceId();
			_monitorId = _input.getMonitors().get(index).GetChoiceId();
			_host = _input.getHosts().get(index).GetChoiceId();
		/*}
		else 
			System.out.println("Argument passé est nul!!!!");*/
	}
	
	public InputLesson getInput()
	{
		return _input;
	}
	
	public String getLessonId()
	{
		return _lessonId;
	}
	
	public Boolean getIsLocked()
	{
		return _isLocked;
	}
	
	public String getPlaceWrapId()
	{
		return _placeWrapId;
	}
	
	public Date getBegDate()
	{
		return _begDate;
	}
	
	public Date getEndDate()
	{
		return _endDate;
	}
	
	public String getIntensityId()
	{
		return _intensityId;
	}
	
	public String getMonitorId()
	{
		return _monitorId;
	}
	
	public String getHostd()
	{
		return _host;
	}
	
	
	
	@Override
	public String toString() {
		return "OutputLesson [_input=" + _input + ", _lessonId=" + _lessonId
				+ ", _isLocked=" + _isLocked + ", _placeWrapId=" + _placeWrapId
				+ ", _begDate=" + _begDate + ", _endDate=" + _endDate
				+ ", _intensityId=" + _intensityId + ", _monitorId="
				+ _monitorId + ", _host=" + _host + "]";
	}

	public List<OutputPlanning> optimize(InputPlanning input, int nbMaxSolution)
	{
		List<OutputPlanning> listOutputPlanning=new ArrayList<OutputPlanning>();
		
		for(int p = 0; p<nbMaxSolution ; p++)
		{
			listOutputPlanning.add( createOutputPlanning(input, p) );
		}
		return listOutputPlanning;
	}
	
	//on prend 5 elements de InputPlanning
	static public OutputPlanning createOutputPlanning (InputPlanning input, int p)
	{
		List<OutputLesson> listOutputLesson = new ArrayList<>();
		for(int i= 0; i< input.getLessons().size(); i++)
		{
			listOutputLesson.add( new OutputLesson(input.getLessons().get(i), p));
						
			System.out.println(listOutputLesson.get(i).toString());
			System.out.println("   ");
		}
		return new OutputPlanning( 0, listOutputLesson );
	}
}