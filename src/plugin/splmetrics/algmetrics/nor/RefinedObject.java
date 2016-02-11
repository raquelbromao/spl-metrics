package plugin.splmetrics.algmetrics.nor;

import java.util.ArrayList;

public class RefinedObject {
	private String className;
	private String featureName;
	private int numRefines;
	private ArrayList<RefinedObject> refinesClasses = new ArrayList<RefinedObject>();	
	
	public RefinedObject(String className, String featureName) {
		this.className = className;
		this.setFeatureName(featureName);
		this.numRefines = 0;
	}
	
	public void incrementNumRefines() {
		this.numRefines++;
	}
	
	public String getNameClass() {
		return className;
	}
	public void setNameClass(String nameClass) {
		this.className = nameClass;
	}
	public int getNumRefines() {
		return numRefines;
	}
	public void setNumRefines(int numRefines) {
		this.numRefines = numRefines;
	}
	public ArrayList<RefinedObject> getRefinesClasses() {
		return refinesClasses;
	}
	public void setRefinesClasses(RefinedObject refinesClasses) {
		this.refinesClasses.add(refinesClasses);
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	
}
