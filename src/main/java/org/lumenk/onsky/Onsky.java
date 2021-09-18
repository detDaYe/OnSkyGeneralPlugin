package org.lumenk.onsky;

import com.google.common.reflect.ClassPath;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import utils.TextUtil;

import java.io.IOException;
import java.util.ArrayList;

public final class Onsky extends JavaPlugin {

    private static Onsky instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        Bukkit.getServer().getLogger().warning(TextUtil.toColor("&bHello &fServer!"));
        Bukkit.getServer().getLogger().info("OnSky가 활성화되었습니다");

        Class[] classes;
        //Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getLogger().info(TextUtil.toColor("&e본격적으로 필요한 것들을 불러와볼까?"));
        Bukkit.getLogger().info(TextUtil.toColor("&e일단 Listener를 불러올거야"));
        Listener listener;
        try{
            classes = getClasses("org.lumenk.onsky.listeners");
            for (Class aClass : classes)
                Bukkit.getPluginManager().registerEvents((Listener) aClass.newInstance(), this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        Bukkit.getLogger().info("명령어를 불러오고 있어!");
        try {
            classes = getClasses("org.lumenk.onsky.commands");
            CommandExecutor commandExecutor;
            for(int i = 0; i < classes.length; i++){
                commandExecutor = (CommandExecutor) classes[i].newInstance();
                Bukkit.getPluginCommand(commandExecutor.toString()).setExecutor(commandExecutor);
                Bukkit.getLogger().info("명령어 " + commandExecutor.toString() + "을 무사히 불러왔어!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getLogger().info("앗! 명령어를 불러오는 도중에 오류가 났어!");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        Bukkit.getLogger().info("이제 Tab Completer을 불러올 거야!");
        try {
            classes = getClasses("org.lumenk.onsky.completers");
            TabCompleter completer;
            for(int i = 0; i < classes.length; i++){
                completer = (TabCompleter) classes[i].newInstance();
                Bukkit.getLogger().info("명령어 " + completer.toString() + "에 대한 completer를 불러오고 있어");
                Bukkit.getPluginCommand(completer.toString()).setTabCompleter(completer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        Bukkit.getLogger().info(TextUtil.toColor("&e필요한 모든 것을 불러왔어. 즐거운 마인크래프트 하자!"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Onsky getInstance() {
        return instance;
    }

    private Class[] getClasses(String packageName) throws IOException {
        ClassLoader classloader = this.getClassLoader();
        ClassPath path = ClassPath.from(classloader);
        ArrayList<Class> arr = new ArrayList<>();
        for (ClassPath.ClassInfo info : path.getTopLevelClassesRecursive(packageName)) {
            try {
                Class clazz = Class.forName(info.getName(), true, classloader);
                arr.add(clazz);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        Class[] result = new Class[arr.size()];
        for(int i = 0; i < result.length; i++){
            result[i] = arr.get(i);
        }
        return  result;
    }
}
