package nz.co.application.core.coreapi.idGenerator

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IdGeneratorController
{
	@Autowired
	lateinit var idGeneratorDAO: IdGeneratorDAO

	val DEFAULT_RANGE_SIZE = 100
	private var currentValue: Long = 0
	private var maxValue: Long = 0

	@GetMapping("/getNextId")
	fun getNextId(): Long
	{
		if (currentValue >= maxValue)
		{
			maxValue = idGeneratorDAO.loadNewRange(rangeSize = DEFAULT_RANGE_SIZE)
			currentValue = maxValue - DEFAULT_RANGE_SIZE
		}

		return currentValue++
	}

}