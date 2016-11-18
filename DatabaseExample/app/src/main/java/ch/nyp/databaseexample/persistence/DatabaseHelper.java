package ch.nyp.databaseexample.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import ch.nyp.databaseexample.logger.AppLogger;
import ch.nyp.databaseexample.model.User;

/**
 * Hilfsklasse, welche benötigt wird, um mit einer SQLite-Datenbank auf dem Smartphone zu
 * arbeiten. Diese Hilfsklasse beinhaltet die Methoden zum Erstellen und Aktualisieren dieser
 * SQLite-Datenbank. Zudem wird die Instanz dieser Klasse benötigt,
 * um in die Datenbank zu schreiben oder aus dieser zu lesen.
 *
 * Diese Klasse wurde als Singleton erstellt. Nur eine Instanz dieser Klasse kann existieren.
 *
 * History:
 * 18.11.2016	1.0	Joel Holzer. Klasse erstellt.
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "databaseExample.db";
	private static final int DATABASE_VERSION = 1;

	private static DatabaseHelper sInstance;

	/**
	 * Konstruktor. Ruft den Konstruktor der Super-Klasse auf, welcher je nach Wert der Konstante
	 * DATABASE_VERSION die Methode onCreate oder onUpgrade dieser Klasse aufruft um die
	 * Datenbank zu erstellen oder zu aktualisieren.
	 *
	 * Existiert noch keine SQLite-Datenbank für die App auf dem Smartphone,
	 * auf welchem die App ausgeführt wird, so wird onCreate aufgerufen. Existiert bereits eine
	 * SQLite-Datenbank, jedoch eine mit einer älteren Version, so wird onUpgrade aufgerufen.
	 *
	 * @param context Kontext der Applikation, d.h. das aktive Activity der App.
	 * @since 1.0
	 */
	private DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * Gibt die aktive Instanz dieser Klasse zurück. Existiert noch keine Instanz,
	 * so wird eine neue Instanz erzeugt und diese zurückgegeben.
	 *
	 * @param context Kontext der Applikation, d.h. das aktive Activity der App.
	 * @return Aktive Instanz dieser Klasse.
	 * @since 1.0
	 */
	public static DatabaseHelper getInstance(Context context) {
		if (sInstance == null) {
			sInstance = new DatabaseHelper(context.getApplicationContext());
		}
		return sInstance;
	}

	/**
	 * Erstellt die Datenbanktabellen der SQLite-Datenbank auf dem Smartphone. Erstellt werden
	 * folgende Tabellen:
	 * - User
	 * - Unterkompetenz
	 *
	 * Update V1.1: Logging hinzugefügt.
	 *
	 * @param database Datenbank, in welcher die Tabellen erstellt werden sollen.
	 * @param connectionSource Verbindung zur Datenbank.
	 * @since 1.1
	 */
	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, User.class);
		} catch (SQLException e) {
			AppLogger.getInstance().logException("DatabaseHelper.onCreate",
					"SQLite-Datenbank konnte nicht erstellt werden.", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Aktualisiert die SQLite-Datenbank auf dem Smartphone. Dabei werden alle Datenbanktabellen
	 * gelöscht und neu erstellt.
	 *
	 * @param database Datenbank, in welcher die Tabellen aktualisiert werden sollen.
	 * @param connectionSource Verbindung zur Datenbank.
	 * @param oldVersion Alte Version der Datenbank. Wird nicht verwendet.
	 * @param newVersion Neue Version der Datenbank. Wird nicht verwendet.
	 * @since 1.0
	 */
	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, User.class, true);
			onCreate(database, connectionSource);
		} catch (SQLException e) {
			AppLogger.getInstance().logException("DatabaseHelper.onUpgrade",
					"SQLite-Datenbank konnte nicht aktualisiert werden.", e);
			throw new RuntimeException(e);
		}
	}
}
