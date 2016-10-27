import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class ThreeWayMerge {
	
	ArrayList<Integer> three_way_mergeList = new ArrayList<Integer>();
	String data;
	long start_time;
	long end_time;
	int merge_count = 0;
	
	public ThreeWayMerge() throws IOException {
		
		FileOutputStream output = new FileOutputStream("C:/Users/jo930_000/workspace/Algorithm_hw_01/hw02_00_201202143_3way_merge_1000man.txt");
		
		FileInputStream input = new FileInputStream("hw02_1000man.txt");
		InputStreamReader reader = new InputStreamReader(input);
		StreamTokenizer token = new StreamTokenizer(reader);
		
		while((token.nextToken() != StreamTokenizer.TT_EOF)) {
			switch(token.ttype){
			case StreamTokenizer.TT_NUMBER :
				three_way_mergeList.add((int) token.nval);
			break;
			}
		}
		
		input.close();
		
		// 정렬
		start_time = System.currentTimeMillis();
		this.mergeSort(three_way_mergeList, three_way_mergeList.size());
		end_time = System.currentTimeMillis();
		
		System.out.println("elapse time : " + (end_time - start_time) + "ms");
		System.out.println("merge count : " + merge_count);
		
		data = three_way_mergeList.get(0).toString();
		output.write(data.getBytes());
		for(int index = 1; index < three_way_mergeList.size(); index++) {
			data =  "," + three_way_mergeList.get(index).toString();
			output.write(data.getBytes());
		}
		
		data = " -> merge count : "+ merge_count;
		output.write(data.getBytes());
		output.close();	
	}
	
	public void mergeSort(ArrayList<Integer> list, int index_count) {
		
		if(index_count < 2) return;
		
		if(index_count == 2) {
			ArrayList<Integer> left_Array = new ArrayList<Integer>();
			ArrayList<Integer> right_Array = new ArrayList<Integer>();
			
			left_Array.add(0, list.get(0));
			right_Array.add(0, list.get(1));
			
			this.mergeSort(left_Array, 1);
			this.mergeSort(right_Array, 1);
			
			int k = 0;
			
			merge_count++;
			
			if(left_Array.get(0) < right_Array.get(0)) {
				list.set(k++, left_Array.get(0));
				list.set(k, right_Array.get(0));
			}
			else {
				list.set(k++, right_Array.get(0));
				list.set(k, left_Array.get(0));
			}
		}
		
		else {
			int mid = index_count/3;
			
			ArrayList<Integer> left_Array = new ArrayList<Integer>();
			ArrayList<Integer> middle_Array = new ArrayList<Integer>();
			ArrayList<Integer> right_Array = new ArrayList<Integer>();
			
			for(int i = 0; i < mid; i++) left_Array.add(i, list.get(i));
			for(int j = mid; j < 2*mid; j++) middle_Array.add(j-mid, list.get(j));
			for(int k = 2*mid; k < index_count; k++) right_Array.add(k-(2*mid), list.get(k));
			
			this.mergeSort(left_Array, mid);
			this.mergeSort(middle_Array, mid);
			this.mergeSort(right_Array, index_count-(2*mid));
			
			this.merge(list, left_Array, mid, middle_Array, mid, right_Array, index_count-(2*mid));
		}
		
	}
	
	// 3way
	public void merge(ArrayList<Integer> list, ArrayList<Integer> left_Array, int left_count, ArrayList<Integer> middle_Array, int middle_count, ArrayList<Integer> right_Array, int right_count) {
		
		merge_count++;
		int x = 0, y = 0, z = 0, k = 0;
		
		while( x < left_count && y < middle_count && z < right_count) {
			if(left_Array.get(x) < middle_Array.get(y)) {
				if(left_Array.get(x) < right_Array.get(z)) list.set(k++, left_Array.get(x++));
				else {
					list.set(k++, right_Array.get(z++));
				}
			}
			else {
				if(middle_Array.get(y) < right_Array.get(z)) list.set(k++, middle_Array.get(y++));
				else {
					list.set(k++, right_Array.get(z++));
				}
			}	
		}
		
		while (y < middle_count && z < right_count) {
			if(middle_Array.get(y) < right_Array.get(z)) list.set(k++, middle_Array.get(y++));
			else list.set(k++, right_Array.get(z++));
		}
		
		while (x < left_count && z < right_count) {
			if(left_Array.get(x) < right_Array.get(z)) list.set(k++, left_Array.get(x++));
			else list.set(k++, right_Array.get(z++));
		}
		
		while (x < left_count && y < middle_count) {
			if(left_Array.get(x) < middle_Array.get(y)) list.set(k++, left_Array.get(x++));
			else list.set(k++, middle_Array.get(y++));
		}
		
		while(x<left_count) {
			list.set(k++, left_Array.get(x++));
		}
		while(y<middle_count) {
			list.set(k++, middle_Array.get(y++));
		}
		while(z<right_count) {
			list.set(k++, right_Array.get(z++));
		}
	}
	
	public static void main(String[] args) throws IOException {
		new ThreeWayMerge();
	}

}
