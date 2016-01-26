package com.wbl.modal;

import java.util.ArrayList;

/**
 * Created by Simple_love on 2015/12/21.
 */
public class Page<E> extends ArrayList<E> {
        // ҳ��
        private int currentPage;

        //ÿҳ��ʾ������
        private int pageSize = 10;

        //�ܹ�������
        private int total;

        //�ܵ�ҳ���������м�ҳ
        private int pages;

        //��ʼ��
        private int startRow;

        //ĩ��
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
