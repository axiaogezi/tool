package date;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class DateUtils {
    public static final String TIMEZONE = "GMT+8";

    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_CHINESE = "yyyy年MM月dd日";
    public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String HHMMSS = "HH:mm";
    public static final String HHMM = "HH:mm";

    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String DAY = "day";
    public static final String HOUR = "hour";
    public static final String MINUTE = "minute";
    public static final String SECOND = "second";
    public static final String DAY_OF_WEEK = "day_of_week";//一周中的第几天

    public static final long ONE_MINUTE = 60;
    public static final long ONE_HOUR = 3600;
    public static final long ONE_DAY = 86400;
    public static final long ONE_MONTH = 2592000;
    public static final long ONE_YEAR = 31104000;

    public static Date formatDate(String date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);//小写的mm表示的是分钟
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            log.error("格式化时间错误{}", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前时间
     * @return
     */
    public static Date getNowDate(){
        return Calendar.getInstance().getTime();
    }

    /**
     * 获取今天的开始时间
     * @return
     */
    public static Date getTodayBegin(){
        SimpleDateFormat formater = new SimpleDateFormat(YYYYMMDDHHMMSS);
        Calendar c = getBegin();
        String first = formater.format(c.getTime());
        return formatDate(first, YYYYMMDDHHMMSS);
    }

    /**
     * 获取今天的结束时间
     * @return
     */
    public static Date getTodayEnd(){
        SimpleDateFormat formater = new SimpleDateFormat(YYYYMMDDHHMMSS);
        Calendar c = getEnd();
        String first = formater.format(c.getTime());
        return formatDate(first, YYYYMMDDHHMMSS);
    }

    /**
     * 获取这周的开始时间
     * @return
     */
    public static Date getWeekBegin(){
        SimpleDateFormat formater = new SimpleDateFormat(YYYYMMDDHHMMSS);
        Calendar c = getBegin();
        c.set(Calendar.DAY_OF_WEEK, 1);
        String first = formater.format(c.getTime());
        return formatDate(first, YYYYMMDDHHMMSS);
    }

    /**
     * 获取这周的结束时间
     * @return
     */
    public static Date getWeekEnd(){
        SimpleDateFormat formater = new SimpleDateFormat(YYYYMMDDHHMMSS);
        Calendar c = getEnd();
        c.set(Calendar.DAY_OF_WEEK, 7);
        String first = formater.format(c.getTime());
        return formatDate(first, YYYYMMDDHHMMSS);
    }

    /**
     * 获取这各月的开始时间
     * @return
     */
    public static Date getMonthBegin(){
        SimpleDateFormat formater = new SimpleDateFormat(YYYYMMDDHHMMSS);
        Calendar c = getBegin();
        c.set(Calendar.DAY_OF_MONTH, 1);
        String first = formater.format(c.getTime());
        return formatDate(first, YYYYMMDDHHMMSS);
    }

    /**
     * 获取这各月的结束时间
     * @return
     */
    public static Date getMonthEnd(){
        SimpleDateFormat formater = new SimpleDateFormat(YYYYMMDDHHMMSS);
        Calendar c = getEnd();
        c.set(Calendar.DAY_OF_MONTH, c.getMaximum(Calendar.DAY_OF_MONTH));
        String first = formater.format(c.getTime());
        return formatDate(first, YYYYMMDDHHMMSS);
    }
    private static Calendar getBegin(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c;
    }
    private static Calendar getEnd(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c;
    }
}
