//import java.util.ArrayList;
//import java.util.List;
//
//import org.bson.Document;
//import org.junit.Test;
//
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientOptions;
//import com.mongodb.MongoClientURI;
//import com.mongodb.MongoCredential;
//import com.mongodb.ServerAddress;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;

public class SimpleTest {
	// @Test
	// public void startWithoutAuth() {
	// MongoClientURI uri = new MongoClientURI(
	// "mongodb://localhost:27017/local", MongoClientOptions.builder()
	// .cursorFinalizerEnabled(false));
	// MongoClient client = new MongoClient(uri);
	// MongoDatabase db = client.getDatabase("local");
	// MongoCollection<Document> collection = db.getCollection("system.users");
	// List<Document> foundDocument = collection.find().into(
	// new ArrayList<Document>());
	// System.out.println(foundDocument);
	// client.close();
	//
	// }
	//
	// @Test
	// public void startWithAuth() {
	// MongoClient client = null;
	// ServerAddress serverAddress = new ServerAddress("127.0.0.1", 27017);
	// List<ServerAddress> seeds = new ArrayList<ServerAddress>();
	// seeds.add(serverAddress);
	// MongoCredential credentials = MongoCredential
	// .createScramSha1Credential("admin", "admin",
	// "admin".toCharArray());
	// List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
	// credentialsList.add(credentials);
	// client = new MongoClient(seeds, credentialsList);
	// MongoDatabase db = client.getDatabase("admin");
	// // MongoIterable<Document> collections=db.listCollections();
	// MongoCollection<Document> collection = db.getCollection("system.users");
	// List<Document> foundDocument = collection.find().into(
	// new ArrayList<Document>());
	// System.out.println(foundDocument);
	// client.close();
	// }

	// @Test
	// public void main() {
	// Mongo mg = null;
	// try {
	// mg = new Mongo();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// // 查询所有的Database
	// for (String name : mg.getDatabaseNames()) {
	// System.out.println("dbName: " + name);
	// }
	//
	// DB db = mg.getDB("test");
	// // 查询所有的聚集集合
	// for (String name : db.getCollectionNames()) {
	// System.out.println("collectionName: " + name);
	// }
	//
	// DBCollection users = db.getCollection("users");
	//
	// // 查询所有的数据
	// DBCursor cur = users.find();
	// while (cur.hasNext()) {
	// System.out.println(cur.next());
	// }
	// System.out.println(cur.count());
	// System.out.println(cur.getCursorId());
	// System.out.println(JSON.serialize(cur));
	// }
}