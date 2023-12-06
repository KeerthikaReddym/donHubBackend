package com.donHub.donHub.common;

public class CommonMethods {
	public Long generateUniqueNumber() {
        // Implement your logic to generate a unique number
        // For simplicity, combining timestamp and random number
        long timestamp = System.currentTimeMillis();
        long randomPart = (long) (Math.random() * 1000); // Adjust the range as needed

        return timestamp * 1000 + randomPart;
    }

}
