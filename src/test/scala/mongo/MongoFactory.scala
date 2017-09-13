package mongo

import com.mongodb.MongoClient
import com.mongodb.client.{MongoCollection, MongoDatabase}
import org.bson.Document

object MongoFactory {

  private var _mongoClient: MongoClient = new MongoClient()
  private var _database: MongoDatabase = _mongoClient.getDatabase("simulation")
  private var _collection: MongoCollection[Document] = _database.getCollection("test")

  def save(document: Document) = _collection.insertOne(document)

  def findFirst: Document = _collection.find().first()

  def close = _mongoClient.close()
}
