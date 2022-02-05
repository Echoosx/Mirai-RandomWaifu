package org.echoosx.mirai.plugin.utils

import com.google.gson.Gson
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.load
import org.echoosx.mirai.plugin.RandomWaifu
import org.jsoup.Jsoup
import java.io.File

class Waifu(name: String, from:String, img:String, detail: String) {
    var name: String? = name
    var from: String? = from
    var img: String? = img
    var detail: String? = detail
}

//fun buildWaifuLib(){
//    val page = 0
//    try{
//        val rootPath = "http://www.chuanxincao.net"
//        var waifuList = arrayListOf<Waifu>()
//
//        for(page in 301..400) {
//            if(page%10==0)
//                RandomWaifu.logger.info("${page}/507")
//            val document = Jsoup.connect("${rootPath}/renwu/${page}-1-0-0-0-0-0-0-0-0-0-0-0/").get()
//            val characters = document.select("div.boxgrid.captionfull")
//            for (chat in characters) {
//                try {
//                    val name = chat.select("img").attr("title")
//                    val from = chat.select("div.cover.bg-text.bg-top100>a>div")[1].text()
//                    val img = chat.select("img").attr("src")
//                    val detail = rootPath + chat.select("div.cover.bg-text.bg-top100>a").attr("href")
//
//                    val waifu = Waifu(name,from,img,detail)
//                    waifuList.add(waifu)
//                }catch (e:Exception){
//                    continue
//                }
//            }
//        }
//        File("waifu_301-400.json").writeText(Gson().toJson(waifuList))
//
//    }catch(e:Throwable){
//        e.printStackTrace()
//    }
//}