package com.almanacka.planning;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
	
/*	public InputLesson getInput(int i)
	{
		return this._lessons.get(i);
	}
	*/
	public Boolean doesMonitorCanTeach(String monitorId, String intensityId)
	{
		return _monitorIntensities.contains( monitorId+"|"+intensityId );
	}
		
	public int computeCardinality()
	{		
		int cardinality = 1;
		for(InputLesson IL : _lessons)
		{
			IL.preprocessInput( this );
			cardinality = cardinality * IL.getIntensities().size() * IL.getMonitors().size() * IL.getHosts().size();
		}
		return cardinality;
    }
	
	/*
	 * Display one element from _monitorIntensities
	 */
	@Override
	public String toString() 
	{
		return "InputPlanning [_monitorIntensities=" + _monitorIntensities + "]";
	}
	
	/*
	 * Display every elements from _monitorIntensities
	 */
	public void PrintInfosHashset(HashSet<String> hashset)
	{	
		Iterator<String> li = hashset.iterator();
		
		while(li.hasNext())
		{
			System.out.println(li.next().toString());
		}
	}

	/*
	 *  Display all informations about an InputLesson
	 */
	public void PrintInfos(List<InputLesson> list)
	{	
		ListIterator<InputLesson> li = list.listIterator();
		while(li.hasNext())
		{
			System.out.println(li.next().toString());
		}
	}
	
	/*
	 * Display all elements from _ monitorIntensities and _lessons
	 */
	public void PrintInfos()
	{
		PrintInfos(_lessons);
		System.out.println("");
		PrintInfosHashset(_monitorIntensities);
	}
}