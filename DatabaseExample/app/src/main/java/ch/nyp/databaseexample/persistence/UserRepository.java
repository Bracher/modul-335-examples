package ch.nyp.databaseexample.persistence;

import android.app.Activity;
import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import ch.nyp.databaseexample.model.User;

/**
 * Beinhaltet Methoden, welche mit der User-Tabelle der SQLite-Datenbank auf dem Gerät
 * operieren (z.B. User speichern, User ermitteln).
 *
 * History:
 * 18.11.2016	1.0	Joel Holzer. Klasse erstellt.
 *
 * @see ch.nyp.databaseexample.model.User
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class UserRepository {
	private Dao<User, Void> mUserDao;
	private Context mContext;

	/**
	 * Konstruktor. Erzeugt das Data Access Object (Dao) für die User-Datenbanktabelle.
	 * Dieses wird benötigt, um später über dieses Data Access Object mit der
	 * User-Datenbanktabelle zu operieren.
	 *
	 * @param context Kontext der Applikation, d.h. das aktive Activity der App.
	 * @throws SQLException Exception, welche beim Erstellen des Data Access Objects auftreten kann.
	 * @since 1.0
	 */
	public UserRepository(Activity context) throws SQLException {
		mUserDao = DaoManager.createDao(DatabaseHelper.getInstance(context).getConnectionSource(), User.class);
		mContext = context;
	}

	/**
	 * Speichert alle User in der übergebenen Liste in der Datenbank. Zuerst wird allerdings die
	 * Tabelle mit den User gelöscht.
	 *
	 * @param users Liste von {@link User}-Objekten, welche in der Datenbank gespeichert werden
	 * sollen.
	 * @throws SQLException Exception welche beim Speichern aufgetreten ist.
	 * @since 1.0
	 */
	public void saveUsers(List<User> users) throws SQLException {
		TableUtils.clearTable(DatabaseHelper.getInstance(mContext).getConnectionSource(),
				User.class);
		for (User user : users) {
			mUserDao.createOrUpdate(user);
		}
	}

	/**
	 * Ermittelt alle Users aus der Datenbank und gibt diese in einer Liste von {@link
	 * User}-Objekten zurück.
	 *
	 * @return Liste aller Users in der Datenbank.
	 * @throws SQLException Exception welche beim Lesen aus der Datenbank aufgetreten ist.
	 * @since 1.0
	 */
	public List<User> getUsers() throws SQLException {
		QueryBuilder<User, Void> queryBuilderUser = mUserDao.queryBuilder();
		return queryBuilderUser.query();
	}
}
