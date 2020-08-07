package fit5042.tutex;

import fit5042.tutex.repository.PropertyRepository;
import fit5042.tutex.repository.PropertyRepositoryFactory;
import fit5042.tutex.repository.entities.Property;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * TODO Exercise 1.3 Step 3 Complete this class. Please refer to tutorial instructions.
 * This is the main driver class. This class contains the main method for Exercise 1A
 * 
 * This program can run without the completion of Exercise 1B.
 * 
 * @author Junyang
 */
public class RealEstateAgency {
    private String name;
    private final PropertyRepository propertyRepository;

    public RealEstateAgency(String name) throws Exception {
        this.name = name;
        this.propertyRepository = PropertyRepositoryFactory.getInstance();
    }
    
    // this method is for initializing the property objects
    // complete this method
    public void createProperty() {
        try {
			propertyRepository.addProperty(new Property(1, "15 King St", 5, 250, 36000));
			propertyRepository.addProperty(new Property(2, "16 King St", 4, 170, 26000));
	        propertyRepository.addProperty(new Property(3, "17 King St", 3, 140, 18000));
	        propertyRepository.addProperty(new Property(4, "18 King St", 4, 180, 23000));
	        propertyRepository.addProperty(new Property(5, "19 King St", 5, 260, 34000));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    // this method is for displaying all the properties
    // complete this method
    public void displayProperties() {
        try {
			for (Property p : propertyRepository.getAllProperties()) {
				System.out.println(p.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    // this method is for searching the property by ID
    // complete this method
    public void searchPropertyById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Plz enter the property id:");
        int input = sc.nextInt();
        
        try {
			Property p = propertyRepository.searchPropertyById(input);
			System.out.println(p != null? p.toString(): "No items found");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void run() {
        createProperty();
        System.out.println("********************************************************************************");
        displayProperties();
        System.out.println("********************************************************************************");
        searchPropertyById();
    }
    
    public static void main(String[] args) {
        try {
            new RealEstateAgency("FIT5042 Real Estate Agency").run();
        } catch (Exception ex) {
            System.out.println("Application fail to run: " + ex.getMessage());
        }
    }
}
