package com.saassignment.osgi.datastore;

import java.util.ArrayList;

import java.util.List;

import com.saassignment.osgi.model.SurgeryApplication;
//Store the shared data among the producer and the consumers
public class ApplicationDataStore {
	public static List<SurgeryApplication> surgicalList = new ArrayList<SurgeryApplication>();
}
