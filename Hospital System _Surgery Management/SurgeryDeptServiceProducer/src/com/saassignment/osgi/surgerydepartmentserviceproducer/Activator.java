package com.saassignment.osgi.surgerydepartmentserviceproducer;

import org.osgi.framework.BundleActivator;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.saassignment.osgi.billingservice.BillingService;
import com.saassignment.osgi.billingserviceimpl.BillingServiceImpl;
import com.saassignment.osgi.managementservice.ManagementService;
import com.saassignment.osgi.managementserviceimpl.SurgeryDeptServiceImpl;
import com.saassignment.osgi.surgeryservice.SurgeryService;
import com.saassignment.osgi.surgeryserviceimpl.SurgeryServiceImpl;

public class Activator implements BundleActivator {
	ServiceRegistration serviceRegisterer;
	@Override
	public void start(BundleContext context) throws Exception {//Life cycle method-start
		System.out.println("					     <<<<<<<<<<<<<<<< SERVICE PRODUCER START >>>>>>>>>>>>>>>>");
		System.out.println("");
		System.out.println("");
		BillingService billingService = new BillingServiceImpl();
		serviceRegisterer = context.registerService(BillingService.class.getName().toString(), billingService, null);//registering the billingService
		ManagementService managerService = new SurgeryDeptServiceImpl();
		serviceRegisterer = context.registerService(ManagementService.class.getName(), managerService, null); //registering the managerService
		
		SurgeryService surgeryService = new SurgeryServiceImpl();
		serviceRegisterer = context.registerService(SurgeryService.class.getName(), surgeryService, null);//registering the surgeryService
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {//Life cycle method-stop
		System.out.println("					     <<<<<<<<<<<<<<<< SERVICE PRODUCER Stopped >>>>>>>>>>>>>>>>");
		serviceRegisterer.unregister();
	}

}
