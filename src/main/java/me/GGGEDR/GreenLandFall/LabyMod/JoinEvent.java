package me.GGGEDR.GreenLandFall.LabyMod;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.labymod.serverapi.bungee.LabyModPlugin;
import net.labymod.serverapi.bungee.event.LabyModPlayerJoinEvent;
import net.labymod.serverapi.bungee.event.MessageReceiveEvent;
import net.labymod.serverapi.bungee.event.MessageSendEvent;
import net.labymod.serverapi.bungee.listener.PluginMessageListener;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JoinEvent implements Listener {
    List<ProxiedPlayer> players = new ArrayList<>();

    @EventHandler
    public void onGet(MessageReceiveEvent e){
        try {
            if (Contains(e.getJsonElement().toString(), "joinSecret")) {
                ProxyServer.getInstance().getPlayer(UUID.fromString(parse(e.getJsonElement().toString(), "joinSecret").split(":play.greenlandmc.eu")[0])).sendMessage(new TextComponent("§a§lParty §8» §7Práve sa k tebe pripája hráč: §a"+ e.getPlayer().getName() +" §7cez tvoju §blabymode pozvánku"));
            }
        } catch (Exception ex){

        }
    }


   /*@EventHandler
    public void onSend(MessageSendEvent e){

        try {
            System.out.println(e.getJsonElement());
            if (Contains(e.getJsonElement().toString(), "joinSecret")) {
                System.out.println(parse(e.getJsonElement().toString(), "joinSecret").split(":play.greenlandmc.eu")[0]);
                ProxyServer.getInstance().getPlayer(UUID.fromString(parse(e.getJsonElement().toString(), "joinSecret").split(":play.greenlandmc.eu")[0])).sendMessage(new TextComponent("§b§lLabyMode §a§lInviter §8» §7Práve sa k tebe pripája hráč cez tvoju §blabymode §7discord pozvánku"));
            }
        } catch (Exception ex){

        }
    }*/

    @EventHandler
    public void onJoin(LabyModPlayerJoinEvent e){
        players.add(e.getPlayer());
        updateGameInfo(e.getPlayer(), true, "AuthMe", System.currentTimeMillis(), false, 0);
        setMiddleClickActions(e.getPlayer());
    }

     @EventHandler
     public void leave(PlayerDisconnectEvent e){
        if(players.contains(e.getPlayer())){
            players.remove(e.getPlayer());
        }
     }

    @EventHandler
    public void ServerChane(ServerSwitchEvent e){
        if(players.contains(e.getPlayer())){
            updateGameInfo(e.getPlayer(), true, e.getPlayer().getServer().getInfo().getName(), System.currentTimeMillis(), true, 1);
            sendCurrentPlayingGamemode(e.getPlayer(), true, e.getPlayer().getName());
            setMiddleClickActions(e.getPlayer());
        }
    }


    private void updateGameInfo( ProxiedPlayer player, boolean hasGame, String gamemode, long startTime, boolean hasParty, int inparty) {

        String domain = "play.greenlandmc.eu";
        // Create game json object
        JsonObject obj = new JsonObject();
        obj.addProperty( "hasGame", hasGame );

        if ( hasGame ) {
            obj.addProperty( "game_mode", gamemode );
            obj.addProperty( "game_startTime", startTime ); // Set to 0 for countdown
        }

        addSecret( obj, "hasMatchSecret", "matchSecret", UUID.randomUUID(), domain );
        addSecret( obj, "hasSpectateSecret", "spectateSecret", UUID.randomUUID(), domain );
        addSecret( obj, "hasJoinSecret", "joinSecret", player.getUniqueId(), domain );

        obj.addProperty( "hasParty", hasParty );

        if ( hasParty ) {
            obj.addProperty( "partyId", player.getUniqueId().toString() + ":" + domain );
            obj.addProperty( "party_size", inparty );
            obj.addProperty( "party_max", 3 );
        }
        // Send to user
        LabyModPlugin.getInstance().sendServerMessage( player, "discord_rpc", obj );
    }

    public JsonObject addSecret( JsonObject jsonObject, String hasKey, String key, UUID secret, String domain) {
        jsonObject.addProperty( hasKey, true );
        jsonObject.addProperty( key, secret.toString() + ":" + domain );
        return jsonObject;
    }


    public void sendCurrentPlayingGamemode(ProxiedPlayer player, boolean visible, String gamemodeName ) {
        JsonObject object = new JsonObject();
        object.addProperty( "show_gamemode", visible ); // Gamemode visible for everyone
        object.addProperty( "gamemode_name", gamemodeName ); // Name of the current playing gamemode

        // Send to LabyMod using the API
        LabyModPlugin.getInstance().sendServerMessage( player, "server_gamemode", object );
    }

    public void setMiddleClickActions( ProxiedPlayer player ) {
        // List of all action menu entries
        JsonArray array = new JsonArray();


        JsonObject entry = new JsonObject();
        entry.addProperty( "displayName", "Poslat pozvánku do party" );
        entry.addProperty( "type", EnumActionType.RUN_COMMAND.name() );
        entry.addProperty( "value", "party {name}" ); // {name} will be replaced with the players name
        array.add(entry);

        entry = new JsonObject();
        entry.addProperty( "displayName", "Otvorit štatistiky" );
        entry.addProperty( "type", EnumActionType.CLIPBOARD.name() );
        entry.addProperty( "value", "https://example.com/stats/{name}" );
        array.add(entry);

        entry = new JsonObject();
        entry.addProperty( "displayName", "Nahlásit hráča" );
        entry.addProperty( "type", EnumActionType.SUGGEST_COMMAND.name() );
        entry.addProperty( "value", "report {name} >reason<" );
        array.add(entry);

        // Send to LabyMod using the API
        LabyModPlugin.getInstance().sendServerMessage( player, "user_menu_actions", array );
    }

    public boolean Contains(String jsonLine, String data) {
        JsonElement jelement = new JsonParser().parse(jsonLine);
        JsonObject  jobject = jelement.getAsJsonObject();
        return jobject.has(data);
    }

    public String parse(String jsonLine, String data) {
        JsonElement jelement = new JsonParser().parse(jsonLine);
        JsonObject  jobject = jelement.getAsJsonObject();
        String result = jobject.get(data).getAsString();
        return result;
    }
}
