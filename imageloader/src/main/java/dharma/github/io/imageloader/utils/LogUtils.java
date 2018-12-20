package dharma.github.io.imageloader.utils;

import android.util.Log;

import java.util.ArrayList;

/**
 * LogUtils
 */
public class LogUtils {

    private static final String LOG_TAG = "ImageLoader";
    private LogInfo mLogInfo;
    private static boolean isLog = false;//Do not print logs by default

    private LogUtils(LogInfo logInfo) {
        this.mLogInfo = logInfo;
    }

    public static void switchLog(boolean isLog) {
        LogUtils.isLog = isLog;
    }

    /**
     * Create log
     *
     * @return Log information
     */
    public static LogInfo log() {
        return new LogInfo();
    }

    /**
     * Print log queue
     */
    public void execute() {
        if (isLog) {
            for (String msg : mLogInfo.msgList) {
                android.util.Log.println(mLogInfo.priority, mLogInfo.tag, mLogInfo.mark + msg);
            }
        }
    }

    /**
     * Log information
     */
    public static class LogInfo {

        /**
         * mark
         */
        String mark;

        /**
         * Print level
         */
        int priority = Log.INFO;

        /**
         * Log tag
         */
        String tag = LOG_TAG;

        /**
         * Log queue
         */
        ArrayList<String> msgList;

        /**
         * Set log tag
         *
         * @param tag mark
         * @return Log information
         */
        public LogInfo tag(String tag) {
            this.tag = tag;
            return this;
        }

        /**
         * Add log
         *
         * @param msg Log content
         * @return Log information
         */
        public LogInfo addMsg(String msg) {
            if (msgList == null) {
                msgList = new ArrayList<>();
            }
            msgList.add(msg);
            return this;
        }

        /**
         * Set the log level
         *
         * @param priority Log level
         * @return Log information
         */
        public LogInfo priority(int priority) {
            this.priority = priority;
            return this;
        }

        /**
         * Generate log information tool class
         *
         * @return Log information tool class
         */
        public LogUtils build() {
            int hashCode = (msgList.get(0) + System.currentTimeMillis()).hashCode();
            mark = "(" + Integer.toHexString(hashCode) + ")";
            return new LogUtils(this);
        }

    }

}