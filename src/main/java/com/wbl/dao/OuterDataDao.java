package com.wbl.dao;

import com.wbl.domainOfOuterData.BookLend;
import com.wbl.domainOfOuterData.Student;
import com.wbl.domainOfOuterData.StudentBook;
import com.wbl.modal.CountModal;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with Simple_love
 * Date: 2016/4/26.
 * Time: 11:26
 */
@Repository
public interface OuterDataDao {
    List<String> getReaderName();
    List<Student> getStudentBasicInfo();
    List<BookLend> getBookLendInfo();

    List<StudentBook> getStudentBookLend();

    List<Student> getStudent();
    List<BookLend> getBookLend();
}
