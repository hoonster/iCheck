package com.toh.fsk315;

/**
 * Receive the byte-data from recorded audio
 */
public interface FSKModemListener315 {
	public void dataReceivedFromFSKModem(byte[] data);
}
