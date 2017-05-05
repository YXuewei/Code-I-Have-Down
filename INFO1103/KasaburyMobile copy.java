/**
 * Kasabury Mobile Phone Class.
 *
 * INFO1103 Assignment 3, Semester 1, 2017.
 *
 * KasaburyMobile
 * In this assignment you will be creating an Kasabury Mobile Phone as part of a simulation.
 * The Mobile phone includes several attributes unique to the phone and has simple functionality.
 * You are to complete 2 classes. KasaburyMobile and KasaburyContact
 *
 * The phone has data
 *  Information about the phone state.
 *    If it is On/Off
 *    Battery level
 *    If it is connected to network.
 *    Signal strength when connected to network
 *  Information about the current owner saved as contact information.
 *    First name
 *    Last name
 *    Phone number
 *  A list of 10 possible contacts.
 *    Each contact stores first name, last name, phone number and chat history up to 40 messages
 *
 * The phone has functionality
 *  Turning on the phone
 *  Charging the phone. Increase battery level
 *  Change battery (set battery level)
 *  Use phone for k units of battery (decreases battery level by k)
 *  Search/add/remove contacts
 *
 * Attribute features
 *  if the phone is off. It is not connected.
 *  if the phone is not connected there is no signal strength
 *  the attribute for battery life has valid range [0,100]. 0 is flat, 100 is full.
 *  the attribute for signal strength has a valid range [0, 5]. 0 is no signal, 5 is best signal.
 *
 * Please implement the methods provided, as some of the marking is
 * making sure that these methods work as specified.
 *
 * @author A INFO1103 tutor.
 * @date April, 2017
 *
 */


public class KasaburyMobile
{




	/* Use this to store contacts. Do not modify. */
	protected KasaburyContact[] contacts;



	/* Every phone manufactured has the following attributes
	 *
	 * the phone is off
	 * the phone has battery life 5
	 * the phone is not connected
	 * the phone has signal strength 0
	 * the phone has factory passcode is 1234
	 * Each of the contacts stored in the array contacts has a null value
	 *
	 * the owner first name "Kasabury"
	 * the owner last name is "Incorporated"
	 * the owner phone number is "180076237867"
	 * the owner chat message should have only one message
	 *         "Thank you for choosing Kasabury products"
	 *
	 */
	public KasaburyMobile() {
		/* given */
		contacts =


	}

	/* returns a copy of the owner contact details
	 * return null if the phone is off
	 */
	public KasaburyContact getCopyOfOwnerContact() {

	}


	/* only works if phone is on
	 * will add the contact in the array only if there is space and does not exist
	 * The method will find an element that is null and set it to be the contact
	 */
	public boolean addContact(KasaburyContact contact) {

	}

	/* only works if phone is on
	 * find the object and set the array element to null
	 */
	public boolean removeContact(KasaburyContact contact) {

	}

	/* only works if phone is on
	 * return the number of contacts, or -1 if phone is off
	 */
	public int getNumberOfContacts() {

	}

	/* only works if phone is on
	 * returns all contacts that match firstname OR lastname
	 * if phone is off, or no results, null is returned
	 */
	public KasaburyContact[] searchContact(String name) {

	}

	/* returns true if phone is on
	 */
	public boolean isPhoneOn() {

	}

	/* when phone turns on, it costs 5 battery for startup. network is initially disconnected
	 * when phone turns off it costs 0 battery, network is disconnected
	 * always return true if turning off
	 * return true if can successfully turned on and have at least battery level 1
	 * return false if do not have enough battery level
	 */
	 public boolean setPhoneOn(boolean on) {

	}

	/* Return the battery life leve. if the phone is off, zero is returned.
	 */
	public int getBatteryLife() {

	}

	/* Change battery of phone.
	 * On success. The phone is off and new battery level adjusted and returns true
	 * If newBatteryLevel is outside manufacturer specification of [0,100], then
	 * no changes occur and returns false.
	 */
	public boolean changeBattery(int newBatteryLevel) {

	}

	/* only works if phone is on.
	 * returns true if the phone is connected to the network
	 */
	public boolean isConnectedNetwork() {

	}

	/* only works if phone is on.
	 * when disconnecting, the signal strength becomes zero
	 */
	public void disconnectNetwork() {

	}

	/* only works if phone is on.
	 * Connect to network
	 * if already connected do nothing
	 * if connecting:
	 *  1) signal strength is set to 1 if it was 0
	 *  2) it will cost 2 battery life to do so
	 * returns the network connected status
	 */
	public boolean connectNetwork() {

	}

	/* only works if phone is on.
	 * returns a value in range [1,5] if connected to network
	 * otherwise returns 0
	 */
	public int getSignalStrength() {

	}

	/* only works if phone is on.
	 * sets the signal strength and may change the network connection status to on or off
	 * signal of 0 disconnects network
	 * signal [1,5] can connect to network if not already connected
	 * if the signal is set outside the range [0,5], nothing will occur and will return false
	 */
	public boolean setSignalStrength(int x) {

    }

	/* each charge increases battery by 10
	 * the phone has overcharge protection and cannot exceed 100
	 * returns true if the phone was charged by 10
	 */
	public boolean chargePhone() {

	}

	/* Use the phone which costs k units of battery life.
	 * if the activity exceeds the battery life, the battery automatically
	 * becomes zero and the phone turns off.
	 */
	public void usePhone(int k) {

	}

}
