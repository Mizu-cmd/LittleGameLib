package fr.mizu.littlegameslib.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemUtil {
    public static void updateName(ItemStack itemStack, String name){
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);
    }

    public static ItemStack makeItem(Material material, String name, boolean enchant, String... lore){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        if (enchant){
            meta.addEnchant(Enchantment.OXYGEN, 10, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    public static ItemStack makeItem(Material material, byte metaID, String name, boolean enchant, String... lore){
        ItemStack itemStack = new ItemStack(material, 1, metaID);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        if (enchant){
            meta.addEnchant(Enchantment.OXYGEN, 10, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    public static ItemStack makeItem(Material material, String name, boolean enchant){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        if (enchant){
            meta.addEnchant(Enchantment.OXYGEN, 10, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    public static ItemStack makeItem(Material material, byte metaID, String name, boolean enchant){
        ItemStack itemStack = new ItemStack(material,1, metaID);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        if (enchant){
            meta.addEnchant(Enchantment.OXYGEN, 10, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
