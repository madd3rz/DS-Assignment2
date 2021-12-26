
import java.util.ArrayList;
import java.util.List;

//Group 4 Aima Aimaduddin Bin Bollhasan (65332) Alexander Anak Harris (64613) Milton Bruen Ak Pali (66645) Lau Ngie Hing (66424) Woo Zhenghan (68097) 
public class Threading {
	static String foundPassword;
	static boolean isFound;
	static ArrayList<String> values = new ArrayList<>();
    int cores = Runtime.getRuntime().availableProcessors();
	static int serverNo;

	static List<MD5Thread> listOfThreads = new ArrayList<MD5Thread>();
	
	// method to start the server
	public static void startServer(String hashcode,int noOfThreads, int passwordLength, int server) {
		serverNo = server;
		List<String> intervals = intervals(noOfThreads);
		startThreads(hashcode,intervals,passwordLength);
	}

	// this method takes number of threads and returns intervals to search 
	public static List<String> intervals(int noOfThreads) {

		int charStart = 0;
		int charEnd = 0;

		if(serverNo == 1){
			charStart = 33;
			charEnd = 80;
		} else  if(serverNo == 2){
			charStart = 80;
			charEnd = 127;
		}

		System.out.println(charEnd);

		List<String> listOfInterval = new ArrayList<String>();
		int diffRange = charEnd - charStart;
		int quot = diffRange/noOfThreads;
		int x=charStart;
		List<Integer> steps = new ArrayList<Integer>();
		for(int i=charStart;i<charEnd;i+=quot) {
			x=i;
			steps.add(x);
		}
		if(steps.get(steps.size()-1)<charEnd-1) {
			steps.set(steps.size()-1,charEnd-1);
		}
		
		for(int i=0;i<steps.size()-1;i++) {
			int a = steps.get(i);
			int b;
			try {
				b = steps.get(i+1);
			}
			catch(Exception e) {b=steps.get(i);}
			listOfInterval.add(a+"-"+b);
		}
		return listOfInterval;
	}

	// this function will get intervals and start thread for each interval
	public static void startThreads(String hashcode,List<String> ints, int passwordLength) {
		// for each loop to intialize thread and put all the threads inside a list (listOfThreads)
		for(String inte:ints) {
			MD5Thread thread = new MD5Thread(inte,hashcode,passwordLength);
			listOfThreads.add(thread);
		}
		// for each loop - start all the threads that is inside listOfThreads
		for(var Thread:listOfThreads) {
			Thread.start();
		}
	}
	
	// this function will stop all the running threads.
 	 public static void stop_threads() {
		//for each loop - stop all the running threads inside the listOfThreads
		for(MD5Thread thread:listOfThreads) {
            thread.interrupt();
		}
	}

	public static void sendBack(String password, int threadID, double totalTime) {
		String thread = Integer.toString(threadID);
		String time = Double.toString(totalTime);
		values.add(password);
		values.add(thread);
		values.add(time);
		System.out.println("AL: " + values);
		//foundPassword = password;
		
	}

	public static ArrayList<String> getPass(){
		return (values);
	}  
	
}
