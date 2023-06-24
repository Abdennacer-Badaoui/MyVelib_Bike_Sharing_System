package fr.cs.group06.myVelib.userInterface;
import java.io.*;
import fr.cs.group06.myVelib.location.Location;
import fr.cs.group06.myVelib.myvelib.MyVelib;
import fr.cs.group06.myVelib.user.User;
import fr.cs.group06.myVelib.ride.*;
import java.util.Scanner;

import fr.cs.group06.myVelib.bike.*;
import fr.cs.group06.myVelib.cards.RegistrationCard;
import fr.cs.group06.myVelib.cards.RegistrationCardFactory;
import fr.cs.group06.myVelib.dockingStation.DockingStation;
import fr.cs.group06.myVelib.dockingStation.ParkingSlot;

import java.util.*;


public class MyVelibCLUI {
    private static final String PROMPT = "myVelib> ";
    static List<MyVelib> networkVelibs = new ArrayList<MyVelib>();
    
    public void setup(String[] parts) {
    	try {
        	if (parts.length == 7) {
            	String name = parts[1];
                int nOfStations = Integer.parseInt(parts[2]);
                int nslots = Integer.parseInt(parts[3]);
                double ratioOccupied = Double.parseDouble(parts[4]);
                double ratioElectrical = Double.parseDouble(parts[5]);
                double side = Double.parseDouble(parts[6]);
                for(MyVelib velib:networkVelibs) {
                	if (velib.getName().equalsIgnoreCase(name)) {
                	     System.out.println("There already exist a MyVelib network with the name you provided!");
                	     return;
                	}
                }
                MyVelib myvelib = new MyVelib(name,nOfStations,nslots,ratioOccupied,ratioElectrical,side);
                System.out.println(">>  MyVelib Network created Succesfully! ");
                networkVelibs.add(myvelib);
            }else if (parts.length == 2){
            	String name = parts[1];
            	Random random = new Random();
            	double ratioElec = random.nextDouble();
                MyVelib myvelib = new MyVelib(name,10,100,0.75,ratioElec,4);
                for(MyVelib velib:networkVelibs) {
                	if (velib.getName().equalsIgnoreCase(name)) {
                	     System.out.println("There already exist a MyVelib network with the name you provided!");
                	     return;
                	}
                }
                System.out.println(">>  MyVelib Network created Succesfully! ");
                networkVelibs.add(myvelib);
            } else {
                System.out.println("Invalid arguments for setup command.");
            }
        }catch(NumberFormatException e){
    		System.out.println("Error " + e.getMessage());
    	}catch(Exception e) {
    		System.out.println("Error: The sum of ratios must be less than or equal to 1.");
    	}
    }
    
    public void addUser(String[] parts) {
    	try {
			if (parts.length == 4) {
    			String name = parts[1];
    			String cardType = parts[2];
    			String velibname = parts[3];
    			
    			RegistrationCard mycard = null;
    			RegistrationCardFactory rgf = new RegistrationCardFactory();
    			if (cardType.equalsIgnoreCase("none")) {
    				mycard = rgf.createRegistrationCard(null);
    				}else if (cardType.equalsIgnoreCase("Vmax") || cardType.equalsIgnoreCase("Vlibre")) {
    				mycard = rgf.createRegistrationCard(cardType);
    				}else {
    					System.out.println("There is not such a name for registration cards! Try Vlibre or Vmax :)");
    					return;
    				}
    			
    			MyVelib myvelib = null;
    			int counter = 0;
    			for (MyVelib velib: networkVelibs) {
    				if (velib.getName().equalsIgnoreCase(velibname)) {
    					myvelib = velib;
    					counter++;
    				}
     			}
    			
    			if (counter == 0) {
    				System.out.println("There is no myVelib system that has the name you afforded");
    			}
    			else {
    				Random random = new Random();
    				double side = myvelib.getSide();
    				double locationx = random.nextDouble()*side;
    				double locationy = random.nextDouble()*side;
    				Location location = new Location(locationx, locationy);
    				User newuser = new User(name, location, null, mycard);
    				myvelib.addUser(newuser);
    				System.out.println("The user "+newuser.getUserName()+" that holds the id="+newuser.getID()+" has been added succesfully!");
    			
    			}

    		}else {
    			System.out.println("Sorry, you didin't use this command correctly!");
		}}catch(NumberFormatException e){
    		System.out.println("Error " + e.getMessage());
    	}
    	
    }
    public void offline(String[] parts) {
    	try {if (parts.length == 3) {
    		String name = parts[1];
    		int StationId = Integer.parseInt(parts[2]);
    		int counter=0;
    		for(MyVelib myvelib:networkVelibs) {
    			if(myvelib.getName().equalsIgnoreCase(name)) {
    				DockingStation station = myvelib.getDockingStation(StationId);
    				station.setStationStatus("Offline");
    				System.out.println("The Station "+StationId +" of the network "+ name + " is now offline");
    				counter++;
    			}
    		}
			if(counter==0) {
				System.out.println("There is no MyVelib network called " + name);
    		}
    		
    	} else {
            System.out.println("Invalid arguments for offline command.");
        }}
    	catch(NumberFormatException e){
    		System.out.println("Error " + e.getMessage());
    	}catch(Exception e) {
    		String name = parts[1];
    		int StationId = Integer.parseInt(parts[2]);
    		System.out.println("There is no station with id="+StationId+" in the MyVelib Network "+name+" !");
    	}
    }
    public void online(String[] parts) {
    	try {
    		if (parts.length == 3) {
        		String name = parts[1];
        		int StationId = Integer.parseInt(parts[2]);
        		int counter = 0;
        		for(MyVelib myvelib:networkVelibs) {
        			if(myvelib.getName().equalsIgnoreCase(name)) {
        				DockingStation station = myvelib.getDockingStation(StationId);
        				station.setStationStatus("Online");
        				System.out.println("The Station "+StationId +" of the network "+ name + " is now online");
        				counter++;
        			}
        		if(counter==0) {
    				System.out.println("There is no MyVelib network called " + name);
        		}
        		}
        	} else {
                System.out.println("Invalid arguments for online command.");
            }
    	}catch(NumberFormatException e){
    		System.out.println("Error " + e.getMessage());
    	}catch(Exception e) {
    		String name = parts[1];
    		int StationId = Integer.parseInt(parts[2]);
    		System.out.println("There is no station with id="+StationId+" in the MyVelib Network "+name+" !");
    	}
    }
    public void rentBike(String[] parts) throws Exception {
    	try {
			if (parts.length == 3) {
    			int user_id = Integer.parseInt(parts[1]);
    			String[] parts_2 = parts[2].split(",");
    			if (parts_2.length == 1) {
    				int station_id = Integer.parseInt(parts_2[0]);
    				MyVelib actual_velib = null;
    				DockingStation ds = null;
    				for (MyVelib myvelib : networkVelibs) {
    					for (DockingStation station : myvelib.getDockingStations()) {
    						if (station.getStationId() == station_id) {
    							ds = station;
    							actual_velib = myvelib;
    							break;
    						}
    					}
    				}
    				
    				int counter = 0;
    				Bike mybike = null;
    				if(ds==null) {
    					System.out.println("There is no station with id="+ station_id);
    					return;
    				}
    				for (ParkingSlot ps : ds.getParkingSlots()) {
    					if (ps.getBike() != null) {
    						mybike = ps.getBike();
    						counter++;
    						break;
    						
    					}
    				}
    				if (counter ==0) {
    					System.out.println("There is no bike in this station :(");
    				}else {
    					String type = "";
    					type = mybike.getBikeType();
    					User user = actual_velib.getUser(user_id);
    					if(user.hasRentedBike()) {
    						System.out.println(":( You can't rent a second bike ! You haven't returned the previous bike yet!  :(");
    						return;
    					}
    					ds.rentFromStation(user, type);
    					System.out.println("The bike has been rented succesfully, you rented a " + type + " bike !");
    				   }
					
    				}else {
    					if(parts_2.length == 2) {
    						double locationx = Double.parseDouble(parts_2[0]);
    						double locationy = Double.parseDouble(parts_2[1]);
    						Location bikelocation = new Location(locationx,locationy);
    						
    						DockingStation ds = null;
    						Bike mybike = null;
    						MyVelib myvelib = null;
    						int counterr = 0;
    						
    						
    						for (MyVelib velib : networkVelibs) {
    							for (DockingStation station : velib.getDockingStations()){
    								for (ParkingSlot ps : station.getParkingSlots()) {
    									if(ps.getBike()!=null) {
    										if (ps.getBike().getLocation().equals(bikelocation)) {
        										mybike = ps.getBike();
        										myvelib = velib;
        										ds = station;
        										counterr++;
        										break;
    										}
    									}
    								}
    							}
    						}
    						if (counterr == 0) {
    							System.out.println("There is no bike in the location above");
    						}
    						else {
    							User user = myvelib.getUser(user_id);
    							String type = mybike.getBikeType();
    							ds.rentFromStation(user, type);
    							System.out.println("The bike has been rented succesfully, you rented a " + type + " bike !");
    						}
    						
    					}else{
    						System.out.println("You haven't used the command correctly, try again !");
    					}
    				}
    			}
		}catch(NumberFormatException e){
    		System.out.println("Error " + e.getMessage());
    	}catch(Exception e) {
    		System.out.println("This station is offline!");
    	}
    }
    public void returnBike(String[] parts) throws Exception {
    	try {
			if(parts.length==4) {
				int userId = Integer.parseInt(parts[1]);
				double time = Double.parseDouble(parts[3]);
				String[] parts2 = parts[2].split(",");
				if(parts2.length==1) {
					int stationId = Integer.parseInt(parts2[0]);
					MyVelib velib = null;
					DockingStation endStation = null;
					for (MyVelib myvelib:networkVelibs) {
						for (DockingStation station:myvelib.getDockingStations()) {
							if(station.getStationId()==stationId) {
								velib=myvelib;
								User user = velib.getUser(userId);
								DockingStation startStation = user.getStartStation();            								
								endStation=station;
								if (user.getRentedBike()==null) {
									System.out.println("You don't have a bike to return it:/");
									break;
								}
								Ride ride = null;
								double cost = 0.0;
								if(endStation.hasFreeSlot() && endStation.getStationStatus().equalsIgnoreCase("online")) {
								    ride = new Ride(user,user.getRentedBike(),startStation,endStation,startStation.getStationLocation(),endStation.getStationLocation(),time);
									System.out.println(" Duration :"+time+" min.          Bike Type: "+user.getRentedBike().getBikeType());
									velib.dropbike(ride);
									cost = ride.calculateCost();
									System.out.println(" The cost of your Ride is = "+cost+" euros.");
	                                return;
								}else {
									System.out.println("There is no free solts available in this station or the station is offline :(");
								}
									
							}
						}
							
					}
				}else if(parts2.length==2) {
					double locationx = Double.parseDouble(parts2[0]);
					double locationy = Double.parseDouble(parts2[1]);
					Location endlocation = new Location(locationx,locationy);
					MyVelib velib = null;
					User user = null;
					for (MyVelib myvelib:networkVelibs) {
						for(User renter:myvelib.getUsers()) {
							if(renter.getID()==userId) {
								user=renter;
								DockingStation startStation = user.getStartStation(); 
								if (user.getRentedBike()==null) {
									System.out.println("You don't have a bike to return it:/");
									break;
								}
								Ride ride = new Ride(user,user.getRentedBike(),startStation,null,startStation.getStationLocation(),endlocation,time);
								velib.dropbike(ride);
								double cost = ride.calculateCost();
								System.out.println(" The cost of your Ride is = "+ride.getCost()+" euros.");
                                break; 
							}
						}
					}
				}
				
			}else {
				System.out.println("the returnBike command take 3 arguments!");
			}
			
		}catch(NumberFormatException e){
    		System.out.println("Error " + e.getMessage());
    	}
    }
    public void displayStation(String[] parts) {
    	try {
    		if (parts.length == 3) {
        		String name = parts[1];
        		int StationId = Integer.parseInt(parts[2]);
        		int counter=0;
        		for(MyVelib myvelib:networkVelibs) {
        			if(myvelib.getName().equalsIgnoreCase(name)) {
        				DockingStation station = myvelib.getDockingStation(StationId);
        				myvelib.StationBalance(station);
        				counter++;
        			}
    			if(counter==0) {
    				System.out.println("There is no MyVelib network called " + name);
        		}
        		}
        	} else {
                System.out.println("Invalid arguments for online command.");
            }
    	}catch(NumberFormatException e){
    		System.out.println("Error " + e.getMessage());
    	}catch(Exception e) {
    		String name = parts[1];
    		int StationId = Integer.parseInt(parts[2]);
    		System.out.println("There is no station with id="+StationId+" in the MyVelib Network "+name+" !");
    	}
    }
    public void displayUser(String[] parts) throws Exception {
    	try {
    		if (parts.length == 3) {
        		String name = parts[1];
        		int userId = Integer.parseInt(parts[2]);
        		int counter = 0;
        		for(MyVelib myvelib:networkVelibs) {
        			if(myvelib.getName().equalsIgnoreCase(name)) {
        				User user = myvelib.getUser(userId);
        				myvelib.UserBalance(user);
        				counter++;
        			}
    			if(counter==0) {
    				System.out.println("There is no MyVelib network called " + name);
        		}
        		}
        	} else {
                System.out.println("Invalid arguments for online command.");
            }
    	}catch(NumberFormatException e){
    		System.out.println("Error " + e.getMessage());
    	}catch(Exception e) {
    		System.out.println("There is no User with this Id in this MyVelib Network !");
    	}
    }
    public void sortStation(String[] parts) {
    	try {
    		if (parts.length == 3) {
        		String name = parts[1];
        		String policy = parts[2];
        		int counter=0;
        		for(MyVelib myvelib:networkVelibs) {
        			if(myvelib.getName().equalsIgnoreCase(name)) {
        				myvelib.sort(policy);
        				counter++;
        			}
        		}
        		if(counter==0) {
    				System.out.println("There is no MyVelib network called " + name);
        		}
        	} else {
                System.out.println("Invalid arguments for sortStation command.");
            }
    	}catch(NumberFormatException e){}
    }
    public void display(String[] parts) {
    	try {
    		if (parts.length == 2) {
        		String name = parts[1];
        		int counter=0;
        		for(MyVelib myvelib:networkVelibs) {
        			if(myvelib.getName().equalsIgnoreCase(name)) {
        				MyVelib velib = myvelib;
        				System.out.println("============ "+name+" VelibNetwork ===========");
						for(DockingStation station: velib.getDockingStations()) {
        					station.showDockingStationState();
        				}
						System.out.println("==== Users :");
						int i = 1;
        				for(User user:velib.getUsers()) {
        					System.out.println(i+". Name = "+user.getUserName());
        		
        				}
        				counter++;
        			}
    			if(counter==0) {
    				System.out.println("There is no MyVelib network called " + name);
        		}
        		}
        	} else {
                System.out.println("Invalid arguments for display command.");
            }
    	}catch(NumberFormatException e){
    		System.out.println("Error " + e.getMessage());
    	}
    }
    
    public void runtest(String filename) throws Exception {
    	networkVelibs.clear();
        boolean running = true;

        System.out.println("Welcome to MyVelib Command Line User Interface!");
        

        try (BufferedReader br = new BufferedReader(new FileReader(new File(MyVelibCLUI.class.getResource(filename).getFile())))) {
            String line;
            int counter = 1;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split(" ");
                System.out.println("               ");
                System.out.println("Step ."+counter);
                System.out.println("               ");
                counter++;
                if (parts.length > 0) {
                    String command = parts[0];
                    
                    switch (command) { 
                        
                        case "setup":
                            setup(parts);
                            break;
                        case "addUser":
                            addUser(parts);
                            break;
                        case "offline":
                            offline(parts);
                            break;
                        case "online":
                            online(parts);
                            break;
                        case "rentBike":
                            rentBike(parts);
                            break;
                        case "returnBike":
                            returnBike(parts);
                            break;
                        case "sortStation":
                        	sortStation(parts);
                        case "displayStation":
                            displayStation(parts);
                            break;
                        case "displayUser":
                            displayUser(parts);
                            break;
                        case "display":
                        	display(parts);
                        default:
                            System.out.println("Unknown command: " + line);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } finally {
        	networkVelibs.clear();
        	networkVelibs.add(new MyVelib("defaultNetwork",10,100,0.75,0.5,4.0));
        	
        }

        System.out.println("End of commands. Exiting MyVelib Command Line User Interface.");
    }

    

    public void run() throws Exception {
    	
    	MyVelibCLUI clui = new MyVelibCLUI();
    	BufferedReader bufferedReader = null;
    	try {
    		bufferedReader = new BufferedReader(new FileReader(new File(MyVelibCLUI.class.getResource("my_velib.ini").getFile()))); 
            String command="";
            String name = "";
            int numberOfStations = 0;
            int numberOfSlots = 0;
            double ratioOccupied = 0.0;
            double ratioElectrical = 0.0;
            double side = 0.0;
            String myinfo="";
            String line;
            while ((line = bufferedReader.readLine()) != null) {
            	String[] parts2 = line.split(":");
            	myinfo=myinfo+" "+parts2[1];
            }
            String[] parts3 = myinfo.split(" ");
            command = parts3[1];
            name = parts3[2];
            numberOfStations = Integer.parseInt(parts3[3]);
            numberOfSlots = Integer.parseInt(parts3[4]);
            ratioOccupied = Double.parseDouble(parts3[5]);
            ratioElectrical = Double.parseDouble(parts3[6]);
            side = Double.parseDouble(parts3[7]);
            MyVelib myvelib = new MyVelib(name,numberOfStations,numberOfSlots,ratioOccupied,ratioElectrical,side);
            System.out.println(">>  MyVelib defaultNetwork created Succesfully! ");
            networkVelibs.add(myvelib);
            
    	}finally {
    		if(bufferedReader!=null) {
    			try {bufferedReader.close();}
    			catch(IOException e) {}
    			
    		}
    	}


    	
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(PROMPT);
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            String command = parts[0];
        
        

            switch (command) {
                case "setup":
                    clui.setup(parts);
                    break;
                    
                case "offline":
                	clui.offline(parts);
                    break;
                case "online":
                	clui.online(parts);
                    break;
                    
            	case "addUser" :
            		clui.addUser(parts);
            		break;
                    
                case "displayStation" :
                	clui.displayStation(parts);
                    break;
                    
                case "displayUser" :
                	clui.displayUser(parts);
                    break;
                	
                case "sortStation":
                	clui.sortStation(parts);
                    break;
                	
            	case "rentBike" :
            		clui.rentBike(parts);	
            		break;
            	
            	case "returnBike":
            		clui.returnBike(parts);
            		break;
            
            		
                case "display":
                	clui.display(parts);
                    break;
                    
                case "runtest":
                	clui.runtest(parts[1]);
                	break;

 
                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }

}