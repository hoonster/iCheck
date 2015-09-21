package com.toh.fsk1225;

/**
 * Receive the byte-data from recorded audio
 */
public interface FSKModemListener1225 {
	public void dataReceivedFromFSKModem(byte[] data);
}
