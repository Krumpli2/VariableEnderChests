package me.saif.betterenderchests.lang.locale;

import me.saif.betterenderchests.VariableEnderChests;
import me.saif.reflectionutils.ReflectionUtils;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PlayerLocaleFinder {

    private VariableEnderChests plugin;
    private Method getLocaleMethod;


    public PlayerLocaleFinder(VariableEnderChests plugin) {
        this.plugin = plugin;

        if (VariableEnderChests.getMCVersion() < 12) {
            getLocaleMethod = ReflectionUtils.getMethod(Player.Spigot.class, "getLocale", true).get();
        }
    }

    public Locale getLocale(Player player) {
        if (VariableEnderChests.getMCVersion() >= 12)
            return plugin.getLocaleLoader().getOrDefault(player.getLocale());
        try {
            return plugin.getLocaleLoader().getOrDefault((String) getLocaleMethod.invoke(player.spigot()));
        } catch (IllegalAccessException | InvocationTargetException e) {
            return plugin.getLocaleLoader().getDefaultLocale();
        }
    }

}
