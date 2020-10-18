package com.ozcanmuhammet.polling.util;

import java.util.ArrayList;
import java.util.List;

public class AnswerTestUtil {

	public static List<Object[]> getResultList() {
		List<Object[]> resultList = new ArrayList<Object[]>();
		Object[] object = new Object[5];
		object[0] = 1L;
		object[1] = "Question - 1";
		object[2] = 3L;
		object[3] = "Option - 1";
		object[4] = 5L;
		resultList.add(object);
		
		Object[] object2 = new Object[5];
		object[0] = 1L;
		object[1] = "Question - 1";
		object[2] = 2L;
		object[3] = "Option - 1";
		object[4] = 4L;
		resultList.add(object2);
		return resultList;
	}
}
