package com.mincoms.book.repository;

public class SpecUtil {
	 public static  String getLikePattern(final String searchTerm) {
         StringBuilder pattern = new StringBuilder();
         pattern.append("%");
         pattern.append(searchTerm.toLowerCase());
         pattern.append("%");
         return pattern.toString();
     }
}
