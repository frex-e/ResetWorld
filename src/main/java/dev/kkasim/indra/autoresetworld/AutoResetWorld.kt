package dev.kkasim.indra.autoresetworld

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.WorldCreator
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.World
import org.bukkit.event.player.PlayerJoinEvent

class AutoResetWorld : JavaPlugin(), Listener {
    private var session : World? = null;

    override fun onEnable() {
        // Plugin startup logic
        session = this.server.createWorld(WorldCreator("main").hardcore(true))
        Bukkit.getServer().pluginManager.registerEvents(this,this)
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        if(!event.player.world.name.contains("main")) {
            event.player.teleport(session!!.spawnLocation)
        }
    }

    @EventHandler
    fun onPlayerDeath(event : PlayerDeathEvent) {

    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}