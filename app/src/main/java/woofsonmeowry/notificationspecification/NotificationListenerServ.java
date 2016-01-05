package woofsonmeowry.notificationspecification;

import android.app.Notification;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

public class NotificationListenerServ extends NotificationListenerService {
    private final int ONGOING_NOTIFICATIONLISTENER_ID = 1;
    private static final String[] maryAliases = {"mary","mikasa","meow","zhang"};
    private AssetFileDescriptor afd = null;
    private MediaPlayer player = null;


    public NotificationListenerServ()
    {
        super();
        try
        {
            afd = getAssets().openFd("AudioFile.mp3");
            player = new MediaPlayer();
            player.setDataSource(afd.getFileDescriptor());
            player.prepare();
        }
        catch(Exception e)
        {
            System.out.println("Media Error");
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startid)
    {
        System.out.println("onStart1");
        Notification notification = new Notification.Builder(this)
                .setContentTitle("Mary Alarm: Active")
                .build();
        NotificationListenerServ notifListen = new NotificationListenerServ();
        notifListen.startForeground(ONGOING_NOTIFICATIONLISTENER_ID, notification);
        System.out.println("onStart2");
        return 1;
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
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
            player.start();
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
