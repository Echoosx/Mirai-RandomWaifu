package org.echoosx.mirai.plugin.command

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.console.command.descriptor.ExperimentalCommandDescriptors
import net.mamoe.mirai.console.util.ConsoleExperimentalApi
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import okhttp3.OkHttpClient
import okhttp3.Request
import org.echoosx.mirai.plugin.RandomWaifu
import org.echoosx.mirai.plugin.utils.Waifu
import java.lang.reflect.Type

object RandomWaifuCommand: SimpleCommand(
    RandomWaifu,
    "randomwaifu","随机老婆",
    description = "随机老婆"
) {
    var waifuList:List<Waifu> = listOf()
    init {
        val jsonString = RandomWaifu.dataFolder.resolve("waifu.json").readText()
        val waifuListType: Type = object : TypeToken<ArrayList<Waifu>>() {}.type
        waifuList = Gson().fromJson(jsonString,waifuListType)
    }

    @OptIn(ConsoleExperimentalApi::class, ExperimentalCommandDescriptors::class)
    override val prefixOptional: Boolean = true

    @Suppress("unused")
    @Handler
    suspend fun CommandSender.handle(){
        try {
            val waifu = waifuList.random()

            val waifuUrl = waifu.img
            val image_ = coroutineScope {
                async {
                    val client = OkHttpClient()
                    val request = Request.Builder().url(waifuUrl!!).get()
                    val response = client.newCall(request.build()).execute()
                    val waifuImage = response.body!!.byteStream()
                    waifuImage.toExternalResource().use { it.uploadAsImage(user!!) }
                }
            }
            val mutex = Mutex()
            mutex.withLock {
                // 延迟1s
                delay(1000)
                sendMessage(At(user!!) + image_.await() + "${waifu.from}${waifu.name}")
            }
        }catch (e:Exception){
            sendMessage(At(user!!) + "获取失败")
        }
    }
}