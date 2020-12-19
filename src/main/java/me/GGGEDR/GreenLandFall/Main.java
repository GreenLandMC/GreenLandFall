package me.GGGEDR.GreenLandFall;

import me.GGGEDR.GreenLandFall.Commands.*;
import me.GGGEDR.GreenLandFall.LabyMod.JoinEvent;
import me.GGGEDR.GreenLandFall.Listeners.Premium;
import me.GGGEDR.GreenLandFall.Logger.LoggerJoin;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;

public final class Main extends Plugin {
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        getDataFolder().mkdir();
        File log_folder = new File(getDataFolder() +"/logy/");
        if(!log_folder.exists()){
            log_folder.mkdirs();
        }
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Lobby());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Msg());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new sc());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Spy());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Server());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new alert());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new discord());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new web());
        ProxyServer.getInstance().getPluginManager().registerListener(this, new Premium());
        ProxyServer.getInstance().getPluginManager().registerListener(this, new JoinEvent());
        ProxyServer.getInstance().getPluginManager().registerListener(this, new LoggerJoin());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance(){
        return instance;
    }
}
