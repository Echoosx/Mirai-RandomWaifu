package org.echoosx.mirai.plugin

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info
import org.echoosx.mirai.plugin.command.RandomWaifuCommand
import org.echoosx.mirai.plugin.command.TodayWaifuCommand

object RandomWaifu : KotlinPlugin(
    JvmPluginDescription(
        id = "org.echoosx.mirai.plugin.random-waifu",
        name = "random-waifu",
        version = "1.1.1"
    ) {
        author("Echoosx")
    }
) {
    override fun onEnable() {
        TodayWaifuCommand.register()
        RandomWaifuCommand.register()
        logger.info { "random-waifu loaded" }
    }

    override fun onDisable() {
        TodayWaifuCommand.unregister()
        RandomWaifuCommand.unregister()
        logger.info{ "random-waifu stopped" }
    }
}
