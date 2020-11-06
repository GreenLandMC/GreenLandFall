package me.GGGEDR.GreenLandFall;

import me.GGGEDR.GreenLandFall.Commands.*;
import me.GGGEDR.GreenLandFall.LabyMod.JoinEvent;
import me.GGGEDR.GreenLandFall.Listeners.Premium;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.net.ProxySelector;

public final class Main extends Plugin {

    @Override
    public void onEnable() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Lobby());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Msg());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new sc());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Spy());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Server());
        ProxyServer.getInstance().getPluginManager().registerListener(this, new Premium());
        ProxyServer.getInstance().getPluginManager().registerListener(this, new JoinEvent());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}