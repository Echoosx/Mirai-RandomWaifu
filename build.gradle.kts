plugins {
    val kotlinVersion = "1.6.0"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.9.2"
}

group = "org.echoosx"
version = "1.1.1"

repositories {
    mavenLocal()
    maven("https://maven.aliyun.com/repository/public") // 阿里云国内代理仓库
    mavenCentral()
}
dependencies {
    implementation("org.jsoup:jsoup:1.14.3")
    implementation("com.google.code.gson:gson:2.8.9")
}
