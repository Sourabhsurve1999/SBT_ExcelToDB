package com.sourabhsurve.SBT_ExcelToDB.service;

import com.sourabhsurve.SBT_ExcelToDB.entity.User;
import com.sourabhsurve.SBT_ExcelToDB.repository.UserRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void readExcelAndSaveToDatabase(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        List<User> userList = new ArrayList<>();

        for (Row row : sheet) {
            if (row.getRowNum() == 0) { // skip the header row
                continue;
            }

            User user = new User();

            Cell idCell = row.getCell(0);
            if (idCell.getCellType() == CellType.NUMERIC) {
                int id = (int) idCell.getNumericCellValue();
                user.setId(id);
            }

            Cell cityCell = row.getCell(1);
            user.setCity(cityCell.getStringCellValue());

            Cell firstNameCell = row.getCell(2);
            user.setFirstName(firstNameCell.getStringCellValue());

            Cell lastNameCell = row.getCell(3);
            user.setLastName(lastNameCell.getStringCellValue());

            userList.add(user);
        }

        userRepository.saveAll(userList);
    }
}
