package me.AutoSoupCheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class Main
  extends JavaPlugin
  implements Listener
  
  {
	public static List<Player> Sopa1 = new ArrayList<Player>();
	public static Plugin plugin;
	public void onEnable()
	  {
		plugin = this;
		getServer().getConsoleSender().sendMessage("�b�lCheckAutoSoup �aBy: �4VinyyHD ");  
		Bukkit.getServer().getPluginManager().registerEvents(new Amount(this), this);
	    getConfig().options().copyDefaults(true);
	    saveDefaultConfig();
		
	    
	  }

public void onDisable() {}
  
private ArrayList<Block> remover = new ArrayList<Block>();
public HashMap<String, ItemStack[]> Arm = new HashMap<String, ItemStack[]>();
public HashMap<String, ItemStack[]> it = new HashMap<String, ItemStack[]>();
public HashMap<String, Location> LocalPlayer1 = new HashMap<String, Location>();

public void Arm(Player p)
{
ItemStack[] arm = p.getInventory().getArmorContents();
this.Arm.put(p.getName(), arm);
}

public void it(Player p)
{
ItemStack[] item = p.getInventory().getContents();
this.it.put(p.getName(), item);
}


@EventHandler
public void removerentrar(PlayerJoinEvent event)
{
Player p = event.getPlayer();
Main.Sopa1.remove(p.getName());

}

@EventHandler
public void removermorrer(PlayerDeathEvent event)
{
if ((event.getEntity() instanceof Player))
{
Player p = event.getEntity();
Main.Sopa1.remove(p.getName());

}
}
@SuppressWarnings("deprecation")
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
{
if (!(sender instanceof Player))
{
sender.sendMessage(ChatColor.translateAlternateColorCodes('&', 
        getConfig().getString("Permission") + ChatColor.RESET));
return true;
}
final Player player = (Player)sender;
if (cmd.getName().equalsIgnoreCase("CheckAS"))
{
if (!player.hasPermission("autosoup.check"))
{
	player.sendMessage(ChatColor.translateAlternateColorCodes('&', 
	        getConfig().getString("Permission") + ChatColor.RESET));
return true;
}
if (args.length == 0)
{
player.sendMessage(ChatColor.translateAlternateColorCodes('&', 
        getConfig().getString("Use") + ChatColor.RESET));
return true;
}
if (args.length == 1)
{
final Player Player2 = Bukkit.getPlayerExact(args[0]);

final PlayerInventory Salvar = Player2.getInventory();
if ((Player2 instanceof Player)) {
	  Location Local = Player2.getPlayer().getLocation();
    Local = Local.getWorld().getHighestBlockAt(Local).getLocation().add(0.0D, 22.0D, 0.0D);
    for (int x = 0; x <= 0; x++) {
      for (int z = 0; z <= 0; z++)
      {
       final Block a = Local.add(x, 0.0D, z).getBlock();
        a.setType(Material.BEDROCK);
        
        final Block b = Local.add(x, -3.0D, z).getBlock();
        b.setType(Material.BEDROCK);
        
        final Block c = Local.add(1, 1.0D, 0).getBlock();
        c.setType(Material.BEDROCK);
        
        final Block d = Local.add(-2, 0.0D, 0).getBlock();
        d.setType(Material.BEDROCK);
        
        final Block e = Local.add(1, 0.0D, 1).getBlock();
        e.setType(Material.BEDROCK);
        
        final Block f = Local.add(0, 0.0D, -2).getBlock();
        f.setType(Material.BEDROCK);
        this.LocalPlayer1.put(Player2.getName(), Player2.getLocation());
        Player2.getPlayer().teleport(Local.add(1.0D, 0.0D, 1.0D));
        
        Arm(Player2);
        it(Player2);
        
        
        Salvar.clear();              
        Salvar.setArmorContents(null);
        Player2.getInventory().setItem(13, new ItemStack(Material.MUSHROOM_SOUP));
        Player2.setHealth(1);
        
        Player2.updateInventory();
       
        Main.Sopa1.add(Player2);
        
        Bukkit.getServer().getScheduler().runTaskLater(Main.plugin, new Runnable(
        {
          public void run()
          {
              if (Main.Sopa1.contains(Player2))
           	 {
              	
            	  player.sendMessage((ChatColor.translateAlternateColorCodes('&', getConfig().getString("MessageCheck").replace("@PLAYER2", Player2.getName()).replace("@SOUP", "" + Amount.getAmount(Player2, Material.BOWL)))));
              	
            	  Salvar.setArmorContents((ItemStack[])Main.this.Arm.get(Player2.getName()));
          	  Salvar.setContents((ItemStack[])Main.this.it.get(Player2.getName()));
          	  Main.this.Arm.remove(Player2.getName());
                Main.this.it.remove(Player2.getName());
          	  Player2.updateInventory();
          	  Player2.setHealth(20);
	                
          	Main.Sopa1.remove(Player2);
          	a.setType(Material.AIR);
          	b.setType(Material.AIR);
          	c.setType(Material.AIR);
          	d.setType(Material.AIR);
          	e.setType(Material.AIR);
          	f.setType(Material.AIR);
          	Main.this.remover.remove(a);
          	Main.this.remover.remove(b);
          	Main.this.remover.remove(c);
          	Main.this.remover.remove(d);
          	Main.this.remover.remove(e);
          	Main.this.remover.remove(f);
          	Player2.teleport((Location)Main.this.LocalPlayer1.get(Player2.getName()));
          	
         	
          	
               }
          }
        }, 8L);
      }
    }
    
}
}
        

 
}
return false;
}
@EventHandler
public void drop4(PlayerDropItemEvent e)
{
Player p = e.getPlayer();
ItemStack item = e.getItemDrop().getItemStack();
if (Main.Sopa1.contains(p.getName()) && (item.getType() == Material.BOWL))
{
e.setCancelled(true);
p.sendMessage("�6-> �c�o Drop negado !");
}
}
}
