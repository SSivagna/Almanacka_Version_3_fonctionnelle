package com.almanacka.planning;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OutputLesson
{
	private InputLesson _input;
	private String _lessonId;
	private Boolean _isLocked;
	private String _placeWrapId;
	private Date _bedDate;
	private Date _endDate;
	private String _intensityId;
	private String _monitorId;
	private List<String> _hosts;
	
	public OutputLesson (InputLesson input)
	{
		if(_input != null && input.getIsLocked() == true)
		{
			_input = input;
			_intensityId = getInput().getIntensities().get(0).GetChoiceId();
			_monitorId = getInput().getMonitors().get(0).GetChoiceId();
			_hosts = new ArrayList<String>();
			for (int i=0; i< getInput().getHosts().size() - 1; i++)
			{
				_hosts.add(getInput().getHosts().get(i).GetChoiceId());
			}
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
	
	public List<String> getHosts()
	{
		return _hosts;
	}
	
	public List<OutputPlanning> optimize(InputPlanning input/*, int nbMaxSolution*/)
	{	
		OutputLesson a = new OutputLesson(input.getLessons().get(0));
		OutputLesson b = new OutputLesson(input.getLessons().get(1));
		OutputLesson c = new OutputLesson(input.getLessons().get(2));
		
		
		return null;
	}
}