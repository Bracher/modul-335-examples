package ch.nyp.databaseexample;

import android.app.Application;

import ch.nyp.databaseexample.persistence.DatabaseHelper;

/**
 * Hauptklasse der Android-Applikation.
 * Diese wird beim Starten der App als Erstes aufgerufen.
 * In unserem Fall erstellt diese Klasse immer beim Start der App eine Instanz der Klasse {@link
 * ch.nyp.databaseexample.persistence.DatabaseHelper} um immer beim App-Start die
 * App-spezifische SQLite-Datenbank auf dem Smartphone zu aktualisieren.
 *
 * History:
 * 18.11.2016	1.0	Joel Holzer. Klasse erstellt.
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class DatabaseExampleApplication extends Application {

	/**
	 * Methode wird beim Starten dieser Klasse, d.h. beim Starten der App aufgerufen.
	 * Ruft den Konstruktor der Super-Klasse auf und erstellt eine Instanz der Klasse {@link
	 * ch.nyp.databaseexample.persistence.DatabaseHelper} um die App-spezifische SQLite-Datenbank auf dem
	 * Smartphone zu aktualisieren.
	 *
	 * @since 1.0
	 */
	@Override
	public void onCreate() {
		super.onCreate();

		//Datenbank aktualisieren
		DatabaseHelper.getInstance(this);
	}
}
