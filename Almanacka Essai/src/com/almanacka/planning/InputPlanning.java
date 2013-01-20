package com.almanacka.planning;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class InputPlanning
{
	private List<InputLesson> _lessons;
	private HashSet<String> _monitorIntensities;
	
	public InputPlanning()
	{
		_lessons = new ArrayList<InputLesson>();
		_monitorIntensities = new HashSet<String>();
	}
	
	public InputPlanning( List<InputLesson> lessons, HashSet<String> monitorIntensities )
	{
		_lessons = lessons;
		_monitorIntensities = monitorIntensities;
	}
	
	public List<InputLesson> getLessons()
	{
		return _lessons;
	}
	
	public Boolean doesMonitorCanTeach(String monitorId, String intensityId)
	{
		return _monitorIntensities.contains( monitorId+"|"+intensityId );
	}
		
	/*public int computeCardinality()
	{		
		int cardinality = 1;
		for(InputLesson IL : _lessons)
		{
			IL.preprocessInput( this );
			cardinality = cardinality * IL.getIntensities().size() * IL.getMonitors().size() * IL.getHosts().size();
		}
		return cardinality;
    }*/
}