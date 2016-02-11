package plugin.splmetrics.algmetrics.nor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import plugin.splmetrics.main.ReadXML;


public class NoR {
	static ArrayList<RefinedObject> refinesObjs = new ArrayList<RefinedObject>();	
	
	public static ArrayList<RefinedObject> getRefinesObjs() {
		return refinesObjs;
	}
	
	public static void reset() {
		refinesObjs = null;
		refinesObjs = new ArrayList<RefinedObject>();
	}
	
	public static void execute() {
		
		
        Path source = Paths.get(ReadXML.getPathFeatureDir());
        try {
        	//Primeiro percorre todos os diretórios e arquivos procurando por classes refinadas
            Files.walkFileTree(source, new RefinedClassesSearch());
            
           
            Files.walkFileTree(source, new DerivedClassesSearch());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
      /*
        for (int i = 0; i < NoR.getRefinesObjs().size(); i++) {
        	//if (NoR.getRefinesObjs().get(i).getNumRefines() > 0) {
        		System.out.println(NoR.getRefinesObjs().get(i).getNameClass() + " [" 
        						 + NoR.getRefinesObjs().get(i).getFeatureName() + "] "
        						 + NoR.getRefinesObjs().get(i).getNumRefines());
        		
            	for(int j = 0; j < NoR.getRefinesObjs().get(i).getRefinesClasses().size(); j++) {
            		System.out.println(" -> " + NoR.getRefinesObjs().get(i).getRefinesClasses().get(j).getNameClass()
            						+ " [" + NoR.getRefinesObjs().get(i).getRefinesClasses().get(j).getFeatureName() + "]"
            						);
            	}
        	//}
        	System.out.println();

        }*/
    }
}