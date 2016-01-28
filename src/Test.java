
public class Test {
	public static void main(String args[]) {
		try {
			TuringMachine tm = new TuringMachine("C:/Users/abo_tam/Desktop/describtion1.txt", "*");
			System.out.println(tm);
			System.out.println("----------");
			System.out.println(tm.getInputTape());
			tm.run();
			System.out.println(tm.getResultMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
