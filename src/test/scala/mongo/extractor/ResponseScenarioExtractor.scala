package mongo.extractor

import io.gatling.commons.stats.Status
import io.gatling.http.request.ExtraInfo
import io.gatling.http.response.{Response, ResponseWrapper}
import mongo.wrapper.ResponseScenarioWrapper

class ResponseScenarioExtractor {

  private var _scenarioResponse = List[ResponseScenarioWrapper]()

  def extractResponse(scenarioName: String,finalStatus: Status): PartialFunction[Response,Response] = {
    case response if response.isReceived =>
      new ResponseWrapper(response) {
        _scenarioResponse = new ResponseScenarioWrapper(scenarioName,response,finalStatus) :: _scenarioResponse
      }
    case _ =>
      println("*** something else happened")
      null
  }

  private def _extractResponse(scenarioName: String, response: Response, finalStatus: Status): ResponseWrapper = {
    new ResponseWrapper(response) {
      _scenarioResponse = new ResponseScenarioWrapper(scenarioName,response,finalStatus) :: _scenarioResponse
    }
  }

  def extractLogInfo(extraInfo: ExtraInfo): List[ResponseWrapper] = {
    _extractResponse(extraInfo.requestName, extraInfo.response, extraInfo.status) :: Nil
  }



  def scenarioResponse = _scenarioResponse

}
