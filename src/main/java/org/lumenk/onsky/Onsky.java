package org.lumenk.onsky;

import com.google.common.reflect.ClassPath;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.lumenk.onsky.commands.*;
import org.lumenk.onsky.completers.DeleteHomeCommandCompleter;
import org.lumenk.onsky.completers.HomeCommandCompleter;
import org.lumenk.onsky.completers.OnSkyDummyCommandCompleter;
import org.lumenk.onsky.listeners.PlayerJoinListener;
import org.lumenk.onsky.listeners.ServerPingListener;
import org.lumenk.onsky.utils.TextUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class Onsky extends JavaPlugin {

    private static Onsky instance;
    private static YamlConfiguration saveFile;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        Bukkit.getServer().getLogger().warning(TextUtil.toColor("&bHello &fServer!"));
        Bukkit.getServer().getLogger().info("안녕!나는 OnSky" + getDescription().getVersion() + "이라고 해!");

        Class[] classes;
        //Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getLogger().info(TextUtil.toColor("&e본격적으로 필요한 것들을 불러와볼까?"));

        Bukkit.getLogger().info(TextUtil.toColor("&처음으로는 &6저장 파일&e을 불러올 거야!"));
        saveFile = new YamlConfiguration();
        loadFiles();


        Bukkit.getLogger().info(TextUtil.toColor("&e이제 내부적으로 필요한 객체를 생성할거야!"));
        Bukkit.getLogger().info(TextUtil.toColor("&e첫 번째로 &6Listener&e를 불러올거야"));

        ArrayList<Class> classList = new ArrayList<>();
        classList.add(PlayerJoinListener.class);
        classList.add(ServerPingListener.class);
        classList.forEach(c -> {
            try {
                Bukkit.getPluginManager().registerEvents((Listener) c.newInstance(), this);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        Bukkit.getLogger().info("총 " + classList.size() + "개의 Listener를 불러왔어.");
        /*
        Listener listener;
        try{
            classes = getClasses("org.lumenk.onsky.listeners");
            for (Class aClass : classes)
                Bukkit.getPluginManager().registerEvents((Listener) aClass.newInstance(), this);
            Bukkit.getLogger().info("총 " + classes.length + "개의 Listener을 불러왔어!");
        } catch (InstantiationException | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
         */


        Bukkit.getLogger().info("&e두 번째로는 &e명령어&e를 불러올거야!");
        classList.clear();

        classList.add(DeleteHomeCommand.class);
        classList.add(HomeCommand.class);
        classList.add(HomeListCommand.class);
        classList.add(OnSkyDummyCommand.class);
        classList.add(SetHomeCommand.class);
        classList.forEach(aClass -> {
            try {
                CommandExecutor commandExecutor = (CommandExecutor) aClass.newInstance();
                Bukkit.getPluginCommand(commandExecutor.toString()).setExecutor(commandExecutor);
                Bukkit.getLogger().info("명령어 " + commandExecutor.toString() + "을 무사히 불러왔어!");
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });




        /*
        try {
            classes = getClasses("org.lumenk.onsky.commands");
            CommandExecutor commandExecutor;
            for(int i = 0; i < classes.length; i++){
                commandExecutor = (CommandExecutor) classes[i].newInstance();
                Bukkit.getPluginCommand(commandExecutor.toString()).setExecutor(commandExecutor);
                Bukkit.getLogger().info("명령어 " + commandExecutor.toString() + "을 무사히 불러왔어!");
            }
        } catch (IllegalAccessException | InstantiationException | IOException e) {
            e.printStackTrace();
        }
         */
        Bukkit.getLogger().info("총 " + classList.size() + "개의 명령어를 불러왔어.");

        Bukkit.getLogger().info("&e세 번째로는 &6Tab Completer&e을 불러올 거야!");
        classList.clear();
        classList.add(DeleteHomeCommandCompleter.class);
        classList.add(HomeCommandCompleter.class);
        classList.add(OnSkyDummyCommandCompleter.class);
        classList.forEach(c -> {
            try {
                TabCompleter completer = (TabCompleter) c.newInstance();
                getCommand(completer.toString()).setTabCompleter(completer);
                Bukkit.getLogger().info("명령어 " + completer.toString() + "에 대한 Tab Completer를 설정했어");
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        Bukkit.getLogger().info("총 " + classList.size() + "개의 명령어의 Completer를 불러왔어.");
        /*
        try {
            classes = getClasses("org.lumenk.onsky.completers");
            TabCompleter completer;
            for(int i = 0; i < classes.length; i++){
                completer = (TabCompleter) classes[i].newInstance();
                Bukkit.getLogger().info("명령어 " + completer.toString() + "에 대한 completer를 불러오고 있어");
                Bukkit.getPluginCommand(completer.toString()).setTabCompleter(completer);
            }
        } catch (IllegalAccessException | InstantiationException | IOException e) {
            e.printStackTrace();
        }
         */


        classList.clear();
        Bukkit.getLogger().info(TextUtil.toColor("&e필요한 모든 것을 불러왔어. 즐거운 마인크래프트 하자!"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveFiles();

        Bukkit.getLogger().info("안녕! 다음에 봐!");
    }

    public static Onsky getInstance() {
        return instance;
    }

    public static YamlConfiguration getSaveFile() {
        return saveFile;
    }

    public static void loadFiles(){
        try {
            saveFile.load("plugins/onsky/save.yml");
        } catch (IOException | InvalidConfigurationException e) {
            Bukkit.getLogger().warning("&c이런! 파일이 없어요! &e괜찮아요. 만들면 됩니다!");
            File folder = new File("plugins/onsky");
            if(folder.mkdir())
                Bukkit.getLogger().info("&b생성 성공! &e계속해볼까?");
            File file = new File("plugins/onsky/save.yml");
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            //e.printStackTrace();
        }

        try {
            saveFile.save("plugins/onsky/save.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveFiles(){
        try {
            saveFile.save("plugins/onsky/save.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
