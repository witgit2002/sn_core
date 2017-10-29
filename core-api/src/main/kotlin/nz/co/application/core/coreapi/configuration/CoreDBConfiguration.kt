package nz.co.application.core.coreapi.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class CoreDBConfiguration
{
	@Value("\${core.main.datasource.username}")
	lateinit var coreUserName: String

	@Value("\${core.main.datasource.password}")
	lateinit var corePassword: String

	@Value("\${core.main.datasource.driver}")
	lateinit var coreDriver: String

	@Value("\${core.main.datasource.url}")
	lateinit var coreURL: String


	@Bean
	fun coreDataSource(): DataSource
	{
		val dataSourceBuilder: DataSourceBuilder = DataSourceBuilder.create()
				.driverClassName(coreDriver)
				.url(coreURL)
				.username(coreUserName)
				.password(corePassword)
		return dataSourceBuilder.build()
	}

}