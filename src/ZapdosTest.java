import java.io.File;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.RecognizedText;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualRecognitionOptions;

public class ZapdosTest {

	private static final String API_KEY = "b79ded46f8c8d9d0f93335e5a6970b3e8d703077";

	public static void main(String[] args) {
		classifyImage(service(), "right.jpg", "turns_1711095970");
	}
	
	public static VisualRecognition service()
	{
		VisualRecognition service = new VisualRecognition(
				VisualRecognition.VERSION_DATE_2016_05_20);
		service.setApiKey(API_KEY);
		return service;
	}
	
	public static void classifyImage(VisualRecognition service, String imageFilename, String classifierId)
	{
		System.out.println("Classify an image");
		ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
				.images(new File(
						"src/test/resources/visual_recognition/" + imageFilename))
				.classifierIds(classifierId)
				.build();
		VisualClassification result = service.classify(options).execute();
		System.out.println(result);
	}
	
	public static void updateTraining(String classiferId) {
		VisualRecognition service = service();
		System.out.println("Train images");
		ClassifierOptions options = new ClassifierOptions.Builder()
		        .classifierName("turns")
				.addClass(
						"left",
						new File(
								"src/test/resources/visual_recognition/leftTurns.zip"))
				.addClass(
						"right",
						new File(
								"src/test/resources/visual_recognition/rightTurns.zip"))
				.negativeExamples(
						new File(
								"src/test/resources/visual_recognition/straight.zip"))
				.build();
		VisualClassifier result = service.updateClassifier(classiferId, options).execute();
        System.out.println(result);
	}
	
	public static void recognizeText(VisualRecognition service, String imageFilename)
	{
		VisualRecognitionOptions options = new VisualRecognitionOptions.Builder().images(new File(
				"src/test/resources/visual_recognition/" + imageFilename)).build();
		RecognizedText result = service.recognizeText(options).execute();
		System.out.println(result);
	}

	public static void train() {
		VisualRecognition service = service();
		System.out.println("Train images");
		ClassifierOptions options = new ClassifierOptions.Builder()
		        .classifierName("turns")
				.addClass(
						"left",
						new File(
								"src/test/resources/visual_recognition/leftTurns.zip"))
				.addClass(
						"right",
						new File(
								"src/test/resources/visual_recognition/rightTurns.zip"))
				.negativeExamples(
						new File(
								"src/test/resources/visual_recognition/straight.zip"))
				.build();
		VisualClassifier result = service.createClassifier(options).execute();
        System.out.println(result);
	}
	
	

}
