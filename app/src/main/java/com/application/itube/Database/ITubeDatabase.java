package com.application.itube.Database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.application.itube.DataAccessObjects.PlaylistDAO;
import com.application.itube.DataAccessObjects.UsersDAO;
import com.application.itube.DataModels.Playlist;
import com.application.itube.DataModels.User;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Playlist.class}, version = 2)
public abstract class ITubeDatabase extends RoomDatabase{

    // Keep track of the single instance of this database
    private static volatile ITubeDatabase INSTANCE;

    // Our DAO containing our queries
    public abstract UsersDAO usersDao();
    public abstract PlaylistDAO playlistDAO();

    // Executor service that will be needed for queries to run on a background thread
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // Implementation of the singleton pattern
    public static ITubeDatabase getDatabase(final Context context) {

        if (INSTANCE == null){
            synchronized (ITubeDatabase.class) {
                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ITubeDatabase.class, "iTube_database").build();
                }
            }
        }

        return INSTANCE;
    }
}
