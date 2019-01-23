package dharma.github.io.mvvmsample.data.api;

import dharma.github.io.mvvmsample.data.database.DBHelper;
import dharma.github.io.mvvmsample.data.prefs.PreferenceHelper;

public interface DataManager extends DBHelper, PreferenceHelper, ApiService {

    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_LOGGED_GOOGLE(1),
        LOGGED_IN_MODE_LOGGED_FB(2),
        LOGGED_IN_MODE_LOGGED_SERVER(3);

        private final int type;

        LoggedInMode(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }
    }
}
