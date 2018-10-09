package com.qui.shopping.web.pagination;


import java.util.ArrayList;
import java.util.List;
 
public class PaginationResult<E> {
 
   private long totalRecords;
   private int currentPage;
   private List<E> list;
   private int maxResult;
   private int totalPages;
 
   private int maxNavigationPage;
 
   private List<Integer> navigationPages;
 
   // @page: 1, 2, ..
   public PaginationResult(List<E> query, int currentPage,int totalPages, long totalRecords, int maxNavigationPage) {
      

      // Total Records
      this.totalRecords = totalRecords;
      this.currentPage = currentPage;
      this.list = query;
     // this.maxResult = maxResult;
      this.totalPages=totalPages;
      /*if (this.totalRecords % this.maxResult == 0) {
         this.totalPages = this.totalRecords / this.maxResult;
      } else {
         this.totalPages = (this.totalRecords / this.maxResult) + 1;
      }*/
 
      this.maxNavigationPage = maxNavigationPage;
 
      if (maxNavigationPage < totalPages) {
         this.maxNavigationPage = maxNavigationPage;
      }
 
      this.calcNavigationPages();
   }
 
   private void calcNavigationPages() {
 
      this.navigationPages = new ArrayList<Integer>();
 
      int current = this.currentPage > this.totalPages ? this.totalPages : this.currentPage;
 
      int begin = current - this.maxNavigationPage / 2;
      int end = current + this.maxNavigationPage / 2;
 
      // The first page
      navigationPages.add(1);
      if (begin > 2) {
 
         // Using for '...'
         navigationPages.add(-1);
      }
 
      for (int i = begin; i < end; i++) {
         if (i > 1 && i < this.totalPages) {
            navigationPages.add(i);
         }
      }
 
      if (end < this.totalPages - 2) {
 
         // Using for '...'
         navigationPages.add(-1);
      }
      // The last page.
      navigationPages.add(this.totalPages);
   }
 
   public int getTotalPages() {
      return totalPages;
   }
 
   public long getTotalRecords() {
      return totalRecords;
   }
 
   public int getCurrentPage() {
      return currentPage;
   }
 
   public List<E> getList() {
      return list;
   }
 
   public int getMaxResult() {
      return maxResult;
   }
 
   public List<Integer> getNavigationPages() {
      return navigationPages;
   }
 
}