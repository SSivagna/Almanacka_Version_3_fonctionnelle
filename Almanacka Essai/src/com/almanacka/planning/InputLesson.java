package com.almanacka.planning;
import java.util.Date;
import java.util.List;

public class InputLesson
{
	private String _lessonId;
	private Boolean _isLocked;
	private Boolean _isInputLocked;
	private Boolean _isAutomaticLocked;
	private String _placeWrapId;
	private Date _begDate;
	private Date _endDate;
	private List<Choice> _intensities;
    private List<Choice> _monitors;
    private List<Choice> _hosts;
	
	public InputLesson(String lessonId, boolean isInputLocked, String placeId, Date begDate, Date endDate )
	{
		_lessonId = lessonId;
		_isInputLocked = isInputLocked;
		_placeWrapId = placeId;
	/*	_begDate = begDate;
		_endDate = endDate;*/
	}
	
	/*
	 * Getters and Setters 
	 */
	public String getLessonId()
	{
		return _lessonId;
	}

	public void setLessonId(String lessonId)
	{
		this._lessonId = lessonId;
	}

	public Boolean getIsLocked()
	{
		return _isLocked;
	}

	public void setIsLocked(Boolean isLocked)
	{
		this._isLocked = isLocked;
	}

	public Boolean getIsInputLocked()
	{
		return _isInputLocked;
	}

	public void setIsInputLocked(Boolean isInputLocked)
	{
		this._isInputLocked = isInputLocked;
	}

	public Boolean getIsAutomaticLocked()
	{
		return _isAutomaticLocked;
	}

	public void setIsAutomaticLocked(Boolean isAutomaticLocked)
	{
		this._isAutomaticLocked = isAutomaticLocked;
	}

	public String getPlaceWrapId()
	{
		return _placeWrapId;
	}

	public void setPlaceWrapId(String placeWrapId)
	{
		this._placeWrapId = placeWrapId;
	}

	public Date getBegDate() 
	{
		return _begDate;
	}

	public void setBegDate ( Date begDate) 
	{
		this._begDate = begDate;
	}

	public Date getEndDate()
	{
		return _endDate;
	}
	
	public void setEndDate( Date endDate)
	{
		this._endDate = endDate;
	}

	public List<Choice> getIntensities()
	{
		return _intensities;
	}
	
    public List<Choice> getMonitors()
    {
    	return _monitors;
    }
    
    public List<Choice> getHosts()
    {
    	return _hosts;
    }

	/*
     * returns false if the lesson is invalid.
     */
    protected Boolean preprocessInput(InputPlanning planning)
    {
        if( !_isLocked )
        {
            _isAutomaticLocked = _intensities.size() == 1 && _monitors.size() == 1 && _hosts.size() == 1;
        }
        return true;
    }
	
	/*
	 * Display all informations about an InputLesson
	 */
	public String toString()
	{
		return "InputLesson [ lessonId = " + _lessonId + ", idPlace = " + _placeWrapId + ", isInputLocked " + _isInputLocked + " ]";
	}
}