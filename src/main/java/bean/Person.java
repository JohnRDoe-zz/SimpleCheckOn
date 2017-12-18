package bean;

import java.util.List;

/**
 * Author:  andy.xwt
 * Date:    2017/12/4 13:42
 * Description:
 */


public class Person {
    public String number;//序号
    public String name;//姓名
    public int turnOutDay;//实际出勤天数
    public int workDay;//正常上班天数
    public float extraWork;//加班时长 小数
    public String evection;//出差
    public String personalHoliday;//事假
    public String sickHoliday;//病假
    public String yearHoliday;//年假
    public String othreHoliday;//其他休假
    public String adjustHoliday;//调休
    public String absent;//旷工
    public int late;//迟到、早退
    public int unCheck;//未打卡次数
    public List<CheckInfo> checkInfos;//签到记录
}
