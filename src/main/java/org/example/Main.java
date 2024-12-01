package org.example;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static Map<String, String> userPassMap = new HashMap<>();
    static String user;
    static String pass;

    public static void main(String[] args) {

        try {
            FileInputStream file = new FileInputStream("dataLogin.xlsx");
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheetDataLogin = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();
            // Read locators from sheet 1
            for (Row row : sheetDataLogin) {
                if (row.getRowNum() == 0) continue;
                // Lấy giá trị của cột "user" (cột 0) và "pass" (cột 1)
                user = dataFormatter.formatCellValue(row.getCell(0)).trim();
                pass = dataFormatter.formatCellValue(row.getCell(1)).trim();
                // Kiểm tra nếu cả "user" và "pass" không rỗng
                if (!user.isEmpty() && !pass.isEmpty()) {
                    // Lưu cặp user-pass vào HashMap
                    userPassMap.put(user, pass);
                }
            }
            workbook.close();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int count = 0;
        // In ra danh sách user-pass từ HashMap
        for (Map.Entry<String, String> entry : userPassMap.entrySet()) {
            if (count > 0) {
                System.out.println("User: " + entry.getKey() + ", Pass: " + entry.getValue());
            }
            count++;
        }
    }
}