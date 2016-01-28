import java.util.Arrays;

public class TuringMachine {
	private Action transitionfunction[][];
	private String states[];
	private char chars[];

	private char[] inputTape;
	private int headPosition;
	private int state;

	private String resultMessage;

	public TuringMachine(String Describtion, String inputTape) throws Exception {
		
		BuildTM build = new BuildTM(Describtion);
		this.transitionfunction = build.getTransitionfunction();
		this.states = build.getStates();
		this.chars = build.getChars();
		this.inputTape = inputTape.concat("***").toCharArray();
		headPosition = 0;
		state = 0;
		resultMessage = "You should run the Turing Machine to get a result Message";
		
	}

	public void run() {
		while (!isHalt()) {
			step();
			System.out.println(getInputTape());
		}
	}

	public void step() {
		Action action = transitionfunction[state][head()];
		state = getIndexOf(action.getNextState());
		inputTape[headPosition] = action.getWrite();
		headPosition += action.getDirection();
	}

	private boolean isHalt() {
		if (transitionfunction[state][head()].getNextState().equals("A")) {
			resultMessage = "Accepted";
			return true;
		} else if (transitionfunction[state][head()].getNextState().equals("R")) {
			resultMessage = "Rejected";
			return true;
		} else
			return false;
	}

	private int getIndexOf(String x) {
		for (int i = 0; i < states.length; i++) {
			if (states[i].equals(x))
				return i;
		}
		return -1;
	}

	private int getIndexOf(char x) {
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == x)
				return i;
		}
		return -1;
	}

	private int head() {
		return getIndexOf(inputTape[headPosition]);
	}

	public String getInputTape() {
		return String.valueOf(inputTape);
	}

	public String getResultMessage() {
		return resultMessage;
	}

	@Override
	public String toString() {
		return "states=" + Arrays.toString(states) + ", chars=" + Arrays.toString(chars) + "]\ntransitions:"
				+ transition();
	}

	private String transition() {
		String s = "\n";
		for (int i = 0; i < states.length; i++) {
			for (int j = 0; j < chars.length; j++) {
				s = s + "[" + i + "," + j + "] = " + transitionfunction[i][j].toString() + " -";
			}
			s = s + "\n";
		}
		return s;
	}

}
