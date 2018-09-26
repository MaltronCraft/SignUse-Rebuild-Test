package me.zachpro.signuse.event;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.SignChangeEvent;
import me.zachpro.signuse.commons.ChatColor;
import me.zachpro.signuse.configuration.Locations;

public class SignChanged implements Listener
{
  public SignChanged() {}
  
  @EventHandler
  public void signChanged(SignChangeEvent event)
  {
    String[] lines = event.getLines();
    
    Player player = event.getPlayer();
    
    if (!player.hasPermission("signuse.sign.create"))
    {
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', me.zachpro.signuse.command.SignCommand.commandPrefix + "&cYou do not have premission for this!"));
      return;
    }
    
    if (lines[0].equalsIgnoreCase("[teleport]"))
    {

      event.setLine(0, ChatColor.translateAlternateColorCodes('&', "&6[&aTeleport&6]"));
      
      if (event.getLines().length < 2) {
        return;
      }
      String locationName = lines[1];
      
      if (Locations.getLocation(locationName) == null)
      {

        event.setLine(1, ChatColor.translateAlternateColorCodes('&', "&4Unknown Location"));
        return;
      }
    }
  }
}
