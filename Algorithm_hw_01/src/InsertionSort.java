import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class InsertionSort {
	
	ArrayList<Integer> insertionList = new ArrayList<Integer>();
	String data;
	int temp_index = 0;
	long start_time;
	long end_time;
	
	public InsertionSort() throws IOException {
		
		FileOutputStream output = new FileOutputStream("C:/Users/jo930_000/workspace/Algorithm_hw_01/hw02_00_201202143_insertion_10man.txt");
		
		FileInputStream input = new FileInputStream("hw02_10man.txt");
		InputStreamReader reader = new InputStreamReader(input);
		StreamTokenizer token = new StreamTokenizer(reader);

		while((token.nextToken() != StreamTokenizer.TT_EOF)) {
			switch(token.ttype){
			case StreamTokenizer.TT_NUMBER :
				insertionList.add((int) token.nval);
			break;
			}
		}
		
		input.close();
		
		// 정렬
		start_time = System.currentTimeMillis();
		for(int index = 1; index < insertionList.size(); index++) {
			temp_index = index;
			
			while(temp_index > 0 && insertionList.get(temp_index) < insertionList.get(temp_index-1)) {
				int temp = insertionList.get(temp_index);
				insertionList.set(temp_index, insertionList.get(temp_index-1));
				insertionList.set(temp_index-1, temp);
				temp_index--;
			}
		}
		end_time = System.currentTimeMillis();
		System.out.println("elapse time : " + (end_time - start_time) + "ms");
		
		data = insertionList.get(0).toString();
		output.write(data.getBytes());
		for(int index = 1; index < insertionList.size(); index++) {
			data =  "," + insertionList.get(index).toString(); 
			output.write(data.getBytes());
		}
		
		output.close();	
		
	}
	
	public static void main(String[] args) throws IOException {
		new InsertionSort();
	}
}
