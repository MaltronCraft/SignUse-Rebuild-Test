package me.zachpro.signuse.configuration;

import cn.nukkit.level.Location;
import cn.nukkit.plugin.Plugin;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import me.zachpro.signuse.Main;
import me.zachpro.signuse.commons.CustomDataSaving;


public class Locations
{
  private static Plugin plugin = ;
  
  public Locations() {}
  
  public static void saveLocation(String name, Location location) {
    File locationsFile = getLocationsFile();
    

    HashMap<String, String> data = getLocations();
    data.put(name, location.toString());
    

    CustomDataSaving.saveToFile(locationsFile, data);
  }
  


  public static Location getLocation(String name)
  {
    File locationsFile = getLocationsFile();
    
    HashMap<String, Location> returnedLocations = CustomDataSaving.getDataFromFile(locationsFile);
    
    if (returnedLocations.containsKey(name)) {
      return (Location)returnedLocations.get(name);
    }
    return null;
  }
  


  public static void deleteLocation(String name)
  {
    File locationsFile = getLocationsFile();
    

    HashMap<String, String> data = getLocations();
    data.remove(name);
    

    CustomDataSaving.saveToFile(locationsFile, data);
  }
  

  public static HashMap<String, String> getLocations()
  {
    File locationsFile = getLocationsFile();
    HashMap<String, Location> returnedLocations = CustomDataSaving.getDataFromFile(locationsFile);
    
    HashMap<String, String> stringLocations = new HashMap();
    

    for (Map.Entry<String, Location> entry : returnedLocations.entrySet())
    {
      stringLocations.put((String)entry.getKey(), ((Location)entry.getValue()).toString());
    }
    
    return stringLocations;
  }
  


  public static void createDefaultFile()
  {
    File locationsFile = getLocationsFile();
    
    try
    {
      locationsFile.createNewFile();
    }
    catch (IOException localIOException) {}
  }
  




  public static File getLocationsFile()
  {
    File pluginDirectory = plugin.getDataFolder();
    
    if (!pluginDirectory.exists()) {
      pluginDirectory.mkdirs();
    }
    File locationsFile = new File(pluginDirectory, "locations.properties");
    
    return locationsFile;
  }
}
