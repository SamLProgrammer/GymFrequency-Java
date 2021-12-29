package persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import models.Gym;

public class ReadAndWrite {

	public Gym read() throws FileNotFoundException {
		Gson gson = new GsonBuilder().setLenient().create();
		JsonParser parser = new JsonParser();
		FileReader file = new FileReader("src/files/Gym.json");
		JsonElement datos = parser.parse(file);
		String json = datos.toString();
	
		Gym list = gson.fromJson(json, Gym.class);

		return list;
	}

	public void write(Gym list){
		if(list != null) {
		Gson json = new Gson();
		String exportar = json.toJson(list);
		FileWriter file;
		try {
			file = new FileWriter("src/files/Gym.json");
			file.write(exportar);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        } else {
        	JOptionPane.showMessageDialog(null, "No hay informacion para almacenar en un archivo");
        }     		
	}
}