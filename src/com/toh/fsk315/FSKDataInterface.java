package com.toh.fsk315;

/**
 * Interface between FSKModem and OS-specific device (AndroidAudio)
 */
interface FSKDataInterface {

	public void feedPlayData(short[] audioData, int dataLen);

	public void pullRecData(short[] audioData, int dataLen);

}
