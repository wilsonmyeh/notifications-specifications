package woofsonmeowry.notificationspecification;

import android.app.Notification;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

public class NotificationListenerServ extends NotificationListenerService {
    private static final String[] maryAliases = {"mary","mikasa","meow","zhang"};
    private final Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    private final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        String str = sbn.getNotification().tickerText.toString();
        Object title = sbn.getNotification().extras.get(Notification.EXTRA_TITLE);
        if (title != null)
        {
            title = title.toString();
            str += title;
        }
        if(containsMary(str))
            r.play();
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn)
    {

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
