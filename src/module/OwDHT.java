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
import java.util.Queue;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

import ow.dht.ByteArray;
import ow.dht.DHT;
import ow.dht.DHTConfiguration;
import ow.dht.DHTFactory;
import ow.dht.ValueInfo;
import ow.id.ID;
import ow.id.IDAddressPair;
import ow.messaging.MessagingAddress;
import ow.messaging.Signature;
import ow.routing.RoutingAlgorithmConfiguration;
import ow.routing.RoutingException;
import ow.routing.RoutingResult;
import ow.routing.RoutingService;
import ow.tool.emulator.EmulatorControllable;
import ow.tool.util.shellframework.CommandUtil;
import ow.tool.util.shellframework.Interruptible;
import ow.tool.util.shellframework.Shell;
import ow.util.HighLevelService;
import ow.util.HighLevelServiceConfiguration;

public class OwDHT<V extends Serializable> extends TimerTask implements Runnable, Interruptible, EmulatorControllable {
	public static byte MODULE_ID_DHT = 5;
	private PrintStream out = System.out;
	private DHT<V> dht;
	private boolean showStatus = false;

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

	// TODO make status logger
	public static void main(String[] args) {
		OwDHT<String> test;
		StringBuilder sb = new StringBuilder();
		DHTConfiguration config = DHTFactory.getDefaultConfiguration();
//		MessagingUtility.HostAndPort hostAndPort = MessagingUtility.parseHostnameAndPort("192.168.132.108:3997",config.getSelfPort());
//		System.out.println(hostAndPort.getHostName());
		config.setSelfAddress("192.168.132.108");
		config.setSelfPort(3997);
		try {
			test = new OwDHT<String>(OwDHT.MODULE_ID_DHT, (short) 0x10000, config);
		} catch (Exception e) {
			System.err.println("An Exception thrown:");
			e.printStackTrace();
			return;
		}
		test.enableShowStatus();
		test.put("sampleKey", "sampleValue");
		while (true)
			;
	}
	public void invoke(String[] arg, PrintStream out) throws Throwable {
		// TODO Auto-generated method stub
		this.out = out;
	}

	@Override
	public Writer getControlPipe() {
		// TODO Auto-generated method stub
		return null;
	}

	public void init(String contactHost, int port) throws UnknownHostException {
		HighLevelService svc = this.dht;
		HighLevelServiceConfiguration config = svc.getConfiguration();
		MessagingAddress contactAddr = null;
		try {
			contactAddr = svc.joinOverlay(contactHost, port);
		} catch (UnknownHostException e) {
			StringBuilder sb = new StringBuilder();
			sb.append("Hostname resolution failed on ").append(svc.getSelfIDAddressPair().getAddress())
					.append(Shell.CRLF);
			this.out.print(sb);
			System.err.print(sb);
			this.out.flush();
			throw e;
		} catch (RoutingException e) {
			StringBuilder sb = new StringBuilder();
			sb.append("routing failed on ").append(svc.getSelfIDAddressPair()).append(Shell.CRLF);
			e.printStackTrace();
			this.out.print(sb);
			System.err.print(sb);
		}
		if (contactAddr != null) {
			this.out.print("contact: " + contactAddr.getHostAddress() + ":" + contactAddr.getPort() + Shell.CRLF);
		} else {
			this.out.print("joined failed." + Shell.CRLF);
		}
		if (showStatus) {
			this.out.print(CommandUtil.buildStatusMessage(this.dht, -1));
		}
		this.out.flush();
	}

	@Override
	public void interrupt() {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public Set get(String keyStr) {
		ID key = ID.parseID(keyStr, this.dht.getRoutingAlgorithmConfiguration().getIDSizeInByte());
		StringBuilder sb = new StringBuilder();
		Set<ValueInfo<V>> value;

		try {
			value = this.dht.get(key);
			return value;

		} catch (RoutingException e) {
			sb.append("routing failed: ").append(key).append(Shell.CRLF);
			System.err.print(sb);
			this.out.print(sb);
			this.out.flush();
		}
		return null;
	}

	public Set[] get(String[] keysStr) {
		Queue<ID> requestQueue = new ConcurrentLinkedQueue<ID>();
		for (int i = 0; i < keysStr.length; i++) {
			ID key = ID.parseID(keysStr[i], this.dht.getRoutingAlgorithmConfiguration().getIDSizeInByte());
			requestQueue.offer(key);
		}
		ID[] keys = new ID[requestQueue.size()];
		for (int i = 0; i < keys.length; i++)
			keys[i] = requestQueue.poll();
		Set<ValueInfo<V>>[] values;
		values = this.dht.get(keys);

		return values;
	}

	public Set getLocalKeys() {
		// TODO Auto-generated method stub
		return null;
	}

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

	public Set put(ID key, Serializable value) {
		// TODO Auto-generated method stub
		Set<ValueInfo<V>> valueInfo = null;
		try {
			valueInfo = dht.put(key, (V) value);
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

		return valueInfo;
	}

	public Set put(ID key, Serializable[] values) {
		// TODO Auto-generated method stub
		Set<ValueInfo<V>> value = null;
		try {
			value = dht.put(key, (V[]) values);
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

}
