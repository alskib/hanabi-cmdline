package edu.shsu.hanabi_cmdline;

public class Tokens {
	private int clockTokens;
	private int fuseTokens;
	
	public Tokens() {
		this.clockTokens = 1;
		this.fuseTokens = 3;
	}
	
	public int getClockTokens() {
		return this.clockTokens;
	}
	
	public int getFuseTokens() {
		return this.fuseTokens;
	}
	
	public void incClockTokens() {
		if (this.clockTokens <= 8)
			this.clockTokens++;
	}
	
	public void decClockTokens() {
		if (this.clockTokens > 0)
			this.clockTokens--;
	}
	
	public boolean decFuseTokens() {	//	If only 1 fuse token left, and there needs a decrement,
		if (this.fuseTokens > 1) {		//		it means the players lose. No point in actually
			this.fuseTokens--;			//		decrementing.
			return false;
		}
		return true;
	}
}
