/**
 *  Update of the Deposit Slot to simulate Timed activation of the slot
 */

package au.edu.rmit.ct;

public class DepositSlotTimed extends com.dietel.DepositSlot implements Runnable  {
	private Thread t;
	private String threadName;
	
	private boolean modeActive = false;
	private long waitTime = 1200; // default wait time
	
	private boolean debug = true;

	DepositSlotTimed(String name) {
		threadName = name;
		if(debug) System.out.println("Creating " + threadName);
	}

	DepositSlotTimed(String name, long customWaitTime) throws Exception {
		if(customWaitTime > 5000)
			throw new IllegalWaitTimeException("Your custom wait time of "+ customWaitTime+ " is too high");
		threadName = name;
		this.waitTime = customWaitTime;
		if(debug)  System.out.println("Creating " + threadName);
	}

	public void run() {
		if(debug)  System.out.println("Running " + threadName);
		try {
			if(debug)  System.out.println("Thread: " + threadName + "set to run");
			this.activateSlot(); // allow deposits to be received - activate the slot
			Thread.sleep(waitTime); // ... by wait_time
		} catch (InterruptedException e) {
			if(debug)  System.out.println("Thread " + threadName + " interrupted.");
		}
		finally {
			if(debug) System.out.println("Thread " + threadName + " exiting.");
			deActivateSlot(); // at end of the wait time deactivate the slot.
		}
	}

	boolean isOpenForDeposit() {
		return this.modeActive;
	}

	boolean receiveEnvelope() {
		if (modeActive == false) {
			return false;
		} else {
			return true;
		}
	}

	void activateSlotWithTimedShut() {
		this.start();
	}
	
	private void start() {
		if(debug) System.out.println("Starting " +  threadName );
	    if (t == null) {
		
	    	t = new Thread (this, threadName );
			if(debug) System.out.println("Created new t Thread " +  threadName );
    		t.start ();
	    }
	}

	private void activateSlot() {
		this.modeActive = true; // or set error.
		// this.run();
	}

	private void deActivateSlot() { // sets deposit slot to be available for deposits
		this.modeActive = false;
	}
	
	public void checkTimer() {
		this.run();
	}
}
