package me.zachpro.signuse;

import cn.nukkit.Server;
import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginManager;
import java.util.Map;
import me.zachpro.signuse.command.SignCommand;
import me.zachpro.signuse.configuration.Locations;

public class Main extends cn.nukkit.plugin.PluginBase
{
  private static Plugin plugin;
  
  public Main() {}
  
  public void onEnable()
  {
    plugin = this;
    
    getServer().getCommandMap().getCommands().put("signuse", new SignCommand("signuse"));
    getServer().getCommandMap().getCommands().put("su", new SignCommand("su"));
    
    if (!Locations.getLocationsFile().exists()) {
      Locations.createDefaultFile();
    }
    getServer().getPluginManager().registerEvents(new me.zachpro.signuse.event.SignChanged(), this);
    getServer().getPluginManager().registerEvents(new me.zachpro.signuse.event.SignClicked(), this);
  }
  




  public void onDisable() {}
  




  public static Plugin getPlugin()
  {
    return plugin;
  }
}
