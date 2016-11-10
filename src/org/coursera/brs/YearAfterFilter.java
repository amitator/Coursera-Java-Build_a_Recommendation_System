package org.coursera.brs;

/**
 * MovieDatabase
 *
 * @author Igor Prus
 * @version Nov 10/16
 *
 *
 */

public class YearAfterFilter implements Filter {
	private int myYear;
	
	public YearAfterFilter(int year) {
		myYear = year;
	}
	
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getYear(id) >= myYear;
	}

}
