
public class BuildTM {
	private Action transitionfunction[][];
	private String states[];
	private char chars[];

	private int nbChars;
	private int nbStates;

	public BuildTM(String descriptionFilePath) throws Exception {
		if (!init(descriptionFilePath))
			throw new Exception("Error: the description file is not a valid file");
	}

	private boolean init(String descriptionFilePath) {
		try {
			Files file = new Files(descriptionFilePath);
			String[] lines = file.read();
			// start reading

			headReader(lines);

			for (int i = 3; i < lines.length; i++) {
				lines[i] = lines[i].trim();
				String currentState = lines[i];
				int CurrentStateIndex = getIndexOf(currentState);
				// System.out.println("i= " + i);
				for (int j = 0; j < nbChars; j++) {
					i++;
					char head = lines[i].charAt(0);
					int headIndex = getIndexOf(head);
					char write = lines[i].charAt(1);
					int direction = 0;
					switch (lines[i].charAt(2)) {
					case 'R':
					case 'r':
						direction = 1;
						break;
					case 'L':
					case 'l':
						direction = -1;
						break;
					default:
						return false;
					}
					String nextState = lines[i].substring(3, lines[i].length());
					// System.out.println("char:" + chars[headIndex] + "|
					// state:" + states[CurrentStateIndex]);
					transitionfunction[CurrentStateIndex][headIndex] = new Action(write, direction, nextState);
					// System.out.printf("i= %d, j= %d: current state= %d, head=
					// %d\n", i , j, CurrentState, head);
				}

			}
			return true;

		} catch (Exception e) {
//			e.printStackTrace();
			return false;
		}
	}

	private void headReader(String[] head) {
		// reading the first line witch describe the number of chars and states
		head[0] = head[0].trim();
		nbStates = Integer.parseInt(head[0].substring(head[0].indexOf('[') + 1, head[0].indexOf(',')));
		nbChars = Integer.parseInt(head[0].substring(head[0].indexOf(',') + 1, head[0].indexOf(']')));
		System.out.println("states: " + nbStates + " chars: " + nbChars);
		transitionfunction = new Action[nbStates][nbChars];
		// reading the second line witch is a set of states
		head[1] = head[1].trim();
		head[1] = head[1].substring(1, head[1].indexOf(']'));
		states = head[1].split(",");
		// reading the second line witch is a set of states
		head[2] = head[2].trim();
		head[2] = head[2].substring(1, head[2].indexOf(']'));
		head[2] = head[2].replace(",", "");
		chars = head[2].toCharArray();
		// System.out.println("states:");
		// for(int i = 0 ; i < states.length; i++){
		// System.out.println(states[i] + "-");
		// }
		//// System.out.println("chars:");
		// for(int i = 0 ; i < chars.length; i++){
		//// System.out.println(chars[i] + "-");
		// }
	}

	private void printTransition() {
		for (int i = 0; i < nbStates; i++) {
			for (int j = 0; j < nbChars; j++) {
				System.out.println("if read " + j + " from state " + i + " function: " + transitionfunction[i][j]);
			}
		}
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

	public Action[][] getTransitionfunction() {
		return transitionfunction;
	}

	public String[] getStates() {
		return states;
	}

	public char[] getChars() {
		return chars;
	}

}
