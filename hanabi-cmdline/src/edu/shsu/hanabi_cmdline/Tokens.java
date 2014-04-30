package edu.shsu.hanabi_cmdline;

public class Tokens {
	private int clockTokens;
	private int fuseTokens;
	
	//	Tokens used to allow or limit player actions
	public Tokens() {
		this.clockTokens = 8;
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
	
	//	If only 1 fuse token left, and there needs a decrement, it means the
	//		players lose. No point in actually decrementing.
	public boolean decFuseTokens() {	
		if (this.fuseTokens > 1) {		
			this.fuseTokens--;			
			return false;
		}
		return true;
	}
}
