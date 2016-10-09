package com.wbl.modal;

import java.util.ArrayList;

/**
 * Created by Simple_love on 2015/12/21.
 */
public class Page<E> extends ArrayList<E> {
        // 页码
        private int currentPage;

        //每页显示的行数
        private int pageSize = 10;

        //总共的行数
        private int total;

        //总的页数，即共有几页
        private int pages;

        //起始行
        private int startRow;

        //末行
        private int endRow;

        public Page() {
                super();
        }

        public Page(int currentPage){
                this.currentPage = currentPage;
        }

        public Page(int currentPage, int pageSize) {
                this.currentPage = currentPage;
                this.pageSize = pageSize;
        }

        public int getCurrentPage() {
                return currentPage;
        }

        public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
        }

        public int getPageSize() {
                return pageSize;
        }

        public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
        }

        public int getTotal() {
                return total;
        }

        public void setTotal(int total) {
                this.total = total;
        }

        public int getPages() {
                return pages;
        }

        public void setPages(int pages) {
                this.pages = pages;
        }

        public int getStartRow() {
                return startRow;
        }

        public void setStartRow(int startRow) {
                this.startRow = startRow;
        }

        public int getEndRow() {
                return endRow;
        }

        public void setEndRow(int endRow) {
                this.endRow = endRow;
        }

        public Page<E> getResult(){
                return this;
        }
}
