package me.zachpro.signuse.commons;

import cn.nukkit.Server;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

public class CustomDataSaving
{
  public CustomDataSaving() {}
  
  public static boolean saveToFile(File file, HashMap<String, String> data)
  {
    Properties properties = new Properties();
    
    for (Map.Entry<String, String> entry : data.entrySet()) {
      properties.put(entry.getKey(), entry.getValue());
    }
    
    try
    {
      properties.store(new FileOutputStream(file), null);

    }
    catch (FileNotFoundException e)
    {
      try
      {
        file.createNewFile();
        properties.store(new FileOutputStream(file), null);
      }
      catch (IOException localIOException2) {}
    }
    catch (IOException localIOException1) {}
    








    return false;
  }
  


  public static HashMap<String, Location> getDataFromFile(File file)
  {
    HashMap<String, Location> data = new HashMap();
    Properties properties = new Properties();
    
    try
    {
      properties.load(new FileInputStream(file));

    }
    catch (FileNotFoundException e)
    {
      try
      {
        file.createNewFile();
        properties.load(new FileInputStream(file));
      }
      catch (IOException localIOException) {}
    }
    catch (IOException localIOException1) {}
    








    for (String key : properties.stringPropertyNames())
    {
      Object hashedData = properties.get(key);
      
      String stringData = hashedData.toString();
      String trimedData = stringData.trim();
      String[] splitData = trimedData.split(",");
      
      cn.nukkit.level.Level world = Server.getInstance().getLevelByName(splitData[0].substring(16));
      
      String cleanXValue = splitData[1].substring(3);
      double x = Double.parseDouble(cleanXValue);
      
      String cleanYValue = splitData[2].substring(3);
      double y = Double.parseDouble(cleanYValue);
      
      String cleanZValue = splitData[3].substring(3);
      double z = Double.parseDouble(cleanZValue);
      
      String cleanYawValue = splitData[4].substring(5);
      double yaw = Double.parseDouble(cleanYawValue);
      
      String pitchValue = splitData[5].replaceAll("\\)", "");
      String cleanPitchValue = pitchValue.substring(7);
      double pitch = Double.parseDouble(cleanPitchValue);
      
      Vector3 pos = new Vector3(x, y, z);
      Location loc = Location.fromObject(pos, world, yaw, pitch);
      
      data.put(key, loc);
    }
    

    return data;
  }
}
