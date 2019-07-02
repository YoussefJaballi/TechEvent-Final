/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent;

import com.cloudinary.Cloudinary;
import edu.esprit.utils.ServiceManager;
import java.util.Map;
import com.cloudinary.utils.ObjectUtils;
import java.io.File;

/**
 *
 * @author Ben-o
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        File file = new File("my_image.jpg");
        Map uploadResult;
        Cloudinary cloudinary = new Cloudinary();

        uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
    }

}
