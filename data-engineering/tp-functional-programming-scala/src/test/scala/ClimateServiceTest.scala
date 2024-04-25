import com.github.polomarcus.utils.ClimateService
import com.github.polomarcus.model.CO2Record
import org.scalatest.funsuite.AnyFunSuite

class ClimateServiceTest extends AnyFunSuite {
  test("containsWordGlobalWarming - non climate related words should return false") {
    assert(ClimateService.isClimateRelated("pizza") == false)
  }

  test("isClimateRelated - climate related words should return true") {
    assert(ClimateService.isClimateRelated("climate change") == true)
    assert(ClimateService.isClimateRelated("IPCC"))
  }

  test("parseRawData") {
    val firstRecord = (2003, 1, 355.2)
    val secondRecord = (2004, 1, 375.2)
    val inputData = List(firstRecord, secondRecord)

    val expectedOutput = List(
      Some(CO2Record(firstRecord._1, firstRecord._2, firstRecord._3)),
      Some(CO2Record(secondRecord._1, secondRecord._2, secondRecord._3))
    )

    val actualOutput = ClimateService.parseRawData(inputData)

    assert(actualOutput == expectedOutput)
  }

  test("filterDecemberData") {
    val testData = List(Some(CO2Record(2022, 11, 400.0)), Some(CO2Record(2022, 12, 410.0)))
    val expectedOutput = List(CO2Record(2022, 11, 400.0))

    val actualOutput = ClimateService.filterDecemberData(testData)

    assert(actualOutput == expectedOutput)
  }
}


