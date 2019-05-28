package com.assignment.demo.billPayment;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import com.assignment.demo.billPayment.CustomerTypes;


@RestController
public class BillPayment {
	
	public static final String EMPLOYEE = "EMPLOYEE";
	public static final String AFFILIATED = "AFFILIATED";
	public static final String OLD_CUST = "OLD_CUST";
		
	
	@RequestMapping(method=RequestMethod.POST, value="/bill")
	public double calculatePayableAmount(@RequestBody Bill bill){
		
		System.out.println("Inside Controller");
		
		double netPayableAmount = 0.0;
		
		String customerType = bill.getCustomerType();
		
		
		if("NON_GROCERY".equalsIgnoreCase(bill.getBillType())) {
			
			if(customerType!=null) {
				
				if(customerType.equalsIgnoreCase(EMPLOYEE)) {
					
					netPayableAmount = bill.getBillAmount()-(bill.getBillAmount()*0.3);
					
					
				}else if(customerType.equalsIgnoreCase(AFFILIATED)) {
					
					netPayableAmount = bill.getBillAmount()-(bill.getBillAmount()*0.1);
					
					
				}else if(customerType.equalsIgnoreCase(OLD_CUST)) {
					
					netPayableAmount = bill.getBillAmount()-(bill.getBillAmount()*0.05);
					
					
				}
				
			}else if(bill.getBillAmount() >= 100) {
				
				netPayableAmount = bill.getBillAmount()-(bill.getBillAmount()*0.05);
			
			}
		}
		return netPayableAmount;
		
	}
	
}


