package module;

import java.io.PrintStream;
import java.io.Serializable;
import java.io.Writer;
import java.net.UnknownHostException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
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
import ow.tool.util.shellframework.Interruptible;

public class OwDHT<V extends Serializable> extends TimerTask implements DHT, Runnable, Interruptible, EmulatorControllable {
	private PrintStream out;
	private DHT<V> dht;
	
	public OwDHT(DHTConfiguration config) throws Exception {
		this.dht = DHTFactory.getDHT(config); //throws Exception
	}
	public OwDHT(DHTConfiguration config, ID selfID) throws Exception {
		this.dht = DHTFactory.getDHT(config, selfID); //throws Exception
	}
	public OwDHT(short applicationID, short applicationVersion, DHTConfiguration config) throws Exception {
		this.dht = DHTFactory.getDHT(applicationID, applicationVersion, config); //throws Exception
	}
	public OwDHT(short applicationID, short applicationVersion, DHTConfiguration config, ID selfID) throws Exception {
		this.dht = DHTFactory.getDHT(applicationID, applicationVersion, config, selfID); //throws Exception
	}
	public OwDHT(DHTConfiguration config, RoutingService routingSvc) throws Exception {
		this.dht = DHTFactory.getDHT(config, routingSvc); //throws Exception
	}
	public void setPrintStream(PrintStream out) {
		if(out != null) {
			this.out = out;
		} else {
			this.out = System.out;
		}
	}
	public static void main(String[] args) {
		
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
	@Override
	public Set get(ID arg0) throws RoutingException {
		// TODO Auto-generated method stub
		return null;
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
	public Set put(String key, V value) {
		
		return null;
	}
	public Set put(String key, V[] values) {
		Map<ID, Set<V>> valueMap = new HashMap<ID, Set<V>>();
		List<String> keyList = new ArrayList<String>();
		
		return null;
	}
	@Override
	public Set[] put(PutRequest[] arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set put(ID arg0, Serializable arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set put(ID arg0, Serializable[] arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
