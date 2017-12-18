package bean;

/**
 * Author:  andy.xwt
 * Date:    2017/12/5 11:55
 * Description:
 */


public enum ExcelHeader {
    REGISTER_COUNT(0, "登记号码"),
    NAME(1, "姓名"),
    DATE(2, "日期"),
    TIME_RANGE(3, "对应时段"),
    START_WORK_TIME(4, "上班时间"),
    END_WORK_TIME(5, "下班时间"),
    SIGN_IN_TIME(6, "签到时间"),
    SIGN_OUT_TIME(7, "签退时间"),
    NOTE(8, "备注"),
    IS_ARRIVE(9, "实到"),
    LATE_TIME(10, "迟到时间"),
    LEAVE_EARLY(11, "早退时间"),
    IS_ABSENT(12, "是否旷工"),
    EXTRA_WORK_TIME(13, "加班时间"),
    WORK_TIME(14, "工作时间"),
    EXCEPTION_SITUATION(15, "例外情况"),
    SHOULD_SIGN_ARRIVE(16, "应签到"),
    SHOULD_SIGN_LEAVE(17, "应签退"),
    DEPARTMENT(18, "部门"),
    NORMAL_DAY(19, "平日"),
    WEEKEND(20, "周末"),
    HOLIDAY(21, "节假日"),
    ON_DUTY_TIME(22, "出勤时间"),
    EXTRA_WORK_IN_NORMAL_DAY(23, "平日加班"),
    EXTRA_WORK_IN_WEEKEND_DAY(24, "周末加班"),
    EXTRA_WORK_IN_HOLIDAY(25, "节假日加班");


    ExcelHeader(int index, String titleDesc) {
        this.index = index;
        this.titleDesc = titleDesc;
    }

    public int index;
    public String titleDesc;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTitleDesc() {
        return titleDesc;
    }

    public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc;
    }

    public static ExcelHeader getTitleByIndex(int index) {
        for (ExcelHeader excelHeader : ExcelHeader.values()) {
            if (excelHeader.index == index) {
                return excelHeader;
            }
        }
        return null;
    }
}
