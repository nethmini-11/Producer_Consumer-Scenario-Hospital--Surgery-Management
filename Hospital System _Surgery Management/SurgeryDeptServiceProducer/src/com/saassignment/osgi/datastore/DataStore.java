package com.saassignment.osgi.datastore;

import java.util.ArrayList;

import java.util.List;

import com.saassignment.osgi.model.Surgery;
//Store the shared data among the producer and the consumers
public class DataStore {
	public static List<Surgery> surgicalList = new ArrayList<Surgery>();
}
