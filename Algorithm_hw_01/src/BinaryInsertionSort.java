import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class BinaryInsertionSort {
	ArrayList<Integer> binary_insertionList = new ArrayList<Integer>();
	String data;
	long start_time;
	long end_time;
	
	public BinaryInsertionSort() throws IOException {
		
		FileOutputStream output = new FileOutputStream("C:/Users/jo930_000/workspace/Algorithm_hw_01/hw02_00_201202143_binary_insertion_10man.txt");
		
		FileInputStream input = new FileInputStream("hw02_10man.txt");
		InputStreamReader reader = new InputStreamReader(input);
		StreamTokenizer token = new StreamTokenizer(reader);

		while((token.nextToken() != StreamTokenizer.TT_EOF)) {
			switch(token.ttype){
			case StreamTokenizer.TT_NUMBER :
				binary_insertionList.add((int) token.nval);
			break;
			}
		}
		
		input.close();
		
		//정렬
		start_time = System.currentTimeMillis();
		for(int index = 0; index < binary_insertionList.size(); ++index) {
			int target = binary_insertionList.get(index);
			int left = 0;
			int right = index;
			
			int mid;
			while(left  < right) {
				mid = (left+right)/2;
				if(target >= binary_insertionList.get(mid)){
					left = mid+1;
				} else {
					right = mid;
				}
			
			}
			
			for ( int swap_index = index; swap_index>left; --swap_index) {
				swap(binary_insertionList, swap_index-1, swap_index);
			}
			
		}
		end_time = System.currentTimeMillis();
		System.out.println("elapse time :  " + (end_time - start_time) + "ms");
		
		data = binary_insertionList.get(0).toString();
		output.write(data.getBytes());
		for(int index = 1; index < binary_insertionList.size(); index++) {
			data =  "," + binary_insertionList.get(index).toString();
			output.write(data.getBytes());
		}
		
		output.close();
	}
	
	public void swap(ArrayList<Integer> list, int index1, int index2) {
		int temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
	}
	
	
	public static void main(String[] args) throws IOException {
		new BinaryInsertionSort();
	}

}
