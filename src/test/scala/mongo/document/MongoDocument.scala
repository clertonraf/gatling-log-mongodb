package mongo.document

import java.util.Date

import io.gatling.core.structure.ScenarioBuilder
import mongo.extractor.MongoExtractor
import org.bson.Document

class MongoDocument(scenario: ScenarioBuilder) {

  private var _scenarioLst: java.util.List[Document] = new java.util.ArrayList[Document]()

  private var _root: Document = new Document("_id",new Date().getTime)

  def addScenario(simulation: MongoExtractor, scenarioName: String): Boolean = {
    var lst: java.util.List[Document] = new java.util.ArrayList[Document]()

    val loginScenario = simulation.responseScenarioExtractor.scenarioResponse.reverse.foreach(
      scenario => lst.add(scenario.buildMongoDbObject())
    )

    _scenarioLst.add(new Document(scenarioName,lst))

  }

  def build: Document = {
    _root.append("testName",scenario.name)
    _root.append("testScenarios",_scenarioLst)
  }

}