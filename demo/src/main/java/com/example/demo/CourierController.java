package com.example.demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

//below mwntioned class will be the controller class
@Controller // only for web projects, uses MVC

// @RestController is for access other web projects(distributable)-allows
// application to application interaction & doesnt use MVC
public class CourierController {
    private CourierController() {
    }

    // Singleton instance of CourierController
    private static CourierController instance = null;

    // Get method to retrieve the singleton instance
    public static CourierController getInstance() {
        if (instance == null) {
            synchronized (CourierController.class) {
                if (instance == null) {
                    instance = new CourierController();
                }
            }
        }
        return instance;
    }
    // @RequestMapping:- general-purpose annotation ,that can map a request to any
    // HTTP method (GET, POST, etc.).
    // the specific HTTP method can be explicitly mentioned: @RequestMapping(value =
    // "/users", method = RequestMethod.GET)

    @GetMapping(value = "/home") // HTTP GET METHOD
    public String home(Model model) {
        System.out.println("Homepg");
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    // below mentioned are models(MVC) that are injected through @Autowired
    // ;implicitly follow setter injection
    @Autowired
    private AdminService adminService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private CourierDetailRepository courierdetailRepository;
    @Autowired
    private DeliverLogRepository deliverlogRepository;

    @GetMapping("/admin")
    public String adminPage(User user, Model model) {
        model.addAttribute("user", new User());
        return "admin";
    }

    @GetMapping("/customer")
    public String cusPage(User user, Model model) {
        System.out.println("customerpg");
        model.addAttribute("user", new User());
        return "customer";
    }

    @GetMapping("/staff")
    public String staffPage(Staffuser user, Model model) {
        model.addAttribute("user", new Staffuser());
        return "staff";
    }

    @GetMapping("/stafflogin")
    public String staffloginPage(Model model) {
        model.addAttribute("user", new Staffuser());
        return "stafflogin";
    }

    @GetMapping("/cuslogin")
    public String loginCusPage(Model model) {
        model.addAttribute("user", new User());
        return "cuslogin";
    }

    @PostMapping(value="/login",consumes = {  MediaType.APPLICATION_FORM_URLENCODED_VALUE })
    public String processLogin(User user, Model model) {
        boolean isValid = adminService.validateLogin(user.getPassword(), user.getUsername());
        if (isValid) {
            
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "admin";
        }
    }

    @PostMapping("/stafflogin") // HTTP POST METHOD
    public String processStaffForm(@RequestParam String name,
            // @RequestParam String username,
            @RequestParam String password,
            @RequestParam String location) {
        Staff staff = new Staff();
        staff.setName(name);
        // staff.setUsername(username);
        staff.setPassword(password);
        staff.setLocation(location);

        staffService.saveStaff(staff);

        return "redirect:/stafflogin";
    }

    @GetMapping("/managestaff")
    public String manageStaff(Staffuser user, Model model) {
        List<Staff> staffList = staffRepository.findAll();
        System.out.println(staffList);
        model.addAttribute("staffList", staffList);
        return "managestaff";
    }

    @PostMapping("/removestaff")
    public String removeStaff(@RequestParam String id) {
        staffRepository.deleteById(id);
        return "redirect:/managestaff";
    }

    @PostMapping("/cuslogin")
    public String processCusLogin(User user, Model model) {
        boolean isValid = customerService.validateCusLogin(user.getPassword(), user.getUsername());
        if (isValid) {
            return "redirect:/cuslogin";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "customer";
        }
    }

    @GetMapping("/sendcourier")
    public String sendCourierPage() {
        return "sendcourier"; // Return the sendCourier.html page
    }

    @PostMapping("/submitCourier")
    public ResponseEntity<byte[]> submitCourier(@RequestParam String pickupAddress, @RequestParam String fromName,
            @RequestParam String pickupCity, @RequestParam String paymentMethod,
            @RequestParam String toName, @RequestParam String toMobile, @RequestParam String destinationAddress,
            @RequestParam String destinationCity, @RequestParam double weight, @RequestParam double totalCost) {

        // Generate random courier tracking number
        String trackingNumber = generateTrackingNumber();

        // Generate the courier receipt image
        BufferedImage image = generateReceiptImage(fromName, pickupAddress, pickupCity, paymentMethod, toName, toMobile,
                destinationAddress,
                destinationCity, weight, totalCost, trackingNumber);

        // Convert BufferedImage to byte array
        byte[] imageBytes = convertImageToByteArray(image);

        // Set response headers for image download
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentDispositionFormData("attachment", "courier_receipt.png");

        CourierDetails courierDetails = new CourierDetails();
        courierDetails.setFromName(fromName);
        courierDetails.setPickupAddress(pickupAddress);
        courierDetails.setPickupCity(pickupCity);
        courierDetails.setToName(toName);
        courierDetails.setToMobile(toMobile);
        courierDetails.setDestinationAddress(destinationAddress);
        courierDetails.setDestinationCity(destinationCity);
        courierDetails.setPaymentMethod(paymentMethod);
        courierDetails.setWeight(weight);
        courierDetails.setTotalCost(totalCost);
        courierDetails.setTrackingNumber(trackingNumber);
        courierDetails.setStatus("Booked");

        // Call installCourier method to save the courier details
        ResponseEntity<CourierDetails> response = installCourier(courierDetails);

        // Return the image bytes as ResponseEntity
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
    // @PutMapping-HTTP PUT METHOD(UPDATE OBJECTS)
    // @PatchMapping-HTTP PATCH METHOD ,PATCH: Used to partially update resources
    // (sending only the fields that need to be updated).

    @PostMapping("/installCourier")
    public ResponseEntity<CourierDetails> installCourier(@RequestBody CourierDetails courierDetails) {
        // Save courier details to the database
        CourierDetails savedCourier = courierdetailRepository.save(courierDetails);

        return ResponseEntity.ok(savedCourier);
    }

    private String generateTrackingNumber() {
        // Generate a random 6-digit tracking number
        Random random = new Random();
        int trackingNumber = 100000 + random.nextInt(900000);
        return String.valueOf(trackingNumber);
    }

    private BufferedImage generateReceiptImage(String fromName, String pickupAddress, String pickupCity,
            String paymentMethod, String toName,
            String toMobile, String destinationAddress, String destinationCity, double weight, double totalCost,
            String trackingNumber) {
        // Create a new BufferedImage
        int width = 600;
        int height = 400;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        // Set background color
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Set font and color for text
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 16));

        // Draw text on the image
        int x = 50;
        int y = 50;
        g2d.drawString("Courier Receipt", x, y);
        y += 30;
        g2d.drawString("From Name: " + fromName, x, y);
        y += 20;
        g2d.drawString("Pickup Address: " + pickupAddress, x, y);
        y += 20;
        g2d.drawString("Pickup City: " + pickupCity, x, y);
        y += 20;
        g2d.drawString("To Name: " + toName, x, y);
        y += 20;
        g2d.drawString("To Mobile: " + toMobile, x, y);
        y += 20;
        g2d.drawString("Destination Address: " + destinationAddress, x, y);
        y += 20;
        g2d.drawString("Destination City: " + destinationCity, x, y);
        y += 20;
        g2d.drawString("Weight of Parcel: " + weight + " kg", x, y);
        y += 20;
        g2d.drawString("Total Cost: Rs" + totalCost, x, y);
        y += 20;
        g2d.drawString("Payment Method: " + paymentMethod, x, y);
        y += 20;
        g2d.drawString("Tracking Number: " + trackingNumber, x, y);
        y += 20;
        g2d.drawString("Status: " + "Booked", x, y);
        // Dispose graphics
        g2d.dispose();

        return image;
    }

    private byte[] convertImageToByteArray(BufferedImage image) {
        // Convert BufferedImage to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    @GetMapping("/pickupLog")
    public String pickupLog(Model model) {
        // Fetch data from the database
        List<CourierDetails> courierDetailsList = courierdetailRepository.findAll();

        // Add the fetched data to the model
        model.addAttribute("courierDetailsList", courierDetailsList);

        // Return the pickupLog.html page
        return "pickupLog";
    }

    @GetMapping("/enterTrackingNumber")
    public String enterTrackingNumberPage() {
        return "enterTrackingNumber"; //
    }

    @GetMapping("/enterfullname")
    public String enterfullnamePage() {
        return "enterfullname"; //
    }

    @GetMapping("/deliverLog")
    public String deliverlogPage(Model model) {
        List<DeliverLog> deliverLogs = deliverlogRepository.findAll();

        // Add the fetched data to the model
        model.addAttribute("deliverLogs", deliverLogs);
        return "deliverLog"; //
    }

    @GetMapping("/checkStatus")
    public String checkStatus(@RequestParam String trackingNumber, Model model) {
        // Fetch courier details from the database based on tracking number
        Optional<CourierDetails> optionalCourier = courierdetailRepository.findByTrackingNumber(trackingNumber);

        // Check if courier details exist
        if (optionalCourier.isPresent()) {
            CourierDetails courier = optionalCourier.get();
            // Get the status from the fetched courier details
            String status = courier.getStatus();
            model.addAttribute("status", status);
        } else {
            // If courier details not found, set status as "Not Found"
            model.addAttribute("status", "Not Found");
        }
        // Return the HTML page for displaying status
        return "displayStatus";
    }

    @PostMapping("/pickup")
    public String pickup(@RequestParam String trackingNumber) {
        CourierDetails courierDetails = courierdetailRepository.findByTrackingNumber(trackingNumber).orElse(null);
        if (courierDetails != null) {
            courierDetails.setStatus("Picked");

            courierdetailRepository.save(courierDetails);
            // return "Courier with tracking number " + courierDetails.getTrackingNumber() +
            // " has been picked.";
            deliverlogRepository.save(new DeliverLog(courierDetails.getTrackingNumber(),
                    courierDetails.getToName(), courierDetails.getDestinationAddress(),
                    courierDetails.getDestinationCity()));
            return "pickupsuccess";
        }
        return "Courier not found or already picked.";
    }

    @PostMapping("/deliver")
    public String deliver(@RequestParam String trackingNumber) {
        CourierDetails courierDetails = courierdetailRepository.findByTrackingNumber(trackingNumber).orElse(null);
        if (courierDetails != null) {
            courierDetails.setStatus("Delivered");
            courierdetailRepository.save(courierDetails);
            return "deliversuccess";
        }
        return "Courier not found or already picked.";
    }

    @GetMapping("/searchByName")
    public String searchByName(@RequestParam String fromName, Model model) {
        List<CourierDetails> courierDetailsList = courierdetailRepository.findByFromName(fromName);
        model.addAttribute("courierDetailsList", courierDetailsList);
        return "displayResults"; // Create a new HTML template to display the search results
    }
}
