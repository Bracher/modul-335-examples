package ch.nyp.databaseexample.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Model-Klasse für einen Benutzer.
 *
 * Diese Klasse und deren Attribute sind mit Annotations als Datenbank-Tabelle,
 * resp. Datenbank-Feld gekennzeichnet. Das bedeutet, dass dieses Model mit Hilfe von ORM-Lite in
 * eine Tabelle in der SQLite-Datenbank auf dem Smartphone abgebildet wird.
 *
 * History:
 * 18.11.2016	1.0	Joel Holzer. Klasse erstellt.
 *
 * @author Joel Holzer
 * @version 1.0
 */
@DatabaseTable(tableName = "user")
public class User{

	//Autoincrement
	@DatabaseField(generatedId = true)
	public int id;

	@DatabaseField
	public String accountName;

	@DatabaseField
	public String firstName;

	@DatabaseField
	public String middleName;

	@DatabaseField
	public String lastName;

	@DatabaseField
	public String street;

	@DatabaseField
	public String email;

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
