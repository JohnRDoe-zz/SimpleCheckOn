import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.ExcelHeader;
import bean.Person;
import utils.TextUtils;


/**
 * Author:  andy.xwt
 * Date:    2017/12/4 13:16
 * Description:
 */


class WorkService {

    private static final int TITLE_ROW = 0;

    private static List<String> TITLE_LIST = new ArrayList<>();
    /**
     * key - number value-> person
     */
    private static Map<String, Person> mPersonCheckMap = new HashMap<>();

    /**
     * 读取考勤表
     *
     * @param file
     */
    public static void readXls(File file) {
        InputStream inp = null;
        try {
            inp = new FileInputStream(file);
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);
            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (!isTitleRow(i)) {
                    createPersonCheckInfo(row);
                } else {
                    getTitle(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStream(inp);
        }

    }

    /**
     * 关闭流
     */
    private static void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 遍历行中的单元格 排除第一行标题行
     *
     * @param row
     */
    public static void createPersonCheckInfo(Row row) {
        String number = getCellData(row.getCell(0));
        Person person;
        if (!checkPersonIsExit(number)) {
            person = new Person();
            person.number = number;
            mPersonCheckMap.put(number, person);
        } else {
            person = mPersonCheckMap.get(number);
        }
        //获取签到信息
        for (int cellIndex = row.getFirstCellNum() + 1; cellIndex < row.getLastCellNum(); cellIndex++) {
            Cell cell = row.getCell(cellIndex);
            ExcelHeader excelHeader = ExcelHeader.getTitleByIndex(cellIndex);
            String cellData = getCellData(cell);
            setPersonCheckInfo(excelHeader, cellData);
        }
    }


    private static void setPersonCheckInfo(ExcelHeader excelHeader, String cellData) {
        Person person = new Person();
        if (excelHeader != null) {
            switch (excelHeader) {
                case NAME:
                    person.name = cellData;
                    break;
                case TIME_RANGE:
                    break;
                case START_WORK_TIME:
                    break;
                case END_WORK_TIME:
                    break;
                case SIGN_IN_TIME://签到时间
//                    if (TextUtils.isEmpty(cellData)) {
//                        person.is_sign_in = false;
//                    } else {
//                        person.sign_in_time = cellData;
//                    }
                    break;
                case SIGN_OUT_TIME://签退时间
//                    if (TextUtils.isEmpty(cellData) || TextUtils.isEmpty(person.sign_in_time)) {
//                        //记录一次未签到
//                        person.unCheck++;
//                    } else {
//                        person.sign_out_time = cellData;
//                        //计算签到与签退的判断是否
//                    }
                    break;
                case NOTE:
                    // TODO: 2017/12/5 xwt 判断时间 标红 且添加备注
                    break;
                case IS_ARRIVE://实到
                    if (cellData.equals("1")) {
                        person.turnOutDay++;
                    }
                    break;
                case LATE_TIME:
                    break;
                case LEAVE_EARLY:
                    break;
                case IS_ABSENT:
                    break;
                case WORK_TIME:
                    break;
                case EXCEPTION_SITUATION:
                    break;
                case SHOULD_SIGN_ARRIVE:
                    break;
                case SHOULD_SIGN_LEAVE:
                    break;
                case DEPARTMENT:
                    break;
                case NORMAL_DAY:
                    break;
                case WEEKEND:
                    break;
                case HOLIDAY:
                    break;
                case ON_DUTY_TIME:
                    break;
                case EXTRA_WORK_IN_NORMAL_DAY://平日加班
                    if (!TextUtils.isEmpty(cellData)) {
                        person.extraWork += Float.parseFloat(cellData);
                    }
                    break;
                case EXTRA_WORK_IN_WEEKEND_DAY://周末加班
                    if (!TextUtils.isEmpty(cellData)) {
                        person.extraWork += Float.parseFloat(cellData);
                    }
                    break;
                case EXTRA_WORK_IN_HOLIDAY://节假日加班
                    if (!TextUtils.isEmpty(cellData)) {
                        person.extraWork += Float.parseFloat(cellData);
                    }
                    break;

            }
        }
    }


    /**
     * 获取cell中的内容
     */
    private static String getCellData(Cell cell) {
        String cellValue = "";
        switch (cell.getCellTypeEnum()) {
            case STRING:
                cellValue = cell.getRichStringCellValue().getString();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    cellValue = cell.getDateCellValue().toString();
                } else {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case BLANK:
                break;
        }
        return cellValue;
    }

    private static boolean isTitleRow(int index) {
        return index == TITLE_ROW;
    }

    private static void getTitle(Row row) {
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            String cellData = getCellData(row.getCell(i));
            TITLE_LIST.add(cellData);
        }
    }


    /**
     * 判断用户是否存在
     *
     * @param number
     */
    private static boolean checkPersonIsExit(String number) {
        return number != null && number.length() > 1 && mPersonCheckMap != null && mPersonCheckMap.keySet().contains(number);
    }
}
