package ch.nyp.databaseexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import ch.nyp.databaseexample.logger.AppLogger;
import ch.nyp.databaseexample.model.User;
import ch.nyp.databaseexample.persistence.DatabaseHelper;
import ch.nyp.databaseexample.persistence.UserRepository;

/**
 * Einstiegsactivity.
 * Beinhaltet zwei Buttons, ein Button zum Speichern von 10 Users in der Datenbank, ein zweiter
 * Button zum Anzeigen aller bereits in der Datenbank gespeicherten Users.
 *
 * History:
 * 18.11.2016	1.0	Joel Holzer. Klasse erstellt.
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

	private UserRepository mUserRepository;

	/**
	 * OnClickListener für den Button "Save 10 Users".
	 */
	private View.OnClickListener mSaveUsersOnClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View openActivityButton) {
			List<User> usersToSave = new ArrayList<>();

			for (int i = 0; i < 10; i++) {
				User user = new User();
				user.firstName = "Vorname" + i;
				user.lastName = "Nachname" + i;
				usersToSave.add(user);
			}

			try {
				mUserRepository.saveUsers(usersToSave);
			} catch (SQLException e) {
				AppLogger.getInstance().logException("MainActivity.mSaveUsersOnClickListener",
						"Users konnten nicht in die DB gespeichert werden", e);
			}
		}
	};

	/**
	 * OnClickListener für den Button "Users anzeigen".
	 */
	private View.OnClickListener mDisplayUsersOnClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View openActivityButton) {
			try {
				List<User> usersFromDatabase = mUserRepository.getUsers();
				String textToDisplay = "";
				for (User user : usersFromDatabase) {
					textToDisplay += "ID: " + user.getId() + "; Vorname: " + user.getFirstName()
							+ "; Nachname: " + user.getLastName() + "\n";
				}
				Toast.makeText(MainActivity.this, textToDisplay , Toast.LENGTH_SHORT).show();
			} catch (SQLException e) {
				AppLogger.getInstance().logException("MainActivity.mDisplayUsersOnClickListener",
						"Users konnten nicht aus der DB geladen werden", e);
			}
		}
	};

	/**
	 * Wird beim Starten des Activity ausgeführt.
	 * Verbindet den JavaCode mit dem XML und initialisiert die Instanzvariablen.
	 *
	 * @param savedInstanceState Status des Activity, falls dieses von einem vorher
	 * 							 gespeicherten Status wiederhergestellt wurde.
	 * @since 1.0
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button saveUserButton = (Button) findViewById(R.id.button_main_saveUsers);
		saveUserButton.setOnClickListener(mSaveUsersOnClickListener);

		Button displayUsersButton = (Button) findViewById(R.id.button_main_displayUsers);
		displayUsersButton.setOnClickListener(mDisplayUsersOnClickListener);

		try {
			mUserRepository = new UserRepository(this);
		} catch (SQLException e) {
			AppLogger.getInstance().logException("MainActivity.onCreate", "UserRepository konnte " +
					"nicht erzeugt werden", e);
		}
	}
}
