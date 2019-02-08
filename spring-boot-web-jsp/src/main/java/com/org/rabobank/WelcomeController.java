package com.org.rabobank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletContext;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WelcomeController {

	// private static String UPLOADED_FOLDER = "C:\\PH-panneer\\assign";
	 
	 @Autowired
	   ServletContext context;

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
			
		return "welcome";
	}
	
	@PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,Map<String, Object> model) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
        	System.out.println("File = "+file.getOriginalFilename());
        	 File file1=new File(file.getOriginalFilename());
        	System.out.println("File1 "+file1.getName()); 
        	/*byte[] bytes = file.getBytes();
             Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
             Files.write(path, bytes);

             File fil = new File(UPLOADED_FOLDER + file.getOriginalFilename());*/
        	
            // Get the file and save it somewhere
            /*byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");*/
        	
        	
        	String uploadPath = context.getRealPath("") + File.separator ;
            //Now do something with file...
        	System.out.println("uploadPath = "+uploadPath);
        	System.out.println("orgpath = "+file.getName());//+file.getOriginalFilename()
            FileCopyUtils.copy(file.getBytes(), new File(uploadPath+file1.getName()));
            //String fileName = multipartFile.getOriginalFilename();
            
        	
            List<CustomerStatement> lsCustomerStatement = new ArrayList<CustomerStatement>();
            
            System.out.println("upload pathxxx"+new File(uploadPath+file.getOriginalFilename()).getAbsolutePath());
            BufferedReader br = new BufferedReader(new FileReader(new File(uploadPath+file1.getName())));

            String line = br.readLine(); // Reading header, Ignoring

            CustomerStatement objCustomerStatement;
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                String[] fields = line.split(",");
                String Fname = fields[0];
                String surname = fields[1];
                String issuecnt = fields[2];
                System.out.println("line = "+line);
                objCustomerStatement = new CustomerStatement(fields[0],fields[1],fields[2],fields[3]);
                
                lsCustomerStatement.add(objCustomerStatement);
            }
            br.close();
            
            System.out.println("lsCustomerStatement = "+lsCustomerStatement);
            Gson gson=new Gson();
            Gson gsonBuilder = new GsonBuilder().create();	
            gsonBuilder.toJson(lsCustomerStatement);
            System.out.println("Gson===="+gsonBuilder.toJson(lsCustomerStatement));
        	model.put("lsdata", lsCustomerStatement);
        	
    		return "welcome";

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "welcome";
    }

 	
	/*public CSVParser csvparse(File file) throws FileNotFoundException, IOException {
		CSVParser parser=new CSVParser(new FileReader(file),CSVFormat.DEFAULT);
		List<CSVRecord> list=parser.getRecords();
		for(CSVRecord record:list) {
			String[] arr=new String[record.size()];
			int i =0;
			for(String str:arr) {
				arr[i++]=str;
			}
			
		}
		try {
			parser.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parser;
	}*/
	
	
}