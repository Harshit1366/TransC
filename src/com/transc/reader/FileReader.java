package com.transc.reader;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.transc.model.Score;
import com.transc.model.Courses;
import com.transc.model.Record;

public class FileReader {

	
	
	public static List<List<String>> readData(String filename) {
		try {
			InputStream inputStream = new ByteArrayInputStream(IOUtils.toByteArray(new FileInputStream(filename)));
			Workbook wb = WorkbookFactory.create(inputStream);
			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> rowIter = sheet.iterator();
			List<List<String>> record = new ArrayList<>();
			while (rowIter.hasNext()) {
				List<String> cellData = new ArrayList<>();
				Row row = rowIter.next();
				Iterator<Cell> cellIter = row.cellIterator();

				while (cellIter.hasNext()) {
					Cell cell = cellIter.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						cellData.add(cell.getStringCellValue().trim().replaceAll("\n", " "));
						break;
					case Cell.CELL_TYPE_NUMERIC:
						double d = cell.getNumericCellValue();
						Double toBeTruncated = new Double(d);
						Double truncatedDouble = BigDecimal.valueOf(toBeTruncated).setScale(2, RoundingMode.HALF_UP)
								.doubleValue();
						cellData.add(Double.toString(truncatedDouble));
						break;
					case Cell.CELL_TYPE_BLANK:
						cellData.add(null);
						break;
					}
				}
				record.add(cellData);
			}

			Iterator<List<String>> i = record.iterator();
			while (i.hasNext()) {

				String s = i.next().get(2);

				// System.out.println(s);
				if (s == null || s.isEmpty()) {
					i.remove();
				}
			}

			if (record != null) {
				for (String string : record.get(0)) {
					// System.out.println(string);
					if (string == null) {
						continue;
					}
					Matcher matcher1 = cgpa_pattern.matcher(string);
					Matcher matcher2 = sgpa_pattern.matcher(string);
					Matcher matcher3 = credits_pattern.matcher(string);
					if (matcher1.find()) {
						cgpa_column = record.get(0).indexOf(string);
						// System.out.println(cgpa_column);
					}
					if (matcher2.find()) {
						sgpa_column = record.get(0).indexOf(string);
						// System.out.println(sgpa_column);
					}
					if (matcher3.find()) {
						credit_column = record.get(0).indexOf(string);
						// System.out.println(credit_column);
					}
				}
				return record;
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e);
		}
		return null;
	}
	
	
	public static List<Courses> getCourses() {
		try {
			//List<List<String>> data = captured_data;
			Matcher match_coursecode;
			List<Courses> course_list = new ArrayList<>();
			System.out.println("getCourses"+captured_data.get(0));
			for (String inner : captured_data.get(0)) {
				
				System.out.println("inner"+inner);
				match_coursecode = coursecode.matcher(inner);
				if (match_coursecode.find()) {
					System.out.println( inner);
					Courses course = new Courses();
					course.setCode(inner.replaceAll(" ", ""));
					course.setName(captured_data.get(1).get(captured_data.get(0).indexOf(inner)));
					course.setLtp(captured_data.get(2).get(captured_data.get(0).indexOf(inner)));
					course.setCredits(captured_data.get(3).get(captured_data.get(0).indexOf(inner)));
					// System.out.println(course);
					course_list.add(course);
				}
			}
			return course_list;
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println(e);
			return null;
		}
	}

	
	public static List<Score> getScores() {
		if (captured_data == null) {
			return null;
		}

		List<Score> scores = new ArrayList<>();
		try {
			List<List<String>> data = captured_data;
			List<Courses> courses = getCourses();
			
			for (Courses course : courses) {
				for (String code : captured_data.get(0)) {

					String trim_code = code.trim().replaceAll(" ", "");

					if (course.getCode().equals(trim_code)) {
						grade_column = captured_data.get(0).indexOf(code);
					}
					if (grade_column > 0) {

						for (int index = 5; index < captured_data.size(); index++) {
							Score score = new Score();
							score.setCourse_id(code);
							score.setRoll_no(captured_data.get(index).get(1));
							score.setStudent_name(captured_data.get(index).get(2));
							score.setGrade(captured_data.get(index).get(grade_column));
							scores.add(score);

						}
					}
					grade_column = 0;
				}
			}
			return scores;
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e);
		}
		return null;
	}


	
	public static List<List<String>> captured_data;
	static int cgpa_column;
	static int sgpa_column;
	static int credit_column;
	static int grade_column;
	String filename;
	static Pattern cgpa_pattern = Pattern.compile("CGPA");
	static Pattern sgpa_pattern = Pattern.compile("SGPA");
	static Pattern credits_pattern = Pattern.compile("Credits Earned");
	static Pattern coursecode = Pattern.compile("[A-Z]{3}[\\d]{3}|[A-Z]{3}[\\s]{1}[\\d]{3}");

	public FileReader(String filename) {
		this.filename = filename;
		captured_data = readData(this.filename);
		System.out.println("READER()"+captured_data.size());
	}
	
	public static void main(String args[]){
		String fake = "C:\\Users\\Deepanshu Jain\\Desktop\\Transcripts\\10CVU026 2nd.xls";
		String fake2 = "C:\\Users\\Deepanshu Jain\\Desktop\\Transcripts\\btech.xls";
		new FileReader(fake2);
//		for(List<String> s: captured_data){
//			for(String s1: s){
//				System.out.print(s1+" ");
//			}
//			System.out.println();
//		}

//		for(Courses r:getCourses()){
//			System.out.println(r);
//		}
		
		for(Score r: getScores()){
			System.out.println(r);
		}
	}
	
}
