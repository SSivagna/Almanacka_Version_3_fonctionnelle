package com.almanacka.planning;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import com.mysql.ConnectionToDB;
//import com.mysql.CreateInputLesonFromDB;

public class OutputLesson
{
	private InputLesson _input;
/*	private String _lessonId;
	private Boolean _isLocked;
	private String _placeWrapId;
	private Date _bedDate;
	private Date _endDate;*/
	private String _intensityId;
	private String _monitorId;
	private String _host;
	
	public OutputLesson (InputLesson input)
	{
		if(_input != null && input.getIsLocked() == true)
		{
			_input = input;
			_intensityId = getInput().getIntensities().get(0).GetChoiceId();
			_monitorId = getInput().getMonitors().get(0).GetChoiceId();
		/*	_hosts = new ArrayList<String>();
			
			for (int i=0; i< getInput().getHosts().size() - 1; i++)
			{
				_hosts.add(getInput().getHosts().get(i).GetChoiceId());
			}*/
			_host = getInput().getHosts().get(0).GetChoiceId();
		}
	}
	
	//Ctor pour méthode CreateOuputPlanning
	public OutputLesson (InputLesson input, int index)
	{
		if(_input != null && input.getIsLocked() == true)
		{
			_input = input;
			_intensityId = getInput().getIntensities().get(index).GetChoiceId();
			_monitorId = getInput().getMonitors().get(index).GetChoiceId();
		/*	_hosts = new ArrayList<String>();
			
			for (int i=0; i< getInput().getHosts().size() - 1; i++)
			{
				_hosts.add(getInput().getHosts().get(i).GetChoiceId());
			}*/
			_host = getInput().getHosts().get(index).GetChoiceId();
		}
	}
	
	public InputLesson getInput()
	{
		return _input;
	}
	
	public String getLessonId()
	{
		return getInput().getLessonId();
	}
	
	public Boolean getIsLocked()
	{
		return getInput().getIsLocked();
	}
	
	public String getPlaceWrapId()
	{
		return getInput().getPlaceWrapId();
	}
	
	public Date getBegDate()
	{
		return getInput().getBegDate();
	}
	
	public Date getEndDate()
	{
		return getInput().getEndDate();
	}
	
	public String getIntensityId()
	{
		return _intensityId;
	}
	
	public String getMonitorId()
	{
		return _monitorId;
	}
	
	/*public List<String> getHosts()
	{
		return _hosts;
	}*/
	
	public String getHostd()
	{
		return _host;
	}
	
	public List<OutputPlanning> optimize(InputPlanning input, int nbMaxSolution)
	{
		List<OutputPlanning> listOutputPlanning=new ArrayList<OutputPlanning>();
		
		for(int p = 0; p<nbMaxSolution ; p++)
		{
			listOutputPlanning.add( CreateOutputPlanning(input, p) );
		}
		return listOutputPlanning;
	}
	
	static public OutputPlanning CreateOutputPlanning (InputPlanning input, int p)
	{
		List<OutputLesson> listOutputLesson = new ArrayList<>();
		for(int i= 0; i< input.getLessons().size(); i++)
		{
			OutputLesson ol = new OutputLesson(input.getLessons().get(i), p);
			
			listOutputLesson.add(ol);
		}
		OutputPlanning op = new OutputPlanning( 0, listOutputLesson );
		return op;
	}
}