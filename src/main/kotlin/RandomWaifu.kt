package org.echoosx.mirai.plugin

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info
import org.echoosx.mirai.plugin.command.RandomWaifuCommand

object RandomWaifu : KotlinPlugin(
    JvmPluginDescription(
        id = "org.echoosx.mirai.plugin.random-waifu",
        name = "random-waifu",
        version = "0.1.0"
    ) {
        author("Echoosx")
    }
) {
    override fun onEnable() {
        RandomWaifuCommand.register()
        logger.info { "random-waifu loaded" }
    }

    override fun onDisable() {
        RandomWaifuCommand.unregister()
        logger.info{ "random-waifu stopped" }
    }
}
