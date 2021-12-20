package org.echoosx.mirai.plugin.command

import io.ktor.client.*
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.contact.Contact.Companion.sendImage
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.message.data.buildMessageChain
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import okhttp3.OkHttpClient
import okhttp3.Request
import org.echoosx.mirai.plugin.RandomWaifu
import java.time.LocalDate
import kotlin.math.abs
import kotlin.random.Random

object RandomWaifuCommand: SimpleCommand(
    RandomWaifu,
    "waifu","今日老婆",
    description = "随机二次元老婆"
) {
    override val prefixOptional: Boolean = true
    @Handler
    suspend fun CommandSender.handle(){
        val localDate = LocalDate.now()
        val random = abs(Random(user!!.id + localDate.year + localDate.monthValue + localDate.dayOfMonth).nextInt()) % 100001
        val waifuUrl = "https://www.thiswaifudoesnotexist.net/example-${random}.jpg"

        val image_ = async {
            val client = OkHttpClient()
            val request = Request.Builder().url(waifuUrl).get()
            val response = client.newCall(request.build()).execute()
            val waifuImage = response.body!!.byteStream()
            waifuImage.toExternalResource().use { it.uploadAsImage(user!!) }
        }
        val mutex = Mutex()
        mutex.withLock {
            sendMessage(At(user!!)+image_.await())
            // 延迟5s
            delay(5000)
        }
    }
}