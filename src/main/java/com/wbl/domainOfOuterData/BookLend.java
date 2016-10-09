package com.wbl.domainOfOuterData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created with Simple_love
 * Date: 2016/4/26.
 * Time: 11:28
 */
@Entity
@Table(name = "book_lend",schema = "data")
public class BookLend {

        private String readerId;
        private String readerName;
        private String bookName;
        private Date lentTime;
        private Date lendDeadLIne;

        public BookLend() {
        }

        @Id
        @Column(name = "READER_ID")
        public String getReaderId() {
                return readerId;
        }

        public void setReaderId(String readerId) {
                this.readerId = readerId;
        }

        @Column(name = "READER_NAME")
        public String getReaderName() {
                return readerName;
        }

        public void setReaderName(String readerName) {
                this.readerName = readerName;
        }

        @Column(name = "BOOK_NAME")
        public String getBookName() {
                return bookName;
        }

        public void setBookName(String bookName) {
                this.bookName = bookName;
        }

        @Column(name = "LEND_TIME")
        public Date getLentTime() {
                return lentTime;
        }

        public void setLentTime(Date lentTime) {
                this.lentTime = lentTime;
        }

        @Column(name = "LEND_DEADLINE")
        public Date getLendDeadLIne() {
                return lendDeadLIne;
        }

        public void setLendDeadLIne(Date lendDeadLIne) {
                this.lendDeadLIne = lendDeadLIne;
        }

        @Override
        public String toString() {
                return "BookLend{" +
                        "readerId='" + readerId + '\'' +
                        ", readerName='" + readerName + '\'' +
                        ", bookName='" + bookName + '\'' +
                        '}';
        }
}
