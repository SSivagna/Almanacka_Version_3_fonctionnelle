package com.almanacka.planning;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OutputLesson
{
	private InputLesson _input;
	private String _lessonId;
	private Byte _isLocked;
	private String _placeWrapId;
	private Date _begDate;
	private Date _endDate;
	private String _intensityId;
	private String _monitorId;
	private String _host;
	
	public OutputLesson (InputLesson input)
	{
		_input = input;
		_lessonId =	input.getLessonId();
		_isLocked =	input.getIsInputLocked();
		_placeWrapId =	input.getPlaceWrapId();
		_begDate =	input.getBegDate();
		_endDate =	input.getEndDate();
		_intensityId =  input.getIntensities().get(0);	/* input.getIntensities();*/
		_monitorId = input.getMonitors().get(0);	/* input.getMonitors();*/
		_host = input.getHosts().get(0); /* input.getHosts();*/
	}
	
	public InputLesson getInput()
	{
		return _input;
	}
	
	public String getLessonId()
	{
		return _lessonId;
	}
	
	public Byte getIsLocked()
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
	
	public  String getMonitorId()
	{
		return _monitorId;
	}
	
	public  String getHostd()
	{
		return _host;
	}
	
	@Override
	public String toString() {
		return "OutputLesson 1 [_lessonId=" + _lessonId
				+ ", _isLocked=" + _isLocked + ", _placeWrapId=" + _placeWrapId
				+ ", _begDate=" + _begDate + ", _endDate=" + _endDate
				+ ", _intensityId=" + _intensityId + ", _monitorId="
				+ _monitorId + ", _host=" + _host + "]";
	}
	
	public List<OutputPlanning> optimize(InputPlanning input, int nbMaxSolution)
	{
		List<OutputPlanning> listOutputPlanning = new ArrayList<OutputPlanning>();
		
		for(int p = 0; p<nbMaxSolution ; p++)
		{
			listOutputPlanning.add( createOutputPlanning(input, p) );
		}
		return listOutputPlanning;
	}
	
	static public OutputPlanning createOutputPlanning (InputPlanning input, int p)
	{
		List<OutputLesson> listOutputLesson = new ArrayList<>();
		for(int i = 0; i < input.getLessons().size(); i++)
		{
			listOutputLesson.add( new OutputLesson(input.getLessons().get(i))); 
			
	//		System.out.println(listOutputLesson.get(i));
		}
		return new OutputPlanning( 0, listOutputLesson );
	}
}