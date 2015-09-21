package com.toh.fsk1225;

/**
 * Configuration values for FSKModem (from the C Macros : #define)
 */
interface FSKConfig {

	// Baud-Rate & Frequencies {
	//public final static int FREQ_LOW = FSKPreset.Baud315.FREQ_LOW;
	//public final static int FREQ_HIGH = FSKPreset.Baud315.FREQ_HIGH;
	//public final static int BAUD = FSKPreset.Baud315.BAUD;
	
	/*
	public final static int FREQ_LOW = FSKPreset.Baud315.FREQ_LOW;
	public final static int FREQ_HIGH = FSKPreset.Baud315.FREQ_HIGH;
	public final static int BAUD = FSKPreset.Baud315.BAUD;
	*/
	
	/*
	public final static int FREQ_LOW = FSKPreset.Baud630.FREQ_LOW;
	public final static int FREQ_HIGH = FSKPreset.Baud630.FREQ_HIGH;
	public final static int BAUD = FSKPreset.Baud630.BAUD;
	*/
	
	
	public final static int FREQ_LOW = FSKPreset.Baud1225.FREQ_LOW;
	public final static int FREQ_HIGH = FSKPreset.Baud1225.FREQ_HIGH;
	public final static int BAUD = FSKPreset.Baud1225.BAUD;
	
	
	// }

	// Sampling-Rate for Audio In/Out {
	public final static int SAMPLE_RATE = 44100;
	//public final static int SAMPLE_RATE = 8000;
	//public final static int SAMPLE_RATE = 11025;
	// }

	// Log Constants {
	public static final String logTag = "TOH_FSK";
	public static final boolean logFlag = true;
	public static final int logLevel = FSKLog.INFO;
	// }
}
