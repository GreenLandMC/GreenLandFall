package me.GGGEDR.GreenLandFall;

import me.GGGEDR.GreenLandFall.Commands.*;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public final class Main extends Plugin {

    @Override
    public void onEnable() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Lobby());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Msg());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new sc());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Spy());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Server());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
