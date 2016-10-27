package heapsort;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TestMaxPQ {
	public static void main(String[] args) throws IOException {
		MaxPQ test = new MaxPQ();
		test.create_heap();
		test.build_max_heap(test.heap);

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int subject_number = 0;
		String subject_name = "";

		System.out.println("**** 현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다 ****");

		int flag = 0;

		while( flag != 6 ){
			try {
				test.resultPrint();

				System.out.println("------------------------------------");
				System.out.println("1. 작업 추가       2. 최대값      3. 최대 우선순위 작업 처리");
				System.out.println("4. 원소 키값 증가                       5. 작업 제거     6. 종료");
				System.out.println("------------------------------------");
				int arg = input.nextInt(); 
				
				while( arg != 6 ) {

					if( arg == 1) {

						System.out.println("**** 현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다 ****");
						test.resultPrint();

						System.out.println("------------------------------------");
						System.out.println("1. 작업 추가       2. 최대값      3. 최대 우선순위 작업 처리");
						System.out.println("4. 원소 키값 증가                       5. 작업 제거     6. 종료");
						System.out.println("------------------------------------");

						System.out.print("신규 작업명 <20 bytes 이내> : ");
						subject_name = input.next();

						System.out.print("우선 순위 ( 0 ~ 999 ) : ");
						try {
							subject_number = input.nextInt();

							Subject subject = new Subject(subject_number, subject_name);
							test.insert(test.heap, subject);

							System.out.println("**** 작업 추가 완료 ****");
							test.resultPrint();

							System.out.println("------------------------------------");
							System.out.println("1. 작업 추가       2. 최대값      3. 최대 우선순위 작업 처리");
							System.out.println("4. 원소 키값 증가                       5. 작업 제거     6. 종료");
							System.out.println("------------------------------------");
							arg = input.nextInt();
						} catch (InputMismatchException e) {
							System.out.println("**** ERROR : 잘못된 입력 ****");
							input.nextLine();
							arg = 1;
						}
					 
					}

					else if( arg == 2) {		
						Subject max_subject = test.max(test.heap);

						System.out.println("**** MAX : "+ max_subject.toString() +" ****");
						test.resultPrint();

						System.out.println("------------------------------------");
						System.out.println("1. 작업 추가       2. 최대값      3. 최대 우선순위 작업 처리");
						System.out.println("4. 원소 키값 증가                       5. 작업 제거     6. 종료");
						System.out.println("------------------------------------");

						arg = input.nextInt();
					}

					else if( arg == 3 ) {
						test.extract_max(test.heap);

						System.out.println("**** 한 개의 작업을 처리했습니다 ****");
						test.resultPrint();

						System.out.println("------------------------------------");
						System.out.println("1. 작업 추가       2. 최대값      3. 최대 우선순위 작업 처리");
						System.out.println("4. 원소 키값 증가                       5. 작업 제거     6. 종료");
						System.out.println("------------------------------------");

						arg = input.nextInt();
					}

					else if( arg == 4 ) {
						System.out.println("**** 수정할 노드를 입력하세요. ****");
						test.resultPrint();

						System.out.println("------------------------------------");
						System.out.println("1. 작업 추가       2. 최대값      3. 최대 우선순위 작업 처리");
						System.out.println("4. 원소 키값 증가                       5. 작업 제거     6. 종료");
						System.out.println("------------------------------------");
						
						System.out.print("수정할 노드 x : ");
						try {
							int x = input.nextInt();

							System.out.print("수정할 key : ");
							try {
								subject_number = input.nextInt();

								test.increase_key(test.heap, x, subject_number);

								System.out.println("**** 한 개의 작업을 처리했습니다 ****");
								test.resultPrint();

								System.out.println("------------------------------------");
								System.out.println("1. 작업 추가       2. 최대값      3. 최대 우선순위 작업 처리");
								System.out.println("4. 원소 키값 증가                       5. 작업 제거     6. 종료");
								System.out.println("------------------------------------");

								arg = input.nextInt();

							} catch(InputMismatchException e) {
								System.out.println("**** ERROR : 잘못된 입력 ****");
								input.nextLine();
								arg = 4;
							}
						} catch (InputMismatchException e) {
							System.out.println("**** ERROR : 잘못된 입력 ****");
							input.nextLine();
							arg = 4;
						}
						
						
					}

					else if( arg == 5 ) {
						System.out.println("**** 삭제할 노드를 입력하세요. ****");
						test.resultPrint();

						System.out.println("------------------------------------");
						System.out.println("1. 작업 추가       2. 최대값      3. 최대 우선순위 작업 처리");
						System.out.println("4. 원소 키값 증가                       5. 작업 제거     6. 종료");
						System.out.println("------------------------------------");
						System.out.print("삭제할 노드 x 입력 : ");
						try {
							int x = input.nextInt();

							test.h_delete(test.heap, x);

							System.out.println("**** 한 개의 작업을 처리했습니다 ****");
							test.resultPrint();

							System.out.println("------------------------------------");
							System.out.println("1. 작업 추가       2. 최대값      3. 최대 우선순위 작업 처리");
							System.out.println("4. 원소 키값 증가                       5. 작업 제거     6. 종료");
							System.out.println("------------------------------------");

							arg = input.nextInt();
						} catch(InputMismatchException e) {
							System.out.println("**** ERROR : 잘못된 입력 ****");
							input.nextLine();
							arg = 5;
						}
					}

					else {
						System.out.println("**** 잘못된 입력입니다 ****");
						test.resultPrint();

						System.out.println("------------------------------------");
						System.out.println("1. 작업 추가       2. 최대값      3. 최대 우선순위 작업 처리");
						System.out.println("4. 원소 키값 증가                       5. 작업 제거     6. 종료");
						System.out.println("------------------------------------");

						arg = input.nextInt();

					}

					flag = arg;
				}

			} catch(InputMismatchException e) {
				System.out.println("**** ERROR : 잘못된 입력 ****");
				input.nextLine();
				flag = 0;
			}
		}
	}

}
