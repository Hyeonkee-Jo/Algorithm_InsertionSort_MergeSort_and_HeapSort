package heapsort;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class MaxPQ {
	
	Subject[] heap = new Subject[65];
	int init_size = 1;
	int heap_size;
	
	public void create_heap() throws IOException {
		
		FileInputStream input = new FileInputStream("data03.txt");
		InputStreamReader reader = new InputStreamReader(input);
		StreamTokenizer token = new StreamTokenizer(reader);
		
		int line_Number = 1;
		String data = "";
		
		for(int i = 0; i < 65; i++) {
			heap[i] = new Subject();
		}
		
		while((token.nextToken() != StreamTokenizer.TT_EOF)) {
			switch(token.ttype){
			case StreamTokenizer.TT_NUMBER :
				if (init_size == 0) {
					
				}
				else if (init_size == 1) {
					if (line_Number == token.lineno()) heap[init_size].setSubjectNo((int)token.nval);
					else {
						init_size++;
						heap[init_size].setSubjectNo((int)token.nval);
					}
				}
				else {
					init_size++;
					heap[init_size].setSubjectNo((int)token.nval);
				}
				break;
			
			case StreamTokenizer.TT_WORD :
				if(line_Number == token.lineno()) {
					data = data + " " + token.sval;
					heap[init_size].setSubjectName(data);
				}
				else {
					line_Number++;
					data = token.sval;
					heap[init_size].setSubjectName(data);
				}
				break;
			}
		}
		input.close();
	}
	
	// implement MAX-HEAPIFY(A, i)
	public void max_heapify(Subject[] heap_node, int index) {
		int largest;
		int left_child = 2*index;
		int right_child = 2*index+1;
		
		if (left_child <= heap_size && heap_node[left_child].subject_no > heap_node[index].subject_no){
			largest = left_child;
		} 
		else {
			largest = index;
		}
		
		if (right_child <= heap_size && heap_node[right_child].subject_no > heap_node[largest].subject_no) {
			largest = right_child;
		}
		
		if (largest != index) {
			Subject temp;
			temp = heap_node[index];
			heap_node[index] = heap_node[largest];
			heap_node[largest] = temp;
			this.max_heapify(heap_node, largest);
		}
	}
	
	// implement BUILD-MAX-HEAP(A)
	public void build_max_heap(Subject[] heap_node) {
		heap_size = init_size;
		
		for(int i = init_size/2; i >= 1; i = i/2) {
			this.max_heapify(heap_node, i);
		}
	}
	
	// a. implement insert(S, x)
	public void insert(Subject[] heap_node, Subject x) {
		init_size++;
		heap_node[init_size] = x;
		this.build_max_heap(heap_node);
	}
	
	// b. implement max(S)
	public Subject max(Subject[] heap_node) {
		return heap_node[1];
	}
	
	// c. implement extract_max(S)
	public void extract_max(Subject[] heap_node) {
		heap_node[1].setSubjectNo(heap_node[init_size].getSubjectNo());
		heap_node[1].setSubjectName(heap_node[init_size].getSubjectName());
		heap_node[init_size].setSubjectNo(0);
		heap_node[init_size].setSubjectName(null);
		init_size--;
		this.build_max_heap(heap_node);
	}
	
	// d. implement increase_key(S, x, k)
	public void increase_key(Subject[] heap_node, int x, int k) {
		heap_node[x].setSubjectNo(k);
		
		for(int i = x/2; i >= 1; i = i/2) {
			this.max_heapify(heap_node, i);
			if(i == 0) break;
		}
	}
	
	// e. implement h_delete(S, x)
	public void h_delete(Subject[] heap_node, int x) {
		heap_node[x].setSubjectNo(heap_node[init_size].getSubjectNo());
		heap_node[x].setSubjectName(heap_node[init_size].getSubjectName());
		heap_node[init_size].setSubjectNo(0);
		heap_node[init_size].setSubjectName(null);
		init_size--;
		this.max_heapify(heap_node, x);
	}
	
	public void resultPrint() {
		for(int i = 1; i <= init_size; i++) {
			System.out.println(heap[i].toString());
		}
	}
}