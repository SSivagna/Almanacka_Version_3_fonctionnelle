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
	
	public Boolean monitorCanTeach(String monitorId, String intensityId)
	{
		return _monitorIntensities.contains( monitorId+"|"+intensityId );
	}
	
	public void AddInputLesson( InputLesson lesson )
	{
		_lessons.add(lesson);
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
   
   public List<InputLesson> getLessons()
	{
		return _lessons;
	}
	
	public void addInputLesson( InputLesson lesson )
	{
		_lessons.add( lesson );
	}
	
	public void addMonitorCanTeach( String monitorId, String intensityId )
	{
		_monitorIntensities.add(monitorId+"|"+intensityId);
	}
}