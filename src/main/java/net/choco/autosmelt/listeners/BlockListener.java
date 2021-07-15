package net.choco.autosmelt.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BlockListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        Player p = e.getPlayer();
        Material drop;
        int dropAmount = 1;
        ItemStack hand = p.getItemInHand();
        switch (block.getType()) {
            case GOLD_ORE: {
                drop = Material.GOLD_INGOT;
                break;
            }
            case IRON_ORE: {
                drop = Material.IRON_INGOT;
                break;
            }
            default: {
                drop = Material.AIR;
                break;
            }
        }
        if (drop.equals(Material.AIR) || block.getDrops(hand).isEmpty()) {
            return;
        }
        if (hand.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
            Random rand = new Random();
            dropAmount = rand.nextInt(hand.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1) + 1;
        }
        block.breakNaturally(new ItemStack(Material.AIR));
        block.getWorld().dropItemNaturally(block.getLocation().add(0.5, 0.5, 0.5), new ItemStack(drop, dropAmount));
    }
}
