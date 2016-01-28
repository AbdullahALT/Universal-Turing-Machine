
public class Action {
	private char write;
	private int direction;
	private String nextState;
	
	public Action(char write, int direction, String nextState) {
		this.write = write;
		this.direction = direction;
		this.nextState = nextState;
	}

	@Override
	public String toString() {
		return "Action [write=" + write + ", direction=" + direction + ", nextState=" + nextState + "]";
	}

	public char getWrite() {
		return write;
	}

	public int getDirection() {
		return direction;
	}

	public String getNextState() {
		return nextState;
	}
	
	
	
	
	
	
}
