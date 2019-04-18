package application;


public class MyThread{

	private StringBuilder linearEquation;
	private int number;
	private boolean flag;
	private boolean flagResult;
	
	public  void calculate() {
//		while (flag) {
//	         try {
//	            wait();
//	         }
//	         catch (InterruptedException e) {
//	        	 System.out.println("Thread has been interrupted");
//	         }
//	    }
//		flag=true;
		int flagSignX=0;
		for(int i = 0; i <linearEquation.length(); i++) { //проверка на минус у x
			if(linearEquation.charAt(i)=='x') {
				if(i==1 || (i==linearEquation.length()-1 && linearEquation.charAt(linearEquation.length()-3)=='=') && linearEquation.charAt(i-1)=='-'){
					linearEquation.deleteCharAt(i-1);
					flagSignX=1;
				}
				else if( i!=0 && linearEquation.charAt(i-1)=='-') {
					linearEquation.setCharAt(i-1,'+');
					flagSignX=1;
				}
			} 
		}
		if(flagSignX==1) { // меняем все знаки если -x
			int flagNumber=0;
			for(int i = 0; i <linearEquation.length(); i++) {
				if(linearEquation.charAt(i)>='0' && linearEquation.charAt(i)<='9') {
					int flag=0;
					while(linearEquation.charAt(i)>='0' && linearEquation.charAt(i)<='9') {  
					   if(flag==0) {
						   if(i==0 || linearEquation.charAt(i-1)=='=') {
							   linearEquation.insert(i, '-');		
							   i++;
							   flag=1;
						   } else if(i-1==0 || linearEquation.charAt(i-2)=='='){
							   linearEquation.deleteCharAt(i-1);
							   //i--;							   
							   flag=1;
						   } else if(linearEquation.charAt(i-1)=='+'){
							   linearEquation.setCharAt(i-1,'-');
							   flag=1;
						   } else if(linearEquation.charAt(i-1)=='-'){
							   linearEquation.setCharAt(i-1,'+');
							   flag=1;
						   }
					   }
					   i++;
					   if(flagNumber==1) break;
					   flagNumber=1;					   
					}
					i--;
				}
			}
		}
		int i=0;
		//делаем x=..
		System.out.println(linearEquation);
		// если пример x=1+1 то нормально
		//пример x+1=1
		if(linearEquation.charAt(0)=='x' && linearEquation.charAt(i+1)!='=') {
			i++;
			if(linearEquation.charAt(i)=='+') {
				linearEquation.setCharAt(i,'-');
			} else if(linearEquation.charAt(i)=='-') {
				linearEquation.setCharAt(i,'+');
			}
			StringBuilder temp=new StringBuilder();
			int pos=0;
			while(linearEquation.charAt(i)!= '=') {
				temp.append(linearEquation.charAt(i));
				i++;
				pos=i;
			}
			linearEquation.delete(1,pos);
			linearEquation.append(temp);			
		} //пример x=1+1
		else if(linearEquation.charAt(0)=='x' && linearEquation.charAt(i+1)=='=') {			
			//ничего не делаем			
		}//пример 1+1=x 
		else if(linearEquation.charAt(linearEquation.length()-1)=='x' && linearEquation.charAt(linearEquation.length()-2)=='=') {
			StringBuilder temp=new StringBuilder();
			i=linearEquation.length()-1;
			temp.append(linearEquation.charAt(i));
			i--;
			temp.append(linearEquation.charAt(i));
			linearEquation.delete(linearEquation.length()-2,linearEquation.length());
			linearEquation.insert(0, temp);
		}//пример 1=1+x
		else if(linearEquation.charAt(linearEquation.length()-1)=='x' && linearEquation.charAt(linearEquation.length()-2)=='+') {
			i=0;
			int pos=0;
			if(linearEquation.charAt(i)!='-') {
				linearEquation.insert(i, '+');
			}
			while(linearEquation.charAt(i)!='=') {				
				i++;
			}
			i++;
			if(linearEquation.charAt(i)=='-') {
				linearEquation.deleteCharAt(i);
			} else {
				linearEquation.insert(i, '-');
			}
			StringBuilder temp=new StringBuilder();
			for(i = 0; i <linearEquation.length(); i++) {
				temp.append(linearEquation.charAt(i));
				pos=i;
				if(linearEquation.charAt(i+1)<'0' || linearEquation.charAt(i+1)>'9') {
					break;
				}
			}
			linearEquation.delete(0,pos+1);
			linearEquation.delete(linearEquation.length()-2,linearEquation.length());
			linearEquation.append(temp);
			linearEquation.insert(0, 'x');
			
		}//пример 1+x=1 или 1=x+1
		else {
			for(int j = 0; j <linearEquation.length(); j++) {
				if(linearEquation.charAt(j)=='x' && linearEquation.charAt(j+1)=='=') {
					if(linearEquation.charAt(i)=='-') {
						linearEquation.setCharAt(i,'+');
					}
					else {
						linearEquation.insert(i, '-');
					}
					StringBuilder temp=new StringBuilder();
					int pos=0;
					for(i = 0; i <linearEquation.length(); i++) {
						temp.append(linearEquation.charAt(i));
						if(linearEquation.charAt(i+1)=='+') {
							pos=i+2;
							break;
						}
					}
					linearEquation.delete(0,pos);
					linearEquation.append(temp);
				}
				else if(linearEquation.charAt(j)=='x' && linearEquation.charAt(j+1)!='='){
					i=++j;
					if(linearEquation.charAt(i)=='-') {
						linearEquation.setCharAt(i,'+');
					}
					else {
						linearEquation.setCharAt(i,'-');
					}
					i=0;
					StringBuilder temp=new StringBuilder();
					//int pos=0;
					for(i = 0; i <linearEquation.length(); i++) {
						temp.append(linearEquation.charAt(i));
						if(linearEquation.charAt(i+1)=='=') {							
							//pos=i+2;
							break;
						}
					}
					temp.insert(0, '=');
					linearEquation.delete(0,j);
					temp.insert(0, 'x');
					linearEquation.insert(0,temp);
				}
			}
		}
		System.out.println(linearEquation);
		i=2;
		StringBuilder temp=new StringBuilder();
		String tempTo1=new String();
		String tempTo2=new String();
		int number1,number2;
		if(linearEquation.charAt(i)=='-') {
			temp.append(linearEquation.charAt(i));
			i++;
		}
		while(linearEquation.charAt(i)>='0' && linearEquation.charAt(i)<='9') {
			temp.append(linearEquation.charAt(i));
			i++;
		}
		tempTo1=temp.toString();
		number1=Integer.parseInt(tempTo1);
		System.out.println(number1);
		temp.setLength(0);
		while(i <linearEquation.length()) {
			temp.append(linearEquation.charAt(i));
			i++;
		}
		tempTo2=temp.toString();
		number2=Integer.parseInt(tempTo2);
		System.out.println(number2);
		int result;
		result=number1+number2;
		System.out.println(result);
		if(result==number) {
			flagResult=true;
		}
		System.out.println("Поток "+number);
//		notify();
//		flag=false;
	}

	public void setEquation(String linearEquation) {
		this.linearEquation=new StringBuilder(linearEquation);
	}
	
	public void setNumber(int number) {
		this.number=number;
	}
	
	public int getNumber() {
		return number;
	}
	
	public boolean getFlagResult() {
		return flagResult;
	}
}
