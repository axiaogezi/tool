package date;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DateUtilsTest {

    @Test
    public void formatDate() {
    }

    @Test
    public void getTodayBegin() {
        Date todayBegin = DateUtils.getTodayBegin();
        System.out.println(todayBegin);
    }

    @Test
    public void getTodayEnd() {
        Date todayEnd = DateUtils.getTodayEnd();
        System.out.println(todayEnd);
    }

    @Test
    public void getWeekBegin() {
        Date weekBegin = DateUtils.getWeekBegin();
        System.out.println(weekBegin);
    }

    @Test
    public void getWeekEnd() {
        Date weekEnd = DateUtils.getWeekEnd();
        System.out.println(weekEnd);
    }

    @Test
    public void getMonthBegin() {
        Date monthBegin = DateUtils.getMonthBegin();
        System.out.println(monthBegin);
    }

    @Test
    public void getMonthEnd() {
        Date monthEnd = DateUtils.getMonthEnd();
        System.out.println(monthEnd);
    }

    @Test
    public void getNowDate() {
        Date nowDate = DateUtils.getNowDate();
        System.out.println(nowDate);
    }
}