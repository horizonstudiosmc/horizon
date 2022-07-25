package org.horizon.plugins.horizon.api.expansion;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.horizon.plugins.horizon.Horizon;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ExpansionAPI {

    Horizon instance;

    public ExpansionAPI(Horizon instance) {
        this.instance = instance;
    }

    public void sfer() {
        File[] files = instance.af.listFiles();
        for (File file : files) {
            if (file.isFile()) { // this line weeds out other directories/folders
                if (file.getName().endsWith(".yml")) {
                    registerExpansion(file);
                }
            }
        }
    }

    public void registerExpansion(File config) {
        File jar;
        String className;
        String mainMethod;
        YamlConfiguration configuration = new YamlConfiguration();
        try {
            configuration.load(config);
            File file = instance.af;
            jar = new File(file.getAbsolutePath() + "/" + configuration.getString("file"));
            className = configuration.getString("className");
            mainMethod = configuration.getString("mainMethod");
        } catch (Exception e) {
            return;
        }
        try {
            URLClassLoader loader = new URLClassLoader(new URL[] { jar.toURI().toURL() },
                    Horizon.class.getClassLoader());

            Class classToLoad = Class.forName(className, true, loader);

            System.out.println(classToLoad);
            Class<?>[] type = {Horizon.class};
            Constructor<?> cons = classToLoad.getConstructor(type);
            Object instance = cons.newInstance(this.instance);
            Method method = classToLoad.getDeclaredMethod(mainMethod);
            method.invoke(instance);

        } catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException | SecurityException
                 | InstantiationException | IllegalAccessException | IllegalArgumentException
                 | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Bukkit.getLogger().warning("Could not load addon " + jar.getName());
        }
    }

}
