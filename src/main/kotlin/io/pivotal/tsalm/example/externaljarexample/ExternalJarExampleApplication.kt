package io.pivotal.tsalm.example.externaljarexample

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.sql.Driver


@SpringBootApplication
class ExternalJarExampleApplication : CommandLineRunner {

    private val log = LoggerFactory.getLogger(ExternalJarExampleApplication::class.java)

    override fun run(vararg args: String?) {
        log.info("Try to use external sql driver ...")
        try {
            val driver = Class.forName(org.h2.Driver::class.qualifiedName).getConstructor().newInstance() as Driver
            log.info("-------------> Found driver with version: " + driver.majorVersion + "." + driver.minorVersion)
        } catch (e: NoClassDefFoundError) {
            log.info("No driver found!!!")
        }

        Thread.currentThread().join()
    }
}

fun main(args: Array<String>) {
    runApplication<ExternalJarExampleApplication>(*args)
}
