# A0138998B-unused
###### \StartTime.java
``` java
    /*
     * Code removed as we decided to use Natty instead
     */
    /**
     * splits date and time into different
     * @param time
     * @return a list of arrays of day and time seperate from each other
     */
	private static ArrayList<String> parseDayAndTime(String time) {
		
		time=time.toLowerCase();
		
		ArrayList<String> times= new ArrayList<String>(Arrays.asList(time.split(" ")));
		
		return times;
		
	}
	/**
	 * 
	 * @param times
	 * @throws IllegalValueException when format of date and time is not right
	 */

	private static void checkForCorrectFormats(ArrayList<String> times) throws IllegalValueException {
		
		if(times.size()!=2){
			throw new IllegalValueException(MESSAGE_DATETIME_CONSTRAINTS);
		}
		
		if(!times.get(0).matches(DATE_VALIDATION_REGEX)){
			throw new IllegalValueException(MESSAGE_DAY_CONSTRAINTS);
		}
		
		if(!times.get(1).matches(TIME_VALIDATION_REGEX)){
			throw new IllegalValueException(MESSAGE_TIME_CONSTRAINTS);
		}
		
	}
	
	/**
	 * 
	 * @param times is an array that holds both the day and time
	 * @param intTime 
	 * @return intTime to match the day of the week specified as an integer
	 */
	private static int getDayAsInt(ArrayList<String> times, int intTime) {
		
		switch(times.get(0)){
		
			case("monday"):
			intTime=1;break;
			
			case("tuesday"):
		   	 intTime=2;break;
			
			case("wednesday"):
		   	 intTime=3;break;
			
			case("thursday"):
		   	 intTime=4;break;
			
			case("friday"):
		   	 intTime=5;break;
			
			case("saturday"):
		     intTime=6;break;
			
			case("sunday"):
		     intTime=7;break;
			
		}
		
		return intTime;
		
	}
	
	/**
	 * 
	 * @param date is the current date time on the users computer
	 * @param intTime is the user specified date 
	 * @return the closest date from current date 
	 */
	private static LocalDateTime getNearestDate(LocalDateTime date, int intTime) {
		
		while (date.getDayOfWeek().getValue() != intTime) {
		    date=date.plusDays(1);
		}
		
		return date;
	}

    
}


```
