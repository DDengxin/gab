package com.tengzhi.base.tools.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.tools.Spring.SpringUtils;
import com.tengzhi.base.tools.UUID.UUIDUtil;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;

/**
 * excel处理工具类
 * 
 * @author lqy
 *
 */
public class ExcelUtil {
	private static final String EXCEL_XLS = ".xls";
	private static final String EXCEL_XLSX = ".xlsx";
	private static final String[] str = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
			"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

//	private BaseDao dao;

	/**
	 * 格式化解析的数据
	 * 
	 * @throws Exception
	 */
	public static String getCellValue(Cell cell) throws Exception {
		String cellValue = "";
		if (cell != null) {
			if (cell.getCellTypeEnum() == CellType.NUMERIC) {
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					cellValue = DateFormatUtils.format(cell.getDateCellValue(), "yyyy-MM-dd HH:mm:ss");
				} else {
					NumberFormat nf = NumberFormat.getInstance();
					cellValue = String.valueOf(nf.format(cell.getNumericCellValue())).replace(",", "");
				}
			} else if (cell.getCellTypeEnum() == CellType.STRING) {
				cellValue = cell.getStringCellValue();
			} else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
				cellValue = String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellTypeEnum() == CellType.ERROR) {
				throw new Exception("数据错误");
			} else if (cell.getCellTypeEnum() == CellType.FORMULA) {
				cellValue = String.valueOf(cell.getCellFormula());
			} else if (cell.getCellTypeEnum() == CellType.BLANK) {
				cellValue = "";
			} else {
				cellValue = cell.toString().trim();
			}
		}
		return cellValue.trim();
	}

	public String timeStamp2Date(long second, String format) {
		String seconds = String.valueOf(second);
		if (seconds == null || seconds.isEmpty() || "null".equals(seconds)) {
			return "";
		}
		if (format == null || format.isEmpty()) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.valueOf(seconds + "000")));
	}

	/**
	 * 判断excel的版本，并根据文件流数据获取workbook
	 * 
	 * @throws IOException
	 */
	public Workbook getWorkBook(InputStream is, File file) throws Exception {
		Workbook workbook = null;
		if (file.getName().endsWith(EXCEL_XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (file.getName().endsWith(EXCEL_XLSX)) {
			workbook = new XSSFWorkbook(is);
		}
		return workbook;
	}

	/**
	 * 校验文件是否为excel
	 * 
	 * @throws Exception
	 */
	public void checkExcelVaild(File file) throws Exception {
		String message = "该文件是EXCEL文件！";
		if (!file.exists()) {
			message = "文件不存在！";
			throw new Exception(message);
		}
		if (!file.isFile() || ((!file.getName().endsWith(EXCEL_XLS) && !file.getName().endsWith(EXCEL_XLSX)))) {
			System.out.println(file.isFile() + "===" + file.getName().endsWith(EXCEL_XLS) + "==="
					+ file.getName().endsWith(EXCEL_XLSX));
			message = "文件不是Excel";
			throw new Exception(message);
		}
	}

	/**
	 * 初始化
	 */
	public static ExcelUtil getInstance() {
		return ReadExcelUtilHolder.ExcelUtil;
	}

	/**
	 * 导出excel到网络（直接将http）
	 *
	 * @param t         对象
	 * @param sheetName 名称
	 * @param response
	 * @return
	 */
	public void ExcelToWeb(Object t, String sheetName, HttpServletResponse response) {
		response.setContentType("multipart/form-data");
		response.setCharacterEncoding("utf-8");
		try {
			response.setHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode(sheetName + ".xls", "utf-8"));
			HSSFWorkbook book = this.exportExcel(t, sheetName, "");
			book.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出excel到网络（直接将http）
	 *
	 * @param t         对象
	 * @param sheetName 名称
	 * @param response
	 * @param version   版本
	 * @return
	 */
	public void ExcelToWeb(Object t, String sheetName, HttpServletResponse response, String version) {
		response.setContentType("multipart/form-data");
		response.setCharacterEncoding("utf-8");
		try {
			response.setHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode(sheetName + ".xls", "utf-8"));
			HSSFWorkbook book = this.exportExcel(t, sheetName, version);
			book.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ExcelToWeb(HttpServletRequest request, String sheetName, HttpServletResponse response,
			List<Map<String, Object>> list) {
		response.setContentType("multipart/form-data");
		response.setCharacterEncoding("utf-8");
		try {
			response.setHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode(sheetName + ".xls", "utf-8"));
			HSSFWorkbook book = this.exportExcel(request, sheetName, list);
			book.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 *
	 * @modifer shaoxiaofei
	 *根据表格需要导入列的注释输出模板
	 * @throws Exception
	 *
	 */
	public void ExcelToWebByTable(String sheetName, HttpServletResponse response, List<Map> list) {
		response.setContentType("multipart/form-data");
		response.setCharacterEncoding("utf-8");
		try {
			response.setHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode(sheetName + ".xls", "utf-8"));
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet();
			workbook.setSheetName(0, sheetName); // 设置表格工作簿名称
			HSSFRow row = sheet.createRow(0);
			boolean type = true;
			HSSFSheet datasheet = null;// 下拉框用到
			int index = 0, tindex = 0;
			// 设置表头
			for (int i = 0; i < list.size(); i++) {
				HSSFCell cell = row.createCell(tindex);
				Map<String, Object> field = list.get(i);
					cell.setCellValue(field.get("description").toString()); // 设置值
				sheet.setColumnWidth(tindex, 256 *cell.getStringCellValue().getBytes().length + 184);
				tindex++;
			}

			workbook.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 
	 * 设置表头
	 * 
	 * @param list
	 * @param sheetName
	 * @return HSSFWorkbook
	 */
	private HSSFWorkbook exportExcel(HttpServletRequest request, String sheetName, List<Map<String, Object>> list) {
		String columns = request.getParameter("columns");
		HSSFWorkbook workbook = new HSSFWorkbook(); // 创建工作簿对象
		HSSFSheet sheet = workbook.createSheet(sheetName); // 创建工作表
		HSSFRow rowRowName = sheet.createRow(0); //
		workbook.setSheetName(0, sheetName); // 设置表格工作簿名称
		JSONArray array = JSONUtil.parseArray(columns);
		int index = 0, hindex = 1;
		// 创建标题
		List<String> titleList = new ArrayList<String>();
		for (int i = 0; i < array.size(); i++) {
			Map<String, Object> map = array.getJSONObject(i);
			if (map.containsKey("header") && map.containsKey("field")) {
				// 样式设置
				HSSFFont titlefont = workbook.createFont();
				titlefont.setFontName("黑体");
				titlefont.setFontHeightInPoints((short) 11);// 设置字体大小
				titlefont.setBold(true);// 加粗
				HSSFCellStyle titleStyle = workbook.createCellStyle();
				// setStyle单元格居中
				titleStyle.setAlignment(HorizontalAlignment.CENTER);// 居中
				titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
				titleStyle.setFont(titlefont);

				HSSFCell cellRowName = rowRowName.createCell(index); // 创建列头对应个数的单元格
				cellRowName.setCellValue(map.get("header").toString()); // 设置列头单元格的值
				cellRowName.setCellStyle(titleStyle);
				if(map.containsKey("displayField")) {
					titleList.add(map.get("displayField").toString());
				}else {
					titleList.add(map.get("field").toString());
				}
				index++;
			}
		}
		// 创建内容
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> vmap = list.get(i);
			HSSFRow row = sheet.createRow(hindex);// 创建行
			for (int j = 0; j < titleList.size(); j++) {
				HSSFCell cellRowName = row.createCell(j); // 创建单元格
				cellRowName
						.setCellValue(vmap.get(titleList.get(j)) == null ? "" : vmap.get(titleList.get(j)).toString()); // 设置列头单元格的值
			}
			hindex++;
		}

		// 让列宽随着导出的列长自动适应
		for (int colNum = 0; colNum < titleList.size(); colNum++) {
			int columnWidth = sheet.getColumnWidth(colNum) / 256;
			for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
				HSSFRow currentRow;
				// 当前行未被使用过
				if (sheet.getRow(rowNum) == null) {
					currentRow = sheet.createRow(rowNum);
				} else {
					currentRow = sheet.getRow(rowNum);
				}
				if (currentRow.getCell(colNum) != null) {
					HSSFCell currentCell = currentRow.getCell(colNum);
					if (currentCell.getCellTypeEnum() == CellType.STRING) {
						int length = currentCell.getStringCellValue().getBytes().length;
						if (columnWidth < length) {
							columnWidth = length;
						}
					}
				}
			}
			if (colNum == 0) {
				int width=(columnWidth - 2) * 256;
                if(width<255*256) {
				sheet.setColumnWidth(colNum, width);
                } else {
    				sheet.setColumnWidth(colNum, 6000);
				}
			} else {
				int width=(columnWidth + 4) * 256;
				if(width<255*256){
					sheet.setColumnWidth(colNum, width);
	            }else{
	                sheet.setColumnWidth(colNum,6000 );
	            }
			}
		}
		return workbook;
	}


	/**
	 * 
	 * 设置表头
	 * 
	 * @param t
	 * @param sheetName
	 * @return HSSFWorkbook
	 */
	private HSSFWorkbook exportExcel(Object t, String sheetName, String version) {
		List<Field> fields = getNote(t);
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, sheetName); // 设置表格工作簿名称
		HSSFRow row = sheet.createRow(0);
		boolean type = true;
		HSSFSheet datasheet = null;// 下拉框用到
		int index = 0, tindex = 0;
		// 设置表头
		for (int i = 0; i < fields.size(); i++) {
			HSSFCell cell = row.createCell(tindex);
			Field field = fields.get(i);
			Excel excel = field.getAnnotation(Excel.class);
			if (excel.show() && (excel.version().indexOf(version) > -1 || "".equals(excel.version()))) {
				cell.setCellValue(excel.name()); // 设置值
				if (excel.width() > 0) {
                    sheet.setColumnWidth(tindex, 256 * excel.width() + 184);
                }
				// 判断是否为下拉框
				tindex++;
			}
		}
		return workbook;
	}

	/**
	 * 读取excel数据
	 * 
	 * @throws Exception
	 *
	 */
	public Result readExcel(MultipartFile files, Object obj, int start, String checkName) throws Exception {
		File file = File.createTempFile(UUIDUtil.uuid(), "tmp.xls");
		files.transferTo(file);
		Result result = readExcel(file, obj, 1, checkName);
		file.deleteOnExit();
		return result;
	}
	/**
	 * 读取excel数据
	 * @modifer shaoxiaofei
	 *根据表格需要导入列的注释读取数据导入
	 * @throws Exception
	 *
	 */
	public Result readExcelByTable(MultipartFile files, Map<String,String> obj,String Dbtable) throws Exception {
		File file = File.createTempFile(UUIDUtil.uuid(), "tmp.xls");
		files.transferTo(file);
		Result result = readExcelByTable(file, obj,Dbtable);
		file.deleteOnExit();
		return result;
	}

	public Result readExcel(File excelFile, Object obj) throws Exception {
		return readExcel(excelFile, obj, 1, null);
	}

	public Result readExcel(File excelFile, Object obj, int start) throws Exception {
		return readExcel(excelFile, obj, start, null);
	}

	public Result readExcel(File excelFile, Object obj, String checkName) throws Exception {
		return readExcel(excelFile, obj, 1, checkName);
	}

	public Result readExcel(File excelFile, Object obj, int start, String checkName) throws Exception {
		InputStream is = new FileInputStream(excelFile);// 创建输入流对象
		checkExcelVaild(excelFile);
		List<Object> dataList = new ArrayList<Object>();
		Map<String, Object> beanMap = getbeanMap(obj);
		Workbook workbook = getWorkBook(is, excelFile);
		int sheetNum = workbook.getNumberOfSheets();
		// 遍历工作簿中的sheet
		for (int index = 0; index < sheetNum; index++) {
			Sheet sheet = workbook.getSheetAt(index);
			if (sheet == null || workbook.isSheetHidden(index)) {
				continue;
			}
			// 循环所有的行
			for (int rowIndex = start; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				Row row = sheet.getRow(rowIndex);
				// 循环行中单元格
				if (row != null && isRowEmpty(row)) {
					Object Instanceobj = obj.getClass().newInstance();
					for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
						Cell cell = row.getCell(cellIndex);
						String rowname = null;
						if (sheet.getRow(0).getCell(cellIndex) != null) {
							rowname = sheet.getRow(0).getCell(cellIndex).toString();
						}
						try {
							String value = getCellValue(cell);
							if (StringUtils.isNotEmpty(rowname)) {
								if (beanMap.containsKey(rowname)) {
									Setfield(Instanceobj, beanMap.get(rowname).toString(), value);
								}
							}
						} catch (Exception e) {
							String mssage = getClassTypeMssage(Instanceobj, beanMap.get(rowname).toString());
							String message = "第" + (rowIndex+1) + "行，" + rowname + " 列转换失败！" + mssage;
							return Result.error(message);
						}
					}
					if (checkName != null) {
						ExcelCheck excelCheck = (ExcelCheck) SpringUtils.getBean(checkName);
						try {
							Instanceobj = excelCheck.Check(Instanceobj);
						} catch (Exception e) {
							String message = "第" + (rowIndex+1) + "行，" + e.getMessage();
							return Result.error(message);
						}
					}
					dataList.add(Instanceobj);

				}
			}
		}
		is.close();
		return Result.resultOk(dataList);
	}
	public Result readExcelByTable(File excelFile, Map<String,String> beanMap,String Dbtable) throws Exception {
		InputStream is = new FileInputStream(excelFile);// 创建输入流对象
		checkExcelVaild(excelFile);
		List<Object> dataList = new ArrayList<Object>();
//		Map<String, Object> beanMap = getbeanMap(obj);
		Workbook workbook = getWorkBook(is, excelFile);
		int sheetNum = workbook.getNumberOfSheets();
		String classname="com.tengzhi.business.system.initdata.model."+Dbtable;
		// 遍历工作簿中的sheet
		for (int index = 0; index < sheetNum; index++) {
			Sheet sheet = workbook.getSheetAt(index);
			if (sheet == null || workbook.isSheetHidden(index)) {
				continue;
			}
			// 循环所有的行
			for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				Row row = sheet.getRow(rowIndex);
				// 循环行中单元格
				if (row != null && isRowEmpty(row)) {
					Object Instanceobj =  Class.forName(classname).newInstance();
					for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
						Cell cell = row.getCell(cellIndex);
						String rowname = null;
						if (sheet.getRow(0).getCell(cellIndex) != null) {
							rowname = sheet.getRow(0).getCell(cellIndex).toString();
						}
						try {
							String value = getCellValue(cell);
							if (StringUtils.isNotEmpty(rowname)) {
								if (beanMap.containsKey(rowname)) {
									Setfield(Instanceobj, beanMap.get(rowname), value);
								}
							}
						} catch (Exception e) {
							String mssage = getClassTypeMssage(Instanceobj, beanMap.get(rowname));
							String message = "第" + (rowIndex+1) + "行，" + rowname + " 列转换失败！" + mssage;
							return Result.error(message);
						}
					}

					dataList.add(Instanceobj);
					
				}
			}
		}
		is.close();
		return Result.resultOk(dataList);
	}

	public static boolean isRowEmpty(Row row) {
		for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
			Cell cell = row.getCell(i);
			if (cell != null && !"".equals(cell.toString())) {
				return true;
			}
		}
		return false;
	}

	// =============================================反射相关=====================================================================================
	/**
	 * 
	 * 反射取Field
	 * 
	 * @param obj
	 * @return List< Field>
	 */
	private List<Field> getNote(Object obj) {
		Field[] allFields = obj.getClass().getDeclaredFields();
		List<Field> fields = new ArrayList<>();
		for (int col = 0; col < allFields.length; col++) {
			System.out.println(col);
			Field field = allFields[col];
			if (field.isAnnotationPresent(Excel.class)) {
				field.setAccessible(true);
				fields.add(field);
			}
		}
		return fields;
	}

	/**
	 * 
	 * 反射取map(注解和属性名)
	 * 
	 * @param obj
	 * @return Map<String, Object>
	 */
	private Map<String, Object> getbeanMap(Object obj) {
		Field[] allFields = obj.getClass().getDeclaredFields();
		Map<String, Object> beanMap = new java.util.HashMap<String, Object>();
		for (int col = 0; col < allFields.length; col++) {
			System.out.println(col);

			Field field = allFields[col];
			Object[] x=field.getAnnotations();
			System.out.println("  xxxxx" +x.length);
			if (field.isAnnotationPresent(Excel.class)) {
				field.setAccessible(true);
				Excel excel = field.getAnnotation(Excel.class);
				beanMap.put(excel.name(), field.getName());
				System.out.println(excel.name());
				String[] spareName = excel.spareName();
				if (spareName != null && spareName.length > 0) {
					for (int i = 0; i < spareName.length; i++) {
						beanMap.put(spareName[i], field.getName());
					}
				}
			}
		}
		return beanMap;
	}

	/**
	 * 
	 * 反射设置值
	 * 
	 * @param obj
	 * @return List< Field>
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 */
	private void Setfield(Object obj, String field, String value) throws Exception {
		setValue(obj, obj.getClass(), field, obj.getClass().getDeclaredField(field).getType(), value);
	}

	/**
	 * 根据属性，拿到set方法，并把值set到对象中
	 * 
	 * @param obj       对象
	 * @param clazz     对象的class
	 * @param filedName  需要设置值得属性
	 * @param typeClass
	 * @param value
	 * @throws Exception
	 */
	public static void setValue(Object obj, Class<?> clazz, String filedName, Class<?> typeClass, Object value)
			throws Exception {
		String methodName = "set" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
		Method method = clazz.getDeclaredMethod(methodName, typeClass);
		method.invoke(obj, getClassTypeValue(typeClass, value));

	}

	/**
	 * 通过class类型获取获取对应类型的值
	 * 
	 * @param typeClass class类型
	 * @param value     值
	 * @return Object
	 * @throws Exception
	 */
	private static Object getClassTypeValue(Class<?> typeClass, Object value) throws Exception {
		if (typeClass == int.class || typeClass == Integer.class) {
			if (null == value) {
				return 0;
			}
			return Integer.parseInt((String) value);
		} else if (typeClass == short.class) {
			if (null == value) {
				return 0;
			}
			return Short.parseShort((String) value);
		} else if (typeClass == byte.class) {
			if (null == value) {
				return 0;
			}
			return value;
		} else if (typeClass == double.class || typeClass == Double.class) {
			if (null == value) {
				return 0;
			}
			return Double.parseDouble((String) value);
		} else if (typeClass == long.class) {
			if (null == value) {
				return 0;
			}
			return Long.parseLong((String) value);
		} else if (typeClass == String.class) {
			if (null == value) {
				return "";
			}
			return value;
		} else if (typeClass == boolean.class) {
			if (null == value) {
				return true;
			}
			return value;
		} else if (typeClass == BigDecimal.class) {
			if (null == value) {
				return new BigDecimal(0);
			}
			return new BigDecimal(value + "");
		} else if (typeClass == Date.class) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = null;
			try {
				date = format.parse(value.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				date = getTime(value.toString());
			}
			return date;
		} else if (typeClass == Timestamp.class) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = getTime(value.toString());
			return new Timestamp(date.getTime());
		} else {
			return typeClass.cast(value);
		}
	}

	/**
	 * 通过class类型给出提示
	 * 
	 * @param field class类型
	 * @return mssage
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws Exception
	 */
	private static String getClassTypeMssage(Object obj, String field) throws NoSuchFieldException, SecurityException {
		Class<?> typeClass = obj.getClass().getDeclaredField(field).getType();
		String mssage = "";
		if (typeClass == int.class || typeClass == Integer.class) {
			mssage = "请检查单元格数据是否为数字";
		} else if (typeClass == short.class) {
			mssage = "请检查单元格数据是否为数字";
		} else if (typeClass == byte.class) {
			mssage = "请检查单元格数据是否为数字";
		} else if (typeClass == double.class || typeClass == Double.class) {
			mssage = "请检查单元格数据是否为数字";
		} else if (typeClass == long.class) {
			mssage = "请检查单元格数据是否为数字";
		} else if (typeClass == String.class) {
			mssage = "";
		} else if (typeClass == boolean.class) {
			mssage = "";
		} else if (typeClass == BigDecimal.class) {
			mssage = "请检查单元格数据是否为数字";
		} else if (typeClass == Date.class) {
			mssage = " 请检查单元格格式是否为日期类型,或者日期格式为yyyy/MM/dd";
		} else {
			mssage = "请检查单元格数据！";
		}
		return mssage;
	}

	/**
	 * 判断string类型格式化
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	private static Date getTime(String value) throws Exception {
		Date date = null;
		if (value.indexOf("/") > 0) {
			if (value.length() == 10) {
                date = format("yyyy/MM/dd", value);
            } else {
                date = format("yyyy/MM/dd HH:mm:ss", value);
            }
		} else if (value.indexOf("-") > 0 && value.length() == 10) {
			if (value.length() == 10) {
                date = format("yyyy-MM-dd", value);
            } else {
                date = format("yyyy-MM-dd HH:mm:ss", value);
            }
		} else if (value.indexOf("-") > 0 && value.length() == 7) {
			if (value.length() == 7) {
                date = format("yyyy-MM", value);
            } else {
                date = format("yyyy-MM-dd HH:mm:ss", value);
            }
		} else {
			throw new Exception("日期格式化错误");
		}
		return date;
	}

	/**
	 * 日期格式化
	 * 
	 * @param farmatstr
	 * @param value
	 * @return
	 * @throws Exception
	 */
	private static Date format(String farmatstr, String value) throws Exception {
		DateFormat format = new SimpleDateFormat(farmatstr);
		Date date = null;
		try {
			date = format.parse(value);
		} catch (ParseException e) {
			throw new Exception("日期格式化错误");
		}
		return date;
	}

}

class ReadExcelUtilHolder {
	static final ExcelUtil ExcelUtil = new ExcelUtil();
}