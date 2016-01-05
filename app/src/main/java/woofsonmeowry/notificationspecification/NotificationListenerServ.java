package woofsonmeowry.notificationspecification;

import android.app.Notification;
import android.content.Intent;
import android.media.MediaPlayer;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.content.LocalBroadcastManager;

public class NotificationListenerServ extends NotificationListenerService {
    private final int ONGOING_NOTIFICATIONLISTENER_ID = 1;
    private static final String[] maryAliases = {"mary","mikasa","meow","zhang"};
    private MediaPlayer player = null;


    @Override
    public int onStartCommand(Intent intent, int flags, int startid)
    {
        Notification notification = new Notification.Builder(this)
                .setContentTitle("Mary Alarm: Active")
                .build();
        startForeground(ONGOING_NOTIFICATIONLISTENER_ID, notification);
        return 1;
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        System.out.println("Notif Received");
        String str = sbn.getNotification().tickerText.toString();
        Object title = sbn.getNotification().extras.get(Notification.EXTRA_TITLE);
        if (title != null)
        {
            title = title.toString();
            str += title;
        }
        System.out.println(str);
        if(containsMary(str))
        {
            Intent intent = new Intent("mary-event");
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn)
    {
        String str = sbn.getNotification().tickerText.toString();
        Object title = sbn.getNotification().extras.get(Notification.EXTRA_TITLE);
        if (title != null)
        {
            title = title.toString();
            str += title;
        }
        System.out.println(str);
        if(containsMary(str))
        {
            player.stop();
        }
    }

    private boolean containsMary(String str)
    {
        for(String alias : maryAliases)
        {
            if(str.contains(alias))
                return true;
        }
        return false;
    }

}
