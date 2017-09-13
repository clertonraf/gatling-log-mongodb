package mongo.wrapper

import io.gatling.commons.stats.Status
import io.gatling.http.response.{Response, ResponseWrapper}
import org.bson.Document

class ResponseScenarioWrapper(name: String, delegate: Response, finalStatus: Status) extends ResponseWrapper(delegate: Response) {

  var scenarioName: String = name

  def buildMongoDbObject(): Document = {
    new Document("name",scenarioName)
      .append("method",delegate.request.getMethod())
      .append("url",delegate.request.getUrl())
      .append("start",delegate.timings.startTimestamp)
      .append("end",delegate.timings.endTimestamp)
      .append("responseTime",delegate.timings.responseTime)
      .append("httpStatusCode",delegate.statusCode.get)
      .append("isRedirect",delegate.isRedirect)
      .append("responseBody",delegate.body.string)
      .append("status",finalStatus.name)
  }

}
