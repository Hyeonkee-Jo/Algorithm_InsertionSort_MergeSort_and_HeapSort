import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class MergeSort {
	
	ArrayList<Integer> mergeList = new ArrayList<Integer>();
	String data;
	long start_time;
	long end_time;
	int merge_count = 0;
	
	public MergeSort() throws IOException {
		
		FileOutputStream output = new FileOutputStream("C:/Users/jo930_000/workspace/Algorithm_hw_01/hw02_00_201202143_merge_100man.txt");
		
		FileInputStream input = new FileInputStream("hw02_100man.txt");
		InputStreamReader reader = new InputStreamReader(input);
		StreamTokenizer token = new StreamTokenizer(reader);
		
		while((token.nextToken() != StreamTokenizer.TT_EOF)) {
			switch(token.ttype){
			case StreamTokenizer.TT_NUMBER :
				mergeList.add((int) token.nval);
			break;
			}
		}
		
		input.close();
		
		// 정렬
		start_time = System.currentTimeMillis();
		this.mergeSort(mergeList, mergeList.size());
		end_time = System.currentTimeMillis();
		
		System.out.println("elapse time : " + (end_time - start_time) + "ms");
		System.out.println("merge count : " + merge_count);
		
		data = mergeList.get(0).toString();
		output.write(data.getBytes());
		for(int index = 1; index < mergeList.size(); index++) {
			data =  "," + mergeList.get(index).toString(); 
			output.write(data.getBytes());
		}
		data = " -> merge count : "+ merge_count;
		output.write(data.getBytes());
		output.close();
	}
	
	public void mergeSort(ArrayList<Integer> list, int index_count) {
		if(index_count<2) return;
		
		int mid = index_count/2;
		
		ArrayList<Integer> left_Array = new ArrayList<Integer>();
		ArrayList<Integer> right_Array = new ArrayList<Integer>();
		
		for(int i = 0; i < mid; i++) left_Array.add(i, list.get(i));
		for(int j = mid; j < index_count; j++) right_Array.add(j-mid, list.get(j));
		
		this.mergeSort(left_Array, mid);
		this.mergeSort(right_Array, index_count-mid);
		
		this.merge(list, left_Array, mid, right_Array, index_count-mid);
		
	}
	
	public void merge(ArrayList<Integer> list, ArrayList<Integer> left_Array, int left_count, ArrayList<Integer> right_Array, int right_count) {
		
		merge_count++;
		int i = 0, j = 0, k = 0;
		
		while( i < left_count && j < right_count) {
			if(left_Array.get(i) < right_Array.get(j)) list.set(k++, left_Array.get(i++));
			else list.set(k++, right_Array.get(j++));		
		}
		
		while(i<left_count) {
			list.set(k++, left_Array.get(i++));
		}
		while(j<right_count) {
			list.set(k++, right_Array.get(j++));
		}
	}
	
	public static void main(String[] args) throws IOException {
		new MergeSort();
	}

}
