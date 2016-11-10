package module;

import java.io.PrintStream;
import java.io.Serializable;
import java.io.Writer;
import java.net.UnknownHostException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;

import ow.dht.ByteArray;
import ow.dht.DHT;
import ow.dht.DHTConfiguration;
import ow.dht.DHTFactory;
import ow.dht.ValueInfo;
import ow.id.ID;
import ow.id.IDAddressPair;
import ow.messaging.MessagingAddress;
import ow.routing.RoutingAlgorithmConfiguration;
import ow.routing.RoutingException;
import ow.routing.RoutingResult;
import ow.routing.RoutingService;
import ow.tool.emulator.EmulatorControllable;
import ow.tool.util.shellframework.CommandUtil;
import ow.tool.util.shellframework.Interruptible;
import ow.tool.util.shellframework.Shell;

public class OwDHT<V extends Serializable> extends TimerTask
		implements DHT, Runnable, Interruptible, EmulatorControllable {
	private PrintStream out = System.out;
	private DHT<V> dht;
	private boolean showStatus = true;

	public OwDHT(DHTConfiguration config) throws Exception {
		this.dht = DHTFactory.getDHT(config); // throws Exception
	}

	public OwDHT(DHTConfiguration config, ID selfID) throws Exception {
		this.dht = DHTFactory.getDHT(config, selfID); // throws Exception
	}

	public OwDHT(short applicationID, short applicationVersion, DHTConfiguration config) throws Exception {
		this.dht = DHTFactory.getDHT(applicationID, applicationVersion, config); // throws
																					// Exception
	}

	public OwDHT(short applicationID, short applicationVersion, DHTConfiguration config, ID selfID) throws Exception {
		this.dht = DHTFactory.getDHT(applicationID, applicationVersion, config, selfID); // throws
																							// Exception
	}

	public OwDHT(DHTConfiguration config, RoutingService routingSvc) throws Exception {
		this.dht = DHTFactory.getDHT(config, routingSvc); // throws Exception
	}

	public void setPrintStream(PrintStream out) {
		if (out != null)
			this.out = out;
	}

	public void enableShowStatus() {
		this.showStatus = true;
	}

	public void disableShowStatus() {
		this.showStatus = false;
	}

	public static void main(String[] args) {
		OwDHT<String> test;
		try {
			test = new OwDHT<String>(DHTFactory.getDefaultConfiguration());
		} catch (Exception e) {
			System.err.println("An Exception thrown:");
			e.printStackTrace();
			return;
		}
		String[] valueStr = {"sampleValue1", "sampleValue2"};
		test.put("sampleKey", valueStr);
	}

	private void start(String[] args) {
	}

	@Override
	public void invoke(String[] arg, PrintStream out) throws Throwable {
		// TODO Auto-generated method stub
		this.out = out;
	}

	@Override
	public Writer getControlPipe() {
		// TODO Auto-generated method stub
		return null;
	}

	private void init() {

	}

	@Override
	public void interrupt() {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearRoutingTable() {
		// TODO Auto-generated method stub

	}

	@Override
	public ID[] getLastKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoutingResult[] getLastRoutingResults() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoutingAlgorithmConfiguration getRoutingAlgorithmConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoutingService getRoutingService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRoutingTableString(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDAddressPair getSelfIDAddressPair() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessagingAddress joinOverlay(String arg0) throws UnknownHostException, RoutingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessagingAddress joinOverlay(String arg0, int arg1) throws UnknownHostException, RoutingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStatCollectorAddress(String arg0, int arg1) throws UnknownHostException {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void suspend() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearDHTState() {
		// TODO Auto-generated method stub

	}

	public Set get(String keyStr) {
		ID key = ID.parseID(keyStr, this.dht.getRoutingAlgorithmConfiguration().getIDSizeInByte());
		StringBuilder sb = new StringBuilder();
		Set<ValueInfo<V>> value;
		try {
			value = this.get(key);
	
		}catch(RoutingException e) {
			sb.append("routing failed: ").append(key).append(Shell.CRLF);
			System.err.print(sb);
			this.out.print(sb);
			this.out.flush();
		}
		return value;
	}
	@Override
	public Set get(ID key) throws RoutingException {
		// TODO Auto-generated method stub
		Set<ValueInfo<V>> value = this.dht.get(key);
		return value;
	}

	@Override
	public Set[] get(ID[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DHTConfiguration getConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set getGlobalKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set getGlobalValues(ID arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set getLocalKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set getLocalValues(ID arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set put(String keyStr, V value) {
		ID key = ID.parseID(keyStr, dht.getRoutingAlgorithmConfiguration().getIDSizeInByte());
		return this.put(key, value);
	}

	public Set<ValueInfo<V>> put(String keyStr, V[] values) {
		ID key = ID.parseID(keyStr, dht.getRoutingAlgorithmConfiguration().getIDSizeInByte());
		return this.put(key, values);
	}

	@Override
	public Set[] put(PutRequest[] arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set put(ID key, Serializable value) {
		// TODO Auto-generated method stub
		Set<ValueInfo<V>> valueInfo = null;
		try {
			valueInfo = dht.put(key, (V)value);
			if(value == null) {
				this.out.print("routing failed: " + key + Shell.CRLF);
			}
			if(this.showStatus) {
				this.out.print(CommandUtil.buildStatusMessage(dht, -1));
				this.out.flush();
			} 
		}catch(Exception e) {
			this.out.print("An exception thrown:" + Shell.CRLF);
			System.err.print("A exception thrown:" + Shell.CRLF);
			e.printStackTrace(this.out);
			e.printStackTrace(System.err);
			this.out.flush();
			System.err.flush();
		}
		
		return valueInfo;
	}

	@Override
	public Set put(ID key, Serializable[] values) {
		// TODO Auto-generated method stub
		Set<ValueInfo<V>> value = null;
		try {
			value = dht.put(key, (V[])values);
			if (value == null) {
				this.out.print("routing failed: " + key + Shell.CRLF);
			}
			if (this.showStatus) {
				this.out.print(CommandUtil.buildStatusMessage(dht, -1));
				this.out.flush();
			}
		} catch (Exception e) {
			this.out.print("An exception thrown:" + Shell.CRLF);
			System.err.print("A exception thrown:" + Shell.CRLF);
			e.printStackTrace(this.out);
			e.printStackTrace(System.err);
			this.out.flush();
			System.err.flush();
		}
		return value;
	}

	@Override
	public Set remove(ID arg0, ByteArray arg1) throws RoutingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set[] remove(RemoveRequest[] arg0, ByteArray arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set remove(ID arg0, Serializable[] arg1, ByteArray arg2) throws RoutingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set remove(ID arg0, ID[] arg1, ByteArray arg2) throws RoutingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ByteArray setHashedSecretForPut(ByteArray arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long setTTLForPut(long arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
