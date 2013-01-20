package com.almanacka.planning;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OutputPlanning 
{
	private double _cost;
	private List<OutputLesson> _lessons;
	
	public OutputPlanning(double cost)
	{
		_cost = cost;
		_lessons = new ArrayList<OutputLesson>();
	}
	
	// fonction qui copie les éléments de la liste
	
	public OutputPlanning(double cost, List<OutputLesson> listOutputlessons)
	{
		_cost = cost;
		_lessons =listOutputlessons;
	}
	
	public double getCost()
	{
		return _cost;
	}
	
	public List<OutputLesson> getLessons()
	{
		return _lessons;
	}
	
	public String getOutputLessonFromLessonId(int index)
	{
		Iterator<OutputLesson> l = _lessons.iterator();
		while( l.hasNext() )
		{
			if( l.next().getLessonId().equals(String.valueOf(index)) ) 
			{
				System.out.println(l.next().getHostd());
				return l.next().getHostd();
			}
		}
		System.out.println("lessonId est absent!!");
		return null;
	}
}