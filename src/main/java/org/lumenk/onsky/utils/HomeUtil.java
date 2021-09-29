package org.lumenk.onsky.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.lumenk.onsky.Onsky;

import java.util.ArrayList;
import java.util.List;

public class HomeUtil {
    public static boolean addHome(Player player, String name){
        List<String> homeList = getHomes(player);
        if(homeList.contains(name))
            return false;
        homeList.add(name);
        Onsky.getSaveFile().set(player.getUniqueId() + ".home.homelist", homeList);
        Onsky.getSaveFile().set(player.getUniqueId() + ".home.homes." + name, player.getLocation());
        Onsky.saveFiles();
        return true;
    }

    public static Location getHome(Player player, String name){
        List<String> homeList = getHomes(player);
        if(!homeList.contains(name)){
            return null;
        }
        return Onsky.getSaveFile().getLocation(player.getUniqueId() + ".home.homes." + name);
    }

    public static boolean delHome(Player player, String name){
        List<String> homeList = getHomes(player);
        if(!homeList.contains(name))
            return false;

        homeList.remove(name);
        Onsky.getSaveFile().set(player.getUniqueId() + ".home.homelist", homeList);
        Onsky.getSaveFile().set(player.getUniqueId() + ".home.homes." + name, null);
        Onsky.saveFiles();
        return true;
    }
    public static List<String> getHomes(Player player){
        List<?> temp = Onsky.getSaveFile().getList(player.getUniqueId() + ".home.homelist");
        if(temp == null) return new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        temp.forEach(h-> result.add(h.toString()));
        return result;
    }

    public static String makeHomeName(String[] args){
        StringBuilder homeName = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            homeName.append(args[i]);
            if (i < args.length - 1)
                homeName.append(" ");
        }
        return homeName.toString();
    }
}
