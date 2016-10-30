import java.io.File;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;

public class ZapdosTest {

	public static void main(String[] args) {
		VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
		service.setApiKey("b79ded46f8c8d9d0f93335e5a6970b3e8d703077");

		System.out.println("Classify an image");
		ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
		    .images(new File("src/test/resources/visual_recognition/car.jpg"))
		    .build();
		VisualClassification result = service.classify(options).execute();
		System.out.println(result);

	}

}
