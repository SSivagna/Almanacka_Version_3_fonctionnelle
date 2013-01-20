package com.almanacka.planning;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InputLesson
{
	private String _lessonId;
	private Byte _isLocked;
	private byte _isInputLocked;
	private Boolean _isAutomaticLocked;
	private String _placeWrapId;
	private Date _begDate;
	private Date _endDate;
	private List<String> _intensities;
	private List<String> _monitors;
	private List<String> _hosts;
	
	public InputLesson(String lessonId, Byte isInputLocked, String placeId, Date begDate, Date endDate, List<String> iTensities, List<String> iMonitors, List<String> iHosts )
	{
		_lessonId = lessonId;
		_isInputLocked = isInputLocked;
		_placeWrapId = placeId;
		_begDate = begDate;
		_endDate = endDate;
		_intensities = new ArrayList<>(iTensities);
		_monitors = new ArrayList<>(iMonitors);
		_hosts = new ArrayList<>(iHosts);
	}
	
	public InputLesson(String lessonId, Byte isInputLocked, String placeId, Date begDate, Date endDate, String intensity, String monitor, List<String> hosts )
	{
		_lessonId = lessonId;
		_isInputLocked = isInputLocked;
		_placeWrapId = placeId;
		_begDate = begDate;
		_endDate = endDate;	
		_intensities = new ArrayList<>();
		_monitors = new ArrayList<>();
		_intensities.add(intensity);
		_monitors.add(monitor);
		_hosts=new ArrayList<String>(hosts);
	}

	public String getLessonId()
	{
		return _lessonId;
	}

	public void setLessonId(String lessonId)
	{
		this._lessonId = lessonId;
	}

	public Byte getIsLocked()
	{
		return _isLocked;
	}

	public void setIsLocked(Byte isLocked)
	{
		this._isLocked = isLocked;
	}

	public Byte getIsInputLocked()	
	{
		return _isInputLocked;
	}

	public void setIsInputLocked(Byte isInputLocked)
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
	
	public List<String> getIntensities()
	{
		return _intensities;
	}
	
	public List<String> getMonitors()
	{
		return _intensities;
	}
	
	public List<String> getHosts()
	{
		return _hosts;
	}

	/*
     * returns false if the lesson is invalid.
     */
	/*protected Boolean preprocessInput(InputPlanning planning)
    {
        if( !_isLocked )
        {
            _isAutomaticLocked = _intensities.size() == 1 && _monitors.size() == 1 && _hosts.size() == 1;
        }
        return true;
    }*/
	
    /*
	 * Display all informations about an InputLesson
	 */
	@Override
	public String toString() {
		return "InputLesson [_lessonId=" + _lessonId + ", _isLocked="
				+ _isLocked + ", _isInputLocked=" + _isInputLocked
				+ ", _isAutomaticLocked=" + _isAutomaticLocked
				+ ", _placeWrapId=" + _placeWrapId + ", _begDate=" + _begDate
				+ ", _endDate=" + _endDate + ", _intensities=" + _intensities
				+ ", _monitors=" + _monitors + ", _hosts=" + _hosts + "]";
	}
}