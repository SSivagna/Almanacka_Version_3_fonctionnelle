package com.almanacka.planning;

import java.util.ArrayList;
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
}