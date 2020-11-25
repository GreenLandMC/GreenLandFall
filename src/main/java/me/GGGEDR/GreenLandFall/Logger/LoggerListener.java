package me.GGGEDR.GreenLandFall.Logger;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class LoggerListener implements Listener {

    @EventHandler
    public void Received(PluginMessageEvent e) throws IOException {
        if(e.getTag().equalsIgnoreCase("logger")){
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(e.getData()));
            String channel = in.readUTF();
            if(channel.equalsIgnoreCase("add")){
                String input = in.readUTF();
                LoggerCache.cache.get(ProxyServer.getInstance().getPlayer(e.getReceiver().toString())).info(input);
            }
        }
    }
}
