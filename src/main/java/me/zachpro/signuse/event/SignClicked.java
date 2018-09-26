package me.zachpro.signuse.event;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockAir;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerInteractEvent.Action;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;
import me.zachpro.signuse.command.SignCommand;
import me.zachpro.signuse.commons.ChatColor;

public class SignClicked implements Listener
{
  public SignClicked() {}
  
  @EventHandler
  public void onPlayerInteractEvent(PlayerInteractEvent event)
  {
    PlayerInteractEvent.Action action = event.getAction();
    Block block = event.getBlock();
    
    Player player = event.getPlayer();
    
    if (action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
    {

      Vector3 pos = new Vector3(block.getX(), block.getY(), block.getZ());
      
      BlockEntity blockEntity = block.getLevel().getBlockEntity(pos);
      
      if (!(blockEntity instanceof BlockEntitySign)) {
        return;
      }
      BlockEntitySign sign = (BlockEntitySign)blockEntity;
      
      if (sign.getText().length != 2) {
        return;
      }
      String[] lines = sign.getText();
      
      if (!ChatColor.stripColor(lines[0]).equalsIgnoreCase("[teleport]")) {
        return;
      }
      if (!player.hasPermission("signuse.sign.use"))
      {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', SignCommand.commandPrefix + "&cYou do not have premission for this!"));
        return;
      }
      
      Location loc = me.zachpro.signuse.configuration.Locations.getLocation(lines[1]);
      
      if (loc == null)
      {

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', SignCommand.commandPrefix + "&4Error: &6Unknown Location"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', SignCommand.commandPrefix + "&4Removed Sign!"));
        Server.getInstance().getLevelByName(block.getLevel().getName()).setBlock(pos, new BlockAir());
        return;
      }
      
      player.teleport(loc);
    }
  }
}
