package ch.nyp.databaseexample.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Model-Klasse für einen Benutzer.
 *
 * Diese Klasse und deren Attribute sind mit Annotations als Datenbank-Tabelle,
 * resp. Datenbank-Feld gekennzeichnet. Das bedeutet, dass dieses Model mit Hilfe von Room in
 * eine Tabelle in der SQLite-Datenbank auf dem Smartphone abgebildet wird.
 *
 * History:
 * 18.11.2016	1.0	Joel Holzer. Klasse erstellt.
 *
 * @author Joel Holzer
 * @version 1.0
 */
@Entity
public class User{

	//Autoincrement
	@PrimaryKey(autoGenerate = true)
	public int id;

	@ColumnInfo
	public String accountName;

	@ColumnInfo
	public String firstName;

	@ColumnInfo
	public String middleName;

	@ColumnInfo
	public String lastName;

	@ColumnInfo
	public String street;

	@ColumnInfo
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
