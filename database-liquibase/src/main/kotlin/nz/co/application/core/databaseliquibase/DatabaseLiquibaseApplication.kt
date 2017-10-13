package nz.co.application.core.databaseliquibase

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class DatabaseLiquibaseApplication

fun main(args: Array<String>) {
    SpringApplication.run(DatabaseLiquibaseApplication::class.java, *args)
}
