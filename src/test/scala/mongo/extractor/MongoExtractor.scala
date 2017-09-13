package mongo.extractor

import io.gatling.http.request.ExtraInfoExtractor

trait MongoExtractor {

  private var _responseScenarioExtractor = new ResponseScenarioExtractor

  def extractLogResponse(): ExtraInfoExtractor = {
    (extraInfo) => _responseScenarioExtractor.extractLogInfo(extraInfo)
  }

  def responseScenarioExtractor = _responseScenarioExtractor

}
