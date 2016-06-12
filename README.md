# LightningDB

LightningDB, an Annotation based ORM that help's you in creating Database for your application in a **SplitSecond**. No need to write multiple queries for *CRUD* operations, neither you have to create a `class` that will `extends` the `SQLiteOpenHelper`. *LightningDB* have your back.

## Configuring LightningDB
- Copy `lightning.jar` into the `libs` folder.
- Add `classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'` into `build.gradle` of project.
```
dependencies {
    classpath 'com.android.tools.build:gradle:2.2.0-alpha2'
    classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
}
```
- Add `apply plugin: 'com.neenbedankt.android-apt'` into `build.gradle` of app.
```
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:appcompat-v7:23.4.0'
    apply plugin: 'com.neenbedankt.android-apt'
}
```
- Enable **Annotation Processing** under *Default Settings* > *Build, Execution, Deployment* > *Compiler* > *Annotation Processors*.

## Creating Table
Creating a table is fairly easy with LightningDB, only thing it requires is a *POJO* `class`.

For this case we'll be creating a *POJO* `class` **User** with some *Getter and Setter* in it and will see how magically LightningDB will create a table & corresponding `UserTable` `class` through which one can access all the *CRUD* operations for that table.
```
@LightningTable(name = "user", primaryKey = "userId", autoIncrement = "userId")
public class User {
	private int userId;
	private String userName;
	private String password;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
```
Here the Annotation ``@LightningTable`` does all the magic, it get's all the fieds from the `class`, creates a table & `class` for it.

**Note:** A table name must be passed inside `name` parameter of `@LightningTable`.

## Using LightningDB
After creating the *POJO* & adding `@LightningTable` to it, **Rebuild** your *Project*, for the changes to take effect.

#### Insertion
```
UserTable userTable = new UserTable(getApplicationContext());
User user = new User();
user.setUserName("admin");
user.setPassword("1234");
userTable.insert(user);
```
#### Updation
```
UserTable userTable = new UserTable(getApplicationContext());
User user = new User();
user.setPassword("12345678");
userTable.update(user, UserTable.COLUMN_USERID +" = 1");
```
**Note:** LightningDB also creates constants for columns, so one should not have to remember the names.


#### Deletion
```
UserTable userTable = new UserTable(getApplicationContext());
userTable.delete(UserTanle.COLUMN_USERID + " = 1");
```
#### Fetching records
```
UserTable userTable = new UserTable(getApplicationContext());
List<User> users = userTable.get();
List<User> users = userTable.get(UserTable.COLUMN_PASSWORD + " = '1234'");
```
#### Clear Table
```
UserTable userTable = new UserTable(getApplicationContext());
userTable.clear();
```
### Version
1.0
