package com.knf.dev.demo.javahttpclientokhttpunitestdemo.client;

import java.io.IOException;

public class DriverClass {

	public static void main(String[] args) {

		// For demo purpose
		PostClient client = 
				new PostClient
				  ("https://jsonplaceholder.typicode.com");
		try {
			System.out.println(client.fetchAllPosts());
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
