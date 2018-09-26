package me.zachpro.signuse.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import me.zachpro.signuse.commons.ChatColor;
import me.zachpro.signuse.configuration.Locations;

public class SignCommand extends Command
{
  public static String commandPrefix = "&6[&6SignUse&6] &r";
  
  public SignCommand(String name) {
    super(name);
    description = "Main command for SignUse Plugin";
    usageMessage = "Use /signuse help for a list of commands.";
    setPermission("");
    
    String[] aliases = { "su" };
    setAliases(aliases);
  }
  

  public boolean execute(CommandSender sender, String alias, String[] args)
  {
    if (!(sender instanceof Player))
    {

      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&4Error: &6This plugin only supports in-game command use."));
      return false;
    }
    

    if (args.length < 1)
    {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&4Syntax Error: &6Invalid arguments."));
      return false;
    }
    
    Player player = (Player)sender;
    
    if (args[0].equalsIgnoreCase("help"))
    {

      if (!player.hasPermission("signuse.help"))
      {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&cYou do not have premission for this!"));
        return false;
      }
      
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&2Commands List:"));
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&7su help"));
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&7/su setlocation <name>"));
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&7/su teleportlocation <name>"));
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&7/su deletelocation <name>"));
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&7/su listlocations"));

    }
    else if ((args[0].equalsIgnoreCase("setlocation")) || 
      (args[0].equalsIgnoreCase("sl")))
    {

      if (!player.hasPermission("signuse.location.create"))
      {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&cYou do not have premission for this!"));
        return false;
      }
      


      if (args.length < 2)
      {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&4Syntax Error: &6Invalid arguments."));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&6Usage: &7/signuse setlocation <name>"));
        return false;
      }
      
      String name = args[1];
      
      Location location = player.getLocation();
      
      Locations.saveLocation(name, location);
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&6Location " + name + " has been set to &a" + Math.round(location.getX()) + ", &a" + Math.round(location.getY()) + ", &a" + Math.round(location.getZ()) + "&6 in world &2" + location.getLevel().getName()));

    }
    else if ((args[0].equalsIgnoreCase("teleportLocation")) || 
      (args[0].equalsIgnoreCase("tl")))
    {

      if (!player.hasPermission("signuse.location.teleport"))
      {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&cYou do not have premission for this!"));
        return false;
      }
      


      if (args.length < 2)
      {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&4Syntax Error: &6Invalid arguments."));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&6Usage: &7/signuse teleportlocation <name>"));
        return false;
      }
      
      String name = args[1];
      
      Location location = Locations.getLocation(name);
      
      if (location == null)
      {

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&4Error: &6Unknown Location"));
        return false;
      }
      
      player.teleport(location);
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&6You have been teleported to &a" + name + " &6location."));

    }
    else if ((args[0].equalsIgnoreCase("deleteLocation")) || 
      (args[0].equalsIgnoreCase("dl")))
    {

      if (!player.hasPermission("signuse.location.delete"))
      {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&cYou do not have premission for this!"));
        return false;
      }
      


      if (args.length < 2)
      {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&4Syntax Error: &6Invalid arguments."));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&6Usage: &7/signuse deletelocation <name>"));
        return false;
      }
      
      String name = args[1];
      
      Locations.deleteLocation(name);
      
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&6Location &a" + name + "&6 has been deleted."));

    }
    else if ((args[0].equalsIgnoreCase("listLocations")) || 
      (args[0].equalsIgnoreCase("lls")))
    {

      if (!player.hasPermission("signuse.location.list"))
      {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&cYou do not have premission for this!"));
        return false;
      }
      


      player.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&6Current Locations:"));
      
      int i = 0;
      for (String location : Locations.getLocations().keySet())
      {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', i + ": &7" + location));
        i++;
      }
      
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', commandPrefix + "&6End of Locations"));
    }
    


    return false;
  }
}
