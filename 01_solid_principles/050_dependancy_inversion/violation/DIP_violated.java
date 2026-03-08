
// Here, the main class have 2-has-a relationship with SqlDatabase class and mongo database class.
// so if lets say we have to remove mongodb and introduce cassandra db, then we have to break open close princinple.
// so its better to have a single interface between main class and all database related operations.
class MySQLDatabase {  // Low-level module
    public void saveToSQL(String data) {
        System.out.println(
                "Executing SQL Query: INSERT INTO users VALUES('"
                        + data + "');"
        );
    }
}

class MongoDBDatabase {  // Low-level module
    public void saveToMongo(String data) {
        System.out.println(
                "Executing MongoDB Function: db.users.insert({name: '"
                        + data + "'})"
        );
    }
}

class UserService {  // High-level module (Tightly coupled)
    // VIOLATION
    private final MySQLDatabase sqlDb = new MySQLDatabase();
    private final MongoDBDatabase mongoDb = new MongoDBDatabase();

    public void storeUserToSQL(String user) {
        // MySQL-specific code
        sqlDb.saveToSQL(user);
    }

    public void storeUserToMongo(String user) {
        // MongoDB-specific code
        mongoDb.saveToMongo(user);
    }
}

public class DIPViolated {
    public static void main(String[] args) {
        UserService service = new UserService();
        service.storeUserToSQL("Aditya");
        service.storeUserToMongo("Rohit");
    }
}