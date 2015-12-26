package me.AutoSoupCheck;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;


public class Amount implements Listener
{
	  private Main pl;

	  public Amount(Main plugin)
	  {
	    this.setPl(plugin);
	  }

  public static int getAmount(Player p, Material m)
  {
    int amount = 0;
    for (ItemStack item : p.getInventory().getContents()) {
      if ((item == null) || (item.getType() != m) || 
        (item.getAmount() <= 0)) continue;
      amount += item.getAmount();
    }

    return amount;
  }

public Main getPl() {
	return pl;
}

public void setPl(Main pl) {
	this.pl = pl;
}
}