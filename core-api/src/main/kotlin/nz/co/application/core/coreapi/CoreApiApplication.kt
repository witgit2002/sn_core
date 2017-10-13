package nz.co.application.core.coreapi

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class CoreApiApplication

fun main(args: Array<String>) {
    SpringApplication.run(CoreApiApplication::class.java, *args)
}
